package com.hndist.server.service.impl;
import com.hndist.server.domain.CimMapproject;
import com.hndist.server.mapper.CimMapprojectMapper;
import com.hndist.server.service.ICimMapprojectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.hndist.framework.core.domain.model.LoginUser;
import com.hndist.framework.utils.SecurityUtils;

/**
 * @Title: CimMapproject
 * @Description: 地图工程服务实现
 * @author AI Builder
 * @date 2022-11-14 03:18:41
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimMapprojectServiceImpl implements ICimMapprojectService
{
    @Autowired
    private CimMapprojectMapper cimMapprojectMapper;


    /**
     * 查询地图工程
     *
     * @param id 地图工程主键
     * @return 地图工程
     */
    @Override
    public CimMapproject selectCimMapprojectById(String id)
    {
        CimMapproject cimMapproject = cimMapprojectMapper.selectCimMapprojectById(id);
        return cimMapproject;
    }

    /**
     * 查询地图工程列表
     *
     * @param cimMapproject 地图工程
     * @return 地图工程
     */
    @Override
    public List<CimMapproject> selectCimMapprojectList(CimMapproject cimMapproject)
    {
        return cimMapprojectMapper.selectCimMapprojectList(cimMapproject);
    }

    /**
     * 新增地图工程
     *
     * @param cimMapproject 地图工程
     * @return 结果
     */
    @Override
    public String insertCimMapproject(CimMapproject cimMapproject)
    {
        if("".equals(cimMapproject.getId())||cimMapproject.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimMapproject.setId(id);
            cimMapprojectMapper.insertCimMapproject(cimMapproject);
        }else{
            CimMapproject dbCimMapproject = cimMapprojectMapper.selectCimMapprojectById(cimMapproject.getId());
            if(dbCimMapproject!=null&&dbCimMapproject.getId()!=null){
                cimMapprojectMapper.updateCimMapproject(cimMapproject);
            }else{
                cimMapprojectMapper.insertCimMapproject(cimMapproject);
            }
        }
        return cimMapproject.getId();
    }

    /**
     * 修改地图工程
     *
     * @param cimMapproject 地图工程
     * @return 结果
     */
    @Override
    public String updateCimMapproject(CimMapproject cimMapproject)
    {
        cimMapprojectMapper.updateCimMapproject(cimMapproject);
        return cimMapproject.getId();
    }

    /**
     * 批量删除地图工程
     *
     * @param ids 需要删除的地图工程主键
     * @return 结果
     */
    @Override
    public int deleteCimMapprojectByIds(String[] ids)
    {
        return cimMapprojectMapper.deleteCimMapprojectByIds(ids);
    }

    /**
     * 删除地图工程信息
     *
     * @param id 地图工程主键
     * @return 结果
     */
    @Override
    public int deleteCimMapprojectById(String id)
    {
        return cimMapprojectMapper.deleteCimMapprojectById(id);
    }
    /**
     * 查询地图工程树信息
     *
     * @param type
     * @return 地图工程
     */
    @Override
    public List<CimMapproject> selectCimMapprojectTreeList(String type)
    {
        return cimMapprojectMapper.selectCimMapprojectTreeList(type);
    }



}
