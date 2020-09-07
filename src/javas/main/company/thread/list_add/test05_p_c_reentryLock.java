package javas.main.company.thread.list_add;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/7 15:13
 **/
public class test05_p_c_reentryLock {
    private static LinkedList<String> l = new LinkedList<>();
    static int max = 10 ;
    static int count = 0;
    static int modCount = 0;
    static Lock lock = new ReentrantLock();
    // 消费者等待队列
    static Condition consumer = lock.newCondition();
    // 生产者等待队列
    static Condition producer = lock.newCondition();
    public static void main(String[] args)  {

        // 消费者线程
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                    while (true) {
                        try {
                            lock.lock();
                            // 如果队列为空，就等待，释放锁
                            while (count == 0) {
                                consumer.await();
                            }
                            while (checkMod()) {
                                String s = l.removeFirst();
                                count--;
                                modCount++;
                                System.out.println("consumer:" + Thread.currentThread().getName() + "is consume :" + s);
                                // 消费完就 通知生产者生产
                                producer.signalAll();
                            }
                            break;
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        } finally {
                            lock.unlock();
                        }
                    }
                System.out.println(modCount);
                System.out.println("consumer:"+Thread.currentThread().getName()+" is over");
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(true) {
                    try {
                        lock.lock();
                        while (count == max) {
                            producer.await();
                        }
                        while (checkMod()) {
                            String add = new Random().nextInt(1000) + "";
                            l.add(add);
                            count++;
                            modCount++;
                            System.out.println("producer:" + Thread.currentThread().getName() + " is produce:" + add);
                            consumer.signalAll();
                        }
                            break;


                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
                System.out.println("producer:"+Thread.currentThread().getName()+"is over");
            }).start();
        }
        // 生产者线程


    }
    private static boolean checkMod() {
        if(modCount >=50) {
            consumer.signalAll();
            producer.signalAll();
            return false;
    }
        return true;
    }
}
