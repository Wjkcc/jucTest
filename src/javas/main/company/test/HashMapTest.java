package javas.main.company.test;

import java.util.*;

/**
 * @description:
 * @author: wjk
 * @date: 2020/7/2 9:38
 **/
public class HashMapTest {
    public static void main(String[] args) {
//    HashMap<String, String> m = new HashMap<>(22);
////        Collection<String> values = map.values();
////        Class<? extends Collection> aClass = values.getClass();
//String a1 = m.put("1","1");
//        System.out.println(a1);
//        String a2 = m.put("1","4");
//        String a3 = m.put("6","3");
//        System.out.println(a2);
//        System.out.println(a3);
        int a = 10;
        if (a<100) {
            System.out.println(1);
        }else if (a<30) {
            System.out.println(2);
        }
        List<String> l = new LinkedList<>();
        List<String> l2 = new ArrayList<>();
        l2.add("3");
        try {
            l2.get(2);
        }catch (RuntimeException e) {
            System.out.println(222);
        }

//        int a = HashT.change(13);
//        System.out.println(a);
//
//        HashT t = new HashT();

//         m.keySet().forEach(System.out::println);
//         m.forEach(new BiConsumer<String, Object>() {
//
//             @Override
//             public void accept(String s, Object o) {
//                 System.out.println("s===="+s+"===="+o.toString());
//             }
//         });


//        m.replaceAll((s,o)-> s.equals("1") ? "2":"3");
//
//        m.forEach((s,o)-> System.out.println(s+"="+o.toString()));
    }

}
