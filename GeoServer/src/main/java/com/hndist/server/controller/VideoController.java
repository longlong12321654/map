package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimVideo;
import com.hndist.server.service.ICimVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimVideo
 * @Description: cim_videoController
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "cim_video")
@RestController
@RequestMapping("/cimVideo")
public class VideoController extends BaseController
{
    @Autowired
    private ICimVideoService cimVideoService;

    /**
     * 查询cim_video列表
     */

    @ApiOperation(value = "查询cim_video列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询cim_video列表")
    //@PreAuthorize("@ss.hasPermi('cimVideo:cimVideo:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimVideo cimVideo)
    {
        startPage();
        List<CimVideo> list = cimVideoService.selectCimVideoList(cimVideo);
        return getDataTable(list);
    }

    /**
     * 导出cim_video列表
     */
    @ApiOperation(value = "导出cim_video列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出cim_video列表")
    //@PreAuthorize("@ss.hasPermi('cimVideo:cimVideo:export')")
    @Log(title = "cim_video", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimVideo cimVideo)
    {
        List<CimVideo> list = cimVideoService.selectCimVideoList(cimVideo);
        ExcelUtil<CimVideo> util = new ExcelUtil<CimVideo>(CimVideo.class);
        util.exportExcel(response, list, "cim_video数据");
    }

    /**
     * 获取cim_video详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass = String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取cim_video详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取cim_video详细信息")
    //@PreAuthorize("@ss.hasPermi('cimVideo:cimVideo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimVideoService.selectCimVideoById(id));
    }

    /**
     * 新增cim_video
     */
    @ApiOperation(value = "新增cim_video",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增cim_video")
    //@PreAuthorize("@ss.hasPermi('cimVideo:cimVideo:add')")
    @Log(title = "cim_video", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimVideo cimVideo)
    {
        return AjaxResult.success("操作成功",cimVideoService.insertCimVideo(cimVideo));
    }

    /**
     * 修改cim_video
     */
    @ApiOperation(value = "修改cim_video",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改cim_video")
    //@PreAuthorize("@ss.hasPermi('cimVideo:cimVideo:edit')")
    @Log(title = "cim_video", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimVideo cimVideo)
    {
        return AjaxResult.success("操作成功",cimVideoService.updateCimVideo(cimVideo));
    }

    /**
     * 删除cim_video
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除cim_video",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除cim_video")
    //@PreAuthorize("@ss.hasPermi('cimVideo:cimVideo:remove')")
    @Log(title = "cim_video", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimVideoService.deleteCimVideoByIds(ids));
    }
}
