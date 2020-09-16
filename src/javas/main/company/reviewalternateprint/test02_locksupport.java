package javas.main.company.reviewalternateprint;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/16 10:52
 **/
public class test02_locksupport {
    static Thread t1 = null;
    static Thread t2 = null;
    static int index = 0;
    static char[] chars = null;
    public static void main(String[] args) {
        String s = "1A2B3C4D5E6F7G8H9I";
        chars = s.toCharArray();
        t1 = new Thread(()->{
            while (index < s.length()) {
                System.out.println("t1 print: "+chars[index++]);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.unpark(t2);
                LockSupport.park();
            }
            LockSupport.unpark(t2);
        });
        t2 = new Thread(()->{
            while (index < s.length()) {
                LockSupport.park();
                System.out.println("t2 print: "+chars[index++]);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.unpark(t1);
            }
            LockSupport.unpark(t1);
        });
        t1.start();
        t2.start();
    }
}
