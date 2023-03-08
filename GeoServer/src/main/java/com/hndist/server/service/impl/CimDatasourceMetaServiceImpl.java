package com.hndist.server.service.impl;
import com.hndist.server.domain.CimDatasourceMeta;
import com.hndist.server.mapper.CimDatasourceMetaMapper;
import com.hndist.server.mapper.CimFilesMapper;
import com.hndist.server.service.ICimDatasourceMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Title: CimDatasourceMeta
 * @Description: 元数据管理服务实现
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimDatasourceMetaServiceImpl implements ICimDatasourceMetaService
{
    @Autowired
    private CimDatasourceMetaMapper cimDatasourceMetaMapper;


    /**
     * 查询元数据管理
     *
     * @param id 元数据管理主键
     * @return 元数据管理
     */
    @Override
    public CimDatasourceMeta selectCimDatasourceMetaById(String id)
    {
        CimDatasourceMeta cimDatasourceMeta = cimDatasourceMetaMapper.selectCimDatasourceMetaById(id);
        return cimDatasourceMeta;
    }

    /**
     * 查询元数据管理列表
     *
     * @param cimDatasourceMeta 元数据管理
     * @return 元数据管理
     */
    @Override
    public List<CimDatasourceMeta> selectCimDatasourceMetaList(CimDatasourceMeta cimDatasourceMeta)
    {
        return cimDatasourceMetaMapper.selectCimDatasourceMetaList(cimDatasourceMeta);
    }

    /**
     * 新增元数据管理
     *
     * @param cimDatasourceMeta 元数据管理
     * @return 结果
     */
    @Override
    public String insertCimDatasourceMeta(CimDatasourceMeta cimDatasourceMeta)
    {
        if("".equals(cimDatasourceMeta.getId())||cimDatasourceMeta.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimDatasourceMeta.setId(id);
            cimDatasourceMetaMapper.insertCimDatasourceMeta(cimDatasourceMeta);
        }else{
            CimDatasourceMeta dbCimDatasourceMeta = cimDatasourceMetaMapper.selectCimDatasourceMetaById(cimDatasourceMeta.getId());
            if(dbCimDatasourceMeta!=null&&dbCimDatasourceMeta.getId()!=null){
                cimDatasourceMetaMapper.updateCimDatasourceMeta(cimDatasourceMeta);
            }else{
                cimDatasourceMetaMapper.insertCimDatasourceMeta(cimDatasourceMeta);
            }
        }
        return cimDatasourceMeta.getId();
    }

    /**
     * 修改元数据管理
     *
     * @param cimDatasourceMeta 元数据管理
     * @return 结果
     */
    @Override
    public String updateCimDatasourceMeta(CimDatasourceMeta cimDatasourceMeta)
    {
        cimDatasourceMetaMapper.updateCimDatasourceMeta(cimDatasourceMeta);
        return cimDatasourceMeta.getId();
    }

    /**
     * 批量删除元数据管理
     *
     * @param ids 需要删除的元数据管理主键
     * @return 结果
     */
    @Override
    public int deleteCimDatasourceMetaByIds(String[] ids)
    {
        return cimDatasourceMetaMapper.deleteCimDatasourceMetaByIds(ids);
    }

    /**
     * 删除元数据管理信息
     *
     * @param id 元数据管理主键
     * @return 结果
     */
    @Override
    public int deleteCimDatasourceMetaById(String id)
    {
        return cimDatasourceMetaMapper.deleteCimDatasourceMetaById(id);
    }



}
