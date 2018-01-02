package io.maphey.lock.zk.lock.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-context.xml" })
public class ZkLockTest {

	@Test
	public void test() {
		testLock();
	}

	@ZkLock("test11")
	public void testLock() {

	}
}
