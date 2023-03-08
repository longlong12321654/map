package com.hndist.server.service;
import com.hndist.server.domain.CimDevice;
import java.util.List;
/**
 * @Title: CimDevice
 * @Description: 传感器服务接口
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimDeviceService
{
    /**
     * 查询传感器
     *
     * @param id 传感器主键
     * @return 传感器
     */
    public CimDevice selectCimDeviceById(String id);

    /**
     * 查询传感器列表
     *
     * @param cimDevice 传感器
     * @return 传感器集合
     */
    public List<CimDevice> selectCimDeviceList(CimDevice cimDevice);

    /**
     * 新增传感器
     *
     * @param cimDevice 传感器
     * @return 结果
     */
    public String insertCimDevice(CimDevice cimDevice);

    /**
     * 修改传感器
     *
     * @param cimDevice 传感器
     * @return 结果
     */
    public String updateCimDevice(CimDevice cimDevice);

    /**
     * 批量删除传感器
     *
     * @param ids 需要删除的传感器主键集合
     * @return 结果
     */
    public int deleteCimDeviceByIds(String[] ids);

    /**
     * 删除传感器信息
     *
     * @param id 传感器主键
     * @return 结果
     */
    public int deleteCimDeviceById(String id);
    /**
     * 查询传感器树信息
     * @param type 为空时返回所有目录 不为空时返回所有信息
     * @return 传感器集合
     */
    public List<CimDevice> selectCimDeviceTreeList(String type);

}
