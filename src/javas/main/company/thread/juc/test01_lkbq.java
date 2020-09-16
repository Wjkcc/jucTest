package javas.main.company.thread.juc;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/15 13:48
 **/
public class test01_lkbq {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    queue.put("s"+i);
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                while (true) {
                    try {
                        String s = queue.take();
                        System.out.println(Thread.currentThread().getName()+"  get " + s);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"thread_"+i).start();
        }

    }
}
