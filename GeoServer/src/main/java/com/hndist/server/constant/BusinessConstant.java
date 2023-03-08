package com.hndist.server.constant;

/**
 * @author ying
 * @version 1.1
 * @className BusinessConstant
 * @description 通用常量
 * @date 2022/2/18 9:59
 **/
public interface BusinessConstant {
    /**
     * 返回成功码
     */
    Integer SUCCESS = 200;

    /**
     * 传输请求协议类型
     */
    String TRANSPORT_PROTOCOL = "http://";

    /**
     * 冒号
     */
    String COLON = ":";

    /**
     * 解析arcGis-操作类型
     */
    String ARC_GIS_STATISTICS = "statistics";

    /**
     * arcgis静态地址
     */
    String ARCGIS_STATIC_PATH = "/arcgis/rest/services";

    /**
     * 缩略图地址
     */
    String IMG_URL = "/server/shrest/imgServer/";

    /**arcGis 服务类型*/
    String MAP_SERVER = "MapServer";
    String TOOL_SERVER = "GeometryServer,GPServer,SearchServer";

    /*arcgis 几何类型*/
    String ESRI_GEOMETRY_POINT = "esriGeometryPoint";
    String ESRI_GEOMETRY_POLYLINE = "esriGeometryPolyline";
    String ESRI_GEOMETRY_POLYGON = "esriGeometryPolygon";
}
