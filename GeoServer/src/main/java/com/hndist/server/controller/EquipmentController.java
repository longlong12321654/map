package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimEquipment;
import com.hndist.server.service.ICimEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimEquipment
 * @Description: 设备信息Controller
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "设备信息")
@RestController
@RequestMapping("/cimEquipment")
public class EquipmentController extends BaseController
{
    @Autowired
    private ICimEquipmentService cimEquipmentService;

    /**
     * 查询设备信息列表
     */

    @ApiOperation(value = "查询设备信息列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询设备信息列表")
    //@PreAuthorize("@ss.hasPermi('cimEquipment:cimEquipment:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimEquipment cimEquipment)
    {
        startPage();
        List<CimEquipment> list = cimEquipmentService.selectCimEquipmentList(cimEquipment);
        return getDataTable(list);
    }

    /**
     * 导出设备信息列表
     */
    @ApiOperation(value = "导出设备信息列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出设备信息列表")
    //@PreAuthorize("@ss.hasPermi('cimEquipment:cimEquipment:export')")
    @Log(title = "设备信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimEquipment cimEquipment)
    {
        List<CimEquipment> list = cimEquipmentService.selectCimEquipmentList(cimEquipment);
        ExcelUtil<CimEquipment> util = new ExcelUtil<CimEquipment>(CimEquipment.class);
        util.exportExcel(response, list, "设备信息数据");
    }

    /**
     * 获取设备信息详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass = String.class,paramType = "path"),
    })
    @ApiOperation(value = "获取设备信息详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取设备信息详细信息")
    //@PreAuthorize("@ss.hasPermi('cimEquipment:cimEquipment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimEquipmentService.selectCimEquipmentById(id));
    }

    /**
     * 新增设备信息
     */
    @ApiOperation(value = "新增设备信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增设备信息")
    //@PreAuthorize("@ss.hasPermi('cimEquipment:cimEquipment:add')")
    @Log(title = "设备信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimEquipment cimEquipment)
    {
        return AjaxResult.success("操作成功",cimEquipmentService.insertCimEquipment(cimEquipment));
    }

    /**
     * 修改设备信息
     */
    @ApiOperation(value = "修改设备信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改设备信息")
    //@PreAuthorize("@ss.hasPermi('cimEquipment:cimEquipment:edit')")
    @Log(title = "设备信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimEquipment cimEquipment)
    {
        return AjaxResult.success("操作成功",cimEquipmentService.updateCimEquipment(cimEquipment));
    }

    /**
     * 删除设备信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除设备信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除设备信息")
    //@PreAuthorize("@ss.hasPermi('cimEquipment:cimEquipment:remove')")
    @Log(title = "设备信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimEquipmentService.deleteCimEquipmentByIds(ids));
    }
}
