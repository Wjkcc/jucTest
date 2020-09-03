package javas.main.company.threadPool;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/14 14:27
 **/
public class Task implements Runnable {
    public Task() {
        super();
    }
    public Task(String name) {
        this.setName(name);
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" is running");
    }
    public void setName(String name) {
        Thread.currentThread().setName(name);
    }
}
