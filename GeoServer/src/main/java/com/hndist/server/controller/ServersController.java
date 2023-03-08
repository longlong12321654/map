package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimServer;
import com.hndist.server.service.ICimServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimServer
 * @Description: 服务管理Controller
 * @author AI Builder
 * @date 2022-03-31 13:42:30
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Api(tags = "GIS服务器")
@RestController
@RequestMapping("/cimServer")
public class ServersController extends BaseController
{
    @Autowired
    private ICimServerService bsServerService;

    /**
     * 查询服务管理列表
     */
    @ApiOperation("查询服务管理列表")
    @GetMapping("/list")
    public TableDataInfo list(CimServer cimServer)
    {
        startPage();
        List<CimServer> list = bsServerService.selectCimServerList(cimServer);
        return getDataTable(list);
    }

    /**
     * 导出服务管理列表
     */
    @ApiOperation("导出服务管理列表")
    @Log(title = "服务管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimServer cimServer)
    {
        List<CimServer> list = bsServerService.selectCimServerList(cimServer);
        ExcelUtil<CimServer> util = new ExcelUtil<CimServer>(CimServer.class);
        util.exportExcel(response, list, "服务管理数据");
    }

    /**
     * 获取服务管理详细信息
     */
    @ApiOperation("获取服务管理详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bsServerService.selectCimServerById(id));
    }

    /**
     * 新增服务管理
     */
    @ApiOperation("新增服务管理")
    @Log(title = "服务管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimServer cimServer)
    {
        int count = bsServerService.existIpForInsert(cimServer.getName());
        if (count > 0) {
            return AjaxResult.error("ip信息不可以重复");
        }
        return toAjax(bsServerService.insertCimServer(cimServer));
    }

    /**
     * 修改服务管理
     */
    @ApiOperation("修改服务管理")
    @Log(title = "服务管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimServer cimServer)
    {
        int count = bsServerService.existIpForEdit(cimServer.getName(), cimServer.getId());
        if (count > 0) {
            return AjaxResult.error("ip信息不可以重复");
        }
        return toAjax(bsServerService.updateCimServer(cimServer));
    }

    /**
     * 删除服务管理
     */
    @ApiOperation("删除服务管理")
    @Log(title = "服务管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bsServerService.deleteCimServerByIds(ids));
    }
}
