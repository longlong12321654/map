package com.hndist.server.service.impl;
import com.hndist.server.domain.CimCity;
import com.hndist.server.mapper.CimCityMapper;
import com.hndist.server.service.ICimCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Title: CimCity
 * @Description: 行政区划服务实现
 * @author AI Builder
 * @date 2022-07-28 03:22:03
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimCityServiceImpl implements ICimCityService
{
    @Autowired
    private CimCityMapper sysDictSortMapper;


    /**
     * 查询行政区划
     *
     * @param id 行政区划主键
     * @return 行政区划
     */
    @Override
    public CimCity selectCimCityById(String id)
    {
        CimCity sysDictSort = sysDictSortMapper.selectCimCityById(id);
        return sysDictSort;
    }

    /**
     * 查询行政区划列表
     *
     * @param sysDictSort 行政区划
     * @return 行政区划
     */
    @Override
    public List<CimCity> selectCimCityList(CimCity sysDictSort)
    {
        return sysDictSortMapper.selectCimCityList(sysDictSort);
    }

    /**
     * 查询行政区划树信息
     *
     * @param type 类型
     * @param name 名称
     * @return
     */
    @Override
    public List<CimCity> selectCimCityTreeList(String type, String name) {
        return sysDictSortMapper.selectCimCityTreeList(type,name);
    }


    /**
     * 新增行政区划
     *
     * @param sysDictSort 行政区划
     * @return 结果
     */
    @Override
    public String insertCimCity(CimCity sysDictSort)
    {
        if("".equals(sysDictSort.getId())||sysDictSort.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            sysDictSort.setId(id);
            sysDictSortMapper.insertCimCity(sysDictSort);

        }else{
            CimCity dbCimCity = sysDictSortMapper.selectCimCityById(sysDictSort.getId());
            if(dbCimCity!=null&&dbCimCity.getId()!=null){
                sysDictSortMapper.updateCimCity(sysDictSort);
            }else{
                sysDictSortMapper.insertCimCity(sysDictSort);
            }
        }
        return sysDictSort.getId();
    }

    /**
     * 修改行政区划
     *
     * @param sysDictSort 行政区划
     * @return 结果
     */
    @Override
    public String updateCimCity(CimCity sysDictSort)
    {
        sysDictSortMapper.updateCimCity(sysDictSort);
        return sysDictSort.getId();
    }

    /**
     * 批量删除行政区划
     *
     * @param ids 需要删除的行政区划主键
     * @return 结果
     */
    @Override
    public int deleteCimCityByIds(String[] ids)
    {
        return sysDictSortMapper.deleteCimCityByIds(ids);
    }

    /**
     * 删除行政区划信息
     *
     * @param id 行政区划主键
     * @return 结果
     */
    @Override
    public int deleteCimCityById(String id)
    {
        return sysDictSortMapper.deleteCimCityById(id);
    }

    @Override
    public CimCity selectDuplicate(CimCity sysDictSort) {
        return sysDictSortMapper.selectDuplicate(sysDictSort);
    }

    @Override
    public CimCity repetitionCode(CimCity sysDictSort) {
        return sysDictSortMapper.repetitionCode(sysDictSort);
    }

}
