package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimClusterAnalys;
import com.hndist.server.service.ICimClusterAnalysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimClusterAnalys
 * @Description: 聚合分析Controller
 * @author AI Builder
 * @date 2022-12-13 05:23:26
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "聚合分析")
@RestController
@RequestMapping("/cimClusterAnalys")
public class ClusterAnalysController extends BaseController
{
    @Autowired
    private ICimClusterAnalysService cimClusterAnalysService;

    /**
     * 查询聚合分析列表
     */

    @ApiOperation(value = "查询聚合分析列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询聚合分析列表")
    //@PreAuthorize("@ss.hasPermi('cimClusterAnalys:cimClusterAnalys:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimClusterAnalys cimClusterAnalys)
    {
        startPage();
        List<CimClusterAnalys> list = cimClusterAnalysService.selectCimClusterAnalysList(cimClusterAnalys);
        return getDataTable(list);
    }

    /**
     * 导出聚合分析列表
     */
    @ApiOperation(value = "导出聚合分析列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出聚合分析列表")
    //@PreAuthorize("@ss.hasPermi('cimClusterAnalys:cimClusterAnalys:export')")
    @Log(title = "聚合分析", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimClusterAnalys cimClusterAnalys)
    {
        List<CimClusterAnalys> list = cimClusterAnalysService.selectCimClusterAnalysList(cimClusterAnalys);
        ExcelUtil<CimClusterAnalys> util = new ExcelUtil<CimClusterAnalys>(CimClusterAnalys.class);
        util.exportExcel(response, list, "聚合分析数据");
    }

    /**
     * 获取聚合分析详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取聚合分析详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取聚合分析详细信息")
    //@PreAuthorize("@ss.hasPermi('cimClusterAnalys:cimClusterAnalys:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimClusterAnalysService.selectCimClusterAnalysById(id));
    }

    /**
     * 新增聚合分析
     */
    @ApiOperation(value = "新增聚合分析",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增聚合分析")
    //@PreAuthorize("@ss.hasPermi('cimClusterAnalys:cimClusterAnalys:add')")
    @Log(title = "聚合分析", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimClusterAnalys cimClusterAnalys)
    {
        return AjaxResult.success("操作成功",cimClusterAnalysService.insertCimClusterAnalys(cimClusterAnalys));
    }

    /**
     * 修改聚合分析
     */
    @ApiOperation(value = "修改聚合分析",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改聚合分析")
    //@PreAuthorize("@ss.hasPermi('cimClusterAnalys:cimClusterAnalys:edit')")
    @Log(title = "聚合分析", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimClusterAnalys cimClusterAnalys)
    {
        return AjaxResult.success("操作成功",cimClusterAnalysService.updateCimClusterAnalys(cimClusterAnalys));
    }

    /**
     * 删除聚合分析
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除聚合分析",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除聚合分析")
    //@PreAuthorize("@ss.hasPermi('cimClusterAnalys:cimClusterAnalys:remove')")
    @Log(title = "聚合分析", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimClusterAnalysService.deleteCimClusterAnalysByIds(ids));
    }
    /**
     * 获取聚合分析树信息
     */
    @ApiOperation(value = "获取聚合分析树信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取聚合分析树信息")
    //@PreAuthorize("@ss.hasPermi('cimClusterAnalys:cimClusterAnalys:query')")
    @GetMapping(value = "/treeList")
    public AjaxResult treeList(String type)
    {
        List<CimClusterAnalys> list = cimClusterAnalysService.selectCimClusterAnalysTreeList(type);
        return AjaxResult.success(list);
    }
}
