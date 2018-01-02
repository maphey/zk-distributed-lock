package io.maphey.lock.zk.lock.annotation.processor;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.util.Assert;

import io.maphey.lock.zk.lock.Locks;

public class ZkLockProvider implements MethodInterceptor {
	private Object target;
	private String syncObj;
	private long waitTime;

	public void init() {

	}

	public void destroy() {

	}

	@Override
	public Object intercept(Object bean, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		InterProcessMutex lock = Locks.getInterProcessMutex(syncObj);
		try {
			boolean lockSuccess = lock.acquire(waitTime, TimeUnit.MILLISECONDS);
			if (!lockSuccess) {
				throw new IllegalStateException("zklock加锁失败");
			}
			return methodProxy.invoke(this.target, args);
		} finally {
			lock.release();
		}
	}

	public <T> T createProxyInstance(Object target, String syncObj, long waitTime) {
		Assert.notNull(target, "使用zklock的对象不能为null");
		Assert.notNull(syncObj, "zklock同步对象不能为null");
		Assert.isTrue(waitTime > 0, "zklock等待的同步时间必须大于0");
		this.target = target;
		this.syncObj = syncObj;
		this.waitTime = waitTime;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		enhancer.setCallback(this);
		return (T) enhancer.create();
	}

}
