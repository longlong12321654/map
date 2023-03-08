package com.hndist.server.service.impl;
import com.hndist.server.domain.CimTask;
import com.hndist.server.mapper.CimTaskMapper;
import com.hndist.server.service.ICimTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Title: CimTask
 * @Description: 任务管理服务实现
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimTaskServiceImpl implements ICimTaskService
{
    @Autowired
    private CimTaskMapper cimTaskMapper;


    /**
     * 查询任务管理
     *
     * @param id 任务管理主键
     * @return 任务管理
     */
    @Override
    public CimTask selectCimTaskById(String id)
    {
        CimTask cimTask = cimTaskMapper.selectCimTaskById(id);
        return cimTask;
    }

    /**
     * 查询任务管理列表
     *
     * @param cimTask 任务管理
     * @return 任务管理
     */
    @Override
    public List<CimTask> selectCimTaskList(CimTask cimTask)
    {
        return cimTaskMapper.selectCimTaskList(cimTask);
    }

    /**
     * 新增任务管理
     *
     * @param cimTask 任务管理
     * @return 结果
     */
    @Override
    public String insertCimTask(CimTask cimTask)
    {
        if("".equals(cimTask.getId())||cimTask.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimTask.setId(id);
            cimTaskMapper.insertCimTask(cimTask);
        }else{
            CimTask dbCimTask = cimTaskMapper.selectCimTaskById(cimTask.getId());
            if(dbCimTask!=null&&dbCimTask.getId()!=null){
                cimTaskMapper.updateCimTask(cimTask);
            }else{
                cimTaskMapper.insertCimTask(cimTask);
            }
        }
        return cimTask.getId();
    }

    /**
     * 修改任务管理
     *
     * @param cimTask 任务管理
     * @return 结果
     */
    @Override
    public String updateCimTask(CimTask cimTask)
    {
        cimTaskMapper.updateCimTask(cimTask);
        return cimTask.getId();
    }

    /**
     * 批量删除任务管理
     *
     * @param ids 需要删除的任务管理主键
     * @return 结果
     */
    @Override
    public int deleteCimTaskByIds(String[] ids)
    {
        return cimTaskMapper.deleteCimTaskByIds(ids);
    }

    /**
     * 删除任务管理信息
     *
     * @param id 任务管理主键
     * @return 结果
     */
    @Override
    public int deleteCimTaskById(String id)
    {
        return cimTaskMapper.deleteCimTaskById(id);
    }



}
