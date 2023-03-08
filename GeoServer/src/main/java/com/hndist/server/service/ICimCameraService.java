package com.hndist.server.service;
import com.hndist.server.domain.CimCamera;
import java.util.List;
/**
 * @Title: CimCamera
 * @Description: 摄像头服务接口
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimCameraService
{
    /**
     * 查询摄像头
     *
     * @param id 摄像头主键
     * @return 摄像头
     */
    public CimCamera selectCimCameraById(String id);

    /**
     * 查询摄像头列表
     *
     * @param cimCamera 摄像头
     * @return 摄像头集合
     */
    public List<CimCamera> selectCimCameraList(CimCamera cimCamera);

    /**
     * 新增摄像头
     *
     * @param cimCamera 摄像头
     * @return 结果
     */
    public String insertCimCamera(CimCamera cimCamera);

    /**
     * 修改摄像头
     *
     * @param cimCamera 摄像头
     * @return 结果
     */
    public String updateCimCamera(CimCamera cimCamera);

    /**
     * 批量删除摄像头
     *
     * @param ids 需要删除的摄像头主键集合
     * @return 结果
     */
    public int deleteCimCameraByIds(String[] ids);

    /**
     * 删除摄像头信息
     *
     * @param id 摄像头主键
     * @return 结果
     */
    public int deleteCimCameraById(String id);
    /**
     * 查询摄像头树信息
     * @param type 为空时返回所有目录 不为空时返回所有信息
     * @return 摄像头集合
     */
    public List<CimCamera> selectCimCameraTreeList(String type);

}
