package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimEllipsoid;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimEllipsoid
 * @Description: 椭球体
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimEllipsoidMapper
{
    /**
     * 查询椭球体
     *
     * @param id 椭球体主键
     * @return 椭球体
     */
    public CimEllipsoid selectBaseEllipsoidById(@Param("id") String id);

    /**
     * 查询椭球体列表
     *
     * @param cimEllipsoid 椭球体
     * @return 椭球体集合
     */
    public List<CimEllipsoid> selectBaseEllipsoidList(@Param("vo") CimEllipsoid cimEllipsoid);

    /**
     * 新增椭球体
     *
     * @param cimEllipsoid 椭球体
     * @return 结果
     */
    public int insertBaseEllipsoid(CimEllipsoid cimEllipsoid);

    /**
     * 修改椭球体
     *
     * @param cimEllipsoid 椭球体
     * @return 结果
     */
    public int updateBaseEllipsoid(CimEllipsoid cimEllipsoid);

    /**
     * 删除椭球体
     *
     * @param id 椭球体主键
     * @return 结果
     */
    public int deleteBaseEllipsoidById(String id);

    /**
     * 批量椭球体学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBaseEllipsoidByIds(String[] ids);

}
