package com.company.design_mode.singleton;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/15 17:37
 **/
public class M3 {
    private M3(){

    }
    private static class M3Holder {
        private static final M3 INSTANCE = new M3();
    }
    public static M3 getInstance() {
        return M3Holder.INSTANCE;
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(()->{
                System.out.println(M3.getInstance().hashCode());
            }).start();

        }
    }
}
