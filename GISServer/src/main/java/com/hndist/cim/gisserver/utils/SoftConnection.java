package com.hndist.cim.gisserver.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author FWY
 * @version V1.0.0
 * @ClassName: SoftConnection
 * @Description: 软连接管理
 * @date 2022/7/8 4:54 PM
 * @Copyright: www.hndist.com All rights reserved.
 * 注意：本内容仅限于研发中心内部传阅，禁止外泄以及用于其他的商业目
 */
public class SoftConnection {
    
    /**
     * @Description: 创建软连接
     * @date 2022/7/8 4:57 PM
     * @Param [java.lang.String, java.lang.String]
     * @return boolean
     */
    public static boolean createSoftConnection(String linkFilePath,String realFilePath){
        try {
            if(!isExistSoftConnection(linkFilePath)){
                Files.createSymbolicLink(Paths.get(linkFilePath),Paths.get(realFilePath));
            }else{
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @Description: 删除软连接
     * @date 2022/7/8 5:23 PM
     * @Param [java.lang.String]
     * @return boolean
     */
    public static boolean deleteSoftConnection(String linkFilePath){
       //如果文件存在则进行删除操作
        if(isExistSoftConnection(linkFilePath)) {
            File sourceFile = new File(linkFilePath);
            FileUtils.deleteQuietly(sourceFile);
        }
        //返回删除软连接状态
        return true;
    }

    /**
     * @Description: 判断文件是否存在
     * @date 2022/7/8 5:33 PM
     * @Param [java.lang.String]
     * @return boolean
     */
    public static boolean isExistSoftConnection(String linkFilePath){
        //创建文件对象
        File sourceFile = new File(linkFilePath);
        //判断文件是否存在，返回文件存在标志
        return sourceFile.exists();
    }

}
