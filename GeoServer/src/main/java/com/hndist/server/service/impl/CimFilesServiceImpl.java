package com.hndist.server.service.impl;
import com.github.pagehelper.util.StringUtil;
import com.hndist.framework.utils.StringUtils;
import com.hndist.server.domain.CimDatasourceMeta;
import com.hndist.server.domain.CimFiles;
import com.hndist.server.mapper.CimDatasourceMetaMapper;
import com.hndist.server.mapper.CimFilesMapper;
import com.hndist.server.service.ICimFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: CimFiles
 * @Description: 地图服务服务实现
 * @author AI Builder
 * @date 2022-11-15 07:02:01
 * @version v1.0.0
 * @Copyright:  www.hndist.com Inc. All rights reserved.
 * 注意：本内容仅限于河南数慧有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class CimFilesServiceImpl implements ICimFilesService
{
    @Autowired
    private CimFilesMapper cimFilesMapper;
    @Autowired
    private CimDatasourceMetaMapper cimDatasourceMetaMapper;

    /**
     * 查询地图服务
     *
     * @param id 地图服务主键
     * @return 地图服务
     */
    @Override
    public CimFiles selectCimFilesById(String id)
    {
        CimFiles cimFiles = cimFilesMapper.selectCimFilesById(id);
        return cimFiles;
    }

    /**
     * 查询地图服务列表
     *
     * @param cimFiles 地图服务
     * @return 地图服务
     */
    @Override
    public List<CimFiles> selectCimFilesList(CimFiles cimFiles)
    {
        List<CimFiles> filesList = cimFilesMapper.selectCimFilesList(cimFiles);
        filesList.forEach(files -> {
            String filePath = files.getFilePath();
            if (StringUtil.isNotEmpty(filePath)) {
                File file = new File(filePath);
                if (file.exists()) {
                    files.setFileSize((file.length()/1024)+"");
                }
            }
        });
        return filesList;
    }

    /**
     * 新增地图服务
     *
     * @param cimFiles 地图服务
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String insertCimFiles(CimFiles cimFiles)
    {
        if("".equals(cimFiles.getId())||cimFiles.getId()==null){
            String id = UUID.randomUUID().toString().replaceAll("-","");
            cimFiles.setId(id);
            cimFilesMapper.insertCimFiles(cimFiles);
            if (Objects.nonNull(cimFiles.getDatasourceMeta())) {
                saveDataSourceMeta(cimFiles);
            }
        }else{
            CimFiles dbCimFiles = cimFilesMapper.selectCimFilesById(cimFiles.getId());
            if(dbCimFiles!=null&&dbCimFiles.getId()!=null){
                cimFilesMapper.updateCimFiles(cimFiles);
            }else{
                cimFilesMapper.insertCimFiles(cimFiles);
                if (Objects.nonNull(cimFiles.getDatasourceMeta())) {
                    saveDataSourceMeta(cimFiles);
                }
            }
        }
        return cimFiles.getId();
    }

    /**
     * 新增地图服务
     *
     * @param cimFiles 地图服务
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String insertCimFilesAndMeta(CimFiles cimFiles)
    {
        String filePath = cimFiles.getFilePath();
        String[] split = filePath.split(",");

        if (StringUtils.isNotEmpty(split)) {
            List<String> list = Arrays.stream(split).collect(Collectors.toList());
           for (int i = 0; i < list.size(); i++) {
               String path = list.get(i);
               cimFiles.setFilePath(path);
               insertCimFiles(cimFiles);
           }
       }

        return cimFiles.getId();
    }


    private void saveDataSourceMeta(CimFiles cimFiles) {
        // 新增元数据
        CimDatasourceMeta datasourceMeta = cimFiles.getDatasourceMeta();
        if (datasourceMeta != null) {
            datasourceMeta.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            datasourceMeta.setDatasourceId(cimFiles.getId());
            cimDatasourceMetaMapper.insertCimDatasourceMeta(datasourceMeta);
        }
    }

    /**
     * 修改地图服务
     *
     * @param cimFiles 地图服务
     * @return 结果
     */
    @Override
    public String updateCimFiles(CimFiles cimFiles)
    {
        cimFilesMapper.updateCimFiles(cimFiles);
        return cimFiles.getId();
    }

    /**
     * 批量删除地图服务
     *
     * @param ids 需要删除的地图服务主键
     * @return 结果
     */
    @Override
    public int deleteCimFilesByIds(String[] ids)
    {
        return cimFilesMapper.deleteCimFilesByIds(ids);
    }

    /**
     * 删除地图服务信息
     *
     * @param id 地图服务主键
     * @return 结果
     */
    @Override
    public int deleteCimFilesById(String id)
    {
        return cimFilesMapper.deleteCimFilesById(id);
    }
    /**
     * 查询地图服务树信息
     *
     * @param type
     * @return 地图服务
     */
    @Override
    public List<CimFiles> selectCimFilesTreeList(String type)
    {
        return cimFilesMapper.selectCimFilesTreeList(type);
    }



}
