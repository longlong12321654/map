package com.hndist.server.service;

import com.hndist.server.domain.CimCity;

import java.util.List;

/**
 * @Title: CimCity
 * @Description: 行政区划服务接口
 * @author AI Builder
 * @date 2022-07-28 03:22:03
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimCityService
{
    /**
     * 查询行政区划
     *
     * @param id 行政区划主键
     * @return 行政区划
     */
    public CimCity selectCimCityById(String id);

    /**
     * 查询行政区划列表
     *
     * @param sysDictSort 行政区划
     * @return 行政区划集合
     */
    public List<CimCity> selectCimCityList(CimCity sysDictSort);

    /**
     * 查询行政区划列表
     *
     * @param type 
     * @return 行政区划集合
     */
    public List<CimCity> selectCimCityTreeList(String type, String name);

    /**
     * 新增行政区划
     *
     * @param sysDictSort 行政区划
     * @return 结果
     */
    public String insertCimCity(CimCity sysDictSort);

    /**
     * 修改行政区划
     *
     * @param sysDictSort 行政区划
     * @return 结果
     */
    public String updateCimCity(CimCity sysDictSort);

    /**
     * 批量删除行政区划
     *
     * @param ids 需要删除的行政区划主键集合
     * @return 结果
     */
    public int deleteCimCityByIds(String[] ids);

    /**
     * 删除行政区划信息
     *
     * @param id 行政区划主键
     * @return 结果
     */
    public int deleteCimCityById(String id);


    /**
     * 判断父级顶目录类型是否重复
     * @param sysDictSort
     * @return
     */
    public CimCity selectDuplicate(CimCity sysDictSort);

    /**
     * 根据字典编码查询是否有重复
     * @param sysDictSort
     * @return
     */
    public CimCity repetitionCode(CimCity sysDictSort);
}
