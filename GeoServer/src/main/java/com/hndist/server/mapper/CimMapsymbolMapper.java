package com.hndist.server.mapper;
import java.util.List;
import com.hndist.server.domain.CimMapsymbol;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: CimMapsymbol
 * @Description: 符号库
 * @author AI Builder
 * @date 2022-11-09 11:06:56
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CimMapsymbolMapper
{
    /**
     * 查询符号库
     *
     * @param id 符号库主键
     * @return 符号库
     */
    public CimMapsymbol selectCimMapsymbolById(@Param("id") String id);

    /**
     * 查询符号库列表
     *
     * @param cimMapsymbol 符号库
     * @return 符号库集合
     */
    public List<CimMapsymbol> selectCimMapsymbolList(@Param("vo") CimMapsymbol cimMapsymbol);

    /**
     * 新增符号库
     *
     * @param cimMapsymbol 符号库
     * @return 结果
     */
    public int insertCimMapsymbol(CimMapsymbol cimMapsymbol);

    /**
     * 修改符号库
     *
     * @param cimMapsymbol 符号库
     * @return 结果
     */
    public int updateCimMapsymbol(CimMapsymbol cimMapsymbol);

    /**
     * 删除符号库
     *
     * @param id 符号库主键
     * @return 结果
     */
    public int deleteCimMapsymbolById(String id);

    /**
     * 批量符号库学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCimMapsymbolByIds(String[] ids);
    /**
     * 查询符号库树信息
     *
     * @param type
     * @return 符号库集合
     */
    public List<CimMapsymbol> selectCimMapsymbolTreeList(String type);

    /**
     * 根据名字查询
     * @param name
     * @return
     */
    public CimMapsymbol selectCimMapsymbolByName(String name);

}
