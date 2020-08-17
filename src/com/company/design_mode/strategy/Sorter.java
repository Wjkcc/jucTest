package com.company.design_mode.strategy;

import java.util.Comparator;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/17 10:35
 **/
public class Sorter{
    public void sort(Comparable[] cats) {
        for (int i=0;i<cats.length-1;i++) {
            for(int j=i+1;j<cats.length;j++) {
                 if (cats[i].compareTo(cats[j]) == 1 ){
                     swap(cats,i,j);
                 }

            }
        }
    }

    private void swap(Comparable[] cats, int i, int j) {
        Comparable temp = cats[i];
        cats[i] = cats[j];
        cats[j] = temp;
    }

    public <T> void sort(T[] t, Comparator<T> comparator) {
        for (int i=0;i<t.length-1;i++) {
            for(int j=i+1;j<t.length;j++) {
                if (comparator.compare(t[i],t[j]) == 1 ){
                    swap(t,i,j);
                }

            }
        }
    }
    private <T> void swap(T[] t, int i, int j) {
        T temp = t[i];
        t[i] = t[j];
        t[j] = temp;
    }

}
