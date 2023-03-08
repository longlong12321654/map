package com.hndist.framework.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @类名: TelnetUtil
 * @描述: telent端口是否可以连通
 * @作者 zqzhao
 * @日期 2022/1/14 17:24
 * @版本 V1.0.0
 */
public class TelnetUtil {
   /**
    * @描述: telne端口是否可以拼通BsResourceContents
    * @作者: zqzhao
    * @时间: 2022/1/14 17:26
    * @参数: [hostname, port, timeout]
    * @返回: boolean
    */
    public static boolean telnet(String hostname, int port, int timeout){
        Socket socket = new Socket();
        boolean isConnected = false;
        try {
            socket.connect(new InetSocketAddress(hostname, port), timeout); // 建立连接
            isConnected = socket.isConnected(); // 通过现有方法查看连通状态
//            System.out.println(isConnected);    // true为连通
        } catch (IOException e) {
            System.out.println("false");        // 当连不通时，直接抛异常，异常捕获即可
        }finally{
            try {
                socket.close();   // 关闭连接
            } catch (IOException e) {
                System.out.println("false");
            }
        }
        return isConnected;
    }

    public static void main(String[] args) {
//        String hostname = "14.215.178.37";
        String hostname = "192.168.1.110";    // hostname 可以是主机的 IP 或者 域名
        int port = 7777;
        int timeout = 200;
        boolean isConnected = telnet(hostname, port, timeout);
        System.out.println("telnet "+ hostname + " " + port + "\n==>isConnected: " + isConnected);
    }
}
