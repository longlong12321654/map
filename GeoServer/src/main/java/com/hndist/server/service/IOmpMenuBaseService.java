package com.hndist.server.service;

import com.hndist.server.domain.GeoTreeSelect;
import com.hndist.server.domain.OmpMenuBase;
import java.util.List;
/**
 * @Title: OmpMenuBase
 * @Description: 一张图菜单权限表服务接口
 * @author AI Builder
 * @date 2023-02-07 03:26:29
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface IOmpMenuBaseService
{
    /**
     * 查询一张图菜单权限表
     *
     * @param id 一张图菜单权限表主键
     * @return 一张图菜单权限表
     */
    public OmpMenuBase selectOmpMenuBaseById(Long id);

    /**
     * 查询一张图菜单权限表列表
     *
     * @param ompMenuBase 一张图菜单权限表
     * @return 一张图菜单权限表集合
     */
    public List<OmpMenuBase> selectOmpMenuBaseList(OmpMenuBase ompMenuBase, Long userId);

    /**
     * 新增一张图菜单权限表
     *
     * @param ompMenuBase 一张图菜单权限表
     * @return 结果
     */
    public Long insertOmpMenuBase(OmpMenuBase ompMenuBase);

    /**
     * 修改一张图菜单权限表
     *
     * @param ompMenuBase 一张图菜单权限表
     * @return 结果
     */
    public Long updateOmpMenuBase(OmpMenuBase ompMenuBase);

    /**
     * 批量删除一张图菜单权限表
     *
     * @param ids 需要删除的一张图菜单权限表主键集合
     * @return 结果
     */
    public int deleteOmpMenuBaseByIds(Long[] ids);

    /**
     * 删除一张图菜单权限表信息
     *
     * @param id 一张图菜单权限表主键
     * @return 结果
     */
    public int deleteOmpMenuBaseById(Long id);
    /**
     * 查询一张图菜单权限表树信息
     * @param type 为空时返回所有目录 不为空时返回所有信息
     * @return 一张图菜单权限表集合
     */
    List<OmpMenuBase> selectOmpMenuBaseTreeList(String type);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    List<GeoTreeSelect> buildMenuTreeSelect(List<OmpMenuBase> menus);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    String checkMenuNameUnique(OmpMenuBase menu);

    /**
     * 是否存在菜单子节点
     *
     * @param id 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    boolean hasChildByMenuId(Long id);

    /**
     * 查询菜单是否存在系统分配
     *
     * @param id 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    boolean checkMenuExistSystem(Long id);
}
