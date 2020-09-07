package javas.main.company.thread.list_add;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/7 11:18
 **/
public class test01 {
    public static void main(String[] args) {
        List<Object> l = new LinkedList<>();
        System.out.println("main");
        Thread t2 = new Thread(()->{
            LockSupport.park();
            System.out.println("t2结束");
        });
        t2.start();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i< 10 ;i++) {
                l.add(new Object());
                System.out.println(i);
                if (l.size() == 5) {
                    LockSupport.unpark(t2);
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();

    }
}
