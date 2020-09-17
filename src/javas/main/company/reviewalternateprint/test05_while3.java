package javas.main.company.reviewalternateprint;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 自旋操作线程顺序打印
 * @author: wjk
 * @date: 2020/9/17 10:22
 **/
public class test05_while3 {
    static volatile int flag = 0; // 0 线程1 1 线程2
    static char[] chars = null;
    static volatile int index = 0;
    public static void main(String[] args) {
        String s = "1A2B3C4D5E6F7G8H9I";
        CountDownLatch countDownLatch = new CountDownLatch(1); // 保证线程1先执行
//        String s = "1A";
        chars = s.toCharArray();
        new Thread(()->{
//            countDownLatch.countDown();
            for (int i = 0; i < chars.length ; i=i+2) {
                while(flag == 1){}
                    System.out.println("t1 print " +chars[i]);
                    flag = 1;
            }
        }).start();
        new Thread(()->{
//            try {
//                countDownLatch.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            for (int i = 1; i < chars.length ; i=i+2) {
                while(flag == 0){}
                System.out.println("t2 print " +chars[i]);
                flag = 0;
            }

        }).start();
    }
}
