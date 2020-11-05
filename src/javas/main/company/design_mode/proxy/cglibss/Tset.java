//package javas.main.company.design_mode.proxy.cglibss;
//
//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;
//
//import java.lang.reflect.Method;
//
///**
// * @description:
// * @author: wjk
// * @date: 2020/8/25 15:39
// **/
//public class Tset {
//    public static void main(String[] args) {
////        Enhancer enhancer = new Enhancer();
////        enhancer.setSuperclass(String.class);
////        enhancer.setCallback(new MethodInterceptor(){
////            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
////                System.out.println(1);
////                Object result = methodProxy.invokeSuper(o,objects);
////                System.out.println(2);
////                return result;
////            }
////        });
////        String s = (String) enhancer.create();
////        System.out.println(s == null);
//    }
//
//}
//
