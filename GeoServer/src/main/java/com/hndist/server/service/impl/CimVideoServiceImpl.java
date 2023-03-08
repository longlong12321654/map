package com.hndist.server.service.impl;
import com.hndist.server.domain.CimVideo;
import com.hndist.server.mapper.CimVideoMapper;
import com.hndist.server.service.ICimVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Title: CimVideo
 * @Description: cim_video服务实现
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimVideoServiceImpl implements ICimVideoService
{
    @Autowired
    private CimVideoMapper cimVideoMapper;


    /**
     * 查询cim_video
     *
     * @param id cim_video主键
     * @return cim_video
     */
    @Override
    public CimVideo selectCimVideoById(String id)
    {
        CimVideo cimVideo = cimVideoMapper.selectCimVideoById(id);
        return cimVideo;
    }

    /**
     * 查询cim_video列表
     *
     * @param cimVideo cim_video
     * @return cim_video
     */
    @Override
    public List<CimVideo> selectCimVideoList(CimVideo cimVideo)
    {
        return cimVideoMapper.selectCimVideoList(cimVideo);
    }

    /**
     * 新增cim_video
     *
     * @param cimVideo cim_video
     * @return 结果
     */
    @Override
    public String insertCimVideo(CimVideo cimVideo)
    {
        if("".equals(cimVideo.getId())||cimVideo.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimVideo.setId(id);
            cimVideoMapper.insertCimVideo(cimVideo);
        }else{
            CimVideo dbCimVideo = cimVideoMapper.selectCimVideoById(cimVideo.getId());
            if(dbCimVideo!=null&&dbCimVideo.getId()!=null){
                cimVideoMapper.updateCimVideo(cimVideo);
            }else{
                cimVideoMapper.insertCimVideo(cimVideo);
            }
        }
        return cimVideo.getId();
    }

    /**
     * 修改cim_video
     *
     * @param cimVideo cim_video
     * @return 结果
     */
    @Override
    public String updateCimVideo(CimVideo cimVideo)
    {
        cimVideoMapper.updateCimVideo(cimVideo);
        return cimVideo.getId();
    }

    /**
     * 批量删除cim_video
     *
     * @param ids 需要删除的cim_video主键
     * @return 结果
     */
    @Override
    public int deleteCimVideoByIds(String[] ids)
    {
        return cimVideoMapper.deleteCimVideoByIds(ids);
    }

    /**
     * 删除cim_video信息
     *
     * @param id cim_video主键
     * @return 结果
     */
    @Override
    public int deleteCimVideoById(String id)
    {
        return cimVideoMapper.deleteCimVideoById(id);
    }



}
