package com.company.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: wjk
 * @date: 2020/7/2 11:03
 **/

public class CallableTest {
    static class T1 implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("hello world");
            TimeUnit.SECONDS.sleep(4);
            return "end";
        }

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        T1 t = new T1();
        FutureTask<String> f = new FutureTask<>(t);
        new Thread(f,"a").start();
        new Thread(f,"b").start();
        System.out.println("=====");

        System.out.println(f.get());


    }
}
