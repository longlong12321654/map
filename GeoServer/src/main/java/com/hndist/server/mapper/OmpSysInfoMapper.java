package com.hndist.server.mapper;

import java.util.List;
import com.hndist.server.domain.OmpSysInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: OmpSysInfo
 * @Description: 一张图系统信息表
 * @author AI Builder
 * @date 2023-02-09 10:17:07
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface OmpSysInfoMapper
{
    /**
     * 查询一张图系统信息表
     *
     * @param id 一张图系统信息表主键
     * @return 一张图系统信息表
     */
    public OmpSysInfo selectOmpSysInfoById(@Param("id") Long id);

    /**
     * 查询一张图系统信息表列表
     *
     * @param ompSysInfo 一张图系统信息表
     * @return 一张图系统信息表集合
     */
    public List<OmpSysInfo> selectOmpSysInfoList(@Param("vo") OmpSysInfo ompSysInfo);

    /**
     * 新增一张图系统信息表
     *
     * @param ompSysInfo 一张图系统信息表
     * @return 结果
     */
    public int insertOmpSysInfo(OmpSysInfo ompSysInfo);

    /**
     * 修改一张图系统信息表
     *
     * @param ompSysInfo 一张图系统信息表
     * @return 结果
     */
    public int updateOmpSysInfo(OmpSysInfo ompSysInfo);

    /**
     * 删除一张图系统信息表
     *
     * @param id 一张图系统信息表主键
     * @return 结果
     */
    public int deleteOmpSysInfoById(Long id);

    /**
     * 批量删除一张图系统信息表
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteOmpSysInfoByIds(Long[] ids);

    List<Long> selectMenuListBySystemId(Long systemId);

    OmpSysInfo checkSystemNameUnique(String sysName);

    OmpSysInfo checkSystemKeyUnique(String sysKey);

}
