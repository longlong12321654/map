package com.hndist.server.controller;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.server.domain.CimCity;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.utils.StringUtils;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.server.service.ICimCityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: CimCity
 * @Description: 行政区划Controller
 * @author AI Builder
 * @date 2022-07-28 03:22:03
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Api(tags = "行政区划管理")
@RestController
@RequestMapping("/cimCity")
public class CityController extends BaseController
{
    @Autowired
    private ICimCityService sysDictSortService;

    /**
     * 查询行政区划列表
     */
    @ApiOperation(value = "查询行政区划列表")
    @GetMapping("/list")
    public TableDataInfo list(CimCity sysDictSort)
    {
        startPage();
        List<CimCity> list = sysDictSortService.selectCimCityList(sysDictSort);
        return getDataTable(list);
    }

    /**
     * 查询行政区划树信息
     * @return
     */
    @ApiOperation(value = "查询行政区划树信息")
    @GetMapping("/treeList")
    public AjaxResult treeList(String type, String name){
        List<CimCity> sysDictSorts = sysDictSortService.selectCimCityTreeList(type, name);
        return AjaxResult.success(sysDictSorts);
    }

    /**
     * 导出行政区划列表
     */
    //@PreAuthorize("@ss.hasPermi('sysDictSort:sysDictSort:export')")
    @Log(title = "行政区划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimCity sysDictSort)
    {
        List<CimCity> list = sysDictSortService.selectCimCityList(sysDictSort);
        ExcelUtil<CimCity> util = new ExcelUtil<CimCity>(CimCity.class);
        util.exportExcel(response, list, "行政区划数据");
    }

    /**
     * 获取行政区划详细信息
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取摄像头详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取摄像头详细信息")
    //@PreAuthorize("@ss.hasPermi('sysDictSort:sysDictSort:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysDictSortService.selectCimCityById(id));
    }

    /**
     * 新增行政区划
     */
    @ApiOperation(value = "新增行政区划")
    @Log(title = "行政区划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimCity sysDictSort)
    {
        if(StringUtils.isEmpty(sysDictSort.getPid()) || "0".equals(sysDictSort.getPid())){
            CimCity sysDictSort1 = sysDictSortService.selectDuplicate(sysDictSort);
            if(sysDictSort1 !=null){
                return AjaxResult.error("行政区划名称或编码已经存在，不能重复，请重新设置！");
            }
            sysDictSort.setPid("0");
        }
        CimCity repetition = sysDictSortService.repetitionCode(sysDictSort);
        if(repetition != null){
            return AjaxResult.error("行政区划名称或编码已经存在，不能重复，请重新设置！");
        }
        return AjaxResult.success("操作成功",sysDictSortService.insertCimCity(sysDictSort));
    }

    /**
     * 修改行政区划
     */
    @ApiOperation(value = "修改行政区划")
    @Log(title = "行政区划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimCity sysDictSort)
    {
        return AjaxResult.success("操作成功",sysDictSortService.updateCimCity(sysDictSort));
    }

    /**
     * 删除行政区划
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除行政区划")
    @Log(title = "行政区划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysDictSortService.deleteCimCityByIds(ids));
    }

}
