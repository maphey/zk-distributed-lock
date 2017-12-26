package io.maphey.lock.zk.config;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigLoader {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigLoader.class);

	private static final ConfigMetaData CONFIG_META_DATA;

	static {
		Properties prop = new Properties();
		try (InputStream in = ClassLoader.getSystemResourceAsStream("zk-lock.properties")) {
			prop.load(in);
		} catch (Exception e) {
			LOGGER.error("load zk config error", e);
		}
		String zkRetryProp = prop.getProperty("zk.retry.count", "3");
		int zkRetry = 3;
		try {
			zkRetry = Integer.parseInt(zkRetryProp);
		} catch (NumberFormatException e) {
			LOGGER.error("parse retry number error", e);
		}
		String baseSleepProp = prop.getProperty("zk.base.sleep", "500");
		int baseSleep = 500;
		try {
			baseSleep = Integer.parseInt(baseSleepProp);
		} catch (NumberFormatException e) {
			LOGGER.error("parse base sleep number error", e);
		}
		String sessionTimeoutProp = prop.getProperty("zk.session.timeout", "1000");
		int sessionTimeout = 3;
		try {
			sessionTimeout = Integer.parseInt(sessionTimeoutProp);
		} catch (NumberFormatException e) {
			LOGGER.error("parse session timeout number error", e);
		}
		String connectionTimeoutProp = prop.getProperty("zk.connection.timeout", "1000");
		int connectionTimeout = 3;
		try {
			connectionTimeout = Integer.parseInt(connectionTimeoutProp);
		} catch (NumberFormatException e) {
			LOGGER.error("parse connection timeout number error", e);
		}
		String zkServer = prop.getProperty("zk.server", "localhost:2181");
		CONFIG_META_DATA = new ConfigMetaData();
		CONFIG_META_DATA.setZkServer(zkServer);
		CONFIG_META_DATA.setRetryCount(zkRetry);
		CONFIG_META_DATA.setBaseSleep(baseSleep);
		CONFIG_META_DATA.setSessionTimeout(sessionTimeout);
		CONFIG_META_DATA.setConnectionTimeout(connectionTimeout);
	}

	public static ConfigMetaData getConfigMetaData() {
		return CONFIG_META_DATA;
	}
}
