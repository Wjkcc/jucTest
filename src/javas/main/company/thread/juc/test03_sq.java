package javas.main.company.thread.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/15 17:11
 **/
public class test03_sq {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new SynchronousQueue<>();
//        new Thread(()->{
//            String take = null;
//            try {
//                take = queue.take();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(take);
//        }).start();
//        queue.put("22");
        try {
            queue.add("22");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(queue.size());
    }
}
