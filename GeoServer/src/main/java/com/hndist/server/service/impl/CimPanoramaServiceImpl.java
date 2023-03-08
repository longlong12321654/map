package com.hndist.server.service.impl;
import com.hndist.server.domain.CimPanorama;
import com.hndist.server.mapper.CimPanoramaMapper;
import com.hndist.server.service.ICimPanoramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.hndist.framework.core.domain.model.LoginUser;
import com.hndist.framework.utils.SecurityUtils;

/**
 * @Title: CimPanorama
 * @Description: 360全景信息服务实现
 * @author AI Builder
 * @date 2022-11-15 08:37:58
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimPanoramaServiceImpl implements ICimPanoramaService
{
    @Autowired
    private CimPanoramaMapper cimPanoramaMapper;


    /**
     * 查询360全景信息
     *
     * @param id 360全景信息主键
     * @return 360全景信息
     */
    @Override
    public CimPanorama selectCimPanoramaById(String id)
    {
        CimPanorama cimPanorama = cimPanoramaMapper.selectCimPanoramaById(id);
        return cimPanorama;
    }

    /**
     * 查询360全景信息列表
     *
     * @param cimPanorama 360全景信息
     * @return 360全景信息
     */
    @Override
    public List<CimPanorama> selectCimPanoramaList(CimPanorama cimPanorama)
    {
        return cimPanoramaMapper.selectCimPanoramaList(cimPanorama);
    }

    /**
     * 新增360全景信息
     *
     * @param cimPanorama 360全景信息
     * @return 结果
     */
    @Override
    public String insertCimPanorama(CimPanorama cimPanorama)
    {
        if("".equals(cimPanorama.getId())||cimPanorama.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimPanorama.setId(id);
            cimPanoramaMapper.insertCimPanorama(cimPanorama);
        }else{
            CimPanorama dbCimPanorama = cimPanoramaMapper.selectCimPanoramaById(cimPanorama.getId());
            if(dbCimPanorama!=null&&dbCimPanorama.getId()!=null){
                cimPanoramaMapper.updateCimPanorama(cimPanorama);
            }else{
                cimPanoramaMapper.insertCimPanorama(cimPanorama);
            }
        }
        return cimPanorama.getId();
    }

    /**
     * 修改360全景信息
     *
     * @param cimPanorama 360全景信息
     * @return 结果
     */
    @Override
    public String updateCimPanorama(CimPanorama cimPanorama)
    {
        cimPanoramaMapper.updateCimPanorama(cimPanorama);
        return cimPanorama.getId();
    }

    /**
     * 批量删除360全景信息
     *
     * @param ids 需要删除的360全景信息主键
     * @return 结果
     */
    @Override
    public int deleteCimPanoramaByIds(String[] ids)
    {
        return cimPanoramaMapper.deleteCimPanoramaByIds(ids);
    }

    /**
     * 删除360全景信息信息
     *
     * @param id 360全景信息主键
     * @return 结果
     */
    @Override
    public int deleteCimPanoramaById(String id)
    {
        return cimPanoramaMapper.deleteCimPanoramaById(id);
    }
    /**
     * 查询360全景信息树信息
     *
     * @param type
     * @return 360全景信息
     */
    @Override
    public List<CimPanorama> selectCimPanoramaTreeList(String type)
    {
        return cimPanoramaMapper.selectCimPanoramaTreeList(type);
    }



}
