package com.hndist.server.domain;

import com.hndist.framework.annotation.Excel;
import com.hndist.framework.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.CollectionUtils;

/**
 * @Title: CimMapresources
 * @Description: 地图服务
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="地图服务", description = "地图服务")
public class CimMapresources extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /**
     * 日期格式化
     */
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** id */
    @ApiModelProperty(value = "id")
    @Excel(name = "id")
    private String id;
    /** 服务名称 */
    @ApiModelProperty(value = "服务名称")
    @Excel(name = "服务名称")
    private String name;
    /** 是否父级 */
    @ApiModelProperty(value = "是否父级")
    @Excel(name = "是否父级")
    private String isparent;
    /** 是否显示 */
    @ApiModelProperty(value = "是否显示")
    @Excel(name = "是否显示")
    private String show;
    /** 服务状态 */
    @ApiModelProperty(value = "服务状态")
    @Excel(name = "服务状态")
    private String status;
    /** 服务地址 */
    @ApiModelProperty(value = "服务地址")
    @Excel(name = "服务地址")
    private String serviceurl;
    /** 行政区划 */
    @ApiModelProperty(value = "行政区划")
    @Excel(name = "行政区划")
    private String city;
    /** 秘钥 */
    @ApiModelProperty(value = "秘钥")
    @Excel(name = "秘钥")
    private String key;
    /** 坐标系 */
    @ApiModelProperty(value = "坐标系")
    @Excel(name = "坐标系")
    private String spatialReference;
    /** 标签 */
    @ApiModelProperty(value = "标签")
    @Excel(name = "标签")
    private String tags;
    /** 缩略图 */
    @ApiModelProperty(value = "缩略图")
    @Excel(name = "缩略图")
    private String thumbnail;
    /** 数据字典 */
    @ApiModelProperty(value = "数据字典")
    @Excel(name = "数据字典")
    private String servicetype;
    /** 服务引擎 */
    @ApiModelProperty(value = "服务引擎")
    @Excel(name = "服务引擎")
    private String serviceengine;
    /** 透明度 */
    @ApiModelProperty(value = "透明度")
    @Excel(name = "透明度")
    private Integer transparency;
    /** 排序 */
    @ApiModelProperty(value = "排序")
    @Excel(name = "排序")
    private Integer reorder;
    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间")
    private Date createTime;
    /** 描述 */
    @ApiModelProperty(value = "描述")
    @Excel(name = "描述")
    private String context;
    /** 父节点 */
    @ApiModelProperty(value = "父节点")
    @Excel(name = "父节点")
    private String pid;
    /** 树名称 */
    @ApiModelProperty(value = "树名称")
    @Excel(name = "树名称")
    private String label;
    private String icon;
    private String extent;
    private String serverId;
    private String spatialRefer;
    private BigDecimal height;
    /**
     * 服务引擎文本
     */
    private String serviceengineText;
    private String servicetypeText;
    private String specialMap;

    private List<CimMapresources> children;


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


    public void setIsparent(String isparent){
        this.isparent = isparent;
    }

    public String getIsparent(){
        return isparent;
    }


    public void setShow(String show){
        this.show = show;
    }

    public String getShow(){
        return show;
    }


    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }


    public void setServiceurl(String serviceurl){
        this.serviceurl = serviceurl;
    }

    public String getServiceurl(){
        return serviceurl;
    }


    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return city;
    }


    public void setKey(String key){
        this.key = key;
    }

    public String getKey(){
        return key;
    }


    public void setSpatialReference(String spatialReference){
        this.spatialReference = spatialReference;
    }

    public String getSpatialReference(){
        return spatialReference;
    }


    public void setTags(String tags){
        this.tags = tags;
    }

    public String getTags(){
        return tags;
    }


    public void setThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }

    public String getThumbnail(){
        return thumbnail;
    }


    public void setServicetype(String servicetype){
        this.servicetype = servicetype;
    }

    public String getServicetype(){
        return servicetype;
    }


    public void setServiceengine(String serviceengine){
        this.serviceengine = serviceengine;
    }

    public String getServiceengine(){
        return serviceengine;
    }


    public void setTransparency(Integer transparency){
        this.transparency = transparency;
    }

    public Integer getTransparency(){
        return transparency;
    }


    public void setReorder(Integer reorder){
        this.reorder = reorder;
    }

    public Integer getReorder(){
        return reorder;
    }


    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public String getPCreateTime(){
        if(this.createTime!=null){
            return dateFormat.format(this.createTime);
        }
        return "";
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setPid(String pid){
        this.pid = pid;
    }

    public String getPid(){
        return pid;
    }

    public String getLabel() {
        return label;
    }

    public String getServiceengineText() {
        return serviceengineText;
    }

    public void setServiceengineText(String serviceengineText) {
        this.serviceengineText = serviceengineText;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<CimMapresources> getChildren() {
        if (CollectionUtils.isEmpty(this.children)){
            children = new ArrayList<>();
        }
        return children;
    }

    public void setChildren(List<CimMapresources> children) {
        this.children = children;
    }

    public String getServicetypeText() {
        return servicetypeText;
    }

    public void setServicetypeText(String servicetypeText) {
        this.servicetypeText = servicetypeText;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getExtent() {
        return extent;
    }

    public void setExtent(String extent) {
        this.extent = extent;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getSpatialRefer() {
        return spatialRefer;
    }

    public void setSpatialRefer(String spatialRefer) {
        this.spatialRefer = spatialRefer;
    }

    public String getSpecialMap() {
        return specialMap;
    }

    public void setSpecialMap(String specialMap) {
        this.specialMap = specialMap;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("name",getName())
            .append("isparent",getIsparent())
            .append("show",getShow())
            .append("status",getStatus())
            .append("serviceurl",getServiceurl())
            .append("city",getCity())
            .append("key",getKey())
            .append("spatialReference",getSpatialReference())
            .append("tags",getTags())
            .append("thumbnail",getThumbnail())
            .append("servicetype",getServicetype())
            .append("serviceengine",getServiceengine())
            .append("transparency",getTransparency())
            .append("reorder",getReorder())
            .append("createTime",getCreateTime())
            .append("context",getContext())
            .append("pid",getPid())
            .toString();
    }
}
