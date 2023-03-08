package com.hndist.cim.gisserver.entity;

/**
 * Shape文件将要使用的类型
 *
 * @author LiuXing
 * @date 2022-01-11
 */

public enum GeomTitleTypeEnum {
    /**
     * 点
     */
    POINT("Point"),
    /**
     * 线
     */
    LINESTRING("LineString"),
    /**
     * 面
     */
    POLYGON("Polygon"),
    /**
     * 复杂线
     */
    MULTILINESTRING("MultiLineString"),
    /**
     * 复杂面
     */
    MULTIPOLYGON("MultiPolygon");

    public String value;

    GeomTitleTypeEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
