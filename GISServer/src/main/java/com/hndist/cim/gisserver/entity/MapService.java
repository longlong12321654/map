package com.hndist.cim.gisserver.entity;

import com.hndist.cim.gisserver.utils.IDUtil;
import com.hndist.cim.gisserver.utils.SystemUtils;
import org.springframework.util.CollectionUtils;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author 河南数慧信息技术有限公司
 * @version V1.0.0
 * @ClassName: Service
 * @Description: TODO
 * @date 2022/11/17 14:07
 * @Copyright: www.hndist.com All rights reserved.
 * 注意：本内容仅限于共享研发中心内部传阅，禁止外泄以及用于其他的商业目
 */
//@ApiModel(value = "地图服务对象")
public class MapService {

    //服务名称
//    @ApiModelProperty(name = "name",value = "服务名称",required = false,dataType = "String")
    private String name;

    //服务目录
//    @ApiModelProperty(name = "catalog",value = "服务目录",required = false,dataType = "String")
    private String catalog;

    //服务IP
//    @ApiModelProperty(name = "mapserver",value = "地图服务",required = false,dataType = "String")
    private String mapserver;

    //服务引擎
//    @ApiModelProperty(name = "serviceEngine",value = "服务引擎:hndist server(不可修改)",required = false,dataType = "String")
    private String serviceEngine = "hndist server";

    //服务地址
//    @ApiModelProperty(name = "serviceURL",value = "服务地址",required = false,dataType = "String")
    private String serviceURL;

    //服务地址
//    @ApiModelProperty(name = "filePath",value = "文件路径",required = false,dataType = "String")
    private String filePath;

    //服务图层
    private List<MapLayer> mapLayerList;

    //最大地图范围
    private Extent fullExtent;

    //geojson
    private String geoJson;

    /**
     * 是否多级目录
     */
    private String multiCatalogFlag;
    /**
     * 高度
     */
    private Double height;
    /**
     * 版本
     */
    private String version;
    /**
     * 盒子
     */
    private Double[] box;

    public String getName() {
        if(this.name==null){
            this.name = IDUtil.fastSimpleUUID();
        }
        return name;
    }

    public MapService() {
    }

    public MapService(String name, String mapserver, String filePath) {
        this.name = name;
        this.mapserver = mapserver;
        this.filePath = filePath;
    }

    public String getMapserver() {
        if(mapserver!=null && mapserver.indexOf("cgi-bin")==-1){
            //判断是否是Windows系统
            if(SystemUtils.isWindows()){
                return mapserver + "cgi-bin/mapserv.exe";
            }else {
                return mapserver + "cgi-bin/mapserv";
            }
        }else {
            return mapserver;
        }
    }

    public void setMapserver(String mapserver) {
        if(mapserver!=null && mapserver.indexOf("cgi-bin")==-1){
            //判断是否是Windows系统
            if(SystemUtils.isWindows()){
                this.mapserver = mapserver + "cgi-bin/mapserv.exe";
            }else {
                this.mapserver = mapserver + "cgi-bin/mapserv";
            }
        }else {
            this.mapserver = mapserver;
        }
    }

    public void setServiceEngine(String serviceEngine) {
        this.serviceEngine = serviceEngine;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getServiceEngine() {
        return serviceEngine;
    }

    public String getServiceURL() {
        if(serviceURL == null){
            return "/cim/rest/services/"+this.getName()+"/MapServer?isPublishing=false";
        }
        return serviceURL;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<MapLayer> getMapLayerList() {
        return mapLayerList;
    }

    public void setMapLayerList(List<MapLayer> mapLayerList) {
        this.mapLayerList = mapLayerList;
    }

    public Extent getFullExtent() {
        if(this.fullExtent == null && mapLayerList!=null){
            this.fullExtent = new Extent();
            List<Double> xminList = new ArrayList<>();
            List<Double> xmaxList = new ArrayList<>();
            List<Double> yminList = new ArrayList<>();
            List<Double> ymaxList = new ArrayList<>();
            for(MapLayer mapLayer:mapLayerList){
                Extent extent = mapLayer.getExtent();
                if (!Objects.isNull(extent)) {
                    xminList.add(extent.getXmin());
                    xmaxList.add(extent.getXmax());
                    yminList.add(extent.getYmin());
                    ymaxList.add(extent.getYmax());
                }
            }
            if (!CollectionUtils.isEmpty(xmaxList) && !CollectionUtils.isEmpty(xminList)
                    && !CollectionUtils.isEmpty(yminList) && !CollectionUtils.isEmpty(ymaxList)) {
                this.fullExtent.setXmin(Collections.min(xminList));
                this.fullExtent.setXmax(Collections.max(xmaxList));
                this.fullExtent.setYmin(Collections.min(yminList));
                this.fullExtent.setYmax(Collections.max(ymaxList));
            }

        }
        return this.fullExtent;
    }

    public void setFullExtent(Extent fullExtent) {
        this.fullExtent = fullExtent;
    }

    public String getMultiCatalogFlag() {
        return multiCatalogFlag;
    }

    public void setMultiCatalogFlag(String multiCatalogFlag) {
        this.multiCatalogFlag = multiCatalogFlag;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Double[] getBox() {
        return box;
    }

    public void setBox(Double[] box) {
        this.box = box;
    }

    public String getGeoJson() {
        return geoJson;
    }

    public void setGeoJson(String geoJson) {
        this.geoJson = geoJson;
    }
}
