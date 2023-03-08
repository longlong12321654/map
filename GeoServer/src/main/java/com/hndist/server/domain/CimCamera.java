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
 * @Title: CimCamera
 * @Description: 摄像头
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="摄像头", description = "摄像头")
public class CimCamera extends BaseEntity
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
    /** 摄像头名称 */
    @ApiModelProperty(value = "摄像头名称")
    @Excel(name = "摄像头名称")
    private String name;
    /** 摄像头编码 */
    @ApiModelProperty(value = "摄像头编码")
    @Excel(name = "摄像头编码")
    private String code;
    /** 具体位置 */
    @ApiModelProperty(value = "具体位置")
    @Excel(name = "具体位置")
    private String address;
    /** 父节点 */
    @ApiModelProperty(value = "父节点")
    @Excel(name = "父节点")
    private String pid;
    /** 摄像头分类 */
    @ApiModelProperty(value = "摄像头分类")
    @Excel(name = "摄像头分类")
    private String cameraType;
    /** 是否父级 */
    @ApiModelProperty(value = "是否父级")
    @Excel(name = "是否父级")
    private String isparent;
    /** 经度 */
    @ApiModelProperty(value = "经度")
    @Excel(name = "经度")
    private String longitud;
    /** 纬度 */
    @ApiModelProperty(value = "纬度")
    @Excel(name = "纬度")
    private String latitude;
    /** 摄像头地址 */
    @ApiModelProperty(value = "摄像头地址")
    @Excel(name = "摄像头地址")
    private String videoUrl;
    /** 摄像头状态 */
    @ApiModelProperty(value = "摄像头状态")
    @Excel(name = "摄像头状态")
    private String cameraStatus;
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


    public void setCameraType(String cameraType){
        this.cameraType = cameraType;
    }

    public String getCameraType(){
        return cameraType;
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


    public void setVideoUrl(String videoUrl){
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl(){
        return videoUrl;
    }


    public void setCameraStatus(String cameraStatus){
        this.cameraStatus = cameraStatus;
    }

    public String getCameraStatus(){
        return cameraStatus;
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
            .append("cameraType",getCameraType())
            .append("isparent",getIsparent())
            .append("longitud",getLongitud())
            .append("latitude",getLatitude())
            .append("videoUrl",getVideoUrl())
            .append("cameraStatus",getCameraStatus())
            .append("thumbnail",getThumbnail())
            .append("createTime",getCreateTime())
            .append("remark",getRemark())
            .toString();
    }
}
