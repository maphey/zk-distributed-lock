package io.maphey.lock.zk.client;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import io.maphey.lock.zk.config.ConfigLoader;
import io.maphey.lock.zk.config.ConfigMetaData;

public class Curators {
	private static final CuratorFramework CLIENT;

	static {
		ConfigMetaData configMeta = ConfigLoader.getConfigMetaData();
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(configMeta.getBaseSleep(), configMeta.getRetryCount());
		CLIENT = CuratorFrameworkFactory.builder().connectString(configMeta.getZkServer()).connectionTimeoutMs(configMeta.getConnectionTimeout())
				.sessionTimeoutMs(configMeta.getSessionTimeout()).retryPolicy(retryPolicy).namespace("appid").build();
		CLIENT.start();
	}

	public static CuratorFramework getClient() {
		return CLIENT;
	}

}
