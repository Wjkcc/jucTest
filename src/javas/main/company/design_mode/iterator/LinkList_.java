package javas.main.company.design_mode.iterator;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/3 10:32
 **/
public class LinkList_ implements Collection_{
    int index = 0;
    Node head = null;
    Node tail = null;
    @Override
    public int size() {
        return index;
    }

    @Override
    public void add(Object o) {
        Node n = new Node(o,null);
        if (head == null) {
          head = tail = n;
        }else{
            tail.next = n;
            tail = n;
        }
        index++;
    }

    public LinkList_() {
        super();
    }

    @Override
    public Iterator_ iterator() {
        return new LinkIterator();
    }
    private  class LinkIterator implements Iterator_ {
        Node currentNext = head;
        int index = 0;
        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public Object next() {
           Node n = currentNext;
           currentNext = currentNext.next;
           index++;
            return n;
        }
    }
    @Data
    @AllArgsConstructor
    private static class Node {
        Object data;
        Node next;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    public static void main(String[] args) {
        LinkList_ l = new LinkList_();
        l.add("1");
        l.add("2");
        Iterator_ i = l.iterator();
        while(i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
