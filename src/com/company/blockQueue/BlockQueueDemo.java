package com.company.blockQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/15 14:26
 **/
public class BlockQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> bq = new LinkedBlockingDeque<>(10);
        for (int i=0;i<1;i++) {
            new Thread(new ProduceTask(bq)).start();
        }
        for (int i=0;i<1;i++) {
            new Thread(new ConsumerTsk(bq)).start();
        }
    }
    public static void produce(String str) {

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,5,60L, TimeUnit.SECONDS,)
    }
}
