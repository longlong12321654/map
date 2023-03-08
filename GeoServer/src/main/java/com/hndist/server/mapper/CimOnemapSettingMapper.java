package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimOnemapSetting;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimOnemapSetting
 * @Description: 一张图配置
 * @author AI Builder
 * @date 2022-11-16 02:52:39
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimOnemapSettingMapper
{
    /**
     * 查询一张图配置
     *
     * @param id 一张图配置主键
     * @return 一张图配置
     */
    public CimOnemapSetting selectCimOnemapSettingById(@Param("id") String id);

    /**
     * 查询一张图配置列表
     *
     * @param cimOnemapSetting 一张图配置
     * @return 一张图配置集合
     */
    public List<CimOnemapSetting> selectCimOnemapSettingList(@Param("vo") CimOnemapSetting cimOnemapSetting);

    /**
     * 新增一张图配置
     *
     * @param cimOnemapSetting 一张图配置
     * @return 结果
     */
    public int insertCimOnemapSetting(CimOnemapSetting cimOnemapSetting);

    /**
     * 修改一张图配置
     *
     * @param cimOnemapSetting 一张图配置
     * @return 结果
     */
    public int updateCimOnemapSetting(CimOnemapSetting cimOnemapSetting);

    /**
     * 删除一张图配置
     *
     * @param id 一张图配置主键
     * @return 结果
     */
    public int deleteCimOnemapSettingById(String id);

    /**
     * 批量一张图配置学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimOnemapSettingByIds(String[] ids);

}
