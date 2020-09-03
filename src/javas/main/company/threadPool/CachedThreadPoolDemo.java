package javas.main.company.threadPool;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/14 14:20
 **/
public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService =  Executors.newCachedThreadPool();
        for (int i = 0;i < 10;i++) {
            executorService.execute(new Task("Thread-"+i));
        }
        executorService.shutdown();
    }
}
