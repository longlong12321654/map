package com.hndist.server.service.impl;

import com.hndist.server.domain.OmpMenuSys;
import com.hndist.server.domain.GeoTreeSelect;
import com.hndist.server.mapper.OmpMenuSysMapper;
import com.hndist.server.service.IOmpMenuSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: OmpMenuSys
 * @Description: 一张图系统菜单表服务实现
 * @author AI Builder
 * @date 2023-02-07 03:26:29
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class OmpMenuSysServiceImpl implements IOmpMenuSysService
{
    @Autowired
    private OmpMenuSysMapper ompMenuSysMapper;


    /**
     * 查询一张图系统菜单表
     *
     * @param id 一张图系统菜单表主键
     * @return 一张图系统菜单表
     */
    @Override
    public OmpMenuSys selectOmpMenuSysById(Long id)
    {
        OmpMenuSys ompMenuSys = ompMenuSysMapper.selectOmpMenuSysById(id);
        return ompMenuSys;
    }

    /**
     * 查询一张图系统菜单表列表
     *
     * @param ompMenuSys 一张图系统菜单表
     * @return 一张图系统菜单表
     */
    @Override
    public List<OmpMenuSys> selectOmpMenuSysList(OmpMenuSys ompMenuSys)
    {
        return ompMenuSysMapper.selectOmpMenuSysList(ompMenuSys);
    }

    /**
     * 新增一张图系统菜单表
     *
     * @param ompMenuSys 一张图系统菜单表
     * @return 结果
     */
    @Override
    public Long insertOmpMenuSys(OmpMenuSys ompMenuSys)
    {
        ompMenuSysMapper.insertOmpMenuSys(ompMenuSys);
        return ompMenuSys.getId();
    }

    /**
     * 修改一张图系统菜单表
     *
     * @param ompMenuSys 一张图系统菜单表
     * @return 结果
     */
    @Override
    public Long updateOmpMenuSys(OmpMenuSys ompMenuSys)
    {
        ompMenuSysMapper.updateOmpMenuSys(ompMenuSys);
        return ompMenuSys.getId();
    }

    /**
     * 批量删除一张图系统菜单表
     *
     * @param ids 需要删除的一张图系统菜单表主键
     * @return 结果
     */
    @Override
    public int deleteOmpMenuSysByIds(Long[] ids)
    {
        return ompMenuSysMapper.deleteOmpMenuSysByIds(ids);
    }

    /**
     * 删除一张图系统菜单表信息
     *
     * @param id 一张图系统菜单表主键
     * @return 结果
     */
    @Override
    public int deleteOmpMenuSysById(Long id)
    {
        return ompMenuSysMapper.deleteOmpMenuSysById(id);
    }

    /**
     * 查询一张图系统菜单表树信息
     *
     * @param type
     * @return 一张图系统菜单表
     */
    @Override
    public List<OmpMenuSys> selectOmpMenuSysTreeList(String type)
    {
        return ompMenuSysMapper.selectOmpMenuSysTreeList(type);
    }

    @Override
    public List<GeoTreeSelect> buildMenuTreeSelect(List<OmpMenuSys> menus) {
        List<OmpMenuSys> menuTrees = buildMenuTree(menus);
        return menuTrees.stream().map(GeoTreeSelect::new).collect(Collectors.toList());
    }

    @Override
    public void deleteOmpMenuSysBySysId(Long id) {
        ompMenuSysMapper.deleteOmpMenuSysBySysId(id);
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    public List<OmpMenuSys> buildMenuTree(List<OmpMenuSys> menus)
    {
        List<OmpMenuSys> returnList = new ArrayList<OmpMenuSys>();
        List<Long> tempList = new ArrayList<Long>();
        for (OmpMenuSys base : menus)
        {
            tempList.add(base.getId());
        }
        for (Iterator<OmpMenuSys> iterator = menus.iterator(); iterator.hasNext();)
        {
            OmpMenuSys menu = (OmpMenuSys) iterator.next();
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
    private void recursionFn(List<OmpMenuSys> list, OmpMenuSys t)
    {
        // 得到子节点列表
        List<OmpMenuSys> childList = getChildList(list, t);
        t.setChildren(childList);
        for (OmpMenuSys tChild : childList)
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
    private List<OmpMenuSys> getChildList(List<OmpMenuSys> list, OmpMenuSys t)
    {
        List<OmpMenuSys> tlist = new ArrayList<OmpMenuSys>();
        Iterator<OmpMenuSys> it = list.iterator();
        while (it.hasNext())
        {
            OmpMenuSys n = (OmpMenuSys) it.next();
            if (n.getPid().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<OmpMenuSys> list, OmpMenuSys t)
    {
        return getChildList(list, t).size() > 0;
    }



}
