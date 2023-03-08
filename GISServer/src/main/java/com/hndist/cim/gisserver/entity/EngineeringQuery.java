package com.hndist.cim.gisserver.entity;

import java.io.Serializable;

/**
 * @author zy
 * @version 1.1
 * @className EngineeringQuery
 * @description TODO
 * @date 2022/11/24 15:37
 **/
public class EngineeringQuery implements Serializable {


    /**
     * 服务名称
     */
    private String mapEngineeringName;
    /**
     * 地址
     */
    private String path;
    /**
     * 文件夹
     */
    private String folder;
    /**
     * 服务
     */
    private String mapServerName;
    /**
     * 值字段
     */
    private String valueFileds;
    /**
     * map文件名
     */
    private String mapname;
    /**
     * 图层名
     */
    private String layerName;
    /**
     * 是否发布
     */
    private boolean isPublishing;


    public String getMapEngineeringName() {
        return mapEngineeringName;
    }

    public void setMapEngineeringName(String mapEngineeringName) {
        this.mapEngineeringName = mapEngineeringName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getMapServerName() {
        return mapServerName;
    }

    public void setMapServerName(String mapServerName) {
        this.mapServerName = mapServerName;
    }

    public String getValueFileds() {
        return valueFileds;
    }

    public void setValueFileds(String valueFileds) {
        this.valueFileds = valueFileds;
    }

    public String getMapname() {
        return mapname;
    }

    public void setMapname(String mapname) {
        this.mapname = mapname;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public boolean isPublishing() {
        return isPublishing;
    }

    public void setPublishing(boolean publishing) {
        isPublishing = publishing;
    }

    @Override
    public String toString() {
        return "EngineeringQuery{" +
                "mapEngineeringName='" + mapEngineeringName + '\'' +
                ", path='" + path + '\'' +
                ", folder='" + folder + '\'' +
                ", mapServerName='" + mapServerName + '\'' +
                '}';
    }
}
