package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimClusterAnalys;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimClusterAnalys
 * @Description: 聚合分析
 * @author AI Builder
 * @date 2022-12-13 05:23:26
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimClusterAnalysMapper
{
    /**
     * 查询聚合分析
     *
     * @param id 聚合分析主键
     * @return 聚合分析
     */
    public CimClusterAnalys selectCimClusterAnalysById(@Param("id") String id);

    /**
     * 查询聚合分析列表
     *
     * @param cimClusterAnalys 聚合分析
     * @return 聚合分析集合
     */
    public List<CimClusterAnalys> selectCimClusterAnalysList(@Param("vo") CimClusterAnalys cimClusterAnalys);

    /**
     * 新增聚合分析
     *
     * @param cimClusterAnalys 聚合分析
     * @return 结果
     */
    public int insertCimClusterAnalys(CimClusterAnalys cimClusterAnalys);

    /**
     * 修改聚合分析
     *
     * @param cimClusterAnalys 聚合分析
     * @return 结果
     */
    public int updateCimClusterAnalys(CimClusterAnalys cimClusterAnalys);

    /**
     * 删除聚合分析
     *
     * @param id 聚合分析主键
     * @return 结果
     */
    public int deleteCimClusterAnalysById(String id);

    /**
     * 批量聚合分析
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimClusterAnalysByIds(String[] ids);
    /**
     * 查询聚合分析树信息
     *
     * @param type
     * @return 聚合分析集合
     */
    public List<CimClusterAnalys> selectCimClusterAnalysTreeList(String type);

}
