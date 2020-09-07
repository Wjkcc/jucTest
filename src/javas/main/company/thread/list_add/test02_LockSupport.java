package javas.main.company.thread.list_add;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/7 11:30
 **/
public class test02_LockSupport {
    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {

        List<Object> l = new LinkedList<>();
        System.out.println("main");
         t2 = new Thread(()->{
             // t2阻塞
            LockSupport.park();
            System.out.println("t2结束");
            // t2 结束后t1继续运行
            LockSupport.unpark(t1);
        });
        t2.start();
         t1 = new Thread(() -> {
            for (int i = 0; i< 10 ;i++) {
                l.add(new Object());
                System.out.println(i);
                if (l.size() == 5) {
                    // t2继续运行，t1阻塞
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });
        t1.start();
    }
}
