package io.maphey.lock.zk.lock;

import static org.junit.Assert.fail;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.junit.Test;

public class LocksTest {

	@Test
	public void testGetInterProcessMutex() {
		InterProcessMutex lock = Locks.getInterProcessMutex("/interprocessmutex");
		try {
			lock.acquire();
			new Thread(() -> {
				InterProcessMutex lock1 = Locks.getInterProcessMutex("/interprocessmutex");
				try {
					lock1.acquire();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						lock1.release();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				lock.release();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testGetInterProcessSemaphoreMutex() {
		InterProcessSemaphoreMutex lock = Locks.getInterProcessSemaphoreMutex("/semaphoremutex");
		try {
			lock.acquire();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				lock.release();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testGetInterProcessReadWriteLock() {
		InterProcessReadWriteLock lock = Locks.getInterProcessReadWriteLock("/semaphoremutex");
		InterProcessMutex readLock = lock.readLock();
		try {
			readLock.acquire();
			new Thread(() -> {
				InterProcessReadWriteLock lock1 = Locks.getInterProcessReadWriteLock("/semaphoremutex");
				InterProcessMutex readLock1 = lock1.readLock();
				try {
					readLock1.acquire();
					System.out.println("获取读锁成功");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						readLock1.release();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				readLock.release();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testGetInterProcessSemaphoreV2() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInterProcessMultiLock() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDistributedBarrier() {
		fail("Not yet implemented");
	}

}
