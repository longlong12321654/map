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
 * @Title: CimTrackPosition
 * @Description: 轨迹位置
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="轨迹位置", description = "轨迹位置")
public class CimTrackPosition extends BaseEntity
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
    /** 轨迹id */
    @ApiModelProperty(value = "轨迹id")
    @Excel(name = "轨迹id")
    private String trackId;
    /** 设备名称 */
    @ApiModelProperty(value = "设备名称")
    @Excel(name = "设备名称")
    private String name;
    /** 设备编码 */
    @ApiModelProperty(value = "设备编码")
    @Excel(name = "设备编码")
    private String code;
    /** 用户编号 */
    @ApiModelProperty(value = "用户编号")
    @Excel(name = "用户编号")
    private String userId;
    /** 经度 */
    @ApiModelProperty(value = "经度")
    @Excel(name = "经度")
    private String longtiud;
    /** 纬度 */
    @ApiModelProperty(value = "纬度")
    @Excel(name = "纬度")
    private String latitude;
    /** 样式 */
    @ApiModelProperty(value = "样式")
    @Excel(name = "样式")
    private String style;
    /** 缩略图 */
    @ApiModelProperty(value = "缩略图")
    @Excel(name = "缩略图")
    private String thumbnail;
    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间")
    private Date createTime;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }


    public void setTrackId(String trackId){
        this.trackId = trackId;
    }

    public String getTrackId(){
        return trackId;
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


    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }


    public void setLongtiud(String longtiud){
        this.longtiud = longtiud;
    }

    public String getLongtiud(){
        return longtiud;
    }


    public void setLatitude(String latitude){
        this.latitude = latitude;
    }

    public String getLatitude(){
        return latitude;
    }


    public void setStyle(String style){
        this.style = style;
    }

    public String getStyle(){
        return style;
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



    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("trackId",getTrackId())
            .append("name",getName())
            .append("code",getCode())
            .append("userId",getUserId())
            .append("longtiud",getLongtiud())
            .append("latitude",getLatitude())
            .append("style",getStyle())
            .append("thumbnail",getThumbnail())
            .append("createTime",getCreateTime())
            .toString();
    }
}
