package io.maphey.lock.zk.client;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import io.maphey.lock.zk.config.ConfigLoader;
import io.maphey.lock.zk.config.ConfigMetaData;

public class Curators {
	private static CuratorFramework client;

	static {
		createClient();
	}

	public static CuratorFramework getClient() {
		if (client != null) {
			return client;
		}
		createClient();
		return client;
	}

	private static synchronized void createClient() {
		ConfigMetaData configMeta = ConfigLoader.getConfigMetaData();
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(configMeta.getBaseSleep(), configMeta.getRetryCount());
		CuratorFramework tmpClient = CuratorFrameworkFactory.builder().connectString(configMeta.getZkServer()).connectionTimeoutMs(configMeta.getConnectionTimeout())
				.sessionTimeoutMs(configMeta.getSessionTimeout()).retryPolicy(retryPolicy).namespace("appid").build();
		tmpClient.start();
		client = tmpClient;
	}
}
