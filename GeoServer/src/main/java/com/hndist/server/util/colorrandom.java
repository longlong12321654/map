package com.hndist.server.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class colorrandom {
    public static void main(String[] args){
        System.out.println(rgbtohex("217 64 75"));
    }
    public static List<String> sjys(){
        List<String> list=new ArrayList<>();
        Random rand=new Random();
        int red=rand.nextInt(256);
        int green=rand.nextInt(256);
        int blue=rand.nextInt(256);
        list.add(Integer.toString(red)+" "+Integer.toString(green)+" "+Integer.toString(blue));
        list.add(rgb2Hex(red,green,blue));

        return list;
    }

    //rgb转十六进制
    public static String rgb2Hex(int r,int g,int b){
        return String.format("#%02X%02X%02X", r,g,b);
    }

    //rgb转十六进制
    public static String rgbtohex(String rgbstr){
        int r= Integer.parseInt(rgbstr.split(" ")[0]);
        int g= Integer.parseInt(rgbstr.split(" ")[0]);
        int b= Integer.parseInt(rgbstr.split(" ")[0]);

        return String.format("#%02X%02X%02X", r,g,b);
    }

    //十六进制转rgb
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
