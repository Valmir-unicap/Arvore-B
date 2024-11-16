import java.util.Scanner;

public class Main {

    public void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o grau:");
        int grau = sc.nextInt();
        
        while(!isPofTwo(grau)) {
            grau = sc.nextInt();
        }

        Btree<Integer> bt = new Btree<>(null, grau);
        
        while(true) {
            System.out.println("Menu de opções");
            System.out.println("");
            System.out.println("1 para inserir");
            System.out.println("2 para encontrar a maior chave,");
            System.out.println("3 para encontrar a menor chave,");
            System.out.println("4 para calcular altura");
            System.out.println("5 exibir por oredem");

            int des = sc.nextInt();
            switch (des) {
                case 1:
                    bt.insert(10);
                    bt.insert(8);
                    bt.insert(13);
                    bt.insert(20);
                    bt.insert(17);
                    break;

                case 2:
                    bt.biggestKey();
                    break;


                case 3:
                    bt.smallestKey();
                    break;

                case 4:
                    bt.height();
                    break;

                case 5:
                    bt.emOrdem();
                    break;

                default:
                    System.out.println("Não encontrada!");
                    break;
            }
        }
    }

    private boolean isPofTwo(long x) {
        x = x-1;
        long l = 2;
        for(int i = 0; i < 60; i++) {
            if(l == x) {
                return true;
            }
            l*=2;
        }
        return false;
    }
}