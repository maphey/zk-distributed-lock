package io.maphey.lock.zk.lock.annotation.processor;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class ZkLockNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("config", new ZkLockBeanDefinitionParser());
	}

}
