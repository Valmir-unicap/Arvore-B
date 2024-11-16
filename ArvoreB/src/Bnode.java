

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Bnode<T extends Comparable<T>> {

    private Bnode<T> parent;
    private LinkedList<T> infos;
    private List<Bnode<T>> children;
    private boolean isLeaf;

    public Bnode(LinkedList<T> infos, List<Bnode<T>> children) {
        this.infos = infos;
        this.children = children;
        this.isLeaf = true;
    }

    public Bnode() {
        infos = new LinkedList<T>();
        children = new ArrayList<>();
        isLeaf = true;
    }

    LinkedList<T> getInfos() {
        return infos;
    }

    List<Bnode<T>> getChildren() {
        return children;
    }

    public void setInfos(LinkedList<T> infos) {
        this.infos = infos;
    }

    public void setChildren(List<Bnode<T>> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Bnode<T> getParent() {
        return parent;
    }

    public void setParent(Bnode<T> parent) {
        this.parent = parent;
    }

}