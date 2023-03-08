package com.hndist.cim.gisserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.hndist.cim.gisserver.entity.MapLayer;
import com.hndist.cim.gisserver.entity.MapService;
import com.hndist.cim.gisserver.service.IPublishingService;
import com.hndist.cim.gisserver.utils.*;
import com.hndist.cim.gisserver.utils.HttpUtil;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
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
//@Api(tags = "CIM - 地图服务列表")
@RequestMapping("/cim/rest")
@Log4j2
public class MapServicesController {
    @Autowired

    private IPublishingService publishingService;

    //3D服务地址（后续需要合并与二维一致）
    @Value("${hndistmap.mapserver}")
    private String serverUrl;

    //服务发布路径，默认为/
    @Value("${hndistmap.serverpath:/}")
    private String serverpath;

    /**
     * @return BaseController 返回类型
     * @throws Exception 抛出自定义异常
     * @Title: MapServer
     * @Description: 新增实体类信息
     */
    @GetMapping( value = {"/services/{serverName}/3DServer","/services/{folder}/{serverName}/3DServer","/services/**/**"}, params = {"!query"})
    @ResponseBody
    public ResponseEntity<byte[]> SceneServer(HttpServletRequest request, HttpServletResponse response, @PathVariable(required = false) String folder, @PathVariable(required = false) String serverName,Boolean isPublishing, String f) throws Exception {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
        //判断是否是HTML/JSON请求
        if(StringUtils.isNotEmpty(f) && ("html".equalsIgnoreCase(f) || "json".equalsIgnoreCase(f))){
            return get3DLayers(request,folder,serverName,isPublishing,f);
        }
        else {
            //地图服务地址(可采用分布式资源池)
            String mapName = request.getServletPath();
            if (StringUtils.isNotEmpty(mapName)) {
                //获取地图服务名称
                int index = mapName.indexOf("/3DServer");
                //获取是否存在索引
                if (index > -1) {
                    mapName = mapName.substring(19, mapName.indexOf("/3DServer"));
                } else {
                    mapName = mapName.substring(19, mapName.length());
                }
                //判断是否是发布对象
                if (isPublishing == null) {
                    isPublishing = true;
                }
                //组合URL 支持域名
                StringBuffer serverURL = new StringBuffer();
                serverURL.append(serverUrl); // 图片IP
                serverURL.append(isPublishing?"MapServer/":"cache/");
                //serverURL.append("3DServer/");
                serverURL.append(mapName);
                //判断是否是主服务
                if (index > -1) {
                    //判断是否存在指定目录，如果不存在则使用当前目录
                    if (serverpath == null || serverpath.length() < 2) {
                        //获取当前路径
                        File tmppath = new File(System.getProperty("user.dir"));
                        //获取相对路径地址
                        serverpath = tmppath.getCanonicalPath() + "/Data/";
                    }
                    //获取文件
                    File file = new File(serverpath +(isPublishing?"MapServer/":"cache/")+ mapName);
                    //判断是否是文件夹
                    if (file.isDirectory()) {
                        serverURL.append("/tileset.json");
                    }
                }
                //验证图层参数
                HashMap<String, String> params = HttpUtil.getParameterNames(request);
                //封装返回对象
                try {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    // 不是用缓存
                    httpHeaders.setCacheControl(CacheControl.noCache());
                    httpHeaders.setPragma("no-cache");
                    httpHeaders.setExpires(0L);
                    httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    return new ResponseEntity<>(HttpUtil.doGet(serverURL, params, request), httpHeaders, HttpStatus.OK);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                log.info("请填写服务名称!");
            }
            return null;
        }
    }

//    /**
//     *
//     * @Title: MapServer
//     * @Description: NGINX代理形式，性能达到最优
//     * @return BaseController 返回类型
//     * @throws Exception 抛出自定义异常
//     */
////    @ApiOperation(value = "5. 地图服务 - 三维地图服务")
//    @GetMapping({"/services/**/3DServer","/services/**/**"})
//    @ResponseBody
//    public void SceneServer(HttpServletRequest request, HttpServletResponse response,Boolean isPublishing) throws Exception {
//        //地图服务地址(可采用分布式资源池)
//        String mapName = request.getServletPath();
//        if (StringUtils.isNotEmpty(mapName)) {
//            //获取地图服务名称
//            int index = mapName.indexOf("/3DServer");
//            //获取是否存在索引
//            if(index>-1) {
//                mapName = mapName.substring(19, mapName.indexOf("/3DServer"));
//            }else{
//                mapName = mapName.substring(19, mapName.length());
//            }
//            String type = isPublishing ?"mapserver/":"cache/";
//            //组合URL 支持域名
//            StringBuffer serverURL = new StringBuffer();
//            serverURL.append(serverUrl); // 图片IP
//            serverURL.append(type);
//            serverURL.append(mapName);
//            //判断是否是主服务
//            if(index>-1) {
//                //判断是否存在指定目录，如果不存在则使用当前目录
//                if (serverpath == null || serverpath.length() < 2) {
//                    //获取当前路径
//                    File tmppath = new File(System.getProperty("user.dir"));
//                    //获取相对路径地址
//                    serverpath = tmppath.getCanonicalPath() + "/Data/";
//                }
//                //获取文件
//                File file = new File(serverpath+type+mapName);
//                //判断是否是文件夹
//                if(file.isDirectory()) {
//                    serverURL.append("/tileset.json");
//                }
//            }
//            //验证图层参数
//            HashMap<String, String> params = HttpUtil.getParameterNames(request);
//            response.sendRedirect(serverURL.toString());
//        } else {
//            log.info("请填写服务名称!");
//        }
//    }

    /*
     * @author WY
     * @Description: 地图服务 - 子服务查询
     * @date 2022/11/19 10:54
     * @Param [mapService]
     * @return com.hndist.cim.gisserver.entity.MapService
     */
//    @ApiOperation("4. 地图服务 - 图层")
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "f", value = "格式化类型", required = false, dataType = "String")
//    )
    @GetMapping(value = {"/services/{serverName}/MapServer/{layerName}","/services/{folder}/{serverName}/MapServer/{layerName}"})
    @ResponseBody
    public ResponseEntity layer(HttpServletRequest request, @PathVariable(required = false) String folder, @PathVariable String serverName, @PathVariable String layerName, String f) throws IOException {
        //定义临时地址对象
        String tmpURL = request.getRequestURI();
        if(folder!=null){
            folder=folder+"/"+serverName+".map";
        }else{
            folder = serverName+".map";
        }
        //获取服务列表
        List<MapService> mapServiceList = publishingService.getMapServiceList(folder, true);
        //返回第一个服务对象
        if ("json".equalsIgnoreCase(f)) {
            // return mapServiceList != null && mapServiceList.size() > 0 ? mapServiceList.get(0) : null;
            return new ResponseEntity(mapServiceList != null && mapServiceList.size() > 0 ? mapServiceList.get(0) : null, HttpStatus.OK);
        } else {
            //地图服务模版
            String ftl_mapserver = "service/layer.html";
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

            //生成服务页面
            String context = FreemarkerUtil.generateString(ftl_mapserver, datas);
            return new ResponseEntity(context, httpHeaders, HttpStatus.OK);
        }
    }

    /*
     * @author WY
     * @Description: 地图服务 - 子服务查询
     * @date 2022/11/19 10:54
     * @Param [mapService]
     * @return com.hndist.cim.gisserver.entity.MapService
     */
//    @ApiOperation("3. 三维地图服务 - 服务")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "service", value = "服务类型", required = false, dataType = "String"),
//            @ApiImplicitParam(name = "f", value = "格式化类型", required = false, dataType = "String")
//    }
//    )
//    @GetMapping(value = {"/services/{serverName}/3DServer","/services/{folder}/{serverName}/3DServer"})
//    @ResponseBody
    public ResponseEntity get3DLayers(HttpServletRequest request, String folder, String serverName, Boolean isPublishing, String f) throws IOException {
        //定义临时地址对象
        String tmpURL = request.getRequestURI();
        if(folder!=null){
            folder=folder+"/"+serverName+".3dmap";
        }else{
            folder = serverName+".3dmap";
        }
        //验证图层参数
        HashMap<String, String> params = HttpUtil.getParameterNames(request);
        //判断是否是发布对象
        if (isPublishing == null) {
            isPublishing = true;
        }
        //获取服务列表
        List<MapService> mapServiceList = publishingService.getMapServiceList(folder, isPublishing);
        if(CollectionUtils.isEmpty(mapServiceList)) {
            return null;
        }
        //判断是否返回JSON数据
        if ("json".equalsIgnoreCase(f)) {
            //返回第一个服务对象
            return new ResponseEntity(mapServiceList != null && mapServiceList.size() > 0 ? mapServiceList.get(0) : null, HttpStatus.OK);
        } else {
            //地图服务模版
            String ftl_mapserver = "service/layers3d.html";
            HashMap<String, Object> datas = new HashMap<String, Object>();
            datas.put("mapServer", mapServiceList.get(0));
            datas.put("projectName","SHMap");
            datas.put("version","V1.0");
            HttpHeaders httpHeaders = new HttpHeaders();
            // 不是用缓存
            httpHeaders.setCacheControl(CacheControl.noCache());
            httpHeaders.setPragma("no-cache");
            httpHeaders.setExpires(0L);

            //生成服务页面
            String context = FreemarkerUtil.generateString(ftl_mapserver, datas);
            return new ResponseEntity(context, httpHeaders, HttpStatus.OK);
        }
    }

    /*
     * @author WY
     * @Description: 地图服务 - 子服务查询
     * @date 2022/11/19 10:54
     * @Param [mapService]
     * @return com.hndist.cim.gisserver.entity.MapService
     */
//    @ApiOperation("3. 地图服务 - 服务")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "service", value = "服务类型", required = false, dataType = "String"),
//            @ApiImplicitParam(name = "f", value = "格式化类型", required = false, dataType = "String")
//    }
//    )
    @GetMapping(value = {"/services/{serverName}/MapServer","/services/{folder}/{serverName}/MapServer"})
    @ResponseBody
    public ResponseEntity layers(HttpServletRequest request, @PathVariable(required = false) String folder, @PathVariable String serverName, Boolean isPublishing, String service, String f) throws IOException {
        //定义临时地址对象
        String tmpURL = request.getRequestURI();
        if(folder!=null){
            folder=folder+"/"+serverName+".map";
        }else{
            folder = serverName+".map";
        }
        //验证图层参数
        HashMap<String, String> params = HttpUtil.getParameterNames(request);
        //判断是否是发布对象
        if (isPublishing == null) {
            isPublishing = true;
        }
        //获取服务列表
        List<MapService> mapServiceList = publishingService.getMapServiceList(folder, isPublishing);
        if(CollectionUtils.isEmpty(mapServiceList)) {
            return null;
        }
        //判断是否是地图服务
        if (mapServiceList!=null && StringUtils.isNotEmpty(service) && "WMS".equalsIgnoreCase(service)) {
            //获取地图地图服务
            MapService mapService = mapServiceList.get(0);
            params.put("map", mapService.getFilePath());
            if(params.get("layers")==null || params.get("layers").equalsIgnoreCase("all")){
                String layers = "";
                //遍历地图图层
                for(MapLayer mapLayer:mapService.getMapLayerList()){
                    if(mapLayer.getName()!=null && mapLayer.getName().length()>0) {
                        if (layers.length() > 0) {
                            layers += ",";
                        }
                        layers += mapLayer.getName();
                    }
                }
                params.put("layers",layers);
            }
            //封装返回对象
            try {
                HttpHeaders httpHeaders = new HttpHeaders();
                // 不是用缓存
                httpHeaders.setCacheControl(CacheControl.noCache());
                httpHeaders.setPragma("no-cache");
                httpHeaders.setExpires(0L);
                if (!params.containsKey("request") || !params.get("request").equalsIgnoreCase("GetFeatureInfo")) {
                    httpHeaders.setContentType(MediaType.IMAGE_JPEG);
                }
                ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(HttpUtil.doGet(new StringBuffer(mapServiceList.get(0).getMapserver()), params, request), httpHeaders, HttpStatus.OK);
                return responseEntity;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //判断是否返回JSON数据
            if ("json".equalsIgnoreCase(f)) {
                //返回第一个服务对象
                return new ResponseEntity(mapServiceList != null && mapServiceList.size() > 0 ? mapServiceList.get(0) : null, HttpStatus.OK);
            } else {
                //地图服务模版
                String ftl_mapserver = "service/layers.html";
                HashMap<String, Object> datas = new HashMap<String, Object>();
                datas.put("mapServer", mapServiceList.get(0));
                datas.put("projectName","SHMap");
                datas.put("version","V1.0");
                HttpHeaders httpHeaders = new HttpHeaders();
                // 不是用缓存
                httpHeaders.setCacheControl(CacheControl.noCache());
                httpHeaders.setPragma("no-cache");
                httpHeaders.setExpires(0L);

                //生成服务页面
                String context = FreemarkerUtil.generateString(ftl_mapserver, datas);
                return new ResponseEntity(context, httpHeaders, HttpStatus.OK);
            }
        }

        return null;
    }

    /*
     * @author WY
     * @Description: 地图服务 - 服务列表
     * @date 2022/11/19 10:54
     * @Param [mapService]
     * @return com.hndist.cim.gisserver.entity.MapService
     */
//    @ApiOperation("1. 地图服务 - 服务列表")
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "f", value = "格式化类型", required = false, dataType = "String")
//    )
    @GetMapping(value = {"", "/","/services","/services/{folder}"})
    @ResponseBody
    public ResponseEntity getServiceList(String f, @PathVariable(required = false) String folder) throws IOException {
        //获取服务列表
        List<MapService> mapServiceList = publishingService.getMapServiceList(folder, true);
        //判断是否返回JSON数据
        if ("json".equalsIgnoreCase(f)) {
            return new ResponseEntity(mapServiceList, HttpStatus.OK);
        } else {
                String ftl_mapserver = "service/service.html";
                HashMap<String, Object> datas = new HashMap<String, Object>(16);
                datas.put("mapServiceList", mapServiceList);
                datas.put("projectName","SHMap");
                datas.put("version","V1.0");
                datas.put("folder",folder==null?'/':folder);
                HttpHeaders httpHeaders = new HttpHeaders();
                // 不是用缓存
                httpHeaders.setCacheControl(CacheControl.noCache());
                httpHeaders.setPragma("no-cache");
                httpHeaders.setExpires(0L);
                //生成服务页面
                String context = FreemarkerUtil.generateString(ftl_mapserver, datas);
                return new ResponseEntity(context, httpHeaders, HttpStatus.OK);
        }
    }

        /**
         * @return BaseController 返回类型
         * @throws Exception 抛出自定义异常
         * @Title: IotServer
         * @Description: 新增实体类信息
         */
    //@ApiOperation(value = "6. 地图服务 - 物联网服务")
    @GetMapping({"/services/{serverName}/IotServer","/services/{folder}/{serverName}/IotServer"})
    @ResponseBody
    public ResponseEntity<String> iotServer(HttpServletRequest request, @PathVariable(required = false) String folder, @PathVariable String serverName, @RequestParam(defaultValue = "true",required = false) Boolean isPublishing, String service, String f) throws Exception {
        if(folder!=null){
            folder=folder+"/"+serverName+".IoT";
        }else{
            folder = serverName+".IoT";
        }
        //获取服务列表
        List<MapService> mapServiceList = publishingService.getMapServiceList(folder, isPublishing);
        if (CollectionUtils.isEmpty(mapServiceList)) {
            return new ResponseEntity<String>("服务不存在！", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //返回第一个服务对象
        if ("html".equalsIgnoreCase(f)) {
            //地图服务模版
            String ftl_mapserver = "service/layersIoT.html";
            HashMap<String, Object> datas = new HashMap<String, Object>();
            datas.put("mapServer", mapServiceList.get(0));
            datas.put("projectName","SHMap");
            datas.put("version","V1.0");
            HttpHeaders httpHeaders = new HttpHeaders();
            // 不是用缓存
            httpHeaders.setCacheControl(CacheControl.noCache());
            httpHeaders.setPragma("no-cache");
            httpHeaders.setExpires(0L);
            //生成服务页面
            String context = FreemarkerUtil.generateString(ftl_mapserver, datas);
            return new ResponseEntity(context, httpHeaders, HttpStatus.OK);
        }else{
            //过滤geoJSON属性
            PropertyFilter profilter = new PropertyFilter() {
                @Override
                public boolean apply(Object object, String name, Object value) {
                    if (name.equals("geoJson")) {
                        // false表示字段将被排除在外
                        return false;
                    }
                    return true;
                }
            };
            //获取地图服务对象
            MapService mapService = mapServiceList.get(0);
            //解析GeoJSON
            JSONObject jsonObject = JSONObject.parseObject(mapService.getGeoJson());
            //重新封装属性
            jsonObject.put("attribute",mapService);
            //返回物联网那个服务数据
            return new ResponseEntity<String>(JSON.toJSONString(jsonObject,profilter), HttpStatus.OK);
        }
    }

}
