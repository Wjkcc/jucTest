package javas.main.company.thread;

/**
 * @description: 可重入
 * @author: wjk
 * @date: 2020/9/1 10:40
 **/
public class testo1 {
    public synchronized void m1() {
        System.out.println("m1 start");
        m2();
        System.out.println("m1 end");
    }
    public synchronized void m2() {
        System.out.println("m2 start");
        try {
            System.out.println("线程开始等待1s");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end");
    }

    public static void main(String[] args) {
        testo1 testo1 = new testo1();
        testo1.m1();
    }
}
