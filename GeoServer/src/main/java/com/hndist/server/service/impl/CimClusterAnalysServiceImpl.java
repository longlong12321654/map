package com.hndist.server.service.impl;
import com.hndist.framework.mapper.SysDictDataMapper;
import com.hndist.server.domain.CimClusterAnalys;
import com.hndist.server.mapper.CimClusterAnalysMapper;
import com.hndist.server.service.ICimClusterAnalysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.hndist.framework.core.domain.model.LoginUser;
import com.hndist.framework.utils.SecurityUtils;
import org.springframework.util.CollectionUtils;

/**
 * @Title: CimClusterAnalys
 * @Description: 聚合分析服务实现
 * @author AI Builder
 * @date 2022-12-13 05:23:26
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimClusterAnalysServiceImpl implements ICimClusterAnalysService
{
    @Autowired
    private CimClusterAnalysMapper cimClusterAnalysMapper;
    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 查询聚合分析
     *
     * @param id 聚合分析主键
     * @return 聚合分析
     */
    @Override
    public CimClusterAnalys selectCimClusterAnalysById(String id)
    {
        CimClusterAnalys cimClusterAnalys = cimClusterAnalysMapper.selectCimClusterAnalysById(id);
        if (cimClusterAnalys != null) {
            String servicetype = cimClusterAnalys.getServicetype();
            cimClusterAnalys.setServicetypeText(dictDataMapper.selectDictLabel("sys_servicetype", servicetype));
        }
        return cimClusterAnalys;
    }

    /**
     * 查询聚合分析列表
     *
     * @param cimClusterAnalys 聚合分析
     * @return 聚合分析
     */
    @Override
    public List<CimClusterAnalys> selectCimClusterAnalysList(CimClusterAnalys cimClusterAnalys)
    {
        List<CimClusterAnalys> analysList = cimClusterAnalysMapper.selectCimClusterAnalysList(cimClusterAnalys);
        if (!CollectionUtils.isEmpty(analysList)) {
            analysList.forEach(item -> {
                String servicetype = item.getServicetype();
                item.setServicetypeText(dictDataMapper.selectDictLabel("sys_servicetype", servicetype));
            });
        }
        return analysList;
    }

    /**
     * 新增聚合分析
     *
     * @param cimClusterAnalys 聚合分析
     * @return 结果
     */
    @Override
    public String insertCimClusterAnalys(CimClusterAnalys cimClusterAnalys)
    {
        if("".equals(cimClusterAnalys.getId())||cimClusterAnalys.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimClusterAnalys.setId(id);
            cimClusterAnalysMapper.insertCimClusterAnalys(cimClusterAnalys);
        }else{
            CimClusterAnalys dbCimClusterAnalys = cimClusterAnalysMapper.selectCimClusterAnalysById(cimClusterAnalys.getId());
            if(dbCimClusterAnalys!=null&&dbCimClusterAnalys.getId()!=null){
                cimClusterAnalysMapper.updateCimClusterAnalys(cimClusterAnalys);
            }else{
                cimClusterAnalysMapper.insertCimClusterAnalys(cimClusterAnalys);
            }
        }
        return cimClusterAnalys.getId();
    }

    /**
     * 修改聚合分析
     *
     * @param cimClusterAnalys 聚合分析
     * @return 结果
     */
    @Override
    public String updateCimClusterAnalys(CimClusterAnalys cimClusterAnalys)
    {
        cimClusterAnalysMapper.updateCimClusterAnalys(cimClusterAnalys);
        return cimClusterAnalys.getId();
    }

    /**
     * 批量删除聚合分析
     *
     * @param ids 需要删除的聚合分析主键
     * @return 结果
     */
    @Override
    public int deleteCimClusterAnalysByIds(String[] ids)
    {
        return cimClusterAnalysMapper.deleteCimClusterAnalysByIds(ids);
    }

    /**
     * 删除聚合分析信息
     *
     * @param id 聚合分析主键
     * @return 结果
     */
    @Override
    public int deleteCimClusterAnalysById(String id)
    {
        return cimClusterAnalysMapper.deleteCimClusterAnalysById(id);
    }
    /**
     * 查询聚合分析树信息
     *
     * @param type
     * @return 聚合分析
     */
    @Override
    public List<CimClusterAnalys> selectCimClusterAnalysTreeList(String type)
    {
        return cimClusterAnalysMapper.selectCimClusterAnalysTreeList(type);
    }



}
