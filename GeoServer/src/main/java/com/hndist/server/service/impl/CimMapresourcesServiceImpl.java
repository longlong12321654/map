package com.hndist.server.service.impl;
import com.hndist.framework.mapper.SysDictDataMapper;
import com.hndist.framework.utils.PageUtils;
import com.hndist.framework.utils.StringUtils;
import com.hndist.server.domain.CimMapresources;
import com.hndist.server.mapper.CimMapresourcesMapper;
import com.hndist.server.service.ICimMapresourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Title: CimMapresources
 * @Description: 地图服务服务实现
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimMapresourcesServiceImpl implements ICimMapresourcesService
{
    @Autowired
    private CimMapresourcesMapper cimMapresourcesMapper;
    @Autowired
    private SysDictDataMapper dictDataMapper;


    /**
     * 查询地图服务
     *
     * @param id 地图服务主键
     * @return 地图服务
     */
    @Override
    public CimMapresources selectCimMapresourcesById(String id)
    {
        CimMapresources cimMapresources = cimMapresourcesMapper.selectCimMapresourcesById(id);
        return cimMapresources;
    }

    /**
     * 查询地图服务列表
     *
     * @param cimMapresources 地图服务
     * @return 地图服务
     */
    @Override
    public List<CimMapresources> selectCimMapresourcesList(CimMapresources cimMapresources)
    {
        List<CimMapresources> mapresourcesList = cimMapresourcesMapper.selectCimMapresourcesList(cimMapresources);
        if (!CollectionUtils.isEmpty(mapresourcesList)) {
            mapresourcesList.forEach(item-> {
                String servicetype = item.getServicetype();
                item.setServicetypeText(dictDataMapper.selectDictLabel("sys_servicetype", servicetype));
            });
        }

        return mapresourcesList;
    }

    /**
     * 通过pid查询资源中心列表
     * @param pid 父节点ID
     * @return
     */
    @Override
    public List<CimMapresources> selectResourceContentsListByPid(String pid, HttpServletRequest request) {
        PageUtils.startPage();
        List<CimMapresources> contentsList = cimMapresourcesMapper.selectCimMapresourcesListByPid(pid);
        if (!CollectionUtils.isEmpty(contentsList)) {
            contentsList.forEach(item-> {
                String servicetype = item.getServicetype();
                item.setServicetypeText(dictDataMapper.selectDictLabel("sys_servicetype", servicetype));
            });
        }
        return contentsList;
    }

    @Override
    public List<CimMapresources> getTree(CimMapresources cimMapresources) {
        List<CimMapresources> data = cimMapresourcesMapper.selectCimMapresourcesList(cimMapresources);
        // 复制data数据
        List<CimMapresources> list = new ArrayList<>(data);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item-> {
                String servicetype = item.getServicetype();
                item.setServicetypeText(dictDataMapper.selectDictLabel("sys_servicetype", servicetype));
            });
        }
        // 遍历两次data来组装带有children关联性的对象，如果找到子级就删除menuList的数据
        for (CimMapresources entity : data) {
            for (CimMapresources entity2 : data) {
                //如果本级id与数据的父id相同，就说明是子父级关系
                if (entity.getId().equals(entity2.getPid())) {
                    entity.getChildren().add(entity2);
                    list.remove(entity2);
                }
            }
        }
        return list;
    }

    /**
     * 新增地图服务
     *
     * @param cimMapresources 地图服务
     * @return 结果
     */
    @Override
    public String insertCimMapresources(CimMapresources cimMapresources)
    {
        if("".equals(cimMapresources.getId())||cimMapresources.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimMapresources.setId(id);
            if (StringUtils.isEmpty(cimMapresources.getIcon())) {
                cimMapresources.setIcon("file");
            }
            cimMapresourcesMapper.insertCimMapresources(cimMapresources);
        }else{
            CimMapresources dbCimMapresources = cimMapresourcesMapper.selectCimMapresourcesById(cimMapresources.getId());
            if(dbCimMapresources!=null&&dbCimMapresources.getId()!=null){
                cimMapresourcesMapper.updateCimMapresources(cimMapresources);
            }else{
                if (StringUtils.isEmpty(cimMapresources.getIcon())) {
                    cimMapresources.setIcon("file");
                }
                cimMapresourcesMapper.insertCimMapresources(cimMapresources);
            }
        }
        return cimMapresources.getId();
    }

    /**
     * 修改地图服务
     *
     * @param cimMapresources 地图服务
     * @return 结果
     */
    @Override
    public String updateCimMapresources(CimMapresources cimMapresources)
    {
        cimMapresourcesMapper.updateCimMapresources(cimMapresources);
        return cimMapresources.getId();
    }

    /**
     * 批量删除地图服务
     *
     * @param ids 需要删除的地图服务主键
     * @return 结果
     */
    @Override
    public int deleteCimMapresourcesByIds(String[] ids)
    {
        return cimMapresourcesMapper.deleteCimMapresourcesByIds(ids);
    }

    /**
     * 查询查询地图服务
     * @param ids 需要查询的数据主键集合
     * @return
     */
    @Override
    public List<CimMapresources> selectCimMapresourcesByIds(String[] ids) {
        return cimMapresourcesMapper.selectCimMapresourcesByIds(ids);
    }

    /**
     * 删除地图服务信息
     *
     * @param id 地图服务主键
     * @return 结果
     */
    @Override
    public int deleteCimMapresourcesById(String id)
    {
        return cimMapresourcesMapper.deleteCimMapresourcesById(id);
    }
    /**
     * 查询地图服务树信息
     *
     * @param type
     * @return 地图服务
     */
    @Override
    public List<CimMapresources> selectCimMapresourcesTreeList(String type)
    {
        return cimMapresourcesMapper.selectCimMapresourcesTreeList(type);
    }



}
