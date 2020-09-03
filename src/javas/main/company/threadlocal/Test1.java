package javas.main.company.threadlocal;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: wjk
 * @date: 2020/7/21 14:34
 **/
public class Test1 {
    static ThreadLocal<Person> tl = new ThreadLocal<>();
    public static void main(String[] args) {


           new Thread(()->{
               try {
                   Thread.sleep(200);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println(tl.get());
           }).start();

        new Thread(()->{
            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        }).start();
    }




    static class Person{
        List<String> l = new ArrayList<>();
        public String name = "123";
    }
}
