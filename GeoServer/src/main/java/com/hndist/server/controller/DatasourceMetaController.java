package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimDatasourceMeta;
import com.hndist.server.service.ICimDatasourceMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimDatasourceMeta
 * @Description: 元数据管理Controller
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "元数据管理")
@RestController
@RequestMapping("/cimDatasourceMeta")
public class DatasourceMetaController extends BaseController
{
    @Autowired
    private ICimDatasourceMetaService cimDatasourceMetaService;

    /**
     * 查询元数据管理列表
     */

    @ApiOperation(value = "查询元数据管理列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询元数据管理列表")
    //@PreAuthorize("@ss.hasPermi('cimDatasourceMeta:cimDatasourceMeta:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimDatasourceMeta cimDatasourceMeta)
    {
        startPage();
        List<CimDatasourceMeta> list = cimDatasourceMetaService.selectCimDatasourceMetaList(cimDatasourceMeta);
        return getDataTable(list);
    }

    /**
     * 导出元数据管理列表
     */
    @ApiOperation(value = "导出元数据管理列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出元数据管理列表")
    //@PreAuthorize("@ss.hasPermi('cimDatasourceMeta:cimDatasourceMeta:export')")
    @Log(title = "元数据管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimDatasourceMeta cimDatasourceMeta)
    {
        List<CimDatasourceMeta> list = cimDatasourceMetaService.selectCimDatasourceMetaList(cimDatasourceMeta);
        ExcelUtil<CimDatasourceMeta> util = new ExcelUtil<CimDatasourceMeta>(CimDatasourceMeta.class);
        util.exportExcel(response, list, "元数据管理数据");
    }

    /**
     * 获取元数据管理详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String",dataTypeClass = String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取元数据管理详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取元数据管理详细信息")
    //@PreAuthorize("@ss.hasPermi('cimDatasourceMeta:cimDatasourceMeta:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimDatasourceMetaService.selectCimDatasourceMetaById(id));
    }

    /**
     * 新增元数据管理
     */
    @ApiOperation(value = "新增元数据管理",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增元数据管理")
    //@PreAuthorize("@ss.hasPermi('cimDatasourceMeta:cimDatasourceMeta:add')")
    @Log(title = "元数据管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimDatasourceMeta cimDatasourceMeta)
    {
        return AjaxResult.success("操作成功",cimDatasourceMetaService.insertCimDatasourceMeta(cimDatasourceMeta));
    }

    /**
     * 修改元数据管理
     */
    @ApiOperation(value = "修改元数据管理",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改元数据管理")
    //@PreAuthorize("@ss.hasPermi('cimDatasourceMeta:cimDatasourceMeta:edit')")
    @Log(title = "元数据管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimDatasourceMeta cimDatasourceMeta)
    {
        return AjaxResult.success("操作成功",cimDatasourceMetaService.updateCimDatasourceMeta(cimDatasourceMeta));
    }

    /**
     * 删除元数据管理
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除元数据管理",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除元数据管理")
    //@PreAuthorize("@ss.hasPermi('cimDatasourceMeta:cimDatasourceMeta:remove')")
    @Log(title = "元数据管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimDatasourceMetaService.deleteCimDatasourceMetaByIds(ids));
    }
}
