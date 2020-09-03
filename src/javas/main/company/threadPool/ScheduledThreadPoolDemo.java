package javas.main.company.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/14 15:08
 **/
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        System.out.println(System.currentTimeMillis());
//        executorService.schedule(()->{
//            System.out.println("delay 3 second before running");
//            System.out.println(System.currentTimeMillis());
//        },3, TimeUnit.SECONDS);
//        executorService.shutdown();
        executorService.scheduleAtFixedRate(()->{
            System.out.println("delay 1 second before running every 3 second");
            System.out.println(System.currentTimeMillis());
            },1,3,TimeUnit.SECONDS);
        executorService.shutdown();
    }
}
