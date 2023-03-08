package com.hndist.server.controller;

import com.hndist.framework.annotation.Log;
import com.hndist.framework.constant.UserConstants;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.OmpMenuBase;
import com.hndist.server.domain.OmpMenuSys;
import com.hndist.server.domain.OmpSysInfo;
import com.hndist.server.service.IOmpMenuBaseService;
import com.hndist.server.service.IOmpMenuSysService;
import com.hndist.server.service.IOmpSysInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: OmpSysInfo
 * @Description: 一张图系统信息表Controller
 * @author AI Builder
 * @date 2023-02-09 10:17:07
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@RestController
@RequestMapping("/ompSysInfo")
public class OmpSysInfoController extends BaseController
{
    @Autowired
    private IOmpSysInfoService ompSysInfoService;
    @Autowired
    private IOmpMenuSysService ompMenuSysService;
    @Autowired
    private IOmpMenuBaseService ompMenuBaseService;

    /**
     * 查询一张图系统信息表列表
     */
    //@PreAuthorize("@ss.hasPermi('ompSysInfo:ompSysInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(OmpSysInfo ompSysInfo)
    {
        startPage();
        List<OmpSysInfo> list = ompSysInfoService.selectOmpSysInfoList(ompSysInfo);
        return getDataTable(list);
    }

    /**
     * 导出一张图系统信息表列表
     */
    //@PreAuthorize("@ss.hasPermi('ompSysInfo:ompSysInfo:export')")
    @Log(title = "一张图系统信息表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OmpSysInfo ompSysInfo)
    {
        List<OmpSysInfo> list = ompSysInfoService.selectOmpSysInfoList(ompSysInfo);
        ExcelUtil<OmpSysInfo> util = new ExcelUtil<OmpSysInfo>(OmpSysInfo.class);
        util.exportExcel(response, list, "一张图系统信息表数据");
    }

    /**
     * 获取一张图系统信息表详细信息
     */
    //@PreAuthorize("@ss.hasPermi('ompSysInfo:ompSysInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        OmpSysInfo ompSysInfo = ompSysInfoService.selectOmpSysInfoById(id);
        //补全menuList
        OmpMenuSys menuSys = new OmpMenuSys();
        menuSys.setSystemId(id);
        ompSysInfo.setMenuList(ompMenuSysService.selectOmpMenuSysList(menuSys));
        return AjaxResult.success(ompSysInfo);
    }

    /**
     * 新增一张图系统信息表
     */
    //@PreAuthorize("@ss.hasPermi('ompSysInfo:ompSysInfo:add')")
    @Log(title = "一张图系统信息表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OmpSysInfo ompSysInfo)
    {
        if (UserConstants.NOT_UNIQUE.equals(ompSysInfoService.checkSystemNameUnique(ompSysInfo)))
        {
            return AjaxResult.error("新增系统'" + ompSysInfo.getSysName() + "'失败，系统名称已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(ompSysInfoService.checkSystemKeyUnique(ompSysInfo)))
        {
            return AjaxResult.error("新增系统'" + ompSysInfo.getSysKey() + "'失败，系统识别码已存在");
        }
        return AjaxResult.success("操作成功",ompSysInfoService.insertOmpSysInfo(ompSysInfo));
    }

    /**
     * 修改一张图系统信息表
     */
    //@PreAuthorize("@ss.hasPermi('ompSysInfo:ompSysInfo:edit')")
    @Log(title = "一张图系统信息表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OmpSysInfo ompSysInfo)
    {
        if (UserConstants.NOT_UNIQUE.equals(ompSysInfoService.checkSystemNameUnique(ompSysInfo)))
        {
            return AjaxResult.error("修改系统'" + ompSysInfo.getSysName() + "'失败，系统名称已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(ompSysInfoService.checkSystemKeyUnique(ompSysInfo)))
        {
            return AjaxResult.error("修改系统'" + ompSysInfo.getSysName() + "'失败，系统识别码已存在");
        }
        return AjaxResult.success("操作成功",ompSysInfoService.updateOmpSysInfo(ompSysInfo));
    }

    /**
     * 删除一张图系统信息表
     */
    //@PreAuthorize("@ss.hasPermi('ompSysInfo:ompSysInfo:remove')")
    @Log(title = "一张图系统信息表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @Transactional
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        int res = ompSysInfoService.deleteOmpSysInfoByIds(ids);
        //删除关联菜单，后续考虑优化为逻辑删除
        for (Long id:ids)
            ompMenuSysService.deleteOmpMenuSysBySysId(id);
        return toAjax(res);
    }

    /**
     * 加载对应系统菜单列表树
     */
    @GetMapping("/sysMenuTreeSelect/{systemId}")
    public AjaxResult sysMenuTreeSelect(@PathVariable("systemId") Long systemId)
    {
        OmpMenuBase base = new OmpMenuBase();
        base.setVisible("0");
        base.setStatus("0");
        List<OmpMenuBase> menus = ompMenuBaseService.selectOmpMenuBaseList(base, getUserId());
        AjaxResult result = AjaxResult.success();
        result.put("checkedKeys", ompSysInfoService.selectMenuListBySystemId(systemId));
        result.put("menus", menus);
        return result;
    }

}
