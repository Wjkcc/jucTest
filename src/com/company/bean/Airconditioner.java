package com.company.bean;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: wjk
 * @date: 2020/6/29 17:04
 **/
public class Airconditioner {
    private int temperature = 0;
    private java.util.concurrent.locks.Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

//    public synchronized void increment() throws InterruptedException {
//        // 判断温度
//        while (temperature != 0) {
//            // 持有当前线程的对象等待
//            this.wait();
//        }
//        // 操作
//        temperature++;
//        System.out.println(Thread.currentThread().getName()+" ,,,"+temperature);
//        // 通知其他线程操作
//        this.notify();
//    }
//
//    public synchronized void decrement() throws InterruptedException {
//        // 判断温度
//        while (temperature == 0) {
//            // 持有当前线程的对象等待
//            this.wait();
//        }
//        // 操作
//        temperature--;
//        System.out.println(Thread.currentThread().getName()+" ,,,"+temperature);
//        // 通知其他线程操作
//        this.notify();
//    }

    public  void increment() throws InterruptedException {
        lock.lock();
        try{
            // 判断温度
            while (temperature != 0) {
                // 持有当前线程的对象等待
                condition.await();
            }
            // 操作
            temperature++;
            System.out.println(Thread.currentThread().getName()+" ,,,"+temperature);
            // 通知其他线程操作
            condition.signalAll();
        }catch(Exception e){
          e.printStackTrace();
        }
        finally{
        lock.unlock();
        }
    }

    public  void decrement() throws InterruptedException {
        lock.lock();
        try{
            // 判断温度
            while (temperature == 0) {
                // 持有当前线程的对象等待
                condition.await();
            }
            // 操作
            temperature--;
            System.out.println(Thread.currentThread().getName()+" ,,,"+temperature);
            // 通知其他线程操作
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }
}
