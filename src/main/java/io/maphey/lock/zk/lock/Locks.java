package io.maphey.lock.zk.lock;

import java.util.List;

import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;

import io.maphey.lock.zk.client.Curators;

public class Locks {
	public synchronized InterProcessMutex getInterProcessMutex(String path) {
		return new InterProcessMutex(Curators.getClient(), path);
	}

	public synchronized InterProcessSemaphoreMutex getInterProcessSemaphoreMutex(String path) {
		return new InterProcessSemaphoreMutex(Curators.getClient(), path);
	}

	public synchronized InterProcessReadWriteLock getInterProcessReadWriteLock(String path) {
		return new InterProcessReadWriteLock(Curators.getClient(), path);
	}

	public synchronized InterProcessSemaphoreV2 getInterProcessSemaphoreV2(String path, int numberOfLeases) {
		return new InterProcessSemaphoreV2(Curators.getClient(), path, numberOfLeases);
	}

	public synchronized InterProcessMultiLock getInterProcessMultiLock(List<String> paths) {
		return new InterProcessMultiLock(Curators.getClient(), paths);
	}

	public synchronized DistributedBarrier getDistributedBarrier(String barrierPath) {
		return new DistributedBarrier(Curators.getClient(), barrierPath);
	}
}
