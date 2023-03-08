package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimMapsymbol;
import com.hndist.server.service.ICimMapsymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * @Title: CimMapsymbol
 * @Description: 符号库Controller
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "符号库")
@RestController
@RequestMapping("/cimMapsymbol")
public class MapsymbolController extends BaseController
{
    @Autowired
    private ICimMapsymbolService cimMapsymbolService;

    /**
     * 查询符号库列表
     */

    @ApiOperation(value = "查询符号库列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询符号库列表")
    //@PreAuthorize("@ss.hasPermi('cimMapsymbol:cimMapsymbol:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimMapsymbol cimMapsymbol)
    {
        startPage();
        List<CimMapsymbol> list = cimMapsymbolService.selectCimMapsymbolList(cimMapsymbol);
        return getDataTable(list);
    }

    /**
     * 导出符号库列表
     */
    @ApiOperation(value = "导出符号库列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出符号库列表")
    //@PreAuthorize("@ss.hasPermi('cimMapsymbol:cimMapsymbol:export')")
    @Log(title = "符号库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimMapsymbol cimMapsymbol)
    {
        List<CimMapsymbol> list = cimMapsymbolService.selectCimMapsymbolList(cimMapsymbol);
        ExcelUtil<CimMapsymbol> util = new ExcelUtil<CimMapsymbol>(CimMapsymbol.class);
        util.exportExcel(response, list, "符号库数据");
    }


    /**
     * 导入符号库
     */
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        if (Objects.isNull(file)) {
            return AjaxResult.error("请上传Excel数据");
        }
        ExcelUtil<CimMapsymbol> util = new ExcelUtil<>(CimMapsymbol.class);
        List<CimMapsymbol> cimMapsymbolList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = cimMapsymbolService.importUser(cimMapsymbolList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    /**
     * 获取符号库详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass = String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取符号库详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取符号库详细信息")
    //@PreAuthorize("@ss.hasPermi('cimMapsymbol:cimMapsymbol:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimMapsymbolService.selectCimMapsymbolById(id));
    }

    /**
     * 新增符号库
     */
    @ApiOperation(value = "新增符号库",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增符号库")
    //@PreAuthorize("@ss.hasPermi('cimMapsymbol:cimMapsymbol:add')")
    @Log(title = "符号库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimMapsymbol cimMapsymbol)
    {
        return AjaxResult.success("操作成功",cimMapsymbolService.insertCimMapsymbol(cimMapsymbol));
    }

    /**
     * 修改符号库
     */
    @ApiOperation(value = "修改符号库",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改符号库")
    //@PreAuthorize("@ss.hasPermi('cimMapsymbol:cimMapsymbol:edit')")
    @Log(title = "符号库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimMapsymbol cimMapsymbol)
    {
        return AjaxResult.success("操作成功",cimMapsymbolService.updateCimMapsymbol(cimMapsymbol));
    }

    /**
     * 删除符号库
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除符号库",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除符号库")
    //@PreAuthorize("@ss.hasPermi('cimMapsymbol:cimMapsymbol:remove')")
    @Log(title = "符号库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimMapsymbolService.deleteCimMapsymbolByIds(ids));
    }
    /**
     * 获取符号库树信息
     */
    @ApiOperation(value = "获取符号库树信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取符号库树信息")
    //@PreAuthorize("@ss.hasPermi('cimMapsymbol:cimMapsymbol:query')")
    @GetMapping(value = "/treeList")
    public AjaxResult treeList(String type)
    {
        List<CimMapsymbol> list = cimMapsymbolService.selectCimMapsymbolTreeList(type);
        return AjaxResult.success(list);
    }
}
