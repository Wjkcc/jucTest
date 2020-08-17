package com.company.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/14 14:53
 **/
public class SingleThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i=0;i<10;i++) {
            executorService.execute(new Task());
        }
        executorService.shutdown();
    }
}
