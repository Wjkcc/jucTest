package javas.main.company.thread.list_add;

import lombok.Synchronized;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/7 11:44
 **/
public class test02_lsynchronized_with_wait_notify {
    public static void main(String[] args) {
        Object lock = new Object();
        List<Object> l = new LinkedList<>();
        System.out.println("main");
        Thread t2 = new Thread(()->{

            synchronized (lock) {
                try {
                    // 线程等待释放锁，等待被唤醒
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2输出");
                System.out.println("t2结束");
                // 唤醒其他等待线程，程序结束后释放锁
                lock.notify();
             }
        });
        t2.start();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i< 10 ;i++) {
                synchronized (lock) {
                    l.add(new Object());
                    System.out.println(i);
                    if (l.size() == 5) {
                        // 唤醒其他线程，不释放锁
                        lock.notify();
                        try {
                            // 线程等待释放锁
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t1.start();
    }
}
