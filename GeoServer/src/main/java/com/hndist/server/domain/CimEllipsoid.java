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
 * @Title: CimEllipsoid
 * @Description: 椭球体
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="椭球体", description = "椭球体")
public class CimEllipsoid extends BaseEntity
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
    /** 椭球体名称 */
    @ApiModelProperty(value = "椭球体名称")
    @Excel(name = "椭球体名称")
    private String name;
    /** 长半轴 */
    @ApiModelProperty(value = "长半轴")
    @Excel(name = "长半轴")
    private String semimajorAxis;
    /** 短半轴 */
    @ApiModelProperty(value = "短半轴")
    @Excel(name = "短半轴")
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


    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
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
            .append("name",getName())
            .append("semimajorAxis",getSemimajorAxis())
            .append("semiminorAxis",getSemiminorAxis())
            .append("inverseFlattening",getInverseFlattening())
            .toString();
    }
}
