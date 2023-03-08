package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimDevice;
import com.hndist.server.service.ICimDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimDevice
 * @Description: 传感器Controller
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "传感器")
@RestController
@RequestMapping("/cimDevice")
public class DeviceController extends BaseController
{
    @Autowired
    private ICimDeviceService cimDeviceService;

    /**
     * 查询传感器列表
     */

    @ApiOperation(value = "查询传感器列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询传感器列表")
    //@PreAuthorize("@ss.hasPermi('cimDevice:cimDevice:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimDevice cimDevice)
    {
        startPage();
        List<CimDevice> list = cimDeviceService.selectCimDeviceList(cimDevice);
        return getDataTable(list);
    }

    /**
     * 导出传感器列表
     */
    @ApiOperation(value = "导出传感器列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出传感器列表")
    //@PreAuthorize("@ss.hasPermi('cimDevice:cimDevice:export')")
    @Log(title = "传感器", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimDevice cimDevice)
    {
        List<CimDevice> list = cimDeviceService.selectCimDeviceList(cimDevice);
        ExcelUtil<CimDevice> util = new ExcelUtil<CimDevice>(CimDevice.class);
        util.exportExcel(response, list, "传感器数据");
    }

    /**
     * 获取传感器详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取传感器详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取传感器详细信息")
    //@PreAuthorize("@ss.hasPermi('cimDevice:cimDevice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimDeviceService.selectCimDeviceById(id));
    }

    /**
     * 新增传感器
     */
    @ApiOperation(value = "新增传感器",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增传感器")
    //@PreAuthorize("@ss.hasPermi('cimDevice:cimDevice:add')")
    @Log(title = "传感器", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimDevice cimDevice)
    {
        return AjaxResult.success("操作成功",cimDeviceService.insertCimDevice(cimDevice));
    }

    /**
     * 修改传感器
     */
    @ApiOperation(value = "修改传感器",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改传感器")
    //@PreAuthorize("@ss.hasPermi('cimDevice:cimDevice:edit')")
    @Log(title = "传感器", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimDevice cimDevice)
    {
        return AjaxResult.success("操作成功",cimDeviceService.updateCimDevice(cimDevice));
    }

    /**
     * 删除传感器
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除传感器",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除传感器")
    //@PreAuthorize("@ss.hasPermi('cimDevice:cimDevice:remove')")
    @Log(title = "传感器", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimDeviceService.deleteCimDeviceByIds(ids));
    }
    /**
     * 获取传感器树信息
     */
    @ApiOperation(value = "获取传感器树信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取传感器树信息")
    //@PreAuthorize("@ss.hasPermi('cimDevice:cimDevice:query')")
    @GetMapping(value = "/treeList")
    public AjaxResult treeList(String type)
    {
        List<CimDevice> list = cimDeviceService.selectCimDeviceTreeList(type);
        return AjaxResult.success(list);
    }
}
