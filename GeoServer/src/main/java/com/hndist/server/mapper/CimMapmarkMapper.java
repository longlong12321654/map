package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimMapmark;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimMapmark
 * @Description: 标绘管理
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimMapmarkMapper
{
    /**
     * 查询标绘管理
     *
     * @param id 标绘管理主键
     * @return 标绘管理
     */
    public CimMapmark selectCimMapmarkById(@Param("id") String id);

    /**
     * 查询标绘管理列表
     *
     * @param cimMapmark 标绘管理
     * @return 标绘管理集合
     */
    public List<CimMapmark> selectCimMapmarkList(@Param("vo") CimMapmark cimMapmark);

    /**
     * 新增标绘管理
     *
     * @param cimMapmark 标绘管理
     * @return 结果
     */
    public int insertCimMapmark(CimMapmark cimMapmark);

    /**
     * 修改标绘管理
     *
     * @param cimMapmark 标绘管理
     * @return 结果
     */
    public int updateCimMapmark(CimMapmark cimMapmark);

    /**
     * 删除标绘管理
     *
     * @param id 标绘管理主键
     * @return 结果
     */
    public int deleteCimMapmarkById(String id);

    /**
     * 批量标绘管理学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimMapmarkByIds(String[] ids);

}
