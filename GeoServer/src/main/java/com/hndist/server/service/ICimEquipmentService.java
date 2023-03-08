package com.hndist.server.service;
import com.hndist.server.domain.CimEquipment;
import java.util.List;
/**
 * @Title: CimEquipment
 * @Description: 设备信息服务接口
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimEquipmentService
{
    /**
     * 查询设备信息
     *
     * @param id 设备信息主键
     * @return 设备信息
     */
    public CimEquipment selectCimEquipmentById(String id);

    /**
     * 查询设备信息列表
     *
     * @param cimEquipment 设备信息
     * @return 设备信息集合
     */
    public List<CimEquipment> selectCimEquipmentList(CimEquipment cimEquipment);

    /**
     * 新增设备信息
     *
     * @param cimEquipment 设备信息
     * @return 结果
     */
    public String insertCimEquipment(CimEquipment cimEquipment);

    /**
     * 修改设备信息
     *
     * @param cimEquipment 设备信息
     * @return 结果
     */
    public String updateCimEquipment(CimEquipment cimEquipment);

    /**
     * 批量删除设备信息
     *
     * @param ids 需要删除的设备信息主键集合
     * @return 结果
     */
    public int deleteCimEquipmentByIds(String[] ids);

    /**
     * 删除设备信息信息
     *
     * @param id 设备信息主键
     * @return 结果
     */
    public int deleteCimEquipmentById(String id);

}
