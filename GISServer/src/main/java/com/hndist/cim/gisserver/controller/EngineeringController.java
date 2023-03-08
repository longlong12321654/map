package com.hndist.cim.gisserver.controller;

import com.hndist.cim.gisserver.entity.EngineeringQuery;
import com.hndist.cim.gisserver.entity.MapLayer;
import com.hndist.cim.gisserver.entity.MapService;
import com.hndist.cim.gisserver.service.IPublishingService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 河南数慧信息技术有限公司
 * @version V1.0.0
 * @ClassName: Engineering
 * @Description: 地图工程管理
 * @date 2022/11/17 10:43
 * @Copyright: www.hndist.com All rights reserved.
 * 注意：本内容仅限于共享研发中心内部传阅，禁止外泄以及用于其他的商业目
 */
@RestController
//@Api(tags = "CIM - 地图工程管理")
@RequestMapping("/cim/rest/engineering")
public class EngineeringController {

    @Autowired
    private IPublishingService publishingService;

    /*
     * @author WY
     * @Description: 获取空间数据列表
     * @date 2022/11/17 11:00
     * @Param [path]
     * @return java.util.ArrayList<com.hndist.cim.gisserver.entity.MapLayer>
     */
//    @ApiOperation("时空数据 - 获取列表")
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "path", value = "磁盘目录",required = true, dataType = "String")
//    )
//    @PostMapping("getSpatialDataList")
//    public ArrayList<MapLayer> getSpatialData(@RequestBody EngineeringQuery query) throws IOException {
//        String path = query.getPath();
//        //获取解析到的文件列表
//        return SpatialDataUtil.getSpatialDataList(path);
//    }

    /*
     * @author WY
     * @Description: 创建地图工程
     * @date 2022/11/18 10:54
     * @Param [mapService]
     * @return com.hndist.cim.gisserver.entity.MapService
     */
//    @ApiOperation("地图工程 - 创建")
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "mapService", value = "地图服务对象",required = true, dataType = "List<SpatialFile>")
//    )
    @PostMapping("createMapEngineering")
    public MapService createMapEngineering(@RequestBody MapService mapService) throws IOException {
        //判断地图服务对象是否存在
        if ( mapService==null ) {
            return null;
        }
        return publishingService.publishingServices(mapService,false);
    }

    /*
     * @author WY
     * @Description: 获取地图工程
     * @date 2022/11/18 10:54
     * @Param [mapService]
     * @return com.hndist.cim.gisserver.entity.MapService
     */
//    @ApiOperation("地图工程 - 查询")
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "mapEngineeringName", value = "地图工程名称",required = true, dataType = "HashMap<String, String>")
//    )
    @PostMapping("getMapEngineering")
    public MapService getMapEngineering(@RequestBody EngineeringQuery query) throws IOException {
        String mapEngineeringName = query.getMapEngineeringName();
        //判断地图工程名称是否为空
        if (mapEngineeringName==null ) {
            return null;
        }
        //获取服务列表
        List<MapService> mapServiceList = publishingService.getMapServiceList(mapEngineeringName,false);
        //返回第一个服务对象
        return mapServiceList!=null&&mapServiceList.size()>0?mapServiceList.get(0):null;
    }

    /*
     * @author WY
     * @Description: 获取地图工程列表
     * @date 2022/11/18 10:54
     * @Param [mapService]
     * @return com.hndist.cim.gisserver.entity.MapService
     */
//    @ApiOperation("地图工程 - 获取列表")
    @PostMapping("getMapEngineeringList")
    public List<MapService> getMapEngineeringList() throws IOException {
        return publishingService.getMapServiceList(null,false);
    }

    /*
     * @author WY
     * @Description: 删除地图工程
     * @date 2022/11/18 10:54
     * @Param [mapService]
     * @return com.hndist.cim.gisserver.entity.MapService
     */
//    @ApiOperation("地图工程 - 删除")
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "mapEngineeringName", value = "地图工程名称",required = true, dataType = "HashMap<String, String>")
//    )
    @PostMapping("delMapEngineering")
    public boolean createMapEngineering(@RequestBody EngineeringQuery query) throws IOException {
        String mapEngineeringName = query.getMapEngineeringName();
        //判断地图工程名称是否为空
        if (mapEngineeringName==null ) {
            return false;
        }
        return publishingService.delServices(mapEngineeringName,false);
    }

}
