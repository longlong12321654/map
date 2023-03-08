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
 * @Title: CimMapmark
 * @Description: 标绘&标注管理
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="标绘管理", description = "标绘管理")
public class CimMapmark extends BaseEntity
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
    /** 标注名称 */
    @ApiModelProperty(value = "标注名称")
    @Excel(name = "标注名称")
    private String name;
    /** 标注类型 1标绘2标注 */
    @ApiModelProperty(value = "标注类型 1标绘2标注")
    @Excel(name = "标注类型 1标绘2标注")
    private String marktype;
    /** 是否显示 1是0否 默认1 */
    @ApiModelProperty(value = "是否显示 1是0否 默认1")
    @Excel(name = "是否显示 1是0否 默认1")
    private String show;
    /** 标绘JSON */
    @ApiModelProperty(value = "标绘JSON")
    @Excel(name = "标绘JSON")
    private String drawjson;
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
    private String context;

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


    public void setMarktype(String marktype){
        this.marktype = marktype;
    }

    public String getMarktype(){
        return marktype;
    }


    public void setShow(String show){
        this.show = show;
    }

    public String getShow(){
        return show;
    }


    public void setDrawjson(String drawjson){
        this.drawjson = drawjson;
    }

    public String getDrawjson(){
        return drawjson;
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

    public void setContext(String context){
        this.context = context;
    }

    public String getContext(){
        return context;
    }




    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("name",getName())
            .append("marktype",getMarktype())
            .append("show",getShow())
            .append("drawjson",getDrawjson())
            .append("thumbnail",getThumbnail())
            .append("createTime",getCreateTime())
            .append("context",getContext())
            .toString();
    }
}
