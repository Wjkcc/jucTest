package javas.main.company.test;


import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: wjk
 * @date: 2020/6/30 14:53
 **/
class ShareData{
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    /**
     * 打印输出
     * @param size
     */
    private void print(int size) {
        checkSize(size);
        for (int i=0; i<size; i++) {
            System.out.println(Thread.currentThread().getName()+"__print:"+i);
        }
    }

    public void execution1() {
        lock.lock();
        try{
            // 判断标志
            while(flag != 1) {
                c1.await();
            }
            //输出
            print(5);
            // 修改
            flag = 2;
            // 通知
            c2.signal();
        }catch(Exception e){
          e.printStackTrace();
        }
        finally{
        lock.unlock();
        }
    }

    public void execution2() {
        lock.lock();
        try{
            // 判断标志
            while(flag != 2) {
                c2.await();
            }
            //输出
            print(10);
            // 修改
            flag = 3;
            // 通知
            c3.signal();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }

    public void execution3() {
        lock.lock();
        try{
            // 判断标志
            while(flag != 3) {
                c3.await();
            }
            //输出
            print(15);
            // 修改
            flag = 1;
            // 通知
            c1.signal();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }
    private void checkSize(int size) {
        if(size < 0) {
            throw new RuntimeException("size is less than 0");
        }
    }
}
public class ConditionTest {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i=0; i<3; i++) {
                shareData.execution1();
            }
        },"a").start();

        new Thread(()->{
            for (int i=0; i<3; i++) {
                shareData.execution2();
            }
        },"b").start();

        new Thread(()->{
            for (int i=0; i<3; i++) {
                shareData.execution3();
            }
        },"c").start();
    }
//    HashSet<String> s = new HashSet<>();

}
class thread3 implements Callable<String>{

    @Override
    public String call() throws Exception {
        Map<String,String> map = new HashMap<>(10,0.8f);
        String s = map.put("1", "s");
        for(String a:map.keySet()) {
            System.out.println(map.get(a));
        }
        Collection<String> values = map.values();
        for(String a2:values) {
            System.out.println(a2);
        }
        return s;
    }
}
