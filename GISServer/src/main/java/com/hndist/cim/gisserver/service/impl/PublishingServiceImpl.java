package com.hndist.cim.gisserver.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hndist.cim.gisserver.entity.MapLayer;
import com.hndist.cim.gisserver.entity.MapService;
import com.hndist.cim.gisserver.service.IPublishingService;
import com.hndist.cim.gisserver.utils.FreemarkerUtil;
import com.hndist.cim.gisserver.utils.HttpUtil;
import com.hndist.cim.gisserver.utils.SoftConnection;
import com.hndist.cim.gisserver.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author 河南数慧信息技术有限公司
 * @version V1.0.0
 * @ClassName: PublishingService
 * @Description: TODO
 * @date 2022/11/17 11:03
 * @Copyright: www.hndist.com All rights reserved.
 * 注意：本内容仅限于共享研发中心内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class PublishingServiceImpl implements IPublishingService {

    //二维地图服务模版
    private final String ftl_mapserver = "maps/mapserver.map";

    //三维地图服务模版
    private final String ftl_3d_mapserver = "maps/mapserver.3dmap";

    //物联网服务模版
    private final String ftl_IoT_mapserver = "maps/mapserver.IoT";

    //服务地址,默认为127.0.0.1
    @Value("${hndistmap.mapserver}")
    private String mapserver;

    //服务发布路径，默认为/
    @Value("${hndistmap.serverpath:/}")
    private String serverpath;

    //发布服务
    @Override
    public MapService publishingServices(MapService mapService,boolean isPublishing) throws IOException {
        //获取真实路径
        String serverpath = getRealPath(isPublishing);
        //判断文件夹是否存在
        if (mapService.getCatalog()!=null){
            serverpath+=mapService.getCatalog()+File.separator;
            //不是当前路径则是多级目录
            if (!"/".equals(mapService.getCatalog())) {
                mapService.setMultiCatalogFlag("true");
            }
        }
        //创建二维地图服务
        this.createServer(mapService,serverpath,isPublishing);
        //创建三维服务
        this.create3DServer(mapService,serverpath,isPublishing);
        //创建IOT服务
        this.createIotServer(mapService,serverpath,isPublishing);
        //返回服务对象
        return mapService;
    }

    @Override
    public MapService getMapService(String mapServiceName, boolean isPublishing) {
        //定义地图服务
        MapService mapService = null;
        //获取真实路径
        String serverpath = getRealPath(isPublishing);
        //定义文件对象
        File file = new File(serverpath+mapServiceName);
        //判断文件是否存在
        if(file!=null && file.exists()){
            //获取地图服务
            mapService = getMapService(file,isPublishing);
        }
        return mapService;
    }

    //获取地图服务列表
    @Override
    public List<MapService> getMapServiceList(String folder,boolean isPublishing) {
        //定义地图服务列表对象
        List<MapService> mapServiceList = new ArrayList<MapService>();

        //获取真实路径
        String serverpath = getRealPath(isPublishing);
        //判断文件夹是否存在
        if(folder!=null){
            serverpath+=folder;
        }

        //定义文件对象
        File file = new File(serverpath);
        if (!file.exists()) {
            return null;
        }

        //判断当前是否为目录
        if(file!=null && file.isDirectory()){
            File[] files = file.listFiles();
            if(files!=null){
                for(File tmpFile:files){
                    //获取地图服务
                    MapService mapService = getMapService(tmpFile,isPublishing);
                    //判断地图服务是否为空
                    if(mapService!=null){
                        mapService.setMapserver(mapserver);
                        mapService.setFilePath(tmpFile.getAbsolutePath());
                        mapServiceList.add(mapService);
                    }
                }
            }
        }else if(file!=null && file.isFile()){
            //获取地图服务
            MapService mapService = getMapService(file,isPublishing);
            mapService.setMapserver(mapserver);
            mapService.setFilePath(file.getAbsolutePath());
            //判断地图服务是否为空
            if(mapService!=null){
                mapServiceList.add(mapService);
            }
        }
        //返回地图服务
        return mapServiceList;
    }

    //删除服务
    @Override
    public boolean delServices(String mapServiceName,boolean isPublishing) {
        //获取真实路径
        String serverpath = getRealPath(isPublishing);
        try {
            //定义文件对象
            File file = new File(serverpath + mapServiceName);
            //判断文件是否存在
            if (file != null && file.exists()) {
                file.delete();
                return true;
            }
        }catch (Exception e){
            System.out.println("删除服务失败:"+e.getMessage());
        }
        return false;
    }

    /*
     * @author WY
     * @Description: 获取地图服务
     * @date 2022/11/18 16:40
     * @Param [mapfile]
     * @return com.hndist.cim.gisserver.entity.MapService
     */
    private MapService getMapService(File mapfile,boolean isPublishing){
        //定义地图服务
        MapService mapService = null;
        if(mapfile.isDirectory()){
            mapService = new MapService();
            mapService.setCatalog("");
            mapService.setName(mapfile.getName());
        }else if(mapfile.isFile() && (mapfile.getName().endsWith(".map") || mapfile.getName().endsWith(".3dmap") || mapfile.getName().endsWith(".IoT"))) {
            //定义临时行
            String line = null;
            //定义内容
            String context = null;
            try {
                //定义读取对象
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(mapfile), "UTF-8"), 1024);
                //读取文件
                while ((line = br.readLine()) != null) {
                    //开始标识
                    if (line.indexOf("/******") > -1) {
                        context = "";
                        continue;
                        //结束标识
                    } else if (line.indexOf("******/") > -1) {
                        break;
                    }
                    //内容累加
                    if (context != null) {
                        context += line;
                    }
                }
                //判断内容是否为空
                if(context!=null) {
                    //转换为地图服务对象
                    mapService = JSON.parseObject(context, MapService.class);
                    if(mapfile.getName().endsWith(".IoT")){
                        mapService.setServiceEngine("IotServer");
                    }else if(mapfile.getName().endsWith(".3dmap")){
                        mapService.setServiceEngine("3DServer");
                    }else{
                        mapService.setServiceEngine("hndist server");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //返回地图服务对象
        return mapService;
    }

    /*
     * @author WY
     * @Description: 获取真实路径
     * @date 2022/11/18 16:40
     * @Param [isPublishing]
     * @return java.lang.String
     */
    private String getRealPath(boolean isPublishing){
        try {
            //判断是否存在指定目录，如果不存在则使用当前目录
            if (serverpath == null || serverpath.length() < 2) {
                //获取当前路径
                File tmppath = new File(System.getProperty("user.dir"));
                //获取相对路径地址
                serverpath = tmppath.getCanonicalPath() + "/Data/";
            }
        }catch (Exception e){
            System.out.println("不支持的操作系统,请检查:"+e.getMessage());
        }
        //返回真实路径
        return serverpath + (isPublishing?"mapserver/":"cache/");
    }

    /*
     * @author WY
     * @Description: 创建地图服务
     * @date 2022/11/24 13:49
     * @Param [mapService]
     * @return void
     */
    private void createServer(MapService mapService,String serverpath,boolean isPublishing){
        //是否存在二维数据
        boolean isExist = false;
        //遍历地图子服务
        for(MapLayer mapLayer:mapService.getMapLayerList()) {
            //定义文件路径
            String filePath = serverpath + mapLayer.getName();
            //判断是否是3dtitles
            if ("hndist server".equalsIgnoreCase(mapLayer.getServiceEngine())) {
                isExist = true;
            }
        }
        //判断是否存在二维数据
        if(!isExist){
            return;
        }
        //封装数据
        HashMap<String,Object> datas = new HashMap<>();
        //生成文件路径
        String filePath = serverpath +mapService.getName()+".map";
        //设置路径
        mapService.setFilePath(filePath);
        mapService.setMapserver(mapserver);
        datas.put("mapService",mapService);
        //正式发布，修改服务地址
        if(isPublishing){
            //获取目录
            String catalog = StringUtils.isNotEmpty(mapService.getCatalog())?mapService.getCatalog()+"/":"";
            mapService.setServiceURL("/cim/rest/services/"+catalog+mapService.getName()+"/MapServer");
        }
        datas.put("mapServiceJSON",JSON.toJSONString(mapService));
        //生成二维地图服务
        FreemarkerUtil.generateFile(ftl_mapserver,filePath,datas);
    }

    /*
     * @author WY
     * @Description: 创建三维地图服务
     * @date 2022/11/24 13:49
     * @Param [mapService]
     * @return void
     */
    private void create3DServer(MapService mapService,String serverpath,boolean isPublishing){
        //判断地图服务及子服务是否为空
        if(mapService!=null && mapService.getMapLayerList()!=null){
            HashMap<String,Object> datas = new HashMap<String,Object>();
            //遍历地图子服务
            for(MapLayer mapLayer:mapService.getMapLayerList()){
                //定义文件路径
                String filePath = serverpath+mapLayer.getName();
                //判断是否是3dtitles
                if("3dtiles".equalsIgnoreCase(mapLayer.getServiceEngine())){
                    //获取真实路径
                    String tmpRealPath = mapLayer.getAbsolutePath().replace("tileset.json","");
                    //创建软连接
                    SoftConnection.createSoftConnection(filePath,tmpRealPath);
                }else if("glb".equalsIgnoreCase(mapLayer.getServiceEngine()) || "gltf".equalsIgnoreCase(mapLayer.getServiceEngine())){
                    //创建软连接
                    SoftConnection.createSoftConnection(filePath,mapLayer.getAbsolutePath());
                }else{
                    break;
                }
                //定义三维地图服务对象
                MapService tmpMapService = new MapService();
                tmpMapService.setName(mapLayer.getMapName());
                if(StringUtils.isEmpty(mapService.getName())){
                    mapService.setName(mapLayer.getName());
                }
                tmpMapService.setCatalog(mapService.getCatalog());
                tmpMapService.setServiceEngine(mapLayer.getServiceEngine());
                tmpMapService.setHeight(mapLayer.getHeight());
                tmpMapService.setVersion(mapLayer.getVersion());
                tmpMapService.setBox(mapLayer.getBox());
                //正式发布，修改服务地址
                if(isPublishing){
                    //获取目录
                    String catalog = StringUtils.isNotEmpty(mapService.getCatalog())?mapService.getCatalog()+"/":"";
                    mapLayer.setServiceURL("/cim/rest/services/"+catalog+mapLayer.getMapName()+"/3DServer");
                }
                tmpMapService.setFilePath(mapLayer.getPath());
                datas.put("mapServiceJSON",JSON.toJSONString(tmpMapService));
                //创建三维服务
                FreemarkerUtil.generateFile(ftl_IoT_mapserver,filePath+".3dmap",datas);
            }
        }
    }

    /**
     * @author WY
     * @Description: 创建IOT服务
     * @date 2022/11/24 13:49
     * @Param [mapService]
     * @return void
     */
    private void createIotServer(MapService mapService,String serverpath,boolean isPublishing){
        //判断地图服务及子服务是否为空
        if(mapService!=null && mapService.getMapLayerList()!=null){
            HashMap<String,Object> datas = new HashMap<String,Object>();
            //遍历地图子服务
            for(MapLayer mapLayer:mapService.getMapLayerList()){

                //判断是否是3dtitles
                if(!"iot".equalsIgnoreCase(mapLayer.getServiceEngine())){
                    break;
                }
                //定义Iot服务对象
                if(StringUtils.isEmpty(mapService.getName())){
                    mapService.setName(mapLayer.getName());
                }
                //定义文件路径
                String filePath = serverpath+mapService.getName();
                mapService.setServiceEngine(mapLayer.getServiceEngine());
                mapService.setHeight(mapLayer.getHeight());
                mapService.setVersion(mapLayer.getVersion());
                try {
                    mapService.setGeoJson(FileUtils.readFileToString(new File(mapLayer.getAbsolutePath()), "utf-8"));
                }catch (Exception e){
                    System.out.println("读取文件失败，请联系管理员:"+e.getMessage());
                }
                mapService.setBox(mapLayer.getBox());
                //正式发布，修改服务地址
                if(isPublishing){
                    //获取目录
                    String catalog = StringUtils.isNotEmpty(mapService.getCatalog())?mapService.getCatalog()+"/":"";
                    mapLayer.setServiceURL("/cim/rest/services/"+catalog+mapService.getName()+"/IotServer");
                }
                mapService.setFilePath(mapLayer.getPath());
                mapService.setServiceURL("/cim/rest/services/"+ mapService.getName() +"/IotServer?isPublishing=false");
                String value = JSONObject.toJSONString(mapService);
                datas.put("mapServiceJSON", value);
                //创建Iot服务
                FreemarkerUtil.generateFile(ftl_IoT_mapserver,filePath+".IoT",datas);
            }

        }
    }

}

