package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimOnemapSetting;
import com.hndist.server.service.ICimOnemapSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimOnemapSetting
 * @Description: 一张图配置Controller
 * @author AI Builder
 * @date 2022-11-16 02:52:39
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "一张图配置")
@RestController
@RequestMapping("/cimOnemapSetting")
public class OnemapSettingController extends BaseController
{
    @Autowired
    private ICimOnemapSettingService cimOnemapSettingService;

    /**
     * 查询一张图配置列表
     */

    @ApiOperation(value = "查询一张图配置列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询一张图配置列表")
    //@PreAuthorize("@ss.hasPermi('cimOnemapSetting:cimOnemapSetting:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimOnemapSetting cimOnemapSetting)
    {
        startPage();
        List<CimOnemapSetting> list = cimOnemapSettingService.selectCimOnemapSettingList(cimOnemapSetting);
        return getDataTable(list);
    }

    /**
     * 导出一张图配置列表
     */
    @ApiOperation(value = "导出一张图配置列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出一张图配置列表")
    //@PreAuthorize("@ss.hasPermi('cimOnemapSetting:cimOnemapSetting:export')")
    @Log(title = "一张图配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimOnemapSetting cimOnemapSetting)
    {
        List<CimOnemapSetting> list = cimOnemapSettingService.selectCimOnemapSettingList(cimOnemapSetting);
        ExcelUtil<CimOnemapSetting> util = new ExcelUtil<CimOnemapSetting>(CimOnemapSetting.class);
        util.exportExcel(response, list, "一张图配置数据");
    }

    /**
     * 获取一张图配置详细信息
     */
    @ApiOperation(value = "获取一张图配置详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取一张图配置详细信息")
    //@PreAuthorize("@ss.hasPermi('cimOnemapSetting:cimOnemapSetting:query')")
    @GetMapping(value = "/info")
    public AjaxResult getInfo()
    {
        return AjaxResult.success(cimOnemapSettingService.selectCimOnemapSettingList(new CimOnemapSetting()).get(0));
    }

    /**
     * 新增一张图配置
     */
    @ApiOperation(value = "新增一张图配置",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增一张图配置")
    //@PreAuthorize("@ss.hasPermi('cimOnemapSetting:cimOnemapSetting:add')")
    @Log(title = "一张图配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimOnemapSetting cimOnemapSetting)
    {
        return AjaxResult.success("操作成功",cimOnemapSettingService.insertCimOnemapSetting(cimOnemapSetting));
    }

    /**
     * 修改一张图配置
     */
    @ApiOperation(value = "修改一张图配置",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改一张图配置")
    //@PreAuthorize("@ss.hasPermi('cimOnemapSetting:cimOnemapSetting:edit')")
    @Log(title = "一张图配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimOnemapSetting cimOnemapSetting)
    {
        return AjaxResult.success("操作成功",cimOnemapSettingService.updateCimOnemapSetting(cimOnemapSetting));
    }

    /**
     * 删除一张图配置
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除一张图配置",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除一张图配置")
    //@PreAuthorize("@ss.hasPermi('cimOnemapSetting:cimOnemapSetting:remove')")
    @Log(title = "一张图配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimOnemapSettingService.deleteCimOnemapSettingByIds(ids));
    }
}
