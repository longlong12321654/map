package com.hndist.server.service;
import com.hndist.server.domain.CimSetting;
import java.util.List;
/**
 * @Title: CimSetting
 * @Description: 基础配置服务接口
 * @author AI Builder
 * @date 2022-11-16 02:52:39
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimSettingService
{
    /**
     * 查询基础配置
     *
     * @param id 基础配置主键
     * @return 基础配置
     */
    public CimSetting selectCimSettingById(String id);

    /**
     * 查询基础配置列表
     *
     * @param cimSetting 基础配置
     * @return 基础配置集合
     */
    public List<CimSetting> selectCimSettingList(CimSetting cimSetting);

    /**
     * 新增基础配置
     *
     * @param cimSetting 基础配置
     * @return 结果
     */
    public String insertCimSetting(CimSetting cimSetting);

    /**
     * 修改基础配置
     *
     * @param cimSetting 基础配置
     * @return 结果
     */
    public String updateCimSetting(CimSetting cimSetting);

    /**
     * 批量删除基础配置
     *
     * @param ids 需要删除的基础配置主键集合
     * @return 结果
     */
    public int deleteCimSettingByIds(String[] ids);

    /**
     * 删除基础配置信息
     *
     * @param id 基础配置主键
     * @return 结果
     */
    public int deleteCimSettingById(String id);

}
