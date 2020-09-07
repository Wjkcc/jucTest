package javas.main.company.call;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/4 10:09
 **/
public class MyCall {
    List<String> l = new ArrayList<>();
    public void main1() throws ExecutionException, InterruptedException {
        l.add("222");
        FutureTask<String> ft = new FutureTask<>(new call1());
        new Thread(ft).start();
        String s = ft.get();
        System.out.println(s);
    }

    public static void main(String[] args) {
        MyCall myCall = new MyCall();
        try {
            myCall.main1();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private class call1 implements Callable<String> {

        @Override
        public String call() throws Exception {
            return l.get(0);
        }
    }
}
