package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimCamera;
import com.hndist.server.service.ICimCameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimCamera
 * @Description: 摄像头Controller
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "摄像头")
@RestController
@RequestMapping("/cimCamera")
public class CameraController extends BaseController
{
    @Autowired
    private ICimCameraService cimCameraService;

    /**
     * 查询摄像头列表
     */

    @ApiOperation(value = "查询摄像头列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询摄像头列表")
    //@PreAuthorize("@ss.hasPermi('cimCamera:cimCamera:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimCamera cimCamera)
    {
        startPage();
        List<CimCamera> list = cimCameraService.selectCimCameraList(cimCamera);
        return getDataTable(list);
    }

    /**
     * 导出摄像头列表
     */
    @ApiOperation(value = "导出摄像头列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出摄像头列表")
    //@PreAuthorize("@ss.hasPermi('cimCamera:cimCamera:export')")
    @Log(title = "摄像头", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimCamera cimCamera)
    {
        List<CimCamera> list = cimCameraService.selectCimCameraList(cimCamera);
        ExcelUtil<CimCamera> util = new ExcelUtil<CimCamera>(CimCamera.class);
        util.exportExcel(response, list, "摄像头数据");
    }

    /**
     * 获取摄像头详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取摄像头详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取摄像头详细信息")
    //@PreAuthorize("@ss.hasPermi('cimCamera:cimCamera:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimCameraService.selectCimCameraById(id));
    }

    /**
     * 新增摄像头
     */
    @ApiOperation(value = "新增摄像头",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增摄像头")
    //@PreAuthorize("@ss.hasPermi('cimCamera:cimCamera:add')")
    @Log(title = "摄像头", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimCamera cimCamera)
    {
        return AjaxResult.success("操作成功",cimCameraService.insertCimCamera(cimCamera));
    }

    /**
     * 修改摄像头
     */
    @ApiOperation(value = "修改摄像头",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改摄像头")
    //@PreAuthorize("@ss.hasPermi('cimCamera:cimCamera:edit')")
    @Log(title = "摄像头", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimCamera cimCamera)
    {
        return AjaxResult.success("操作成功",cimCameraService.updateCimCamera(cimCamera));
    }

    /**
     * 删除摄像头
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除摄像头",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除摄像头")
    //@PreAuthorize("@ss.hasPermi('cimCamera:cimCamera:remove')")
    @Log(title = "摄像头", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimCameraService.deleteCimCameraByIds(ids));
    }
    /**
     * 获取摄像头树信息
     */
    @ApiOperation(value = "获取摄像头树信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取摄像头树信息")
    //@PreAuthorize("@ss.hasPermi('cimCamera:cimCamera:query')")
    @GetMapping(value = "/treeList")
    public AjaxResult treeList(String type)
    {
        List<CimCamera> list = cimCameraService.selectCimCameraTreeList(type);
        return AjaxResult.success(list);
    }
}
