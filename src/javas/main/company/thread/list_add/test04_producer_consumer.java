package javas.main.company.thread.list_add;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/7 13:49
 **/
public class test04_producer_consumer {
    static int max = 10;
    static int count = 0;
    static int modcount = 0;
    static List<String> l = new ArrayList<>(10);
    public static void main(String[] args) {
        Object lock = new Object();
        Thread producer = new Thread(()->{
                while (true) {
                    synchronized (lock) {
                        if (modcount >= 30) {
                            lock.notify();
                            break;
                        }
                    if(count < max) {
                        String s = new Random().nextInt(1000)+"";
                        l.add(s);
                        count++;
                        modcount++;
                        System.out.println("add elements： " + s +"  count = "+count);
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
            System.out.println("produce is over");
        });
        producer.start();
        Thread consumer = new Thread(() ->{
            while(true) {
                synchronized (lock) {
                    if (modcount >= 30) {
                        lock.notify();
                        break;
                    }
                    if(count > 0) {
                        String remove = l.remove(count-1);
                        System.out.println("get element"+remove);
                        count--;
                        modcount++;
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            System.out.println("consumer is over");
        });
        consumer.start();
    }
}
