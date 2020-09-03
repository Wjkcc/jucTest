package javas.main.company.design_mode.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/15 16:29
 **/
public class M1 {
    private AtomicInteger atomicInteger = new AtomicInteger();
    private final static M1 INSTANCE = new M1();
    private M1() {
        System.out.println("实例化 = "+atomicInteger.incrementAndGet());
    }
    public static M1 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        M1 a1 = M1.getInstance();
        M1 a2 = M1.getInstance();
        System.out.println(a1==a2);
    }

}
