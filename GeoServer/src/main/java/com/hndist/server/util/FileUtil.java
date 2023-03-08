package com.hndist.server.util;

import org.apache.commons.io.FileUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zy
 */
public class FileUtil {

    public static void delAllFiles(String path) throws IOException {

        File file=new File(path);
        File[] files=file.listFiles();

        for (int i = 0; i <files.length ; i++) {
            if (files[i].isFile()){
                file.delete();
            }else {
                 FileUtils.deleteDirectory(files[i]);
            }
        }

    }

    /**
     * 获取本机盘符
     * @return
     */
    public static List<Map<String, Object>> listRoots() {
        File[] roots = File.listRoots();
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < roots.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("path", roots[i].getPath());
            list.add(map);
        }
        return list;
    }

    /**
     * 仅获取本地磁盘（除去网络磁盘等）
     * @return
     */
    public static List<Map<String, Object>> fileSystemView() {
        File[] roots = File.listRoots();
        List<Map<String, Object>> list = new ArrayList<>();
        FileSystemView sys = FileSystemView.getFileSystemView();
        for (int i = 0; i < roots.length; i++) {
            Map<String, Object> map = new HashMap<>();
            if(!sys.getSystemTypeDescription(roots[i]).equals("本地磁盘")){
                continue;
            }
            // 磁盘路径
            map.put("path", roots[i].getPath());
        }
        return null;
    }

    /**
     * 遍历某一个盘符的文件
     * @return
     */
    public static List<Map<String, Object>> subFiles(String dir) {
        File file = new File(dir);
        List<Map<String, Object>> lists = new ArrayList<>();
        if (file.isAbsolute()) { // 判断是否为根目录
            File[] list = file.listFiles(); // 使用数组接收带有完整路径的文件夹
            for (int i = 0; i < list.length; i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("path", list[i].getPath());
                lists.add(map);
            }
            return lists;
        }
        return null;
    }
}
