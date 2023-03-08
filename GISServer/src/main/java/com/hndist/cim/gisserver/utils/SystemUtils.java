package com.hndist.cim.gisserver.utils;

/**
 * @author 河南数慧信息技术有限公司
 * @version V1.0.0
 * @ClassName: SystemUtils
 * @Description: TODO
 * @date 2023/2/1 09:42
 * @Copyright: www.hndist.com All rights reserved.
 * 注意：本内容仅限于共享研发中心内部传阅，禁止外泄以及用于其他的商业目
 */
public class SystemUtils {
    /**
     * 判断操作系统是否是 Windows
     *
     * @return true：操作系统是 Windows
     *         false：其它操作系统
     */
    public static boolean isWindows() {
        String osName = getOsName();

        return osName != null && osName.startsWith("Windows");
    }

    /**
     * 判断操作系统是否是 MacOS
     *
     * @return true：操作系统是 MacOS
     *         false：其它操作系统
     */
    public static boolean isMacOs() {
        String osName = getOsName();

        return osName != null && osName.startsWith("Mac");
    }

    /**
     * 判断操作系统是否是 Linux
     *
     * @return true：操作系统是 Linux
     *         false：其它操作系统
     */
    public static boolean isLinux() {
        String osName = getOsName();

        return (osName != null && osName.startsWith("Linux")) || (!isWindows() && !isMacOs());
    }

    /**
     * 获取操作系统名称
     * @return os.name 属性值
     */
    public static String getOsName() {
        return System.getProperty("os.name");
    }
}
