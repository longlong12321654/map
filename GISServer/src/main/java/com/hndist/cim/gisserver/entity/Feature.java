package com.hndist.cim.gisserver.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author FWY
 * @version V1.0.0
 * @ClassName: Feature
 * @Description: TODO
 * @date 2022/5/26 5:56 下午
 * @Copyright: http://www.hndist.com All rights reserved.
 * 注意：本内容仅限于河南数慧信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class Feature implements Serializable {

    //名称
    private String name;

    //定义ArrayList字段
    private ArrayList<Field> fields;

    //定义HashMap字段
    private HashMap<String,Field> fieldHashMap;

    //空间类型
    private SpatialReference spatialReference;

    //经纬度信息
    private ArrayList[] rings = new ArrayList[1];

    //Geometry
    private Geometry geometry = new Geometry();

    //属性信息
    private HashMap<String,Object> attributes = new HashMap<String,Object>();

    /**
     * @ClassName: Feature
     * @Description: 添加属性
     * @author FWY
     * @date 2022/5/30 11:08 上午
     * @version V1.0.0
     * @Copyright:  http://www.hndist.com All rights reserved.
     * 注意：本内容仅限于河南数慧信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目
     */
    public void addAttribute(String key,Object value){
        attributes.put(key,value);
    }

    /**
     * @author FWY
     * @Description: 添加点信息
     * @date 2022/5/30 4:01 下午
     * @Param [x, y]
     * @return void
     */
    public void addPoint(Double x,Double y){
        if(rings[0]==null){
            rings[0] = new ArrayList();
        }
        //创建点列表
        ArrayList<Double> tmpPoint = new ArrayList<Double>();
        tmpPoint.add(x);
        tmpPoint.add(y);
        //添加到rings
        rings[0].add(tmpPoint);
    }

    /**
     * @author FWY
     * @Description: 获取属性信息
     * @date 2022/5/30 3:02 下午
     * @Param [key]
     * @return java.lang.Object
     */
    public Object getAttribute(String key){
        return attributes.get(key);
    }

    public Geometry getGeometry() {
        geometry.setRings(getRings());
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList[] getRings() {
        return rings;
    }

    public void setRings(ArrayList[] rings) {
        this.rings = rings;
    }

    public void addRings(ArrayList rings) {
        this.rings[0] = rings;
    }

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public SpatialReference getSpatialReference() {
//        if(spatialReference==null){
//            spatialReference = new SpatialReference();
//            spatialReference.setLatestWkid(4525);
//            spatialReference.setWkid(4525);
//        }
        return spatialReference;
    }

    public HashMap<String, Field> getFieldHashMap() {
        //判断字段是否为空
        if(fieldHashMap==null && fields != null){
            fieldHashMap = new HashMap<String,Field>();
            for(Field field:fields){
                fieldHashMap.put(field.getName(),field);
            }
        }
        return fieldHashMap;
    }

    public Field getField(String name){
        if(fieldHashMap==null){
            fieldHashMap = getFieldHashMap();
        }
        return fieldHashMap.get(name);
    }

    public void setFieldHashMap(HashMap<String, Field> fieldHashMap) {
        this.fieldHashMap = fieldHashMap;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public void setSpatialReference(SpatialReference spatialReference) {
        this.spatialReference = spatialReference;
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
    }
}
