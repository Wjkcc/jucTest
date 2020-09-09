package javas.main.company.thread.map;

import javas.main.company.bean.HashT;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/9 11:37
 **/
public class Test01_hashtable {
    static Map<UUID,UUID> map = new ConcurrentHashMap<>();
    static int count = Constant.COUNT;
    static int thread_count = Constant.THREAD_COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static {
        for (int i = 0; i < count ; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }
    static class MyThread extends Thread {
        int start;
        int gap = count/thread_count;
        public MyThread(int start) {
            this.start = start;
        }
        public void run() {
            for (int i = start; i <start+gap ; i++) {
                map.put(keys[i],values[i]);
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread[] ts = new Thread[thread_count];
        for (int i = 0; i < thread_count; i++) {
            ts[i] = new MyThread(i*(count/thread_count));
        }
        for (Thread t : ts) {
            t.start();
        }
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < thread_count; i++) {
            ts[i] = new Thread(()-> {
                for (int j = 0; j < count; j++) {
                    map.get(keys[10]);
                }
            });
        }
        for (Thread t : ts) {
            t.start();
        }
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end1 = System.currentTimeMillis();
        System.out.println(end1-start1);
    }

}
