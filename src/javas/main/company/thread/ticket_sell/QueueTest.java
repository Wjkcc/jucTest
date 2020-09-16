package javas.main.company.thread.ticket_sell;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/14 17:06
 **/
public class QueueTest {
    public static Queue<String> queue = new ConcurrentLinkedDeque<>();
    static {
        for (int i = 0; i < 1000; i++) {
            queue.add("i:"+i);
        }
    }

    public static void main(String[] args) {
        // 你好
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(true) {
                    if (queue.isEmpty())
                        break;
                    System.out.println(queue.poll());
                }
            }).start();
        }
    }
}
