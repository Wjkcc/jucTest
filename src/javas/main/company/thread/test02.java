package javas.main.company.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/1 10:52
 **/
public class test02 {
    int count = 0;
    public synchronized void m1() {

        while(true) {
            System.out.println(Thread.currentThread().getName()+"count:"+count);
            count++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {
                System.out.println("跑出异常，其他线程获得锁");
                try {
                    int i = 5/0;
                }catch (Exception e) {
                    System.out.println("获得当前异常:"+e.getMessage());
                    continue;
                }
            }
            if (count == 10 || count == 15) {
                break;
            }
        }
    }

    public static void main(String[] args) {
//        test02 a = new test02();
//        new Thread(()->{
//            a.m1();
//        },"t1").start();
//        new Thread(()->{
//            a.m1();
//        },"t2").start();
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(""+i,"+"+i);
        }
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, String> entry = it.next();
            String value = entry.getValue();
            if (new Random().nextInt(100) > 10) {
                it.remove();
            }
        }
        System.out.println(map.size());

        Map<String,String> map1 = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            map1.put("1"+i,"1"+i);
        }
        Set<String> strings = map1.keySet();
        strings.forEach(e->{
            if (new Random().nextInt(100) > 10) {
                map1.remove(e);
            }
        });
        System.out.println(map1.size());
    }
}
