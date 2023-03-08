package com.hndist.server.service.impl;

import com.hndist.framework.constant.UserConstants;
import com.hndist.framework.utils.StringUtils;
import com.hndist.server.domain.OmpMenuBase;
import com.hndist.server.domain.OmpMenuSys;
import com.hndist.server.domain.OmpSysInfo;
import com.hndist.server.mapper.OmpMenuBaseMapper;
import com.hndist.server.mapper.OmpMenuSysMapper;
import com.hndist.server.mapper.OmpSysInfoMapper;
import com.hndist.server.service.IOmpSysInfoService;
import com.hndist.server.util.CustomArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: OmpSysInfo
 * @Description: 一张图系统信息表服务实现
 * @author AI Builder
 * @date 2023-02-09 10:17:07
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class OmpSysInfoServiceImpl implements IOmpSysInfoService
{
    @Autowired
    private OmpSysInfoMapper ompSysInfoMapper;
    @Autowired
    private OmpMenuBaseMapper ompMenuBaseMapper;
    @Autowired
    private OmpMenuSysMapper ompMenuSysMapper;


    /**
     * 查询一张图系统信息表
     *
     * @param id 一张图系统信息表主键
     * @return 一张图系统信息表
     */
    @Override
    public OmpSysInfo selectOmpSysInfoById(Long id)
    {
        OmpSysInfo ompSysInfo = ompSysInfoMapper.selectOmpSysInfoById(id);
        return ompSysInfo;
    }

    /**
     * 查询一张图系统信息表列表
     *
     * @param ompSysInfo 一张图系统信息表
     * @return 一张图系统信息表
     */
    @Override
    public List<OmpSysInfo> selectOmpSysInfoList(OmpSysInfo ompSysInfo)
    {
        return ompSysInfoMapper.selectOmpSysInfoList(ompSysInfo);
    }

    /**
     * 新增一张图系统信息表
     *
     * @param ompSysInfo 一张图系统信息表
     * @return 结果
     */
    @Override
    public Long insertOmpSysInfo(OmpSysInfo ompSysInfo)
    {
        //1.新增系统信息
        ompSysInfoMapper.insertOmpSysInfo(ompSysInfo);

        // 这个是先根据ID分配菜单，后修改菜单信息的模式的，现在是分配完直接提交整个菜单信息了
//        List<OmpMenuBase> menuBaseList = ompMenuBaseMapper.selectOmpMenuBaseByIds(ompSysInfo.getMenuIds());
//        ompMenuSysMapper.batchMenuSys(menuBaseList,ompSysInfo.getId());

        //2.新增系统菜单
        List<OmpMenuSys> sysList = ompSysInfo.getMenuList();
        if(null!=sysList&&sysList.size()>0){
            sysList.stream().forEach(item->{
                item.setSystemId(ompSysInfo.getId());
                item.setDelFlag("0");
            });
            ompMenuSysMapper.batchMenuSys(sysList,ompSysInfo.getId());
        }
        return ompSysInfo.getId();
    }

    /**
     * 修改一张图系统信息表
     *
     * @param ompSysInfo 一张图系统信息表
     * @return 结果
     */
    @Override
    @Transactional
    public Long updateOmpSysInfo(OmpSysInfo ompSysInfo)
    {
        ompSysInfoMapper.updateOmpSysInfo(ompSysInfo);

//        // 获取更新前后的菜单ID集合
//        Long[] dbMenuIds = ompMenuSysMapper.selectMenuIdsBySysId(ompSysInfo.getId());
//        Long[] newMenuIds = ompSysInfo.getMenuIds();
//
//        // 求新旧菜单ID差集
//        Long[] diff = CustomArrayUtils.intersection(newMenuIds, dbMenuIds);
//
//        //更新前集合，减去差集，是需要删除的部分
//        Long[] db_diff = CustomArrayUtils.intersection(dbMenuIds, diff);
//        ompMenuSysMapper.deleteOmpMenuSysByIds(db_diff);
//
//        //更新后集合，减去差集，是需要新增的部分
//        Long[] new_diff = CustomArrayUtils.intersection(newMenuIds, diff);
//        List<OmpMenuBase> menuBaseList = ompMenuBaseMapper.selectOmpMenuBaseByIds(new_diff);
//        ompMenuSysMapper.batchMenuSys(menuBaseList,ompSysInfo.getId());

        //由于系统信息页面前端可以直接进行菜单分配后的修改删除再提交，这里直接全删替换
        ompMenuSysMapper.deleteOmpMenuSysBySysId(ompSysInfo.getId());
        ompMenuSysMapper.batchMenuSys(ompSysInfo.getMenuList(),ompSysInfo.getId());
        return ompSysInfo.getId();
    }

    /**
     * 批量删除一张图系统信息表
     *
     * @param ids 需要删除的一张图系统信息表主键
     * @return 结果
     */
    @Override
    public int deleteOmpSysInfoByIds(Long[] ids)
    {
        return ompSysInfoMapper.deleteOmpSysInfoByIds(ids);
    }

    /**
     * 删除一张图系统信息表信息
     *
     * @param id 一张图系统信息表主键
     * @return 结果
     */
    @Override
    public int deleteOmpSysInfoById(Long id)
    {
        return ompSysInfoMapper.deleteOmpSysInfoById(id);
    }

    @Override
    public List<Long> selectMenuListBySystemId(Long systemId) {
        return ompSysInfoMapper.selectMenuListBySystemId(systemId);
    }

    /**
     * 校验系统名称是否唯一
     */
    @Override
    public String checkSystemNameUnique(OmpSysInfo info) {
        Long sysId = StringUtils.isNull(info.getId()) ? -1L : info.getId();
        OmpSysInfo ompSysInfo = ompSysInfoMapper.checkSystemNameUnique(info.getSysName());
        if (StringUtils.isNotNull(ompSysInfo) && ompSysInfo.getId().longValue() != sysId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验系统权限码是否唯一
     */
    @Override
    public String checkSystemKeyUnique(OmpSysInfo info) {
        Long sysId = StringUtils.isNull(info.getId()) ? -1L : info.getId();
        OmpSysInfo ompSysInfo = ompSysInfoMapper.checkSystemKeyUnique(info.getSysKey());
        if (StringUtils.isNotNull(ompSysInfo) && ompSysInfo.getId().longValue() != sysId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }


}
