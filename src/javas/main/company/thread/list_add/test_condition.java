package javas.main.company.thread.list_add;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/12 13:54
 **/
public class test_condition {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
//        new Thread(()->{
//                lock.lock();
//                System.out.println(1111);
//                try {
//                    System.out.println("1 block");
//                    condition.await();
//                    System.out.println("22");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("结束");
//        }).start();
//        TimeUnit.SECONDS.sleep(1);
//        new Thread(()->{
//            lock.lock();
//            System.out.println("2 get lock");
//            condition.signal();
//            lock.unlock();
//        }).start();
            new Thread(()->{
                lock.lock();
                System.out.println(222);
            }).start();
            new Thread(()->{
                System.out.println(333);
            }).start();
    }

}
