package javas.main.company.reviewalternateprint;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/16 11:54
 **/
public class test04_synctest {
    public static void main(String[] args) {
//        BlockingQueue<String> queue = new SynchronousQueue<>();
//        new Thread(()->{
//            try {
//                queue.take();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
        Long i = new Long(2);
        Long j = new Long(2);
        System.out.println(i==j);

    }
}
