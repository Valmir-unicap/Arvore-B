public class LinkedListNode<T extends Comparable <T>> {

    private LinkedListNode<T> next;
    private T info;

    public LinkedListNode(LinkedListNode<T> next, T info) {
        this.next = next;
        this.info = info;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

}