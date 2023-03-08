package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimPanorama;
import com.hndist.server.service.ICimPanoramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimPanorama
 * @Description: 360全景信息Controller
 * @author AI Builder
 * @date 2022-11-15 08:37:58
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "360全景信息")
@RestController
@RequestMapping("/cimPanorama")
public class PanoramaController extends BaseController
{
    @Autowired
    private ICimPanoramaService cimPanoramaService;

    /**
     * 查询360全景信息列表
     */

    @ApiOperation(value = "查询360全景信息列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询360全景信息列表")
    //@PreAuthorize("@ss.hasPermi('cimPanorama:cimPanorama:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimPanorama cimPanorama)
    {
        startPage();
        List<CimPanorama> list = cimPanoramaService.selectCimPanoramaList(cimPanorama);
        return getDataTable(list);
    }

    /**
     * 导出360全景信息列表
     */
    @ApiOperation(value = "导出360全景信息列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出360全景信息列表")
    //@PreAuthorize("@ss.hasPermi('cimPanorama:cimPanorama:export')")
    @Log(title = "360全景信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimPanorama cimPanorama)
    {
        List<CimPanorama> list = cimPanoramaService.selectCimPanoramaList(cimPanorama);
        ExcelUtil<CimPanorama> util = new ExcelUtil<CimPanorama>(CimPanorama.class);
        util.exportExcel(response, list, "360全景信息数据");
    }

    /**
     * 获取360全景信息详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取360全景信息详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取360全景信息详细信息")
    //@PreAuthorize("@ss.hasPermi('cimPanorama:cimPanorama:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimPanoramaService.selectCimPanoramaById(id));
    }

    /**
     * 新增360全景信息
     */
    @ApiOperation(value = "新增360全景信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增360全景信息")
    //@PreAuthorize("@ss.hasPermi('cimPanorama:cimPanorama:add')")
    @Log(title = "360全景信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimPanorama cimPanorama)
    {
        return AjaxResult.success("操作成功",cimPanoramaService.insertCimPanorama(cimPanorama));
    }

    /**
     * 修改360全景信息
     */
    @ApiOperation(value = "修改360全景信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改360全景信息")
    //@PreAuthorize("@ss.hasPermi('cimPanorama:cimPanorama:edit')")
    @Log(title = "360全景信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimPanorama cimPanorama)
    {
        return AjaxResult.success("操作成功",cimPanoramaService.updateCimPanorama(cimPanorama));
    }

    /**
     * 删除360全景信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除360全景信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除360全景信息")
    //@PreAuthorize("@ss.hasPermi('cimPanorama:cimPanorama:remove')")
    @Log(title = "360全景信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimPanoramaService.deleteCimPanoramaByIds(ids));
    }
    /**
     * 获取360全景信息树信息
     */
    @ApiOperation(value = "获取360全景信息树信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取360全景信息树信息")
    //@PreAuthorize("@ss.hasPermi('cimPanorama:cimPanorama:query')")
    @GetMapping(value = "/treeList")
    public AjaxResult treeList(String type)
    {
        List<CimPanorama> list = cimPanoramaService.selectCimPanoramaTreeList(type);
        return AjaxResult.success(list);
    }
}
