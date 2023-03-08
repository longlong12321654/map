package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimMapproject;
import com.hndist.server.service.ICimMapprojectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimMapproject
 * @Description: 地图工程Controller
 * @author AI Builder
 * @date 2022-11-14 03:18:41
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "地图工程")
@RestController
@RequestMapping("/cimMapproject")
public class MapprojectController extends BaseController
{
    @Autowired
    private ICimMapprojectService cimMapprojectService;

    /**
     * 查询地图工程列表
     */

    @ApiOperation(value = "查询地图工程列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询地图工程列表")
    //@PreAuthorize("@ss.hasPermi('cimMapproject:cimMapproject:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimMapproject cimMapproject)
    {
        startPage();
        List<CimMapproject> list = cimMapprojectService.selectCimMapprojectList(cimMapproject);
        return getDataTable(list);
    }

    /**
     * 导出地图工程列表
     */
    @ApiOperation(value = "导出地图工程列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出地图工程列表")
    //@PreAuthorize("@ss.hasPermi('cimMapproject:cimMapproject:export')")
    @Log(title = "地图工程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimMapproject cimMapproject)
    {
        List<CimMapproject> list = cimMapprojectService.selectCimMapprojectList(cimMapproject);
        ExcelUtil<CimMapproject> util = new ExcelUtil<CimMapproject>(CimMapproject.class);
        util.exportExcel(response, list, "地图工程数据");
    }

    /**
     * 获取地图工程详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取地图工程详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取地图工程详细信息")
    //@PreAuthorize("@ss.hasPermi('cimMapproject:cimMapproject:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimMapprojectService.selectCimMapprojectById(id));
    }

    /**
     * 新增地图工程
     */
    @ApiOperation(value = "新增地图工程",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增地图工程")
    //@PreAuthorize("@ss.hasPermi('cimMapproject:cimMapproject:add')")
    @Log(title = "地图工程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimMapproject cimMapproject)
    {
        return AjaxResult.success("操作成功",cimMapprojectService.insertCimMapproject(cimMapproject));
    }

    /**
     * 修改地图工程
     */
    @ApiOperation(value = "修改地图工程",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改地图工程")
    //@PreAuthorize("@ss.hasPermi('cimMapproject:cimMapproject:edit')")
    @Log(title = "地图工程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimMapproject cimMapproject)
    {
        return AjaxResult.success("操作成功",cimMapprojectService.updateCimMapproject(cimMapproject));
    }

    /**
     * 删除地图工程
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除地图工程",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除地图工程")
    //@PreAuthorize("@ss.hasPermi('cimMapproject:cimMapproject:remove')")
    @Log(title = "地图工程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimMapprojectService.deleteCimMapprojectByIds(ids));
    }
    /**
     * 获取地图工程树信息
     */
    @ApiOperation(value = "获取地图工程树信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取地图工程树信息")
    //@PreAuthorize("@ss.hasPermi('cimMapproject:cimMapproject:query')")
    @GetMapping(value = "/treeList")
    public AjaxResult treeList(String type)
    {
        List<CimMapproject> list = cimMapprojectService.selectCimMapprojectTreeList(type);
        return AjaxResult.success(list);
    }
}
