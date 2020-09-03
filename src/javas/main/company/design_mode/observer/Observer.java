package javas.main.company.design_mode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/21 13:49
 **/
public class Observer {
    public static void main(String[] args) {

    }

}
class child {
    List<ObserverMode> list;
    public synchronized void addObserver(ObserverMode observerMode) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(observerMode);
    }
    public void doRespond(ActionEvent actionEvent) {
        System.out.println("sss");
        for (ObserverMode observerMode : list) {
            observerMode.doAction(actionEvent);
        }
    }
}
class ActionEvent {
    long timestamp;
    String local;
    child source;
    public ActionEvent(long timestamp, String local,child source) {
        this.timestamp = timestamp;
        this.local = local;
        this.source = source;
    }
}
interface ObserverMode {
    void doAction(ActionEvent actionEvent);
}
class Obs01 implements ObserverMode {

    @Override
    public void doAction(ActionEvent actionEvent) {
        this.p();
    }
    public void p() {
        System.out.println("pppp");
    }
}
