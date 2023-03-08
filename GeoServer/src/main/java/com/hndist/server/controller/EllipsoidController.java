package com.hndist.server.controller;
import com.hndist.server.domain.CimEllipsoid;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.service.ICimEllipsoidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimEllipsoid
 * @Description: 椭球体Controller
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "椭球体")
@RestController
@RequestMapping("/cimEllipsoid")
public class EllipsoidController extends BaseController
{
    @Autowired
    private ICimEllipsoidService baseEllipsoidService;

    /**
     * 查询椭球体列表
     */

    @ApiOperation(value = "查询椭球体列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询椭球体列表")
    //@PreAuthorize("@ss.hasPermi('cimEllipsoid:cimEllipsoid:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimEllipsoid cimEllipsoid)
    {
        startPage();
        List<CimEllipsoid> list = baseEllipsoidService.selectBaseEllipsoidList(cimEllipsoid);
        return getDataTable(list);
    }

    /**
     * 导出椭球体列表
     */
    @ApiOperation(value = "导出椭球体列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出椭球体列表")
    //@PreAuthorize("@ss.hasPermi('cimEllipsoid:cimEllipsoid:export')")
    @Log(title = "椭球体", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimEllipsoid cimEllipsoid)
    {
        List<CimEllipsoid> list = baseEllipsoidService.selectBaseEllipsoidList(cimEllipsoid);
        ExcelUtil<CimEllipsoid> util = new ExcelUtil<CimEllipsoid>(CimEllipsoid.class);
        util.exportExcel(response, list, "椭球体数据");
    }

    /**
     * 获取椭球体详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass = String.class,paramType = "path"),
    })
    @ApiOperation(value = "获取椭球体详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取椭球体详细信息")
    //@PreAuthorize("@ss.hasPermi('baseEllipsoid:baseEllipsoid:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseEllipsoidService.selectBaseEllipsoidById(id));
    }

    /**
     * 新增椭球体
     */
    @ApiOperation(value = "新增椭球体",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增椭球体")
    //@PreAuthorize("@ss.hasPermi('cimEllipsoid:cimEllipsoid:add')")
    @Log(title = "椭球体", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimEllipsoid cimEllipsoid)
    {
        return AjaxResult.success("操作成功",baseEllipsoidService.insertBaseEllipsoid(cimEllipsoid));
    }

    /**
     * 修改椭球体
     */
    @ApiOperation(value = "修改椭球体",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改椭球体")
    //@PreAuthorize("@ss.hasPermi('cimEllipsoid:cimEllipsoid:edit')")
    @Log(title = "椭球体", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimEllipsoid cimEllipsoid)
    {
        return AjaxResult.success("操作成功",baseEllipsoidService.updateBaseEllipsoid(cimEllipsoid));
    }

    /**
     * 删除椭球体
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除椭球体",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除椭球体")
    //@PreAuthorize("@ss.hasPermi('baseEllipsoid:baseEllipsoid:remove')")
    @Log(title = "椭球体", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseEllipsoidService.deleteBaseEllipsoidByIds(ids));
    }
}
