package com.hndist.server.service.impl;
import com.hndist.server.domain.CimBaseMap;
import com.hndist.server.mapper.CimBaseMapMapper;
import com.hndist.server.service.ICimBaseMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.hndist.framework.core.domain.model.LoginUser;
import com.hndist.framework.utils.SecurityUtils;

/**
 * @Title: CimBaseMap
 * @Description: 底图服务服务实现
 * @author AI Builder
 * @date 2022-11-15 08:03:48
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimBaseMapServiceImpl implements ICimBaseMapService
{
    @Autowired
    private CimBaseMapMapper cimBaseMapMapper;


    /**
     * 查询底图服务
     *
     * @param id 底图服务主键
     * @return 底图服务
     */
    @Override
    public CimBaseMap selectCimBaseMapById(String id)
    {
        CimBaseMap cimBaseMap = cimBaseMapMapper.selectCimBaseMapById(id);
        return cimBaseMap;
    }

    /**
     * 查询底图服务列表
     *
     * @param cimBaseMap 底图服务
     * @return 底图服务
     */
    @Override
    public List<CimBaseMap> selectCimBaseMapList(CimBaseMap cimBaseMap)
    {
        return cimBaseMapMapper.selectCimBaseMapList(cimBaseMap);
    }

    /**
     * 新增底图服务
     *
     * @param cimBaseMap 底图服务
     * @return 结果
     */
    @Override
    public String insertCimBaseMap(CimBaseMap cimBaseMap)
    {
        if("".equals(cimBaseMap.getId())||cimBaseMap.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimBaseMap.setId(id);
            cimBaseMapMapper.insertCimBaseMap(cimBaseMap);
        }else{
            CimBaseMap dbCimBaseMap = cimBaseMapMapper.selectCimBaseMapById(cimBaseMap.getId());
            if(dbCimBaseMap!=null&&dbCimBaseMap.getId()!=null){
                cimBaseMapMapper.updateCimBaseMap(cimBaseMap);
            }else{
                cimBaseMapMapper.insertCimBaseMap(cimBaseMap);
            }
        }
        return cimBaseMap.getId();
    }

    /**
     * 修改底图服务
     *
     * @param cimBaseMap 底图服务
     * @return 结果
     */
    @Override
    public String updateCimBaseMap(CimBaseMap cimBaseMap)
    {
        cimBaseMapMapper.updateCimBaseMap(cimBaseMap);
        return cimBaseMap.getId();
    }

    /**
     * 批量删除底图服务
     *
     * @param ids 需要删除的底图服务主键
     * @return 结果
     */
    @Override
    public int deleteCimBaseMapByIds(String[] ids)
    {
        return cimBaseMapMapper.deleteCimBaseMapByIds(ids);
    }

    /**
     * 删除底图服务信息
     *
     * @param id 底图服务主键
     * @return 结果
     */
    @Override
    public int deleteCimBaseMapById(String id)
    {
        return cimBaseMapMapper.deleteCimBaseMapById(id);
    }



}
