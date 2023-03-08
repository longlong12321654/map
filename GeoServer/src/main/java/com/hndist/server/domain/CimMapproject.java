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
 * @Title: CimMapproject
 * @Description: 地图工程
 * @author AI Builder
 * @date 2022-11-14 03:18:41
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="地图工程", description = "地图工程")
public class CimMapproject extends BaseEntity
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
    /** 工程名称 */
    @ApiModelProperty(value = "工程名称")
    @Excel(name = "工程名称")
    private String name;
    /** 是否父级 1是0否 */
    @ApiModelProperty(value = "是否父级 1是0否")
    @Excel(name = "是否父级 1是0否")
    private String isparent;
    /** 工程地址 */
    @ApiModelProperty(value = "工程地址")
    @Excel(name = "工程地址")
    private String projectpath;
    /** 坐标系 */
    @ApiModelProperty(value = "坐标系")
    @Excel(name = "坐标系")
    private String spatialReference;
    /** 缩略图 */
    @ApiModelProperty(value = "缩略图")
    @Excel(name = "缩略图")
    private String thumbnail;
    /** 工程类型 */
    @ApiModelProperty(value = "工程类型")
    @Excel(name = "工程类型")
    private String projecttype;
    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间")
    private Date createTime;
    /** 工程描述 */
    @ApiModelProperty(value = "工程描述")
    @Excel(name = "工程描述")
    private String remark;
    /** 父节点 */
    @ApiModelProperty(value = "父节点")
    @Excel(name = "父节点")
    private String pid;
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


    public void setIsparent(String isparent){
        this.isparent = isparent;
    }

    public String getIsparent(){
        return isparent;
    }


    public void setProjectpath(String projectpath){
        this.projectpath = projectpath;
    }

    public String getProjectpath(){
        return projectpath;
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


    public void setProjecttype(String projecttype){
        this.projecttype = projecttype;
    }

    public String getProjecttype(){
        return projecttype;
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


    public void setPid(String pid){
        this.pid = pid;
    }

    public String getPid(){
        return pid;
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
            .append("isparent",getIsparent())
            .append("projectpath",getProjectpath())
            .append("spatialReference",getSpatialReference())
            .append("thumbnail",getThumbnail())
            .append("projecttype",getProjecttype())
            .append("createTime",getCreateTime())
            .append("remark",getRemark())
            .append("pid",getPid())
            .toString();
    }
}
