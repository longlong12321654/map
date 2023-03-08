package com.hndist.cim.gisserver.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author FWY
 * @version V1.0.0
 * @ClassName: Feature
 * @Description: TODO
 * @date 2022/5/26 5:54 下午
 * @Copyright: http://www.hndist.com All rights reserved.
 * 注意：本内容仅限于河南数慧信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class Features implements Serializable {

    /**
     * @author FWY
     * @Description: Feature列表
     * @date 2022/5/27 5:51 下午
     * @Param
     * @return
     */
    private ArrayList<Feature> featureList = new ArrayList<Feature>();

    /**
     * @author FWY
     * @Description: 添加Feature
     * @date 2022/5/27 5:51 下午
     * @Param [feature]
     * @return void
     */
    public void addFeature(Feature feature){
        this.featureList.add(feature);
    }

    /**
     * @author FWY
     * @Description: 设置属性
     * @date 2022/5/30 2:09 下午
     * @Param [attributes]
     * @return void
     */
    public void setAttributes(HashMap<String,Object> attributes){
        if(featureList!=null){
            for (Feature feature:featureList){
                feature.setAttributes(attributes);
            }
        }
    }

    /**
     * @author FWY
     * @Description: 添加属性信息
     * @date 2022/5/30 2:51 下午
     * @Param [key, value]
     * @return void
     */
    public void addAttribute(String key,Object value){
        if(featureList!=null){
            for (Feature feature:featureList){
                feature.addAttribute(key,value);
            }
        }
    }

    public ArrayList<Feature> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(ArrayList<Feature> featureList) {
        this.featureList = featureList;
    }

}
