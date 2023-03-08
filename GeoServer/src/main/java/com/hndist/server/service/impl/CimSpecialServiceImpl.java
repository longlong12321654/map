package com.hndist.server.service.impl;
import com.hndist.framework.mapper.SysDictDataMapper;
import com.hndist.server.mapper.CimSpecialMapper;
import com.hndist.server.service.ICimSpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hndist.server.domain.CimSpecial;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

/**
 * @Title: CimSpecial
 * @Description: 专题图服务实现
 * @author AI Builder
 * @date 2022-12-02 05:26:19
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimSpecialServiceImpl implements ICimSpecialService
{
    @Autowired
    private CimSpecialMapper cimSpecialMapper;
    @Autowired
    private SysDictDataMapper dictDataMapper;


    /**
     * 查询专题图
     *
     * @param id 专题图主键
     * @return 专题图
     */
    @Override
    public CimSpecial selectCimSpecialById(String id)
    {
        CimSpecial cimSpecial = cimSpecialMapper.selectCimSpecialById(id);
        if (cimSpecial != null) {
            String serviceengine = cimSpecial.getServiceengine();
            cimSpecial.setServiceengineText(dictDataMapper.selectDictLabel("sys_serviceengine", serviceengine));
            cimSpecial.setServicetypeText(dictDataMapper.selectDictLabel("sys_servicetype", cimSpecial.getServicetype()));
        }
        return cimSpecial;
    }

    /**
     * 查询专题图列表
     *
     * @param cimSpecial 专题图
     * @return 专题图
     */
    @Override
    public List<CimSpecial> selectCimSpecialList(CimSpecial cimSpecial)
    {
        List<CimSpecial> cimSpecials = cimSpecialMapper.selectCimSpecialList(cimSpecial);
        if (!CollectionUtils.isEmpty(cimSpecials)) {
            cimSpecials.forEach(item-> {
                String serviceengine = item.getServiceengine();
                item.setServiceengineText(dictDataMapper.selectDictLabel("sys_serviceengine", serviceengine));
                item.setServicetypeText(dictDataMapper.selectDictLabel("sys_servicetype", item.getServicetype()));
            });
        }
        return cimSpecials;
    }

    /**
     * 新增专题图
     *
     * @param cimSpecial 专题图
     * @return 结果
     */
    @Override
    public String insertCimSpecial(CimSpecial cimSpecial)
    {
        if("".equals(cimSpecial.getId())||cimSpecial.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimSpecial.setId(id);
            cimSpecialMapper.insertCimSpecial(cimSpecial);
        }else{
            CimSpecial dbCimSpecial = cimSpecialMapper.selectCimSpecialById(cimSpecial.getId());
            if(dbCimSpecial!=null&&dbCimSpecial.getId()!=null){
                cimSpecialMapper.updateCimSpecial(cimSpecial);
            }else{
                cimSpecialMapper.insertCimSpecial(cimSpecial);
            }
        }
        return cimSpecial.getId();
    }

    /**
     * 修改专题图
     *
     * @param cimSpecial 专题图
     * @return 结果
     */
    @Override
    public String updateCimSpecial(CimSpecial cimSpecial)
    {
        cimSpecialMapper.updateCimSpecial(cimSpecial);
        return cimSpecial.getId();
    }

    /**
     * 批量删除专题图
     *
     * @param ids 需要删除的专题图主键
     * @return 结果
     */
    @Override
    public int deleteCimSpecialByIds(String[] ids)
    {
        return cimSpecialMapper.deleteCimSpecialByIds(ids);
    }

    /**
     * 删除专题图信息
     *
     * @param id 专题图主键
     * @return 结果
     */
    @Override
    public int deleteCimSpecialById(String id)
    {
        return cimSpecialMapper.deleteCimSpecialById(id);
    }
    /**
     * 查询专题图树信息
     *
     * @param type
     * @return 专题图
     */
    @Override
    public List<CimSpecial> selectCimSpecialTreeList(String type)
    {
        return cimSpecialMapper.selectCimSpecialTreeList(type);
    }



}
