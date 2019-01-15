package com.ddou.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {
	@Autowired
	private static ApplicationContext cxt;

	public static ApplicationContext getCxt() {
		return cxt;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		cxt = applicationContext;
	}

	public static <T> T getBean(Class<T> clazz) {
		T dao = getCxt().getBean(clazz);
		return dao;
	}

}
