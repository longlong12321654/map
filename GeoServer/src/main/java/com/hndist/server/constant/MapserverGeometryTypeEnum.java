package com.hndist.server.constant;

/**
 * @author zy
 * @version 1.1
 * @className SysServiceengineEnum
 * @description 服务引擎枚举类
 * @date 2023/1/10 17:19
 **/
public enum MapserverGeometryTypeEnum {
    /**
     * 属性
     */
    Point("Point", "1"),
    Polyline("Polyline", "2"),
    Polygon("Polygon", "3");

    private String name;
    private String index;

    // 构造方法
    MapserverGeometryTypeEnum(String name, String index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (MapserverGeometryTypeEnum c : MapserverGeometryTypeEnum.values()) {
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
