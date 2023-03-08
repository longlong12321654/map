package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimCityExperience;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimCityExperience
 * @Description: 城市体验
 * @author AI Builder
 * @date 2023-01-11 07:11:11
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimCityExperienceMapper
{
    /**
     * 查询城市体验
     *
     * @param id 城市体验主键
     * @return 城市体验
     */
    public CimCityExperience selectCimCityExperienceById(@Param("id") String id);

    /**
     * 查询城市体验列表
     *
     * @param cimCityExperience 城市体验
     * @return 城市体验集合
     */
    public List<CimCityExperience> selectCimCityExperienceList(@Param("vo") CimCityExperience cimCityExperience);

    /**
     * 新增城市体验
     *
     * @param cimCityExperience 城市体验
     * @return 结果
     */
    public int insertCimCityExperience(CimCityExperience cimCityExperience);

    /**
     * 修改城市体验
     *
     * @param cimCityExperience 城市体验
     * @return 结果
     */
    public int updateCimCityExperience(CimCityExperience cimCityExperience);

    /**
     * 删除城市体验
     *
     * @param id 城市体验主键
     * @return 结果
     */
    public int deleteCimCityExperienceById(String id);

    /**
     * 批量城市体验
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimCityExperienceByIds(String[] ids);
    /**
     * 查询城市体验树信息
     *
     * @param type
     * @return 城市体验集合
     */
    public List<CimCityExperience> selectCimCityExperienceTreeList(String type);

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    int selectCimCityExperByName(String name);

    /**
     * 根据名称查询排除id
     * @param name
     * @param id
     * @return
     */
    int selectCimCityExperByNameExclude(String name, String id);

}
