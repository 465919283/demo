/**
 *
 */
package com.primer.demo.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


public abstract class EHCacheUtil {

    private static Logger logger = LoggerFactory.getLogger(EHCacheUtil.class);
    private static final ReentrantLock lock = new ReentrantLock();

    public static final String EHCACHE_DEFAULT_CACHE = "oneMCache";// default
    // cache
    // name
    public static final String EHCACHE_ONEM_CACHE = "oneMCache";
    public static CacheManager manager;

    /**
     * 使用默认配制(/ehcache.xml)生成CacheManager
     *
     * @return
     */
    public static CacheManager getCacheManager() {
        if (manager == null) {
            lock.lock(); // block until condition holds
            try {
                if (manager == null) {
                    manager = CacheManager.create();
                }
            } finally {
                lock.unlock();
            }
        }
        return manager;
    }

    /**
     * 获取默认Cache,defaultCache为默认Cache名称
     *
     * @return
     */
    public static Cache getCache() {
        return getCache(EHCACHE_DEFAULT_CACHE);
    }

    /**
     * 根据cacheName获取Cache
     *
     * @param cacheName
     * @return
     */
    public static Cache getCache(String cacheName) {
        if (null == manager) {
            manager = getCacheManager();
        }
        return manager.getCache(cacheName);
    }

    /**
     * 使用默认Cache缓存对象
     *
     * @param key
     * @param value
     */
    public static void put(String key, Object value) {
        put(EHCACHE_DEFAULT_CACHE, key, value);
    }

    /**
     * 使用指定Cache缓存对象
     *
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, String key, Object value) throws RuntimeException {
        if (StringUtils.isBlank(key)) {
            logger.error("EHCache put error ! key is required !");
            throw new RuntimeException("ERROR_EHCACHE");
        }

        if (StringUtils.isBlank(cacheName)) {
            logger.error("EHCache put error ! cacheName is required !");
            throw new RuntimeException("ERROR_EHCACHE");
        }

        if (null == value) {
            logger.error("EHCache put error ! value required");
            return;
        }
        Cache cache = getCache(cacheName);
        if (null == cache) {
            logger.error("EHCache put error ! cache is null ");
            throw new RuntimeException("ERROR_EHCACHE");
        }
        Element element = new Element(key, value);
        cache.put(element);
    }

    /**
     * 从默认缓存中根据Key查询被缓存的对象
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return get(EHCACHE_DEFAULT_CACHE, key);
    }

    /**
     * 从指定缓存中根据Key查询被缓存的对象
     *
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, String key) {
        if (StringUtils.isBlank(key)) {
            logger.error("EHCache get error ! key is required !");
            return null;
        }

        if (StringUtils.isBlank(cacheName)) {
            logger.error("EHCache get error ! cacheName is required !");
            return null;
        }

        Cache cache = getCache(cacheName);
        if (null == cache) {
            logger.error("EHCache get error ! cache is null ");
            return null;
        }
        Element element = cache.get(key);
        if (element != null) {
            logger.info("EHCache hit ok!");
        } else {
            logger.info("EHCache hit fail!");
        }
        return null == element ? null : element.getObjectValue();
    }

    /**
     * 从默认缓存中删除已经缓存的对象
     *
     * @param key
     * @return
     */
    public static boolean remove(String key) {
        return remove(null, key);
    }

    /**
     * 从指定缓存中删除已经缓存的对象
     *
     * @param cacheName
     * @param key
     * @return
     */
    public static boolean remove(String cacheName, String key) {
        if (StringUtils.isBlank(key)) {
            logger.error("EHCache remove error ! key is required !");
            return false;
        }

        if (StringUtils.isBlank(cacheName)) {
            logger.error("EHCache remove error ! cacheName is required !");
            return false;
        }

        Cache cache = getCache(cacheName);
        if (null == cache) {
            logger.error("CHCache remove error !  cache is null");
            return false;
        }
        return cache.remove(key);
    }

    /**
     * 生成Key
     *
     * @param type
     * @param clazz
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String fromCacheKey(Class type, Class clazz) {
        return fromCacheKey(type.getName(), clazz.getName());
    }

    /**
     * 生成Key
     *
     * @param type
     * @param id
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String fromCacheKey(Class type, String id) {
        return fromCacheKey(type.getName(), id);
    }

    /**
     * 生成Key
     *
     * @param type
     * @param id
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String fromCacheKey(Class type, long id) {
        return fromCacheKey(type.getName(), "" + id);
    }

    /**
     * 生成Key
     *
     * @param type
     * @param id
     * @return
     */
    public static String fromCacheKey(String type, long id) {
        return fromCacheKey(type, "" + id);
    }

    /**
     * 生成Key
     *
     * @param type
     * @param id
     * @return
     */
    public static String fromCacheKey(String type, String id) {
        String cacheKey = StringUtils.isEmpty(id) ? type : (type + "." + id);
        if (logger.isDebugEnabled())
            logger.debug("***cacheKey=" + cacheKey);
        return DigestUtils.md5Hex(cacheKey);
    }

    /**
     * 生成Key
     *
     * @return
     */
    public static String fromCacheKey(Map<String, Object> params) {
        String cacheKey = params == null ? new HashMap<String, Object>().toString() : params.toString();
        if (logger.isDebugEnabled())
            logger.debug("***cacheKey=" + cacheKey);
        return DigestUtils.md5Hex(cacheKey);
    }

    public static void main(String args[]) throws Exception {
        put(fromCacheKey(Object.class, Integer.class), "++++++++++++++++++++++++++++++++++++++++++test");
        System.out.println(get(fromCacheKey(Object.class, Integer.class)));
        Thread.sleep(1000);
        System.out.println(get(fromCacheKey(Object.class, Integer.class)));

    }
}
