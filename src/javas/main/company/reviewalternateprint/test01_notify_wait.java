package javas.main.company.reviewalternateprint;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/16 10:31
 **/
public class test01_notify_wait {
    static int index = 0;
    static char[] chars = null;
    static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        String s = "1A2B3C4D5E6F7G8H9I";
        chars = s.toCharArray();
//        保证线程1先执行
        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(()->{
            countDownLatch.countDown();
            synchronized (lock) {
                while (index < s.length()) {
                    System.out.println("t1 print:" + chars[index]);
                    index++;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }).start();

        new Thread(()->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                while (index < s.length()) {
                    System.out.println("t2 print:" + chars[index]);
                    index++;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }).start();

    }
}
