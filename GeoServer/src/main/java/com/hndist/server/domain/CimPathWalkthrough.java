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
 * @Title: CimPathWalkthrough
 * @Description: 路径漫游
 * @author AI Builder
 * @date 2022-12-01 10:07:23
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="路径漫游", description = "路径漫游")
public class CimPathWalkthrough extends BaseEntity
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
    /** 速度 */
    @ApiModelProperty(value = "速度")
    @Excel(name = "速度")
    private Integer speed;
    /** 高度 */
    @ApiModelProperty(value = "高度")
    @Excel(name = "高度")
    private Integer height;
    /** 视距 */
    @ApiModelProperty(value = "视距")
    @Excel(name = "视距")
    private Integer distance;
    /** 运动物体 */
    @ApiModelProperty(value = "运动物体")
    @Excel(name = "运动物体")
    private String model;
    /** 视角 */
    @ApiModelProperty(value = "视角")
    @Excel(name = "视角")
    private String pitch;
    /** 坐标 */
    @ApiModelProperty(value = "坐标")
    @Excel(name = "坐标")
    private String positions;
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


    public void setSpeed(Integer speed){
        this.speed = speed;
    }

    public Integer getSpeed(){
        return speed;
    }


    public void setHeight(Integer height){
        this.height = height;
    }

    public Integer getHeight(){
        return height;
    }


    public void setDistance(Integer distance){
        this.distance = distance;
    }

    public Integer getDistance(){
        return distance;
    }


    public void setModel(String model){
        this.model = model;
    }

    public String getModel(){
        return model;
    }


    public void setPitch(String pitch){
        this.pitch = pitch;
    }

    public String getPitch(){
        return pitch;
    }


    public void setPositions(String positions){
        this.positions = positions;
    }

    public String getPositions(){
        return positions;
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
            .append("speed",getSpeed())
            .append("height",getHeight())
            .append("distance",getDistance())
            .append("model",getModel())
            .append("pitch",getPitch())
            .append("positions",getPositions())
            .append("createTime",getCreateTime())
            .toString();
    }
}
