package io.maphey.lock.zk.lock.annotation.processor;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;

import io.maphey.lock.zk.lock.annotation.ZkLock;

public class AnnotationBean implements BeanPostProcessor, ApplicationContextAware, BeanFactoryAware {

	private AbstractBeanFactory beanFactory;
	private ApplicationContext applicationContext;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		if (beanFactory instanceof AbstractBeanFactory) {
			this.beanFactory = (AbstractBeanFactory) beanFactory;
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return parseMethods(bean, bean.getClass().getDeclaredMethods());
	}

	private Object parseMethods(Object bean, Method[] methods) {
		for (Method method : methods) {
			ZkLock zkLockAnnotation = AnnotationUtils.findAnnotation(method, ZkLock.class);
			if (zkLockAnnotation != null) {
				return handleZkLockAnnotation(bean, method, zkLockAnnotation);
			}
		}
		return bean;
	}

	private Object handleZkLockAnnotation(Object bean, Method method, ZkLock zkLockAnnotation) {
		String syncObj = zkLockAnnotation.value();
		long waitTime = zkLockAnnotation.waitTime();
		ZkLockProvider provider = new ZkLockProvider();
		return provider.createProxyInstance(bean, syncObj, waitTime);
	}
}
