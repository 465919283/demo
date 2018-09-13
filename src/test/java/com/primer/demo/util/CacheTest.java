package com.primer.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import net.sf.ehcache.Cache;

public class CacheTest {
	private Cache cache;

	public CacheTest() {
		this.cache = EHCacheUtil.getCacheManager().getCache(EHCacheUtil.EHCACHE_ONEM_CACHE);
	}

	public List<Long> concurrentService(int para1, int param2) {
		long beginTime = System.nanoTime();
		final String cacheKey = "IamKey";
		List<Long> list = (List<Long>) EHCacheUtil.get(cacheKey);
		if (list == null) {
			Callable<Object> caller = new Callable<Object>() {
				public Object call() throws InterruptedException {
					System.out.println(" go to dao or rmi");
					List<Long> list = new ArrayList<Long>();
					list.add(1l);
					list.add(2l);
					// 将计算结果缓存
					System.out.println("结果计算完毕，存入分布式缓存中");
					EHCacheUtil.put(cacheKey, list);
					Thread.sleep(500);
					// 计算结果，通常是访问数据库或者远程服务
					return list;
				}
			};
			List<Long> result = (List<Long>) CacheTask.getInTask(cacheKey, caller);
			long end = System.nanoTime();
			// useTimes.add(end-beginTime);
			return result;
		} else {
			System.out.println("1.缓存命中，直接返回");
			long end = System.nanoTime();
			// useTimes.add(end-beginTime);
			return list;
		}
	}

	public static void main(String[] args) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 50; i++) {
					new CacheTest().concurrentService(1, 1);
				}
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 50; i++) {
					new CacheTest().concurrentService(1, 1);
				}
			}
		}).start();
	}
}
