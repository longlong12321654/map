package com.hndist.cim.gisserver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hndist.cim.gisserver.common.AjaxResult;
import com.hndist.cim.gisserver.entity.*;
import com.hndist.cim.gisserver.service.IPublishingService;
import com.hndist.cim.gisserver.utils.ShapeUtil;
import com.hndist.cim.gisserver.utils.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 河南数慧信息技术有限公司
 * @version V1.0.0
 * @ClassName: Publishing
 * @Description: TODO
 * @date 2022/11/17 10:43
 * @Copyright: www.hndist.com All rights reserved.
 * 注意：本内容仅限于共享研发中心内部传阅，禁止外泄以及用于其他的商业目
 */
@RestController
//@Api(tags = "CIM - GIS服务")
@RequestMapping("/cim/rest/parsing")
public class GISServicesController {

    @Autowired
    private IPublishingService publishingService;

    /*
     * @author WY 
     * @Description: 解析国标文件
     * @date 2023/01/31 08:51
     * @Param [spatialFileList]
     * @return java.util.List<java.lang.String> 
     */
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "mapService", value = "时空数据文件列表",required = true, dataType = "List<MapLayer>")
//    )
//    @ApiOperation("解析服务 - 国标文件")
    @PostMapping("txtFile")
    public AjaxResult parsingTxtFile(@RequestBody Parsing parsing) throws IOException {
        //判断文件是否为空
        if (parsing==null) {
            return AjaxResult.error("文件不为空");
        }
        //解析国标文件
        Features features = ShapeUtil.parsingTxtFile(parsing.getPath());
        //返回国标数据
        ArrayList<Feature> featureList = features.getFeatureList();
        JSONArray data = (JSONArray)JSONArray.toJSON(featureList);
        //如果是类型是shmap，则处理参数为geojson
        if (StringUtils.isEmpty(parsing.getType()) || "shmap".equals(parsing.getType())) {
            // 遍历
            data.stream().forEach(jbt-> {
                JSONObject object = (JSONObject) jbt;
                JSONObject geometry = object.getJSONObject("geometry");
                geometry.put("type", "Polygon");
                geometry.put("coordinates", geometry.getJSONArray("rings"));
                // 移除元素
                geometry.remove("rings");
            });
        }
        return AjaxResult.success(data);
    }

    /*
     * @author WY
     * @Description: 解析GeoJson文件
     * @date 2023/01/31 08:51
     * @Param [spatialFileList]
     * @return java.util.List<java.lang.String>
     */
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "mapService", value = "时空数据文件列表",required = true, dataType = "List<MapLayer>")
//    )
//    @ApiOperation("解析服务 - GeoJson文件")
    @PostMapping("geoJsonFile")
    public AjaxResult parsingGeoJsonFile(@RequestBody Parsing parsing) throws IOException {
        //判断文件是否为空
        if (parsing == null) {
            return AjaxResult.error("文件不为空");
        }
        //解析jeojson文件
        String jsonString = new String(Files.readAllBytes(Paths.get(parsing.getPath())));
        return AjaxResult.success(JSONObject.parse(jsonString));
    }

    /*
     * @author WY
     * @Description: 解析服务 - Excel文件
     * @date 2023/01/31 08:55
     * @Param [spatialFileList]
     * @return java.util.List<java.lang.String>
     */
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "mapService", value = "时空数据文件列表",required = true, dataType = "List<MapLayer>")
//    )
//    @ApiOperation("解析服务 - Excel文件")
    @PostMapping("excelFile")
    public AjaxResult parsingExcelFile(@RequestBody Parsing parsing) throws Exception {
        //判断文件是否为空
        if (parsing == null) {
            return AjaxResult.error("文件不为空");
        }
            InputStream stream = new FileInputStream(parsing.getPath());
            Workbook wb = new HSSFWorkbook(stream);
            // 获取工作表数量，默认1
            //int sheetSize = wb.getNumberOfSheets();
            //List<List<List>> sheetList = new ArrayList<>();
            //for (int i = 0; i < sheetSize; i++) {
            List<List> outerList = new ArrayList<>();
            Sheet sheet = wb.getSheetAt(0);
            for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                List<Double> list = new ArrayList<>();
                for (int c = 0; c < sheet.getRow(j).getLastCellNum(); c++) {
                    Cell cell = sheet.getRow(j).getCell(c);
                    cell.setCellType(CellType.STRING);
                    String str = cell.getStringCellValue();
                    if (c > 0 && c < 3) {
                        list.add(Double.valueOf(str));
                    }
                }
                outerList.add(list);
            }
            //sheetList.add(outerList);
            //}

            //封装geoJson
            HashMap<String, Object> geometry = new HashMap<>(16);
            geometry.put("coordinates", outerList);
            geometry.put("type", "LineString");

            HashMap<String, Object> obj = new HashMap<>(16);
            obj.put("type", "Feature");
            obj.put("geometry", geometry);

            HashMap<String, Object> data = new HashMap<>(16);
            data.put("type", "FeatureCollection");
            ArrayList<Object> featureslist = new ArrayList<>();
            featureslist.add(obj);
            data.put("features", featureslist);

            return AjaxResult.success(data);
        }

}
