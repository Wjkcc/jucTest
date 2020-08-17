package com.company.design_mode.strategy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/17 10:14
 **/
public class StrategyDemo {
    private static Sorter sorter = new Sorter();
    public static void main(String[] args) {
//        Cat[] cats ={new Cat(2,3),new Cat(1,2),new Cat(3,2)};
//        Sorter sorter = new Sorter();
//        sorter.sort(cats);
//        System.out.println(Arrays.toString(cats));
        Dog[] dogs = {new Dog(22),new Dog(12),new Dog(44)};
        sorter.sort(dogs, new Comparator<Dog>() {
            @Override
            public int compare(Dog o1, Dog o2) {
                if(o1.getFoodAmount() > o2.getFoodAmount()) return 1;
                else if (o1.getFoodAmount() < o1.getFoodAmount()) return -1;
                else return 0;
            }
        });


        System.out.println(Arrays.toString(dogs));
    }
}
