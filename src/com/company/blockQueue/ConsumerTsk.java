package com.company.blockQueue;

import com.company.Consumer;

import java.util.concurrent.BlockingQueue;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/15 14:45
 **/
public class ConsumerTsk implements Runnable{
    private BlockingQueue<String> blockingQueue;
    public ConsumerTsk(){
        super();
    }
    public ConsumerTsk(BlockingQueue<String> queue){
        this.blockingQueue = queue;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
//           for(int i=0;i<5;i++){
//               String take = blockingQueue.poll();
//               System.out.println(Thread.currentThread().getName()+"is consume:"+take);
//           }
while(!blockingQueue.isEmpty()){
    String take = blockingQueue.poll();
               System.out.println(Thread.currentThread().getName()+"is consume:"+take);
}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
