package com.hndist.cim.gisserver.controller;

import com.hndist.cim.gisserver.entity.EngineeringQuery;
import com.hndist.cim.gisserver.entity.MapService;
import com.hndist.cim.gisserver.service.IPublishingService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
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
//@Api(tags = "CIM - 地图发布管理")
@RequestMapping("/cim/rest/publishing")
public class PublishingController {

    @Autowired
    private IPublishingService publishingService;

    /*
     * @author WY 
     * @Description: 发布地图服务
     * @date 2022/11/17 11:01
     * @Param [spatialFileList]
     * @return java.util.List<java.lang.String> 
     */
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "mapService", value = "时空数据文件列表",required = true, dataType = "List<MapLayer>")
//    )
//    @ApiOperation("地图服务 - 发布")
    @PostMapping("publishMapService")
    public MapService publishMapService(@RequestBody MapService mapService) throws IOException {
        //判断文件是否为空
        if (mapService==null) {
            return null;
        }
        //发布地图服务
        return publishingService.publishingServices(mapService,true);
    }

    /*
     * @author WY
     * @Description: 删除地图服务
     * @date 2022/11/18 10:54
     * @Param [mapService]
     * @return com.hndist.cim.gisserver.entity.MapService
     */
//    @ApiOperation("地图服务 - 删除")
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "mapServerName", value = "地图服务名称",required = true, dataType = "HashMap<String, String>")
//    )
    @PostMapping("delMapService")
    @ResponseBody
    public boolean delMapService(@RequestBody EngineeringQuery query) throws IOException {
        String mapServerName = query.getMapServerName();
        //判断地图服务名称是否为空
        if (mapServerName==null ) {
            return false;
        }
        return publishingService.delServices(mapServerName,true);
    }

    /*
     * @author WY 
     * @Description: 获取地图服务列表
     * @date 2022/11/18 11:01
     * @Param [folder] 
     * @return java.util.List<com.hndist.cim.gisserver.entity.MapService> 
     */
//    @ApiOperation("地图服务 - 获取列表")
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "folder", value = "服务目录",required = false, dataType = "HashMap<String, String>")
//    )
    @PostMapping("getMapServiceList")
    @ResponseBody
    public List<MapService> getMapServiceList(@RequestBody EngineeringQuery query) throws IOException {
        String folder = query.getFolder();
        //发布地图服务
        return publishingService.getMapServiceList(folder,true);
    }

}
