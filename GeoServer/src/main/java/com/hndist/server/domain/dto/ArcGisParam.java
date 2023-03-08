package com.hndist.server.domain.dto;

import com.hndist.framework.utils.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author zy
 * @version 1.1
 * @className arcGisParam
 * @description TODO
 * @date 2023/1/4 23:25
 **/
public class ArcGisParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * where 语句，默认 1=1
     */
    private String where;
    /**
     * 返回字段 英文逗号隔开
     */
    private String outfields;
    /**
     * 是否返回几何 默认true
     */
    private String returnGeometry;
    /**
     * 几何 Syntax:
     *
     * geometryType=esriGeometryEnvelope&geometry={xmin: -104, ymin: 35.6, xmax: -94.32, ymax: 41}
     * geometryType=esriGeometryEnvelope&geometry=-104,35.6,-94.32,41
     * geometryType=esriGeometryPoint&geometry=-104,35.6
     */
    private String geometry;
    /**
     * 几何类型：esriGeometryPoint | esriGeometryMultipoint | esriGeometryPolyline | esriGeometryPolygon | esriGeometryEnvelope
     */
    private String geometryType;
    /**
     * 空间关系：esriSpatialRelIntersects | esriSpatialRelContains | esriSpatialRelCrosses | esriSpatialRelEnvelopeIntersects | esriSpatialRelIndexIntersects | esriSpatialRelOverlaps | esriSpatialRelTouches | esriSpatialRelWithin | esriSpatialRelRelation
     * 默认 esriSpatialRelIntersects
     */
    private String spatialRel;
    /**
     * 输出统计 语法：“[
     *   {
     *     "statisticType": "<count | sum | min | max | avg | stddev | var>",
     *     "onStatisticField": "Field1",
     *     "outStatisticFieldName": "Out_Field_Name1"
     *   },
     *   {
     *     "statisticType": "<count | sum | min | max | avg | stddev | var>",
     *     "onStatisticField": "Field2",
     *     "outStatisticFieldName": "Out_Field_Name2"
     *   }
     * ]”
     */
    private List<OutputStatistics> outputStatistics;
    /**
     * 分组统计 语法：groupByFieldsForStatistics=field1, field2
     */
    private String groupByFieldsForStatistics;
    /**
     * URL
     */
    private String url;


    /*构造函数*/
    public ArcGisParam() {
    }

    public String getWhere() {
        if (StringUtils.isEmpty(where)) {
            where = "1=1";
        }
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getOutfields() {
        return outfields;
    }

    public void setOutfields(String outfields) {
        this.outfields = outfields;
    }

    public String getReturnGeometry() {
        if (StringUtils.isEmpty(returnGeometry)) {
            returnGeometry = "false";
        }
        return returnGeometry;
    }

    public void setReturnGeometry(String returnGeometry) {
        this.returnGeometry = returnGeometry;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getGeometryType() {
        if (StringUtils.isEmpty(geometryType)) {
            geometryType = "esriGeometryEnvelope";
        }
        return geometryType;
    }

    public void setGeometryType(String geometryType) {
        this.geometryType = geometryType;
    }

    public String getSpatialRel() {
        // 空间关系 默认相交
        if (StringUtils.isEmpty(spatialRel)) {
            spatialRel = "esriSpatialRelIntersects";
        }
        return spatialRel;
    }

    public void setSpatialRel(String spatialRel) {
        this.spatialRel = spatialRel;
    }

    public List<OutputStatistics> getOutputStatistics() {
        return outputStatistics;
    }

    public void setOutputStatistics(List<OutputStatistics> outputStatistics) {
        this.outputStatistics = outputStatistics;
    }

    public String getGroupByFieldsForStatistics() {
        return groupByFieldsForStatistics;
    }

    public void setGroupByFieldsForStatistics(String groupByFieldsForStatistics) {
        this.groupByFieldsForStatistics = groupByFieldsForStatistics;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ArcGisParam{" +
                "outfields='" + outfields + '\'' +
                ", returnGeometry='" + returnGeometry + '\'' +
                ", geometry='" + geometry + '\'' +
                ", spatialRel='" + spatialRel + '\'' +
                ", outputStatistics='" + outputStatistics + '\'' +
                ", groupByFieldsForStatistics='" + groupByFieldsForStatistics + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
