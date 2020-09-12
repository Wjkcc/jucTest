package javas.main.company.thread.list_add;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/12 10:58
 **/
public class ThreadPrint {
    static volatile int modcount = 1;
    public static void main(String[] args) {
        v3();
    }
    public static void v1() throws InterruptedException {
        System.out.println(1);
        Object lock1 = new Object();
        new Thread(()->{
            int i = 1;
            while(true) {
                synchronized (lock1) {
                    if (modcount == 10) {
                        System.out.println("t1 mod =" +modcount);
                        lock1.notify();
                        break;
                    }
                    System.out.println("t1 print "+i);
                    i = i+2;
                    modcount++;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock1.notify();
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("t1 end");
        }).start();
        Thread.currentThread().sleep(1000);
        new Thread(()->{
            int i = 2;
            while(true) {
                synchronized (lock1) {
                    if (modcount == 10) {
                        System.out.println("t2 mod =" +modcount);
                        lock1.notify();
                        break;
                    }
                    System.out.println("t2 print " + i);
                    i = i + 2;
                    modcount++;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock1.notify();
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("t2 end");
        }).start();
    }
    public static void v2() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(()->{
            int i = 1;
            while(true) {
                try {
                    lock.lock();
                    if (modcount == 10) {
                        condition.signal();
                        System.out.println("t1 mod = " + modcount);
                        break;
                    }
                    System.out.println("t1 print " + i);
                    i=i+2;
                    modcount++;
                    condition.signal();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }  finally {
                    lock.unlock();
                }
            }
        }).start();
        new Thread(()->{
            int i = 2;
            while(true) {
                try {
                    lock.lock();
                    if (modcount == 10) {
                        condition.signal();
                        System.out.println("t2 mod = " + modcount);
                        break;
                    }
                    System.out.println("t2 print " + i);
                    i = i + 2;
                    modcount++;
                    condition.signal();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
    public static void v3() {
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        new Thread(()->{
            int i = 1;
            while(true) {
                try {
                    lock.lock();
                    if (modcount == 10) {
                        condition2.signal();
                        System.out.println("t1 mod = " + modcount);
                        break;
                    }
                    System.out.println("t1 print " + i);
                    i=i+2;
                    modcount++;
                    condition2.signal();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }  finally {
                    lock.unlock();
                }
            }
        }).start();
        new Thread(()->{
            int i = 2;
            while(true) {
                try {
                    lock.lock();
                    if (modcount == 10) {
                        condition1.signal();
                        System.out.println("t2 mod = " + modcount);
                        break;
                    }
                    System.out.println("t2 print " + i);
                    i = i + 2;
                    modcount++;
                    condition1.signal();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}
