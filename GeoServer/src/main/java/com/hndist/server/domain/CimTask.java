package com.hndist.server.domain;

import com.hndist.framework.annotation.Excel;
import com.hndist.framework.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @Title: CimTask
 * @Description: 任务管理
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="任务管理", description = "任务管理")
public class CimTask extends BaseEntity
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
    /** 任务名称 */
    @ApiModelProperty(value = "任务名称")
    @Excel(name = "任务名称")
    private String name;
    /** 任务类型 */
    @ApiModelProperty(value = "任务类型")
    @Excel(name = "任务类型")
    private String tasktype;
    /** 任务内容 */
    @ApiModelProperty(value = "任务内容")
    @Excel(name = "任务内容")
    private String taskremark;
    /** 完成进度 1-100% */
    @ApiModelProperty(value = "完成进度 1-100%")
    @Excel(name = "完成进度 1-100%")
    private Integer progress;
    /** 任务状态 */
    @ApiModelProperty(value = "任务状态")
    @Excel(name = "任务状态")
    private String taskstatus;
    /** 开始时间 */
    @ApiModelProperty(value = "开始时间")
    @Excel(name = "开始时间")
    private Date begintime;
    /** 结束时间 */
    @ApiModelProperty(value = "结束时间")
    @Excel(name = "结束时间")
    private Date endtime;
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


    public void setTasktype(String tasktype){
        this.tasktype = tasktype;
    }

    public String getTasktype(){
        return tasktype;
    }


    public void setTaskremark(String taskremark){
        this.taskremark = taskremark;
    }

    public String getTaskremark(){
        return taskremark;
    }


    public void setProgress(Integer progress){
        this.progress = progress;
    }

    public Integer getProgress(){
        return progress;
    }


    public void setTaskstatus(String taskstatus){
        this.taskstatus = taskstatus;
    }

    public String getTaskstatus(){
        return taskstatus;
    }


    public void setBegintime(Date begintime){
        this.begintime = begintime;
    }

    public Date getBegintime(){
        return begintime;
    }

    public String getPBegintime(){
        if(this.begintime!=null){
            return dateFormat.format(this.begintime);
        }
        return "";
    }

    public void setEndtime(Date endtime){
        this.endtime = endtime;
    }

    public Date getEndtime(){
        return endtime;
    }

    public String getPEndtime(){
        if(this.endtime!=null){
            return dateFormat.format(this.endtime);
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
            .append("tasktype",getTasktype())
            .append("taskremark", getTaskremark())
            .append("progress",getProgress())
            .append("taskstatus",getTaskstatus())
            .append("begintime",getBegintime())
            .append("endtime",getEndtime())
            .append("context",getContext())
            .toString();
    }
}
