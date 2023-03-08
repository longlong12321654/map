package com.hndist.server.service;
import com.hndist.server.domain.CimVideo;
import java.util.List;
/**
 * @Title: CimVideo
 * @Description: cim_video服务接口
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimVideoService
{
    /**
     * 查询cim_video
     *
     * @param id cim_video主键
     * @return cim_video
     */
    public CimVideo selectCimVideoById(String id);

    /**
     * 查询cim_video列表
     *
     * @param cimVideo cim_video
     * @return cim_video集合
     */
    public List<CimVideo> selectCimVideoList(CimVideo cimVideo);

    /**
     * 新增cim_video
     *
     * @param cimVideo cim_video
     * @return 结果
     */
    public String insertCimVideo(CimVideo cimVideo);

    /**
     * 修改cim_video
     *
     * @param cimVideo cim_video
     * @return 结果
     */
    public String updateCimVideo(CimVideo cimVideo);

    /**
     * 批量删除cim_video
     *
     * @param ids 需要删除的cim_video主键集合
     * @return 结果
     */
    public int deleteCimVideoByIds(String[] ids);

    /**
     * 删除cim_video信息
     *
     * @param id cim_video主键
     * @return 结果
     */
    public int deleteCimVideoById(String id);

}
