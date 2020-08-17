package com.company;

import com.company.bean.Airconditioner;

/**
 * @description:
 * @author: wjk
 * @date: 2020/6/29 17:03
 **/
public class Consumer {
    public static void main(String[] args) {
        Airconditioner airconditioner = new Airconditioner();
        new Thread(()->{
            for(int i=0;i<10;i++) {
                try {
                    airconditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"a").start();

       new Thread(()->{
        for(int i=0;i<10;i++) {
            try {
                airconditioner.decrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    },"b").start();
        new Thread(()->{
            for(int i=0;i<10;i++) {
                try {
                    airconditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"c").start();

        new Thread(()->{
            for(int i=0;i<10;i++) {
                try {
                    airconditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"d").start();
}
}
