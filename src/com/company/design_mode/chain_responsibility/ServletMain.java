package com.company.design_mode.chain_responsibility;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/20 11:48
 **/
public class ServletMain {
    public static void main(String[] args) {
        Request request = new Request();
        request.request = "22222222222222d";
        Response response = new Response();
        response.response = "22222222222333333333";
        ServletFilterChain servletFilterChain = new ServletFilterChain();
        servletFilterChain.add(new HelloFilter01()).add(new HelloFilter02());
        servletFilterChain.doFilter(request,response,servletFilterChain);
    }

}
class Request {
    String request;
    boolean complete;
}
class Response {
    String response;
    boolean complete;
}
interface ServletFilter {
    void doFilter(Request request, Response response,ServletFilterChain servletFilterChain);
}

/**
 * 模板
 */
abstract class DetailFilter implements ServletFilter {
    @Override
    public void doFilter(Request request, Response response, ServletFilterChain servletFilterChain) {
        if(!doFilter(request)){
            return;
        }
        servletFilterChain.doFilter(request,response,servletFilterChain);
        doFilter(response);
    }
    abstract boolean doFilter(Request request);
    abstract boolean doFilter(Response response);
}
class HelloFilter01 extends DetailFilter {

    @Override
    boolean doFilter(Request request) {
        System.out.print("hello filter01 request  ");
        if (request.request.endsWith("2")) {
            System.out.print("blocking ==");
            System.out.println("request end with str 2");
            return false;
        }
        System.out.println("--->");
        return true;
    }

    @Override
    boolean doFilter(Response response) {
        System.out.print("hello filter01 response  ");
        if (response.response.endsWith("3")) {
            System.out.println("exec");
           response.response = response.response.replace('3','1');
        }
        System.out.print("--->");
        return true;
    }
}
class HelloFilter02 extends DetailFilter {

    @Override
    boolean doFilter(Request request) {
        System.out.print("hello filter02 request  ");
        if (request.request.endsWith("5")) {
            System.out.println("request end with str 5");
            return false;
        }
        System.out.print("--->");
        return true;
    }

    @Override
    boolean doFilter(Response response) {
        System.out.print("hello filter02 response  ");
        if (response.response.endsWith("1")) {
            System.out.println("22222");
            response.response = response.response.replace('1','3');
        }
        System.out.print("--->");
        return true;
    }
}
class ServletFilterChain implements ServletFilter{
    List<ServletFilter> list = new ArrayList<>();
    AtomicInteger atomicInteger = new AtomicInteger();
    public ServletFilterChain add(ServletFilter servletFilter) {
        list.add(servletFilter);
        return this;
    }
    @Override
    public void doFilter(Request request, Response response, ServletFilterChain servletFilterChain) {
        if (list.size() == atomicInteger.get()) return;
        ServletFilter f = list.get(atomicInteger.get());
        atomicInteger.incrementAndGet();
        f.doFilter(request,response,servletFilterChain);
    }
}
