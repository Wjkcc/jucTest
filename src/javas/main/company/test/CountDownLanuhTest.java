package javas.main.company.test;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: wjk
 * @date: 2020/7/2 14:35
 **/
public class CountDownLanuhTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i=0; i<6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+".....");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("end" +
                "222");
    }
}
