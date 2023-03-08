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
 * @Title: CimFiles
 * @Description: 地图服务
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="数据管理", description = "数据管理")
public class CimFiles extends BaseEntity
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
    /** 父节点 */
    @ApiModelProperty(value = "父节点")
    @Excel(name = "父节点")
    private String pid;
    /** 是否父级 1是0否 */
    @ApiModelProperty(value = "是否父级 1是0否")
    @Excel(name = "是否父级 1是0否")
    private String isparent;
    /** 服务状态 1是0否 */
    @ApiModelProperty(value = "服务状态 1是0否")
    @Excel(name = "服务状态 1是0否")
    private String status;
    /** 服务地址 */
    @ApiModelProperty(value = "服务地址")
    @Excel(name = "服务地址")
    private String servicesUrl;
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
    private String thumbnailUrl;
    /** 服务类型 */
    @ApiModelProperty(value = "服务类型")
    @Excel(name = "服务类型")
    private String serveType;
    /** 服务引擎 */
    @ApiModelProperty(value = "服务引擎")
    @Excel(name = "服务引擎")
    private String serviceEngine;
    /** 透明度 */
    @ApiModelProperty(value = "透明度")
    @Excel(name = "透明度")
    private Integer opacity;
    /** 排序 */
    @ApiModelProperty(value = "排序")
    @Excel(name = "排序")
    private Integer sortNum;
    /** 描述 */
    @ApiModelProperty(value = "描述")
    @Excel(name = "描述")
    private String context;
    /** 是否显示 1是0否 默认1 */
    @ApiModelProperty(value = "是否显示 1是0否 默认1")
    @Excel(name = "是否显示 1是0否 默认1")
    private String show;
    /**
     * 元数据id
     */
    private String datasourceMetaid;

    @ApiModelProperty(value = "元数据对象")
    private CimDatasourceMeta datasourceMeta;
    @ApiModelProperty(value = "元数据方案")
    @Excel(name = "元数据方案")
    private String programme;
    @ApiModelProperty(value = "开启lod 1开启0关闭，默认开启")
    @Excel(name = "开启lod 1开启0关闭，默认开启")
    private String openlod;
//    @ApiModelProperty(value = "是否免费数据 1是0否，默认是")
//    @Excel(name = "是否免费数据 1是0否，默认是")
//    private String freedata;
    private String icon;

    @ApiModelProperty(value = "文件路径")
    @Excel(name = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "文件URL")
    @Excel(name = "文件URL")
    private String fileurl;

    private String fileSize;

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


    public void setPid(String pid){
        this.pid = pid;
    }

    public String getPid(){
        return pid;
    }


    public void setIsparent(String isparent){
        this.isparent = isparent;
    }

    public String getIsparent(){
        return isparent;
    }


    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }


    public void setServicesUrl(String servicesUrl){
        this.servicesUrl = servicesUrl;
    }

    public String getServicesUrl(){
        return servicesUrl;
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


    public void setThumbnailUrl(String thumbnailUrl){
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getThumbnailUrl(){
        return thumbnailUrl;
    }


    public void setServeType(String serveType){
        this.serveType = serveType;
    }

    public String getServeType(){
        return serveType;
    }


    public void setServiceEngine(String serviceEngine){
        this.serviceEngine = serviceEngine;
    }

    public String getServiceEngine(){
        return serviceEngine;
    }


    public void setOpacity(Integer opacity){
        this.opacity = opacity;
    }

    public Integer getOpacity(){
        return opacity;
    }


    public void setSortNum(Integer sortNum){
        this.sortNum = sortNum;
    }

    public Integer getSortNum(){
        return sortNum;
    }


    public void setContext(String context){
        this.context = context;
    }

    public String getContext(){
        return context;
    }


    public void setShow(String show){
        this.show = show;
    }

    public String getShow(){
        return show;
    }


    public String getDatasourceMetaid() {
        return datasourceMetaid;
    }

    public void setDatasourceMetaid(String datasourceMetaid) {
        this.datasourceMetaid = datasourceMetaid;
    }

    public CimDatasourceMeta getDatasourceMeta() {
        return datasourceMeta;
    }

    public void setDatasourceMeta(CimDatasourceMeta datasourceMeta) {
        this.datasourceMeta = datasourceMeta;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getOpenlod() {
        return openlod;
    }

    public void setOpenlod(String openlod) {
        this.openlod = openlod;
    }

//    public String getFreedata() {
//        return freedata;
//    }
//
//    public void setFreedata(String freedata) {
//        this.freedata = freedata;
//    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("name",getName())
            .append("pid",getPid())
            .append("isparent",getIsparent())
            .append("status",getStatus())
            .append("servicesUrl",getServicesUrl())
            .append("city",getCity())
            .append("key",getKey())
            .append("spatialReference",getSpatialReference())
            .append("tags",getTags())
            .append("thumbnailUrl",getThumbnailUrl())
            .append("serveType",getServeType())
            .append("serviceEngine",getServiceEngine())
            .append("opacity",getOpacity())
            .append("sortNum",getSortNum())
            .append("context",getContext())
            .append("show",getShow())
            .toString();
    }
}
