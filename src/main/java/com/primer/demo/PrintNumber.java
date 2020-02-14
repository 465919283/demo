package com.primer.demo;

/**
 * @author Wesley
 * @create 2020/2/14
 */
public class PrintNumber {
    static class Wrapper {
        private int count;
        public Integer getCount() {
            return count;
        }
        public void setCount(Integer count) {
            this.count = count;
        }
    }
    static class PrintOdd implements Runnable{
        private Wrapper wrapper;
        public PrintOdd(Wrapper wrapper){
            this.wrapper = wrapper;
        }

        @Override
        public void run() {
            try {
                synchronized (wrapper){
                    while (wrapper.getCount() <= 100){
                        if (wrapper.getCount() % 2 == 0){
                            wrapper.wait();
                        }else {
                            System.out.println(Thread.currentThread().getName() + "PrintOdd" + wrapper.getCount());
                            wrapper.setCount(wrapper.getCount() + 1);
                            wrapper.notifyAll();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class PrintEven implements Runnable{
        private Wrapper wrapper;
        public PrintEven(Wrapper wrapper){
            this.wrapper = wrapper;
        }

        @Override
        public void run() {
            try {
                synchronized (wrapper){
                    while (wrapper.getCount() <= 100){
                        if (wrapper.getCount() % 2 == 1){
                            wrapper.wait();
                        }else {
                            System.out.println(Thread.currentThread().getName() + "PrintEven" + wrapper.getCount());
                            wrapper.setCount(wrapper.getCount() + 1);
                            wrapper.notifyAll();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Wrapper wrapper = new Wrapper();
        PrintOdd odd = new PrintOdd(wrapper);
        PrintEven even = new PrintEven(wrapper);
        new Thread(odd).start();
        new Thread(even).start();
    }
}
