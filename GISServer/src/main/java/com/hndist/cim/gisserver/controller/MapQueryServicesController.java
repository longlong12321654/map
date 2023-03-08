package com.hndist.cim.gisserver.controller;

import com.alibaba.fastjson.JSON;
import com.hndist.cim.gisserver.entity.LayerQuery;
import com.hndist.cim.gisserver.entity.MapService;
import com.hndist.cim.gisserver.service.IPublishingService;
import com.hndist.cim.gisserver.utils.FreemarkerUtil;
import com.hndist.cim.gisserver.utils.HttpUtil;
import com.hndist.cim.gisserver.utils.HttpUtil;
import com.hndist.cim.gisserver.utils.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.XML;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

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
//@Api(tags = "CIM - 地图查询接口")
@RequestMapping("/cim/rest/")
@Log4j2
public class MapQueryServicesController {

    @Autowired
    private IPublishingService publishingService;

    /*
     * @author WY
     * @Description: 地图服务 - 空间/属性查询
     * @date 2022/11/19 10:54
     * @Param [mapService]
     * @return com.hndist.cim.gisserver.entity.MapService
     */
//    @ApiOperation("1. 地图查询 - 空间查询")
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "f", value = "格式化类型", required = false, dataType = "String")

    @GetMapping(value = {"/services/{serverName}/MapServer/{layerName}/query", "/services/{folder}/{serverName}/MapServer/{layerName}/query"})
    @ResponseBody
    public ResponseEntity layer(HttpServletRequest request, Boolean isPublishing, @PathVariable(required = false) String folder, @PathVariable String serverName, @PathVariable String layerName, LayerQuery layerQuery, String f) throws IOException {
        //判断文件夹
        if (folder != null) {
            folder = folder + "/" + serverName + ".map";
        } else {
            folder = serverName + ".map";
        }
        //判断是否是发布对象
        if (isPublishing == null) {
            isPublishing = true;
        }
        //获取服务列表
        List<MapService> mapServiceList = publishingService.getMapServiceList(folder, isPublishing);
        //判断是否数返回JSON
        if (!"json".equalsIgnoreCase(f)) {
            //地图服务模版
            String ftl_mapserver = "service/query.html";
            HashMap<String, Object> datas = new HashMap<String, Object>();
            datas.put("mapServer", mapServiceList.get(0));
            datas.put("layerName",layerName);
            datas.put("projectName","SHMap");
            datas.put("version","V1.0");
            HttpHeaders httpHeaders = new HttpHeaders();
            // 不是用缓存
            httpHeaders.setCacheControl(CacheControl.noCache());
            httpHeaders.setPragma("no-cache");
            httpHeaders.setExpires(0L);
            //httpHeaders.add("Content-Type","application/xml; charset=utf-8");

            //生成服务页面
            String context = FreemarkerUtil.generateString(ftl_mapserver, datas);
            return new ResponseEntity(context, httpHeaders, HttpStatus.OK);
        }
        return null;
    }

}
