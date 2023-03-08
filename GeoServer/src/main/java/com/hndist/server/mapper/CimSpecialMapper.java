package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimSpecial;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimSpecial
 * @Description: 专题图
 * @author AI Builder
 * @date 2022-12-02 05:26:19
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimSpecialMapper
{
    /**
     * 查询专题图
     *
     * @param id 专题图主键
     * @return 专题图
     */
    public CimSpecial selectCimSpecialById(@Param("id") String id);

    /**
     * 查询专题图列表
     *
     * @param cimSpecial 专题图
     * @return 专题图集合
     */
    public List<CimSpecial> selectCimSpecialList(@Param("vo") CimSpecial cimSpecial);

    /**
     * 新增专题图
     *
     * @param cimSpecial 专题图
     * @return 结果
     */
    public int insertCimSpecial(CimSpecial cimSpecial);

    /**
     * 修改专题图
     *
     * @param cimSpecial 专题图
     * @return 结果
     */
    public int updateCimSpecial(CimSpecial cimSpecial);

    /**
     * 删除专题图
     *
     * @param id 专题图主键
     * @return 结果
     */
    public int deleteCimSpecialById(String id);

    /**
     * 批量专题图
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimSpecialByIds(String[] ids);
    /**
     * 查询专题图树信息
     *
     * @param type
     * @return 专题图集合
     */
    public List<CimSpecial> selectCimSpecialTreeList(String type);

}
