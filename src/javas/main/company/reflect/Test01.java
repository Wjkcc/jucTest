package company.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test01
{
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException
    {
        Class c = Person.class;
        Object o = c.newInstance();
        Method setName = c.getDeclaredMethod("setAge", int.class);
       Object age = 22;
       Number rs = (Number)age;
        setName.invoke(o,rs.intValue());
        System.out.println(o);

    }
}
