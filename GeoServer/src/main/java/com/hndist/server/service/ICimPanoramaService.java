package com.hndist.server.service;
import com.hndist.server.domain.CimPanorama;
import java.util.List;
/**
 * @Title: CimPanorama
 * @Description: 360全景信息服务接口
 * @author AI Builder
 * @date 2022-11-15 08:37:58
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimPanoramaService
{
    /**
     * 查询360全景信息
     *
     * @param id 360全景信息主键
     * @return 360全景信息
     */
    public CimPanorama selectCimPanoramaById(String id);

    /**
     * 查询360全景信息列表
     *
     * @param cimPanorama 360全景信息
     * @return 360全景信息集合
     */
    public List<CimPanorama> selectCimPanoramaList(CimPanorama cimPanorama);

    /**
     * 新增360全景信息
     *
     * @param cimPanorama 360全景信息
     * @return 结果
     */
    public String insertCimPanorama(CimPanorama cimPanorama);

    /**
     * 修改360全景信息
     *
     * @param cimPanorama 360全景信息
     * @return 结果
     */
    public String updateCimPanorama(CimPanorama cimPanorama);

    /**
     * 批量删除360全景信息
     *
     * @param ids 需要删除的360全景信息主键集合
     * @return 结果
     */
    public int deleteCimPanoramaByIds(String[] ids);

    /**
     * 删除360全景信息信息
     *
     * @param id 360全景信息主键
     * @return 结果
     */
    public int deleteCimPanoramaById(String id);
    /**
     * 查询360全景信息树信息
     * @param type 为空时返回所有目录 不为空时返回所有信息
     * @return 360全景信息集合
     */
    public List<CimPanorama> selectCimPanoramaTreeList(String type);

}
