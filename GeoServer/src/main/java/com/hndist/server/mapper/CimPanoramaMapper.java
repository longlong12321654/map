package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimPanorama;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimPanorama
 * @Description: 360全景信息
 * @author AI Builder
 * @date 2022-11-15 08:37:58
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimPanoramaMapper
{
    /**
     * 查询360全景信息
     *
     * @param id 360全景信息主键
     * @return 360全景信息
     */
    public CimPanorama selectCimPanoramaById(@Param("id") String id);

    /**
     * 查询360全景信息列表
     *
     * @param cimPanorama 360全景信息
     * @return 360全景信息集合
     */
    public List<CimPanorama> selectCimPanoramaList(@Param("vo") CimPanorama cimPanorama);

    /**
     * 新增360全景信息
     *
     * @param cimPanorama 360全景信息
     * @return 结果
     */
    public int insertCimPanorama(CimPanorama cimPanorama);

    /**
     * 修改360全景信息
     *
     * @param cimPanorama 360全景信息
     * @return 结果
     */
    public int updateCimPanorama(CimPanorama cimPanorama);

    /**
     * 删除360全景信息
     *
     * @param id 360全景信息主键
     * @return 结果
     */
    public int deleteCimPanoramaById(String id);

    /**
     * 批量360全景信息学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimPanoramaByIds(String[] ids);
    /**
     * 查询360全景信息树信息
     *
     * @param type
     * @return 360全景信息集合
     */
    public List<CimPanorama> selectCimPanoramaTreeList(String type);

}
