package javas.main.company.reviewalternateprint;

import java.sql.Time;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/16 11:24
 **/
public class test04_blockingqueue {
    static char[] chars = null;
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new SynchronousQueue<>();

        String s = "1A2B3C4D5E6F7G8H9I";
        char[] chars = s.toCharArray();
        new Thread(()->{
            int index = 0;
            while(index < chars.length) {
                System.out.println("t1 print" + chars[index++]);
                try {
                    blockingQueue.put(index++);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            Integer i = 0;
            try {
               while((i = blockingQueue.take()) < chars.length) {
                   System.out.println("t2 print" + chars[i]);

               }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
