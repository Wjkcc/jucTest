package com.company.threadlocal;

/**
 * @description:
 * @author: wjk
 * @date: 2020/7/20 14:12
 **/
public class M {
    public M() {
        super();
    }
    @Override
    protected void finalize() throws Throwable{
        System.out.println("finalize");
    }
}
