package javas.main.company.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: wjk
 * @date: 2020/7/3 10:36
 **/
public class DebugTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(22);
        String a = map.put("1","2");
        System.out.println(a);
        Object o = new Object();
        List<String> l = new ArrayList<>();
    }
}
