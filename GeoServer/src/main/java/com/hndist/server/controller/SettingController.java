package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimSetting;
import com.hndist.server.service.ICimSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimSetting
 * @Description: 基础配置Controller
 * @author AI Builder
 * @date 2022-11-16 02:52:39
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "基础配置")
@RestController
@RequestMapping("/cimSetting")
public class SettingController extends BaseController
{
    @Autowired
    private ICimSettingService cimSettingService;

    /**
     * 查询基础配置列表
     */

    @ApiOperation(value = "查询基础配置列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询基础配置列表")
    //@PreAuthorize("@ss.hasPermi('cimSetting:cimSetting:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimSetting cimSetting)
    {
        startPage();
        List<CimSetting> list = cimSettingService.selectCimSettingList(cimSetting);
        return getDataTable(list);
    }

    /**
     * 导出基础配置列表
     */
    @ApiOperation(value = "导出基础配置列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出基础配置列表")
    //@PreAuthorize("@ss.hasPermi('cimSetting:cimSetting:export')")
    @Log(title = "基础配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimSetting cimSetting)
    {
        List<CimSetting> list = cimSettingService.selectCimSettingList(cimSetting);
        ExcelUtil<CimSetting> util = new ExcelUtil<CimSetting>(CimSetting.class);
        util.exportExcel(response, list, "基础配置数据");
    }

    /**
     * 获取基础配置详细信息
     */
    @ApiOperation(value = "获取基础配置详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取基础配置详细信息")
    //@PreAuthorize("@ss.hasPermi('cimSetting:cimSetting:query')")
    @GetMapping(value = "/info")
    public AjaxResult getInfo()
    {
        return AjaxResult.success(cimSettingService.selectCimSettingList(new CimSetting()).get(0));
    }

    /**
     * 新增基础配置
     */
    @ApiOperation(value = "新增基础配置",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增基础配置")
    //@PreAuthorize("@ss.hasPermi('cimSetting:cimSetting:add')")
    @Log(title = "基础配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimSetting cimSetting)
    {
        return AjaxResult.success("操作成功",cimSettingService.insertCimSetting(cimSetting));
    }

    /**
     * 修改基础配置
     */
    @ApiOperation(value = "修改基础配置",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改基础配置")
    //@PreAuthorize("@ss.hasPermi('cimSetting:cimSetting:edit')")
    @Log(title = "基础配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimSetting cimSetting)
    {
        return AjaxResult.success("操作成功",cimSettingService.updateCimSetting(cimSetting));
    }

    /**
     * 删除基础配置
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除基础配置",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除基础配置")
    //@PreAuthorize("@ss.hasPermi('cimSetting:cimSetting:remove')")
    @Log(title = "基础配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimSettingService.deleteCimSettingByIds(ids));
    }
}
