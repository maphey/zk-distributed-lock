package io.maphey.lock.zk.client;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.junit.Assert;
import org.junit.Test;

public class CuratorsTest {

	@Test
	public void testGetClient() {
		CuratorFramework curatorsClient = Curators.getClient();
		try {
			String s = curatorsClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/test");
			Assert.assertEquals(s, "/test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
