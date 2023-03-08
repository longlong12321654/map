package com.hndist.server.service.impl;

import com.hndist.framework.constant.UserConstants;
import com.hndist.framework.utils.StringUtils;
import com.hndist.server.domain.OmpMenuBase;
import com.hndist.server.domain.GeoTreeSelect;
import com.hndist.server.mapper.OmpMenuBaseMapper;
import com.hndist.server.mapper.OmpMenuSysMapper;
import com.hndist.server.service.IOmpMenuBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: OmpMenuBase
 * @Description: 一张图基础菜单表服务实现
 * @author AI Builder
 * @date 2023-02-07 03:26:29
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class OmpMenuBaseServiceImpl implements IOmpMenuBaseService
{
    @Autowired
    private OmpMenuBaseMapper ompMenuBaseMapper;
    @Autowired
    private OmpMenuSysMapper ompMenuSysMapper;


    /**
     * 查询一张图基础菜单表
     *
     * @param id 一张图基础菜单表主键
     * @return 一张图基础菜单表
     */
    @Override
    public OmpMenuBase selectOmpMenuBaseById(Long id)
    {
        OmpMenuBase ompMenuBase = ompMenuBaseMapper.selectOmpMenuBaseById(id);
        return ompMenuBase;
    }

    /**
     * 查询一张图基础菜单表列表
     *
     * @param ompMenuBase 一张图基础菜单表
     * @return 一张图基础菜单表
     */
    @Override
    public List<OmpMenuBase> selectOmpMenuBaseList(OmpMenuBase ompMenuBase, Long userId)
    {
        return ompMenuBaseMapper.selectOmpMenuBaseList(ompMenuBase);
    }

    /**
     * 新增一张图基础菜单表
     *
     * @param ompMenuBase 一张图基础菜单表
     * @return 结果
     */
    @Override
    public Long insertOmpMenuBase(OmpMenuBase ompMenuBase)
    {
        if("".equals(ompMenuBase.getMenuId())||ompMenuBase.getMenuId()==null){
//            String id = UUID.randomUUID().toString().replaceAll("-","");
//            ompMenuBase.setId(id);
            ompMenuBaseMapper.insertOmpMenuBase(ompMenuBase);
        }else{
            OmpMenuBase dbOmpMenuBase = ompMenuBaseMapper.selectOmpMenuBaseById(ompMenuBase.getMenuId());
            if(dbOmpMenuBase!=null&&dbOmpMenuBase.getMenuId()!=null){
                ompMenuBaseMapper.updateOmpMenuBase(ompMenuBase);
            }else{
                ompMenuBaseMapper.insertOmpMenuBase(ompMenuBase);
            }
        }
        return ompMenuBase.getMenuId();
    }

    /**
     * 修改一张图基础菜单表
     *
     * @param ompMenuBase 一张图基础菜单表
     * @return 结果
     */
    @Override
    public Long updateOmpMenuBase(OmpMenuBase ompMenuBase)
    {
        ompMenuBaseMapper.updateOmpMenuBase(ompMenuBase);
        return ompMenuBase.getMenuId();
    }

    /**
     * 批量删除一张图基础菜单表
     *
     * @param ids 需要删除的一张图基础菜单表主键
     * @return 结果
     */
    @Override
    public int deleteOmpMenuBaseByIds(Long[] ids)
    {
        return ompMenuBaseMapper.deleteOmpMenuBaseByIds(ids);
    }

    /**
     * 删除一张图基础菜单表信息
     *
     * @param id 一张图基础菜单表主键
     * @return 结果
     */
    @Override
    public int deleteOmpMenuBaseById(Long id)
    {
        return ompMenuBaseMapper.deleteOmpMenuBaseById(id);
    }
    /**
     * 查询一张图基础菜单表树信息
     *
     * @param type
     * @return 一张图基础菜单表
     */
    @Override
    public List<OmpMenuBase> selectOmpMenuBaseTreeList(String type)
    {
        return ompMenuBaseMapper.selectOmpMenuBaseTreeList(type);
    }

    @Override
    public List<GeoTreeSelect> buildMenuTreeSelect(List<OmpMenuBase> menus) {
        List<OmpMenuBase> menuTrees = buildMenuTree(menus);
        return menuTrees.stream().map(GeoTreeSelect::new).collect(Collectors.toList());
    }

    @Override
    public String checkMenuNameUnique(OmpMenuBase menu) {
        Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        OmpMenuBase info = ompMenuBaseMapper.checkMenuNameUnique(menu.getName(), menu.getPid());
        if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    public List<OmpMenuBase> buildMenuTree(List<OmpMenuBase> menus)
    {
        List<OmpMenuBase> returnList = new ArrayList<OmpMenuBase>();
        List<Long> tempList = new ArrayList<Long>();
        for (OmpMenuBase base : menus)
        {
            tempList.add(base.getMenuId());
        }
        for (Iterator<OmpMenuBase> iterator = menus.iterator(); iterator.hasNext();)
        {
            OmpMenuBase menu = (OmpMenuBase) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getPid()))
            {
                recursionFn(menus, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<OmpMenuBase> list, OmpMenuBase t)
    {
        // 得到子节点列表
        List<OmpMenuBase> childList = getChildList(list, t);
        t.setChildren(childList);
        for (OmpMenuBase tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<OmpMenuBase> getChildList(List<OmpMenuBase> list, OmpMenuBase t)
    {
        List<OmpMenuBase> tlist = new ArrayList<OmpMenuBase>();
        Iterator<OmpMenuBase> it = list.iterator();
        while (it.hasNext())
        {
            OmpMenuBase n = (OmpMenuBase) it.next();
            if (n.getPid().longValue() == t.getMenuId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<OmpMenuBase> list, OmpMenuBase t)
    {
        return getChildList(list, t).size() > 0;
    }


    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean hasChildByMenuId(Long menuId)
    {
        int result = ompMenuBaseMapper.hasChildByMenuId(menuId);
        return result > 0;
    }

    @Override
    public boolean checkMenuExistSystem(Long menuId) {
        int result = ompMenuSysMapper.checkMenuExistSystem(menuId);
        return result > 0;
    }


}
