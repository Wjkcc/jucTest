package javas.main.company.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/31 13:55
 **/
public class MyThread implements Runnable {
     int count = 10;
    @Override
    public synchronized void run() {
        count--;
        System.out.println("count="+count);
    }

    public static void main(String[] args) {
//        MyThread m = new MyThread();
//        for (int i = 0; i < 100; i++) {
//            System.out.println("你好" +
//                    "");
//            new Thread(m).start();
//        }
        Map<String,Object> map = new ConcurrentHashMap<>(2);
        Map<String,Object> map2 = new HashMap<>(2);
        map2.put("1",null);

    }
}
