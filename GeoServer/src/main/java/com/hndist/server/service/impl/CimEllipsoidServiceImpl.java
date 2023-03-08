package com.hndist.server.service.impl;
import com.hndist.server.domain.CimEllipsoid;
import com.hndist.server.mapper.CimEllipsoidMapper;
import com.hndist.server.service.ICimEllipsoidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Title: CimEllipsoid
 * @Description: 椭球体服务实现
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimEllipsoidServiceImpl implements ICimEllipsoidService
{
    @Autowired
    private CimEllipsoidMapper cimEllipsoidMapper;


    /**
     * 查询椭球体
     *
     * @param id 椭球体主键
     * @return 椭球体
     */
    @Override
    public CimEllipsoid selectBaseEllipsoidById(String id)
    {
        CimEllipsoid cimEllipsoid = cimEllipsoidMapper.selectBaseEllipsoidById(id);
        return cimEllipsoid;
    }

    /**
     * 查询椭球体列表
     *
     * @param cimEllipsoid 椭球体
     * @return 椭球体
     */
    @Override
    public List<CimEllipsoid> selectBaseEllipsoidList(CimEllipsoid cimEllipsoid)
    {
        return cimEllipsoidMapper.selectBaseEllipsoidList(cimEllipsoid);
    }

    /**
     * 新增椭球体
     *
     * @param cimEllipsoid 椭球体
     * @return 结果
     */
    @Override
    public String insertBaseEllipsoid(CimEllipsoid cimEllipsoid)
    {
        if("".equals(cimEllipsoid.getId())|| cimEllipsoid.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimEllipsoid.setId(id);
            cimEllipsoidMapper.insertBaseEllipsoid(cimEllipsoid);
        }else{
            CimEllipsoid dbCimEllipsoid = cimEllipsoidMapper.selectBaseEllipsoidById(cimEllipsoid.getId());
            if(dbCimEllipsoid !=null&& dbCimEllipsoid.getId()!=null){
                cimEllipsoidMapper.updateBaseEllipsoid(cimEllipsoid);
            }else{
                cimEllipsoidMapper.insertBaseEllipsoid(cimEllipsoid);
            }
        }
        return cimEllipsoid.getId();
    }

    /**
     * 修改椭球体
     *
     * @param cimEllipsoid 椭球体
     * @return 结果
     */
    @Override
    public String updateBaseEllipsoid(CimEllipsoid cimEllipsoid)
    {
        cimEllipsoidMapper.updateBaseEllipsoid(cimEllipsoid);
        return cimEllipsoid.getId();
    }

    /**
     * 批量删除椭球体
     *
     * @param ids 需要删除的椭球体主键
     * @return 结果
     */
    @Override
    public int deleteBaseEllipsoidByIds(String[] ids)
    {
        return cimEllipsoidMapper.deleteBaseEllipsoidByIds(ids);
    }

    /**
     * 删除椭球体信息
     *
     * @param id 椭球体主键
     * @return 结果
     */
    @Override
    public int deleteBaseEllipsoidById(String id)
    {
        return cimEllipsoidMapper.deleteBaseEllipsoidById(id);
    }



}
