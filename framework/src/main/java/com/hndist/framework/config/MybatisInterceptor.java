package com.hndist.framework.config;


import com.hndist.framework.utils.DateUtils;
import com.hndist.framework.utils.SecurityUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author
 */
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class MybatisInterceptor implements Interceptor {
	private static Logger log = LoggerFactory.getLogger(MybatisInterceptor.class);
	/**
	 * 获取当前操作用户
	 *
	 * @return 当前操作用户的用户名称
	 */
	private String getUserName() {
		try {
			return SecurityUtils.getUsername();
		} catch (Exception e) {
			log.debug("未登录用户操作，无法获取UID");
			return null;
		}
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		// 获取sql执行类型：insert、update、select、delete
		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
		Object parameter = invocation.getArgs()[1];
		if (parameter == null) {
			return invocation.proceed();
		}
		// 当sql为新增或更新类型时，自动填充操作人相关信息
		if (SqlCommandType.INSERT == sqlCommandType) {
			Field[] fields = getAllFields(parameter);
			for (Field field : fields) {
				try {
					// 注入创建人 - 可以改成从ThreadLocal获取 (UserInfoHolder.getUserCode();)
					if ("createBy".equals(field.getName())) {
						field.setAccessible(true);
						field.set(parameter, getUserName());
						field.setAccessible(false);
					}
					//注入创建时间
					if ("createTime".equals(field.getName())) {
						field.setAccessible(true);
						field.set(parameter, new Date());
						field.setAccessible(false);
					}
					//注入删除标记
					if ("delFlag".equals(field.getName())) {
						field.setAccessible(true);
						field.set(parameter, 0);
						field.setAccessible(false);
					}
				} catch (Exception e) {
					log.error("failed to insert data, exception = ", e);
				}
			}
		} else if (SqlCommandType.UPDATE == sqlCommandType) {
			Field[] fields;
			if (parameter instanceof MapperMethod.ParamMap) {
				MapperMethod.ParamMap<?> paramMap = (MapperMethod.ParamMap<?>) parameter;
				if (paramMap.containsKey("et")) {
					parameter = paramMap.get("et");
				} else {
					parameter = paramMap.get("param1");
				}
			}
			fields = parameter.getClass().getDeclaredFields();
			for (Field field : fields) {
				try {
					// 注入修改人 - 可以改成从ThreadLocal获取 (UserInfoHolder.getUserCode();)
					if ("updateBy".equals(field.getName())) {
						field.setAccessible(true);
						field.set(parameter, getUserName());
						field.setAccessible(false);
					}
					if ("updateTime".equals(field.getName())) {
						field.setAccessible(true);
						field.set(parameter, new Date());
						field.setAccessible(false);
					}
				} catch (Exception e) {
					log.error("failed to update data, exception = ", e);
				}
			}
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 获取类的所有属性，包括父类
	 */
	private Field[] getAllFields(Object object) {
		Class<?> clazz = object.getClass();
		List<Field> fieldList = new ArrayList<>();
		while (clazz != null) {
			fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
			clazz = clazz.getSuperclass();
		}
		Field[] fields = new Field[fieldList.size()];
		fieldList.toArray(fields);
		return fields;
	}

}
