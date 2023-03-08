package com.hndist.server.domain;

import com.hndist.framework.annotation.Excel;
import com.hndist.framework.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Title: CimCity
 * @Description: 行政区划
 * @author AI Builder
 * @date 2022-07-28 15:22:03
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="行政区划", description = "行政区划")
public class CimCity extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /**
     * 日期格式化
     */
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** 编号 */
    @ApiModelProperty("编号")
    @Excel(name = "编号")
    private String id;
    /** 父节点 */
    @ApiModelProperty("父节点")
    @Excel(name = "父节点")
    private String pid;
    /** 字典编码 */
    @ApiModelProperty("字典编码")
    @Excel(name = "字典编码")
    private String code;
    /** 字典名称 */
    @ApiModelProperty("字典名称")
    @Excel(name = "字典名称")
    private String name;
    /** 拼音 */
    @ApiModelProperty("拼音")
    @Excel(name = "拼音")
    private String pinyin;
    /** 地图边界 */
    @ApiModelProperty("地图边界")
    @Excel(name = "地图边界")
    private String ploygon;
    /** 邮政编码 */
    @ApiModelProperty("邮政编码")
    @Excel(name = "邮政编码")
    private String zipCode;
    /** 是否父级 */
    @ApiModelProperty("是否父级")
    @Excel(name = "是否父级")
    private String isparent;
    @ApiModelProperty("图标")
    private String icon;
    /** 创建时间 */
    @ApiModelProperty("创建时间")
    @Excel(name = "创建时间")
    private Date createTime;

    /**
     * 子节点
     */
    @ApiModelProperty("子节点")
    private List<CimCity> children;

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


    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }


    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPloygon() {
        return ploygon;
    }

    public void setPloygon(String ploygon) {
        this.ploygon = ploygon;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getIsparent() {
        return isparent;
    }

    public void setIsparent(String isparent) {
        this.isparent = isparent;
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

    public List<CimCity> getChildren() {
        return children;
    }

    public void setChildren(List<CimCity> children) {
        this.children = children;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "CimCity{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", ploygon='" + ploygon + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", isparent='" + isparent + '\'' +
                ", icon='" + icon + '\'' +
                ", createTime=" + createTime +
                ", children=" + children +
                '}';
    }
}
