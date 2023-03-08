package com.hndist.cim.gisserver.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/***
 * All rights Reserved, Designed By www.tcsn.vip
 * @Title: SpringUtil
 * @Description: (Spring工具类)
 * @author 共享研发 
 * @date 2018-05-20 10:20
 * @version V1.0
 * @Copyright:  www.tcsn.vip Inc. All rights reserved.
 * 注意：本内容仅限于共享研发中心有限公司内部传阅，禁止外泄以及用于其他的商业目
 */ 
@Component
public class SpringUtil implements ApplicationContextAware {

    //上下文对象实例定义
    private static ApplicationContext applicationContext;

    /**
     * @Title: setApplicationContext
     * @Description: (获取到上下文对象实例)
     * @Param: [applicationContext]
     * @return: void
     * @throws
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    /**
     * @Title: getApplicationContext
     * @Description: (获取applicationContext)
     * @Param: []
     * @return: org.springframework.context.ApplicationContext
     * @throws
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @Title: getEnvironment
     * @Description: (获取上下文中的环境)
     * @Param: []
     * @return: org.springframework.core.env.Environment
     * @throws
     */ 
    public static Environment getEnvironment() {
        return applicationContext.getEnvironment();
    }

    /**
     * @Title: getBean
     * @Description: (通过name获取 Bean.)
     * @Param: [name]
     * @return: java.lang.Object
     * @throws
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    /**
     * @Title: getBean
     * @Description: (通过class获取Bean.)
     * @Param: [clazz]
     * @return: T
     * @throws
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * @Title: getBean
     * @Description: (通过name,以及Clazz返回指定的Bean)
     * @Param: [name, clazz]
     * @return: T
     * @throws
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

}
