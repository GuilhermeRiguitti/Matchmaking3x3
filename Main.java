import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Matchmaking matchmaking = new Matchmaking();
        boolean continuar = true;

        while (continuar) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Adicionar jogador");
            System.out.println("2 - Exibir lista de espera");
            System.out.println("3 - Formar times");
            System.out.println("4 - Exibir partidas em andamento");
            System.out.println("0 - Sair");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    matchmaking.adicionarJogador();
                    break;
                case 2:
                    matchmaking.exibirListaEspera();
                    break;
                case 3:
                    matchmaking.formarTimes();
                    break;
                case 4:
                    ArrayList<Time> partidasEmAndamento = matchmaking.getPartidasEmAndamento();
                    for (Time time : partidasEmAndamento) {
                        System.out.println("Time " + time.getId() + ": " + time + " | Pontuação total: "
                                + time.calcularPontuacaoTotal());

                    }
                    break;
                case 0:
                    matchmaking.limparArquivo();
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}