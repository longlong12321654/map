package com.hndist.server.service;
import com.hndist.server.domain.CimTask;
import java.util.List;
/**
 * @Title: CimTask
 * @Description: 任务管理服务接口
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimTaskService
{
    /**
     * 查询任务管理
     *
     * @param id 任务管理主键
     * @return 任务管理
     */
    public CimTask selectCimTaskById(String id);

    /**
     * 查询任务管理列表
     *
     * @param cimTask 任务管理
     * @return 任务管理集合
     */
    public List<CimTask> selectCimTaskList(CimTask cimTask);

    /**
     * 新增任务管理
     *
     * @param cimTask 任务管理
     * @return 结果
     */
    public String insertCimTask(CimTask cimTask);

    /**
     * 修改任务管理
     *
     * @param cimTask 任务管理
     * @return 结果
     */
    public String updateCimTask(CimTask cimTask);

    /**
     * 批量删除任务管理
     *
     * @param ids 需要删除的任务管理主键集合
     * @return 结果
     */
    public int deleteCimTaskByIds(String[] ids);

    /**
     * 删除任务管理信息
     *
     * @param id 任务管理主键
     * @return 结果
     */
    public int deleteCimTaskById(String id);

}
