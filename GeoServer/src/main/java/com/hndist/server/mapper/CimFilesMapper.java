package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimFiles;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimFiles
 * @Description: 地图服务
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimFilesMapper
{
    /**
     * 查询地图服务
     *
     * @param id 地图服务主键
     * @return 地图服务
     */
    public CimFiles selectCimFilesById(@Param("id") String id);

    /**
     * 查询地图服务列表
     *
     * @param cimFiles 地图服务
     * @return 地图服务集合
     */
    public List<CimFiles> selectCimFilesList(@Param("vo") CimFiles cimFiles);

    /**
     * 新增地图服务
     *
     * @param cimFiles 地图服务
     * @return 结果
     */
    public int insertCimFiles(CimFiles cimFiles);

    /**
     * 修改地图服务
     *
     * @param cimFiles 地图服务
     * @return 结果
     */
    public int updateCimFiles(CimFiles cimFiles);

    /**
     * 删除地图服务
     *
     * @param id 地图服务主键
     * @return 结果
     */
    public int deleteCimFilesById(String id);

    /**
     * 批量地图服务学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimFilesByIds(String[] ids);
    /**
     * 查询地图服务树信息
     *
     * @param type
     * @return 地图服务集合
     */
    public List<CimFiles> selectCimFilesTreeList(String type);

}
