package javas.main.company.thread.juc;

import com.sun.org.apache.regexp.internal.RE;
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
 * @date: 2020/9/15 15:02
 **/
public class test02_dq {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<MyDelay> queue = new DelayQueue<>();
        long now = System.currentTimeMillis();
        try {
            queue.put(new MyDelay(500,"t1",now));
            queue.put(new MyDelay(1500,"t2",now));
            queue.put(new MyDelay(2500,"t3",now));
            queue.put(new MyDelay(300,"t4",now));
            queue.put(new MyDelay(400,"t5",now));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(queue.size());
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            MyDelay take = queue.take();
            System.out.println(take);
        }

    }
    @Data
    @AllArgsConstructor
    @ToString
    private static class MyDelay implements Delayed {
        private long seconds;
        private String name;
        private long now;
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(now + seconds-System.currentTimeMillis(),unit);
        }
        public MyDelay(long time, String name) {
            this.name = name;
            this.seconds = time;
        }
        @Override
        public int compareTo(Delayed o) {
            if (o.getDelay(TimeUnit.MILLISECONDS) < this.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else if (o.getDelay(TimeUnit.MILLISECONDS) > this.getDelay(TimeUnit.MILLISECONDS)){
                return -1;
            } else
                return 0;
        }
    }
}
