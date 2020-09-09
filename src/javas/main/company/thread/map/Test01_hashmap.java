package javas.main.company.thread.map;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javas.main.company.threadlocal.M;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/9 11:09
 **/
public class Test01_hashmap {
    static Map<UUID,UUID> map = new Hashtable<>();
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

    public static void main(String[] args) {
        Thread[] threads = new Thread[thread_count];
        long start = System.currentTimeMillis();
        for (int i = 0; i < thread_count; i++) {
//            System.out.println(i);
            MyThread t = new MyThread(map,i*(count/thread_count),count/thread_count,keys,values);
            threads[i] = t;
        }
        for (int i = 0; i < thread_count; i++) {
            threads[i].start();
        }
        for (int i = 0; i < thread_count; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println(map.size());
        long start1 = System.currentTimeMillis();
//        for (int i = 0; i < thread_count; i++) {
//            new Thread(()-> {
//                for (int j = 0; j < count; j++) {
//                    map.get(keys[10]);
//                }
//            }).start();
//        }
//        long end1 = System.currentTimeMillis();
//        for (int i = 0; i < thread_count; i++) {
//            threads[i].start();
//        }
//        for (int i = 0; i < thread_count; i++) {
//            try {
//                threads[i].join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(end1-start1);
    }
}
