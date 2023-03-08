package com.hndist.server.service.impl;
import com.hndist.server.domain.CimTrackPosition;
import com.hndist.server.mapper.CimTrackPositionMapper;
import com.hndist.server.service.ICimTrackPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.hndist.framework.core.domain.model.LoginUser;
import com.hndist.framework.utils.SecurityUtils;

/**
 * @Title: CimTrackPosition
 * @Description: 轨迹位置服务实现
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimTrackPositionServiceImpl implements ICimTrackPositionService
{
    @Autowired
    private CimTrackPositionMapper cimTrackPositionMapper;


    /**
     * 查询轨迹位置
     *
     * @param id 轨迹位置主键
     * @return 轨迹位置
     */
    @Override
    public CimTrackPosition selectCimTrackPositionById(String id)
    {
        CimTrackPosition cimTrackPosition = cimTrackPositionMapper.selectCimTrackPositionById(id);
        return cimTrackPosition;
    }

    /**
     * 查询轨迹位置列表
     *
     * @param cimTrackPosition 轨迹位置
     * @return 轨迹位置
     */
    @Override
    public List<CimTrackPosition> selectCimTrackPositionList(CimTrackPosition cimTrackPosition)
    {
        return cimTrackPositionMapper.selectCimTrackPositionList(cimTrackPosition);
    }

    /**
     * 新增轨迹位置
     *
     * @param cimTrackPosition 轨迹位置
     * @return 结果
     */
    @Override
    public String insertCimTrackPosition(CimTrackPosition cimTrackPosition)
    {
        if("".equals(cimTrackPosition.getId())||cimTrackPosition.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimTrackPosition.setId(id);
            cimTrackPositionMapper.insertCimTrackPosition(cimTrackPosition);
        }else{
            CimTrackPosition dbCimTrackPosition = cimTrackPositionMapper.selectCimTrackPositionById(cimTrackPosition.getId());
            if(dbCimTrackPosition!=null&&dbCimTrackPosition.getId()!=null){
                cimTrackPositionMapper.updateCimTrackPosition(cimTrackPosition);
            }else{
                cimTrackPositionMapper.insertCimTrackPosition(cimTrackPosition);
            }
        }
        return cimTrackPosition.getId();
    }

    /**
     * 修改轨迹位置
     *
     * @param cimTrackPosition 轨迹位置
     * @return 结果
     */
    @Override
    public String updateCimTrackPosition(CimTrackPosition cimTrackPosition)
    {
        cimTrackPositionMapper.updateCimTrackPosition(cimTrackPosition);
        return cimTrackPosition.getId();
    }

    /**
     * 批量删除轨迹位置
     *
     * @param ids 需要删除的轨迹位置主键
     * @return 结果
     */
    @Override
    public int deleteCimTrackPositionByIds(String[] ids)
    {
        return cimTrackPositionMapper.deleteCimTrackPositionByIds(ids);
    }

    /**
     * 删除轨迹位置信息
     *
     * @param id 轨迹位置主键
     * @return 结果
     */
    @Override
    public int deleteCimTrackPositionById(String id)
    {
        return cimTrackPositionMapper.deleteCimTrackPositionById(id);
    }



}
