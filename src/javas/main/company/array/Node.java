package javas.main.company.array;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/10 16:39
 **/
public class Node<T>{
    private T key;

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
    public Node() {
        super();
    }
    public Node(T key, T data, Node next) {
        this.key = key;
        this.data = data;
        this.next = next;
    }
    private T data;
     private Node next;
    private Boolean red = true;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Boolean getRed() {
        return red;
    }

    public void setRed(Boolean red) {
        this.red = red;
    }
}
