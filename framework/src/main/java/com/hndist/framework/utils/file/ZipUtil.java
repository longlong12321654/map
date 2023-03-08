package com.hndist.framework.utils.file;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import com.hndist.framework.config.ServerConfig;
import com.hndist.framework.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author zy
 * @descript
 */
public class ZipUtil {

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 功能:压缩多个文件，输出压缩后的zip文件流
     *
     * @return
     */
    public static void zipFiles(String filepath, HttpServletResponse response) {
        File desfile = new File(filepath);
        if (!desfile.exists()) {
            return;
        }
        File parentFile = desfile.getParentFile();

        byte[] buf = new byte[1024];
        // 获取输出流
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // 清除buffer
            response.reset();
            // 不同类型的文件对应不同的MIME类型
            response.setContentType("application/x-msdownload");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".zip");

            // ZipOutputStream类：完成文件或文件夹的压缩
            ZipOutputStream out = new ZipOutputStream(bos);
            File[] files = parentFile.listFiles();
            String ofileName = desfile.getName();
            ofileName = ofileName.substring(0,ofileName.lastIndexOf("."));


            if (files.length > 0) {
                for (File f:files) {
                    String fileName = f.getName();
                    String name="";
                    if (f.isFile()) {
                        name = fileName.substring(0,fileName.lastIndexOf("."));
                    }
                    //过滤坐标文件
                    if (Objects.equals(ofileName, name) && !desfile.getName().endsWith(".zip")) {
                        FileInputStream in = new FileInputStream(f);
                        // 给列表中的文件单独命名
                        out.putNextEntry(new ZipEntry(f.getName()));
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        out.closeEntry();
                        in.close();
                    }
                }
            }
            out.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取zip文件中.shp文件所在的路径
     *
     * @param srcFile 源文件
     * @param destDirPath 输出路径
     * @return
     * @throws RuntimeException
     */
    public static File unZip(File srcFile, String destDirPath) throws RuntimeException {
        final int BUFFER_SIZE = 2048;
        ZipFile zipFile = null;
        File shpFile = null;
        try {
            zipFile = new ZipFile(srcFile, Charset.forName("gbk"));
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                //System.out.println("解压" + entry.getName());

                // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                String outPath = (destDirPath + File.separator + entry.getName()).replaceAll("\\\\", "/");
                File targetFile = new File(outPath);
                if (entry.isDirectory()) {
                    targetFile.mkdirs();
                    continue;
                }
                if (entry.getName().endsWith(".shp")) {
                    shpFile = targetFile;
                }
                System.out.println(outPath);
                // 将压缩文件内容写入到这个文件中
                InputStream is = zipFile.getInputStream(entry);
                FileOutputStream fos = new FileOutputStream(targetFile);
                int len;
                byte[] buf = new byte[BUFFER_SIZE];
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                // 关流顺序，先打开的后关闭
                fos.close();
                is.close();
            }
            System.out.println("******************解压完毕********************");
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return shpFile;
    }

    /**
     * 解压zip压缩文件
     *
     * @param zipFile zip压缩包完整路径
     * @param descDir 目标目录地址
     * @return
     * @throws IOException
     */
    public static List<Map<String, String>> unZipFiles(File zipFile, String descDir) throws IOException {

        File outFile = null;
        List<Map<String, String>> list = new ArrayList<>();
        try {
            //解决中文文件夹乱码
            ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
            File pathFile = new File(descDir);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }

            int count = 0;
            //即使有内层目录，目录里的文件也是可以在这里遍历出来的，只不过在内层目录之后，而且是带内层目录的全路径，所以解压时需要判断路径是否存在，
            // 不存在则创建内层目录dir;判断一下内层目录，continue,目录里面的文件再写
            for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zip.getInputStream(entry);
                String outPath = (descDir + File.separator + zipEntryName).replaceAll("\\\\", "/");
                File file = new File(outPath);
                //有内层文件夹，需要创建新的内层目录后continue,后面内层文件夹里的压缩文件才有合法新路径，否则内层文件夹里的压缩文件使用路径创建流时报拒绝访问异常，因为父目录不存在
                if (entry.isDirectory()) {
                    //是文件而不是文件夹路径的不能写这个，会创建到以文件名为最终目录名的全目录导致与文件路径冲突，下面流关联文件时因读取了同名文件夹而发生异常
                    file.mkdirs();
                    continue;
                }
                if (zipEntryName.endsWith(Constants.DOT_JSON)) {
                    count = 1;
                }
                // 判断文件类型
                boolean isContinue = false;
                String fileType = "";
                if (zipEntryName.endsWith(Constants.DOT_SHP)) {
                    isContinue = true;
                    fileType = "shp";
                } else if (count==1 && zipEntryName.endsWith(Constants.DOT_JSON)) {
                    isContinue = true;
                    fileType = "3dtiles";
                } else if (zipEntryName.endsWith(Constants.DOT_GLTF)) {
                    isContinue = true;
                    fileType = "gltf";
                } else if (zipEntryName.endsWith(Constants.DOT_GLB)) {
                    isContinue = true;
                    fileType = "glb";
                } else if (zipEntryName.endsWith(Constants.DOT_TIF)) {
                    isContinue = true;
                    fileType = "tif";
                } else if (zipEntryName.endsWith(Constants.DOT_IOT)) {
                    isContinue = true;
                    fileType = "iot";
                }

                if (isContinue) {
                    Map<String, String> map = new HashMap<>(16);
                    // 输出路径
                    outFile = file;
                    String outpath = outFile.getCanonicalPath().replace("\\", "/");
                    map.put("url", Constants.COMPRESSDOWNLOAD_SERVICES+"?filePath="+outpath);
                    map.put("filePath", outpath);
                    map.put("fileType", fileType);
                    list.add(map);
                }
                FileOutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }

                in.close();
                out.close();

                //unZipFiles(file, outPath.substring(0, outPath.lastIndexOf('/')));//可递归解压zip,jar文件，不能解压rar文件
            }
            //System.out.println("******************解压完毕********************");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解压Rar文件
     *
     * @param rarFile rar文件
     * @param descDir 文件目录
     */
    public static List<Map<String, String>> unRar(File rarFile, String descDir) throws IOException, RarException {
        File dstDiretory = new File(descDir);

        if (!dstDiretory.exists()) {
            dstDiretory.mkdirs();
        }
        Archive archive = new Archive(rarFile);
        List<Map<String, String>> filepathList = new ArrayList<>();

        if (archive != null) {
            archive.getMainHeader().print(); //打印文件信息.
            FileHeader fh = archive.nextFileHeader();
            while (fh != null) {
                // 判断编码,解决中文乱码的问题
                String localpath = fh.isUnicode() ? fh.getFileNameW() : fh.getFileNameString();
                //文件
                File outFile = new File(descDir + File.separator + localpath.trim());
                String outPath = outFile.getAbsolutePath().replaceAll("\\*", "/");
                //判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf('\\')));
                if (!file.exists()) {
                    file.mkdirs();
                }
                //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if (new File(outPath).isDirectory()) {
                    break;
                }
                //输出文件路径信息
                boolean b = outFile.getName().endsWith(Constants.DOT_SHP) || outFile.getName().endsWith(Constants.DOT_JSON);
                if ( b) {
                    Map<String, String> map = new HashMap<>();
                    map.put("url", Constants.COMPRESSDOWNLOAD_SERVICES+"?filePath="+outFile.getCanonicalPath());
                    map.put("filePath", outFile.getAbsolutePath());
                    filepathList.add(map);
                }
                FileOutputStream os = new FileOutputStream(outFile);
                archive.extractFile(fh, os);
                os.close();
                fh = archive.nextFileHeader();
            }
        }

        archive.close();
        return filepathList;
    }

}
