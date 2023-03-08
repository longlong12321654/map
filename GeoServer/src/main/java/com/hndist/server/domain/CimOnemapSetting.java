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
 * @Title: CimOnemapSetting
 * @Description: 一张图配置
 * @author AI Builder
 * @date 2022-11-16 02:52:39
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="一张图配置", description = "一张图配置")
public class CimOnemapSetting extends BaseEntity
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
    /** 系统名称 */
    @ApiModelProperty(value = "系统名称")
    @Excel(name = "系统名称")
    private String name;
    /** 系统LOGO */
    @ApiModelProperty(value = "系统LOGO")
    @Excel(name = "系统LOGO")
    private String logo;
    /** 头部显示 1是0否 */
    @ApiModelProperty(value = "头部显示 1是0否")
    @Excel(name = "头部显示 1是0否")
    private String ishead;
    /** 显示菜单 */
    @ApiModelProperty(value = "显示菜单")
    @Excel(name = "显示菜单")
    private String isLeftMenu;
    /** 显示地形 */
    @ApiModelProperty(value = "显示地形")
    @Excel(name = "显示地形")
    private String isTerrain;
    /** 显示等高线 */
    @ApiModelProperty(value = "显示等高线")
    @Excel(name = "显示等高线")
    private String iscontour;
    /** 显示经纬度 */
    @ApiModelProperty(value = "显示经纬度")
    @Excel(name = "显示经纬度")
    private String isWarp;
    /** 系统皮肤（数据字典 */
    @ApiModelProperty(value = "系统皮肤（数据字典")
    @Excel(name = "系统皮肤（数据字典")
    private String skins;
    /** 用户编号 */
    @ApiModelProperty(value = "用户编号")
    @Excel(name = "用户编号")
    private String userId;
    /** 角色编号 */
    @ApiModelProperty(value = "角色编号")
    @Excel(name = "角色编号")
    private String roleId;
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


    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }


    public void setLogo(String logo){
        this.logo = logo;
    }

    public String getLogo(){
        return logo;
    }


    public void setIshead(String ishead){
        this.ishead = ishead;
    }

    public String getIshead(){
        return ishead;
    }


    public void setIsLeftMenu(String isLeftMenu){
        this.isLeftMenu = isLeftMenu;
    }

    public String getIsLeftMenu(){
        return isLeftMenu;
    }


    public void setIsTerrain(String isTerrain){
        this.isTerrain = isTerrain;
    }

    public String getIsTerrain(){
        return isTerrain;
    }


    public void setIscontour(String iscontour){
        this.iscontour = iscontour;
    }

    public String getIscontour(){
        return iscontour;
    }


    public void setIsWarp(String isWarp){
        this.isWarp = isWarp;
    }

    public String getIsWarp(){
        return isWarp;
    }


    public void setSkins(String skins){
        this.skins = skins;
    }

    public String getSkins(){
        return skins;
    }


    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }


    public void setRoleId(String roleId){
        this.roleId = roleId;
    }

    public String getRoleId(){
        return roleId;
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
            .append("name",getName())
            .append("logo",getLogo())
            .append("ishead",getIshead())
            .append("isLeftMenu", getIsLeftMenu())
            .append("isTerrain", getIsTerrain())
            .append("iscontour",getIscontour())
            .append("isWarp", getIsWarp())
            .append("skins",getSkins())
            .append("userId",getUserId())
            .append("roleId",getRoleId())
            .append("createTime",getCreateTime())
            .toString();
    }
}
