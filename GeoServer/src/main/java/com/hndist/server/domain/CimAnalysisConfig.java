package com.hndist.server.domain;

import com.hndist.framework.annotation.Excel;
import com.hndist.framework.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title: CimAnalysisConfig
 * @Description: 一键分析
 * @author AI Builder
 * @date 2022-03-31 03:42:25
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(description = "一键分析")
public class CimAnalysisConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /**
     * 日期格式化
     */
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** id */
    @Excel(name = "id")
    private String id;

    /** 创建者 */
    @ApiModelProperty("创建人")
    @Excel(name = "创建者")
    private String createBy;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    @Excel(name = "创建时间")
    private Date createTime;

    /** 修改者 */
    @ApiModelProperty("修改人")
    @Excel(name = "修改者")
    private String updateBy;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    @Excel(name = "修改时间")
    private Date updateTime;

    /** 分析名称 */
    @ApiModelProperty("分析名称")
    @Excel(name = "分析名称")
    private String name;

    /** 分析类型 */
    @ApiModelProperty("分析类型")
    @Excel(name = "分析类型")
    private String type;

    /** 分析参数 */
    @ApiModelProperty("分析参数")
    @Excel(name = "分析参数")
    private String param;

    /** 备注 */
    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String memo;

    /** pid */
    @ApiModelProperty(value = "父id")
    private String pid;

    /** 服务url */
    @ApiModelProperty(value = "服务url")
    private String serviceUrl;

    /** 字段 */
    @ApiModelProperty(value = "字段")
    private String viewFields;

    /** 结论 */
    @ApiModelProperty(value = "结论")
    private String conclesion;

    /** 是否为目录 1是0否 */
    @ApiModelProperty(value = "是否为目录 1是0否")
    private String content;

    @ApiModelProperty(value = "标签")
    private String label;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "服务器编号")
    private String serverId;
    @ApiModelProperty(value = "服务引擎")
    private String serviceengine;
    private String ip;
    private String port;


    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
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

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }


    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }


    public void setParam(String param){
        this.param = param;
    }

    public String getParam(){
        return param;
    }


    public void setMemo(String memo){
        this.memo = memo;
    }

    public String getMemo(){
        return memo;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getViewFields() {
        return viewFields;
    }

    public void setViewFields(String viewFields) {
        this.viewFields = viewFields;
    }

    public String getConclesion() {
        return conclesion;
    }

    public void setConclesion(String conclesion) {
        this.conclesion = conclesion;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getServiceengine() {
        return serviceengine;
    }

    public void setServiceengine(String serviceengine) {
        this.serviceengine = serviceengine;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("createBy",getCreateBy())
            .append("createTime",getCreateTime())
            .append("updateBy",getUpdateBy())
            .append("updateTime",getUpdateTime())
            .append("name",getName())
            .append("type",getType())
            .append("param",getParam())
            .append("memo",getMemo())
            .toString();
    }
}
