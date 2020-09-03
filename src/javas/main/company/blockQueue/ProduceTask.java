package javas.main.company.blockQueue;

import java.util.concurrent.BlockingQueue;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/15 14:36
 **/
public class ProduceTask implements Runnable{
    private BlockingQueue<String> blockingQueue;
    public ProduceTask() {

    }
    public ProduceTask(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
        try {
//           for(int i=0;i<5;i++){
//               blockingQueue.put("a");
//               System.out.println(Thread.currentThread().getName()+" is produce:=> a");
//           }
            blockingQueue.put("a");
            System.out.println(Thread.currentThread().getName()+" is produce:=> a");
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
