package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.domain.CimCityExperience;
import com.hndist.server.service.ICimCityExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimCityExperience
 * @Description: 城市体验Controller
 * @author AI Builder
 * @date 2023-01-11 07:11:11
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "城市体验")
@RestController
@RequestMapping("/cimCityExperience")
public class CityExperienceController extends BaseController
{
    @Autowired
    private ICimCityExperienceService cimCityExperienceService;

    /**
     * 查询城市体验列表
     */

    @ApiOperation(value = "查询城市体验列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询城市体验列表")
    //@PreAuthorize("@ss.hasPermi('cimCityExperience:cimCityExperience:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimCityExperience cimCityExperience)
    {
        startPage();
        List<CimCityExperience> list = cimCityExperienceService.selectCimCityExperienceList(cimCityExperience);
        return getDataTable(list);
    }

    /**
     * 导出城市体验列表
     */
    @ApiOperation(value = "导出城市体验列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出城市体验列表")
    //@PreAuthorize("@ss.hasPermi('cimCityExperience:cimCityExperience:export')")
    @Log(title = "城市体验", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimCityExperience cimCityExperience)
    {
        List<CimCityExperience> list = cimCityExperienceService.selectCimCityExperienceList(cimCityExperience);
        ExcelUtil<CimCityExperience> util = new ExcelUtil<CimCityExperience>(CimCityExperience.class);
        util.exportExcel(response, list, "城市体验数据");
    }

    /**
     * 获取城市体验详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取城市体验详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取城市体验详细信息")
    //@PreAuthorize("@ss.hasPermi('cimCityExperience:cimCityExperience:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimCityExperienceService.selectCimCityExperienceById(id));
    }

    /**
     * 新增城市体验
     */
    @ApiOperation(value = "新增城市体验",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增城市体验")
    //@PreAuthorize("@ss.hasPermi('cimCityExperience:cimCityExperience:add')")
    @Log(title = "城市体验", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimCityExperience cimCityExperience)
    {
        int i = cimCityExperienceService.selectCimCityExperByName(cimCityExperience.getName());
        if (i>0) {
            return AjaxResult.error("名称已经存在，不允许重复！");
        }
        return AjaxResult.success("操作成功",cimCityExperienceService.insertCimCityExperience(cimCityExperience));
    }

    /**
     * 修改城市体验
     */
    @ApiOperation(value = "修改城市体验",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改城市体验")
    //@PreAuthorize("@ss.hasPermi('cimCityExperience:cimCityExperience:edit')")
    @Log(title = "城市体验", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimCityExperience cimCityExperience)
    {
        int i = cimCityExperienceService.selectCimCityExperByNameExclude(cimCityExperience.getName(), cimCityExperience.getId());
        if (i>0) {
            return AjaxResult.error("名称已经存在，不允许重复！");
        }
        return AjaxResult.success("操作成功",cimCityExperienceService.updateCimCityExperience(cimCityExperience));
    }

    /**
     * 删除城市体验
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除城市体验",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除城市体验")
    //@PreAuthorize("@ss.hasPermi('cimCityExperience:cimCityExperience:remove')")
    @Log(title = "城市体验", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cimCityExperienceService.deleteCimCityExperienceByIds(ids));
    }
    /**
     * 获取城市体验树信息
     */
    @ApiOperation(value = "获取城市体验树信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取城市体验树信息")
    //@PreAuthorize("@ss.hasPermi('cimCityExperience:cimCityExperience:query')")
    @GetMapping(value = "/treeList")
    public AjaxResult treeList(String type)
    {
        List<CimCityExperience> list = cimCityExperienceService.selectCimCityExperienceTreeList(type);
        return AjaxResult.success(list);
    }
    /**
     * 获取城市体验树节点信息
     */
    @ApiOperation(value = "获取城市体验树信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取城市体验树信息")
    //@PreAuthorize("@ss.hasPermi('cimCityExperience:cimCityExperience:query')")
    @GetMapping(value = "/treeNodeList")
    public AjaxResult treeNodeList(CimCityExperience cimCityExperience)
    {
        List<CimCityExperience> list = cimCityExperienceService.selectCimCityExperienceTreeNodeList(cimCityExperience);
        return AjaxResult.success(list);
    }
}
