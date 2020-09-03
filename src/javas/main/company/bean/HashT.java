package javas.main.company.bean;

/**
 * @description:
 * @author: wjk
 * @date: 2020/7/6 13:57
 **/
public class HashT {



    public static final int c = 22;
    public static final  int change(int a) {
        int n = a - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n < 0 ? 1: n+1;
    }
    protected void print() {
        System.out.println("hello world");
    }

}
