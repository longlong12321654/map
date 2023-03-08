package com.hndist.server.service.impl;
import com.hndist.server.domain.CimCamera;
import com.hndist.server.mapper.CimCameraMapper;
import com.hndist.server.service.ICimCameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.hndist.framework.core.domain.model.LoginUser;
import com.hndist.framework.utils.SecurityUtils;

/**
 * @Title: CimCamera
 * @Description: 摄像头服务实现
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimCameraServiceImpl implements ICimCameraService
{
    @Autowired
    private CimCameraMapper cimCameraMapper;


    /**
     * 查询摄像头
     *
     * @param id 摄像头主键
     * @return 摄像头
     */
    @Override
    public CimCamera selectCimCameraById(String id)
    {
        CimCamera cimCamera = cimCameraMapper.selectCimCameraById(id);
        return cimCamera;
    }

    /**
     * 查询摄像头列表
     *
     * @param cimCamera 摄像头
     * @return 摄像头
     */
    @Override
    public List<CimCamera> selectCimCameraList(CimCamera cimCamera)
    {
        return cimCameraMapper.selectCimCameraList(cimCamera);
    }

    /**
     * 新增摄像头
     *
     * @param cimCamera 摄像头
     * @return 结果
     */
    @Override
    public String insertCimCamera(CimCamera cimCamera)
    {
        if("".equals(cimCamera.getId())||cimCamera.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimCamera.setId(id);
            cimCameraMapper.insertCimCamera(cimCamera);
        }else{
            CimCamera dbCimCamera = cimCameraMapper.selectCimCameraById(cimCamera.getId());
            if(dbCimCamera!=null&&dbCimCamera.getId()!=null){
                cimCameraMapper.updateCimCamera(cimCamera);
            }else{
                cimCameraMapper.insertCimCamera(cimCamera);
            }
        }
        return cimCamera.getId();
    }

    /**
     * 修改摄像头
     *
     * @param cimCamera 摄像头
     * @return 结果
     */
    @Override
    public String updateCimCamera(CimCamera cimCamera)
    {
        cimCameraMapper.updateCimCamera(cimCamera);
        return cimCamera.getId();
    }

    /**
     * 批量删除摄像头
     *
     * @param ids 需要删除的摄像头主键
     * @return 结果
     */
    @Override
    public int deleteCimCameraByIds(String[] ids)
    {
        return cimCameraMapper.deleteCimCameraByIds(ids);
    }

    /**
     * 删除摄像头信息
     *
     * @param id 摄像头主键
     * @return 结果
     */
    @Override
    public int deleteCimCameraById(String id)
    {
        return cimCameraMapper.deleteCimCameraById(id);
    }
    /**
     * 查询摄像头树信息
     *
     * @param type
     * @return 摄像头
     */
    @Override
    public List<CimCamera> selectCimCameraTreeList(String type)
    {
        return cimCameraMapper.selectCimCameraTreeList(type);
    }



}
