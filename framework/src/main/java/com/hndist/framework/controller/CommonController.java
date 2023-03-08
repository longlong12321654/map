package com.hndist.framework.controller;

import com.github.junrar.exception.RarException;
import com.hndist.framework.config.HndistConfig;
import com.hndist.framework.constant.Constants;
import com.hndist.framework.core.domain.AjaxResult;
import com.hndist.framework.utils.StringUtils;
import com.hndist.framework.utils.file.FileUploadUtils;
import com.hndist.framework.utils.file.FileUtils;
import com.hndist.framework.config.ServerConfig;
import com.hndist.framework.utils.file.ZipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * 通用请求处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/common")
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    private static final String FILE_DELIMETER = ",";

    /**
     * 通用下载请求
     * 
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = HndistConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 压缩文件下载
     * @param filePath
     * @param response
     */
    @GetMapping("/compressDownload")
    public void compressDownload(String filePath, HttpServletResponse response)
    {
        try
        {
            ZipUtil.zipFiles(filePath, response);
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = HndistConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            //String url = serverConfig.getUrl() + fileName;
            String url = fileName;
            AjaxResult ajax = AjaxResult.success();
            String filePaths = HndistConfig.getProfile()+url.replace("/profile", "");
            //判断是否是zip文件,解压shp压缩文件
            String extension = FileUploadUtils.getExtension(file);
            if ("zip".equalsIgnoreCase(extension)) {
                File shpFile = ZipUtil.unZip(new File(filePaths), filePath);
                filePaths = shpFile.getAbsolutePath();
            } else {
                File f = new File(filePaths);
                filePaths = f.getCanonicalPath();
            }
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            ajax.put("filePath", filePaths);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 通用上传请求（多个）
     */
    @PostMapping("/uploads")
    public AjaxResult uploadFiles(List<MultipartFile> files) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = HndistConfig.getUploadPath();
            List<String> urls = new ArrayList<String>();
            List<String> fileNames = new ArrayList<String>();
            List<String> newFileNames = new ArrayList<String>();
            List<String> originalFilenames = new ArrayList<String>();
            for (MultipartFile file : files)
            {
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                //String url = serverConfig.getUrl() + fileName;
                String url = fileName;
                urls.add(url);
                fileNames.add(fileName);
                newFileNames.add(FileUtils.getName(fileName));
                originalFilenames.add(file.getOriginalFilename());
            }
            AjaxResult ajax = AjaxResult.success();
            ajax.put("urls", StringUtils.join(urls, FILE_DELIMETER));
            ajax.put("fileNames", StringUtils.join(fileNames, FILE_DELIMETER));
            ajax.put("newFileNames", StringUtils.join(newFileNames, FILE_DELIMETER));
            ajax.put("originalFilenames", StringUtils.join(originalFilenames, FILE_DELIMETER));
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        try
        {
            if (!FileUtils.checkAllowDownload(resource))
            {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = HndistConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 上传文件到目录
     * @param uploadFiles 上传文件
     * @param pathName 指定目录完整路径
     * @return
     */
    @PostMapping(value = "/uploadFileByDir")
    public AjaxResult uploadFileByDir(MultipartFile[] uploadFiles, String pathName) {

        String uploadDatapath = HndistConfig.getUploadDatapath();
        if (StringUtils.isNotEmpty(pathName)) {
            uploadDatapath = uploadDatapath + File.separator + pathName;
        }
        //判断文件是否存在
        if (null != uploadFiles && uploadFiles.length > 0) {
            List<Map<String, String>> list = new ArrayList<>();
            for (int i = 0; i < uploadFiles.length; i++) {
                MultipartFile uploadFile = uploadFiles[i];
                //获取文件名称
                String name = uploadFile.getOriginalFilename();
                File file = new File(uploadDatapath);
                //判断目录是否存在
                if (!file.isDirectory()) {
                    file.mkdirs();
                }
                try {
                    //保存文件
                    File tmpFile = new File(file, name);
                    org.apache.commons.io.FileUtils.copyInputStreamToFile(uploadFile.getInputStream(),tmpFile);

                    //如果是解压文件
                    if (tmpFile.getCanonicalPath().endsWith(Constants.DOT_ZIP)) {
                        List<Map<String, String>> maps = ZipUtil.unZipFiles(tmpFile, uploadDatapath);
                        list.addAll(maps);
                    } else if (tmpFile.getCanonicalPath().endsWith(Constants.DOT_RAR)){
                        List<Map<String, String>> maps = ZipUtil.unRar(tmpFile, uploadDatapath);
                        list.addAll(maps);
                    } else {
                        //否则为普通文件
                        String canonicalPath = tmpFile.getCanonicalPath();
                        Map<String, String> map = new HashMap<>(16);
                        File desfile = new File(canonicalPath);
                        map.put("url", Constants.COMPRESSDOWNLOAD_SERVICES+"?pathName="+desfile.getName());
                        map.put("pathName", canonicalPath);
                        list.add(map);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (RarException r) {
                    r.printStackTrace();
                }
            }
            return AjaxResult.success(list);
        }
        return AjaxResult.error("操作失败");
    }

    /**
     * 通过⽂件路径直接修改⽂件名(重命名)
     * @param filePath 需要修改的⽂件的完整路径
     * @param newFileName 需要修改的⽂件的名称
     * @return
     */
    @PostMapping(value = "/modifyFileName")
    public String modifyFileName(String filePath, String newFileName) {
        File f = new File(filePath);
        // 判断原⽂件是否存在
        if (!f.exists()) {
            return null;
        }
        newFileName = newFileName.trim();
        // ⽂件名不能为空
        if ("".equals(newFileName)) {
            return null;
        }
        String newFilePath = null;
        // 判断是否为⽂件夹 filePath.substring(0, filePath.lastIndexOf("\\"))
        if (f.isDirectory()) {
            newFilePath = f.getParent() + File.separator + newFileName;
        } else {
            newFilePath = f.getParent() + File.separator  + newFileName + filePath.substring(filePath.lastIndexOf("."));
        }
        File nf = new File(newFilePath);
        // 判断需要修改为的⽂件是否存在（防⽌⽂件名冲突）
        if (!f.exists()) {
            return null;
        }
        try {
            // 修改⽂件名
            f.renameTo(nf);
        } catch(Exception err) {
            err.printStackTrace();
            return null;
        }
        return newFilePath;
    }

    /**
     * 拷贝文件到另一个目录
     * @param sourcePath 资源文件
     * @param destPath 目标文件路径
     * @throws IOException
     */
    @PostMapping("/moveFile2Dict")
    public void copyFile2FileChannels(String sourcePath, String destPath) throws IOException {
        if (StringUtils.isEmpty(sourcePath) || StringUtils.isEmpty(destPath)) {
            return;
        }
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        File sourceFile = new File(sourcePath);
        File sdestFile = new File(destPath);
        try {
            inputChannel = new FileInputStream(sourceFile).getChannel();
            outputChannel = new FileOutputStream(sdestFile).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
            // 删除源文件
            if (sourceFile.exists()) {
                sdestFile.delete();
            }
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }

    /**
     * 创建文件夹
     * @param myPath 完整路径
     */
    @PostMapping("/createFolder")
    public void createFolder(String myPath) {
        File file = new File(myPath);
        // 判断⽂件夹是否存在
        if (!file.exists()) {
            //多级⽂件夹⽬录
            file.mkdirs();
            //单级⽂件夹⽬录
            //myPpath.mkdir();
        }
    }
}
