package com.company;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description:
 * @author: wjk
 * @date: 2020/6/28 14:48
 **/
public class ArrayListTest {
    public static void main(String[] args) {
//        List<String> l = new Vector<>();
//        List<String> l = Collections.synchronizedList(new ArrayList<>());
        List<String> l = new CopyOnWriteArrayList<>();
        Map<String,String> map1 = new HashMap<>();
        for (int i =0; i<3; i++) {
            new Thread(()->{
                l.add(UUID.randomUUID().toString().substring(1,9));
                System.out.println(l);
            }, String.valueOf(i)).start();
        }
        System.out.println("lll"+l);

        Map<String,String> map =new ConcurrentHashMap<>();





    }
}
