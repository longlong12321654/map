package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimPathWalkthrough;
import com.hndist.server.service.ICimPathWalkthroughService;
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
 * @Title: CimPathWalkthrough
 * @Description: 路径漫游Controller
 * @author AI Builder
 * @date 2022-12-01 10:07:23
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "路径漫游")
@RestController
@RequestMapping("/cimPathWalkthrough")
public class PathWalkthroughController extends BaseController
{
    @Autowired
    private ICimPathWalkthroughService cimPathWalkthroughService;

    /**
     * 查询路径漫游列表
     */

    @ApiOperation(value = "查询路径漫游列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询路径漫游列表")
    //@PreAuthorize("@ss.hasPermi('cimPathWalkthrough:cimPathWalkthrough:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimPathWalkthrough cimPathWalkthrough)
    {
        startPage();
        List<CimPathWalkthrough> list = cimPathWalkthroughService.selectCimPathWalkthroughList(cimPathWalkthrough);
        return getDataTable(list);
    }

    /**
     * 导出路径漫游列表
     */
    @ApiOperation(value = "导出路径漫游列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出路径漫游列表")
    //@PreAuthorize("@ss.hasPermi('cimPathWalkthrough:cimPathWalkthrough:export')")
    @Log(title = "路径漫游", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimPathWalkthrough cimPathWalkthrough)
    {
        List<CimPathWalkthrough> list = cimPathWalkthroughService.selectCimPathWalkthroughList(cimPathWalkthrough);
        ExcelUtil<CimPathWalkthrough> util = new ExcelUtil<CimPathWalkthrough>(CimPathWalkthrough.class);
        util.exportExcel(response, list, "路径漫游数据");
    }

    /**
     * 获取路径漫游详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取路径漫游详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取路径漫游详细信息")
    //@PreAuthorize("@ss.hasPermi('cimPathWalkthrough:cimPathWalkthrough:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimPathWalkthroughService.selectCimPathWalkthroughById(id));
    }

    /**
     * 新增路径漫游
     */
    @ApiOperation(value = "新增路径漫游",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增路径漫游")
    //@PreAuthorize("@ss.hasPermi('cimPathWalkthrough:cimPathWalkthrough:add')")
    @Log(title = "路径漫游", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimPathWalkthrough cimPathWalkthrough)
    {
        return AjaxResult.success("操作成功",cimPathWalkthroughService.insertCimPathWalkthrough(cimPathWalkthrough));
    }

    /**
     * 修改路径漫游
     */
    @ApiOperation(value = "修改路径漫游",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改路径漫游")
    //@PreAuthorize("@ss.hasPermi('cimPathWalkthrough:cimPathWalkthrough:edit')")
    @Log(title = "路径漫游", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimPathWalkthrough cimPathWalkthrough)
    {
        return AjaxResult.success("操作成功",cimPathWalkthroughService.updateCimPathWalkthrough(cimPathWalkthrough));
    }

    /**
     * 删除路径漫游
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除路径漫游",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除路径漫游")
    //@PreAuthorize("@ss.hasPermi('cimPathWalkthrough:cimPathWalkthrough:remove')")
    @Log(title = "路径漫游", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimPathWalkthroughService.deleteCimPathWalkthroughByIds(ids));
    }
}
