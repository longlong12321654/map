package com.hndist.server.service;
import com.hndist.server.domain.CimCityExperience;
import java.util.List;
/**
 * @Title: CimCityExperience
 * @Description: 城市体验服务接口
 * @author AI Builder
 * @date 2023-01-11 07:11:11
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimCityExperienceService
{
    /**
     * 查询城市体验
     *
     * @param id 城市体验主键
     * @return 城市体验
     */
    public CimCityExperience selectCimCityExperienceById(String id);

    /**
     * 查询城市体验列表
     *
     * @param cimCityExperience 城市体验
     * @return 城市体验集合
     */
    public List<CimCityExperience> selectCimCityExperienceList(CimCityExperience cimCityExperience);

    /**
     * 新增城市体验
     *
     * @param cimCityExperience 城市体验
     * @return 结果
     */
    public String insertCimCityExperience(CimCityExperience cimCityExperience);

    /**
     * 修改城市体验
     *
     * @param cimCityExperience 城市体验
     * @return 结果
     */
    public String updateCimCityExperience(CimCityExperience cimCityExperience);

    /**
     * 批量删除城市体验
     *
     * @param ids 需要删除的城市体验主键集合
     * @return 结果
     */
    public int deleteCimCityExperienceByIds(String[] ids);

    /**
     * 删除城市体验信息
     *
     * @param id 城市体验主键
     * @return 结果
     */
    public int deleteCimCityExperienceById(String id);
    /**
     * 查询城市体验树信息
     * @param type 为空时返回所有目录 不为空时返回所有信息
     * @return 城市体验集合
     */
    public List<CimCityExperience> selectCimCityExperienceTreeList(String type);
    /**
     * 查询城市体验树节点信息
     * @param cimCityExperience 信息
     * @return 城市体验集合
     */
    public List<CimCityExperience> selectCimCityExperienceTreeNodeList(CimCityExperience cimCityExperience);
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
    int selectCimCityExperByNameExclude(String name,String id);

}
