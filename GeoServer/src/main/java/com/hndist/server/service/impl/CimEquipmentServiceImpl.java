package com.hndist.server.service.impl;
import com.hndist.server.domain.CimEquipment;
import com.hndist.server.mapper.CimEquipmentMapper;
import com.hndist.server.service.ICimEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Title: CimEquipment
 * @Description: 设备信息服务实现
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimEquipmentServiceImpl implements ICimEquipmentService
{
    @Autowired
    private CimEquipmentMapper cimEquipmentMapper;


    /**
     * 查询设备信息
     *
     * @param id 设备信息主键
     * @return 设备信息
     */
    @Override
    public CimEquipment selectCimEquipmentById(String id)
    {
        CimEquipment cimEquipment = cimEquipmentMapper.selectCimEquipmentById(id);
        return cimEquipment;
    }

    /**
     * 查询设备信息列表
     *
     * @param cimEquipment 设备信息
     * @return 设备信息
     */
    @Override
    public List<CimEquipment> selectCimEquipmentList(CimEquipment cimEquipment)
    {
        return cimEquipmentMapper.selectCimEquipmentList(cimEquipment);
    }

    /**
     * 新增设备信息
     *
     * @param cimEquipment 设备信息
     * @return 结果
     */
    @Override
    public String insertCimEquipment(CimEquipment cimEquipment)
    {
        if("".equals(cimEquipment.getId())||cimEquipment.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimEquipment.setId(id);
            cimEquipmentMapper.insertCimEquipment(cimEquipment);
        }else{
            CimEquipment dbCimEquipment = cimEquipmentMapper.selectCimEquipmentById(cimEquipment.getId());
            if(dbCimEquipment!=null&&dbCimEquipment.getId()!=null){
                cimEquipmentMapper.updateCimEquipment(cimEquipment);
            }else{
                cimEquipmentMapper.insertCimEquipment(cimEquipment);
            }
        }
        return cimEquipment.getId();
    }

    /**
     * 修改设备信息
     *
     * @param cimEquipment 设备信息
     * @return 结果
     */
    @Override
    public String updateCimEquipment(CimEquipment cimEquipment)
    {
        cimEquipmentMapper.updateCimEquipment(cimEquipment);
        return cimEquipment.getId();
    }

    /**
     * 批量删除设备信息
     *
     * @param ids 需要删除的设备信息主键
     * @return 结果
     */
    @Override
    public int deleteCimEquipmentByIds(String[] ids)
    {
        return cimEquipmentMapper.deleteCimEquipmentByIds(ids);
    }

    /**
     * 删除设备信息信息
     *
     * @param id 设备信息主键
     * @return 结果
     */
    @Override
    public int deleteCimEquipmentById(String id)
    {
        return cimEquipmentMapper.deleteCimEquipmentById(id);
    }



}
