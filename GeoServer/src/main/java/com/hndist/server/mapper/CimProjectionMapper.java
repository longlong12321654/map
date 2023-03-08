package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimProjection;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimProjection
 * @Description: 投影坐标
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimProjectionMapper
{
    /**
     * 查询投影坐标
     *
     * @param id 投影坐标主键
     * @return 投影坐标
     */
    public CimProjection selectCimProjectionById(@Param("id") String id);

    /**
     * 查询投影坐标列表
     *
     * @param cimProjection 投影坐标
     * @return 投影坐标集合
     */
    public List<CimProjection> selectCimProjectionList(@Param("vo") CimProjection cimProjection);

    /**
     * 新增投影坐标
     *
     * @param cimProjection 投影坐标
     * @return 结果
     */
    public int insertCimProjection(CimProjection cimProjection);

    /**
     * 修改投影坐标
     *
     * @param cimProjection 投影坐标
     * @return 结果
     */
    public int updateCimProjection(CimProjection cimProjection);

    /**
     * 删除投影坐标
     *
     * @param id 投影坐标主键
     * @return 结果
     */
    public int deleteCimProjectionById(String id);

    /**
     * 批量投影坐标学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimProjectionByIds(String[] ids);

}
