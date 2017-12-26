package io.maphey.lock.zk.config;

import org.junit.Assert;
import org.junit.Test;

public class ConfigLoaderTest {

	@Test
	public void test() {
		ConfigMetaData configMeta = ConfigLoader.getConfigMetaData();
		Assert.assertEquals(configMeta.getZkServer(), "localhost:2181");
		Assert.assertEquals(configMeta.getRetryCount(), 2);
		Assert.assertEquals(configMeta.getSessionTimeout(), 60000);
		Assert.assertEquals(configMeta.getConnectionTimeout(), 1000);
	}

}
