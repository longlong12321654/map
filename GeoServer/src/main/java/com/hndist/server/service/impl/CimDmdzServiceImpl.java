package com.hndist.server.service.impl;
import com.hndist.server.domain.CimDmdz;
import com.hndist.server.domain.dto.CimDmdzDTO;
import com.hndist.server.mapper.CimDmdzMapper;
import com.hndist.server.service.ICimDmdzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.hndist.framework.core.domain.model.LoginUser;
import com.hndist.framework.utils.SecurityUtils;

/**
 * @Title: CimDmdz
 * @Description: 地名地址服务实现
 * @author AI Builder
 * @date 2022-03-31 03:42:27
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimDmdzServiceImpl implements ICimDmdzService
{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CimDmdzMapper bsDmdzMapper;


    /**
     * 查询地名地址
     *
     * @param id 地名地址主键
     * @return 地名地址
     */
    @Override
    public CimDmdz selectCimDmdzById(String id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        CimDmdz bsDmdz = bsDmdzMapper.selectCimDmdzById(id, String.valueOf(loginUser.getUserId()), loginUser.getUser().getRoleIds());
        return bsDmdz;
    }

    /**
     * 查询地名地址列表
     *
     * @param bsDmdz 地名地址
     * @return 地名地址
     */
    @Override
    public List<CimDmdz> selectCimDmdzList(CimDmdz bsDmdz)
    {
        //LoginUser loginUser = SecurityUtils.getLoginUser();
//        long start = System.currentTimeMillis();
//        if ("2".equals(bsDmdz.getListType())) {
//            // 获取推荐列表
//            List<CimDmdzDTO> bsDmdzs = bsDmdzMapper.selectDmdzQuickSearchList(bsDmdz);
//            System.out.println("---------" +(System.currentTimeMillis()-start)+"ms");
//            return bsDmdzs;
//        } else {
            // 获取分页列表
            return bsDmdzMapper.selectCimDmdzList(bsDmdz, null, null);
//        }
    }

    @Override
    public List<CimDmdzDTO> selectDmdzQuickSearchList(CimDmdz bsDmdz) {
        long start = System.currentTimeMillis();
        List<CimDmdzDTO> bsDmdzs = bsDmdzMapper.selectDmdzQuickSearchList(bsDmdz);
        System.out.println("client列表" + (System.currentTimeMillis() - start) + "ms");
        return bsDmdzs;
    }


    /**
     * 新增地名地址
     *
     * @param bsDmdz 地名地址
     * @return 结果
     */
    @Override
    public int insertCimDmdz(CimDmdz bsDmdz)
    {
        String id = UUID.randomUUID().toString().replaceAll("-","");
        bsDmdz.setId(id);
        int num = bsDmdzMapper.insertCimDmdz(bsDmdz);
        return num;
    }

    /**
     * 修改地名地址
     *
     * @param bsDmdz 地名地址
     * @return 结果
     */
    @Override
    public int updateCimDmdz(CimDmdz bsDmdz)
    {
        int num = bsDmdzMapper.updateCimDmdz(bsDmdz);
        return num;
    }

    /**
     * 批量删除地名地址
     *
     * @param ids 需要删除的地名地址主键
     * @return 结果
     */
    @Override
    public int deleteCimDmdzByIds(String[] ids)
    {
        return bsDmdzMapper.deleteCimDmdzByIds(ids);
    }

    /**
     * 删除地名地址信息
     *
     * @param id 地名地址主键
     * @return 结果
     */
    @Override
    public int deleteCimDmdzById(String id)
    {
        return bsDmdzMapper.deleteCimDmdzById(id);
    }



}
