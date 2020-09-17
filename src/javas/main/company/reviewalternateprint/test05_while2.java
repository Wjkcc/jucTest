package javas.main.company.reviewalternateprint;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/17 10:11
 **/
public class test05_while2 {
    public enum flag { T1,T2}
    static volatile flag f = flag.T1;

    public static void main(String[] args) {
        String s1 = "123456789";
        String s2 = "ABCDEFGHI";
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        new Thread(()->{
            for(char c:c1) {
                while( f == flag.T2){}
                System.out.println("t1 print"+ c);
                f = flag.T2;
            }
        }).start();
        new Thread(()->{
            for(char c:c2) {
                while( f == flag.T1){}
                System.out.println("t2 print"+ c);
                f = flag.T1;
            }
        }).start();
    }

}
