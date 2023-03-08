package com.hndist.cim.gisserver.service;

import com.hndist.cim.gisserver.entity.MapService;
import com.hndist.cim.gisserver.entity.MapLayer;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 河南数慧信息技术有限公司
 * @version V1.0.0
 * @ClassName: PublishingService
 * @Description: TODO
 * @date 2022/11/17 11:03
 * @Copyright: www.hndist.com All rights reserved.
 * 注意：本内容仅限于共享研发中心内部传阅，禁止外泄以及用于其他的商业目
 */
public interface IPublishingService {
    
    /*
     * @author WY 
     * @Description: 发布服务
     * @date 2022/11/17 11:09
     * @Param [spatialFileList] 
     * @return void 
     */
    public MapService publishingServices(MapService mapService,boolean isPublishing) throws IOException;

    /*
     * @author WY
     * @Description: 获取地图服务
     * @date 2022/11/18 15:53
     * @Param [folder]
     * @return com.hndist.cim.gisserver.entity.MapService
     */
    public MapService getMapService(String mapServiceName,boolean isPublishing);

    /*
     * @author WY 
     * @Description: 获取地图服务列表
     * @date 2022/11/18 15:53
     * @Param [folder] 
     * @return java.util.List<com.hndist.cim.gisserver.entity.MapService> 
     */
    public List<MapService> getMapServiceList(String folder,boolean isPublishing);

    /*
     * @author WY
     * @Description: 删除服务
     * @date 2022/11/17 11:09
     * @Param [spatialFileList]
     * @return void
     */
    public boolean delServices(String mapServiceName,boolean isPublishing);

//    /*
//     * @author ZY
//     * @Description: 绘制类别
//     * @date 2022/11/17 11:09
//     * @Param [spatialFileList]
//     * @return void
//     */
//    List<Map> drawCategory(String valueFiles, String serverUrl, boolean isPublishing, String layerName);
}

