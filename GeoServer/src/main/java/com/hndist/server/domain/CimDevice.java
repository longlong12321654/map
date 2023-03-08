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
 * @Title: CimDevice
 * @Description: 传感器
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="传感器", description = "传感器")
public class CimDevice extends BaseEntity
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
    /** 名称 */
    @ApiModelProperty(value = "名称")
    @Excel(name = "名称")
    private String name;
    /** 编码 */
    @ApiModelProperty(value = "编码")
    @Excel(name = "编码")
    private String code;
    /** 具体位置 */
    @ApiModelProperty(value = "具体位置")
    @Excel(name = "具体位置")
    private String address;
    /** 父节点 */
    @ApiModelProperty(value = "父节点")
    @Excel(name = "父节点")
    private String pid;
    /** 摄分类 */
    @ApiModelProperty(value = "摄分类")
    @Excel(name = "摄分类")
    private String deviceType;
    /** 是否父级 */
    @ApiModelProperty(value = "是否父级")
    @Excel(name = "是否父级")
    private String isparent;
    /** 经度 */
    @ApiModelProperty(value = "经度")
    @Excel(name = "经度")
    private String longitud;
    /** 维度 */
    @ApiModelProperty(value = "维度")
    @Excel(name = "维度")
    private String latitude;
    /** 地址 */
    @ApiModelProperty(value = "地址")
    @Excel(name = "地址")
    private String deviceUrl;
    /** 缩略图 */
    @ApiModelProperty(value = "缩略图")
    @Excel(name = "缩略图")
    private String thumbnail;
    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间")
    private Date createTime;
    /** 描述 */
    @ApiModelProperty(value = "描述")
    @Excel(name = "描述")
    private String remark;
    private String icon;

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


    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }


    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }


    public void setPid(String pid){
        this.pid = pid;
    }

    public String getPid(){
        return pid;
    }


    public void setDeviceType(String deviceType){
        this.deviceType = deviceType;
    }

    public String getDeviceType(){
        return deviceType;
    }


    public void setIsparent(String isparent){
        this.isparent = isparent;
    }

    public String getIsparent(){
        return isparent;
    }


    public void setLongitud(String longitud){
        this.longitud = longitud;
    }

    public String getLongitud(){
        return longitud;
    }


    public void setLatitude(String latitude){
        this.latitude = latitude;
    }

    public String getLatitude(){
        return latitude;
    }


    public void setDeviceUrl(String deviceUrl){
        this.deviceUrl = deviceUrl;
    }

    public String getDeviceUrl(){
        return deviceUrl;
    }


    public void setThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }

    public String getThumbnail(){
        return thumbnail;
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

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark(){
        return remark;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("name",getName())
            .append("code",getCode())
            .append("address",getAddress())
            .append("pid",getPid())
            .append("deviceType",getDeviceType())
            .append("isparent",getIsparent())
            .append("longitud",getLongitud())
            .append("latitude",getLatitude())
            .append("deviceUrl",getDeviceUrl())
            .append("thumbnail",getThumbnail())
            .append("createTime",getCreateTime())
            .append("remark",getRemark())
            .toString();
    }
}
