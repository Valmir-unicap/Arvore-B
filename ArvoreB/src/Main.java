public class Main {
    public static void main(String[] args) {
        BTree tree = new BTree(3);  // Ordem de 5
        
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(6);
        tree.insert(12);
        tree.insert(30);
        tree.insert(7);
        tree.insert(17);

        System.out.print("Árvore B após inserção: ");
        tree.traverse();  // Exibe todas as chaves
        System.out.println();

        // Testar a busca de uma chave
        int keyToSearch = 6;

        BTreeNode result = tree.search(keyToSearch);

        if (result != null) {
            System.out.println("Chave " + keyToSearch + " encontrada na árvore.");
        } else {
            System.out.println("Chave " + keyToSearch + " não encontrada na árvore.");
        }

        // Buscar chave inexistente
        keyToSearch = 25;
        result = tree.search(keyToSearch);
        if (result != null) {
            System.out.println("Chave " + keyToSearch + " encontrada na árvore.");
        } else {
            System.out.println("Chave " + keyToSearch + " não encontrada na árvore.");
        }

        // Remover chave 6
        tree.remove(6);
        System.out.print("Árvore B após remover chave 6: ");
        tree.traverse();
        System.out.println();
    }
}
