package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimDevice;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimDevice
 * @Description: 传感器
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimDeviceMapper
{
    /**
     * 查询传感器
     *
     * @param id 传感器主键
     * @return 传感器
     */
    public CimDevice selectCimDeviceById(@Param("id") String id);

    /**
     * 查询传感器列表
     *
     * @param cimDevice 传感器
     * @return 传感器集合
     */
    public List<CimDevice> selectCimDeviceList(@Param("vo") CimDevice cimDevice);

    /**
     * 新增传感器
     *
     * @param cimDevice 传感器
     * @return 结果
     */
    public int insertCimDevice(CimDevice cimDevice);

    /**
     * 修改传感器
     *
     * @param cimDevice 传感器
     * @return 结果
     */
    public int updateCimDevice(CimDevice cimDevice);

    /**
     * 删除传感器
     *
     * @param id 传感器主键
     * @return 结果
     */
    public int deleteCimDeviceById(String id);

    /**
     * 批量传感器学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimDeviceByIds(String[] ids);
    /**
     * 查询传感器树信息
     *
     * @param type
     * @return 传感器集合
     */
    public List<CimDevice> selectCimDeviceTreeList(String type);

}
