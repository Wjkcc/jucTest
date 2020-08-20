package com.company.design_mode.chain_responsibility;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/20 10:21
 **/
public class Main {
    public static void main(String[] args) {
        Msg m = new Msg();
        m.setMessage("666666662");
        FilterChain filterChain = new FilterChain();
        filterChain.add(new Filter01()).add(new Filter02());
        filterChain.doFilter(m);
        System.out.println(m.getMessage());
    }
    static class Msg {
        String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "Msg{" +
                    "message='" + message + '\'' +
                    '}';
        }
    }
}
interface Filter {
    <T> boolean doFilter(T t);
}
class Filter01 implements Filter{

    @Override
    public <T> boolean doFilter(T t) {
        if (t instanceof Main.Msg) {
            String s = ((Main.Msg) t).getMessage();
            if(s.endsWith("2")){
                System.out.println(1111);
                String replace = s.replace('2', '1');
                System.out.println(replace);
                ((Main.Msg) t).setMessage(replace);
                return true;
            }
        }
        return false;
    }
}
class Filter02 implements Filter{

    @Override
    public <T> boolean doFilter(T t) {
        if (t instanceof String) {
            String s = (String)t;
            return !s.endsWith("f");
        }
        return false;
    }
}
class ParamFilter implements Filter{
    @Override
    public <T> boolean doFilter(T t) {
        Class<T> tClass = (Class<T>) t.getClass();
        Field[] declaredFields = tClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field == null) {
                throw new RuntimeException(field.getName()+"is null");
            }
        }
        return true;
    }
}
/**
 * 过滤链
 */
class FilterChain implements Filter{
    List<Filter> list = new LinkedList<>();
    public FilterChain add(Filter f) {
        list.add(f);
        return this;
    }

    /***
     * 对于每一个过滤器，如果其中一个返回false，后面的就不在执行
     * @param t
     * @param <T>
     * @return
     */
    @Override
    public <T> boolean doFilter(T t) {
        for (Filter filter : list) {
          if (!filter.doFilter(t)) {
              return false;
          }
        }
        return true;
    }
}

