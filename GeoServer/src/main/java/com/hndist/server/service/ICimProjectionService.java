package com.hndist.server.service;
import com.hndist.server.domain.CimProjection;
import java.util.List;
/**
 * @Title: CimProjection
 * @Description: 投影坐标服务接口
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimProjectionService
{
    /**
     * 查询投影坐标
     *
     * @param id 投影坐标主键
     * @return 投影坐标
     */
    public CimProjection selectCimProjectionById(String id);

    /**
     * 查询投影坐标列表
     *
     * @param cimProjection 投影坐标
     * @return 投影坐标集合
     */
    public List<CimProjection> selectCimProjectionList(CimProjection cimProjection);

    /**
     * 新增投影坐标
     *
     * @param cimProjection 投影坐标
     * @return 结果
     */
    public String insertCimProjection(CimProjection cimProjection);

    /**
     * 修改投影坐标
     *
     * @param cimProjection 投影坐标
     * @return 结果
     */
    public String updateCimProjection(CimProjection cimProjection);

    /**
     * 批量删除投影坐标
     *
     * @param ids 需要删除的投影坐标主键集合
     * @return 结果
     */
    public int deleteCimProjectionByIds(String[] ids);

    /**
     * 删除投影坐标信息
     *
     * @param id 投影坐标主键
     * @return 结果
     */
    public int deleteCimProjectionById(String id);

}
