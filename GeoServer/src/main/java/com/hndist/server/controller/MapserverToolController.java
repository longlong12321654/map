package com.hndist.server.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hndist.cim.gisserver.utils.HttpUtil;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.server.constant.BusinessConstant;
import com.hndist.server.domain.CimMapmark;
import com.hndist.server.domain.CimServer;
import com.hndist.server.service.ICimMapmarkService;
import com.hndist.server.service.ICimServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author zy
 * @version 1.1
 * @className GisToolController
 * @description TODO
 * @date 2022/12/1 15:09
 **/
@Api(tags = "Mapserver工具")
@RequestMapping("/mapserverTool")
@Controller
public class MapserverToolController {

    @Autowired
    private ICimMapmarkService mapmarkService;
    @Autowired
    private ICimServerService serverService;


    /**
     * 读取gis服务目录
     *
     * @param serverId gis服务ID
     * @param mapServerFlag 服务类型 1mapserver 2arcGisServer
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "读取arcgis服务目录", notes = "读取arcgis服务目录")
    @GetMapping("/readGisData")
    public AjaxResult readArcGISData(@RequestParam(name = "serverId") String serverId,
                                     @RequestParam(name = "mapServerFlag", required = false) String mapServerFlag) {
        CimServer bsServer = serverService.selectCimServerById(serverId);
        if (bsServer == null || StringUtils.isEmpty(bsServer.getIp())) {
            return AjaxResult.error("数据不存在");
        }

        String ip = bsServer.getIp();
        String port = bsServer.getPort();
        if (StringUtils.isEmpty(port)) {
            port = "6080";
        }

        // 拼接请求url
        StringBuilder sbf = new StringBuilder();
        sbf.append(BusinessConstant.TRANSPORT_PROTOCOL);
        sbf.append(ip);
        sbf.append(BusinessConstant.COLON);
        sbf.append(port);
        sbf.append(BusinessConstant.ARCGIS_STATIC_PATH);
        //String serverPath = BusinessConstant.TRANSPORT_PROTOCOL + ip + port + BusinessConstant.ARCGIS_STATIC_PATH;
        return AjaxResult.success(treeChildrenJson(sbf.toString(), mapServerFlag ));

    }

    /**
     * 导出shp文件
     * @return
     */
    @ApiOperation(value = "导出shp文件", notes = "geoJson2Shp")
    @GetMapping("/geoJson2Shp")
    public AjaxResult geoJson2Shp(String mapMarkId, HttpServletResponse response) {
        CimMapmark cimMapmark = mapmarkService.selectCimMapmarkById(mapMarkId);
        String drawjson = cimMapmark.getDrawjson();
        if (StringUtils.isEmpty(drawjson)) {
            return AjaxResult.error("标绘JSON为空，不可导出shp文件！");
        }
//        Shp2GeojsonUtils.geoJson2Shape(JSONObject.parseObject(drawjson),response);
        return AjaxResult.success();
    }

    /**
     * 递归获取解析json
     *
     * @param serverPath 服务路径
     * @return
     */
    private JSONObject treeChildrenJson(String serverPath, String serverType) {
        // url后缀
        String suffix = "?f=json";
        //初始化对象
        JSONObject newObj = new JSONObject();
        // 初始化目录
        JSONArray newfloder = new JSONArray();
        // 拼接后缀
        String path = serverPath + suffix;
        // 调用接口
        String s = HttpUtil.sendGet(path);
        // 目录层级
        //level = level +1;
        if (org.apache.commons.lang3.StringUtils.isNotBlank(s)) {

            //解析json
            JSONObject jsonObject = JSONObject.parseObject(s);
            // 获取目录
            JSONArray folders = jsonObject.getJSONArray("folders");
            // 获取服务
            JSONArray services = jsonObject.getJSONArray("services");
            // 目录不为空，则拼接url，继续获取子目录json
            if (folders != null) {
                for (int i = 0; i < folders.size(); i++) {
                    JSONObject floderObj = new JSONObject();
                    String folder = folders.getString(i);
                    //http://192.168.1.9:6080/arcgis/rest/services/jy
                    String serverPathNew = null;
                    try {
                        serverPathNew = serverPath + "/" + URLEncoder.encode(folder, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    JSONObject treeChildrenJson = treeChildrenJson(serverPathNew, serverType);
                    floderObj.put("name", folder);
                    floderObj.put("value", treeChildrenJson);
                    newfloder.set(i, floderObj);

                }
            }
            newObj.put("folders", newfloder);
            newObj.put("services", services);
        }
        return newObj;
    }
}
