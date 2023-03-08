package com.hndist.cim.gisserver.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hndist.cim.gisserver.entity.Feature;
import com.hndist.cim.gisserver.entity.Features;
import com.hndist.cim.gisserver.entity.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * @author FWY
 * @version V1.0.0
 * @ClassName: ArcGisUtil
 * @Description: TODO
 * @date 2022/5/30 2:11 下午
 * @Copyright: http://www.hndist.com All rights reserved.
 * 注意：本内容仅限于河南数慧信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class ArcGisUtil {

    //临时feature
    private static HashMap<String, Feature> featureHashMap = new HashMap<>();

    /**
     * @author FWY
     * @Description: 获取Feature属性
     * @date 2022/6/13 4:36 下午
     * @Param [url]
     * @return com.hndist.entity.gisServer.Feature
     */
    public static Feature getFeature(String layerUrl){
        //获取缓存属性
        Feature feature = featureHashMap.get(layerUrl);
        //判断是否存在缓存
        if(feature==null) {
            //获取属性信息
            String tmp = HttpClientUtil.doGet(layerUrl + "?f=json");
            //格式化属性对象
            feature = JSONObject.parseObject(tmp, Feature.class);
            //添加到缓存
            featureHashMap.put(layerUrl, feature);
        }
        //返回feature
        return feature;
    }

    /**
     * @author FWY
     * @Description: 添加Features
     * @date 2022/5/30 2:17 下午
     * @Param [features]
     * @return Message
     */
    public static Message addFeatures(String url, Features features){
        //返回消息
        Message message = new Message();
        //创建并组合请求参数
        Map<String, String> formData = new HashMap<String, String>();
        formData.put("features", JSONArray.toJSONString(features.getFeatureList(),SerializerFeature.DisableCircularReferenceDetect));
        formData.put("f", "json");
        //发送Post请求
        String body = HttpClientUtil.postParams(url+"/addFeatures", formData);
        message.setMessage(body);
        //判断内容是否为空
        if(body!=null){
            message.setState(body.indexOf("error")==-1);
        }
        //返回消息对象
        return message;
    }

    /**
     * @author FWY
     * @Description: 删除Features
     * @date 2022/5/30 2:18 下午
     * @Param [name, value]
     * @return Message
     */
    public static Message deleteFeatures(String url,String where){
        //返回消息
        Message message = new Message();
        //创建并组合请求参数
        Map<String, String> formData = new HashMap<String, String>();
        formData.put("where", where);
        formData.put("f", "json");
        //发送Post请求
        String body = HttpClientUtil.postParams(url+"/deleteFeatures", formData);
        message.setMessage(body);
        //判断内容是否为空
        if(body!=null){
            message.setState(body.indexOf("error")==-1);
        }
        //返回消息对象
        return message;
    }

    /**
     * @author FWY
     * @Description: 删除Features
     * @date 2022/5/30 2:18 下午
     * @Param [name, value]
     * @return Message
     */
    public static Message deleteFeatures(String url,String name,String value){
        //组合查询条件
        String where = name+"='"+value+"'";
        return deleteFeatures(url,value);
    }

    /**
     * @author FWY
     * @Description: 更新Features
     * @date 2022/5/30 2:18 下午
     * @Param [url,features]
     * @return boolean
     */
    public static Message updateFeatures(String url,Features features){
        //返回消息
        Message message = new Message();
        //创建并组合请求参数
        Map<String, String> formData = new HashMap<String, String>();
        formData.put("features", JSONArray.toJSONString(features.getFeatureList(),SerializerFeature.DisableCircularReferenceDetect));
        formData.put("f", "json");
        //发送Post请求
        String body = HttpClientUtil.postParams(url+"/updateFeatures", formData);
        message.setMessage(body);
        //判断内容是否为空
        if(body!=null){
            message.setState(body.indexOf("error")==-1);
        }
        //返回消息对象
        return message;
    }

    /**
     * @author FWY
     * @Description: 更新Features
     * @date 2022/5/30 2:18 下午
     * @Param [features, name]
     * @return boolean
     */
    public static Message updateFeatures(String url,Features features,String name){
        //创建返回消息对象
        Message message = new Message();
        //判断features是否为空
        if(features!=null){
            //遍历Feature
            for (Feature feature:features.getFeatureList()){
                //根据属性获取数据
                Object attribute = feature.getAttribute(name);
                //判断值是否为空
                if(attribute!=null){
                    //删除数据
                    message = deleteFeatures(url,name,attribute.toString());
                    //判断是否删除成功
                    if(message.isState()){
                        //添加并返回消息
                        message = addFeatures(url,features);
                    }
                }
            }
        }
        return message;
    }
    
    /**
     * @author FWY
     * @Description: 查询数据
     * @date 2022/5/30 5:43 下午
     * @Param [url, name, value]
     * @return org.jeecg.common.util.entity.bean.Message
     */
    public static Message query(String url,String name,String value){
        //返回消息
        Message message = new Message();
        //创建并组合请求参数
        Map<String, String> formData = new HashMap<String, String>();
        formData.put("where", name+"='"+value+"'");
        formData.put("outFields", "*");
        formData.put("f", "json");
        //发送Post请求
        String body = HttpClientUtil.postParams(url+"/query", formData);
        message.setMessage(body);
        //判断内容是否为空
        if(body!=null){
            message.setState(body.indexOf("error")==-1);
        }
        //返回消息对象
        return message;
    }

    /**
     * @author FWY
     * @Description: 查询数据
     * @date 2022/5/30 5:43 下午
     * @Param [url, name, value]
     * @return org.jeecg.common.util.entity.bean.Message
     */
    public static Message query(String url,String where){
        //返回消息
        Message message = new Message();
        //创建并组合请求参数
        Map<String, String> formData = new HashMap<String, String>();
        formData.put("where", where);
        formData.put("outFields", "*");
        formData.put("f", "json");
        //发送Post请求
        String body = HttpClientUtil.postParams(url+"/query", formData);
        message.setMessage(body);
        //判断内容是否为空
        if(body!=null){
            message.setState(body.indexOf("error")==-1);
        }
        //返回消息对象
        return message;
    }

    public static void main(String[] args) {
        ArcGisUtil.getFeature("http://10.37.1.22:6080/arcgis/rest/services/GHSCTEST/ZDTCH/FeatureServer/0");
//        Features features = ShapeUtil.parsingShpFile("/Users/tcsn/Desktop/济源档案测试/济源档案测试.shp");
//        Features features = ShapeUtil.parsingTxtFile("/Users/tcsn/Desktop/济源档案测试/坐标文件.txt");
//        features.addAttribute("wid","1000111");
//        features.addAttribute("name","张三");
//        Message message =  ArcGisUtil.addFeatures(url,features);
//        Message message =  ArcGisUtil.deleteFeatures("name","test");
//        Message message =  ArcGisUtil.updateFeatures(features,"name");
//        System.out.println(message.getMessage());
    }

}
