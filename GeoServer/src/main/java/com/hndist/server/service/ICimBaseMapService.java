package com.hndist.server.service;
import com.hndist.server.domain.CimBaseMap;
import java.util.List;
/**
 * @Title: CimBaseMap
 * @Description: 底图服务服务接口
 * @author AI Builder
 * @date 2022-11-15 08:03:48
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimBaseMapService
{
    /**
     * 查询底图服务
     *
     * @param id 底图服务主键
     * @return 底图服务
     */
    public CimBaseMap selectCimBaseMapById(String id);

    /**
     * 查询底图服务列表
     *
     * @param cimBaseMap 底图服务
     * @return 底图服务集合
     */
    public List<CimBaseMap> selectCimBaseMapList(CimBaseMap cimBaseMap);

    /**
     * 新增底图服务
     *
     * @param cimBaseMap 底图服务
     * @return 结果
     */
    public String insertCimBaseMap(CimBaseMap cimBaseMap);

    /**
     * 修改底图服务
     *
     * @param cimBaseMap 底图服务
     * @return 结果
     */
    public String updateCimBaseMap(CimBaseMap cimBaseMap);

    /**
     * 批量删除底图服务
     *
     * @param ids 需要删除的底图服务主键集合
     * @return 结果
     */
    public int deleteCimBaseMapByIds(String[] ids);

    /**
     * 删除底图服务信息
     *
     * @param id 底图服务主键
     * @return 结果
     */
    public int deleteCimBaseMapById(String id);

}
