package com.company.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/14 14:41
 **/
public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0;i < 15; i++) {
            int finalI = i;
            executorService.execute(new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" is Running with ["+ finalI+"]");
            },"Thread-"+i));
        }
        executorService.shutdown();
    }
}
