package com.hndist.server.service.impl;
import com.hndist.server.domain.CimSetting;
import com.hndist.server.mapper.CimSettingMapper;
import com.hndist.server.service.ICimSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.hndist.framework.core.domain.model.LoginUser;
import com.hndist.framework.utils.SecurityUtils;

/**
 * @Title: CimSetting
 * @Description: 基础配置服务实现
 * @author AI Builder
 * @date 2022-11-16 02:52:39
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimSettingServiceImpl implements ICimSettingService
{
    @Autowired
    private CimSettingMapper cimSettingMapper;


    /**
     * 查询基础配置
     *
     * @param id 基础配置主键
     * @return 基础配置
     */
    @Override
    public CimSetting selectCimSettingById(String id)
    {
        CimSetting cimSetting = cimSettingMapper.selectCimSettingById(id);
        return cimSetting;
    }

    /**
     * 查询基础配置列表
     *
     * @param cimSetting 基础配置
     * @return 基础配置
     */
    @Override
    public List<CimSetting> selectCimSettingList(CimSetting cimSetting)
    {
        return cimSettingMapper.selectCimSettingList(cimSetting);
    }

    /**
     * 新增基础配置
     *
     * @param cimSetting 基础配置
     * @return 结果
     */
    @Override
    public String insertCimSetting(CimSetting cimSetting)
    {
        if("".equals(cimSetting.getId())||cimSetting.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimSetting.setId(id);
            cimSettingMapper.insertCimSetting(cimSetting);
        }else{
            CimSetting dbCimSetting = cimSettingMapper.selectCimSettingById(cimSetting.getId());
            if(dbCimSetting!=null&&dbCimSetting.getId()!=null){
                cimSettingMapper.updateCimSetting(cimSetting);
            }else{
                cimSettingMapper.insertCimSetting(cimSetting);
            }
        }
        return cimSetting.getId();
    }

    /**
     * 修改基础配置
     *
     * @param cimSetting 基础配置
     * @return 结果
     */
    @Override
    public String updateCimSetting(CimSetting cimSetting)
    {
        cimSettingMapper.updateCimSetting(cimSetting);
        return cimSetting.getId();
    }

    /**
     * 批量删除基础配置
     *
     * @param ids 需要删除的基础配置主键
     * @return 结果
     */
    @Override
    public int deleteCimSettingByIds(String[] ids)
    {
        return cimSettingMapper.deleteCimSettingByIds(ids);
    }

    /**
     * 删除基础配置信息
     *
     * @param id 基础配置主键
     * @return 结果
     */
    @Override
    public int deleteCimSettingById(String id)
    {
        return cimSettingMapper.deleteCimSettingById(id);
    }



}
