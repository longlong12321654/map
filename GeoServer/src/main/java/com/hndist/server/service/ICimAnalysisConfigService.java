package com.hndist.server.service;
import com.alibaba.fastjson.JSONArray;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.server.domain.CimAnalysisConfig;
import com.hndist.server.domain.dto.ArcGisParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * @Title: CimAnalysisConfig
 * @Description: 一键分析服务接口
 * @author AI Builder
 * @date 2022-11-07
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimAnalysisConfigService
{
    /**
     * 查询一键分析
     *
     * @param id 一键分析主键
     * @return 一键分析
     */
    public CimAnalysisConfig selectBsAnalysisConfigById(String id);

    /**
     * 查询一键分析列表
     *
     * @param cimAnalysisConfig 一键分析
     * @return 一键分析集合
     */
    public List<CimAnalysisConfig> selectBsAnalysisConfigList(CimAnalysisConfig cimAnalysisConfig);


    /**
     * 一键分析信息
     *
     * @return 结果
     */
    public AjaxResult onemapAnalysis(String[] ids, String geometry, String geometryType);

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
     * 批量删除一键分析
     *
     * @param ids 需要删除的一键分析主键集合
     * @return 结果
     */
    public int deleteBsAnalysisConfigByIds(String[] ids);

    /**
     * 删除一键分析信息
     *
     * @param id 一键分析主键
     * @return 结果
     */
    public int deleteBsAnalysisConfigById(String id);

    /**
     * 查询一键分析管理树信息
     * @param type 为空时返回所有目录 不为空时返回所有信息
     * @return 服务管理集合
     */
    public List<CimAnalysisConfig> selectBsAnalysisConfigTreeList(String type);

}
