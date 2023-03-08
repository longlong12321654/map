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
 * @Title: CimDatasourceMeta
 * @Description: 元数据管理
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@ApiModel(value="元数据管理", description = "元数据管理")
public class CimDatasourceMeta extends BaseEntity
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
    /** 数据源id */
    @ApiModelProperty(value = "数据源id")
    @Excel(name = "数据源id")
    private String datasourceId;
    /** 数据一级类型（矢量，影像，高程，业务) */
    @ApiModelProperty(value = "数据一级类型（矢量，影像，高程，业务)")
    @Excel(name = "数据一级类型（矢量，影像，高程，业务)")
    private String dataType;
    /** 数据二级类型（矢量：poi，建筑；影像：高分.. ) */
    @ApiModelProperty(value = "数据二级类型（矢量：poi，建筑；影像：高分.. )")
    @Excel(name = "数据二级类型（矢量：poi，建筑；影像：高分.. )")
    private String dataSubType;
    /** 数据采集时间 */
    @ApiModelProperty(value = "数据采集时间")
    @Excel(name = "数据采集时间")
    private Date productTime;
    /** 摘要 */
    @ApiModelProperty(value = "摘要")
    @Excel(name = "摘要")
    private String summary;
    /** 关键字 */
    @ApiModelProperty(value = "关键字")
    @Excel(name = "关键字")
    private String keywords;
    /** 数据质量说明 */
    @ApiModelProperty(value = "数据质量说明")
    @Excel(name = "数据质量说明")
    private String dataQuality;
    /** 元数据标准名称 */
    @ApiModelProperty(value = "元数据标准名称")
    @Excel(name = "元数据标准名称")
    private String standardName;
    /** 文件标识符 */
    @ApiModelProperty(value = "文件标识符")
    @Excel(name = "文件标识符")
    private String fileIdentifier;
    /** 数据志说明 */
    @ApiModelProperty(value = "数据志说明")
    @Excel(name = "数据志说明")
    private String dataLog;
    /** 数据集语种 */
    @ApiModelProperty(value = "数据集语种")
    @Excel(name = "数据集语种")
    private String datasetLang;
    /** 专题类型 */
    @ApiModelProperty(value = "专题类型")
    @Excel(name = "专题类型")
    private String projectType;
    /** 覆盖范围 */
    @ApiModelProperty(value = "覆盖范围")
    @Excel(name = "覆盖范围")
    private String extent;
    /** 空间表示类型 */
    @ApiModelProperty(value = "空间表示类型")
    @Excel(name = "空间表示类型")
    private String spaceType;
    /** 空间分辨率 */
    @ApiModelProperty(value = "空间分辨率")
    @Excel(name = "空间分辨率")
    private String spatialResolution;
    /** 卫星 */
    @ApiModelProperty(value = "卫星")
    @Excel(name = "卫星")
    private String satellite;
    /** 仪器（传感器） */
    @ApiModelProperty(value = "仪器（传感器）")
    @Excel(name = "仪器（传感器）")
    private String sensor;
    /** 分幅标识 */
    @ApiModelProperty(value = "分幅标识")
    @Excel(name = "分幅标识")
    private String imagOrbid;
    /** 参照系名称 */
    @ApiModelProperty(value = "参照系名称")
    @Excel(name = "参照系名称")
    private String refSystem;
    /** 投影坐标 */
    @ApiModelProperty(value = "投影坐标")
    @Excel(name = "投影坐标")
    private String projection;
    /** 椭球体 */
    @ApiModelProperty(value = "椭球体")
    @Excel(name = "椭球体")
    private String ellipsoid;
    /** 垂向基准名称代码 */
    @ApiModelProperty(value = "垂向基准名称代码")
    @Excel(name = "垂向基准名称代码")
    private String vetDatum;
    /** 垂向基准名称 */
    @ApiModelProperty(value = "垂向基准名称")
    @Excel(name = "垂向基准名称")
    private String vetName;
    /** 图层名称 */
    @ApiModelProperty(value = "图层名称")
    @Excel(name = "图层名称")
    private String layerName;
    /** 属性列表 */
    @ApiModelProperty(value = "属性列表")
    @Excel(name = "属性列表")
    private String attrDesc;
    /** 云斑覆盖比例 */
    @ApiModelProperty(value = "云斑覆盖比例")
    @Excel(name = "云斑覆盖比例")
    private String cloudCov;
    /** 表达方式 */
    @ApiModelProperty(value = "表达方式")
    @Excel(name = "表达方式")
    private String expression;
    /** 栅格/影像内容描述 */
    @ApiModelProperty(value = "栅格/影像内容描述")
    @Excel(name = "栅格/影像内容描述")
    private String contentDesc;
    /** 联系分发方 */
    @ApiModelProperty(value = "联系分发方")
    @Excel(name = "联系分发方")
    private String distorCont;
    /** 格式名称 */
    @ApiModelProperty(value = "格式名称")
    @Excel(name = "格式名称")
    private String formatName;
    /** 格式版本 */
    @ApiModelProperty(value = "格式版本")
    @Excel(name = "格式版本")
    private String formatVer;
    /** 链接地址 */
    @ApiModelProperty(value = "链接地址")
    @Excel(name = "链接地址")
    private String linkage;
    /** 用途限制 */
    @ApiModelProperty(value = "用途限制")
    @Excel(name = "用途限制")
    private String uselimit;
    /** 访问限制 */
    @ApiModelProperty(value = "访问限制")
    @Excel(name = "访问限制")
    private String accessConsts;
    /** 使用限制 */
    @ApiModelProperty(value = "使用限制")
    @Excel(name = "使用限制")
    private String useConsts;
    /** 安全限制分级 */
    @ApiModelProperty(value = "安全限制分级")
    @Excel(name = "安全限制分级")
    private String safetyConsts;
    /** 引用信息名称 */
    @ApiModelProperty(value = "引用信息名称")
    @Excel(name = "引用信息名称")
    private String resTitle;
    /** 引用信息日期 */
    @ApiModelProperty(value = "引用信息日期")
    @Excel(name = "引用信息日期")
    private Date resDate;
    /** 引用信息版本 */
    @ApiModelProperty(value = "引用信息版本")
    @Excel(name = "引用信息版本")
    private String resEd;
    /** 时间范围时间 */
    @ApiModelProperty(value = "时间范围时间")
    @Excel(name = "时间范围时间")
    private String timeExtent;
    /** 起始时间 */
    @ApiModelProperty(value = "起始时间")
    @Excel(name = "起始时间")
    private Date beginning;
    /** 终止时间 */
    @ApiModelProperty(value = "终止时间")
    @Excel(name = "终止时间")
    private Date ending;
    /** 负责人名 */
    @ApiModelProperty(value = "负责人名")
    @Excel(name = "负责人名")
    private String rpindName;
    /** 负责单位名 */
    @ApiModelProperty(value = "负责单位名")
    @Excel(name = "负责单位名")
    private String rporgName;
    /** 职务 */
    @ApiModelProperty(value = "职务")
    @Excel(name = "职务")
    private String rpposName;
    /** 职责 */
    @ApiModelProperty(value = "职责")
    @Excel(name = "职责")
    private String role;
    /** 详细地址 */
    @ApiModelProperty(value = "详细地址")
    @Excel(name = "详细地址")
    private String address;
    /** 城市 */
    @ApiModelProperty(value = "城市")
    @Excel(name = "城市")
    private String city;
    /** 行政区 */
    @ApiModelProperty(value = "行政区")
    @Excel(name = "行政区")
    private String adminArea;
    /** 邮政编码 */
    @ApiModelProperty(value = "邮政编码")
    @Excel(name = "邮政编码")
    private String postalCode;
    /** 国家 */
    @ApiModelProperty(value = "国家")
    @Excel(name = "国家")
    private String country;
    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间")
    private Date createTime;
    /** 创建人 */
    @ApiModelProperty(value = "创建人")
    @Excel(name = "创建人")
    private String createBy;
    /** 修改时间 */
    @ApiModelProperty(value = "修改时间")
    @Excel(name = "修改时间")
    private Date updateTime;
    /** 修改人 */
    @ApiModelProperty(value = "修改人")
    @Excel(name = "修改人")
    private String updateBy;
    /** 权限 */
    @ApiModelProperty(value = "权限")
    @Excel(name = "权限")
    private String permi;
    @ApiModelProperty(value = "数据获取时间")
    @Excel(name = "数据获取时间")
    private Date gettime;
    @ApiModelProperty(value = "联系电话")
    @Excel(name = "联系电话")
    private String linktel;
    @ApiModelProperty(value = "乡镇")
    @Excel(name = "乡镇")
    private String township;
    @ApiModelProperty(value = "生产单位信息-负责人名称")
    @Excel(name = "生产单位信息-负责人名称")
    private String pchargeName;
    @ApiModelProperty(value = "生产单位信息-负责单位名称")
    @Excel(name = "生产单位信息-负责单位名称")
    private String prunitName;
    @ApiModelProperty(value = "生产单位信息-职务")
    @Excel(name = "生产单位信息-职务")
    private String ppostName;
    @ApiModelProperty(value = "生产单位信息-联系电话")
    @Excel(name = "生产单位信息-联系电话")
    private String ptellink;
    @ApiModelProperty(value = "父级编号")
    @Excel(name = "父级编号")
    private String pid;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }


    public void setDatasourceId(String datasourceId){
        this.datasourceId = datasourceId;
    }

    public String getDatasourceId(){
        return datasourceId;
    }


    public void setDataType(String dataType){
        this.dataType = dataType;
    }

    public String getDataType(){
        return dataType;
    }


    public void setDataSubType(String dataSubType){
        this.dataSubType = dataSubType;
    }

    public String getDataSubType(){
        return dataSubType;
    }


    public void setProductTime(Date productTime){
        this.productTime = productTime;
    }

    public Date getProductTime(){
        return productTime;
    }

    public String getPProductTime(){
        if(this.productTime!=null){
            return dateFormat.format(this.productTime);
        }
        return "";
    }

    public void setSummary(String summary){
        this.summary = summary;
    }

    public String getSummary(){
        return summary;
    }


    public void setKeywords(String keywords){
        this.keywords = keywords;
    }

    public String getKeywords(){
        return keywords;
    }


    public void setDataQuality(String dataQuality){
        this.dataQuality = dataQuality;
    }

    public String getDataQuality(){
        return dataQuality;
    }


    public void setStandardName(String standardName){
        this.standardName = standardName;
    }

    public String getStandardName(){
        return standardName;
    }


    public void setFileIdentifier(String fileIdentifier){
        this.fileIdentifier = fileIdentifier;
    }

    public String getFileIdentifier(){
        return fileIdentifier;
    }


    public void setDataLog(String dataLog){
        this.dataLog = dataLog;
    }

    public String getDataLog(){
        return dataLog;
    }


    public void setDatasetLang(String datasetLang){
        this.datasetLang = datasetLang;
    }

    public String getDatasetLang(){
        return datasetLang;
    }


    public void setProjectType(String projectType){
        this.projectType = projectType;
    }

    public String getProjectType(){
        return projectType;
    }


    public void setExtent(String extent){
        this.extent = extent;
    }

    public String getExtent(){
        return extent;
    }


    public void setSpaceType(String spaceType){
        this.spaceType = spaceType;
    }

    public String getSpaceType(){
        return spaceType;
    }


    public void setSpatialResolution(String spatialResolution){
        this.spatialResolution = spatialResolution;
    }

    public String getSpatialResolution(){
        return spatialResolution;
    }


    public void setSatellite(String satellite){
        this.satellite = satellite;
    }

    public String getSatellite(){
        return satellite;
    }


    public void setSensor(String sensor){
        this.sensor = sensor;
    }

    public String getSensor(){
        return sensor;
    }


    public void setImagOrbid(String imagOrbid){
        this.imagOrbid = imagOrbid;
    }

    public String getImagOrbid(){
        return imagOrbid;
    }


    public void setRefSystem(String refSystem){
        this.refSystem = refSystem;
    }

    public String getRefSystem(){
        return refSystem;
    }


    public void setProjection(String projection){
        this.projection = projection;
    }

    public String getProjection(){
        return projection;
    }


    public void setEllipsoid(String ellipsoid){
        this.ellipsoid = ellipsoid;
    }

    public String getEllipsoid(){
        return ellipsoid;
    }


    public void setVetDatum(String vetDatum){
        this.vetDatum = vetDatum;
    }

    public String getVetDatum(){
        return vetDatum;
    }


    public void setVetName(String vetName){
        this.vetName = vetName;
    }

    public String getVetName(){
        return vetName;
    }


    public void setLayerName(String layerName){
        this.layerName = layerName;
    }

    public String getLayerName(){
        return layerName;
    }


    public void setAttrDesc(String attrDesc){
        this.attrDesc = attrDesc;
    }

    public String getAttrDesc(){
        return attrDesc;
    }


    public void setCloudCov(String cloudCov){
        this.cloudCov = cloudCov;
    }

    public String getCloudCov(){
        return cloudCov;
    }


    public void setExpression(String expression){
        this.expression = expression;
    }

    public String getExpression(){
        return expression;
    }


    public void setContentDesc(String contentDesc){
        this.contentDesc = contentDesc;
    }

    public String getContentDesc(){
        return contentDesc;
    }


    public void setDistorCont(String distorCont){
        this.distorCont = distorCont;
    }

    public String getDistorCont(){
        return distorCont;
    }


    public void setFormatName(String formatName){
        this.formatName = formatName;
    }

    public String getFormatName(){
        return formatName;
    }


    public void setFormatVer(String formatVer){
        this.formatVer = formatVer;
    }

    public String getFormatVer(){
        return formatVer;
    }


    public void setLinkage(String linkage){
        this.linkage = linkage;
    }

    public String getLinkage(){
        return linkage;
    }


    public void setUselimit(String uselimit){
        this.uselimit = uselimit;
    }

    public String getUselimit(){
        return uselimit;
    }


    public void setAccessConsts(String accessConsts){
        this.accessConsts = accessConsts;
    }

    public String getAccessConsts(){
        return accessConsts;
    }


    public void setUseConsts(String useConsts){
        this.useConsts = useConsts;
    }

    public String getUseConsts(){
        return useConsts;
    }


    public void setSafetyConsts(String safetyConsts){
        this.safetyConsts = safetyConsts;
    }

    public String getSafetyConsts(){
        return safetyConsts;
    }


    public void setResTitle(String resTitle){
        this.resTitle = resTitle;
    }

    public String getResTitle(){
        return resTitle;
    }


    public void setResDate(Date resDate){
        this.resDate = resDate;
    }

    public Date getResDate(){
        return resDate;
    }

    public String getPResDate(){
        if(this.resDate!=null){
            return dateFormat.format(this.resDate);
        }
        return "";
    }

    public void setResEd(String resEd){
        this.resEd = resEd;
    }

    public String getResEd(){
        return resEd;
    }


    public void setTimeExtent(String timeExtent){
        this.timeExtent = timeExtent;
    }

    public String getTimeExtent(){
        return timeExtent;
    }


    public void setBeginning(Date beginning){
        this.beginning = beginning;
    }

    public Date getBeginning(){
        return beginning;
    }

    public String getPBeginning(){
        if(this.beginning!=null){
            return dateFormat.format(this.beginning);
        }
        return "";
    }

    public void setEnding(Date ending){
        this.ending = ending;
    }

    public Date getEnding(){
        return ending;
    }

    public String getPEnding(){
        if(this.ending!=null){
            return dateFormat.format(this.ending);
        }
        return "";
    }

    public void setRpindName(String rpindName){
        this.rpindName = rpindName;
    }

    public String getRpindName(){
        return rpindName;
    }


    public void setRporgName(String rporgName){
        this.rporgName = rporgName;
    }

    public String getRporgName(){
        return rporgName;
    }


    public void setRpposName(String rpposName){
        this.rpposName = rpposName;
    }

    public String getRpposName(){
        return rpposName;
    }


    public void setRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }


    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }


    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return city;
    }


    public void setAdminArea(String adminArea){
        this.adminArea = adminArea;
    }

    public String getAdminArea(){
        return adminArea;
    }


    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    public String getPostalCode(){
        return postalCode;
    }


    public void setCountry(String country){
        this.country = country;
    }

    public String getCountry(){
        return country;
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

    public void setCreateBy(String createBy){
        this.createBy = createBy;
    }

    public String getCreateBy(){
        return createBy;
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

    public void setUpdateBy(String updateBy){
        this.updateBy = updateBy;
    }

    public String getUpdateBy(){
        return updateBy;
    }


    public void setPermi(String permi){
        this.permi = permi;
    }

    public String getPermi(){
        return permi;
    }


    public Date getGettime() {
        return gettime;
    }

    public void setGettime(Date gettime) {
        this.gettime = gettime;
    }

    public String getLinktel() {
        return linktel;
    }

    public void setLinktel(String linktel) {
        this.linktel = linktel;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public String getPchargeName() {
        return pchargeName;
    }

    public void setPchargeName(String pchargeName) {
        this.pchargeName = pchargeName;
    }

    public String getPrunitName() {
        return prunitName;
    }

    public void setPrunitName(String prunitName) {
        this.prunitName = prunitName;
    }

    public String getPpostName() {
        return ppostName;
    }

    public void setPpostName(String ppostName) {
        this.ppostName = ppostName;
    }

    public String getPtellink() {
        return ptellink;
    }

    public void setPtellink(String ptellink) {
        this.ptellink = ptellink;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("datasourceId",getDatasourceId())
            .append("dataType",getDataType())
            .append("dataSubType",getDataSubType())
            .append("productTime",getProductTime())
            .append("summary",getSummary())
            .append("keywords",getKeywords())
            .append("dataQuality",getDataQuality())
            .append("standardName",getStandardName())
            .append("fileIdentifier",getFileIdentifier())
            .append("dataLog",getDataLog())
            .append("datasetLang",getDatasetLang())
            .append("projectType",getProjectType())
            .append("extent",getExtent())
            .append("spaceType",getSpaceType())
            .append("spatialResolution",getSpatialResolution())
            .append("satellite",getSatellite())
            .append("sensor",getSensor())
            .append("imagOrbid",getImagOrbid())
            .append("refSystem",getRefSystem())
            .append("projection",getProjection())
            .append("ellipsoid",getEllipsoid())
            .append("vetDatum",getVetDatum())
            .append("vetName",getVetName())
            .append("layerName",getLayerName())
            .append("attrDesc",getAttrDesc())
            .append("cloudCov",getCloudCov())
            .append("expression",getExpression())
            .append("contentDesc",getContentDesc())
            .append("distorCont",getDistorCont())
            .append("formatName",getFormatName())
            .append("formatVer",getFormatVer())
            .append("linkage",getLinkage())
            .append("uselimit",getUselimit())
            .append("accessConsts",getAccessConsts())
            .append("useConsts",getUseConsts())
            .append("safetyConsts",getSafetyConsts())
            .append("resTitle",getResTitle())
            .append("resDate",getResDate())
            .append("resEd",getResEd())
            .append("timeExtent",getTimeExtent())
            .append("beginning",getBeginning())
            .append("ending",getEnding())
            .append("rpindName",getRpindName())
            .append("rporgName",getRporgName())
            .append("rpposName",getRpposName())
            .append("role",getRole())
            .append("address",getAddress())
            .append("city",getCity())
            .append("adminArea",getAdminArea())
            .append("postalCode",getPostalCode())
            .append("country",getCountry())
            .append("createTime",getCreateTime())
            .append("createBy",getCreateBy())
            .append("updateTime",getUpdateTime())
            .append("updateBy",getUpdateBy())
            .append("permi",getPermi())
            .toString();
    }
}
