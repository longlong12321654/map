package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimSpecial;
import com.hndist.server.service.ICimSpecialService;
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
 * @Title: CimSpecial
 * @Description: 专题图Controller
 * @author AI Builder
 * @date 2022-12-02 05:26:19
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "专题图")
@RestController
@RequestMapping("/cimSpecial")
public class SpecialController extends BaseController
{
    @Autowired
    private ICimSpecialService cimSpecialService;

    /**
     * 查询专题图列表
     */

    @ApiOperation(value = "查询专题图列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询专题图列表")
    //@PreAuthorize("@ss.hasPermi('cimSpecial:cimSpecial:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimSpecial cimSpecial)
    {
        startPage();
        List<CimSpecial> list = cimSpecialService.selectCimSpecialList(cimSpecial);
        return getDataTable(list);
    }

    /**
     * 导出专题图列表
     */
    @ApiOperation(value = "导出专题图列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出专题图列表")
    //@PreAuthorize("@ss.hasPermi('cimSpecial:cimSpecial:export')")
    @Log(title = "专题图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimSpecial cimSpecial)
    {
        List<CimSpecial> list = cimSpecialService.selectCimSpecialList(cimSpecial);
        ExcelUtil<CimSpecial> util = new ExcelUtil<CimSpecial>(CimSpecial.class);
        util.exportExcel(response, list, "专题图数据");
    }

    /**
     * 获取专题图详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取专题图详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取专题图详细信息")
    //@PreAuthorize("@ss.hasPermi('cimSpecial:cimSpecial:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimSpecialService.selectCimSpecialById(id));
    }

    /**
     * 新增专题图
     */
    @ApiOperation(value = "新增专题图",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增专题图")
    //@PreAuthorize("@ss.hasPermi('cimSpecial:cimSpecial:add')")
    @Log(title = "专题图", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimSpecial cimSpecial)
    {
        return AjaxResult.success("操作成功",cimSpecialService.insertCimSpecial(cimSpecial));
    }

    /**
     * 修改专题图
     */
    @ApiOperation(value = "修改专题图",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改专题图")
    //@PreAuthorize("@ss.hasPermi('cimSpecial:cimSpecial:edit')")
    @Log(title = "专题图", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimSpecial cimSpecial)
    {
        return AjaxResult.success("操作成功",cimSpecialService.updateCimSpecial(cimSpecial));
    }

    /**
     * 删除专题图
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除专题图",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除专题图")
    //@PreAuthorize("@ss.hasPermi('cimSpecial:cimSpecial:remove')")
    @Log(title = "专题图", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimSpecialService.deleteCimSpecialByIds(ids));
    }
    /**
     * 获取专题图树信息
     */
    @ApiOperation(value = "获取专题图树信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取专题图树信息")
    //@PreAuthorize("@ss.hasPermi('cimSpecial:cimSpecial:query')")
    @GetMapping(value = "/treeList")
    public AjaxResult treeList(String type)
    {
        List<CimSpecial> list = cimSpecialService.selectCimSpecialTreeList(type);
        return AjaxResult.success(list);
    }
}
