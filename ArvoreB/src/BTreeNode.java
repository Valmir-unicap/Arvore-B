public class BTreeNode {
    int[] keys;        // Array de chaves
    int t;             // Grau mínimo (mínimo de chaves por nó)
    BTreeNode[] children; // Array de filhos (subárvores)
    int n;             // Número atual de chaves
    boolean isLeaf;    // Verdadeiro se o nó for uma folha, falso caso contrário

    // Construtor para inicializar um nó
    public BTreeNode(int t, boolean isLeaf) {
        this.t = t;
        this.isLeaf = isLeaf;
        this.keys = new int[2 * t - 1]; // Máximo de 2t - 1 chaves
        this.children = new BTreeNode[2 * t]; // Máximo de 2t filhos
        this.n = 0;
    }

    // Função para percorrer e imprimir o nó
    public void traverse() {
        int i = 0;
        // Percorre todas as chaves e imprime
        while (i < n) {
            // Se o nó não for folha, percorre o filho antes de imprimir a chave
            if (!isLeaf) {
                children[i].traverse(); // Percorre o filho esquerdo
            }
            // Imprime a chave
            System.out.print(keys[i] + " ");
            i++;
        }

        // Se o nó não for folha, percorre o último filho
        if (!isLeaf) {
            children[i].traverse();
        }
    }

    // Função para buscar uma chave no nó
    public BTreeNode search(int key) {
        int i = 0;
        while (i < n && key > keys[i]) {
            i++;
        }
        if (i < n && keys[i] == key) {
            return this;
        }
        if (isLeaf) {
            return null;
        }
        return children[i].search(key);
    }

    // Função para inserir uma chave não cheia
    public void insertNonFull(int key) {
        int i = n - 1;

        if (isLeaf) {
            // Move as chaves para a direita até encontrar a posição certa
            while (i >= 0 && keys[i] > key) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = key;
            n++;
        } else {
            // Encontre o filho que deve receber a chave
            while (i >= 0 && keys[i] > key) {
                i--;
            }
            i++;

            if (children[i].n == 2 * t - 1) {
                splitChild(i, children[i]);

                if (keys[i] < key) {
                    i++;
                }
            }
            children[i].insertNonFull(key);
        }
    }

    // Função para dividir um filho que está cheio
    public void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(t, y.isLeaf);
        z.n = t - 1;

        // Mover as chaves para o novo nó
        for (int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[j + t];
        }

        if (!y.isLeaf) {
            // Mover os filhos
            for (int j = 0; j < t; j++) {
                z.children[j] = y.children[j + t];
            }
        }

        y.n = t - 1;

        // Mover os filhos do nó pai
        for (int j = n; j > i; j--) {
            children[j + 1] = children[j];
        }
        children[i + 1] = z;

        // Mover as chaves do nó pai
        for (int j = n - 1; j >= i; j--) {
            keys[j + 1] = keys[j];
        }

        keys[i] = y.keys[t - 1];
        n++;
    }

    // Função para remover uma chave do nó
    public boolean remove(int key, BTreeNode parent) {
        int i = 0;
        while (i < n && keys[i] < key) {
            i++;
        }

        if (i < n && keys[i] == key) {
            // Caso 1: A chave está em um nó folha
            if (isLeaf) {
                // Remover a chave diretamente
                for (int j = i; j < n - 1; j++) {
                    keys[j] = keys[j + 1];
                }
                n--;
                return true;
            } else {
                // Caso 2: O nó não é uma folha, precisamos achar um sucessor ou predecessor
                BTreeNode child = children[i];
                if (child.n >= t) {
                    // Caso 2.1: O filho tem pelo menos t chaves
                    int predKey = getPredecessor(i);
                    keys[i] = predKey;
                    child.remove(predKey, this);
                    return true;
                } else {
                    BTreeNode sibling = children[i + 1];
                    if (sibling.n >= t) {
                        // Caso 2.2: O irmão tem pelo menos t chaves
                        int succKey = getSuccessor(i);
                        keys[i] = succKey;
                        sibling.remove(succKey, this);
                        return true;
                    } else {
                        // Caso 2.3: O filho e o irmão têm menos de t chaves, fundimos
                        merge(i);
                        return children[i].remove(key, this);
                    }
                }
            }
        } else if (!isLeaf) {
            // Caso 3: A chave não está neste nó, mas o nó não é folha
            return children[i].remove(key, this);
        }

        return false;
    }

    // Função para pegar o maior valor da subárvore à esquerda
    public int getPredecessor(int i) {
        BTreeNode curr = children[i];
        while (!curr.isLeaf) {
            curr = curr.children[curr.n];
        }
        return curr.keys[curr.n - 1];
    }

    // Função para pegar o menor valor da subárvore à direita
    public int getSuccessor(int i) {
        BTreeNode curr = children[i + 1];
        while (!curr.isLeaf) {
            curr = curr.children[0];
        }
        return curr.keys[0];
    }

    // Função para fundir dois filhos
    public void merge(int i) {
        BTreeNode left = children[i];
        BTreeNode right = children[i + 1];

        // Mover a chave do nó pai para o filho esquerdo
        left.keys[left.n] = keys[i];
        for (int j = 0; j < right.n; j++) {
            left.keys[left.n + 1 + j] = right.keys[j];
        }

        // Mover todos os filhos do nó direito para o nó esquerdo
        if (!left.isLeaf) {
            for (int j = 0; j <= right.n; j++) {
                left.children[left.n + j + 1] = right.children[j];
            }
        }

        // Atualizar o número de chaves e filhos no nó esquerdo
        left.n += right.n + 1;

        // Deslocar todas as chaves e filhos do nó pai
        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
        }
        for (int j = i + 1; j < n; j++) {
            children[j] = children[j + 1];
        }

        n--;
    }
}
