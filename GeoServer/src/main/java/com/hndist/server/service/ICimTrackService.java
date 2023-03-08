package com.hndist.server.service;
import com.hndist.server.domain.CimTrack;
import java.util.List;
/**
 * @Title: CimTrack
 * @Description: 轨迹服务接口
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimTrackService
{
    /**
     * 查询轨迹
     *
     * @param id 轨迹主键
     * @return 轨迹
     */
    public CimTrack selectCimTrackById(String id);

    /**
     * 查询轨迹列表
     *
     * @param cimTrack 轨迹
     * @return 轨迹集合
     */
    public List<CimTrack> selectCimTrackList(CimTrack cimTrack);

    /**
     * 新增轨迹
     *
     * @param cimTrack 轨迹
     * @return 结果
     */
    public String insertCimTrack(CimTrack cimTrack);

    /**
     * 修改轨迹
     *
     * @param cimTrack 轨迹
     * @return 结果
     */
    public String updateCimTrack(CimTrack cimTrack);

    /**
     * 批量删除轨迹
     *
     * @param ids 需要删除的轨迹主键集合
     * @return 结果
     */
    public int deleteCimTrackByIds(String[] ids);

    /**
     * 删除轨迹信息
     *
     * @param id 轨迹主键
     * @return 结果
     */
    public int deleteCimTrackById(String id);
    /**
     * 查询轨迹树信息
     * @param type 为空时返回所有目录 不为空时返回所有信息
     * @return 轨迹集合
     */
    public List<CimTrack> selectCimTrackTreeList(String type);

}
