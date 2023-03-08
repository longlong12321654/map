package com.hndist.server.service;

import com.hndist.server.domain.OmpSysInfo;
import java.util.List;
/**
 * @Title: OmpSysInfo
 * @Description: 一张图系统信息表服务接口
 * @author AI Builder
 * @date 2023-02-09 10:17:07
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface IOmpSysInfoService
{
    /**
     * 查询一张图系统信息表
     *
     * @param id 一张图系统信息表主键
     * @return 一张图系统信息表
     */
    public OmpSysInfo selectOmpSysInfoById(Long id);

    /**
     * 查询一张图系统信息表列表
     *
     * @param ompSysInfo 一张图系统信息表
     * @return 一张图系统信息表集合
     */
    public List<OmpSysInfo> selectOmpSysInfoList(OmpSysInfo ompSysInfo);

    /**
     * 新增一张图系统信息表
     *
     * @param ompSysInfo 一张图系统信息表
     * @return 结果
     */
    public Long insertOmpSysInfo(OmpSysInfo ompSysInfo);

    /**
     * 修改一张图系统信息表
     *
     * @param ompSysInfo 一张图系统信息表
     * @return 结果
     */
    public Long updateOmpSysInfo(OmpSysInfo ompSysInfo);

    /**
     * 批量删除一张图系统信息表
     *
     * @param ids 需要删除的一张图系统信息表主键集合
     * @return 结果
     */
    public int deleteOmpSysInfoByIds(Long[] ids);

    /**
     * 删除一张图系统信息表信息
     *
     * @param id 一张图系统信息表主键
     * @return 结果
     */
    public int deleteOmpSysInfoById(Long id);

    /**
     * 根据系统ID查询分配菜单信息
     *
     * @param systemId 系统ID
     * @return 分配菜单列表
     */
    List<Long> selectMenuListBySystemId(Long systemId);

    /**
     * 校验系统名称是否唯一
     *
     * @param info 系统信息
     * @return 结果
     */
    public String checkSystemNameUnique(OmpSysInfo info);

    /**
     * 校验系统权限码是否唯一
     *
     * @param info 系统信息
     * @return 结果
     */
    public String checkSystemKeyUnique(OmpSysInfo info);

}
