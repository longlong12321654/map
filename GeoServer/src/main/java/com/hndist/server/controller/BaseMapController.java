package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimBaseMap;
import com.hndist.server.service.ICimBaseMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimBaseMap
 * @Description: 底图服务Controller
 * @author AI Builder
 * @date 2022-11-15 08:03:48
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "底图服务")
@RestController
@RequestMapping("/cimBaseMap")
public class BaseMapController extends BaseController
{
    @Autowired
    private ICimBaseMapService cimBaseMapService;

    /**
     * 查询底图服务列表
     */

    @ApiOperation(value = "查询底图服务列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询底图服务列表")
    //@PreAuthorize("@ss.hasPermi('cimBaseMap:cimBaseMap:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimBaseMap cimBaseMap)
    {
        startPage();
        List<CimBaseMap> list = cimBaseMapService.selectCimBaseMapList(cimBaseMap);
        return getDataTable(list);
    }

    /**
     * 导出底图服务列表
     */
    @ApiOperation(value = "导出底图服务列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出底图服务列表")
    //@PreAuthorize("@ss.hasPermi('cimBaseMap:cimBaseMap:export')")
    @Log(title = "底图服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimBaseMap cimBaseMap)
    {
        List<CimBaseMap> list = cimBaseMapService.selectCimBaseMapList(cimBaseMap);
        ExcelUtil<CimBaseMap> util = new ExcelUtil<CimBaseMap>(CimBaseMap.class);
        util.exportExcel(response, list, "底图服务数据");
    }

    /**
     * 获取底图服务详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取底图服务详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取底图服务详细信息")
    //@PreAuthorize("@ss.hasPermi('cimBaseMap:cimBaseMap:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimBaseMapService.selectCimBaseMapById(id));
    }

    /**
     * 新增底图服务
     */
    @ApiOperation(value = "新增底图服务",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增底图服务")
    //@PreAuthorize("@ss.hasPermi('cimBaseMap:cimBaseMap:add')")
    @Log(title = "底图服务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimBaseMap cimBaseMap)
    {
        return AjaxResult.success("操作成功",cimBaseMapService.insertCimBaseMap(cimBaseMap));
    }

    /**
     * 修改底图服务
     */
    @ApiOperation(value = "修改底图服务",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改底图服务")
    //@PreAuthorize("@ss.hasPermi('cimBaseMap:cimBaseMap:edit')")
    @Log(title = "底图服务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimBaseMap cimBaseMap)
    {
        return AjaxResult.success("操作成功",cimBaseMapService.updateCimBaseMap(cimBaseMap));
    }

    /**
     * 删除底图服务
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除底图服务",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除底图服务")
    //@PreAuthorize("@ss.hasPermi('cimBaseMap:cimBaseMap:remove')")
    @Log(title = "底图服务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimBaseMapService.deleteCimBaseMapByIds(ids));
    }
}
