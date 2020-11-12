package company.reflect;

import sun.security.pkcs11.Secmod;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

public class Test02
{
    public static Set<Character> letters = new HashSet<>(26);
    public static String letterString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static void main(String[] args)
    {
//        Map<String, String> deMap = getDeMap(Person.class);
//        Set<String> strings = deMap.keySet();
//        strings.forEach(e-> System.out.println(e+"    "+deMap.get(e)));
        HelloWorld helloWorld = new HelloWorld("hello","app","33","haha");
        save(helloWorld);
    }

    /**
     * 根据sql，传入查询参数和返回的类型
     * @param sql
     * @param parameters
     * @param clazz
     * @return
     */

    public static List getQueryList(String sql, Object[] parameters, Class clazz) throws SQLException
    {
        List list = null;
        Connection connection= null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        // 获取连接，执行语句
        try{
            connection = DBUtils.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            // 入参
            if (parameters != null) {
                for (Object obj : parameters) {
                    preparedStatement.setObject(index++,obj);
                }
            }
            // 执行查询
            resultSet = preparedStatement.executeQuery();
            // 获取元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            // 获取数据的列数,每行数据的属性个数
            int columnCount = metaData.getColumnCount();
            // 创建空列表
            list = Collections.emptyList();
            // 返回结果封装成传入类型的类
            // 循环结果
            while(resultSet.next()) {
                Object o = clazz.newInstance();
                // 每一行封装
                for (int i = 1; i <= columnCount ; i++)
                {
                    // 当前行当前列的值
                    Object value = resultSet.getObject(i);
                    // 当前行的当前列的名称
                    String columnName = metaData.getColumnName(i);
                    // 获取类中的对应属性
                    Field declaredField = clazz.getDeclaredField(columnName);
                    Method method = clazz.getDeclaredMethod(getSetMethod(columnName), declaredField.getType());
                    if (value instanceof Number) {
                        Number number = (Number)value;
                        String typeName = declaredField.getType().getName();
                        if ("int".equals(typeName) || "java.lang.Integer".equals(typeName)) {
                            method.invoke(o,((Number) value).intValue());
                        }
                        else if ("long".equals(typeName) || "java.lang.Long".equals(typeName)) {
                            method.invoke(o,((Number) value).longValue());
                        }
                        else if ("byte".equals(typeName) || "java.lang.Byte".equals(typeName)) {
                            method.invoke(o,((Number) value).byteValue());
                        }
                        else if ("short".equals(typeName) || "java.lang.Short".equals(typeName)) {
                            method.invoke(o,((Number) value).shortValue());
                        }
                        else if ("float".equals(typeName) || "java.lang.Float".equals(typeName)) {
                            method.invoke(o,((Number) value).floatValue());
                        }
                        else if ("double".equals(typeName) || "java.lang.Double".equals(typeName)) {
                            method.invoke(o,((Number) value).doubleValue());
                        }
                    }else
                    {
                        method.invoke(o,value);
                    }
                }
                list.add(o);
            }
        }catch(Exception e){
            System.out.println("error");
        }
        finally
        {
            connection.close();
        }
        return list;
    }

    /**
     * 获取传入名称的set方法
     * @param name
     * @return
     */
    public static String getSetMethod(String name) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("set").append(name.substring(0,1).toUpperCase()).append(name.substring(1));
        return stringBuffer.toString();
    }
    public static String getGetMethod(String name) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("get").append(name.substring(0,1).toUpperCase()).append(name.substring(1));
        return stringBuffer.toString();
    }

    /**
     * 获取当前实体类和数据库表对应的属性map
     * v 对应数据库的属性名称
     * k 实体类属性名称
     * name -> name
     * appName -> app_name
     * @param clazz
     * @return
     */
    public static Map<String,String> getDeMap(Class clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        Map<String,String> res = new HashMap<>();
        for (Field field : declaredFields) {
            String desName = field.getName();
            // 对比属性名称如果全部小写后和原名称相同，表面没有驼峰命名，直接放到map
            if (desName.equals(desName.toLowerCase())) {
                res.put(desName,desName);
                continue;
            }
            StringBuilder stringBuffer = new StringBuilder();
            char[] chars = desName.toCharArray();
            for (int i = 0; i < chars.length; i++)
            {
                if (letterString.contains(chars[i]+"")) {
                    stringBuffer.append("_");
//                    // 转成小写
//                    char c = (char)((int)chars[i] + 32);
//                    stringBuffer.append(c);
                }
                    stringBuffer.append(chars[i]);
            }
            res.put(desName,stringBuffer.toString().toLowerCase());
        }
        return res;
    }
//    public static boolean litterContain(char c) {
//        if (letters.isEmpty()) {
//            putUpLetters(letters);
//        }
//    }
//
//    private static void putUpLetters(Set<Character> letters)
//    {
//        String letterString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        letters.add(letterString.co);
//    }
    /**
     * 数据库插入数据
     *
     */
    public static<T> int save(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        StringBuffer sql = null;
        StringBuffer values = null;
        Class<?> aClass = t.getClass();
        Map<String, String> reMap = getDeMap(aClass);
        try
        {
            // 获取数据库表名称
            String name = aClass.getSimpleName();
            String dbName = getDbName(name);
             // 数据库实体类对应字段名称
            Set<String> strings = reMap.keySet();
            Map<String, Object> addMap = new HashMap<>();
            // 把实体类属性中有值得数据拿出来，拼接sql
            strings.forEach(e ->{
                try
                {
                    // 通过get方法获取对应属性名称的属性值
                    Method method = aClass.getDeclaredMethod(getGetMethod(e),null);
                    Object invoke = method.invoke(t, null);
                    if (invoke != null) {
                        addMap.put(reMap.get(e),invoke);
                    }
                }
                catch (Exception es)
                {
                    es.printStackTrace();
                }
            });
            // 如果
            if(addMap.isEmpty()) {
                System.out.println("null");
                return 0;
            }
            sql = new StringBuffer();
            sql.append("insert into ").append(dbName).append(" (");
            values = new StringBuffer();
            values.append(" values(");
            StringBuffer finalSql = sql;
            StringBuffer finalValues = values;
            addMap.forEach((k, v)->{
                finalSql.append(k+",");
                finalValues.append(v+",");
            });
            finalSql.delete(finalSql.length()-1,finalSql.length());
            finalValues.delete(finalValues.length()-1,finalValues.length());
            finalSql.append(")").append(finalValues+")");
            System.out.println(sql.toString());

            // 获取连接
//             connection = DBUtils.getInstance().getConnection();
//             statement = connection.prepareStatement(sql.toString());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        int res = 0;
        return res;
    }

    private static String getDbName(String name)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(name.substring(0,1));
        char[] chars = name.substring(1).toCharArray();
        for (char c : chars) {
            if (letterString.contains(c+"")) {
                sb.append("_");
            }
            sb.append(c);
        }
        return sb.toString().toLowerCase();
    }

}
