package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimServer;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimServer
 * @Description: 服务管理
 * @author AI Builder
 * @date 2022-03-31 03:42:30
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimServerMapper
{
    /**
     * 查询服务管理
     *
     * @param id 服务管理主键
     * @return 服务管理
     */
    public CimServer selectCimServerById(@Param("id") String id);

    /**
     * 查询服务管理
     *
     * @param name 服务管理名称
     * @return 服务管理
     */
    public CimServer selectCimServerByName(@Param("name") String name);

    /**
     * 查询服务管理列表
     *
     * @param cimServer 服务管理
     * @return 服务管理集合
     */
    public List<CimServer> selectCimServerList(@Param("vo") CimServer cimServer);

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
     * 删除服务管理
     *
     * @param id 服务管理主键
     * @return 结果
     */
    public int deleteCimServerById(String id);

    /**
     * 批量服务管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimServerByIds(String[] ids);

    /**
     * 校验新增时ip+port不重复
     * @param name 服务ip
     * @return
     */
    int existIpForInsert(@Param("name") String name);

    /**
     * 校验新增时ip+port不重复
     * @param name  服务ip
     * @param id 主键
     * @return
     */
    int existIpForEdit(@Param("name") String name, @Param("id") String id);

    /**
     * 统计数量
     * @return
     */
    int selectServerCount();

}
