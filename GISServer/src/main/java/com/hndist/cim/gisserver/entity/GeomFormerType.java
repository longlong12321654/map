package com.hndist.cim.gisserver.entity;

/**
 * 原始类型
 * @author LiuXing
 * @date 2022-01-11
 */

public enum GeomFormerType {
    /**
     * 点
     */
    POINT("POINT"),
    /**
     * 线
     */
    LINESTRING("LINESTRING"),
    /**
     * 面
     */
    POLYGON("POLYGON"),
    /**
     * 复杂线
     */
    MULTILINESTRING("MULTILINESTRING"),
    /**
     * 复杂面
     */
    MULTIPOLYGON("MULTIPOLYGON");

    public String value;

    GeomFormerType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}

