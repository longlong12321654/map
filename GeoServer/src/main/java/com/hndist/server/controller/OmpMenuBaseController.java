package com.hndist.server.controller;

import com.hndist.framework.annotation.Log;
import com.hndist.framework.constant.UserConstants;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.StringUtils;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.OmpMenuBase;
import com.hndist.server.service.IOmpMenuBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: OmpMenuBase
 * @Description: 一张图基础菜单表Controller
 * @author AI Builder
 * @date 2023-02-07 03:26:29
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@RestController
@RequestMapping("/ompMenuBase")
public class OmpMenuBaseController extends BaseController
{
    @Autowired
    private IOmpMenuBaseService ompMenuBaseService;

    /**
     * 查询一张图基础菜单表列表
     */
    //@PreAuthorize("@ss.hasPermi('ompMenuBase:ompMenuBase:list')")
    @GetMapping("/list")
    public AjaxResult list(OmpMenuBase ompMenuBase)
    {
        List<OmpMenuBase> menus = ompMenuBaseService.selectOmpMenuBaseList(ompMenuBase, getUserId());
        return AjaxResult.success(menus);
    }

    /**
     * 获取一张图基础菜单表详细信息
     */
    //@PreAuthorize("@ss.hasPermi('ompMenuBase:ompMenuBase:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(ompMenuBaseService.selectOmpMenuBaseById(id));
    }

    /**
     * 新增一张图基础菜单表
     */
    //@PreAuthorize("@ss.hasPermi('ompMenuBase:ompMenuBase:add')")
    @Log(title = "一张图基础菜单表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OmpMenuBase menu)
    {
        if (UserConstants.NOT_UNIQUE.equals(ompMenuBaseService.checkMenuNameUnique(menu)))
        {
            return AjaxResult.error("新增菜单'" + menu.getName() + "'失败，菜单名称已存在");
        }
        menu.setCreateBy(getUsername());
        return AjaxResult.success("操作成功",ompMenuBaseService.insertOmpMenuBase(menu));
    }

    /**
     * 修改一张图基础菜单表
     */
    //@PreAuthorize("@ss.hasPermi('ompMenuBase:ompMenuBase:edit')")
    @Log(title = "一张图基础菜单表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OmpMenuBase menu)
    {
        if (UserConstants.NOT_UNIQUE.equals(ompMenuBaseService.checkMenuNameUnique(menu)))
        {
            return AjaxResult.error("修改菜单'" + menu.getName() + "'失败，菜单名称已存在");
        }
        else if (menu.getMenuId().equals(menu.getPid()))
        {
            return AjaxResult.error("修改菜单'" + menu.getName() + "'失败，上级菜单不能选择自己");
        }
        menu.setUpdateBy(getUsername());
        return AjaxResult.success("操作成功",ompMenuBaseService.updateOmpMenuBase(menu));
    }

    /**
     * 删除一张图基础菜单表
     */
    //@PreAuthorize("@ss.hasPermi('ompMenuBase:ompMenuBase:remove')")
    @Log(title = "一张图基础菜单表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        if (ompMenuBaseService.hasChildByMenuId(id))
        {
            return AjaxResult.error("存在子菜单,不允许删除");
        }
        if (ompMenuBaseService.checkMenuExistSystem(id))
        {
            return AjaxResult.error("菜单已分配至系统,不允许删除");
        }
        return toAjax(ompMenuBaseService.deleteOmpMenuBaseById(id));
    }

    /**
     * 获取一张图基础菜单表树信息
     */
    //@PreAuthorize("@ss.hasPermi('ompMenuBase:ompMenuBase:query')")
    @GetMapping(value = "/treeList")
    public AjaxResult treeList(String type)
    {
        List<OmpMenuBase> list = ompMenuBaseService.selectOmpMenuBaseTreeList(type);
        return AjaxResult.success(list);
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeSelect")
    public AjaxResult treeSelect(OmpMenuBase menu)
    {
        List<OmpMenuBase> menus = ompMenuBaseService.selectOmpMenuBaseList(menu, getUserId());
        return AjaxResult.success(ompMenuBaseService.buildMenuTreeSelect(menus));
    }
}
