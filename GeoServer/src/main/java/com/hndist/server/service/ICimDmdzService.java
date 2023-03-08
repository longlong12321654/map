package com.hndist.server.service;
import com.hndist.server.domain.CimDmdz;
import com.hndist.server.domain.dto.CimDmdzDTO;

import java.util.List;
/**
 * @Title: CimDmdz
 * @Description: 地名地址服务接口
 * @author AI Builder
 * @date 2022-03-31 03:42:27
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimDmdzService
{
    /**
     * 查询地名地址
     *
     * @param id 地名地址主键
     * @return 地名地址
     */
    public CimDmdz selectCimDmdzById(String id);

    /**
     * 查询地名地址列表
     *
     * @param bsDmdz 地名地址
     * @return 地名地址集合
     */
    public List<CimDmdz> selectCimDmdzList(CimDmdz bsDmdz);

    /**
     * dmdz快速列表
     *
     * @param bsDmdz 地名地址
     * @return 地名地址集合
     */
    public List<CimDmdzDTO> selectDmdzQuickSearchList(CimDmdz bsDmdz);

    /**
     * 新增地名地址
     *
     * @param bsDmdz 地名地址
     * @return 结果
     */
    public int insertCimDmdz(CimDmdz bsDmdz);

    /**
     * 修改地名地址
     *
     * @param bsDmdz 地名地址
     * @return 结果
     */
    public int updateCimDmdz(CimDmdz bsDmdz);

    /**
     * 批量删除地名地址
     *
     * @param ids 需要删除的地名地址主键集合
     * @return 结果
     */
    public int deleteCimDmdzByIds(String[] ids);

    /**
     * 删除地名地址信息
     *
     * @param id 地名地址主键
     * @return 结果
     */
    public int deleteCimDmdzById(String id);

}
