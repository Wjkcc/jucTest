package javas.main.company.reviewalternateprint;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/16 11:01
 **/
public class test03_reentrantlock {
    static int index = 0;
    static char[] chars = null;
    public static void main(String[] args) {
        String s = "1A2B3C4D5E6F7G8H9I";
        chars = s.toCharArray();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        CountDownLatch countDownLatch = new CountDownLatch(1);

           new Thread(()->{
                countDownLatch.countDown();
                try{
                    lock.lock();
                    while (index < s.length()) {
                        System.out.println("t1 print:" + chars[index++]);
                        TimeUnit.SECONDS.sleep(1);
                        condition.signal();
                        condition.await();
                    }
                    condition.signal();
                }catch(Exception e){
                  e.printStackTrace();
                }
                finally{
                lock.unlock();
                }
           }).start();

        new Thread(()->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try{
                lock.lock();
                while (index < s.length()) {
                    System.out.println("t2 print:" + chars[index++]);
                    TimeUnit.SECONDS.sleep(1);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            }catch(Exception e){
                e.printStackTrace();
            }
            finally{
                lock.unlock();
            }
        }).start();

    }
}
