package com.hndist.server.service.impl;
import com.hndist.server.domain.CimServer;
import com.hndist.server.mapper.CimServerMapper;
import com.hndist.server.util.PingUtils;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import com.hndist.server.service.ICimServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import static java.util.stream.Collectors.toList;

/**
 * @Title: CimServer
 * @Description: 服务管理服务实现
 * @author AI Builder
 * @date 2022-03-31 03:42:30
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimServerServiceImpl implements ICimServerService
{

    @Autowired
    private CimServerMapper cimServerMapper;

    /**
     * 查询服务管理
     *
     * @param id 服务管理主键
     * @return 服务管理
     */
    @Override
    public CimServer selectCimServerById(String id)
    {
        CimServer cimServer = cimServerMapper.selectCimServerById(id);
        if (cimServer == null) {
            return null;
        }
        return cimServer;
    }

    /**
     * 查询服务管理
     *
     * @param name 服务管理名称
     * @return 服务管理
     */
    @Override
    public CimServer selectCimServerByName(String name)
    {
        CimServer cimServer = cimServerMapper.selectCimServerByName(name);
        if (cimServer == null) {
            return null;
        }
        return cimServer;
    }

    /**
     * 查询服务管理列表
     *
     * @param cimServer 服务管理
     * @return 服务管理
     */
    @Override
    public List<CimServer> selectCimServerList(CimServer cimServer)
    {
        return cimServerMapper.selectCimServerList(cimServer);
    }

    /**
     * 新增服务管理
     *
     * @param cimServer 服务管理
     * @return 结果
     */
    @Override
    @Transactional
    public int insertCimServer(CimServer cimServer)
    {
        String id = UUID.randomUUID().toString().replaceAll("-","");
        cimServer.setId(id);
        cimServer.setLabel(cimServer.getIp());
        return cimServerMapper.insertCimServer(cimServer);
    }

    /**
     * 修改服务管理
     *
     * @param cimServer 服务管理
     * @return 结果
     */
    @Override
    @Transactional
    public int updateCimServer(CimServer cimServer)
    {
        cimServer.setLabel(cimServer.getIp());
        return cimServerMapper.updateCimServer(cimServer);
    }

    /**
     * 批量删除服务管理
     *
     * @param ids 需要删除的服务管理主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteCimServerByIds(String[] ids)
    {
        return cimServerMapper.deleteCimServerByIds(ids);
    }

    /**
     * 删除服务管理信息
     *
     * @param id 服务管理主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteCimServerById(String id)
    {
        return cimServerMapper.deleteCimServerById(id);
    }

    @Override
    public int existIpForInsert(String name) {
        return cimServerMapper.existIpForInsert(name);
    }

    @Override
    public int existIpForEdit(String name, String id) {
        return cimServerMapper.existIpForEdit(name, id);
    }


}
