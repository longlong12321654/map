package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimTrackPosition;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimTrackPosition
 * @Description: 轨迹位置
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimTrackPositionMapper
{
    /**
     * 查询轨迹位置
     *
     * @param id 轨迹位置主键
     * @return 轨迹位置
     */
    public CimTrackPosition selectCimTrackPositionById(@Param("id") String id);

    /**
     * 查询轨迹位置列表
     *
     * @param cimTrackPosition 轨迹位置
     * @return 轨迹位置集合
     */
    public List<CimTrackPosition> selectCimTrackPositionList(@Param("vo") CimTrackPosition cimTrackPosition);

    /**
     * 新增轨迹位置
     *
     * @param cimTrackPosition 轨迹位置
     * @return 结果
     */
    public int insertCimTrackPosition(CimTrackPosition cimTrackPosition);

    /**
     * 修改轨迹位置
     *
     * @param cimTrackPosition 轨迹位置
     * @return 结果
     */
    public int updateCimTrackPosition(CimTrackPosition cimTrackPosition);

    /**
     * 删除轨迹位置
     *
     * @param id 轨迹位置主键
     * @return 结果
     */
    public int deleteCimTrackPositionById(String id);

    /**
     * 批量轨迹位置学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimTrackPositionByIds(String[] ids);

}
