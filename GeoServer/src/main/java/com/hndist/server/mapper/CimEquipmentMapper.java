package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimEquipment;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimEquipment
 * @Description: 设备信息表
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimEquipmentMapper
{
    /**
     * 查询设备信息表
     *
     * @param id 设备信息表主键
     * @return 设备信息表
     */
    public CimEquipment selectCimEquipmentById(@Param("id") String id);

    /**
     * 查询设备信息表列表
     *
     * @param cimEquipment 设备信息表
     * @return 设备信息表集合
     */
    public List<CimEquipment> selectCimEquipmentList(@Param("vo") CimEquipment cimEquipment);

    /**
     * 新增设备信息表
     *
     * @param cimEquipment 设备信息表
     * @return 结果
     */
    public int insertCimEquipment(CimEquipment cimEquipment);

    /**
     * 修改设备信息表
     *
     * @param cimEquipment 设备信息表
     * @return 结果
     */
    public int updateCimEquipment(CimEquipment cimEquipment);

    /**
     * 删除设备信息表
     *
     * @param id 设备信息表主键
     * @return 结果
     */
    public int deleteCimEquipmentById(String id);

    /**
     * 批量设备信息表学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimEquipmentByIds(String[] ids);

}
