package com.hndist.server.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.hndist.cim.gisserver.entity.Extent;
import com.hndist.framework.utils.StringUtils;
import com.hndist.server.constant.BusinessConstant;
import com.hndist.server.constant.SysServiceengineEnum;
import com.hndist.server.domain.CimServer;
import com.hndist.server.service.ICimServerService;
import com.hndist.server.util.HttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author FWY
 * @version V1.0.0
 * @ClassName: SHMapServerController
 * @Description: TODO
 * @date 2022/7/11 10:08
 * @Copyright: www.hndist.com All rights reserved.
 * 注意：本内容仅限于研发中心内部传阅，禁止外泄以及用于其他的商业目
 */
@Api(tags = "代理ArcGisServer地图服务")
@RestController
@RequestMapping("/cimforward")
@Log4j2
public class ArcGisServerController {
//    /**
//     * mapserver服务
//     */
//    @Value("${hndistmap.mapserver}")
//    private String mapserver;
    @Autowired
    private ICimServerService cimServerService;

    //临时使用(后续采用与NGINX结合的形式提高性能)
    //String serverUrl = "http://192.168.1.9:6080";

    /**
     * @return BaseController 返回类型
     * @throws Exception 抛出自定义异常
     * @Title: MapServer
     * @Description: 新增实体类信息
     */
    @ApiOperation(value = "转发服务")
    @GetMapping("/{serverName}/**")
    @ResponseBody
    public ResponseEntity mapServer(HttpServletRequest request,  @PathVariable String serverName) throws Exception {
        if (StringUtils.isEmpty(serverName)) {
            return new ResponseEntity<>("服务名称不为空", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //地图服务地址(可采用分布式资源池)
        String mapName = request.getServletPath();
        if (StringUtil.isNotEmpty(mapName) && mapName.length() > 24) {

            CimServer cimServer = cimServerService.selectCimServerByName(serverName);
            mapName = mapName.substring(12).replace(serverName,"");
            String serverUrl = null;
            if (Objects.isNull(cimServer)) {
                return new ResponseEntity<>("服务不存在", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            // 判断服务引擎是否为Hndist Server
            if (SysServiceengineEnum.HndistServer.getIndex().equals(cimServer.getServiceengine())) {
                serverUrl = BusinessConstant.TRANSPORT_PROTOCOL + cimServer.getIp() + BusinessConstant.COLON + cimServer.getPort()+"/SHMap";
            } else if(SysServiceengineEnum.ArcGISServer.getIndex().equals(cimServer.getServiceengine())) {
                serverUrl = BusinessConstant.TRANSPORT_PROTOCOL + cimServer.getIp() + BusinessConstant.COLON + cimServer.getPort();
            } else {
                return new ResponseEntity<>("未知服务引擎，请联系管理员", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            //组合URL 支持域名
            StringBuffer serverURL = new StringBuffer();
            serverURL.append(serverUrl);
            serverURL.append(mapName);
            //验证图层参数
            HashMap<String, String> params = HttpUtils.getParameterNames(request);

            //封装返回对象
            try {
                HttpHeaders httpHeaders = new HttpHeaders();
                // 不是用缓存
                httpHeaders.setCacheControl(CacheControl.noCache());
                httpHeaders.setPragma("no-cache");
                httpHeaders.setExpires(0L);
                if (params.isEmpty() || (!params.containsKey("f") && !params.containsKey("request") && !params.get("request").equalsIgnoreCase("GetFeatureInfo"))) {
                    httpHeaders.setContentType(MediaType.IMAGE_JPEG);
                    return new ResponseEntity<>(HttpUtils.doGet(serverURL, params, request), httpHeaders, HttpStatus.OK);
                } else {
                    String context = null;
                    String result = HttpUtils.doGet(HttpUtils.encodeURL(serverURL.toString()), params);
                    context = result;
                    if (StringUtils.isNotEmpty(context)) {
                        JSONObject jsonObj = new JSONObject();
                        JSONArray folderArray = new JSONArray();
                        JSONArray servicesArray = new JSONArray();
                        // 如果是ArcGIS Server
                        if (SysServiceengineEnum.ArcGISServer.getIndex().equals(cimServer.getServiceengine())) {
                            JSONObject arcObj = JSONObject.parseObject(context);
                            if (arcObj.containsKey("folders")) {
                                JSONArray folders = arcObj.getJSONArray("folders");
                                for (int i = 0; i < folders.size(); i++) {
                                    String name = folders.getString(i);
                                    folderArray.set(i,name);
                                }
                                folderArray.addAll(folders);
                            }

                            if (arcObj.containsKey("services")) {
                                JSONArray services = arcObj.getJSONArray("services");
                                for (int i = 0; i < services.size(); i++) {
                                    JSONObject serviceObj = services.getJSONObject(i);
                                    String name = serviceObj.getString("name");
                                    if (name.contains("/")) {
                                        name=name.substring(name.indexOf("/")+1);
                                    }
                                    String newServerUrl = serverURL + "/" + name +"/MapServer";
                                    newServerUrl = HttpUtils.encodeURL(newServerUrl);
                                    String newresult = HttpUtils.doGet(newServerUrl, params);
                                    JSONObject serviceinfo = JSONObject.parseObject(newresult);
                                    serviceObj.put("serviceEngine", "arcgis");
                                    String arcGisUrl = mapName + "/" + serviceinfo.getString("name") + "/MapServer";
                                    serviceObj.put("serviceurl", arcGisUrl);
                                    JSONObject fullExtent = serviceinfo.getJSONObject("fullExtent");
                                    if (fullExtent != null) {
                                        serviceObj.put("fullExtent", fullExtent);
                                    } else {
                                        Extent extent = new Extent();
                                        extent.setXmin((double) -180);
                                        extent.setYmin((double) -90);
                                        extent.setXmax((double) 180);
                                        extent.setYmax((double) 90);
                                        serviceObj.put("fullExtent", extent);
                                    }
                                    services.set(i, serviceObj);

                                }
                                servicesArray.addAll(services);
                            }
                            jsonObj.put("folders",folderArray);
                            jsonObj.put("services",servicesArray);

                        } else if (SysServiceengineEnum.HndistServer.getIndex().equals(cimServer.getServiceengine())){
                            // 如果是Hndist Server
                            JSONArray services = JSONArray.parseArray(context);
                            for (int i = 0; i < services.size(); i++) {
                                JSONObject service = services.getJSONObject(i);
                                String filePath = service.getString("filePath");
                                String mapLayerList = service.getString("mapLayerList");
                                // 是目录
                                if (StringUtils.isEmpty(mapLayerList)) {
                                    // 3dtile文件集合
                                    if (filePath.endsWith("3dmap")) {
                                        JSONObject serviceObj = new JSONObject();
                                        serviceObj.put("name", service.getString("name"));
                                        serviceObj.put("serviceEngine", service.getString("serviceEngine"));
                                        serviceObj.put("height", service.getString("height"));
                                        String url3d = mapName + "/" + service.getString("name") + "/3DServer";
                                        serviceObj.put("serviceurl", url3d);
                                        JSONObject fullExtent = service.getJSONObject("fullExtent");
                                        if (fullExtent != null) {
                                            serviceObj.put("fullExtent", fullExtent);
                                        }else {
                                            Extent extent = new Extent();
                                            extent.setXmin((double) -180);
                                            extent.setYmin((double) -90);
                                            extent.setXmax((double) 180);
                                            extent.setYmax((double) 90);
                                            serviceObj.put("fullExtent", extent);
                                        }
                                        serviceObj.put("type", "");
                                        servicesArray.add(serviceObj);
//
                                    } else {
                                        folderArray.add(service.getString("name"));
                                    }
                                }else {
                                    if ("hndist server".equals(service.getString("serviceEngine"))) {
                                        JSONObject serviceObj = new JSONObject();
                                        serviceObj.put("name", service.getString("name"));
                                        serviceObj.put("serviceEngine", service.getString("serviceEngine"));
                                        serviceObj.put("height", "");
                                        serviceObj.put("fullExtent", "");
                                        serviceObj.put("type", "");
                                        String urlHndist = mapName + "/" + service.getString("name") + "/MapServer";
                                        serviceObj.put("serviceurl", urlHndist);
                                        servicesArray.add(serviceObj);
                                    } else if ("IotServer".equals(service.getString("serviceEngine"))) {
                                        JSONObject serviceObj = new JSONObject();
                                        serviceObj.put("name", service.getString("name"));
                                        serviceObj.put("serviceEngine", service.getString("serviceEngine"));
                                        serviceObj.put("height", "");
                                        serviceObj.put("fullExtent", "");
                                        serviceObj.put("type", "");
                                        String urlIot = mapName + "/" + service.getString("name") + "/IotServer";
                                        serviceObj.put("serviceurl", urlIot);
                                        servicesArray.add(serviceObj);
                                    }
                                }
                            }
                            jsonObj.put("folders",folderArray);
                            jsonObj.put("services",servicesArray);
                        }
                        context = jsonObj.toJSONString();
                    }
                    return new ResponseEntity<>(context, httpHeaders, HttpStatus.OK);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            log.info("请填写服务名称!");
        }
        return null;
    }

    private static String encodeUrl(String baseUrl, String url) {
        String newUrl = url.replace(baseUrl, "");
        newUrl = newUrl.replaceFirst("/", "");
        String[] split = newUrl.split("/");
        StringBuilder sbf = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            String str = URLEncoder.encode(split[i]);
            sbf.append("/").append(str);
        }
        return baseUrl+sbf.toString();
    }

    public static void main(String[] args) {
        String s = encodeUrl("http://192.168.1.9:6080", "http://192.168.1.9:6080/arcgis/rest/services/武陟POI推荐/MapServer");
        System.out.println(s);
    }

}
