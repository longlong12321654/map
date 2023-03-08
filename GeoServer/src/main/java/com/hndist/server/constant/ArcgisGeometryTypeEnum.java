package com.hndist.server.constant;

/**
 * @author zy
 * @version 1.1
 * @className SysServiceengineEnum
 * @description 服务引擎枚举类
 * @date 2023/1/10 17:19
 **/
public enum ArcgisGeometryTypeEnum {
    /**
     * 属性
     */
    Point("esriGeometryPoint", "1"),
    Polyline("esriGeometryPolyline", "2"),
    Polygon("esriGeometryPolygon", "3");

    private String name;
    private String index;

    // 构造方法
    ArcgisGeometryTypeEnum(String name, String index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ArcgisGeometryTypeEnum c : ArcgisGeometryTypeEnum.values()) {
            if (c.getIndex().equals(index)) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }}
