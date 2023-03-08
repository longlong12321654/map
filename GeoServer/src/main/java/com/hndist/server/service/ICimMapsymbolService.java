package com.hndist.server.service;
import com.hndist.framework.domain.SysUser;
import com.hndist.server.domain.CimMapsymbol;
import java.util.List;
/**
 * @Title: CimMapsymbol
 * @Description: 符号库服务接口
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ICimMapsymbolService
{
    /**
     * 查询符号库
     *
     * @param id 符号库主键
     * @return 符号库
     */
    public CimMapsymbol selectCimMapsymbolById(String id);

    /**
     * 查询符号库列表
     *
     * @param cimMapsymbol 符号库
     * @return 符号库集合
     */
    public List<CimMapsymbol> selectCimMapsymbolList(CimMapsymbol cimMapsymbol);

    /**
     * 新增符号库
     *
     * @param cimMapsymbol 符号库
     * @return 结果
     */
    public String insertCimMapsymbol(CimMapsymbol cimMapsymbol);

    /**
     * 修改符号库
     *
     * @param cimMapsymbol 符号库
     * @return 结果
     */
    public String updateCimMapsymbol(CimMapsymbol cimMapsymbol);

    /**
     * 批量删除符号库
     *
     * @param ids 需要删除的符号库主键集合
     * @return 结果
     */
    public int deleteCimMapsymbolByIds(String[] ids);

    /**
     * 删除符号库信息
     *
     * @param id 符号库主键
     * @return 结果
     */
    public int deleteCimMapsymbolById(String id);
    /**
     * 查询符号库树信息
     * @param type 为空时返回所有目录 不为空时返回所有信息
     * @return 符号库集合
     */
    public List<CimMapsymbol> selectCimMapsymbolTreeList(String type);

    /**
     * 导入数据
     * @param mapsymbolList
     * @param updateSupport
     * @param operName
     * @return
     */
    public String importUser(List<CimMapsymbol> mapsymbolList, Boolean updateSupport, String operName);
}
