package com.hndist.server.controller;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.hndist.framework.annotation.Log;
import com.hndist.framework.core.controller.BaseController;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.core.page.TableDataInfo;
import com.hndist.framework.enums.BusinessType;
import com.hndist.framework.service.ISysDictDataService;
import com.hndist.framework.utils.DateUtils;
import com.hndist.framework.utils.StringUtils;
import com.hndist.framework.utils.poi.ExcelUtil;
import com.hndist.framework.utils.uuid.IdUtils;
import com.hndist.framework.utils.uuid.UUID;
import com.hndist.server.domain.CimAnalysisConfig;
import com.hndist.server.domain.CimMapresources;
import com.hndist.server.service.ICimAnalysisConfigService;
import com.hndist.server.service.ICimMapresourcesService;
import com.hndist.server.service.impl.CimAnalysisConfigServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * @Title: CimMapresources
 * @Description: 资源目录Controller
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Api(tags = "资源目录")
@RestController
@RequestMapping("/cimMapresources")
public class MapresourcesController extends BaseController
{
    @Autowired
    private ICimMapresourcesService cimMapresourcesService;
    @Autowired
    private ICimAnalysisConfigService analysisConfigService;
    @Autowired
    private ISysDictDataService sysDictDataService;

    /**
     * 查询资源目录列表
     */

    @ApiOperation(value = "查询资源目录列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;查询资源目录列表")
    //@PreAuthorize("@ss.hasPermi('cimMapresources:cimMapresources:list')")
    @GetMapping("/list")
    public TableDataInfo list(CimMapresources cimMapresources)
    {
        startPage();
        List<CimMapresources> list = cimMapresourcesService.selectCimMapresourcesList(cimMapresources);
        return getDataTable(list);
    }

    /**
     * 通过pid查询资源目录列表
     */
    @ApiOperation(value = "通过pid查询资源中心列表")
    @GetMapping("/listByPid")
    public TableDataInfo selectResourceContentsListByPid(@RequestParam(value = "pid", required = true) String pid, HttpServletRequest request)
    {
        List<CimMapresources> list = cimMapresourcesService.selectResourceContentsListByPid(pid, request);
        return getDataTable(list);
    }

    /**
     * 获取tree列表
     */
    @ApiOperation(value = "获取tree列表")
    @GetMapping("/getTree")
    public TableDataInfo getTree(CimMapresources cimMapresources)
    {
        List<CimMapresources> list = cimMapresourcesService.getTree(cimMapresources);
        return getDataTable(list);
    }

    /**
     * 导出资源目录列表
     */
    @ApiOperation(value = "导出资源目录列表",notes = "<span style='color:red;'>详细描述：</span>&nbsp;导出资源目录列表")
    //@PreAuthorize("@ss.hasPermi('cimMapresources:cimMapresources:export')")
    @Log(title = "资源目录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CimMapresources cimMapresources)
    {
        List<CimMapresources> list = cimMapresourcesService.selectCimMapresourcesList(cimMapresources);
        ExcelUtil<CimMapresources> util = new ExcelUtil<CimMapresources>(CimMapresources.class);
        util.exportExcel(response, list, "资源目录数据");
    }

    /**
     * 获取资源目录详细信息
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", dataTypeClass = String.class, paramType = "path"),
    })
    @ApiOperation(value = "获取资源目录详细信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取资源目录详细信息")
    //@PreAuthorize("@ss.hasPermi('cimMapresources:cimMapresources:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cimMapresourcesService.selectCimMapresourcesById(id));
    }

    /**
     * 新增资源目录
     */
    @ApiOperation(value = "新增资源目录",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增资源目录")
    //@PreAuthorize("@ss.hasPermi('cimMapresources:cimMapresources:add')")
    @Log(title = "资源目录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CimMapresources cimMapresources)
    {
        return AjaxResult.success("操作成功",cimMapresourcesService.insertCimMapresources(cimMapresources));
    }

    /**
     * 修改资源目录
     */
    @ApiOperation(value = "修改资源目录",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改资源目录")
    //@PreAuthorize("@ss.hasPermi('cimMapresources:cimMapresources:edit')")
    @Log(title = "资源目录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CimMapresources cimMapresources)
    {
        return AjaxResult.success("操作成功",cimMapresourcesService.updateCimMapresources(cimMapresources));
    }

    /**
     * 删除资源目录
     */

    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键id，多个英文逗号分隔", dataType = "String", dataTypeClass=String.class, paramType = "path"),
    })
    @ApiOperation(value = "删除资源目录",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除资源目录")
    //@PreAuthorize("@ss.hasPermi('cimMapresources:cimMapresources:remove')")
    @Log(title = "资源目录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        if (StringUtils.isEmpty(ids)) {
            return AjaxResult.error("参数不为空");
        }
        if (ids.length>1) {
            return AjaxResult.error("最多删除一条记录");
        }
        List<CimMapresources> list = cimMapresourcesService.selectCimMapresourcesByIds(ids);
        if (!CollectionUtils.isEmpty(list)) {
            CimMapresources cm = new CimMapresources();
            String id = list.get(0).getId();
            cm.setPid(id);
            List<CimMapresources> sublist = cimMapresourcesService.selectCimMapresourcesList(cm);
            if (!CollectionUtils.isEmpty(sublist)) {
                return AjaxResult.error("存在下级资源,不允许删除");
            }
        }
        return toAjax(cimMapresourcesService.deleteCimMapresourcesByIds(ids));
    }
    /**
     * 获取资源目录树信息
     */
    @ApiOperation(value = "获取资源目录树信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取资源目录树信息")
    //@PreAuthorize("@ss.hasPermi('cimMapresources:cimMapresources:query')")
    @GetMapping(value = "/treeList")
    public AjaxResult treeList(String type)
    {
        List<CimMapresources> list = cimMapresourcesService.selectCimMapresourcesTreeList(type);
        return AjaxResult.success(list);
    }




    @GetMapping("/readJson")
    public AjaxResult readJson(String layerType) {
        String jsonString = null;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("F:\\所属项目\\CIM平台\\文档\\layers.json")));

        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if (jsonObject.containsKey("layers") && "layer".equals(layerType)) {
            JSONArray layers = jsonObject.getJSONArray("layers");
            if (Objects.nonNull(layers)) {
                for (int i = 0; i < layers.size(); i++) {
                    JSONObject jsonObject1 = layers.getJSONObject(i);
                    String label = jsonObject1.getString("label");
                    String id = jsonObject1.getString("id");
                    String spatialReference = jsonObject.getString("spatialReference");
                    String serviceEngine = jsonObject.getString("ServiceEngine");
                    String serviceURL = jsonObject.getString("serviceURL");
                    String context = jsonObject.getString("context");
                    CimMapresources cmr = new CimMapresources();
                    cmr.setId(id);
                    cmr.setName(label);
                    cmr.setSpatialReference(spatialReference);
                    cmr.setServiceengine(serviceEngine);
                    cmr.setServiceurl(serviceURL);
                    cmr.setContext(context);
                    cmr.setShow("1");
                    // 转换字典键值
                    String dictValue = sysDictDataService.selectDictValue("sys_serviceengine", serviceEngine);
                    cmr.setServiceengine(dictValue);
                    cmr.setServicetype(dictValue);
                    if (StringUtils.isEmpty(serviceURL)) {
                        cmr.setIsparent("1");
                    } else {
                        cmr.setIsparent("0");
                    }
                    cimMapresourcesService.insertCimMapresources(cmr);
                    if (jsonObject1.containsKey("children")) {
                        JSONArray children = jsonObject1.getJSONArray("children");
                        for (int j = 0; j < children.size(); j++) {
                            JSONObject jsonObject2 = children.getJSONObject(j);
                            String id1 = jsonObject2.getString("id");
                            String label1 = jsonObject2.getString("label");
                            String spatialReference1 = jsonObject2.getString("spatialReference");
                            String serviceEngine1 = jsonObject2.getString("ServiceEngine");
                            String serviceURL1 = jsonObject2.getString("serviceURL");
                            String context1 = jsonObject2.getString("context");
                            CimMapresources cmr1 = new CimMapresources();
                            cmr1.setId(id1);
                            cmr1.setPid(id);
                            cmr1.setName(label1);
                            cmr1.setSpatialReference(spatialReference1);
                            cmr1.setServiceengine(serviceEngine1);
                            cmr1.setServiceurl(serviceURL1);
                            cmr1.setContext(context1);
                            cmr1.setIsparent("0");
                            cmr1.setShow("1");
                            // 转换字典键值
                            String dictValue1 = sysDictDataService.selectDictValue("sys_serviceengine", serviceEngine1);
                            cmr1.setServiceengine(dictValue1);
                            cmr1.setServicetype(dictValue1);
                            cimMapresourcesService.insertCimMapresources(cmr1);
                            //System.out.println(jsonObject2);
                        }
        //                System.out.println(children);
                    }

                }

            }
        }

        if (jsonObject.containsKey("basemaps")&& "basemap".equals(layerType)) {
            JSONArray basemaps = jsonObject.getJSONArray("basemaps");
            for (int i = 0; i < basemaps.size(); i++) {
                JSONObject jsonObject1 = basemaps.getJSONObject(i);
                String label = jsonObject1.getString("label");
                String id = jsonObject1.getString("id");
                String spatialReference = jsonObject.getString("spatialReference");
                String serviceEngine = jsonObject.getString("ServiceEngine");
                String serviceURL = jsonObject.getString("serviceURL");
                String context = jsonObject.getString("context");
                String position = jsonObject.getString("position");

                CimMapresources cmr = new CimMapresources();
                cmr.setId(id);
                cmr.setName(label);
                cmr.setSpatialReference(spatialReference);
                cmr.setServiceengine(serviceEngine);
                cmr.setServiceurl(serviceURL);
                cmr.setContext(context);
                cmr.setSpatialReference(position);
                cmr.setShow("1");
                // 转换字典键值
                String dictValue = sysDictDataService.selectDictValue("sys_servicetype", serviceEngine);
                cmr.setServicetype(dictValue);
                if (StringUtils.isEmpty(serviceURL)) {
                    cmr.setIsparent("1");
                } else {
                    cmr.setIsparent("0");
                }
                cimMapresourcesService.insertCimMapresources(cmr);
            }
        }

        if (jsonObject.containsKey("tmpLayers") && "tmpLayer".equals(layerType)) {
            CimMapresources cmr1 = new CimMapresources();
            String simpleUUID = IdUtils.simpleUUID();
            cmr1.setId(simpleUUID);
            cmr1.setName("3D数据");
            cmr1.setShow("1");
            cmr1.setIsparent("1");
            cimMapresourcesService.insertCimMapresources(cmr1);
            JSONArray tmpLayers = jsonObject.getJSONArray("tmpLayers");
            for (int i = 0; i < tmpLayers.size(); i++) {
                JSONObject jsonObject1 = tmpLayers.getJSONObject(i);
                String label = jsonObject1.getString("label");
                String id = jsonObject1.getString("id");
                String serviceURL = jsonObject1.getString("url");
                String height = jsonObject1.getString("height");
                String type = jsonObject1.getString("type");
                System.out.println(jsonObject1);
                CimMapresources cmr = new CimMapresources();
                cmr.setId(id);
                cmr.setPid(simpleUUID);
                cmr.setName(label);
                cmr.setServiceurl(serviceURL);
                cmr.setShow("1");
                cmr.setContext(height);
                // 转换字典键值
                String dictValue = sysDictDataService.selectDictValue("sys_servicetype", type);
                cmr.setServicetype(dictValue);
                if (StringUtils.isEmpty(serviceURL)) {
                    cmr.setIsparent("1");
                } else {
                    cmr.setIsparent("0");
                }
                cimMapresourcesService.insertCimMapresources(cmr);
            }
        }


        if (jsonObject.containsKey("analyConfig") && "analyConfig".equals(layerType)) {
            CimAnalysisConfig cmr = new CimAnalysisConfig();
            cmr.setName("土地利用现状分析");
            cmr.setLabel("土地利用现状分析");
            cmr.setContent("1");
            cmr.setCreateTime(DateUtils.getNowDate());
            cmr.setPid("0");
            analysisConfigService.insertBsAnalysisConfig(cmr);
            JSONArray tmpLayers = jsonObject.getJSONArray("analyConfig");
            for (int i = 0; i < tmpLayers.size(); i++) {
                JSONObject jsonObject1 = tmpLayers.getJSONObject(i);
                String label = jsonObject1.getString("name");
                String id = jsonObject1.getString("id");
                String serviceURL = jsonObject1.getString("serviceURL");
                String viewFields = jsonObject1.getString("viewFields");
                String conclusion = jsonObject1.getString("conclusion");
                CimAnalysisConfig cmr1 = new CimAnalysisConfig();
                cmr1.setId(id);
                cmr1.setName(label);
                cmr1.setLabel(label);
                cmr1.setServiceUrl(serviceURL);
                cmr1.setViewFields(viewFields);
                cmr1.setConclesion(conclusion);
                String[] values= StringUtils.substringsBetween(conclusion,"{","}");
                String viewColumn = CimAnalysisConfigServiceImpl.arrayToStrWithComma(values);
                viewColumn = viewColumn.substring(0, viewColumn.lastIndexOf(":"));
                cmr1.setParam(viewColumn);
                cmr1.setCreateTime(DateUtils.getNowDate());
                cmr1.setPid("0");
                cmr.setContent("0");
                if (label.contains("土地利用现状分析")) {
                    cmr1.setPid(cmr.getId());
                }
                analysisConfigService.insertBsAnalysisConfig(cmr1);
            }
        }


        return AjaxResult.success("执行完成!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AjaxResult.error("执行失败");
    }
}
