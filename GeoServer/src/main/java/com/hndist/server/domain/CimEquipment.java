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
 * @Title: CimEquipment
 * @Description: 设备信息表
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="设备信息", description = "设备信息")
public class CimEquipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /**
     * 日期格式化
     */
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** 主键 */
    @ApiModelProperty(value = "主键")
    @Excel(name = "主键")
    private String id;
    /** 创建人 */
    @ApiModelProperty(value = "创建人")
    @Excel(name = "创建人")
    private String createBy;
    /** 创建日期 */
    @ApiModelProperty(value = "创建日期")
    @Excel(name = "创建日期")
    private Date createTime;
    /** 更新人 */
    @ApiModelProperty(value = "更新人")
    @Excel(name = "更新人")
    private String updateBy;
    /** 更新日期 */
    @ApiModelProperty(value = "更新日期")
    @Excel(name = "更新日期")
    private Date updateTime;
    /** 所属部门 */
    @ApiModelProperty(value = "所属部门")
    @Excel(name = "所属部门")
    private String sysOrgCode;
    /** 排序 */
    @ApiModelProperty(value = "排序")
    @Excel(name = "排序")
    private Integer sort;
    /** 设备名称 */
    @ApiModelProperty(value = "设备名称")
    @Excel(name = "设备名称")
    private String name;
    /** 设备类型(1摄像头 2水位 3 雨量 4 水量 5流量 6公示牌--暂定 ) 7、瞬时流量 */
    @ApiModelProperty(value = "设备类型(1摄像头 2水位 3 雨量 4 水量 5流量 6公示牌--暂定 ) 7、瞬时流量")
    @Excel(name = "设备类型(1摄像头 2水位 3 雨量 4 水量 5流量 6公示牌--暂定 ) 7、瞬时流量")
    private String type;
    /** 坐标 */
    @ApiModelProperty(value = "坐标")
    @Excel(name = "坐标")
    private String coordinate;
    /** 位置类型（1河流 2 湖泊 3 水库 4淤地坝 5其他） */
    @ApiModelProperty(value = "位置类型（1河流 2 湖泊 3 水库 4淤地坝 5其他）")
    @Excel(name = "位置类型（1河流 2 湖泊 3 水库 4淤地坝 5其他）")
    private String locationType;
    /** 位置id */
    @ApiModelProperty(value = "位置id")
    @Excel(name = "位置id")
    private String locationId;
    /** 河湖泊名字 */
    @ApiModelProperty(value = "河湖泊名字")
    @Excel(name = "河湖泊名字")
    private String locationName;
    /** 设备详细位置 */
    @ApiModelProperty(value = "设备详细位置")
    @Excel(name = "设备详细位置")
    private String address;
    /** 设备编码（对接外部标识） */
    @ApiModelProperty(value = "设备编码（对接外部标识）")
    @Excel(name = "设备编码（对接外部标识）")
    private String code;
    /** 设备参数 */
    @ApiModelProperty(value = "设备参数")
    @Excel(name = "设备参数")
    private String parameter;
    /** 联系人 */
    @ApiModelProperty(value = "联系人")
    @Excel(name = "联系人")
    private String contacts;
    /** 联系电话 */
    @ApiModelProperty(value = "联系电话")
    @Excel(name = "联系电话")
    private String contactsPhone;
    /** 设备厂商 */
    @ApiModelProperty(value = "设备厂商")
    @Excel(name = "设备厂商")
    private String manufacturer;
    /** 乡镇/街道 */
    @ApiModelProperty(value = "乡镇/街道")
    @Excel(name = "乡镇/街道")
    private String town;
    /** 村 */
    @ApiModelProperty(value = "村")
    @Excel(name = "村")
    private String village;
    /** 负责人（河长用户id） */
    @ApiModelProperty(value = "负责人（河长用户id）")
    @Excel(name = "负责人（河长用户id）")
    private String riverLead;
    /** 融云token(视频通话使用) */
    @ApiModelProperty(value = "融云token(视频通话使用)")
    @Excel(name = "融云token(视频通话使用)")
    private String rytoken;
    /** 负责人手机号（用户手机号） */
    @ApiModelProperty(value = "负责人手机号（用户手机号）")
    @Excel(name = "负责人手机号（用户手机号）")
    private String personPhone;
    /** 在线状态（0 不在线  1在线） */
    @ApiModelProperty(value = "在线状态（0 不在线  1在线）")
    @Excel(name = "在线状态（0 不在线  1在线）")
    private String onlineStatus;
    /** 市镇级分类（1市级 2镇级） */
    @ApiModelProperty(value = "市镇级分类（1市级 2镇级）")
    @Excel(name = "市镇级分类（1市级 2镇级）")
    private String classification;
    /** 负责人姓名 */
    @ApiModelProperty(value = "负责人姓名")
    @Excel(name = "负责人姓名")
    private String riverLeadName;
    /** 摄像头设备类型（1枪机 2球机） */
    @ApiModelProperty(value = "摄像头设备类型（1枪机 2球机）")
    @Excel(name = "摄像头设备类型（1枪机 2球机）")
    private String cameraTypeName;
    /** 设备来源（0自建、1接入） */
    @ApiModelProperty(value = "设备来源（0自建、1接入）")
    @Excel(name = "设备来源（0自建、1接入）")
    private String cameraDistinguish;
    /** 地图显示状态（默认1显示  2隐藏） */
    @ApiModelProperty(value = "地图显示状态（默认1显示  2隐藏）")
    @Excel(name = "地图显示状态（默认1显示  2隐藏）")
    private String showStatus;
    /** 地图显示缩放级别 --字典 */
    @ApiModelProperty(value = "地图显示缩放级别 --字典")
    @Excel(name = "地图显示缩放级别 --字典")
    private String mapLevel;

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

    public void setSysOrgCode(String sysOrgCode){
        this.sysOrgCode = sysOrgCode;
    }

    public String getSysOrgCode(){
        return sysOrgCode;
    }


    public void setSort(Integer sort){
        this.sort = sort;
    }

    public Integer getSort(){
        return sort;
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


    public void setCoordinate(String coordinate){
        this.coordinate = coordinate;
    }

    public String getCoordinate(){
        return coordinate;
    }


    public void setLocationType(String locationType){
        this.locationType = locationType;
    }

    public String getLocationType(){
        return locationType;
    }


    public void setLocationId(String locationId){
        this.locationId = locationId;
    }

    public String getLocationId(){
        return locationId;
    }


    public void setLocationName(String locationName){
        this.locationName = locationName;
    }

    public String getLocationName(){
        return locationName;
    }


    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }


    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }


    public void setParameter(String parameter){
        this.parameter = parameter;
    }

    public String getParameter(){
        return parameter;
    }


    public void setContacts(String contacts){
        this.contacts = contacts;
    }

    public String getContacts(){
        return contacts;
    }


    public void setContactsPhone(String contactsPhone){
        this.contactsPhone = contactsPhone;
    }

    public String getContactsPhone(){
        return contactsPhone;
    }


    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }

    public String getManufacturer(){
        return manufacturer;
    }


    public void setTown(String town){
        this.town = town;
    }

    public String getTown(){
        return town;
    }


    public void setVillage(String village){
        this.village = village;
    }

    public String getVillage(){
        return village;
    }


    public void setRiverLead(String riverLead){
        this.riverLead = riverLead;
    }

    public String getRiverLead(){
        return riverLead;
    }


    public void setRytoken(String rytoken){
        this.rytoken = rytoken;
    }

    public String getRytoken(){
        return rytoken;
    }


    public void setPersonPhone(String personPhone){
        this.personPhone = personPhone;
    }

    public String getPersonPhone(){
        return personPhone;
    }


    public void setOnlineStatus(String onlineStatus){
        this.onlineStatus = onlineStatus;
    }

    public String getOnlineStatus(){
        return onlineStatus;
    }


    public void setClassification(String classification){
        this.classification = classification;
    }

    public String getClassification(){
        return classification;
    }


    public void setRiverLeadName(String riverLeadName){
        this.riverLeadName = riverLeadName;
    }

    public String getRiverLeadName(){
        return riverLeadName;
    }


    public void setCameraTypeName(String cameraTypeName){
        this.cameraTypeName = cameraTypeName;
    }

    public String getCameraTypeName(){
        return cameraTypeName;
    }


    public void setCameraDistinguish(String cameraDistinguish){
        this.cameraDistinguish = cameraDistinguish;
    }

    public String getCameraDistinguish(){
        return cameraDistinguish;
    }


    public void setShowStatus(String showStatus){
        this.showStatus = showStatus;
    }

    public String getShowStatus(){
        return showStatus;
    }


    public void setMapLevel(String mapLevel){
        this.mapLevel = mapLevel;
    }

    public String getMapLevel(){
        return mapLevel;
    }




    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("createBy",getCreateBy())
            .append("createTime",getCreateTime())
            .append("updateBy",getUpdateBy())
            .append("updateTime",getUpdateTime())
            .append("sysOrgCode",getSysOrgCode())
            .append("sort",getSort())
            .append("name",getName())
            .append("type",getType())
            .append("coordinate",getCoordinate())
            .append("locationType",getLocationType())
            .append("locationId",getLocationId())
            .append("locationName",getLocationName())
            .append("address",getAddress())
            .append("code",getCode())
            .append("parameter",getParameter())
            .append("contacts",getContacts())
            .append("contactsPhone",getContactsPhone())
            .append("manufacturer",getManufacturer())
            .append("town",getTown())
            .append("village",getVillage())
            .append("riverLead",getRiverLead())
            .append("rytoken",getRytoken())
            .append("personPhone",getPersonPhone())
            .append("onlineStatus",getOnlineStatus())
            .append("classification",getClassification())
            .append("riverLeadName",getRiverLeadName())
            .append("cameraTypeName",getCameraTypeName())
            .append("cameraDistinguish",getCameraDistinguish())
            .append("showStatus",getShowStatus())
            .append("mapLevel",getMapLevel())
            .toString();
    }
}
