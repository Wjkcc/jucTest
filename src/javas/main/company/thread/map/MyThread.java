package javas.main.company.thread.map;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.UUID;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/9 11:16
 **/
@AllArgsConstructor
public class MyThread extends Thread{
    private Map<UUID,UUID> map;
    private int start;
    private int gap;
    private UUID[] keys;
    private UUID[] values;
    public void run() {
        for (int i = start; i < start+gap; i++) {
//            System.out.println("tt"+i);
            map.put(keys[i],values[i]);
        }
    }
}
