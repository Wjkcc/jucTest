package com.company.design_mode.singleton;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/15 16:41
 **/
public class M2 {
    private  static M2 INSTANCE = null;
    private M2() {

    }
    public static M2 getInstance() {
        if (null == INSTANCE) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new M2();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++) {
            new Thread(()->{
                System.out.println(M2.getInstance().hashCode());
            }).start();
        }
    }
}
