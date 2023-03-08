package com.hndist.server.controller;

import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.OmpMenuBase;
import com.hndist.server.domain.OmpMenuSys;
import com.hndist.server.domain.OmpMenuSys;
import com.hndist.server.service.IOmpMenuBaseService;
import com.hndist.server.service.IOmpMenuSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: OmpMenuSys
 * @Description: 一张图系统菜单表Controller
 * @author AI Builder
 * @date 2023-02-07 03:26:29
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@RestController
@RequestMapping("/ompMenuSys")
public class OmpMenuSysController extends BaseController
{
    @Autowired
    private IOmpMenuSysService ompMenuSysService;
    @Autowired
    private IOmpMenuBaseService ompMenuBaseService;

    /**
     * 查询一张图系统菜单表列表
     * 一张图免鉴权查询，系统菜单配置回显
     */
    //@PreAuthorize("@ss.hasPermi('ompMenuSys:ompMenuSys:list')")
    @GetMapping("/list")
    public AjaxResult list(OmpMenuSys ompMenuSys)
    {
        List<OmpMenuSys> menus = ompMenuSysService.selectOmpMenuSysList(ompMenuSys);
        return AjaxResult.success(menus);
    }

    /**
     * 获取一张图系统菜单表详细信息
     */
    //@PreAuthorize("@ss.hasPermi('ompMenuSys:ompMenuSys:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(ompMenuSysService.selectOmpMenuSysById(id));
    }

    /**
     * 新增一张图系统菜单表
     */
    //@PreAuthorize("@ss.hasPermi('ompMenuSys:ompMenuSys:add')")
//    @Log(title = "一张图系统菜单表", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody OmpMenuSys ompMenuSys)
//    {
//        return AjaxResult.success("操作成功",ompMenuSysService.insertOmpMenuSys(ompMenuSys));
//    }

    /**
     * 分配一张图系统菜单
     */
    //@PreAuthorize("@ss.hasPermi('ompMenuSys:ompMenuSys:add')")
    @Log(title = "一张图系统菜单表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OmpMenuSys ompMenuSys)
    {
        return AjaxResult.success("操作成功",ompMenuSysService.insertOmpMenuSys(ompMenuSys));
    }

    /**
     * 修改已分配的一张图系统菜单表
     */
    //@PreAuthorize("@ss.hasPermi('ompMenuSys:ompMenuSys:edit')")
    @Log(title = "一张图系统菜单表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OmpMenuSys ompMenuSys)
    {
        return AjaxResult.success("操作成功",ompMenuSysService.updateOmpMenuSys(ompMenuSys));
    }

    /**
     * 删除一张图系统菜单表
     */
    //@PreAuthorize("@ss.hasPermi('ompMenuSys:ompMenuSys:remove')")
    @Log(title = "一张图系统菜单表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ompMenuSysService.deleteOmpMenuSysByIds(ids));
    }
    /**
     * 获取一张图系统菜单表树信息
     */
    //@PreAuthorize("@ss.hasPermi('ompMenuSys:ompMenuSys:query')")
    @GetMapping(value = "/treeList")
    public AjaxResult treeList(String type)
    {
        List<OmpMenuSys> list = ompMenuSysService.selectOmpMenuSysTreeList(type);
        return AjaxResult.success(list);
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeSelect")
    public AjaxResult treeSelect(OmpMenuSys menu)
    {
        List<OmpMenuSys> menus = ompMenuSysService.selectOmpMenuSysList(menu);
        return AjaxResult.success(ompMenuSysService.buildMenuTreeSelect(menus));
    }

}
