package com.hndist.server.domain;

import com.hndist.framework.annotation.Excel;
import com.hndist.framework.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @Title: CimMapsymbol
 * @Description: 符号库
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="符号库", description = "符号库")
public class CimMapsymbol extends BaseEntity
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
    /** 符号名称 */
    @ApiModelProperty(value = "符号名称")
    @Excel(name = "符号名称")
    private String name;
    /** 父节点 */
    @ApiModelProperty(value = "父节点")
    @Excel(name = "父节点")
    private String pid;
    /** 缩略图 */
    @ApiModelProperty(value = "缩略图")
    @Excel(name = "缩略图")
    private String thumbnail;
    /** 符号文本 */
    @ApiModelProperty(value = "符号文本")
    @Excel(name = "符号文本")
    private String symbol;
    /** 描述 */
    @ApiModelProperty(value = "描述")
    @Excel(name = "描述")
    private String context;
    /** 是否父级 */
    @ApiModelProperty(value = "是否父级 1是0否 字典sys_isparent")
    @Excel(name = "是否父级")
    private String isparent;
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


    public void setThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }

    public String getThumbnail(){
        return thumbnail;
    }


    public void setSymbol(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }


    public String getIsparent() {
        return isparent;
    }

    public void setIsparent(String isparent) {
        this.isparent = isparent;
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
            .append("thumbnail",getThumbnail())
            .append("symbol",getSymbol())
            .append("context",getContext())
            .append("isparent",getIsparent())
            .toString();
    }
}
