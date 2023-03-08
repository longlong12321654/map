package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimTask;
import com.hndist.server.service.ICimTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimTask
 * @Description: 任务管理Controller
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "任务管理")
@RestController
@RequestMapping("/cimTask")
public class TaskController extends BaseController
{
    @Autowired
    private ICimTaskService cimTaskService;

    /**
     * 查询任务管理列表
     */

    @ApiOperation(value = "查询任务管理列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询任务管理列表")
    //@PreAuthorize("@ss.hasPermi('cimTask:cimTask:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimTask cimTask)
    {
        startPage();
        List<CimTask> list = cimTaskService.selectCimTaskList(cimTask);
        return getDataTable(list);
    }

    /**
     * 导出任务管理列表
     */
    @ApiOperation(value = "导出任务管理列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出任务管理列表")
    //@PreAuthorize("@ss.hasPermi('cimTask:cimTask:export')")
    @Log(title = "任务管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimTask cimTask)
    {
        List<CimTask> list = cimTaskService.selectCimTaskList(cimTask);
        ExcelUtil<CimTask> util = new ExcelUtil<CimTask>(CimTask.class);
        util.exportExcel(response, list, "任务管理数据");
    }

    /**
     * 获取任务管理详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass = String.class,paramType = "path"),
    })
    @ApiOperation(value = "获取任务管理详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取任务管理详细信息")
    //@PreAuthorize("@ss.hasPermi('cimTask:cimTask:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimTaskService.selectCimTaskById(id));
    }

    /**
     * 新增任务管理
     */
    @ApiOperation(value = "新增任务管理",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增任务管理")
    //@PreAuthorize("@ss.hasPermi('cimTask:cimTask:add')")
    @Log(title = "任务管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimTask cimTask)
    {
        return AjaxResult.success("操作成功",cimTaskService.insertCimTask(cimTask));
    }

    /**
     * 修改任务管理
     */
    @ApiOperation(value = "修改任务管理",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改任务管理")
    //@PreAuthorize("@ss.hasPermi('cimTask:cimTask:edit')")
    @Log(title = "任务管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimTask cimTask)
    {
        return AjaxResult.success("操作成功",cimTaskService.updateCimTask(cimTask));
    }

    /**
     * 删除任务管理
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除任务管理",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除任务管理")
    //@PreAuthorize("@ss.hasPermi('cimTask:cimTask:remove')")
    @Log(title = "任务管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimTaskService.deleteCimTaskByIds(ids));
    }
}
