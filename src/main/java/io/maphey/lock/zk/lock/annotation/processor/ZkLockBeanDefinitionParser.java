package io.maphey.lock.zk.lock.annotation.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ZkLockBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
	private static final Logger LOGGER = LoggerFactory.getLogger(ZkLockBeanDefinitionParser.class);
	static final String ZK_LOCK_ANNOTATION = "ZK_LOCK_ANNOTATION";
	static final String ZK_LOCK_ID = "ZK_LOCK_ID";

	private volatile boolean init = false;

	@Override
	protected Class<?> getBeanClass(Element element) {
		return ZkLockProvider.class;
	}

	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		if (init) {
			throw new RuntimeException("一个应用中只允许配置一个<dlock:annotation-config>");
		}
		builder.setInitMethodName("init");
		builder.setDestroyMethodName("destroy");
		if (!parserContext.getRegistry().containsBeanDefinition(ZK_LOCK_ANNOTATION)) {
			RootBeanDefinition annotation = new RootBeanDefinition(AnnotationBean.class);
			parserContext.getRegistry().registerBeanDefinition(ZK_LOCK_ANNOTATION, annotation);
		}
		init = true;
	}

	@Override
	protected String resolveId(Element element, AbstractBeanDefinition definition, ParserContext parserContext)
			throws BeanDefinitionStoreException {
		return ZK_LOCK_ID;
	}

}
