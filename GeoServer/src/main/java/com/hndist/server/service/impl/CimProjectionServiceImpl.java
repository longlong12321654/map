package com.hndist.server.service.impl;
import com.hndist.server.domain.CimProjection;
import com.hndist.server.mapper.CimProjectionMapper;
import com.hndist.server.service.ICimProjectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Title: CimProjection
 * @Description: 投影坐标服务实现
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimProjectionServiceImpl implements ICimProjectionService
{
    @Autowired
    private CimProjectionMapper cimProjectionMapper;


    /**
     * 查询投影坐标
     *
     * @param id 投影坐标主键
     * @return 投影坐标
     */
    @Override
    public CimProjection selectCimProjectionById(String id)
    {
        CimProjection cimProjection = cimProjectionMapper.selectCimProjectionById(id);
        return cimProjection;
    }

    /**
     * 查询投影坐标列表
     *
     * @param cimProjection 投影坐标
     * @return 投影坐标
     */
    @Override
    public List<CimProjection> selectCimProjectionList(CimProjection cimProjection)
    {
        return cimProjectionMapper.selectCimProjectionList(cimProjection);
    }

    /**
     * 新增投影坐标
     *
     * @param cimProjection 投影坐标
     * @return 结果
     */
    @Override
    public String insertCimProjection(CimProjection cimProjection)
    {
        if("".equals(cimProjection.getId())||cimProjection.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimProjection.setId(id);
            cimProjectionMapper.insertCimProjection(cimProjection);
        }else{
            CimProjection dbCimProjection = cimProjectionMapper.selectCimProjectionById(cimProjection.getId());
            if(dbCimProjection!=null&&dbCimProjection.getId()!=null){
                cimProjectionMapper.updateCimProjection(cimProjection);
            }else{
                cimProjectionMapper.insertCimProjection(cimProjection);
            }
        }
        return cimProjection.getId();
    }

    /**
     * 修改投影坐标
     *
     * @param cimProjection 投影坐标
     * @return 结果
     */
    @Override
    public String updateCimProjection(CimProjection cimProjection)
    {
        cimProjectionMapper.updateCimProjection(cimProjection);
        return cimProjection.getId();
    }

    /**
     * 批量删除投影坐标
     *
     * @param ids 需要删除的投影坐标主键
     * @return 结果
     */
    @Override
    public int deleteCimProjectionByIds(String[] ids)
    {
        return cimProjectionMapper.deleteCimProjectionByIds(ids);
    }

    /**
     * 删除投影坐标信息
     *
     * @param id 投影坐标主键
     * @return 结果
     */
    @Override
    public int deleteCimProjectionById(String id)
    {
        return cimProjectionMapper.deleteCimProjectionById(id);
    }



}
