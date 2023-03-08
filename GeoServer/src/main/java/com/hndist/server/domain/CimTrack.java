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
 * @Title: CimTrack
 * @Description: 轨迹
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="轨迹", description = "轨迹")
public class CimTrack extends BaseEntity
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
    /** 父节点 */
    @ApiModelProperty(value = "父节点")
    @Excel(name = "父节点")
    private String pid;
    /** 是否显示 1是0否 */
    @ApiModelProperty(value = "是否显示 1是0否")
    @Excel(name = "是否显示 1是0否")
    private String show;
    /** 是否动态 1是0否 */
    @ApiModelProperty(value = "是否动态 1是0否")
    @Excel(name = "是否动态 1是0否")
    private String isdynamic;
    /** 显示样式 */
    @ApiModelProperty(value = "显示样式")
    @Excel(name = "显示样式")
    private String style;
    /** 是否父级 */
    @ApiModelProperty(value = "是否父级")
    @Excel(name = "是否父级")
    private String isparent;
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
    /** 图标 */
    @ApiModelProperty(value = "图标")
    @Excel(name = "图标")
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


    public void setPid(String pid){
        this.pid = pid;
    }

    public String getPid(){
        return pid;
    }


    public void setShow(String show){
        this.show = show;
    }

    public String getShow(){
        return show;
    }


    public void setIsdynamic(String isdynamic){
        this.isdynamic = isdynamic;
    }

    public String getIsdynamic(){
        return isdynamic;
    }


    public void setStyle(String style){
        this.style = style;
    }

    public String getStyle(){
        return style;
    }


    public void setIsparent(String isparent){
        this.isparent = isparent;
    }

    public String getIsparent(){
        return isparent;
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
            .append("pid",getPid())
            .append("show",getShow())
            .append("isdynamic",getIsdynamic())
            .append("style",getStyle())
            .append("isparent",getIsparent())
            .append("thumbnail",getThumbnail())
            .append("createTime",getCreateTime())
            .append("remark",getRemark())
            .append("icon",getIcon())
            .toString();
    }
}
