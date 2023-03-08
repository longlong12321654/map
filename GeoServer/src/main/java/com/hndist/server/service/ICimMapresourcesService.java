package com.hndist.server.service;
import com.hndist.server.domain.CimMapresources;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * @Title: CimMapresources
 * @Description: 资源目录接口
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimMapresourcesService
{
    /**
     * 查询资源目录
     *
     * @param id 资源目录主键
     * @return 资源目录
     */
    public CimMapresources selectCimMapresourcesById(String id);

    /**
     * 查询资源目录列表
     *
     * @param cimMapresources 资源目录
     * @return 资源目录集合
     */
    public List<CimMapresources> selectCimMapresourcesList(CimMapresources cimMapresources);

    /**
     * 通过pid查询资源中心列表
     * @param pid 父节点ID
     * @return
     */
    public List<CimMapresources> selectResourceContentsListByPid(String pid, HttpServletRequest request);

    List<CimMapresources> getTree(CimMapresources cimMapresources);

    /**
     * 新增资源目录
     *
     * @param cimMapresources 资源目录
     * @return 结果
     */
    public String insertCimMapresources(CimMapresources cimMapresources);

    /**
     * 修改资源目录
     *
     * @param cimMapresources 资源目录
     * @return 结果
     */
    public String updateCimMapresources(CimMapresources cimMapresources);

    /**
     * 批量删除资源目录
     *
     * @param ids 需要删除的资源目录主键集合
     * @return 结果
     */
    public int deleteCimMapresourcesByIds(String[] ids);
    /**
     * 批量查询地图服务
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public List<CimMapresources> selectCimMapresourcesByIds(String[] ids);
    /**
     * 删除资源目录信息
     *
     * @param id 资源目录主键
     * @return 结果
     */
    public int deleteCimMapresourcesById(String id);
    /**
     * 查询资源目录树信息
     * @param type 为空时返回所有目录 不为空时返回所有信息
     * @return 资源目录集合
     */
    public List<CimMapresources> selectCimMapresourcesTreeList(String type);



}
