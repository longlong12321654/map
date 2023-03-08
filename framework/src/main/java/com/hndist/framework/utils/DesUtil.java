package com.hndist.framework.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/***
 * All rights Reserved, Designed By www.tcsn.vip
 * @Title: DesUtil
 * @Description: (Des加密解密)
 * @author 共享研发 
 * @date 201812-02 14:19
 * @version V1.0
 * @Copyright:  www.tcsn.vip Inc. All rights reserved.
 * 注意：本内容仅限于共享研发中心有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class DesUtil {
	
    /**
     * 加密秘钥
     */
    private static String KEY = null;
    
    /**
     * 静态块
     */
    static{
    	try{
    		//加密秘钥
    		KEY = "HNSH_*&*";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * Description 根据键值进行加密
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt(String data) {
        return encrypt(data,KEY);
    }
    
    /**
     * Description 根据键值进行加密
     * @param data 
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encrypt(String data,String key) {
		try {
			byte[] bt = encrypt(data.getBytes(), key.getBytes());
			Encoder encoder = Base64.getEncoder();
	        String strs = encoder.encodeToString(bt);
	        return strs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 
     * @Title: getFileMD5
     * @Description: TODO(获取文件MD5)
     * @param @param file
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getFileMD5(File file) {
        if (!file.exists() || !file.isFile()) {
            return null;  
        }
        MessageDigest digest = null;  
        FileInputStream in = null;  
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");  
            in = new FileInputStream(file);  
            while ((len = in.read(buffer, 0, 1024)) != -1) {  
                digest.update(buffer, 0, len);  
            }
            in.close();  
            BigInteger bigInt = new BigInteger(1, digest.digest());  
            return bigInt.toString(16);  
        } catch (Exception e) {
            e.printStackTrace();  
            return null;  
        }  
        
    }

    public static String stringToMD5(String plainText) {
       byte[] secretBytes = null;
      try {
           secretBytes = MessageDigest.getInstance("md5").digest(
           plainText.getBytes());
      } catch (NoSuchAlgorithmException e) {
          throw new RuntimeException("没有这个md5算法！");
    }
       String md5code = new BigInteger(1, secretBytes).toString(16);
    for (int i = 0; i < 32 - md5code.length(); i++) {
         md5code = "0" + md5code;
    }
    return md5code;
}
    
    /**
     * Description 根据键值进行解密
     * @param data
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data) throws IOException,
            Exception {
        return decrypt(data,KEY);
    }
    
    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data,String key) throws IOException,
            Exception {
        if (data == null){
            return null;
        }
        Decoder decoder = Base64.getDecoder();
        byte[] buf = decoder.decode(data);
        byte[] bt = decrypt(buf,KEY.getBytes());
        return new String(bt);
    }
    
    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
    	try{
	        // 生成一个可信任的随机数源
	        SecureRandom sr = new SecureRandom();
	        // 从原始密钥数据创建DESKeySpec对象
	        DESKeySpec dks = new DESKeySpec(key);
	        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey securekey = keyFactory.generateSecret(dks);
	        // Cipher对象实际完成加密操作
	        Cipher cipher = Cipher.getInstance("DES");
	        // 用密钥初始化Cipher对象
	        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
	        return cipher.doFinal(data);
    	}catch (Exception e) {
    		throw e;
		}
    }
    
    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
    	try{
	        // 生成一个可信任的随机数源
	        SecureRandom sr = new SecureRandom();
	        // 从原始密钥数据创建DESKeySpec对象
	        DESKeySpec dks = new DESKeySpec(key);
	        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey securekey = keyFactory.generateSecret(dks);
	        // Cipher对象实际完成解密操作
	        Cipher cipher = Cipher.getInstance("DES");
	        // 用密钥初始化Cipher对象
	        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
	        return cipher.doFinal(data);
    	}catch(Exception e){
    		throw e;
    	}
    }

    public static void main(String[] args) throws Exception {
        String token_encrypt =  DesUtil.encrypt("admin@hndist@admin123");
        if(token_encrypt!=null) {
            String token = DesUtil.decrypt(token_encrypt);
            String[] userinfo = token.split("@hndist@");
            if(userinfo!=null && userinfo.length==2){
                System.out.println(userinfo[0]+":"+userinfo[1]);
            }
        }
    }

}
