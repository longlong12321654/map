package com.hndist.cim.gisserver.entity;

import java.util.List;

/**
 * @author 河南数慧信息技术有限公司
 * @version V1.0.0
 * @ClassName: MapQuery
 * @Description: TODO
 * @date 2022/11/23 09:31
 * @Copyright: www.hndist.com All rights reserved.
 * 注意：本内容仅限于共享研发中心内部传阅，禁止外泄以及用于其他的商业目
 */
public class LayerQuery {

    //查询条件(Logical Operators:And、Or、Not;Comparison Operators:=、<>、<、>、<=、>=、%、range)
    private String where;

    //空间几何图形
    private String geometry;

    //几何类型(Point、Polyline、Polygon)
    private String geometryType;

    //空间关系(Equals、Disjoint、Touches、Within、Overlaps、Crosses、Intersects、Contains、DWithin、BBOX)
    private String spatialRel;

    //返回条目数
    private int count;

    // 分组
    private String groupby;

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getGeometryType() {
        return geometryType;
    }

    public void setGeometryType(String geometryType) {
        this.geometryType = geometryType;
    }

    public String getSpatialRel() {
        return spatialRel;
    }

    public void setSpatialRel(String spatialRel) {
        this.spatialRel = spatialRel;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGroupby() {
        return groupby;
    }

    public void setGroupby(String groupby) {
        this.groupby = groupby;
    }
}
