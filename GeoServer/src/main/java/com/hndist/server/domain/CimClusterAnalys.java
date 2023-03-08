package com.hndist.server.domain;

import com.hndist.framework.annotation.Excel;
import com.hndist.framework.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @Title: CimClusterAnalys
 * @Description: 聚合分析
 * @author AI Builder
 * @date 2022-12-13 05:23:26
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="聚合分析", description = "聚合分析")
public class CimClusterAnalys extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /**
     * 日期格式化
     */
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** 编号 */
    @ApiModelProperty(value = "编号")
    @Excel(name = "编号")
    private String id;
    /** 服务名称(表名) */
    @ApiModelProperty(value = "服务名称(表名)")
    @Excel(name = "服务名称(表名)")
    private String name;
    /** 服务地址 */
    @ApiModelProperty(value = "服务地址")
    @Excel(name = "服务地址")
    private String serviceurl;
    /** 缩略图 */
    @ApiModelProperty(value = "缩略图")
    @Excel(name = "缩略图")
    private String thumbnail;
    /** 描述 */
    @ApiModelProperty(value = "描述")
    @Excel(name = "描述")
    private String context;
    /** 聚合方式 1热力图2聚合图 */
    @ApiModelProperty(value = "聚合方式 1热力图2聚合图")
    @Excel(name = "聚合方式 1热力图2聚合图")
    private String analysType;
    /** 默认底图 */
    @ApiModelProperty(value = "默认底图")
    @Excel(name = "默认底图")
    private String basemap;
    /** 默认视角 */
    @ApiModelProperty(value = "默认视角")
    @Excel(name = "默认视角")
    private String visualAngle;
    /** 服务图层 */
    @ApiModelProperty(value = "服务图层")
    @Excel(name = "服务图层")
    private String layerName;
    /** 聚合字段 */
    @ApiModelProperty(value = "聚合字段")
    @Excel(name = "聚合字段")
    private String clusterFiled;
    /** 是否父级 1是0否 */
    @ApiModelProperty(value = "是否父级 1是0否")
    @Excel(name = "是否父级 1是0否")
    private String isparent;
    /** 父编号 */
    @ApiModelProperty(value = "父编号")
    @Excel(name = "父编号")
    private String pid;
    /** 图标 */
    @ApiModelProperty(value = "图标")
    @Excel(name = "图标")
    private String icon;
    /** 数据类型1服务2数据表 */
    @ApiModelProperty(value = "数据类型1服务2数据表")
    @Excel(name = "数据类型1服务2数据表")
    private String datatype;
    @ApiModelProperty(value = "服务类型 数据字典:sys_servicetype")
    @Excel(name = "服务类型")
    private String servicetype;
    private String servicetypeText;
    @ApiModelProperty(value = "服务引擎 数据字典:sys_serviceengine")
    @Excel(name = "服务引擎")
    private String serviceengine;



    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }


    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }


    public void setServiceurl(String serviceurl){
        this.serviceurl = serviceurl;
    }

    public String getServiceurl(){
        return serviceurl;
    }


    public void setThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }

    public String getThumbnail(){
        return thumbnail;
    }


    public void setContext(String context){
        this.context = context;
    }

    public String getContext(){
        return context;
    }


    public void setAnalysType(String analysType){
        this.analysType = analysType;
    }

    public String getAnalysType(){
        return analysType;
    }


    public void setBasemap(String basemap){
        this.basemap = basemap;
    }

    public String getBasemap(){
        return basemap;
    }


    public void setVisualAngle(String visualAngle){
        this.visualAngle = visualAngle;
    }

    public String getVisualAngle(){
        return visualAngle;
    }


    public void setLayerName(String layerName){
        this.layerName = layerName;
    }

    public String getLayerName(){
        return layerName;
    }


    public void setClusterFiled(String clusterFiled){
        this.clusterFiled = clusterFiled;
    }

    public String getClusterFiled(){
        return clusterFiled;
    }


    public void setIsparent(String isparent){
        this.isparent = isparent;
    }

    public String getIsparent(){
        return isparent;
    }


    public void setPid(String pid){
        this.pid = pid;
    }

    public String getPid(){
        return pid;
    }


    public void setIcon(String icon){
        this.icon = icon;
    }

    public String getIcon(){
        return icon;
    }


    public void setDatatype(String datatype){
        this.datatype = datatype;
    }

    public String getDatatype(){
        return datatype;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getServicetypeText() {
        return servicetypeText;
    }

    public void setServicetypeText(String servicetypeText) {
        this.servicetypeText = servicetypeText;
    }

    public String getServiceengine() {
        return serviceengine;
    }

    public void setServiceengine(String serviceengine) {
        this.serviceengine = serviceengine;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("name",getName())
            .append("serviceurl",getServiceurl())
            .append("thumbnail",getThumbnail())
            .append("context",getContext())
            .append("analysType",getAnalysType())
            .append("basemap",getBasemap())
            .append("visualAngle",getVisualAngle())
            .append("layerName",getLayerName())
            .append("clusterFiled",getClusterFiled())
            .append("isparent",getIsparent())
            .append("pid",getPid())
            .append("icon",getIcon())
            .append("datatype",getDatatype())
            .toString();
    }
}
