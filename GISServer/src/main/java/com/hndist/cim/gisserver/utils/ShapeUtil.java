package com.hndist.cim.gisserver.utils;

import com.alibaba.fastjson.JSONArray;
import com.hndist.cim.gisserver.entity.Feature;
import com.hndist.cim.gisserver.entity.Features;


import java.io.*;
import java.nio.charset.Charset;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author FWY
 * @version V1.0.0
 * @ClassName: ShpUtil
 * @Description: TODO
 * @date 2022/5/26 1:50 下午
 * @Copyright: http://www.hndist.com All rights reserved.
 * 注意：本内容仅限于河南数慧信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class ShapeUtil {

    /**
     * @ClassName: ShpUtil
     * @Description: 解析文本文件
     * @author FWY
     * @date 2022/5/26 5:20 下午
     * @version V1.0.0
     * @Copyright: http://www.hndist.com All rights reserved.
     * 注意：本内容仅限于河南数慧信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目
     */
    public static Features parsingTxtFile(String path)  {
        //定义读取列
        String lineTxt = null;
        //序号
        String ordrnumber = null;
        //定义Features对象
        Features features = new Features();
        //定义临时Feature
        Feature tmpFeature = null;

        try {
            //文件读取
            InputStream inputStream = new FileInputStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

            //遍历文件数据
            while ((lineTxt = bufferedReader.readLine()) != null) {
                //进行字符串拆分
                String[] cols = lineTxt.split(",");
                //根据自定义拆分标准封装数据
                if (cols != null && cols.length >= 4) {
                    //根据序号、X、Y三个坐标顺序进行判定是否是数字类型
                    if (isNumeric(cols[1]) && isNumeric(cols[2]) && isNumeric(cols[3])) {
                        //判定是否需要创建新的Feature
                        if (ordrnumber == null || !ordrnumber.equalsIgnoreCase(cols[1])) {
                            ordrnumber = cols[1];
                            tmpFeature = new Feature();
                            features.addFeature(tmpFeature);
                        }
                        //定义经纬度点信息
                        tmpFeature.addPoint(Double.parseDouble(cols[2]), Double.parseDouble(cols[3]));
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //返回features
        return features;
    }

    /**
     * @author FWY
     * @Description: 判断是否是数字
     * @date 2022/5/30 3:46 下午
     * @Param [str]
     * @return boolean
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
        return pattern.matcher(str).matches();
    }

    /**
     * 压缩shape文件
     *
     * @param shpPath shape文件路径（包含shape文件名称）
     */
    public static String zipShapeFile(String shpPath) {
        //定义压缩路径
        String zipPath = null;
        try {
            File shpFile = new File(shpPath);
            String shpRoot = shpFile.getParentFile().getPath();
            String shpName = shpFile.getName().substring(0, shpFile.getName().lastIndexOf("."));

            zipPath = shpRoot + File.separator + shpName + ".zip";
            File zipFile = new File(zipPath);
            InputStream input = null;
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            // zip的名称为
            zipOut.setComment(shpName);
            String[] shpFiles = new String[]{
                    shpRoot + File.separator + shpName + ".dbf",
                    shpRoot + File.separator + shpName + ".prj",
                    shpRoot + File.separator + shpName + ".shp",
                    shpRoot + File.separator + shpName + ".shx",
                    shpRoot + File.separator + shpName + ".fix"
            };

            for (int i = 0; i < shpFiles.length; i++) {
                File file = new File(shpFiles[i]);
                input = new FileInputStream(file);
                zipOut.putNextEntry(new ZipEntry(file.getName()));
                int temp = 0;
                while ((temp = input.read()) != -1) {
                    zipOut.write(temp);
                }
                input.close();
            }
            zipOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zipPath;
    }

    public static void main(String[] args) {
        try {
            Features features = ShapeUtil.parsingTxtFile("/Users/agile/Desktop/解析坐标--多个.txt");
            System.out.println(JSONArray.toJSON(features.getFeatureList()));
        }catch (Exception e){
            e.printStackTrace();
        }
//        获取Features
//        Features features = ShapeUtil.parsingShpFile("/Users/tcsn/Desktop/济源档案测试/济源档案测试.shp");
//        System.out.println(JSONArray.toJSON(features.getFeatureList()));
    }
}
