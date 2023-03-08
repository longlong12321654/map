package com.hndist.server.service;
import com.hndist.server.domain.CimOnemapSetting;
import java.util.List;
/**
 * @Title: CimOnemapSetting
 * @Description: 一张图配置服务接口
 * @author AI Builder
 * @date 2022-11-16 02:52:39
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimOnemapSettingService
{
    /**
     * 查询一张图配置
     *
     * @param id 一张图配置主键
     * @return 一张图配置
     */
    public CimOnemapSetting selectCimOnemapSettingById(String id);

    /**
     * 查询一张图配置列表
     *
     * @param cimOnemapSetting 一张图配置
     * @return 一张图配置集合
     */
    public List<CimOnemapSetting> selectCimOnemapSettingList(CimOnemapSetting cimOnemapSetting);

    /**
     * 新增一张图配置
     *
     * @param cimOnemapSetting 一张图配置
     * @return 结果
     */
    public String insertCimOnemapSetting(CimOnemapSetting cimOnemapSetting);

    /**
     * 修改一张图配置
     *
     * @param cimOnemapSetting 一张图配置
     * @return 结果
     */
    public String updateCimOnemapSetting(CimOnemapSetting cimOnemapSetting);

    /**
     * 批量删除一张图配置
     *
     * @param ids 需要删除的一张图配置主键集合
     * @return 结果
     */
    public int deleteCimOnemapSettingByIds(String[] ids);

    /**
     * 删除一张图配置信息
     *
     * @param id 一张图配置主键
     * @return 结果
     */
    public int deleteCimOnemapSettingById(String id);

}
