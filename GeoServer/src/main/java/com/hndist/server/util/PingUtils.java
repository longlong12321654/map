package com.hndist.server.util;

import com.hndist.framework.utils.TelnetUtil;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zy
 * @version 1.1
 * @className PingUtils
 * @description TODO
 * @date 2022/5/30 22:09
 **/
public class PingUtils {
    private Queue<String> allIp;
    private Queue<String> newIp = new LinkedList<String>();
    private int fetchedNum = 0; // 已经取得的任务数量，每次从队列中取一个ip就加1

    public Queue<String> getNewIp() {
        return newIp;
    }

    public void setNewIp(Queue<String> newIp) {
        this.newIp = newIp;
    }

    public PingUtils() {
        // 首先创建一个队列用于存储所有ip地址

        allIp = new LinkedList<String>();
//        for (int i = 0; i < 10; i++) {
            //allIp.offer("192.168.9." + i);
            for (int j = 0; j < 256; j++) {
                allIp.offer("192.168." + 1 + "." + j);
            }
//        }

    }

    public void startPing() {
        // 创建一个线程池，多个线程同时跑
        int threadNum = 100;
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        for (int i = 0; i < threadNum; i++) {
            executor.execute(new PingRunner());
        }
        executor.shutdown();
        try {
            while (!executor.isTerminated()) {
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("Fetched num is " + fetchedNum);
    }

    private class PingRunner implements Runnable {
        private String taskIp = null;
        @Override
        public void run() {
            try {
                while ((taskIp = getIp()) != null) {
                    //InetAddress addr = InetAddress.getByName(taskIp);
                    boolean succ = TelnetUtil.telnet(taskIp, 6080, 200);
//                    if (addr.isReachable(1000)) {
                    if (succ) {
                        newIp.add(taskIp);
                        //System.out.println("host ["+taskIp+"] is reachable");
                    } else {
                        //System.out.println("host ["+taskIp+"] is not reachable");
                    }
                }
//            } catch (SocketException e) {
//                System.out.println("host ["+taskIp+"] permission denied");
            } catch (Exception e) {
                //System.out.println("---------------------------------------------"+taskIp);
                e.printStackTrace();
            }
        }

        public String getIp() {
            String ip = null;
            synchronized (allIp) {
                ip = allIp.poll();
            }
            if (ip != null) {
                fetchedNum++;
            }
            return ip;
        }

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        PingUtils tester = new PingUtils();
        tester.startPing();
        Queue<String> newIp = tester.getNewIp();
        //System.out.println(JSONArray.toJSON(newIp));
        //System.out.println(newIp.size());
        long end = System.currentTimeMillis();
        System.out.println((end-start)+"ms");
    }
}
