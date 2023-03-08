package com.hndist.server.domain;

import com.hndist.framework.annotation.Excel;
import com.hndist.framework.core.domain.BaseEntity;
import com.hndist.framework.domain.SysMenu;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @Title: OmpMenuSys
 * @Description: 一张图菜单权限表
 * @author AI Builder
 * @date 2023-02-07 03:26:29
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class OmpMenuSys extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /**
     * 日期格式化
     */
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** 系统菜单ID */
    @Excel(name = "系统菜单ID")
    private Long id;
    /** 基础菜单ID */
    @Excel(name = "基础菜单ID")
    private Long menuId;
    /** 所属系统 */
    @Excel(name = "所属系统")
    private Long systemId;
    /** 菜单名称 */
    @Excel(name = "菜单名称")
    private String name;
    /** 父菜单ID */
    @Excel(name = "父菜单ID")
    private Long pid;
    /** 菜单对照码 */
    @Excel(name = "菜单对照码")
    private Integer orderNum;
    /** menu_code */
    @Excel(name = "menu_code")
    private Integer menuCode;
    /** 菜单类型（M目录 F功能 B按钮） */
    @Excel(name = "菜单类型（M目录 F功能 B按钮）")
    private String menuType;
    /** 是否公共菜单（0否 1是） */
    @Excel(name = "是否公共菜单（0否 1是）")
    private String pub;
    /** 菜单状态（0显示 1隐藏） */
    @Excel(name = "菜单状态（0显示 1隐藏）")
    private String visible;
    /** 菜单状态（0正常 1停用） */
    @Excel(name = "菜单状态（0正常 1停用）")
    private String status;
    /** 删除状态（0正常 2删除） */
    @Excel(name = "菜单状态（0正常 2删除）")
    private String delFlag;
    /** 服务名称 */
    @Excel(name = "服务名称")
    private String serviceName;
    /** 服务类型 */
    @Excel(name = "服务类型")
    private String serviceType;
    /** 服务地址 */
    @Excel(name = "服务地址")
    private String serviceUrl;
    /** 缩放最大值 */
    @Excel(name = "缩放最大值")
    private Integer maxScaleVal;
    /** 缩放最小值 */
    @Excel(name = "缩放最小值")
    private Integer minScaleVal;
    /** 是否定位 */
    @Excel(name = "是否定位")
    private Boolean zoomTo;
    /** 不透明工具栏 */
    @Excel(name = "不透明工具栏")
    private Boolean opacityBar;
    /** 透明度 */
    @Excel(name = "透明度")
    private Double opacityVal;
    /** 加载遮罩层显示 */
    @Excel(name = "加载遮罩层显示")
    private Boolean loadVisible;
    /** perms */
    @Excel(name = "perms")
    private String perms;
    /** 创建者 */
    @Excel(name = "创建者")
    private String createBy;
    /** 创建时间 */
    @Excel(name = "创建时间")
    private Date createTime;
    /** 更新者 */
    @Excel(name = "更新者")
    private String updateBy;
    /** 更新时间 */
    @Excel(name = "更新时间")
    private Date updateTime;
    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 子菜单 */
    private List<OmpMenuSys> children = new ArrayList<OmpMenuSys>();

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }


    public void setSystemId(Long systemId){
        this.systemId = systemId;
    }

    public Long getSystemId(){
        return systemId;
    }


    public void setName(String name){
        this.name = name;
    }

    @NotBlank(message = "菜单名称不能为空")
    public String getName(){
        return name;
    }


    public void setPid(Long pid){
        this.pid = pid;
    }

    public Long getPid(){
        return pid;
    }


    public void setOrderNum(Integer orderNum){
        this.orderNum = orderNum;
    }

    @NotNull(message = "显示顺序不能为空")
    public Integer getOrderNum(){
        return orderNum;
    }


    public void setMenuCode(Integer menuCode){
        this.menuCode = menuCode;
    }

    public Integer getMenuCode(){
        return menuCode;
    }


    public void setMenuType(String menuType){
        this.menuType = menuType;
    }

    @NotBlank(message = "菜单类型不能为空")
    public String getMenuType(){
        return menuType;
    }


    public void setPub(String pub){
        this.pub = pub;
    }

    public String getPub(){
        return pub;
    }


    public void setVisible(String visible){
        this.visible = visible;
    }

    public String getVisible(){
        return visible;
    }


    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void setServiceName(String serviceName){
        this.serviceName = serviceName;
    }

    public String getServiceName(){
        return serviceName;
    }


    public void setServiceType(String serviceType){
        this.serviceType = serviceType;
    }

    public String getServiceType(){
        return serviceType;
    }


    public void setServiceUrl(String serviceUrl){
        this.serviceUrl = serviceUrl;
    }

    public String getServiceUrl(){
        return serviceUrl;
    }


    public Integer getMaxScaleVal() {
        return maxScaleVal;
    }

    public void setMaxScaleVal(Integer maxScaleVal) {
        this.maxScaleVal = maxScaleVal;
    }

    public Integer getMinScaleVal() {
        return minScaleVal;
    }

    public void setMinScaleVal(Integer minScaleVal) {
        this.minScaleVal = minScaleVal;
    }

    public void setZoomTo(Boolean zoomTo){
        this.zoomTo = zoomTo;
    }

    public Boolean getZoomTo(){
        return zoomTo;
    }


    public void setOpacityBar(Boolean opacityBar){
        this.opacityBar = opacityBar;
    }

    public Boolean getOpacityBar(){
        return opacityBar;
    }


    public void setOpacityVal(Double opacityVal){
        this.opacityVal = opacityVal;
    }

    public Double getOpacityVal(){
        return opacityVal;
    }


    public void setLoadVisible(Boolean loadVisible){
        this.loadVisible = loadVisible;
    }

    public Boolean getLoadVisible(){
        return loadVisible;
    }


    public void setPerms(String perms){
        this.perms = perms;
    }

    public String getPerms(){
        return perms;
    }


    public void setCreateBy(String createBy){
        this.createBy = createBy;
    }

    public String getCreateBy(){
        return createBy;
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

    public void setUpdateBy(String updateBy){
        this.updateBy = updateBy;
    }

    public String getUpdateBy(){
        return updateBy;
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

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark(){
        return remark;
    }

    public List<OmpMenuSys> getChildren()
    {
        return children;
    }

    public void setChildren(List<OmpMenuSys> children)
    {
        this.children = children;
    }





    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("systemId",getSystemId())
            .append("name",getName())
            .append("pid",getPid())
            .append("orderNum",getOrderNum())
            .append("menuCode",getMenuCode())
            .append("menuType",getMenuType())
            .append("pub",getPub())
            .append("visible",getVisible())
            .append("status",getStatus())
            .append("serviceName",getServiceName())
            .append("serviceType",getServiceType())
            .append("serviceUrl",getServiceUrl())
            .append("maxScaleVal",getMaxScaleVal())
            .append("minScaleVal",getMinScaleVal())
            .append("zoomTo",getZoomTo())
            .append("opacityBar",getOpacityBar())
            .append("opacityVal",getOpacityVal())
            .append("loadVisible",getLoadVisible())
            .append("perms",getPerms())
            .append("createBy",getCreateBy())
            .append("createTime",getCreateTime())
            .append("updateBy",getUpdateBy())
            .append("updateTime",getUpdateTime())
            .append("remark",getRemark())
            .toString();
    }
}
