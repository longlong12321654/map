package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimBaseMap;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimBaseMap
 * @Description: 底图服务
 * @author AI Builder
 * @date 2022-11-15 08:03:48
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimBaseMapMapper
{
    /**
     * 查询底图服务
     *
     * @param id 底图服务主键
     * @return 底图服务
     */
    public CimBaseMap selectCimBaseMapById(@Param("id") String id);

    /**
     * 查询底图服务列表
     *
     * @param cimBaseMap 底图服务
     * @return 底图服务集合
     */
    public List<CimBaseMap> selectCimBaseMapList(@Param("vo") CimBaseMap cimBaseMap);

    /**
     * 新增底图服务
     *
     * @param cimBaseMap 底图服务
     * @return 结果
     */
    public int insertCimBaseMap(CimBaseMap cimBaseMap);

    /**
     * 修改底图服务
     *
     * @param cimBaseMap 底图服务
     * @return 结果
     */
    public int updateCimBaseMap(CimBaseMap cimBaseMap);

    /**
     * 删除底图服务
     *
     * @param id 底图服务主键
     * @return 结果
     */
    public int deleteCimBaseMapById(String id);

    /**
     * 批量底图服务学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimBaseMapByIds(String[] ids);

}
