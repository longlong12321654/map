package com.hndist.server.service;
import com.hndist.server.domain.CimFiles;
import java.util.List;
/**
 * @Title: CimFiles
 * @Description: 地图服务服务接口
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimFilesService
{
    /**
     * 查询地图服务
     *
     * @param id 地图服务主键
     * @return 地图服务
     */
    public CimFiles selectCimFilesById(String id);

    /**
     * 查询地图服务列表
     *
     * @param cimFiles 地图服务
     * @return 地图服务集合
     */
    public List<CimFiles> selectCimFilesList(CimFiles cimFiles);

    /**
     * 新增地图服务
     *
     * @param cimFiles 地图服务
     * @return 结果
     */
    public String insertCimFiles(CimFiles cimFiles);

    /**
     * 新增地图服务
     *
     * @param cimFiles 地图服务
     * @return 结果
     */
    public String insertCimFilesAndMeta(CimFiles cimFiles);

    /**
     * 修改地图服务
     *
     * @param cimFiles 地图服务
     * @return 结果
     */
    public String updateCimFiles(CimFiles cimFiles);

    /**
     * 批量删除地图服务
     *
     * @param ids 需要删除的地图服务主键集合
     * @return 结果
     */
    public int deleteCimFilesByIds(String[] ids);

    /**
     * 删除地图服务信息
     *
     * @param id 地图服务主键
     * @return 结果
     */
    public int deleteCimFilesById(String id);
    /**
     * 查询地图服务树信息
     * @param type 为空时返回所有目录 不为空时返回所有信息
     * @return 地图服务集合
     */
    public List<CimFiles> selectCimFilesTreeList(String type);

}
