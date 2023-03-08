package com.hndist.cim.gisserver.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hndist.cim.gisserver.entity.*;
import com.hndist.cim.gisserver.service.IArcGisService;
import com.hndist.cim.gisserver.utils.ArcGisUtil;
import com.hndist.cim.gisserver.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ArcGisService
 * @Description: ArcGisServiceImpl
 * @author FWY
 * @date 2022/6/13 2:49 下午
 * @version V1.0.0
 * @Copyright:  http://www.hndist.com All rights reserved.
 * 注意：本内容仅限于河南数慧信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class ArcGisServiceImpl implements IArcGisService {

    @Override
    public Features parseLayerData(String layerUrl, String layerData) {
        //创建Features对象
        Features features = new Features();
        //获取属性对象
        Feature tmpFeatureAttr = ArcGisUtil.getFeature(layerUrl);
        //定义临时属性
        Feature tmpFeature = null;

        //解析拿到的图层数据
        JSONArray jsonArray = JSONObject.parseArray(layerData);
        //遍历图层
        for (int i = 0; i < jsonArray.size(); i++) {
            //定义临时Feature
            tmpFeature = new Feature();
            features.addFeature(tmpFeature);

            //获取JSONObject
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            //遍历属性及空间对象
            for (Map.Entry<String, Object> stringObjectEntry : jsonObject.entrySet()) {
                //获取key与value
                String key = stringObjectEntry.getKey().toLowerCase();
                Object value = stringObjectEntry.getValue();
                //判断是否是空间数据
                if (key.equalsIgnoreCase("SHAPE")) {
                    //获取空间点信息
                    JSONArray tmpArray = (JSONArray) value;
                    //获取经纬度数据
                    for (int j = 0; j < tmpArray.size(); j++) {
                        JSONObject object = tmpArray.getJSONObject(j);
                        String X = object.getString("X");
                        String Y = object.getString("Y");
                        //定义经纬度点信息
                        tmpFeature.addPoint(Double.parseDouble(X), Double.parseDouble(Y));
                    }
                }else{
                    //获取字段对象
                    Field field = tmpFeatureAttr.getField(key);
                    //判断字段是否为空
                    if(field!=null && value!=null && !value.toString().equalsIgnoreCase("null")){
                        tmpFeature.addAttribute(key,value);
                    }
                }
            }
        }
        return features;
    }
    @Override
    public Message queryFeatures(String layerUrl, String where) {
        return ArcGisUtil.query(layerUrl,where);
    }
    
    /**
     * @author FWY
     * @Description: 获取图层
     * @date 2022/6/17 8:51 上午
     * @Param [layerUrl, isAsyn]
     * @return com.hndist.entity.gisServer.Layers
     */
    @Override
    public Layers getLayers(String layerUrl, boolean isAsyn) {
        Layers layers = dgLayer(layerUrl,null,"/",isAsyn);
        return layers;
    }

    /**
     * @author FWY
     * @Description: 递归获取地图
     * @date 2022/6/17 8:50 上午
     * @Param [layerUrl, layers, isAsyn]
     * @return com.hndist.entity.gisServer.Layers
     */
    private Layers dgLayer(String layerUrl,Layers layers,String name, boolean isAsyn){
        //获取图层数据
        layers = getLayer(layerUrl,name);
        //获取子服务
        if(isAsyn && layers!=null){
            List<String> tmplayers = layers.getFolders();
            for (String layerName:tmplayers){
                Layers sonlayers = getLayer(layerUrl+"services/"+layerName,layerName);
                layers.getChildrenLayers().add(sonlayers);
                if(sonlayers.getFolders().size()>0){
                    dgLayer(layerUrl+"services/"+layerName,sonlayers,layerName,isAsyn);
                }
            }
        }
        return layers;
    }

    /**
     * @author FWY
     * @Description: 获取图层
     * @date 2022/6/16 7:14 下午
     * @Param [layerUrl]
     * @return com.hndist.entity.gisServer.Layers
     */
    private Layers getLayer(String layerUrl,String name){
        //获取图层数据
        String txtLayers = HttpClientUtil.doGet(layerUrl+"?f=json");
        //解析为对象
        Layers layers = JSONObject.parseObject(txtLayers, Layers.class);
        //设置文件夹名称
        layers.setName(name);
        return layers;
    }

    @Override
    public Message addFeatures(String layerUrl, Features features) {
        return ArcGisUtil.addFeatures(layerUrl,features);
    }

    @Override
    public Message updateFeatures(String layerUrl, Features features) {
        return ArcGisUtil.updateFeatures(layerUrl,features);
    }

    @Override
    public Message deleteFeatures(String layerUrl, String where) {
        return ArcGisUtil.deleteFeatures(layerUrl,where);
    }

    @Override
    public Message updateHistoryFeatures(String layerUrl, Features features, String historyLayerName) {
        Message message = ArcGisUtil.addFeatures(layerUrl,features);
        if(message.isState()){
            ArcGisUtil.deleteFeatures(layerUrl,"","");
        }
        return null;
    }
}
