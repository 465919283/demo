package com.primer.demo.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.FutureTask;

public class CacheTask {  
    private static final ConcurrentMap<String, FutureTask<Object>> cache = new ConcurrentHashMap<String, FutureTask<Object>>();  
    public static Object getInTask(String cacheKey, Callable<Object> caller) {  
        System.out.println("1.缓存未命中，将查询数据库或者调用远程服务");  
        //未命中缓存，开始计算  
        FutureTask<Object> f = cache.get(cacheKey);  
        if (f == null) {  
            FutureTask<Object> ft = new FutureTask<Object>(caller);  
            f = cache.putIfAbsent(cacheKey, ft);  
            if (f == null) {  
                System.out.println("2.任务未命中，将查询数据库或者调用远程服务");  
                f = ft;  
                ft.run();  
            }  
        }  
        else {  
            System.out.println("2.任务命中,直接从缓存取结果");  
        }  
        try {  
            Object result = f.get();  
            System.out.println("取回的结果result:"+result);  
            return result;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        finally{  
            //最后将计算任务去掉,虽然已经移除任务对象，但其他线程  
            //仍然能够获取到计算的结果，直到所有引用都失效，被垃圾回收掉  
            boolean success = cache.remove(cacheKey,f);  
            System.out.println(success);  
        }  
        return null;  
    }  
}  
