package com.hndist.server.domain;

import com.hndist.framework.annotation.Excel;
import com.hndist.framework.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title: CimServer
 * @Description: 服务
 * @author AI Builder
 * @date 2022-03-31 03:42:30
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(description = "GIS服务器")
public class CimServer extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /**
     * 日期格式化
     */
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** 编号 */
    @ApiModelProperty(value = "主键")
    @Excel(name = "编号")
    private String id;

    /** 服务名称 */
    @ApiModelProperty(value = "服务名称")
    @Excel(name = "服务名称")
    private String name;

    /** ip */
    @ApiModelProperty(value = "ip")
    @Excel(name = "ip")
    private String ip;

    /** 端口号 */
    @ApiModelProperty(value = "端口号")
    @Excel(name = "端口号")
    private String port;

    /** arcGis 服务引擎*/
    @ApiModelProperty(value = "服务引擎")
    private String serviceengine;

    /** 创建时间 */
    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    /** 描述*/
    @ApiModelProperty(value = "描述")
    private String context;

    /** 父编号*/
    @ApiModelProperty(value = "父编号")
    private String pid;

    /** 20221202前端要求加的字段*/
    @ApiModelProperty(value = "label")
    private String label;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getServiceengine() {
        return serviceengine;
    }

    public void setServiceengine(String serviceengine) {
        this.serviceengine = serviceengine;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "CimServer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", serviceengine='" + serviceengine + '\'' +
                ", createTime=" + createTime +
                ", context='" + context + '\'' +
                '}';
    }
}
