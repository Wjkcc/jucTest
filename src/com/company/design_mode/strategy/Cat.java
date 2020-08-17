package com.company.design_mode.strategy;

import java.util.concurrent.Callable;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/17 10:32
 **/
public class Cat implements Comparable<Object>{
    private int height;
    private int weight;

    public Cat(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }
    public int compareTo(Object c) {
        if (null == c) {
            System.out.println("c is null");
            throw new NullPointerException("c is null");
        }
        if (c instanceof Cat) {
           Cat cs =(Cat)c;
            if (this.weight > cs.weight) return 1;
            else if (this.weight < cs.weight) return -1;
            else return 0;
        }
        throw new RuntimeException("c is not a Cat.class");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "height=" + height +
                ", weight=" + weight +
                '}';
    }
}
