package io.maphey.lock.zk.config;

public class ConfigMetaData {
	private String zkServer;
	private int retryCount = 3;
	private int baseSleep = 500;
	private int connectionTimeout = 1000;
	private int sessionTimeout = 60000;

	public String getZkServer() {
		return zkServer;
	}

	public void setZkServer(String zkServer) {
		this.zkServer = zkServer;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public int getBaseSleep() {
		return baseSleep;
	}

	public void setBaseSleep(int baseSleep) {
		this.baseSleep = baseSleep;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(int sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

}
