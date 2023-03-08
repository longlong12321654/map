package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimDatasourceMeta;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimDatasourceMeta
 * @Description: 元数据管理
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimDatasourceMetaMapper
{
    /**
     * 查询元数据管理
     *
     * @param id 元数据管理主键
     * @return 元数据管理
     */
    public CimDatasourceMeta selectCimDatasourceMetaById(@Param("id") String id);

    /**
     * 查询元数据管理列表
     *
     * @param cimDatasourceMeta 元数据管理
     * @return 元数据管理集合
     */
    public List<CimDatasourceMeta> selectCimDatasourceMetaList(@Param("vo") CimDatasourceMeta cimDatasourceMeta);

    /**
     * 新增元数据管理
     *
     * @param cimDatasourceMeta 元数据管理
     * @return 结果
     */
    public int insertCimDatasourceMeta(CimDatasourceMeta cimDatasourceMeta);

    /**
     * 修改元数据管理
     *
     * @param cimDatasourceMeta 元数据管理
     * @return 结果
     */
    public int updateCimDatasourceMeta(CimDatasourceMeta cimDatasourceMeta);

    /**
     * 删除元数据管理
     *
     * @param id 元数据管理主键
     * @return 结果
     */
    public int deleteCimDatasourceMetaById(String id);

    /**
     * 批量元数据管理学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimDatasourceMetaByIds(String[] ids);

}
