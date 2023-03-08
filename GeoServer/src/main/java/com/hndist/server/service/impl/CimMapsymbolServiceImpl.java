package com.hndist.server.service.impl;
import com.hndist.framework.domain.SysUser;
import com.hndist.framework.exception.ServiceException;
import com.hndist.framework.utils.SecurityUtils;
import com.hndist.framework.utils.StringUtils;
import com.hndist.framework.utils.bean.BeanValidators;
import com.hndist.server.domain.CimMapsymbol;
import com.hndist.server.mapper.CimMapsymbolMapper;
import com.hndist.server.service.ICimMapsymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Title: CimMapsymbol
 * @Description: 符号库服务实现
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimMapsymbolServiceImpl implements ICimMapsymbolService
{
    @Autowired
    private CimMapsymbolMapper cimMapsymbolMapper;


    /**
     * 查询符号库
     *
     * @param id 符号库主键
     * @return 符号库
     */
    @Override
    public CimMapsymbol selectCimMapsymbolById(String id)
    {
        CimMapsymbol cimMapsymbol = cimMapsymbolMapper.selectCimMapsymbolById(id);
        return cimMapsymbol;
    }

    /**
     * 查询符号库列表
     *
     * @param cimMapsymbol 符号库
     * @return 符号库
     */
    @Override
    public List<CimMapsymbol> selectCimMapsymbolList(CimMapsymbol cimMapsymbol)
    {
        return cimMapsymbolMapper.selectCimMapsymbolList(cimMapsymbol);
    }

    /**
     * 新增符号库
     *
     * @param cimMapsymbol 符号库
     * @return 结果
     */
    @Override
    public String insertCimMapsymbol(CimMapsymbol cimMapsymbol)
    {
        if("".equals(cimMapsymbol.getId())||cimMapsymbol.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimMapsymbol.setId(id);
            cimMapsymbolMapper.insertCimMapsymbol(cimMapsymbol);
        }else{
            CimMapsymbol dbCimMapsymbol = cimMapsymbolMapper.selectCimMapsymbolById(cimMapsymbol.getId());
            if(dbCimMapsymbol!=null&&dbCimMapsymbol.getId()!=null){
                cimMapsymbolMapper.updateCimMapsymbol(cimMapsymbol);
            }else{
                cimMapsymbolMapper.insertCimMapsymbol(cimMapsymbol);
            }
        }
        return cimMapsymbol.getId();
    }

    /**
     * 修改符号库
     *
     * @param cimMapsymbol 符号库
     * @return 结果
     */
    @Override
    public String updateCimMapsymbol(CimMapsymbol cimMapsymbol)
    {
        cimMapsymbolMapper.updateCimMapsymbol(cimMapsymbol);
        return cimMapsymbol.getId();
    }

    /**
     * 批量删除符号库
     *
     * @param ids 需要删除的符号库主键
     * @return 结果
     */
    @Override
    public int deleteCimMapsymbolByIds(String[] ids)
    {
        return cimMapsymbolMapper.deleteCimMapsymbolByIds(ids);
    }

    /**
     * 删除符号库信息
     *
     * @param id 符号库主键
     * @return 结果
     */
    @Override
    public int deleteCimMapsymbolById(String id)
    {
        return cimMapsymbolMapper.deleteCimMapsymbolById(id);
    }
    /**
     * 查询符号库树信息
     *
     * @param type
     * @return 符号库
     */
    @Override
    public List<CimMapsymbol> selectCimMapsymbolTreeList(String type)
    {
        return cimMapsymbolMapper.selectCimMapsymbolTreeList(type);
    }

    /**
     * 导入符号库数据
     *
     * @param mapsymbolList 符号库数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importUser(List<CimMapsymbol> mapsymbolList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(mapsymbolList) || mapsymbolList.size() == 0)
        {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (CimMapsymbol cimMapsymbol : mapsymbolList)
        {
            try
            {
                // 验证是否存在这个用户
                CimMapsymbol c = cimMapsymbolMapper.selectCimMapsymbolByName(cimMapsymbol.getName());
                if (StringUtils.isNull(c))
                {
                    cimMapsymbol.setCreateBy(operName);
                    this.insertCimMapsymbol(cimMapsymbol);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、符号 " + cimMapsymbol.getName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    cimMapsymbol.setUpdateBy(operName);
                    this.updateCimMapsymbol(cimMapsymbol);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、符号 " + cimMapsymbol.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、符号 " + cimMapsymbol.getName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、符号 " + cimMapsymbol.getName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }


}
