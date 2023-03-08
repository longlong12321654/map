package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimMapproject;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimMapproject
 * @Description: 地图工程
 * @author AI Builder
 * @date 2022-11-14 03:18:41
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimMapprojectMapper
{
    /**
     * 查询地图工程
     *
     * @param id 地图工程主键
     * @return 地图工程
     */
    public CimMapproject selectCimMapprojectById(@Param("id") String id);

    /**
     * 查询地图工程列表
     *
     * @param cimMapproject 地图工程
     * @return 地图工程集合
     */
    public List<CimMapproject> selectCimMapprojectList(@Param("vo") CimMapproject cimMapproject);

    /**
     * 新增地图工程
     *
     * @param cimMapproject 地图工程
     * @return 结果
     */
    public int insertCimMapproject(CimMapproject cimMapproject);

    /**
     * 修改地图工程
     *
     * @param cimMapproject 地图工程
     * @return 结果
     */
    public int updateCimMapproject(CimMapproject cimMapproject);

    /**
     * 删除地图工程
     *
     * @param id 地图工程主键
     * @return 结果
     */
    public int deleteCimMapprojectById(String id);

    /**
     * 批量地图工程
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimMapprojectByIds(String[] ids);
    /**
     * 查询地图工程树信息
     *
     * @param type
     * @return 地图工程集合
     */
    public List<CimMapproject> selectCimMapprojectTreeList(String type);

}
