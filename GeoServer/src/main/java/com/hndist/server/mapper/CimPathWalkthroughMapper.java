package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimPathWalkthrough;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimPathWalkthrough
 * @Description: 路径漫游
 * @author AI Builder
 * @date 2022-12-01 10:07:23
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimPathWalkthroughMapper
{
    /**
     * 查询路径漫游
     *
     * @param id 路径漫游主键
     * @return 路径漫游
     */
    public CimPathWalkthrough selectCimPathWalkthroughById(@Param("id") String id);

    /**
     * 查询路径漫游列表
     *
     * @param cimPathWalkthrough 路径漫游
     * @return 路径漫游集合
     */
    public List<CimPathWalkthrough> selectCimPathWalkthroughList(@Param("vo") CimPathWalkthrough cimPathWalkthrough);

    /**
     * 新增路径漫游
     *
     * @param cimPathWalkthrough 路径漫游
     * @return 结果
     */
    public int insertCimPathWalkthrough(CimPathWalkthrough cimPathWalkthrough);

    /**
     * 修改路径漫游
     *
     * @param cimPathWalkthrough 路径漫游
     * @return 结果
     */
    public int updateCimPathWalkthrough(CimPathWalkthrough cimPathWalkthrough);

    /**
     * 删除路径漫游
     *
     * @param id 路径漫游主键
     * @return 结果
     */
    public int deleteCimPathWalkthroughById(String id);

    /**
     * 批量路径漫游
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimPathWalkthroughByIds(String[] ids);

}
