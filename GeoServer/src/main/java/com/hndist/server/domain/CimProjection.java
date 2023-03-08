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
 * @Title: CimProjection
 * @Description: 投影坐标
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="投影坐标", description = "投影坐标")
public class CimProjection extends BaseEntity
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
    /** 投影编码 */
    @ApiModelProperty(value = "投影编码")
    @Excel(name = "投影编码")
    private String projectionId;
    /** 投影名称 */
    @ApiModelProperty(value = "投影名称")
    @Excel(name = "投影名称")
    private String name;
    /** 地理坐标系 */
    @ApiModelProperty(value = "地理坐标系")
    @Excel(name = "地理坐标系")
    private String geographic;
    /** 角度单位 */
    @ApiModelProperty(value = "角度单位")
    @Excel(name = "角度单位")
    private String coordinateSystem;
    /** 角度单位 */
    @ApiModelProperty(value = "角度单位")
    @Excel(name = "角度单位")
    private String angularUnit;
    /** 本初子午线 */
    @ApiModelProperty(value = "本初子午线")
    @Excel(name = "本初子午线")
    private String primeMeridian;
    /** 数据 */
    @ApiModelProperty(value = "数据")
    @Excel(name = "数据")
    private String datum;
    /** 球体 */
    @ApiModelProperty(value = "球体")
    @Excel(name = "球体")
    private Integer spheroid;
    /** 半长轴 */
    @ApiModelProperty(value = "半长轴")
    @Excel(name = "半长轴")
    private String semimajorAxis;
    /** 半短轴 */
    @ApiModelProperty(value = "半短轴")
    @Excel(name = "半短轴")
    private String semiminorAxis;
    /** 扁率 */
    @ApiModelProperty(value = "扁率")
    @Excel(name = "扁率")
    private String inverseFlattening;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }


    public void setProjectionId(String projectionId){
        this.projectionId = projectionId;
    }

    public String getProjectionId(){
        return projectionId;
    }


    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }


    public void setGeographic(String geographic){
        this.geographic = geographic;
    }

    public String getGeographic(){
        return geographic;
    }


    public void setCoordinateSystem(String coordinateSystem){
        this.coordinateSystem = coordinateSystem;
    }

    public String getCoordinateSystem(){
        return coordinateSystem;
    }


    public void setAngularUnit(String angularUnit){
        this.angularUnit = angularUnit;
    }

    public String getAngularUnit(){
        return angularUnit;
    }


    public void setPrimeMeridian(String primeMeridian){
        this.primeMeridian = primeMeridian;
    }

    public String getPrimeMeridian(){
        return primeMeridian;
    }


    public void setDatum(String datum){
        this.datum = datum;
    }

    public String getDatum(){
        return datum;
    }


    public void setSpheroid(Integer spheroid){
        this.spheroid = spheroid;
    }

    public Integer getSpheroid(){
        return spheroid;
    }


    public void setSemimajorAxis(String semimajorAxis){
        this.semimajorAxis = semimajorAxis;
    }

    public String getSemimajorAxis(){
        return semimajorAxis;
    }


    public void setSemiminorAxis(String semiminorAxis){
        this.semiminorAxis = semiminorAxis;
    }

    public String getSemiminorAxis(){
        return semiminorAxis;
    }


    public void setInverseFlattening(String inverseFlattening){
        this.inverseFlattening = inverseFlattening;
    }

    public String getInverseFlattening(){
        return inverseFlattening;
    }




    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("projectionId",getProjectionId())
            .append("name",getName())
            .append("geographic",getGeographic())
            .append("coordinateSystem",getCoordinateSystem())
            .append("angularUnit",getAngularUnit())
            .append("primeMeridian",getPrimeMeridian())
            .append("datum",getDatum())
            .append("spheroid",getSpheroid())
            .append("semimajorAxis",getSemimajorAxis())
            .append("semiminorAxis",getSemiminorAxis())
            .append("inverseFlattening",getInverseFlattening())
            .toString();
    }
}
