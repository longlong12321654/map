package com.hndist.server.service.impl;
import com.hndist.server.domain.CimMapmark;
import com.hndist.server.mapper.CimMapmarkMapper;
import com.hndist.server.service.ICimMapmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.hndist.framework.core.domain.model.LoginUser;
import com.hndist.framework.utils.SecurityUtils;

/**
 * @Title: CimMapmark
 * @Description: 标绘管理服务实现
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimMapmarkServiceImpl implements ICimMapmarkService
{
    @Autowired
    private CimMapmarkMapper cimMapmarkMapper;


    /**
     * 查询标绘管理
     *
     * @param id 标绘管理主键
     * @return 标绘管理
     */
    @Override
    public CimMapmark selectCimMapmarkById(String id)
    {
        CimMapmark cimMapmark = cimMapmarkMapper.selectCimMapmarkById(id);
        return cimMapmark;
    }

    /**
     * 查询标绘管理列表
     *
     * @param cimMapmark 标绘管理
     * @return 标绘管理
     */
    @Override
    public List<CimMapmark> selectCimMapmarkList(CimMapmark cimMapmark)
    {
        return cimMapmarkMapper.selectCimMapmarkList(cimMapmark);
    }

    /**
     * 新增标绘管理
     *
     * @param cimMapmark 标绘管理
     * @return 结果
     */
    @Override
    public String insertCimMapmark(CimMapmark cimMapmark)
    {
        if("".equals(cimMapmark.getId())||cimMapmark.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimMapmark.setId(id);
            cimMapmarkMapper.insertCimMapmark(cimMapmark);
        }else{
            CimMapmark dbCimMapmark = cimMapmarkMapper.selectCimMapmarkById(cimMapmark.getId());
            if(dbCimMapmark!=null&&dbCimMapmark.getId()!=null){
                cimMapmarkMapper.updateCimMapmark(cimMapmark);
            }else{
                cimMapmarkMapper.insertCimMapmark(cimMapmark);
            }
        }
        return cimMapmark.getId();
    }

    /**
     * 修改标绘管理
     *
     * @param cimMapmark 标绘管理
     * @return 结果
     */
    @Override
    public String updateCimMapmark(CimMapmark cimMapmark)
    {
        cimMapmarkMapper.updateCimMapmark(cimMapmark);
        return cimMapmark.getId();
    }

    /**
     * 批量删除标绘管理
     *
     * @param ids 需要删除的标绘管理主键
     * @return 结果
     */
    @Override
    public int deleteCimMapmarkByIds(String[] ids)
    {
        return cimMapmarkMapper.deleteCimMapmarkByIds(ids);
    }

    /**
     * 删除标绘管理信息
     *
     * @param id 标绘管理主键
     * @return 结果
     */
    @Override
    public int deleteCimMapmarkById(String id)
    {
        return cimMapmarkMapper.deleteCimMapmarkById(id);
    }



}
