package com.hndist.server.service.impl;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.util.StringUtil;
import com.hndist.cim.gisserver.entity.GeomFormerType;
import com.hndist.cim.gisserver.utils.HttpUtil;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.utils.StringUtils;
import com.hndist.server.constant.ArcgisGeometryTypeEnum;
import com.hndist.server.constant.BusinessConstant;
import com.hndist.server.constant.SysServiceengineEnum;
import com.hndist.server.domain.CimAnalysisConfig;
import com.hndist.server.domain.dto.ArcGisParam;
import com.hndist.server.domain.dto.OutputStatistics;
import com.hndist.server.mapper.CimAnalysisConfigMapper;
import com.hndist.server.service.ICimAnalysisConfigService;
import com.hndist.server.util.BigDecimalUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Title: CimAnalysisConfig
 * @Description: 一键分析服务实现
 * @author AI Builder
 * @date 2022-03-31 03:42:25
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimAnalysisConfigServiceImpl implements ICimAnalysisConfigService
{

    @Autowired
    private CimAnalysisConfigMapper cimAnalysisConfigMapper;


    /**
     * 查询一键分析
     *
     * @param id 一键分析主键
     * @return 一键分析
     */
    @Override
    public CimAnalysisConfig selectBsAnalysisConfigById(String id)
    {
        CimAnalysisConfig cimAnalysisConfig = cimAnalysisConfigMapper.selectBsAnalysisConfigById(id);
        return cimAnalysisConfig;
    }

    /**
     * 查询一键分析列表
     *
     * @param cimAnalysisConfig 一键分析
     * @return 一键分析
     */
    @Override
    public List<CimAnalysisConfig> selectBsAnalysisConfigList(CimAnalysisConfig cimAnalysisConfig)
    {
        return cimAnalysisConfigMapper.selectBsAnalysisConfigList(cimAnalysisConfig);
    }

    /**
     * 一键分析信息
     *
     * @param ids 一键分析主键
     * @return 结果
     */
    @Override
    public AjaxResult onemapAnalysis(String[] ids, String geometry, String geometryType) {
        List<CimAnalysisConfig> configList = cimAnalysisConfigMapper.selectListByIds(ids);
        if (!CollectionUtils.isEmpty(configList)) {

            JSONArray jsonArray = new JSONArray();
            for (CimAnalysisConfig item : configList) {
                String serviceengine = item.getServiceengine();
                // 获取展示字段
//                String viewFields = item.getViewFields();
//                String[] viewField = viewFields.split(",");
//                String viewColumn = arrayToStrWithComma(viewField);
                // 服务名称
                String serverName = item.getName();
                String datastr = null;
                // mapserver服务分析
                if (SysServiceengineEnum.HndistServer.getIndex().equals(serviceengine) && "0".equals(item.getContent())) {
                    //拼接路径
                    StringBuilder serverUrl = new StringBuilder();
                    // 点：http://192.168.1.241:9082/SHMap/p_cim/rest/services/China/ZGXZQHJX/MapServer/%E4%B8%AD%E5%9B%BD%E8%A1%8C%E6%94%BF%E5%8C%BA%E7%95%8C%E7%BA%BF/query?pageSize=5&pageNum=1&serverName=China%2FZGXZQHJX&layerName=%E4%B8%AD%E5%9B%BD%E8%A1%8C%E6%94%BF%E5%8C%BA%E7%95%8C%E7%BA%BF&geometry=79.621373+30.981137&geometryType=Point&spatialRel=Intersects&returnGeometry=True
                    // 线：http://192.168.1.241:9082/SHMap/p_cim/rest/services/China/ZGXZQHJX/MapServer/%E4%B8%AD%E5%9B%BD%E8%A1%8C%E6%94%BF%E5%8C%BA%E7%95%8C%E7%BA%BF/query?pageSize=5&pageNum=1&serverName=China%2FZGXZQHJX&layerName=%E4%B8%AD%E5%9B%BD%E8%A1%8C%E6%94%BF%E5%8C%BA%E7%95%8C%E7%BA%BF&geometry=76.842935+30.825110,85.602701+34.256447,91.760001+27.551838,76.466657+30.806314,76.466657+30.806314,76.842935+30.825110&geometryType=Polyline&spatialRel=Intersects&returnGeometry=True
                    // 面：http://192.168.1.241:9082/SHMap/p_cim/rest/services/China/ZGXZQHJX/MapServer/%E4%B8%AD%E5%9B%BD%E8%A1%8C%E6%94%BF%E5%8C%BA%E7%95%8C%E7%BA%BF/query?pageSize=5&pageNum=1&serverName=China%2FZGXZQHJX&layerName=%E4%B8%AD%E5%9B%BD%E8%A1%8C%E6%94%BF%E5%8C%BA%E7%95%8C%E7%BA%BF&geometryType=Polygon&geometry=86.542260+37.631769,86.542260+37.631769,73.669447+30.377242,123.340436+35.833608,94.380117+33.495453,86.542260+37.631769&spatialRel=Intersects&returnGeometry=True
                    serverUrl.append(BusinessConstant.TRANSPORT_PROTOCOL);
                    serverUrl.append(item.getIp());
                    serverUrl.append(BusinessConstant.COLON);
                    serverUrl.append(item.getPort());
                    serverUrl.append("/SHMap");
                    serverUrl.append(item.getServiceUrl());
                    // 参数
                    Map<String, String> params = new HashMap<>(16);
                    params.put("geometry", geometry);
                    params.put("geometryType", geometryType);
                    params.put("serverName", serverName);
                    params.put("returnGeometry", "false");
                    // 查询hndist server服务
                    datastr = HttpUtil.doGet(serverUrl.toString(), params);
                } else if (SysServiceengineEnum.ArcGISServer.getIndex().equals(serviceengine) && "0".equals(item.getContent())) {
                    // arcgis服务分析
                    String serverUrl = BusinessConstant.TRANSPORT_PROTOCOL + item.getIp() + BusinessConstant.COLON + item.getPort() + item.getServiceUrl() +"/0/query";
                    datastr = arcgisQuery(serverUrl, geometryType, geometry);
                }

                // 获取结论中的字段数组
                String conclesion = item.getConclesion();
                String[] values = StringUtils.substringsBetween(conclesion, "{", "}");

                BigDecimal initvalue = new BigDecimal(0);

                JSONObject jsonObject = JSON.parseObject(datastr);
                JSONArray features = jsonObject.getJSONArray("features");
                if(features != null) {
                    // 限制条数500
                    int limit = features.size() > 500 ? 500 : features.size();
                    for (int i = 0; i < limit; i++) {
                        JSONObject obj = features.getJSONObject(i);
                        JSONObject attributes = obj.getJSONObject("attributes");
                        if (attributes.containsKey(values[1])) {
                            BigDecimal val = attributes.getBigDecimal(values[1]);
                            initvalue = BigDecimalUtil.add(initvalue, val);
                        }
                    }
                    int length = limit;

                    // 去掉花括号
                    // 结论：占压个数:%s个，占压总面积(㎡):%s。其中：{TBMJ:DLMC:占压面积(㎡)}
                    String conclesionNew = conclesion.replace("{", "").replace("}", "");
                    // 替换字段值
                    conclesionNew = conclesionNew
                            .replace("length", length + "")
                            .replace(values[1], initvalue + "");
                    item.setConclesion(conclesionNew);

                    JSONObject configJson = (JSONObject) JSONObject.toJSON(item);
                    jsonObject.put("analysisConfig", configJson);

                    jsonArray.add(jsonObject);
                }
            }

            return AjaxResult.success(jsonArray);
        }
        return AjaxResult.success();
    }

    /**
     * 新增一键分析
     *
     * @param cimAnalysisConfig 一键分析
     * @return 结果
     */
    @Override
    public int insertBsAnalysisConfig(CimAnalysisConfig cimAnalysisConfig)
    {
        String id = UUID.randomUUID().toString().replaceAll("-","");
        cimAnalysisConfig.setId(id);
        int num = cimAnalysisConfigMapper.insertBsAnalysisConfig(cimAnalysisConfig);
        return num;
    }

    /**
     * 修改一键分析
     *
     * @param cimAnalysisConfig 一键分析
     * @return 结果
     */
    @Override
    public int updateBsAnalysisConfig(CimAnalysisConfig cimAnalysisConfig)
    {
        int num = cimAnalysisConfigMapper.updateBsAnalysisConfig(cimAnalysisConfig);
        return num;
    }

    /**
     * 批量删除一键分析
     *
     * @param ids 需要删除的一键分析主键
     * @return 结果
     */
    @Override
    public int deleteBsAnalysisConfigByIds(String[] ids)
    {
        return cimAnalysisConfigMapper.deleteBsAnalysisConfigByIds(ids);
    }

    /**
     * 删除一键分析信息
     *
     * @param id 一键分析主键
     * @return 结果
     */
    @Override
    public int deleteBsAnalysisConfigById(String id)
    {
        return cimAnalysisConfigMapper.deleteBsAnalysisConfigById(id);
    }



    /**
     * 查询服务管理树信息
     *
     * @param type
     * @return 服务管理
     */
    @Override
    public List<CimAnalysisConfig> selectBsAnalysisConfigTreeList(String type)
    {
        return cimAnalysisConfigMapper.selectBsAnalysisConfigTreeList(type);
    }

    /**
     * arcGis查询
     * @return
     */
    private static String arcgisQuery(String url, String geometryType, String geometry) {
        // geojson类型
        String geoJsonType = "Polygon";
        //处理几何坐标
        if (ArcgisGeometryTypeEnum.Point.name().equals(geometryType)) {
            geometryType = BusinessConstant.ESRI_GEOMETRY_POINT;
            String content = geometry.replace(" ", ",");
            geometry = content;

        } else if (ArcgisGeometryTypeEnum.Polyline.name().equals(geometryType)) {
            geometryType = BusinessConstant.ESRI_GEOMETRY_POLYLINE;
            Map<String, Object> rings = new HashMap<>();
            String[] split = geometry.split(" ");
            List<String> stringList = Arrays.asList(split);
            List<List> outter = new ArrayList<>();
            List<List> content = new ArrayList<>();
            stringList.forEach(str->{
                String[] xys = str.split(",");
                List<Double> itemlist = new ArrayList<>();
                itemlist.add(Double.valueOf(xys[0]));
                itemlist.add(Double.valueOf(xys[1]));
                content.add(itemlist);
            });
            outter.add(content);
            rings.put("paths", outter);
            geometry = JSON.toJSONString(rings, SerializerFeature.DisableCircularReferenceDetect);

        } else if (ArcgisGeometryTypeEnum.Polygon.name().equals(geometryType)) {
            geometryType = BusinessConstant.ESRI_GEOMETRY_POLYGON;
            Map<String, Object> rings = new HashMap<>();
            String[] split = geometry.split(" ");
            List<String> stringList = Arrays.asList(split);
            List<List> outter = new ArrayList<>();
            List<List> content = new ArrayList<>();
            for (String str : stringList) {
                String[] xys = str.split(",");
                List<Double> itemlist = new ArrayList<>();
                itemlist.add(Double.valueOf(xys[0]));
                itemlist.add(Double.valueOf(xys[1]));
                content.add(itemlist);
            }
            outter.add(content);
            rings.put("rings", outter);
            geometry = JSON.toJSONString(rings, SerializerFeature.DisableCircularReferenceDetect);

        }
        //查询条件  name='3' 默认1=1
        String where = "1=1";
        // 服务url格式：http: IP+端口号+/arcgis/rest/services/wzx/土地利用现状2019/MapServer/0/query
        //是否返回几何图形 默认false
        String returnGeometry = "true";

        //创建一个http客户端
        HttpClient client = new DefaultHttpClient();
        //创建一个POST请求
        //String url = "http://192.168.1.9:6080/arcgis/rest/services/wuzhi/%E5%9C%9F%E5%9C%B0%E5%88%A9%E7%94%A8%E7%8E%B0%E7%8A%B62019/MapServer/5/query";
        HttpPost requestPost = new HttpPost(url);
        //设置HTTP POST请求参数必须用NameValuePair
        //where=1%3D1&text=&objectIds=1&time=&geometry=&geometryType=esriGeometryEnvelope&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&outFields=*&returnGeometry=true&maxAllowableOffset=&geometryPrecision=&outSR=&returnIdsOnly=false&returnCountOnly=false&orderByFields=&groupByFieldsForStatistics=&outStatistics=&returnZ=false&returnM=false&gdbVersion=&returnDistinctValues=false&f=pjson
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("f", "json"));
        params.add(new BasicNameValuePair("where", where));
        params.add(new BasicNameValuePair("outFields", "*"));
        params.add(new BasicNameValuePair("returnGeometry", returnGeometry));
        params.add(new BasicNameValuePair("geometryType", geometryType));
        params.add(new BasicNameValuePair("geometry", geometry));
        params.add(new BasicNameValuePair("spatialRel", "esriSpatialRelIntersects"));
        params.add(new BasicNameValuePair("outStatistics", ""));

        try {
            //设置http Post请求参数
            HttpEntity entity = new UrlEncodedFormEntity(params);
            requestPost.setEntity(entity);
            HttpResponse response = client.execute(requestPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                //如果成功，则进行后续的分析
                String result = EntityUtils.toString(response.getEntity());
                String content = "";
                if (StringUtil.isNotEmpty(result)) {
                    JSONObject jsonObject = JSONObject.parseObject(result);
                    //获取要素数据转换geojson数据
                    JSONArray featuresArr = jsonObject.getJSONArray("features");
                    if (featuresArr != null) {
                        for (int i = 0; i < featuresArr.size(); i++) {
                            JSONObject itemFeatures = featuresArr.getJSONObject(i);
                            JSONObject geometryObj = itemFeatures.getJSONObject("geometry");
                            JSONArray ringsArr = geometryObj.getJSONArray("rings");
                            geometryObj.put("type", geoJsonType);
                            geometryObj.put("coordinates", ringsArr);
                            geometryObj.remove("rings");
                        }
                    }
                    content = jsonObject.toString();
                }
                return content;

            } else {
                String result = EntityUtils.toString(response.getEntity());
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            //进行处理操作
        } catch (IOException e) {
            //进行处理操作
        }
        return null;
    }

    /**
     * 将数组转换成以逗号分隔的字符串
     *
     * @param needChange
     *            需要转换的数组
     * @return 以逗号分割的字符串
     */
    public static String arrayToStrWithComma(String[] needChange) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < needChange.length; i++) {
            sb.append(needChange[i].trim());
            if ((i + 1) != needChange.length) {
                sb.append(",");
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
//        String str = "结论：占压个数:{length}个，占压总面积(㎡):{DKMJ}。其中：{DKMJ:PZDWMC:占压面积(㎡)}";
//        String[] values= StringUtils.substringsBetween(str,"{","}");
//        System.out.print(Arrays.toString(values));

//        ArcGisParam param = new ArcGisParam();
//        param.setOutfields("*");
//        param.setReturnGeometry("false");
//        //param.setGeometry("{\"hasZ\":true,\"spatialReference\":{\"wkid\":4490},\"rings\":[[[113.10970954805461,35.106719150768946,0],[113.27612101500009,35.07661405427454,0],[113.2761214007118,35.076613977128495,0],[113.27612196324061,35.07661383747836,0],[113.27612251340452,35.07661366801794,0],[113.27612304884765,35.07661346947288,0],[113.27612356727718,35.076613242693384,0],[113.27612406647307,35.076612988650545,0],[113.27612454429773,35.07661270843223,0],[113.27612499870499,35.07661240323836,0],[113.27612542774905,35.07661207437583,0],[113.27612582959266,35.07661172325287,0],[113.27612620251506,35.076611351373074,0],[113.27612654491938,35.07661096032885,0],[113.27612685533931,35.07661055179473,0],[113.27612713244568,35.07661012752011,0],[113.2761273750518,35.0766096893218,0],[113.27612758211883,35.07660923907624,0],[113.27612775276009,35.07660877871144,0],[113.27612788624484,35.07660831019876,0],[113.27612798200148,35.076607835544436,0],[113.27612803961998,35.07660735678104,0],[113.2761280588536,35.07660687595867,0],[113.27612803961998,35.07660639513628,0],[113.27612798200148,35.07660591637287,0],[113.27612788624484,35.07660544171853,0],[113.27612775276009,35.07660497320583,0],[113.27612758211883,35.076604512841016,0],[113.2761273750518,35.07660406259543,0],[113.27612713244568,35.07660362439708,0],[113.27612685533931,35.07660320012243,0],[113.27612654491938,35.076602791588265,0],[113.27612620251506,35.07660240054401,0],[113.27612582959266,35.07660202866415,0],[113.27612542774905,35.076601677541156,0],[113.27612499870499,35.0766013486786,0],[113.2761245560048,35.07660105084888,0],[113.09752377062775,34.963985619642216,0],[113.09752328109603,34.96398533166329,0],[113.09752278190012,34.96398507727028,0],[113.0975222634706,34.963984850178186,0],[113.09752172802746,34.96398465135944,0],[113.09752117786354,34.96398448166543,0],[113.09752061533474,34.96398434182281,0],[113.09752004284988,34.963984232430406,0],[113.09751946286043,34.96398415395665,0],[113.09751887785,34.96398410673756,0],[113.09751829032369,34.96398409097537,0],[113.09751770279739,34.96398410673756,0],[113.09751711778696,34.96398415395665,0],[113.09751653779752,34.963984232430406,0],[113.09751596531265,34.96398434182281,0],[113.09751540278386,34.96398448166543,0],[113.09751485261994,34.96398465135944,0],[113.0975143171768,34.963984850178186,0],[113.09751379874727,34.96398507727028,0],[113.09751329955137,34.96398533166329,0],[113.09751282172672,34.963985612267855,0],[113.09751236731947,34.96398591788241,0],[113.09751193827542,34.96398624719823,0],[113.0975115364318,34.96398659880516,0],[113.0975111635094,34.96398697119756,0],[113.09751082110509,34.96398736278078,0],[113.09751051068514,34.963987771878,0],[113.09751023357877,34.96398819673742,0],[113.09750999097265,34.9639886355397,0],[113.09750978390562,34.963989086405846,0],[113.09750961326438,34.96398954740516,0],[113.09750947977962,34.963990016563585,0],[113.09750938402297,34.96399049187211,0],[113.09750932640449,34.963990971295395,0],[113.09750930717085,34.96399145278047,0],[113.09750932905595,34.96399196634438,0],[113.10969864748753,35.10671248777034,0],[113.10969870245457,35.10671293433416,0],[113.10969879821121,35.106713408813285,0],[113.10969893169596,35.10671387715304,0],[113.10969910233722,35.10671433734791,0],[113.10969930940425,35.10671478742729,0],[113.10969955201037,35.10671522546387,0],[113.10969982911674,35.106715649581886,0],[113.10970013953668,35.106716057965215,0],[113.10970048194099,35.1067164488651,0],[113.10970085486339,35.10671682060765,0],[113.10970125670701,35.106717171601005,0],[113.10970168575106,35.10671750034215,0],[113.10970214015832,35.10671780542337,0],[113.10970261798296,35.10671808553827,0],[113.10970311717887,35.10671833948734,0],[113.1097036356084,35.10671856618314,0],[113.10970417105153,35.106718764654914,0],[113.10970472121544,35.10671893405279,0],[113.10970528374425,35.106719073651355,0],[113.10970585622911,35.10671918285287,0],[113.10970643621856,35.106719261189674,0],[113.10970702122899,35.106719308326355,0],[113.10970760875529,35.10671932406104,0],[113.1097081962816,35.106719308326355,0],[113.10970878129203,35.106719261189674,0],[113.10970936128146,35.10671918285287,0],[113.10970954805461,35.106719150768946,0]]]}");
//        param.setGeometryType("esriGeometryPolygon");
//        new CimBaseMapServiceImpl();
//        String s = arcgisQuery(param);
//        System.out.println(s);

    }

}
