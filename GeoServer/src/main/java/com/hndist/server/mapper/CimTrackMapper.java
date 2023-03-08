package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimTrack;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimTrack
 * @Description: 轨迹
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimTrackMapper
{
    /**
     * 查询轨迹
     *
     * @param id 轨迹主键
     * @return 轨迹
     */
    public CimTrack selectCimTrackById(@Param("id") String id);

    /**
     * 查询轨迹列表
     *
     * @param cimTrack 轨迹
     * @return 轨迹集合
     */
    public List<CimTrack> selectCimTrackList(@Param("vo") CimTrack cimTrack);

    /**
     * 新增轨迹
     *
     * @param cimTrack 轨迹
     * @return 结果
     */
    public int insertCimTrack(CimTrack cimTrack);

    /**
     * 修改轨迹
     *
     * @param cimTrack 轨迹
     * @return 结果
     */
    public int updateCimTrack(CimTrack cimTrack);

    /**
     * 删除轨迹
     *
     * @param id 轨迹主键
     * @return 结果
     */
    public int deleteCimTrackById(String id);

    /**
     * 批量轨迹学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimTrackByIds(String[] ids);
    /**
     * 查询轨迹树信息
     *
     * @param type
     * @return 轨迹集合
     */
    public List<CimTrack> selectCimTrackTreeList(String type);

}
