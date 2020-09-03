package javas.main.company.thread;

import sun.misc.Unsafe;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/2 10:46
 **/
public class test03 {
    AtomicInteger atomicInteger = new AtomicInteger(1);
    Unsafe unsafe = Unsafe.getUnsafe();

    int v;
    int add;
    int e;
    public test03(int v) {
        this.v = v;
    }
    public int addOne() {
        this.e = v;
        this.add = v;
      do {
         add++;
      }while(!compareAndSet(v,e,add));
      return add;
    }

    private boolean compareAndSet(int v, int e, int add) {
        if (v == e) {
            this.v = add;
            return true;
        }
        this.e = v;
        this.add = v;
        return false;
    }

    public static void main(String[] args) throws IOException {
        test03 test03 = new test03(0);
        for (int j = 0; j <100 ; j++) {
            new Thread(()->{
                System.out.println(test03.addOne());
            }).start();
        }
        System.in.read();
    }
}
