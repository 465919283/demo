package com.primer.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Wesley
 * @create 2020/2/20
 */
public class BlockingQueue {
    private List<Object> list = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public void put(Object o){
        try{
            lock.lock();
            while (list.size() > 5){
                System.out.println("put wait....................................");
                notFull.await();
            }
            list.add(o);
            notEmpty.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public Object take(){
        Object result = null;
        try{
            lock.lock();
            while (list.size()<=0){
                System.out.println("take wait ........................");
                notEmpty.await();
            }
            result = list.get(0);
            list.remove(0);
            notFull.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return result;
    }

    public static void main(String[] args) {
        BlockingQueue blockingQueue = new BlockingQueue();
        new Thread(()->{
            for (int i=0;i<40;i++){
                blockingQueue.put(i);
                try{
                    Thread.sleep((int)Math.random()*1000);
                }catch (Exception e){

                }
            }
        }).start();
        new Thread(()->{
            for (int i=0;i<40;i++){
                System.out.println(blockingQueue.take());;
                try{
                    Thread.sleep((int)Math.random()*500);
                }catch (Exception e){

                }
            }
        }).start();
    }
}
