package com.hndist.cim.gisserver.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName: colorrandom
 * @Description: 随机颜色
 * @author 河南数慧信息技术有限公司
 * @date 2022/11/23 16:14
 * @version V1.0.0
 * @Copyright: www.hndist.com All rights reserved.
 * 注意：本内容仅限于共享研发中心内部传阅，禁止外泄以及用于其他的商业目
 */
public class ColorUtil {
    
    /*
     * @author WY 
     * @Description: 生成RGB
     * @date 2022/11/23 16:14
     * @Param [] 
     * @return java.util.List<java.lang.String> 
     */
    public static StringBuffer sjys(){
        //List<String> list=new ArrayList<>();
        StringBuffer sbf = new StringBuffer();
        Random rand=new Random();
        int red=rand.nextInt(256);
        int green=rand.nextInt(256);
        int blue=rand.nextInt(256);
        //list.add(Integer.toString(red)+" "+Integer.toString(green)+" "+Integer.toString(blue));
        sbf.append(rgb2Hex(red,green,blue));

        return sbf;
    }

    /*
     * @author WY
     * @Description: rgb转十六进制
     * @date 2022/11/23 16:14
     * @Param [r, g, b]
     * @return java.lang.String
     */
    public static String rgb2Hex(int r,int g,int b){
        return String.format("#%02X%02X%02X", r,g,b);
    }

    /*
     * @author WY
     * @Description: rgb转十六进制
     * @date 2022/11/23 16:14
     * @Param [rgbstr]
     * @return java.lang.String
     */
    public static String rgbtohex(String rgbstr){
        int r= Integer.parseInt(rgbstr.split(" ")[0]);
        int g= Integer.parseInt(rgbstr.split(" ")[0]);
        int b= Integer.parseInt(rgbstr.split(" ")[0]);

        return String.format("#%02X%02X%02X", r,g,b);
    }

    /*
     * @author WY
     * @Description: 十六进制转rgb
     * @date 2022/11/23 16:14
     * @Param [hexStr]
     * @return java.lang.String
     */
    public static String Hextorgb(String hexStr){
        if(hexStr != null && !"".equals(hexStr) && hexStr.length() == 7){
            int[] rgb = new int[3];
            rgb[0] = Integer.valueOf(hexStr.substring( 1, 3 ), 16);
            rgb[1] = Integer.valueOf(hexStr.substring( 3, 5 ), 16);
            rgb[2] = Integer.valueOf(hexStr.substring( 5, 7 ), 16);
            return String.valueOf(rgb[0])+" "+String.valueOf(rgb[1])+" "+String.valueOf(rgb[2]);
        }
        return null;
    }

}
