package com.hndist.server.service.impl;
import com.hndist.server.domain.CimDevice;
import com.hndist.server.mapper.CimDeviceMapper;
import com.hndist.server.service.ICimDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.hndist.framework.core.domain.model.LoginUser;
import com.hndist.framework.utils.SecurityUtils;

/**
 * @Title: CimDevice
 * @Description: 传感器服务实现
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimDeviceServiceImpl implements ICimDeviceService
{
    @Autowired
    private CimDeviceMapper cimDeviceMapper;


    /**
     * 查询传感器
     *
     * @param id 传感器主键
     * @return 传感器
     */
    @Override
    public CimDevice selectCimDeviceById(String id)
    {
        CimDevice cimDevice = cimDeviceMapper.selectCimDeviceById(id);
        return cimDevice;
    }

    /**
     * 查询传感器列表
     *
     * @param cimDevice 传感器
     * @return 传感器
     */
    @Override
    public List<CimDevice> selectCimDeviceList(CimDevice cimDevice)
    {
        return cimDeviceMapper.selectCimDeviceList(cimDevice);
    }

    /**
     * 新增传感器
     *
     * @param cimDevice 传感器
     * @return 结果
     */
    @Override
    public String insertCimDevice(CimDevice cimDevice)
    {
        if("".equals(cimDevice.getId())||cimDevice.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimDevice.setId(id);
            cimDeviceMapper.insertCimDevice(cimDevice);
        }else{
            CimDevice dbCimDevice = cimDeviceMapper.selectCimDeviceById(cimDevice.getId());
            if(dbCimDevice!=null&&dbCimDevice.getId()!=null){
                cimDeviceMapper.updateCimDevice(cimDevice);
            }else{
                cimDeviceMapper.insertCimDevice(cimDevice);
            }
        }
        return cimDevice.getId();
    }

    /**
     * 修改传感器
     *
     * @param cimDevice 传感器
     * @return 结果
     */
    @Override
    public String updateCimDevice(CimDevice cimDevice)
    {
        cimDeviceMapper.updateCimDevice(cimDevice);
        return cimDevice.getId();
    }

    /**
     * 批量删除传感器
     *
     * @param ids 需要删除的传感器主键
     * @return 结果
     */
    @Override
    public int deleteCimDeviceByIds(String[] ids)
    {
        return cimDeviceMapper.deleteCimDeviceByIds(ids);
    }

    /**
     * 删除传感器信息
     *
     * @param id 传感器主键
     * @return 结果
     */
    @Override
    public int deleteCimDeviceById(String id)
    {
        return cimDeviceMapper.deleteCimDeviceById(id);
    }
    /**
     * 查询传感器树信息
     *
     * @param type
     * @return 传感器
     */
    @Override
    public List<CimDevice> selectCimDeviceTreeList(String type)
    {
        return cimDeviceMapper.selectCimDeviceTreeList(type);
    }



}
