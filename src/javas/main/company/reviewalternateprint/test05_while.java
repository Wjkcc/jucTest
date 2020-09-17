package javas.main.company.reviewalternateprint;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/17 9:58
 **/
public class test05_while {
    static volatile int flag = 0; // 0 线程1 1 线程2
    static char[] chars = null;
    static volatile int index = 0;
//    static  int index = 0;
    public static void main(String[] args) {
        String s = "1A2B3C4D5E6F7G8H9I";

//        String s = "1A";
        chars = s.toCharArray();
        new Thread(()->{
            while (index < chars.length) {
                while(flag == 1){}
                if (index < chars.length) {
                    System.out.println("t1 print " +chars[index++]);
                    flag = 1;
                }else {
                    break;
                }
            }
        }).start();
        new Thread(()->{
            while (index < chars.length) {
                while(flag == 0){}
                if (index < chars.length) {
                    System.out.println("t2 print " +chars[index++]);
                    flag = 0;
                }else {
                    break;
                }

            }
        }).start();
    }
}
