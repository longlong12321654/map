package com.hndist.server.domain;

import com.hndist.framework.annotation.Excel;
import com.hndist.framework.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @Title: CimVideo
 * @Description: cim_video
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="cim_video", description = "cim_video")
public class CimVideo extends BaseEntity
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
    /** 视频名称 */
    @ApiModelProperty(value = "视频名称")
    @Excel(name = "视频名称")
    private String videoName;
    /** 视频描述 */
    @ApiModelProperty(value = "视频描述")
    @Excel(name = "视频描述")
    private String videoDesc;
    /** 视频分类 字典 */
    @ApiModelProperty(value = "视频分类 字典")
    @Excel(name = "视频分类 字典")
    private String videoType;
    /** 视频地址 */
    @ApiModelProperty(value = "视频地址")
    @Excel(name = "视频地址")
    private String videoAddr;
    /** 经度 */
    @ApiModelProperty(value = "经度")
    @Excel(name = "经度")
    private String longt;
    /** 纬度 */
    @ApiModelProperty(value = "纬度")
    @Excel(name = "纬度")
    private String lat;
    /** 高程 */
    @ApiModelProperty(value = "高程")
    @Excel(name = "高程")
    private Double altitude;
    /** 缩略图 */
    @ApiModelProperty(value = "缩略图")
    @Excel(name = "缩略图")
    private String thumbnail;
    /** 图文详情 */
    @ApiModelProperty(value = "图文详情")
    @Excel(name = "图文详情")
    private String content;
    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间")
    private Date createTime;
    /** 创建人 */
    @ApiModelProperty(value = "创建人")
    @Excel(name = "创建人")
    private String createBy;
    /** 更新时间 */
    @ApiModelProperty(value = "更新时间")
    @Excel(name = "更新时间")
    private Date updateTime;
    /** 更新人 */
    @ApiModelProperty(value = "更新人")
    @Excel(name = "更新人")
    private String updateBy;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }


    public void setVideoName(String videoName){
        this.videoName = videoName;
    }

    public String getVideoName(){
        return videoName;
    }


    public void setVideoDesc(String videoDesc){
        this.videoDesc = videoDesc;
    }

    public String getVideoDesc(){
        return videoDesc;
    }


    public void setVideoType(String videoType){
        this.videoType = videoType;
    }

    public String getVideoType(){
        return videoType;
    }


    public void setVideoAddr(String videoAddr){
        this.videoAddr = videoAddr;
    }

    public String getVideoAddr(){
        return videoAddr;
    }


    public void setLongt(String longt){
        this.longt = longt;
    }

    public String getLongt(){
        return longt;
    }


    public void setLat(String lat){
        this.lat = lat;
    }

    public String getLat(){
        return lat;
    }


    public void setAltitude(Double altitude){
        this.altitude = altitude;
    }

    public Double getAltitude(){
        return altitude;
    }


    public void setThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }

    public String getThumbnail(){
        return thumbnail;
    }


    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
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

    public void setCreateBy(String createBy){
        this.createBy = createBy;
    }

    public String getCreateBy(){
        return createBy;
    }


    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    public Date getUpdateTime(){
        return updateTime;
    }

    public String getPUpdateTime(){
        if(this.updateTime!=null){
            return dateFormat.format(this.updateTime);
        }
        return "";
    }

    public void setUpdateBy(String updateBy){
        this.updateBy = updateBy;
    }

    public String getUpdateBy(){
        return updateBy;
    }




    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("videoName",getVideoName())
            .append("videoDesc",getVideoDesc())
            .append("videoType",getVideoType())
            .append("videoAddr",getVideoAddr())
            .append("longt",getLongt())
            .append("lat",getLat())
            .append("altitude",getAltitude())
            .append("thumbnail",getThumbnail())
            .append("content",getContent())
            .append("createTime",getCreateTime())
            .append("createBy",getCreateBy())
            .append("updateTime",getUpdateTime())
            .append("updateBy",getUpdateBy())
            .toString();
    }
}
