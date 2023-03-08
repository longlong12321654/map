package com.hndist.server.mapper;

import com.hndist.server.domain.CimCity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: CimCity
 * @Description: 分类字典
 * @author AI Builder
 * @date 2022-07-28 03:22:03
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimCityMapper
{
    /**
     * 查询分类字典
     *
     * @param id 分类字典主键
     * @return 分类字典
     */
    public CimCity selectCimCityById(@Param("id") String id);

    /**
     * 查询分类字典列表
     *
     * @param cimCity 分类字典
     * @return 分类字典集合
     */
    public List<CimCity> selectCimCityList(@Param("vo") CimCity cimCity);
    
    /**
     * 查询分类字典列表
     *
     * @param type 分类
     * @return 分类字典集合
     */
    public List<CimCity> selectCimCityTreeList(@Param("type") String type, @Param("name") String name);

    /**
     * 新增分类字典
     *
     * @param cimCity 分类字典
     * @return 结果
     */
    public int insertCimCity(CimCity cimCity);

    /**
     * 修改分类字典
     *
     * @param cimCity 分类字典
     * @return 结果
     */
    public int updateCimCity(CimCity cimCity);

    /**
     * 删除分类字典
     *
     * @param id 分类字典主键
     * @return 结果
     */
    public int deleteCimCityById(String id);

    /**
     * 删除节点下的子节点
     * @param sortCode
     * @return
     */
    public int deleteChildren(String sortCode);

    /**
     * 批量分类字典学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimCityByIds(String[] ids);

    /**
     * 判断父级顶目录类型是否重复
     * @param cimCity
     * @return
     */
    public CimCity selectDuplicate(CimCity cimCity);

    /**
     * 根据字典编码查询是否有重复
     * @param cimCity
     * @return
     */
    public CimCity repetitionCode(CimCity cimCity);




}
