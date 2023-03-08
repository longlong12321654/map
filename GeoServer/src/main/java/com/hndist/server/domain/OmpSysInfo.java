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
/**
 * @Title: OmpSysInfo
 * @Description: 一张图系统信息表
 * @author AI Builder
 * @date 2023-02-09 10:17:07
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class OmpSysInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /**
     * 日期格式化
     */
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** 系统ID */
    @Excel(name = "系统ID")
    private Long id;
    /** 系统名称 */
    @Excel(name = "系统名称")
    private String sysName;
    /** 系统识别码 */
    @Excel(name = "系统识别码")
    private String sysKey;
    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNum;
    /** 系统描述 */
    @Excel(name = "系统描述")
    private String sysDes;
    /** 系统状态 */
    @Excel(name = "系统状态")
    private String status;
    /** 删除标志 */
    @Excel(name = "删除标志")
    private String delFlag;
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

    /** 菜单组 */
    private Long[] menuIds;

    /** 关联菜单 */
    private List<OmpMenuSys> menuList;



    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }


    public void setSysName(String sysName){
        this.sysName = sysName;
    }

    public String getSysName(){
        return sysName;
    }


    public void setSysKey(String sysKey){
        this.sysKey = sysKey;
    }

    public String getSysKey(){
        return sysKey;
    }


    public void setOrderNum(Integer orderNum){
        this.orderNum = orderNum;
    }

    public Integer getOrderNum(){
        return orderNum;
    }


    public void setSysDes(String sysDes){
        this.sysDes = sysDes;
    }

    public String getSysDes(){
        return sysDes;
    }


    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }


    public void setDelFlag(String delFlag){
        this.delFlag = delFlag;
    }

    public String getDelFlag(){
        return delFlag;
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

    public Long[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Long[] menuIds) {
        this.menuIds = menuIds;
    }


    public List<OmpMenuSys> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<OmpMenuSys> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("sysName",getSysName())
            .append("sysKey",getSysKey())
            .append("orderNum",getOrderNum())
            .append("sysDes",getSysDes())
            .append("status",getStatus())
            .append("delFlag",getDelFlag())
            .append("createBy",getCreateBy())
            .append("createTime",getCreateTime())
            .append("updateBy",getUpdateBy())
            .append("updateTime",getUpdateTime())
            .append("remark",getRemark())
            .toString();
    }
}
