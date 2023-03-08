package com.hndist.server.service;

import com.hndist.server.domain.OmpMenuSys;
import com.hndist.server.domain.GeoTreeSelect;

import java.util.List;
/**
 * @Title: OmpMenuSys
 * @Description: 一张图系统菜单表服务接口
 * @author AI Builder
 * @date 2023-02-07 03:26:29
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface IOmpMenuSysService
{
    /**
     * 查询一张图系统菜单表
     *
     * @param id 一张图系统菜单表主键
     * @return 一张图系统菜单表
     */
    public OmpMenuSys selectOmpMenuSysById(Long id);

    /**
     * 查询一张图系统菜单表列表
     *
     * @param ompMenuSys 一张图系统菜单表
     * @return 一张图系统菜单表集合
     */
    public List<OmpMenuSys> selectOmpMenuSysList(OmpMenuSys ompMenuSys);

    /**
     * 新增一张图系统菜单表
     *
     * @param ompMenuSys 一张图系统菜单表
     * @return 结果
     */
    public Long insertOmpMenuSys(OmpMenuSys ompMenuSys);

    /**
     * 修改一张图系统菜单表
     *
     * @param ompMenuSys 一张图系统菜单表
     * @return 结果
     */
    public Long updateOmpMenuSys(OmpMenuSys ompMenuSys);

    /**
     * 批量删除一张图系统菜单表
     *
     * @param ids 需要删除的一张图系统菜单表主键集合
     * @return 结果
     */
    public int deleteOmpMenuSysByIds(Long[] ids);

    /**
     * 删除一张图系统菜单表信息
     *
     * @param id 一张图系统菜单表主键
     * @return 结果
     */
    public int deleteOmpMenuSysById(Long id);
    /**
     * 查询一张图系统菜单表树信息
     * @param type 为空时返回所有目录 不为空时返回所有信息
     * @return 一张图系统菜单表集合
     */
    public List<OmpMenuSys> selectOmpMenuSysTreeList(String type);

    public List<GeoTreeSelect>  buildMenuTreeSelect(List<OmpMenuSys> menus);

    void deleteOmpMenuSysBySysId(Long id);
}
