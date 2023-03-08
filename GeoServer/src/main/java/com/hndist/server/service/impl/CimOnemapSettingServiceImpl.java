package com.hndist.server.service.impl;
import com.hndist.server.domain.CimOnemapSetting;
import com.hndist.server.mapper.CimOnemapSettingMapper;
import com.hndist.server.service.ICimOnemapSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.hndist.framework.core.domain.model.LoginUser;
import com.hndist.framework.utils.SecurityUtils;

/**
 * @Title: CimOnemapSetting
 * @Description: 一张图配置服务实现
 * @author AI Builder
 * @date 2022-11-16 02:52:39
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimOnemapSettingServiceImpl implements ICimOnemapSettingService
{
    @Autowired
    private CimOnemapSettingMapper cimOnemapSettingMapper;


    /**
     * 查询一张图配置
     *
     * @param id 一张图配置主键
     * @return 一张图配置
     */
    @Override
    public CimOnemapSetting selectCimOnemapSettingById(String id)
    {
        CimOnemapSetting cimOnemapSetting = cimOnemapSettingMapper.selectCimOnemapSettingById(id);
        return cimOnemapSetting;
    }

    /**
     * 查询一张图配置列表
     *
     * @param cimOnemapSetting 一张图配置
     * @return 一张图配置
     */
    @Override
    public List<CimOnemapSetting> selectCimOnemapSettingList(CimOnemapSetting cimOnemapSetting)
    {
        return cimOnemapSettingMapper.selectCimOnemapSettingList(cimOnemapSetting);
    }

    /**
     * 新增一张图配置
     *
     * @param cimOnemapSetting 一张图配置
     * @return 结果
     */
    @Override
    public String insertCimOnemapSetting(CimOnemapSetting cimOnemapSetting)
    {
        if("".equals(cimOnemapSetting.getId())||cimOnemapSetting.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimOnemapSetting.setId(id);
            cimOnemapSettingMapper.insertCimOnemapSetting(cimOnemapSetting);
        }else{
            CimOnemapSetting dbCimOnemapSetting = cimOnemapSettingMapper.selectCimOnemapSettingById(cimOnemapSetting.getId());
            if(dbCimOnemapSetting!=null&&dbCimOnemapSetting.getId()!=null){
                cimOnemapSettingMapper.updateCimOnemapSetting(cimOnemapSetting);
            }else{
                cimOnemapSettingMapper.insertCimOnemapSetting(cimOnemapSetting);
            }
        }
        return cimOnemapSetting.getId();
    }

    /**
     * 修改一张图配置
     *
     * @param cimOnemapSetting 一张图配置
     * @return 结果
     */
    @Override
    public String updateCimOnemapSetting(CimOnemapSetting cimOnemapSetting)
    {
        cimOnemapSettingMapper.updateCimOnemapSetting(cimOnemapSetting);
        return cimOnemapSetting.getId();
    }

    /**
     * 批量删除一张图配置
     *
     * @param ids 需要删除的一张图配置主键
     * @return 结果
     */
    @Override
    public int deleteCimOnemapSettingByIds(String[] ids)
    {
        return cimOnemapSettingMapper.deleteCimOnemapSettingByIds(ids);
    }

    /**
     * 删除一张图配置信息
     *
     * @param id 一张图配置主键
     * @return 结果
     */
    @Override
    public int deleteCimOnemapSettingById(String id)
    {
        return cimOnemapSettingMapper.deleteCimOnemapSettingById(id);
    }



}
