package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimMapmark;
import com.hndist.server.service.ICimMapmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimMapmark
 * @Description: 标绘&标注管理Controller
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "标绘&标注管理")
@RestController
@RequestMapping("/cimMapmark")
public class MapmarkController extends BaseController
{
    @Autowired
    private ICimMapmarkService cimMapmarkService;

    /**
     * 查询标绘&标注管理列表
     */

    @ApiOperation(value = "查询标绘&标注管理列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询标绘&标注管理列表")
    //@PreAuthorize("@ss.hasPermi('cimMapmark:cimMapmark:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimMapmark cimMapmark)
    {
        startPage();
        List<CimMapmark> list = cimMapmarkService.selectCimMapmarkList(cimMapmark);
        return getDataTable(list);
    }

    /**
     * 导出标绘&标注管理列表
     */
    @ApiOperation(value = "导出标绘&标注管理列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出标绘&标注管理列表")
    //@PreAuthorize("@ss.hasPermi('cimMapmark:cimMapmark:export')")
    @Log(title = "标绘&标注管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimMapmark cimMapmark)
    {
        List<CimMapmark> list = cimMapmarkService.selectCimMapmarkList(cimMapmark);
        ExcelUtil<CimMapmark> util = new ExcelUtil<CimMapmark>(CimMapmark.class);
        util.exportExcel(response, list, "标绘&标注管理数据");
    }

    /**
     * 获取标绘&标注管理详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取标绘&标注管理详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取标绘&标注管理详细信息")
    //@PreAuthorize("@ss.hasPermi('cimMapmark:cimMapmark:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimMapmarkService.selectCimMapmarkById(id));
    }

    /**
     * 新增标绘&标注管理
     */
    @ApiOperation(value = "新增标绘&标注管理",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增标绘&标注管理")
    //@PreAuthorize("@ss.hasPermi('cimMapmark:cimMapmark:add')")
    @Log(title = "标绘&标注管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimMapmark cimMapmark)
    {
        return AjaxResult.success("操作成功",cimMapmarkService.insertCimMapmark(cimMapmark));
    }

    /**
     * 修改标绘&标注管理
     */
    @ApiOperation(value = "修改标绘&标注管理",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改标绘&标注管理")
    //@PreAuthorize("@ss.hasPermi('cimMapmark:cimMapmark:edit')")
    @Log(title = "标绘&标注管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimMapmark cimMapmark)
    {
        return AjaxResult.success("操作成功",cimMapmarkService.updateCimMapmark(cimMapmark));
    }

    /**
     * 删除标绘&标注管理
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除标绘&标注管理",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除标绘&标注管理")
    //@PreAuthorize("@ss.hasPermi('cimMapmark:cimMapmark:remove')")
    @Log(title = "标绘&标注管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimMapmarkService.deleteCimMapmarkByIds(ids));
    }
}
