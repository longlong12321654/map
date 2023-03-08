package com.hndist.server.util;


import lombok.extern.log4j.Log4j2;
import org.apache.http.conn.ConnectTimeoutException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * All rights Reserved, Designed By www.jx-lab.com
 * @Title: HttpUtils
 * @Description: Http工具类
 * @author Liu
 * @date 2020/9/11 17:01
 * @version V1.0
 * @Copyright: www.jx-lab.com Inc. All rights reserved.
 * 注意：本内容仅限于众合景轩信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Log4j2
public class HttpUtils {

    /**
     * @Title: doPost
     * @Description: post请求
     * @param _url
     * @param jsonParams
     * @return {@link String}
     */
    public static String doPost(String _url, String jsonParams) {
        StringBuffer sb = new StringBuffer();
        PrintWriter out = null;
        InputStream is = null;
        try {
            URL url = new URL(_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-type", "application/json;charset=utf-8");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(jsonParams);
            out.flush();
            is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            is.close();
            conn.disconnect();

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }

        return sb.toString();
    }

    /**
     * @Title: doPost
     * @Description: post请求
     * @param _url
     * @param jsonParams
     * @param authorize
     * @return {@link String}
     */
    public static String doPost(String _url, String jsonParams, String authorize) {
        StringBuffer sb = new StringBuffer();
        PrintWriter out = null;
        InputStream is = null;
        try {
            URL url = new URL(_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-type", "application/json;charset=utf-8");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("Authorization", authorize);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(jsonParams);
            out.flush();
            is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            is.close();
            conn.disconnect();

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }

        return sb.toString();
    }

    /**
     * @Title: doGet
     * @Description: get请求
     * @param apiUrl
     * @return {@link String}
     */
    public static String doGet(String apiUrl) {
        return doGet(apiUrl, new HashMap<>());
    }

    /**
     * @Title: doGet
     * @Description: get请求
     * @param apiUrl
     * @param params
     * @return {@link String}
     */
    public static String doGet(String apiUrl, Map<String, String> params) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;
        if (!params.isEmpty()) {
            if (!apiUrl.contains("?")) {
                apiUrl = apiUrl + "?";
            }
            for (Map.Entry<String, String> entry : params.entrySet()) {
                apiUrl = apiUrl + entry.getKey() + "=" + entry.getValue() + "&";
            }
            apiUrl = apiUrl.substring(0, apiUrl.lastIndexOf("&"));
        }

        try {
            /** 创建远程url连接对象 */
            URL url = new URL(apiUrl);
            /** 通过远程url对象打开一个连接，强制转换为HttpUrlConnection类型 */
            connection = (HttpURLConnection) url.openConnection();

            /** 设置连接方式：GET */
            connection.setRequestMethod("GET");
            /** 设置连接主机服务器超时时间：15000毫秒 */
            connection.setConnectTimeout(15000);
            /** 设置读取远程返回的数据时间：60000毫秒 */
            connection.setReadTimeout(60000);

            /** 设置通用的请求属性 */
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            /** 发送GET方式请求，使用connet方法建立和远程资源之间的实际连接即可 */
            connection.connect();

            /*-------------------------->*/
            /** 获取所有相应头字段 */
            Map<String, List<String>> map = connection.getHeaderFields();
            /** 遍历响应头字段 */
            for (String key : map.keySet()) {
                System.out.println(key + "---------->" + map.get(key));
            }

            /** 请求成功：返回码为200 */
            if (connection.getResponseCode() == 200) {
                /** 通过connection连接，获取输入流 */
                is = connection.getInputStream();
                /** 封装输入流is，并指定字符集 */
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                /** 存放数据 */
                StringBuffer sbf = new StringBuffer();
                String line = null;
                while ((line = br.readLine()) != null) {
                    sbf.append(line);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }

        } catch (SocketTimeoutException | ConnectTimeoutException e) {
            e.printStackTrace();
            System.out.println("请求连接超时：" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("请求异常，异常信息：" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /** 关闭资源 */
            try {
                if (null != br) {
                    br.close();
                }
                if (null != is) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            /** 关闭远程连接 */
            // 断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            // 固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些
            connection.disconnect();

            System.out.println("--------->>> GET request end <<<----------");
        }
        return result;
    }

    /**
     * doPost
     * @param reqUrl
     * @param parameters
     * @param data
     * @param request
     * @return
     * @throws ServletException
     */
    public String doPost(String reqUrl, Map parameters, String data,
                         HttpServletRequest request) throws ServletException {
        // 定义HttpURLConnection对象
        HttpURLConnection url_con = null;
        String responseContent = null;
        try {
            // 实例化url对象
            URL url = new URL(reqUrl);
            // 拼装内容
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setDoInput(true);
            url_con.setDoOutput(true);
            url_con.setUseCaches(true);
            url_con.setRequestMethod("POST");
            url_con.setRequestProperty("Content-Type", request.getContentType()
                    + "; charset=iso-8895-1");
            url_con.connect();
            // 设置输出流
            OutputStream out = url_con.getOutputStream();
            BufferedWriter rw = new BufferedWriter(new OutputStreamWriter(out,
                    request.getCharacterEncoding()));
            rw.write(data); // 写入请求的字符串
            rw.flush();
            rw.close();

            InputStream in = url_con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
                    request.getCharacterEncoding()));
            String tempLine = rd.readLine();
            StringBuffer tempStr = new StringBuffer();
            String crlf = System.getProperty("line.separator");
            while (tempLine != null) {
                tempStr.append(tempLine);
                tempStr.append(crlf);
                tempLine = rd.readLine();
            }
            responseContent = tempStr.toString();
            rd.close();
            in.close();
//            Baseuser user = (Baseuser) (request.getSession()
//                    .getAttribute("curuser"));
        } catch (IOException e) {

        } finally {
            if (url_con != null) {
                try {
                    if (url_con.getResponseCode() == 200) {
                        url_con.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                url_con = null;
            }
        }
        return responseContent;
    }

    /**
     * 获取请求参数
     * @param request
     * @return
     */
    public static HashMap<String,String> getParameterNames(HttpServletRequest request) {
        HashMap paramNameMap = new HashMap<String,Object>();
        // 获取请求参数集合
        Enumeration paramNames = request.getParameterNames();
        // 遍历请求参数
        while (paramNames.hasMoreElements()) {
            // 获取参数名称
            String paramName = (String) paramNames.nextElement();
            // 获取参数值数组
            String[] paramValues = request.getParameterValues(paramName);
            // 判断请求参数是否唯一
            if (paramValues != null && paramValues.length > 0) {
                try {
                    paramNameMap.put(paramName.toLowerCase(),isContainChinese(paramValues[0])
                            ? URLEncoder.encode(paramValues[0], "utf-8") : paramValues[0]);
                } catch (UnsupportedEncodingException e) {
                    e.getMessage();
                }
            }
        }
        return paramNameMap;
    }

    /**
     * doGet
     * @param realurl
     * @throws IOException
     * @throws MalformedURLException
     * @throws ProtocolException
     */
    public static byte[] doGet(StringBuffer realurl,Map<String,String> paramName ,HttpServletRequest request) throws IOException,
            MalformedURLException, ProtocolException {
        // 是否是第一个参数
        boolean isFirstParam = true;

        for (String key : paramName.keySet()) {
            String paramValue = paramName.get(key);
            // 判断请求参数是否唯一
            if (paramValue != null && paramValue.length() > 0) {
                if (isFirstParam) {
                    if (realurl.indexOf("?") > -1) {
                        realurl.append("&");
                    } else {
                        realurl.append("?");
                    }
                    isFirstParam = false;
                } else {
                    realurl.append("&");
                }
                // 添加请求参数
                realurl.append(key);
                realurl.append("=");
                //判断是否为中文字符
                realurl.append(paramValue);

            }
        }

        // 创建请求对象
        String sourceUrl = realurl.toString();
        if (isContainChinese(sourceUrl)) {
            sourceUrl = encodeURL(sourceUrl);
        }
        URL sendurl = new URL(sourceUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) sendurl
                .openConnection();
        // 不使用缓存
        httpURLConnection.setDefaultUseCaches(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestMethod(request.getMethod());
        httpURLConnection.connect();
        // 获取输入流
        InputStream inStream = httpURLConnection.getInputStream();

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        httpURLConnection.disconnect();
        return outStream.toByteArray();
    }

    public static String encodeURL(String url) {
        try {
            //http://192.168.0.177:10000/terrain/北京市.tif/layer.json?layers=terrain/北京市.tif/layer.json
            String result = URLEncoder.encode(url, "UTF-8");
            //http%3A%2F%2F192.168.0.177%3A10000%2Fterrain%2F%E5%8C%97%E4%BA%AC%E5%B8%82.tif%2Flayer.json%3Flayers%3Dterrain%2F%E5%8C%97%E4%BA%AC%E5%B8%82.tif%2Flayer.json
            result = result.replaceAll("%3A", ":")
                    .replaceAll("%2F", "/")
                    .replaceAll("%3F", "?")
                    .replaceAll("%3D", "=")
                    .replaceAll("\\+", "%20");//+实际上是 空格 url encode而来
            //http://192.168.0.177:10000/terrain/%E5%8C%97%E4%BA%AC%E5%B8%82.tif/layer.json?layers=terrain/%E5%8C%97%E4%BA%AC%E5%B8%82.tif/layer.json
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 判断是否是中文汉字
     *
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str) {
        Matcher m = pattern.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

}
