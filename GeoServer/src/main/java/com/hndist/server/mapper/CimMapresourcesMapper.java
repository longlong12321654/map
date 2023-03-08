package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimMapresources;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimMapresources
 * @Description: 地图服务
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimMapresourcesMapper
{
    /**
     * 查询地图服务
     *
     * @param id 地图服务主键
     * @return 地图服务
     */
    public CimMapresources selectCimMapresourcesById(@Param("id") String id);

    /**
     * 查询地图服务列表
     *
     * @param cimMapresources 地图服务
     * @return 地图服务集合
     */
    public List<CimMapresources> selectCimMapresourcesList(@Param("vo") CimMapresources cimMapresources);


    /**
     * 通过pid查询地图服务列表
     *
     * @param pid 父id
     * @return 资源中心集合
     */
    public List<CimMapresources> selectCimMapresourcesListByPid(String pid);
    /**
     * 新增地图服务
     *
     * @param cimMapresources 地图服务
     * @return 结果
     */
    public int insertCimMapresources(CimMapresources cimMapresources);

    /**
     * 修改地图服务
     *
     * @param cimMapresources 地图服务
     * @return 结果
     */
    public int updateCimMapresources(CimMapresources cimMapresources);

    /**
     * 删除地图服务
     *
     * @param id 地图服务主键
     * @return 结果
     */
    public int deleteCimMapresourcesById(String id);

    /**
     * 批量地图服务学生
     *
     * @param ids 需要删除的数据主键集合
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
     * 查询地图服务树信息
     *
     * @param type
     * @return 地图服务集合
     */
    public List<CimMapresources> selectCimMapresourcesTreeList(String type);

    /**
     * 校验保存目录名
     * @param name 目录名
     * @return
     */
    int verifyLabelBySave(String name);
    /**
     * 校验保存目录名
     * @param name 目录名
     * @param id  主键id
     * @return
     */
    int verifyLabelByEdit(@Param("name") String name, @Param("id") String id);

}
