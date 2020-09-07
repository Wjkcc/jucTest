package javas.main.company.thread.list_add;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/7 11:39
 **/
public class test03_CountDownLaTCH {
    public static void main(String[] args) {
        List<Object> l = new LinkedList<>();
        CountDownLatch c1 = new CountDownLatch(1);
        CountDownLatch c2 = new CountDownLatch(1);
        Thread t2 = new Thread(()->{
            try {
                // t2先阻塞
                c1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2结束");
            // t1运行
            c2.countDown();
        });
        t2.start();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i< 10 ;i++) {
                l.add(new Object());
                System.out.println(i);
                if (l.size() == 5) {
//                    t2运行
                    c1.countDown();
                    try {
//                        t1阻塞
                        c2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });
        t1.start();
        System.out.println("main");
    }
}
