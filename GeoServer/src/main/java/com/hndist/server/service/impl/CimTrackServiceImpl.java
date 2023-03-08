package com.hndist.server.service.impl;
import com.hndist.server.domain.CimTrack;
import com.hndist.server.mapper.CimTrackMapper;
import com.hndist.server.service.ICimTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.hndist.framework.core.domain.model.LoginUser;
import com.hndist.framework.utils.SecurityUtils;

/**
 * @Title: CimTrack
 * @Description: 轨迹服务实现
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimTrackServiceImpl implements ICimTrackService
{
    @Autowired
    private CimTrackMapper cimTrackMapper;


    /**
     * 查询轨迹
     *
     * @param id 轨迹主键
     * @return 轨迹
     */
    @Override
    public CimTrack selectCimTrackById(String id)
    {
        CimTrack cimTrack = cimTrackMapper.selectCimTrackById(id);
        return cimTrack;
    }

    /**
     * 查询轨迹列表
     *
     * @param cimTrack 轨迹
     * @return 轨迹
     */
    @Override
    public List<CimTrack> selectCimTrackList(CimTrack cimTrack)
    {
        return cimTrackMapper.selectCimTrackList(cimTrack);
    }

    /**
     * 新增轨迹
     *
     * @param cimTrack 轨迹
     * @return 结果
     */
    @Override
    public String insertCimTrack(CimTrack cimTrack)
    {
        if("".equals(cimTrack.getId())||cimTrack.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimTrack.setId(id);
            cimTrackMapper.insertCimTrack(cimTrack);
        }else{
            CimTrack dbCimTrack = cimTrackMapper.selectCimTrackById(cimTrack.getId());
            if(dbCimTrack!=null&&dbCimTrack.getId()!=null){
                cimTrackMapper.updateCimTrack(cimTrack);
            }else{
                cimTrackMapper.insertCimTrack(cimTrack);
            }
        }
        return cimTrack.getId();
    }

    /**
     * 修改轨迹
     *
     * @param cimTrack 轨迹
     * @return 结果
     */
    @Override
    public String updateCimTrack(CimTrack cimTrack)
    {
        cimTrackMapper.updateCimTrack(cimTrack);
        return cimTrack.getId();
    }

    /**
     * 批量删除轨迹
     *
     * @param ids 需要删除的轨迹主键
     * @return 结果
     */
    @Override
    public int deleteCimTrackByIds(String[] ids)
    {
        return cimTrackMapper.deleteCimTrackByIds(ids);
    }

    /**
     * 删除轨迹信息
     *
     * @param id 轨迹主键
     * @return 结果
     */
    @Override
    public int deleteCimTrackById(String id)
    {
        return cimTrackMapper.deleteCimTrackById(id);
    }
    /**
     * 查询轨迹树信息
     *
     * @param type
     * @return 轨迹
     */
    @Override
    public List<CimTrack> selectCimTrackTreeList(String type)
    {
        return cimTrackMapper.selectCimTrackTreeList(type);
    }



}
