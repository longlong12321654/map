package com.hndist.server.domain;

import com.hndist.framework.annotation.Excel;
import com.hndist.framework.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title: CimDmdz
 * @Description: 地名地址
 * @author AI Builder
 * @date 2022-03-31 03:42:27
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel("地名地址")
public class CimDmdz extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /**
     * 日期格式化
     */
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** 编号 */
    @ApiModelProperty("id")
    @Excel(name = "编号")
    private String id;

    /** 名称 */
    @ApiModelProperty("名称")
    @Excel(name = "名称")
    private String name;

    /** 图片地址 */
    @ApiModelProperty("图片地址")
    @Excel(name = "图片地址")
    private String imageUrl;

    /** 视频地址 */
    @ApiModelProperty("视频地址")
    @Excel(name = "视频地址")
    private String videoUrl;

    /** 详细地址 */
    @ApiModelProperty("详细地址")
    @Excel(name = "详细地址")
    private String address;

    /** 地名状态 */
    @ApiModelProperty("地名状态 0=待审核,1=审核通过,2=驳回")
    @Excel(name = "地名状态", readConverterExp = "0=待审核,1=审核通过,2=驳回")
    private String dmStatus;

    /** 创建人 */
    @ApiModelProperty("创建人")
    @Excel(name = "创建人")
    private String createBy;

    /** 修改人 */
    @ApiModelProperty("修改人")
    @Excel(name = "修改人")
    private String updateBy;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间 */
    @ApiModelProperty("更新时间")
    @Excel(name = "更新时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 是否推荐 */
    @ApiModelProperty("是否推荐")
    @Excel(name = "是否推荐", readConverterExp = "1=是,0=否")
    private String recommend;

    /** 分类 */
    @Excel(name = "分类")
    @ApiModelProperty("分类")
    private String useSearch;

    /** 权限 */
    @ApiModelProperty("权限")
    @Excel(name = "权限")
    private String permi;

    /** 坐标 */
    @ApiModelProperty("坐标")
    @Excel(name = "坐标")
    private String coordinate;

    /** 备注 */
    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String memo;

    /** 列表类型 1正常列表 2推荐列表*/
    private String listType;

    @ApiModelProperty("高程")
    private String altitude;

    @ApiModelProperty("设备类型")
    private String deviceType;

    @ApiModelProperty("设备地址")
    private String deviceAddr;



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


    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl(){
        return imageUrl;
    }


    public void setVideoUrl(String videoUrl){
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl(){
        return videoUrl;
    }


    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }


    public void setDmStatus(String dmStatus){
        this.dmStatus = dmStatus;
    }

    public String getDmStatus(){
        return dmStatus;
    }


    public void setCreateBy(String createBy){
        this.createBy = createBy;
    }

    public String getCreateBy(){
        return createBy;
    }


    public void setUpdateBy(String updateBy){
        this.updateBy = updateBy;
    }

    public String getUpdateBy(){
        return updateBy;
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

    public void setRecommend(String recommend){
        this.recommend = recommend;
    }

    public String getRecommend(){
        return recommend;
    }


    public void setUseSearch(String useSearch){
        this.useSearch = useSearch;
    }

    public String getUseSearch(){
        return useSearch;
    }


    public void setPermi(String permi){
        this.permi = permi;
    }

    public String getPermi(){
        return permi;
    }


    public void setCoordinate(String coordinate){
        this.coordinate = coordinate;
    }

    public String getCoordinate(){
        return coordinate;
    }


    public void setMemo(String memo){
        this.memo = memo;
    }

    public String getMemo(){
        return memo;
    }


    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceAddr() {
        return deviceAddr;
    }

    public void setDeviceAddr(String deviceAddr) {
        this.deviceAddr = deviceAddr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("name",getName())
            .append("imageUrl",getImageUrl())
            .append("videoUrl",getVideoUrl())
            .append("address",getAddress())
            .append("dmStatus",getDmStatus())
            .append("createBy",getCreateBy())
            .append("updateBy",getUpdateBy())
            .append("createTime",getCreateTime())
            .append("updateTime",getUpdateTime())
            .append("recommend",getRecommend())
            .append("useSearch",getUseSearch())
            .append("permi",getPermi())
            .append("coordinate",getCoordinate())
            .append("memo",getMemo())
            .toString();
    }
}
