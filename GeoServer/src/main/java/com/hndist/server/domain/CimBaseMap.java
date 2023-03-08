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
 * @Title: CimBaseMap
 * @Description: 底图服务
 * @author AI Builder
 * @date 2022-11-15 08:03:48
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="底图服务", description = "底图服务")
public class CimBaseMap extends BaseEntity
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
    /** 底图名称 */
    @ApiModelProperty(value = "底图名称")
    @Excel(name = "底图名称")
    private String name;
    /** 是否显示 1是0否 默认显示 */
    @ApiModelProperty(value = "是否显示 1是0否 默认显示")
    @Excel(name = "是否显示 1是0否 默认显示")
    private String show;
    /** 服务状态 1开启0暂停 */
    @ApiModelProperty(value = "服务状态 1开启0暂停")
    @Excel(name = "服务状态 1开启0暂停")
    private String status;
    /** 服务地址 */
    @ApiModelProperty(value = "服务地址")
    @Excel(name = "服务地址")
    private String serviceurl;
    /** 秘钥 */
    @ApiModelProperty(value = "秘钥")
    @Excel(name = "秘钥")
    private String key;
    /** 坐标系 */
    @ApiModelProperty(value = "坐标系")
    @Excel(name = "坐标系")
    private String spatialReference;
    /** 缩略图 */
    @ApiModelProperty(value = "缩略图")
    @Excel(name = "缩略图")
    private String thumbnail;
    /** 服务类型 */
    @ApiModelProperty(value = "服务类型")
    @Excel(name = "服务类型")
    private String servicetype;
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
    /** 服务引擎 */
    @ApiModelProperty(value = "服务引擎")
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

    public void setContext(String context){
        this.context = context;
    }

    public String getContext(){
        return context;
    }


    public void setServiceengine(String serviceengine){
        this.serviceengine = serviceengine;
    }

    public String getServiceengine(){
        return serviceengine;
    }




    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("name",getName())
            .append("show",getShow())
            .append("status",getStatus())
            .append("serviceurl",getServiceurl())
            .append("key",getKey())
            .append("spatialReference",getSpatialReference())
            .append("thumbnail",getThumbnail())
            .append("servicetype",getServicetype())
            .append("transparency",getTransparency())
            .append("reorder",getReorder())
            .append("createTime",getCreateTime())
            .append("context",getContext())
            .append("serviceengine",getServiceengine())
            .toString();
    }
}
