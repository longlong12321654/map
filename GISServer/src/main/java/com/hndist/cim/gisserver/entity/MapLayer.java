package com.hndist.cim.gisserver.entity;

import com.hndist.cim.gisserver.utils.StringUtils;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//
import java.util.ArrayList;
import java.util.List;

/**
 * @author 河南数慧信息技术有限公司
 * @version V1.0.0
 * @ClassName: MapLayer
 * @Description: 地图图层
 * @date 2022/11/16 11:00
 * @Copyright: www.hndist.com All rights reserved.
 * 注意：本内容仅限于共享研发中心内部传阅，禁止外泄以及用于其他的商业目
 */
//@ApiModel(value = "地图图层")
public class MapLayer {

    //支持的文件后缀
    private final String fileSuffix[] = {".shp",".tif",".glb",".gltf"};

    //图层编号
//    @ApiModelProperty(name = "name",value = "图层编号",required = false,dataType = "String")
    private String id;

    //图层名称
//    @ApiModelProperty(name = "label",value = "图层名称:兼容现有前端功能，下个版本处理",required = false,dataType = "String")
    private String label;

    //图层名称
//    @ApiModelProperty(name = "mapName",value = "图层名称:兼容现有前端功能，下个版本处理",required = false,dataType = "String")
    private String mapName;

    //图层名称
//    @ApiModelProperty(name = "name",value = "图层名称",required = false,dataType = "String")
    private String name;

    //空间参考
//    @ApiModelProperty(name = "spatialReference",value = "空间参考",required = false,dataType = "String")
    private String spatialReference;

    //服务引擎
//    @ApiModelProperty(name = "serviceEngine",value = "服务引擎:amap、osm、tianditu、hndist server、3dtiles、glb、gltf、arcgis server",required = true,dataType = "String")
    private String serviceEngine;

    //服务地址
//    @ApiModelProperty(name = "serviceURL",value = "服务地址",required = false,dataType = "String")
    private String serviceURL;

    //文件路径
//    @ApiModelProperty(name = "path",value = "文件路径",required = false,dataType = "String")
    private String path;

    //绝对路径
//    @ApiModelProperty(name = "absolutePath",value = "绝对路径",required = false,dataType = "String")
    private String  absolutePath;

    //文件类型
//    @ApiModelProperty(name = "type",value = "文件类型",required = false,dataType = "String")
    private String type;

    //空间类型
//    @ApiModelProperty(name = "spaceType",value = "空间类型",required = false,dataType = "String")
    private String spaceType;

    //选中属性
//    @ApiModelProperty(name = "select",value = "前端使用：选中属性",required = false,dataType = "String")
    private boolean select;

    //描述
//    @ApiModelProperty(name = "context",value = "描述",required = false,dataType = "String")
    private String context;

    //子图层
//    @ApiModelProperty(name = "children",value = "子图层列表",required = false,dataType = "List<MapLayer>")
    private List<MapLayer> children;

    //符号
    private LayerSymbol symbol;

    // 符号系统
    private SymbolSystem symbolSystem;
    // 标注
    private Tagging tagging;

    //是否是切片
    private boolean singleFusedMapCache;

    //地图范围
    private Extent extent;

    //字段信息
    private List<Field> fields;

    // 数据源编码
    private String encoding;

    /**
     * 高度
     */
    private Double height;
    /**
     * 版本
     */
    private String version;
    /**
     * 盒子
     */
    private Double[] box;
    /**
     * 几何字段
     */
    private String geom;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return this.getName();
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        if(this.name!=null && fileSuffix!=null){
            for(String suffix:fileSuffix){
                this.name = this.name.replaceAll(suffix,"");
            }
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMapName() {
        return this.getName();
    }

    public String getSpatialReference() {
        return spatialReference;
    }

    public void setSpatialReference(String spatialReference) {
        this.spatialReference = spatialReference;
    }

    public String getServiceEngine() {
        return serviceEngine;
    }

    public void setServiceEngine(String serviceEngine) {
        this.serviceEngine = serviceEngine;
    }

    public String getServiceURL() {
        if(serviceURL==null && this.serviceEngine!=null && "3dtiles、glb、gltf".indexOf(this.serviceEngine)>-1){
           return "/cim/rest/services/"+this.getName()+"/3DServer?isPublishing=false";
//        } else if (serviceURL==null && this.serviceEngine!=null && "iot".indexOf(this.serviceEngine)>-1) {
//            return "/cim/rest/services/"+this.getName()+"/IotServer?isPublishing=false";
        }
        return serviceURL;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<MapLayer> getChildren() {
        //判断如果是二维地图服务且children为空
        if(children==null && "hndist server".equalsIgnoreCase(serviceEngine)){
            //创建子图层（仅包含符号）
            children = new ArrayList<>();
            //创建临时图层对象
            MapLayer tmpLayer = new MapLayer();
            //设置Symbol
            tmpLayer.setSymbol(this.getSymbol());
            //添加到子图层中
            children.add(tmpLayer);
        }
        return children;
    }

    public void setChildren(List<MapLayer> children) {
        this.children = children;
    }

    public LayerSymbol getSymbol() {
        if(symbol==null){
            this.symbol = new LayerSymbol();
        }
        return symbol;
    }

    public void setSymbol(LayerSymbol symbol) {
        this.symbol = symbol;
    }

    public SymbolSystem getSymbolSystem() {
        if (symbolSystem == null) {
            symbolSystem = new SymbolSystem();
        }
        return symbolSystem;
    }

    public void setSymbolSystem(SymbolSystem symbolSystem) {
        this.symbolSystem = symbolSystem;
    }

    public String getSpaceType() {
//        //转换空间数据类型
//        if("hndist server".equalsIgnoreCase(serviceEngine) && this.type!=null){
//            if(this.type.equalsIgnoreCase("MultiPolygon")){
//                this.spaceType = "POLYGON";
//            }else if(this.type.equalsIgnoreCase("MultiLineString")){
//                this.spaceType = "LINE";
//            }else if(this.type.equalsIgnoreCase("Point")){
//                this.spaceType = "POINT";
//            }else{
//                this.spaceType = "RASTER";
//            }
//        }
        return spaceType;
    }


    public void setSpaceType(String spaceType) {
        this.spaceType = spaceType;
    }

    public Tagging getTagging() {
        return tagging;
    }

    public void setTagging(Tagging tagging) {
        this.tagging = tagging;
    }

    public Extent getExtent() {
        return extent;
    }

    public void setExtent(Extent extent) {
        this.extent = extent;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Double[] getBox() {
        return box;
    }

    public void setBox(Double[] box) {
        this.box = box;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }
}
