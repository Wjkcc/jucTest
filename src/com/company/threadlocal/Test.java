package com.company.threadlocal;

import java.io.IOException;
import java.lang.ref.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: wjk
 * @date: 2020/7/20 14:14
 **/
public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
//        M m = new M();
//        m = null;
//
//        System.gc(); // 垃圾回收
//
//        // System.in.read(); //阻塞mian线程，给垃圾回收线程时间执行
//        Thread.sleep(1000);
//        SoftReference<byte[]> sf = new SoftReference<>(new byte[1024]);
//        System.out.println(sf.get());
        /**
         * 虚引用
         * 直接拿不到
         */
        WeakReference<M> wr = new WeakReference<>(new M());
        List<Object> l = new LinkedList<>();
        ReferenceQueue<M> queue = new ReferenceQueue<>();
        PhantomReference<M> pf = new PhantomReference<>(new M(),queue);
        System.out.println(pf.get());

        new Thread(()->{
            while(true) {
                l.add(new byte[1024*1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupted();
                }
                System.out.println(pf.get());
            }
        }).start();

        new Thread(()->{
            while(true){
                Reference<? extends M> r = queue.poll();
                if (null != r) {
                    System.out.println("正在被回收" + r);
                }
            }

        }).start();

        Thread.sleep(500);
    }
}
