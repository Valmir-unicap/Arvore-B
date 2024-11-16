
public class LinkedList<T extends Comparable <T>> {

    private int size;
    private LinkedListNode<T> head;

    public LinkedList() {
        size = 0;
    }

    public void add(T info) {
        setSize(size()+1);
        if(head.getNext() == null) {
            head.setInfo(info);
            return ;
        }

        LinkedListNode<T> currNode = getHead();
        while(head.getNext() != null && head.getNext().getInfo().compareTo(info) < 0) {
            currNode = currNode.getNext();
        }
        LinkedListNode<T> nodeToBeAdded = new LinkedListNode<>(currNode.getNext(), info);
        currNode.setNext(nodeToBeAdded);
        return ;
    }

    public T search(int idx) {
        int i = 0;
        LinkedListNode<T> currNode = getHead();
        while(i < idx) {
            currNode = currNode.getNext();
        }
        return currNode.getInfo();
    }

    public int size() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LinkedListNode<T> getHead() {
        return head;
    }

    public void setHead(LinkedListNode<T> head) {
        this.head = head;
    }

}