import java.util.LinkedList;

public class Btree<T extends Comparable<T>> {

    private Bnode<T> root;
    private int order;

    public Btree(Bnode<T> root, int order) {
        this.root = root;
        this.order = order;
    }

    public void insert(T info) {
        insert(info, getRoot());
    }

    private void insert(T info, Bnode<T> currNode) {
        if(currNode == null) {
            Bnode<T> newNode = new Bnode<>();
            LinkedList<T> newInfos = new LinkedList<>();
            newInfos.add(info);
            newNode.setInfos(newInfos);
            setRoot(newNode);
            return;
        }

        while(!currNode.isLeaf()) {
            int i = 0;
            LinkedListNode<T> aNode = currNode.getInfos().getHead();
            while(aNode.getNext() != null) {
                if(info.compareTo(aNode.getInfo()) < 0) {
                    currNode = currNode.getChildren().get(i);
                }
                i++;
                aNode = aNode.getNext();
            }
        }

        if(currNode.getInfos().size() == 2*getOrder()-1) { // full
            currNode = split(currNode);
            return;
        }

        // not full
        currNode.getInfos().add(info);
        return;
    }

    public Bnode<T> split(Bnode<T> currNode) {
        Bnode<T> leftN = new Bnode<>();
        Bnode<T> rightN = new Bnode<>();
        T mid;
        int limit = currNode.getInfos().size() % 2 == 0 ? (currNode.getInfos().size()/2)-1 : (currNode.getInfos().size()+1)/2;
        LinkedListNode<T> node = currNode.getInfos().getHead();
        int i = 0;

        while(i < limit) {
            leftN.getInfos().add(node.getInfo());
            node = node.getNext();
            i++;
        }
        mid = node.getInfo();
        node = node.getNext();
        while(node.getNext() != null) {
            rightN.getInfos().add(node.getInfo());
            node = node.getNext();
        }

        if(currNode.getParent() == null) {
            currNode.getChildren().add(leftN);
            currNode.getChildren().add(rightN);
            LinkedList<T> newL = new LinkedList<>();
            newL.add(mid);
            currNode.setInfos(newL);
            currNode.setLeaf(false); // remember to set it to the another one
            return currNode;
        } else {
            currNode.getParent().getInfos().add(mid);
            currNode.getParent().getChildren().add(rightN);
            return leftN;
        }
    }

    public T biggestKey() {
        Bnode<T> node = getRoot();
        while(!node.isLeaf()) {
            node = node.getChildren().get(node.getChildren().size()-1);
        }
        LinkedListNode<T> llNode = node.getInfos().getHead();
        while(llNode.getNext() != null) {
            llNode = llNode.getNext();
        }
        return llNode.getInfo();
    }

    public T smallestKey() {
        Bnode<T> node = getRoot();
        while(!node.isLeaf()) {
            node = node.getChildren().get(0);
        }
        return node.getInfos().getHead().getInfo();
    }

    public int height() {
        int i = 0;
        Bnode<T> node = getRoot();
        while(!node.isLeaf()) {
            i++;
            node = node.getChildren().get(0);
        }
        return i;
    }

    private Bnode<T> getRoot() {
        return root;
    }

    private void setRoot(Bnode<T> root) {
        this.root = root;
    }

    private int getOrder() {
        return order;
    }

    private void setOrder(int order) {
        this.order = order;
    }

    public void percorrerEmOrdem() {
        int i;
        for (i = 0; i < this.order; i++) {
            if (root.children[i] != null) {
            root.children[i].percorrerEmOrdem();
        }
            System.out.println(root.infos[i]);
        }
        if (root.children[i] != null) { root.children[i].percorrerEmOrdem();
        }
    }

    public void emOrdem() {
        if (root == null) {
            percorrerEmOrdem();
        } else {
            System.out.println("Árvore vazia"); }
    }
}