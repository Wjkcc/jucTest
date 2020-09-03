package javas.main.company.design_mode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/25 10:59
 **/
public class Test {
    public static void main(String[] args) {
            Tank tank = new Tank();
            // 显示当前动态生成得类
//        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
            Moveable moveable = (Moveable) Proxy.newProxyInstance(tank.getClass().getClassLoader(),
                    new Class[]{Moveable.class},
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if (method.getName().equals("move")){
                                System.out.println("1");
                                Object o = method.invoke(tank,args);
                                System.out.println("2");
                                return o;
                            }
                            return method.invoke(tank,args);
                        }
                    });
            moveable.move();
            moveable.stop();
        String name = moveable.getClass().getName();
        System.out.println(name);
    }
}
interface Moveable {
    void move();
    void stop();
}
class Tank implements Moveable {

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            System.out.println("23");
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("tank runs "+ (end - start));
    }

    @Override
    public void stop() {
        System.out.println("cccc");
    }
}
class TankProxy extends Proxy {

    /**
     * Constructs a new {@code Proxy} instance from a subclass
     * (typically, a dynamic proxy class) with the specified value
     * for its invocation handler.
     *
     * @param h the invocation handler for this proxy instance
     * @throws NullPointerException if the given invocation handler, {@code h},
     *                              is {@code null}.
     */
    protected TankProxy(InvocationHandler h) {
        super(h);
    }
}
