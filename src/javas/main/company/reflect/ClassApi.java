package company.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassApi
{
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException
    {
        Class<?> aClass = Class.forName("company.reflect.Person");
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods)
        {
            System.out.println(declaredMethod.getName());
        }
        System.out.println("----------------------");
        Object o = aClass.newInstance();
        Method add = aClass.getDeclaredMethod("add", int.class);
        add.setAccessible(true);
        Object invoke = add.invoke(o, 1);
        if (invoke instanceof String) {
            System.out.println(((String) invoke).toCharArray());
        }
        System.out.println("----------------------------");
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor<?> constructor : constructors)
        {
            System.out.println(constructor.getName());
        }
        System.out.println("----------------------------");
        Constructor<?> constructor = aClass.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Object bb = constructor.newInstance("bb");
        System.out.println(bb.toString());
    }
}
