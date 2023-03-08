package com.hndist.server.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.hndist.framework.utils.StringUtils;
import com.hndist.server.domain.CimCity;
import com.hndist.server.domain.CimCityExperience;
import com.hndist.server.domain.CimMapresources;
import com.hndist.server.mapper.CimCityExperienceMapper;
import com.hndist.server.mapper.CimMapresourcesMapper;
import com.hndist.server.service.ICimCityExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import com.hndist.framework.core.domain.model.LoginUser;
import com.hndist.framework.utils.SecurityUtils;
import org.springframework.util.CollectionUtils;

/**
 * @Title: CimCityExperience
 * @Description: 城市体验服务实现
 * @author AI Builder
 * @date 2023-01-11 07:11:11
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimCityExperienceServiceImpl implements ICimCityExperienceService
{
    @Autowired
    private CimCityExperienceMapper cimCityExperienceMapper;
    @Autowired
    private CimMapresourcesMapper mapresourcesMapper;


    /**
     * 查询城市体验
     *
     * @param id 城市体验主键
     * @return 城市体验
     */
    @Override
    public CimCityExperience selectCimCityExperienceById(String id)
    {
        CimCityExperience cimCityExperience = cimCityExperienceMapper.selectCimCityExperienceById(id);
        return cimCityExperience;
    }

    /**
     * 查询城市体验列表
     *
     * @param cimCityExperience 城市体验
     * @return 城市体验
     */
    @Override
    public List<CimCityExperience> selectCimCityExperienceList(CimCityExperience cimCityExperience)
    {
        return cimCityExperienceMapper.selectCimCityExperienceList(cimCityExperience);
    }

    /**
     * 新增城市体验
     *
     * @param cimCityExperience 城市体验
     * @return 结果
     */
    @Override
    public String insertCimCityExperience(CimCityExperience cimCityExperience)
    {
        if("".equals(cimCityExperience.getId())||cimCityExperience.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimCityExperience.setId(id);
            // 设置地图缩略图地址
            //parseThumbnailPath(cimCityExperience);
            cimCityExperienceMapper.insertCimCityExperience(cimCityExperience);
        }else{
            CimCityExperience dbCimCityExperience = cimCityExperienceMapper.selectCimCityExperienceById(cimCityExperience.getId());
            if(dbCimCityExperience!=null&&dbCimCityExperience.getId()!=null){
                cimCityExperienceMapper.updateCimCityExperience(cimCityExperience);
            }else{
                cimCityExperienceMapper.insertCimCityExperience(cimCityExperience);
            }
        }
        return cimCityExperience.getId();
    }

    /**
     * 设置地图缩略图地址
     */
    private void parseThumbnailPath(CimCityExperience cimCityExperience) {

        if (StringUtils.isNotEmpty(cimCityExperience.getResourceId())) {
            // 获取资源目录
            CimMapresources cimMapresources = mapresourcesMapper.selectCimMapresourcesById(cimCityExperience.getResourceId());
            if (cimMapresources != null) {
                String extent = cimMapresources.getExtent();
                // 组装缩略图地址
                if (StringUtils.isNotEmpty(extent)) {
                    StringBuilder bbox = new StringBuilder();
                    String path = "";
                    JSONObject extentJson = JSONObject.parseObject(extent);
                    for (Map.Entry<String, Object> entry : extentJson.entrySet()) {
                        Object value = entry.getValue();
                        bbox.append(",").append(String.valueOf(value));
                    }
                    if (StringUtils.isNotEmpty(bbox.toString())) {
                        bbox = new StringBuilder(bbox.toString().replaceFirst(",", ""));
                        path = cimMapresources.getServiceurl()+"?service=WMS&format=image/png&transparent=true&version=1.1.1&request=GetMap&styles=&layers=all&bbox="+bbox+"&width=256&height=256&srs=EPSG:4326";
                    }
                    cimCityExperience.setThumbnail(path);
                }
            }
        }
    }

    /**
     * 修改城市体验
     *
     * @param cimCityExperience 城市体验
     * @return 结果
     */
    @Override
    public String updateCimCityExperience(CimCityExperience cimCityExperience)
    {
        // 设置地图缩略图地址
        //parseThumbnailPath(cimCityExperience);
        cimCityExperienceMapper.updateCimCityExperience(cimCityExperience);
        return cimCityExperience.getId();
    }

    /**
     * 批量删除城市体验
     *
     * @param ids 需要删除的城市体验主键
     * @return 结果
     */
    @Override
    public int deleteCimCityExperienceByIds(String[] ids)
    {
        return cimCityExperienceMapper.deleteCimCityExperienceByIds(ids);
    }

    /**
     * 删除城市体验信息
     *
     * @param id 城市体验主键
     * @return 结果
     */
    @Override
    public int deleteCimCityExperienceById(String id)
    {
        return cimCityExperienceMapper.deleteCimCityExperienceById(id);
    }
    /**
     * 查询城市体验树信息
     *
     * @param type
     * @return 城市体验
     */
    @Override
    public List<CimCityExperience> selectCimCityExperienceTreeList(String type)
    {
        return cimCityExperienceMapper.selectCimCityExperienceTreeList(type);
    }
    /**
     * 查询城市体验树信息
     *
     * @return 城市体验
     */
    @Override
    public List<CimCityExperience> selectCimCityExperienceTreeNodeList(CimCityExperience cimCityExperience)
    {
        // 获取列表
        List<CimCityExperience> list = cimCityExperienceMapper.selectCimCityExperienceList(cimCityExperience);
        if (!CollectionUtils.isEmpty(list)) {
            List<CimCityExperience> listCity = list.stream()
                    .filter(city -> city.getPid().equals("0"))
                    .peek(city ->
                            city.setChildren(getChildrens(city,list))).collect(Collectors.toList());
            return listCity;
        }
        return list;
    }
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    @Override
    public int selectCimCityExperByName(String name) {
        return cimCityExperienceMapper.selectCimCityExperByName(name);
    }

    /**
     * 根据名称查询排除id
     * @param name
     * @param id
     * @return
     */
    @Override
    public int selectCimCityExperByNameExclude(String name, String id) {
        return cimCityExperienceMapper.selectCimCityExperByNameExclude(name, id);
    }


    /**
     * 代码实现递归方法
     *
     */
    public List<CimCityExperience> getChildrens(CimCityExperience cityExperience, List<CimCityExperience> cityExperienceList) {
        List<CimCityExperience> childrens = cityExperienceList.stream().filter(u -> Objects.equals(u.getPid(), cityExperience.getId())).map(
                u -> {
                    u.setChildren(getChildrens(u, cityExperienceList));
                    return u;
                }
        ).collect(Collectors.toList());
        return childrens;
    }


}
