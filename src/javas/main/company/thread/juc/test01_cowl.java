package javas.main.company.thread.juc;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/15 10:49
 **/
public class test01_cowl {
    public static void main(String[] args) {
        List<String> l = new CopyOnWriteArrayList<>();
        l.add("s");
    }
}
