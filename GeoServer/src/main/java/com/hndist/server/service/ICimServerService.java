package com.hndist.server.service;
import com.hndist.server.domain.CimServer;
import com.hndist.framework.core.domain.AjaxResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * @Title: CimServer
 * @Description: 服务管理服务接口
 * @author AI Builder
 * @date 2022-03-31 03:42:30
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimServerService
{

    /**
     * 查询服务管理
     *
     * @param id 服务管理主键
     * @return 服务管理
     */
    public CimServer selectCimServerById(String id);

    /**
     * 查询服务管理
     *
     * @param name 服务管理主键
     * @return 服务管理
     */
    public CimServer selectCimServerByName(String name);

    /**
     * 查询服务管理列表
     *
     * @param cimServer 服务管理
     * @return 服务管理集合
     */
    public List<CimServer> selectCimServerList(CimServer cimServer);

    /**
     * 新增服务管理
     *
     * @param cimServer 服务管理
     * @return 结果
     */
    public int insertCimServer(CimServer cimServer);

    /**
     * 修改服务管理
     *
     * @param cimServer 服务管理
     * @return 结果
     */
    public int updateCimServer(CimServer cimServer);

    /**
     * 批量删除服务管理
     *
     * @param ids 需要删除的服务管理主键集合
     * @return 结果
     */
    public int deleteCimServerByIds(String[] ids);

    /**
     * 删除服务管理信息
     *
     * @param id 服务管理主键
     * @return 结果
     */
    public int deleteCimServerById(String id);


    /**
     * 校验新增时不重复
     * @param name 服务名称
     * @return
     */
    int existIpForInsert(String name);

    /**
     * 校验新增时不重复
     * @param name  服务名称
     * @param id 主键
     * @return
     */
    int existIpForEdit(String name, String id);

}
