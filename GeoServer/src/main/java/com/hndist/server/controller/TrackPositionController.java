package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimTrackPosition;
import com.hndist.server.service.ICimTrackPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimTrackPosition
 * @Description: 轨迹位置Controller
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "轨迹位置")
@RestController
@RequestMapping("/cimTrackPosition")
public class TrackPositionController extends BaseController
{
    @Autowired
    private ICimTrackPositionService cimTrackPositionService;

    /**
     * 查询轨迹位置列表
     */

    @ApiOperation(value = "查询轨迹位置列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询轨迹位置列表")
    //@PreAuthorize("@ss.hasPermi('cimTrackPosition:cimTrackPosition:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimTrackPosition cimTrackPosition)
    {
        startPage();
        List<CimTrackPosition> list = cimTrackPositionService.selectCimTrackPositionList(cimTrackPosition);
        return getDataTable(list);
    }

    /**
     * 导出轨迹位置列表
     */
    @ApiOperation(value = "导出轨迹位置列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出轨迹位置列表")
    //@PreAuthorize("@ss.hasPermi('cimTrackPosition:cimTrackPosition:export')")
    @Log(title = "轨迹位置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimTrackPosition cimTrackPosition)
    {
        List<CimTrackPosition> list = cimTrackPositionService.selectCimTrackPositionList(cimTrackPosition);
        ExcelUtil<CimTrackPosition> util = new ExcelUtil<CimTrackPosition>(CimTrackPosition.class);
        util.exportExcel(response, list, "轨迹位置数据");
    }

    /**
     * 获取轨迹位置详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取轨迹位置详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取轨迹位置详细信息")
    //@PreAuthorize("@ss.hasPermi('cimTrackPosition:cimTrackPosition:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimTrackPositionService.selectCimTrackPositionById(id));
    }

    /**
     * 新增轨迹位置
     */
    @ApiOperation(value = "新增轨迹位置",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增轨迹位置")
    //@PreAuthorize("@ss.hasPermi('cimTrackPosition:cimTrackPosition:add')")
    @Log(title = "轨迹位置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimTrackPosition cimTrackPosition)
    {
        return AjaxResult.success("操作成功",cimTrackPositionService.insertCimTrackPosition(cimTrackPosition));
    }

    /**
     * 修改轨迹位置
     */
    @ApiOperation(value = "修改轨迹位置",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改轨迹位置")
    //@PreAuthorize("@ss.hasPermi('cimTrackPosition:cimTrackPosition:edit')")
    @Log(title = "轨迹位置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimTrackPosition cimTrackPosition)
    {
        return AjaxResult.success("操作成功",cimTrackPositionService.updateCimTrackPosition(cimTrackPosition));
    }

    /**
     * 删除轨迹位置
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除轨迹位置",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除轨迹位置")
    //@PreAuthorize("@ss.hasPermi('cimTrackPosition:cimTrackPosition:remove')")
    @Log(title = "轨迹位置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimTrackPositionService.deleteCimTrackPositionByIds(ids));
    }
}
