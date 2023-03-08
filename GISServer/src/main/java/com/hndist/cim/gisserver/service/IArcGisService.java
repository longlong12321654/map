package com.hndist.cim.gisserver.service;


import com.hndist.cim.gisserver.entity.Features;
import com.hndist.cim.gisserver.entity.Layers;
import com.hndist.cim.gisserver.entity.Message;

/**
 * @ClassName: IArcGisService
 * @Description: ArcGisService
 * @author FWY
 * @date 2022/6/13 2:49 下午
 * @version V1.0.0
 * @Copyright:  http://www.hndist.com All rights reserved.
 * 注意：本内容仅限于河南数慧信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface IArcGisService{

    /**
     * @author FWY
     * @Description: 查询Features
     * @date 2022/6/13 2:39 下午
     * @Param [layerUrl, where]
     * @return com.hndist.entity.common.AjaxResult
     */
    public Message queryFeatures(String layerUrl, String where);
    
    /**
     * @author FWY
     * @Description: 获取图层
     * @date 2022/6/16 1:51 下午
     * @Param [layerName, isAsyn]
     * @return com.hndist.entity.common.AjaxResult
     */
    public Layers getLayers(String layerUrl, boolean isAsyn);

    /**
     * @author FWY
     * @Description: 解析图层数据
     * @date 2022/6/13 3:20 下午
     * @Param [layerName, layerData]
     * @return com.hndist.entity.common.AjaxResult
     */
    public Features parseLayerData(String layerUrl, String layerData);
    
    /**
     * @author FWY
     * @Description: 添加Features
     * @date 2022/6/13 2:39 下午
     * @Param [layerUrl, layerData]
     * @return com.hndist.entity.common.AjaxResult
     */
    public Message addFeatures(String layerUrl, Features features) ;

    /**
     * @author FWY
     * @Description: 更新Features
     * @date 2022/6/13 2:39 下午
     * @Param [layerUrl, layerData]
     * @return com.hndist.entity.common.AjaxResult
     */
    public Message updateFeatures(String layerUrl, Features features);

    /**
     * @author FWY
     * @Description: 删除Features
     * @date 2022/6/13 2:39 下午
     * @Param [layerUrl, where]
     * @return com.hndist.entity.common.AjaxResult
     */
    public Message deleteFeatures(String layerUrl, String where);

    /**
     * @author FWY
     * @Description: 更新历史Features
     * @date 2022/6/13 2:40 下午
     * @Param [layerUrl, layerData, historyLayerName]
     * @return com.hndist.entity.common.AjaxResult
     */
    public Message updateHistoryFeatures(String layerUrl, Features features, String historyLayerName);
}

