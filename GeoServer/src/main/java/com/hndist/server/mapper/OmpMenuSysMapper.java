package com.hndist.server.mapper;

import java.util.List;

import com.hndist.server.domain.OmpMenuBase;
import com.hndist.server.domain.OmpMenuSys;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: OmpMenuSys
 * @Description: 一张图菜单权限表
 * @author AI Builder
 * @date 2023-02-07 03:26:29
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface OmpMenuSysMapper
{
    /**
     * 查询一张图菜单权限表
     *
     * @param id 一张图菜单权限表主键
     * @return 一张图菜单权限表
     */
    public OmpMenuSys selectOmpMenuSysById(@Param("id") Long id);

    /**
     * 查询一张图菜单权限表列表
     *
     * @param ompMenuSys 一张图菜单权限表
     * @return 一张图菜单权限表集合
     */
    public List<OmpMenuSys> selectOmpMenuSysList(@Param("vo") OmpMenuSys ompMenuSys);

    /**
     * 新增一张图菜单权限表
     *
     * @param ompMenuSys 一张图菜单权限表
     * @return 结果
     */
    public int insertOmpMenuSys(OmpMenuSys ompMenuSys);

    /**
     * 修改一张图菜单权限表
     *
     * @param ompMenuSys 一张图菜单权限表
     * @return 结果
     */
    public int updateOmpMenuSys(OmpMenuSys ompMenuSys);

    /**
     * 删除一张图菜单权限表
     *
     * @param id 一张图菜单权限表主键
     * @return 结果
     */
    public int deleteOmpMenuSysById(Long id);

    /**
     * 批量删除一张图菜单权限表
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOmpMenuSysByIds(Long[] ids);
    /**
     * 查询一张图菜单权限表树信息
     *
     * @param type
     * @return 一张图菜单权限表集合
     */
    public List<OmpMenuSys> selectOmpMenuSysTreeList(String type);

    /**
     * 查询系统菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    int checkMenuExistSystem(Long menuId);

    int batchMenuSys(@Param("menus") List<OmpMenuSys> menuList, @Param("sysId") Long id);

    public Long[] selectMenuIdsBySysId(Long id);

    int deleteOmpMenuSysBySysId(Long id);

}
