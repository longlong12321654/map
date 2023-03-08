package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimTrack;
import com.hndist.server.service.ICimTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimTrack
 * @Description: 轨迹Controller
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "轨迹")
@RestController
@RequestMapping("/cimTrack")
public class TrackController extends BaseController
{
    @Autowired
    private ICimTrackService cimTrackService;

    /**
     * 查询轨迹列表
     */

    @ApiOperation(value = "查询轨迹列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询轨迹列表")
    //@PreAuthorize("@ss.hasPermi('cimTrack:cimTrack:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimTrack cimTrack)
    {
        startPage();
        List<CimTrack> list = cimTrackService.selectCimTrackList(cimTrack);
        return getDataTable(list);
    }

    /**
     * 导出轨迹列表
     */
    @ApiOperation(value = "导出轨迹列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出轨迹列表")
    //@PreAuthorize("@ss.hasPermi('cimTrack:cimTrack:export')")
    @Log(title = "轨迹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimTrack cimTrack)
    {
        List<CimTrack> list = cimTrackService.selectCimTrackList(cimTrack);
        ExcelUtil<CimTrack> util = new ExcelUtil<CimTrack>(CimTrack.class);
        util.exportExcel(response, list, "轨迹数据");
    }

    /**
     * 获取轨迹详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取轨迹详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取轨迹详细信息")
    //@PreAuthorize("@ss.hasPermi('cimTrack:cimTrack:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimTrackService.selectCimTrackById(id));
    }

    /**
     * 新增轨迹
     */
    @ApiOperation(value = "新增轨迹",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增轨迹")
    //@PreAuthorize("@ss.hasPermi('cimTrack:cimTrack:add')")
    @Log(title = "轨迹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimTrack cimTrack)
    {
        return AjaxResult.success("操作成功",cimTrackService.insertCimTrack(cimTrack));
    }

    /**
     * 修改轨迹
     */
    @ApiOperation(value = "修改轨迹",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改轨迹")
    //@PreAuthorize("@ss.hasPermi('cimTrack:cimTrack:edit')")
    @Log(title = "轨迹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimTrack cimTrack)
    {
        return AjaxResult.success("操作成功",cimTrackService.updateCimTrack(cimTrack));
    }

    /**
     * 删除轨迹
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除轨迹",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除轨迹")
    //@PreAuthorize("@ss.hasPermi('cimTrack:cimTrack:remove')")
    @Log(title = "轨迹", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimTrackService.deleteCimTrackByIds(ids));
    }
    /**
     * 获取轨迹树信息
     */
    @ApiOperation(value = "获取轨迹树信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取轨迹树信息")
    //@PreAuthorize("@ss.hasPermi('cimTrack:cimTrack:query')")
    @GetMapping(value = "/treeList")
    public AjaxResult treeList(String type)
    {
        List<CimTrack> list = cimTrackService.selectCimTrackTreeList(type);
        return AjaxResult.success(list);
    }
}
