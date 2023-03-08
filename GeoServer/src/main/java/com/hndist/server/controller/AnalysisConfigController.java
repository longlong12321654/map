package com.hndist.server.controller;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.util.StringUtil;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.StringUtils;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimAnalysisConfig;
import com.hndist.server.domain.dto.ArcGisParam;
import com.hndist.server.service.ICimAnalysisConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimAnalysisConfig
 * @Description: 一键分析Controller
 * @author AI Builder
 * @date 2022-03-31 03:42:25
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Api(tags = "一键分析管理")
@RestController
@RequestMapping("/cimAnalysisConfig")
public class AnalysisConfigController extends BaseController
{
    @Autowired
    private ICimAnalysisConfigService bsAnalysisConfigService;

    /**
     * 分析列表
     */
    @ApiOperation("查询一键分析列表")
    //@PreAuthorize("@ss.hasPermi('cimAnalysisConfig:cimAnalysisConfig:list')")
    @GetMapping("/analysisQuery")
    public AjaxResult analysisQuery(String ids, String geometry, String geometryType)
    {
        if (StringUtils.isEmpty(ids)) {
            return AjaxResult.error("请选择一条数据!");
        }
        if (StringUtils.isEmpty(geometry) || StringUtils.isEmpty(geometryType)) {
            return AjaxResult.error("几何参数不能为空!");
        }
        String[] array = ids.split(",");
        return bsAnalysisConfigService.onemapAnalysis(array, geometry, geometryType);
    }

    /**
     * 查询一键分析列表
     */
    @ApiOperation("查询一键分析列表")
    //@PreAuthorize("@ss.hasPermi('cimAnalysisConfig:cimAnalysisConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimAnalysisConfig cimAnalysisConfig)
    {
        startPage();
        List<CimAnalysisConfig> list = bsAnalysisConfigService.selectBsAnalysisConfigList(cimAnalysisConfig);
        return getDataTable(list);
    }

    /**
     * 导出一键分析列表
     */
    @ApiOperation("导出一键分析列表")
    //@PreAuthorize("@ss.hasPermi('cimAnalysisConfig:cimAnalysisConfig:export')")
    @Log(title = "一键分析", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimAnalysisConfig cimAnalysisConfig)
    {
        List<CimAnalysisConfig> list = bsAnalysisConfigService.selectBsAnalysisConfigList(cimAnalysisConfig);
        ExcelUtil<CimAnalysisConfig> util = new ExcelUtil<CimAnalysisConfig>(CimAnalysisConfig.class);
        util.exportExcel(response, list, "一键分析数据");
    }

    /**
     * 获取一键分析详细信息
     */
    @ApiOperation("获取一键分析详细信息")
    //@PreAuthorize("@ss.hasPermi('bsAnalysisConfig:bsAnalysisConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bsAnalysisConfigService.selectBsAnalysisConfigById(id));
    }

    /**
     * 新增一键分析
     */
    @ApiOperation("新增一键分析")
    //@PreAuthorize("@ss.hasPermi('cimAnalysisConfig:cimAnalysisConfig:add')")
    @Log(title = "一键分析", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimAnalysisConfig cimAnalysisConfig)
    {
        return toAjax(bsAnalysisConfigService.insertBsAnalysisConfig(cimAnalysisConfig));
    }

    /**
     * 修改一键分析
     */
    @ApiOperation("修改一键分析")
    //@PreAuthorize("@ss.hasPermi('cimAnalysisConfig:cimAnalysisConfig:edit')")
    @Log(title = "一键分析", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimAnalysisConfig cimAnalysisConfig)
    {
        return toAjax(bsAnalysisConfigService.updateBsAnalysisConfig(cimAnalysisConfig));
    }

    /**
     * 删除一键分析
     */
    @ApiOperation("删除一键分析")
    //@PreAuthorize("@ss.hasPermi('bsAnalysisConfig:bsAnalysisConfig:remove')")
    @Log(title = "一键分析", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bsAnalysisConfigService.deleteBsAnalysisConfigByIds(ids));
    }

    /**
     * 获取一键分析树信息
     */
    @ApiOperation("获取一键分析树信息")
    @GetMapping(value = "/treeList")
    public AjaxResult treeList(String type)
    {
        List<CimAnalysisConfig> list = bsAnalysisConfigService.selectBsAnalysisConfigTreeList(type);
        return AjaxResult.success(list);
    }
}
