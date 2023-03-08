package com.hndist.cim.gisserver.utils;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import org.springframework.stereotype.Component;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;

/***
 * All rights Reserved, Designed By www.tcsn.vip
 * @Title: FreemarkerManager
 * @Description: (模板管理)
 * @author 共享研发
 * @date 2018-05-22 12:06
 * @version V1.0
 * @Copyright:  www.tcsn.vip Inc. All rights reserved.
 * 注意：本内容仅限于共享研发中心有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Component
public class FreemarkerUtil {

    /**
     * 模板配置对象
     */
    public static Configuration configuration;

    /**
     * 是否压缩
     */
    public static boolean isCompressor = true;

    /**
     *
     * @param templateFilePath   模板资源相对路径
     * @param codeFilePath  代码绝对路径
     * @param data  替换数据
     */
    public static void generateFile(String templateFilePath, String codeFilePath,
                                    Map<String, Object> data) {
        try {
            //判断模板配置对象是否为空
            if(configuration==null){
                //获取模板配置对象
                configuration = SpringUtil.getBean(Configuration.class);
            }
            //获取模板
            Template template = configuration.getTemplate(templateFilePath);
            //创建Html文件
            File htmlFile = new File(codeFilePath);
            //获取文件主目录，如果不存在则创建目录
            File htmlDirectory = htmlFile.getParentFile();
            if (!htmlDirectory.exists()) {
                htmlDirectory.mkdirs();
            }
            //输出Html文件
            FileOutputStream out = new FileOutputStream(htmlFile);
            //输出的文件
            Writer outWriter = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            //判断是否需要压缩
            template.process(data,outWriter);
            //刷新文件流
            out.flush();
            //关闭文件流
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param templateFilePath   模板资源相对路径[需要继续优化代码,增强验证!!!]
     * @param data  替换数据
     */
    public static String generateString(String templateFilePath,Map<String, Object> data) {
        try {
            //判断模板配置对象是否为空
            if(configuration==null){
                //获取模板配置对象
                configuration = SpringUtil.getBean(Configuration.class);
            }
            //获取模板
            Template template = configuration.getTemplate(templateFilePath);

            //创建StringWriter
            StringWriter outWriter = new StringWriter();
            //写入文件流
            template.process(data, outWriter);
            //刷新文件流
            outWriter.flush();
            //关闭文件流
            outWriter.close();
            if(isCompressor){
                return htmlCompress(outWriter.getBuffer().toString());
            }else{
                return outWriter.getBuffer().toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Html压缩
     * @param text
     * @return
     */
    private static String htmlCompress(String text) {
        HtmlCompressor compressor = new HtmlCompressor();
        compressor.setEnabled(true);
        compressor.setCompressCss(true);
        compressor.setYuiJsPreserveAllSemiColons(true);
        compressor.setYuiJsLineBreak(1);
        compressor.setPreserveLineBreaks(false);
        compressor.setRemoveIntertagSpaces(true);
        compressor.setRemoveComments(true);
        compressor.setRemoveMultiSpaces(true);
        compressor.setCompressJavaScript(true);
        return compressor.compress(text);
    }

}
