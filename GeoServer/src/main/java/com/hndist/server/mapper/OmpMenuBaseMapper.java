package com.hndist.server.mapper;

import java.util.List;
import com.hndist.server.domain.OmpMenuBase;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: OmpMenuBase
 * @Description: 一张图菜单权限表
 * @author AI Builder
 * @date 2023-02-07 03:26:29
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface OmpMenuBaseMapper
{
    /**
     * 查询一张图菜单权限表
     *
     * @param id 一张图菜单权限表主键
     * @return 一张图菜单权限表
     */
    public OmpMenuBase selectOmpMenuBaseById(@Param("id") Long id);

    /**
     * 查询一张图菜单权限表列表
     *
     * @param ompMenuBase 一张图菜单权限表
     * @return 一张图菜单权限表集合
     */
    public List<OmpMenuBase> selectOmpMenuBaseList(@Param("vo") OmpMenuBase ompMenuBase);

    /**
     * 新增一张图菜单权限表
     *
     * @param ompMenuBase 一张图菜单权限表
     * @return 结果
     */
    public int insertOmpMenuBase(OmpMenuBase ompMenuBase);

    /**
     * 修改一张图菜单权限表
     *
     * @param ompMenuBase 一张图菜单权限表
     * @return 结果
     */
    public int updateOmpMenuBase(OmpMenuBase ompMenuBase);

    /**
     * 删除一张图菜单权限表
     *
     * @param id 一张图菜单权限表主键
     * @return 结果
     */
    public int deleteOmpMenuBaseById(Long id);

    /**
     * 批量删除一张图菜单权限表
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOmpMenuBaseByIds(Long[] ids);
    /**
     * 查询一张图菜单权限表树信息
     *
     * @param type
     * @return 一张图菜单权限表集合
     */
    public List<OmpMenuBase> selectOmpMenuBaseTreeList(String type);

    /**
     * 校验菜单名称是否唯一
     *
     * @param name 菜单名称
     * @param pid 父菜单ID
     * @return 结果
     */
    OmpMenuBase checkMenuNameUnique(@Param("name") String name, @Param("pid") Long pid);

    int hasChildByMenuId(Long menuId);

    List<OmpMenuBase> selectOmpMenuBaseByIds(Long[] menuIds);

}
