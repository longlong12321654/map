package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimAnalysisConfig;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimAnalysisConfig
 * @Description: 一键分析
 * @author AI Builder
 * @date 2022-03-31 03:42:25
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimAnalysisConfigMapper
{
    /**
     * 查询一键分析
     *
     * @param id 一键分析主键
     * @return 一键分析
     */
    public CimAnalysisConfig selectBsAnalysisConfigById(@Param("id") String id);

    /**
     * 查询一键分析列表
     *
     * @param cimAnalysisConfig 一键分析
     * @return 一键分析集合
     */
    public List<CimAnalysisConfig> selectBsAnalysisConfigList(@Param("vo") CimAnalysisConfig cimAnalysisConfig);

    /**
     * 新增一键分析
     *
     * @param cimAnalysisConfig 一键分析
     * @return 结果
     */
    public int insertBsAnalysisConfig(CimAnalysisConfig cimAnalysisConfig);

    /**
     * 修改一键分析
     *
     * @param cimAnalysisConfig 一键分析
     * @return 结果
     */
    public int updateBsAnalysisConfig(CimAnalysisConfig cimAnalysisConfig);

    /**
     * 删除一键分析
     *
     * @param id 一键分析主键
     * @return 结果
     */
    public int deleteBsAnalysisConfigById(String id);

    /**
     * 批量一键分析
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBsAnalysisConfigByIds(String[] ids);

    /**
     * 查询一键分析
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public List<CimAnalysisConfig> selectListByIds(String[] ids);

    /**
     * 查询一键分析树信息
     *
     * @param type
     * @return 服务管理集合
     */
    public List<CimAnalysisConfig> selectBsAnalysisConfigTreeList(String type);

}
