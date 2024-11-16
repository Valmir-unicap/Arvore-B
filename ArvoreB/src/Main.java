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
            System.out.println("1 para inserir, 2 para remover, 3 para encontrar a maior chave, 4 para encontrar  a  menor  chave, 5 para calcular altura , 6 encontrar um valor, 7 exibir por níve, 8 exibir por oredem");
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

                    break;

                case 3:
                    bt.biggestKey();
                    break;


                case 4:
                    bt.smallestKey();
                    break;

                case 5:
                    bt.height();
                    break;

                case 6:

                    break;

                case 7:

                    break;

                case 8:
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