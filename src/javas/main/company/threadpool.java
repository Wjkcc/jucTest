package javas.main.company;

import java.util.concurrent.*;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/17 10:56
 **/
public class threadpool {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(1,1,1, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(1));
    }
}
