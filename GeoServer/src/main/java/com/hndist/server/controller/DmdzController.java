package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimDmdz;
import com.hndist.server.service.ICimDmdzService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimDmdz
 * @Description: 地名地址Controller
 * @author AI Builder
 * @date 2022-03-31 03:42:27
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Api(tags = "地名地址管理")
@RestController
@RequestMapping("/cimDmdz")
public class DmdzController extends BaseController
{
    @Autowired
    private ICimDmdzService bsDmdzService;

    /**
     * 查询地名地址列表
     */
//    @PreAuthorize("@ss.hasPermi('bsDmdz:bsDmdz:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimDmdz bsDmdz)
    {
        startPage();
        List<CimDmdz> list = bsDmdzService.selectCimDmdzList(bsDmdz);
        return getDataTable(list);
    }

    /**
     * 导出地名地址列表
     */
//    @PreAuthorize("@ss.hasPermi('bsDmdz:bsDmdz:export')")
    @Log(title = "地名地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimDmdz bsDmdz)
    {
        List<CimDmdz> list = bsDmdzService.selectCimDmdzList(bsDmdz);
        ExcelUtil<CimDmdz> util = new ExcelUtil<CimDmdz>(CimDmdz.class);
        util.exportExcel(response, list, "地名地址数据");
    }

    /**
     * 获取地名地址详细信息
     */
//    @PreAuthorize("@ss.hasPermi('bsDmdz:bsDmdz:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bsDmdzService.selectCimDmdzById(id));
    }

    /**
     * 新增地名地址
     */
    //@PreAuthorize("@ss.hasPermi('bsDmdz:bsDmdz:add')")
    @Log(title = "地名地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimDmdz bsDmdz)
    {
        return toAjax(bsDmdzService.insertCimDmdz(bsDmdz));
    }

    /**
     * 修改地名地址
     */
//    @PreAuthorize("@ss.hasPermi('bsDmdz:bsDmdz:edit')")
    @Log(title = "地名地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimDmdz bsDmdz)
    {
        return toAjax(bsDmdzService.updateCimDmdz(bsDmdz));
    }

    /**
     * 删除地名地址
     */
//    @PreAuthorize("@ss.hasPermi('bsDmdz:bsDmdz:remove')")
    @Log(title = "地名地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bsDmdzService.deleteCimDmdzByIds(ids));
    }
}
