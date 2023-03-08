package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.StringUtils;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimFiles;
import com.hndist.server.service.ICimFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimFiles
 * @Description: 地图服务Controller
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "地图服务")
@RestController
@RequestMapping("/cimFiles")
public class FilesController extends BaseController
{
    @Autowired
    private ICimFilesService cimFilesService;

    /**
     * 查询地图服务列表
     */

    @ApiOperation(value = "查询地图服务列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询地图服务列表")
    //@PreAuthorize("@ss.hasPermi('cimFiles:cimFiles:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimFiles cimFiles)
    {
        startPage();
        List<CimFiles> list = cimFilesService.selectCimFilesList(cimFiles);
        return getDataTable(list);
    }

    /**
     * 导出地图服务列表
     */
    @ApiOperation(value = "导出地图服务列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出地图服务列表")
    //@PreAuthorize("@ss.hasPermi('cimFiles:cimFiles:export')")
    @Log(title = "地图服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimFiles cimFiles)
    {
        List<CimFiles> list = cimFilesService.selectCimFilesList(cimFiles);
        ExcelUtil<CimFiles> util = new ExcelUtil<CimFiles>(CimFiles.class);
        util.exportExcel(response, list, "地图服务数据");
    }

    /**
     * 获取地图服务详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取地图服务详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取地图服务详细信息")
    //@PreAuthorize("@ss.hasPermi('cimFiles:cimFiles:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimFilesService.selectCimFilesById(id));
    }

    /**
     * 新增地图服务
     */
    @ApiOperation(value = "新增地图服务",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增地图服务")
    //@PreAuthorize("@ss.hasPermi('cimFiles:cimFiles:add')")
    @Log(title = "地图服务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimFiles cimFiles)
    {
        cimFilesService.insertCimFiles(cimFiles);
        return AjaxResult.success("操作成功");
    }

    /**
     * 新增地图服务和元数据
     */
    @ApiOperation(value = "新增地图服务",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增地图服务")
    //@PreAuthorize("@ss.hasPermi('cimFiles:cimFiles:add')")
    @Log(title = "地图服务", businessType = BusinessType.INSERT)
    @PostMapping("/addFileAndMeta")
    public AjaxResult addFileAndMeta(@RequestBody CimFiles cimFiles)
    {
        if (StringUtils.isEmpty(cimFiles.getFilePath())) {
            return AjaxResult.error("filePath参数不能为空！");
        }
        cimFilesService.insertCimFiles(cimFiles);
        return AjaxResult.success("操作成功");
    }

    /**
     * 修改地图服务
     */
    @ApiOperation(value = "修改地图服务",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改地图服务")
    //@PreAuthorize("@ss.hasPermi('cimFiles:cimFiles:edit')")
    @Log(title = "地图服务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimFiles cimFiles)
    {
        return AjaxResult.success("操作成功",cimFilesService.updateCimFiles(cimFiles));
    }

    /**
     * 删除地图服务
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除地图服务",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除地图服务")
    //@PreAuthorize("@ss.hasPermi('cimFiles:cimFiles:remove')")
    @Log(title = "地图服务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimFilesService.deleteCimFilesByIds(ids));
    }
    /**
     * 获取地图服务树信息
     */
    @ApiOperation(value = "获取地图服务树信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取地图服务树信息")
    //@PreAuthorize("@ss.hasPermi('cimFiles:cimFiles:query')")
    @GetMapping(value = "/treeList")
    public AjaxResult treeList(String type)
    {
        List<CimFiles> list = cimFilesService.selectCimFilesTreeList(type);
        return AjaxResult.success(list);
    }
}
