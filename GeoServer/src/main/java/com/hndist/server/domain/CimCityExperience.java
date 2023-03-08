package com.hndist.server.domain;

import com.hndist.framework.annotation.Excel;
import com.hndist.framework.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @Title: CimCityExperience
 * @Description: 城市体验
 * @author AI Builder
 * @date 2023-01-11 07:11:11
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="城市体验", description = "城市体验")
public class CimCityExperience extends BaseEntity
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
    /** 父编号 */
    @ApiModelProperty(value = "父编号")
    @Excel(name = "父编号")
    private String pid;
    /** 是否父级 1是0否 */
    @ApiModelProperty(value = "是否父级 1是0否")
    @Excel(name = "是否父级 1是0否")
    private String isparent;
    /** 名称 */
    @ApiModelProperty(value = "名称")
    @Excel(name = "名称")
    private String name;
    /** 图标 */
    @ApiModelProperty(value = "图标")
    @Excel(name = "图标")
    private String icon;
    /** 缩略图 */
    @ApiModelProperty(value = "缩略图")
    @Excel(name = "缩略图")
    private String thumbnail;
    /** 工具类型 */
    @ApiModelProperty(value = "工具类型 (数据字典：sysTool)")
    @Excel(name = "工具类型")
    private String tool;
    /** 行政区 */
    @ApiModelProperty(value = "行政区")
    @Excel(name = "行政区")
    private String xzq;
    /** 行政区编码 */
    @ApiModelProperty(value = "行政区编码")
    @Excel(name = "行政区编码")
    private String xzqCode;
    /** 资源目录编号 */
    @ApiModelProperty(value = "资源目录编号")
    @Excel(name = "资源目录编号")
    private String resourceId;
    /** 是否选中 1是0否 */
    @ApiModelProperty(value = "是否选中 1是0否")
    @Excel(name = "是否选中 1是0否")
    private String isselected;
    /** 专题样式 */
    @ApiModelProperty(value = "专题样式")
    @Excel(name = "专题样式 (数据字典：sysSpecialStyle)")
    private String specialStyle;
    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间")
    private Date createTime;
    /** 排序 */
    @ApiModelProperty(value = "排序")
    @Excel(name = "排序")
    private Integer reorder;
    @ApiModelProperty(value = "区域代码")
    @Excel(name = "区域代码")
    private String areaCode;
    @ApiModelProperty(value = "管理专题")
    @Excel(name = "管理专题")
    private String manageSpecial;
    /**
     * 子节点
     */
    private List<CimCityExperience> children;
    /**
     * 资源目录对象 一对一
     */
    private CimMapresources mapresources;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
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


    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }


    public void setIcon(String icon){
        this.icon = icon;
    }

    public String getIcon(){
        return icon;
    }


    public void setThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }

    public String getThumbnail(){
        return thumbnail;
    }


    public void setTool(String tool){
        this.tool = tool;
    }

    public String getTool(){
        return tool;
    }


    public void setXzq(String xzq){
        this.xzq = xzq;
    }

    public String getXzq(){
        return xzq;
    }


    public void setXzqCode(String xzqCode){
        this.xzqCode = xzqCode;
    }

    public String getXzqCode(){
        return xzqCode;
    }


    public void setResourceId(String resourceId){
        this.resourceId = resourceId;
    }

    public String getResourceId(){
        return resourceId;
    }


    public void setIsselected(String isselected){
        this.isselected = isselected;
    }

    public String getIsselected(){
        return isselected;
    }


    public void setSpecialStyle(String specialStyle){
        this.specialStyle = specialStyle;
    }

    public String getSpecialStyle(){
        return specialStyle;
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

    public void setReorder(Integer reorder){
        this.reorder = reorder;
    }

    public Integer getReorder(){
        return reorder;
    }

    public List<CimCityExperience> getChildren() {
        return children;
    }

    public void setChildren(List<CimCityExperience> children) {
        this.children = children;
    }

    public CimMapresources getMapresources() {
        return mapresources;
    }

    public void setMapresources(CimMapresources mapresources) {
        this.mapresources = mapresources;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getManageSpecial() {
        return manageSpecial;
    }

    public void setManageSpecial(String manageSpecial) {
        this.manageSpecial = manageSpecial;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("pid",getPid())
            .append("isparent",getIsparent())
            .append("name",getName())
            .append("icon",getIcon())
            .append("thumbnail",getThumbnail())
            .append("tool",getTool())
            .append("xzq",getXzq())
            .append("xzqCode", getXzqCode())
            .append("resourceId",getResourceId())
            .append("isselected",getIsselected())
            .append("specialStyle",getSpecialStyle())
            .append("createTime",getCreateTime())
            .append("reorder",getReorder())
            .toString();
    }
}
