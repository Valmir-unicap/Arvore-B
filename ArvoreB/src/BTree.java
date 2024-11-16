public class BTree {
    BTreeNode root;  // Raiz da árvore
    int t;           // Grau mínimo

    public BTree(int t) {
        this.t = t;
        this.root = null;
    }

    // Função para percorrer e imprimir a árvore
    public void traverse() {
        if (root != null) {
            root.traverse();
        }
    }

    // Função para buscar uma chave na árvore
    public BTreeNode search(int key) {
        return root != null ? root.search(key) : null;
    }

    // Função para inserir uma chave na árvore
    public void insert(int key) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = key;
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) {
                BTreeNode newRoot = new BTreeNode(t, false);
                newRoot.children[0] = root;
                newRoot.splitChild(0, root);
                root = newRoot;
            }
            root.insertNonFull(key);
        }
    }

    // Função para remover uma chave da árvore
    public boolean remove(int key) {
        if (root == null) {
            return false;
        }
        boolean result = root.remove(key, null);
        if (root.n == 0) {
            if (root.isLeaf) {
                root = null;
            } else {
                root = root.children[0];
            }
        }
        return result;
    }
}
