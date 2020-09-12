package javas.main.company.review;

import javafx.animation.ScaleTransition;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/12 9:42
 **/
public class Transcationaltest {
    public static void main(String[] args) {
        try {
            int i = 1/0;
        }catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }
        int c =1/0;
        System.out.println("hello world");
    }
}
