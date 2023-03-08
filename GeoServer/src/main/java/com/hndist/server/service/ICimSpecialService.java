package com.hndist.server.service;
import com.hndist.server.domain.CimSpecial;
import java.util.List;
/**
 * @Title: CimSpecial
 * @Description: 专题图服务接口
 * @author AI Builder
 * @date 2022-12-02 05:26:19
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimSpecialService
{
    /**
     * 查询专题图
     *
     * @param id 专题图主键
     * @return 专题图
     */
    public CimSpecial selectCimSpecialById(String id);

    /**
     * 查询专题图列表
     *
     * @param cimSpecial 专题图
     * @return 专题图集合
     */
    public List<CimSpecial> selectCimSpecialList(CimSpecial cimSpecial);

    /**
     * 新增专题图
     *
     * @param cimSpecial 专题图
     * @return 结果
     */
    public String insertCimSpecial(CimSpecial cimSpecial);

    /**
     * 修改专题图
     *
     * @param cimSpecial 专题图
     * @return 结果
     */
    public String updateCimSpecial(CimSpecial cimSpecial);

    /**
     * 批量删除专题图
     *
     * @param ids 需要删除的专题图主键集合
     * @return 结果
     */
    public int deleteCimSpecialByIds(String[] ids);

    /**
     * 删除专题图信息
     *
     * @param id 专题图主键
     * @return 结果
     */
    public int deleteCimSpecialById(String id);
    /**
     * 查询专题图树信息
     * @param type 为空时返回所有目录 不为空时返回所有信息
     * @return 专题图集合
     */
    public List<CimSpecial> selectCimSpecialTreeList(String type);

}
