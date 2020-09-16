package javas.main.company.thread.juc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/15 16:13
 **/
public class test02_dq1 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<MyDelay> queue = new DelayQueue<>();
        long now = System.currentTimeMillis();
        try {
            queue.put(new MyDelay(now+5000,"t1"));
            queue.put(new MyDelay(now+15000,"t2"));
            queue.put(new MyDelay(now+25000,"t3"));
            queue.put(new MyDelay(now+3000,"t4"));
            queue.put(new MyDelay(now+4000,"t5"));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
           MyDelay take = queue.take();
            System.out.println(take);
        }

    }
    @Data
    @ToString
    private static class MyDelay implements Delayed {
        private long time;
        private String name;

        public MyDelay(long time, String name) {
            this.name = name;
            this.time = time;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long convert = unit.convert(time - System.currentTimeMillis(), unit);
            return convert;
        }

        @Override
        public int compareTo(Delayed o) {
            MyDelay item = (MyDelay) o;
            long diff = this.time - item.time;
            if (diff <= 0) {// 改成>=会造成问题
                return -1;
            } else {
                return 1;
            }
        }
    }
}
