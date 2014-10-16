package com.devanshu.compiler.utils;

import javax.inject.Named;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Named
public class WebContextUtils  implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	static <T> T getBean(Class<T> beanClass) {
	       return applicationContext.getBean(beanClass);
	    }

}
