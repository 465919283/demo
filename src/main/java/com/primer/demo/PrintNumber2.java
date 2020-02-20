package com.primer.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Wesley
 * @create 2020/2/20
 */
public class PrintNumber2 {
    private static volatile int count = 0;
    private static Lock lock = new ReentrantLock();
    private static Condition a = lock.newCondition();
    private static Condition b = lock.newCondition();
    private static Condition c = lock.newCondition();

    static class ThreadA implements Runnable{
        @Override
        public void run() {
            try{
                lock.lock();
                while (count < 75){
                    while (count % 3 != 0){
                        a.await();
                    }
                    if (count > 75){
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + " " + count);
                    count +=1;
                    b.signal();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
    static class ThreadB implements Runnable{
        @Override
        public void run() {
            try{
                lock.lock();
                while (count < 75){
                    while (count % 3 != 1){
                        b.await();
                    }
                    if (count > 75){
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + " " + count);
                    count +=1;
                    c.signal();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
    static class ThreadC implements Runnable{
        @Override
        public void run() {
            try{
                lock.lock();
                while (count < 75){
                    while (count % 3 != 2){
                        c.await();
                    }
                    if (count > 75){
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + " " + count);
                    count +=1;
                    a.signal();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
        new Thread(new ThreadC()).start();
    }
}
