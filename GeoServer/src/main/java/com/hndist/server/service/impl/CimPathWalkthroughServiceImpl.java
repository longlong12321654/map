package com.hndist.server.service.impl;
import com.hndist.server.domain.CimPathWalkthrough;
import com.hndist.server.mapper.CimPathWalkthroughMapper;
import com.hndist.server.service.ICimPathWalkthroughService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.hndist.framework.core.domain.model.LoginUser;
import com.hndist.framework.utils.SecurityUtils;

/**
 * @Title: CimPathWalkthrough
 * @Description: 路径漫游服务实现
 * @author AI Builder
 * @date 2022-12-01 10:07:23
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimPathWalkthroughServiceImpl implements ICimPathWalkthroughService
{
    @Autowired
    private CimPathWalkthroughMapper cimPathWalkthroughMapper;


    /**
     * 查询路径漫游
     *
     * @param id 路径漫游主键
     * @return 路径漫游
     */
    @Override
    public CimPathWalkthrough selectCimPathWalkthroughById(String id)
    {
        CimPathWalkthrough cimPathWalkthrough = cimPathWalkthroughMapper.selectCimPathWalkthroughById(id);
        return cimPathWalkthrough;
    }

    /**
     * 查询路径漫游列表
     *
     * @param cimPathWalkthrough 路径漫游
     * @return 路径漫游
     */
    @Override
    public List<CimPathWalkthrough> selectCimPathWalkthroughList(CimPathWalkthrough cimPathWalkthrough)
    {
        return cimPathWalkthroughMapper.selectCimPathWalkthroughList(cimPathWalkthrough);
    }

    /**
     * 新增路径漫游
     *
     * @param cimPathWalkthrough 路径漫游
     * @return 结果
     */
    @Override
    public String insertCimPathWalkthrough(CimPathWalkthrough cimPathWalkthrough)
    {
        if("".equals(cimPathWalkthrough.getId())||cimPathWalkthrough.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimPathWalkthrough.setId(id);
            cimPathWalkthroughMapper.insertCimPathWalkthrough(cimPathWalkthrough);
        }else{
            CimPathWalkthrough dbCimPathWalkthrough = cimPathWalkthroughMapper.selectCimPathWalkthroughById(cimPathWalkthrough.getId());
            if(dbCimPathWalkthrough!=null&&dbCimPathWalkthrough.getId()!=null){
                cimPathWalkthroughMapper.updateCimPathWalkthrough(cimPathWalkthrough);
            }else{
                cimPathWalkthroughMapper.insertCimPathWalkthrough(cimPathWalkthrough);
            }
        }
        return cimPathWalkthrough.getId();
    }

    /**
     * 修改路径漫游
     *
     * @param cimPathWalkthrough 路径漫游
     * @return 结果
     */
    @Override
    public String updateCimPathWalkthrough(CimPathWalkthrough cimPathWalkthrough)
    {
        cimPathWalkthroughMapper.updateCimPathWalkthrough(cimPathWalkthrough);
        return cimPathWalkthrough.getId();
    }

    /**
     * 批量删除路径漫游
     *
     * @param ids 需要删除的路径漫游主键
     * @return 结果
     */
    @Override
    public int deleteCimPathWalkthroughByIds(String[] ids)
    {
        return cimPathWalkthroughMapper.deleteCimPathWalkthroughByIds(ids);
    }

    /**
     * 删除路径漫游信息
     *
     * @param id 路径漫游主键
     * @return 结果
     */
    @Override
    public int deleteCimPathWalkthroughById(String id)
    {
        return cimPathWalkthroughMapper.deleteCimPathWalkthroughById(id);
    }



}
