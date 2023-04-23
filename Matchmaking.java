import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Matchmaking {
    private ArrayList<Jogador> listaEspera;
    private ArrayList<Time> partidasEmAndamento;
    private int proximoIdTime;

    public Matchmaking() {
        listaEspera = new ArrayList<>();
        partidasEmAndamento = new ArrayList<>();
        proximoIdTime = 1;
    }

    public void lerArquivoJogadores(String nomeArquivo) {
        try {
            FileReader fr = new FileReader(nomeArquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("id;role;pontuacao_habilidade")) {
                    // Ignorar linha de cabeçalho
                    continue;
                }
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String role = dados[1];
                int pontuacaoHabilidade = Integer.parseInt(dados[2]);

                Jogador jogador = new Jogador(id, role, pontuacaoHabilidade);
                adicionarJogador(jogador);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo.");
        }
    }

    public void adicionarJogador() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ID do jogador:");
        int id = scanner.nextInt();
        System.out.println("Digite a role do jogador:");
        String role = scanner.next();
        System.out.println("Digite a pontuação de habilidade do jogador:");
        int pontuacaoHabilidade = scanner.nextInt();

        // escreve os dados do jogador no arquivo Jogadores.txt
        try (FileWriter fw = new FileWriter("Jogadores.txt", true)) {
            fw.write("ID: " + id + " | Role: " + role + " | Pontuação de habilidade: " + pontuacaoHabilidade + "\n");
            System.out.println("Jogador adicionado ao arquivo Jogadores.txt com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo Jogadores.txt: " + e.getMessage());
        }

        Jogador jogador = new Jogador(id, role, pontuacaoHabilidade);
        adicionarJogador(jogador);
    }

    public void adicionarJogador(Jogador jogador) {
        listaEspera.add(jogador);
    }

    public void exibirListaEspera() {
        System.out.println("Lista de jogadores em espera:");
        for (Jogador jogador : listaEspera) {
            System.out.println("ID: " + jogador.getId() + " | Role: " + jogador.getRole()
                    + " | Pontuação de habilidade: " + jogador.getPontuacaoHabilidade());
        }
    }

    public void formarTimes() {
        ordenarListaEsperaPorHabilidade();
        int numJogadores = listaEspera.size();
        int numTimes = (numJogadores / 3); // Arredonda para cima
        ArrayList<Time> times = new ArrayList<>();
        for (int i = 0; i < numTimes; i++) {
            Time time = new Time(proximoIdTime++);
            times.add(time);
            for (int j = 0; j < 2; j++) {
                if (!listaEspera.isEmpty()) {
                    Jogador jogador = listaEspera.remove(0);
                    time.adicionarJogador(jogador);
                }
            }
        }
        for (Jogador jogador : listaEspera) {
            boolean adicionado = false;
            for (Time time : times) {
                if (time.getJogadores().size() < 3 && !possuiJogador(jogador) && !time.possuiRoleRepetida(jogador)) {
                    time.adicionarJogador(jogador);
                    adicionado = true;
                    break;
                }
            }
            if (!adicionado) {
                Time novoTime = new Time(proximoIdTime++);
                novoTime.adicionarJogador(jogador);
                times.add(novoTime);
            }
        }
        partidasEmAndamento.addAll(times);
        listaEspera.clear();
    }

    public ArrayList<Time> getPartidasEmAndamento() {
        return partidasEmAndamento;
    }

    private void ordenarListaEsperaPorHabilidade() {
        int n = listaEspera.size();
        for (int i = 0; i < n; ++i) {
            Jogador key = listaEspera.get(i);
            int j = i;

            while (j >= 0 && listaEspera.get(j).getPontuacaoHabilidade() > key.getPontuacaoHabilidade()) {
                listaEspera.set(j, listaEspera.get(j));

            }
            listaEspera.set(j, key);
        }
    }

    public boolean possuiRoleRepetida(Time time) {
        ArrayList<String> roles = new ArrayList<>();
        for (Jogador jogador : time.getJogadores()) {
            if (roles.contains(jogador.getRole())) {
                return true;
            } else {
                roles.add(jogador.getRole());
            }
        }
        return false;
    }

    public void exibirPartidasEmAndamento() {
        System.out.println("Partidas em andamento:");
        for (Time time : partidasEmAndamento) {
            System.out.println("Time " + time.getId() + ":");
            for (Jogador jogador : time.getJogadores()) {
                System.out.println("ID: " + jogador.getId() + " | Role: " + jogador.getRole()
                        + " | Pontuação de habilidade: " + jogador.getPontuacaoHabilidade());
            }
            System.out.println("------------------");
        }
    }

    public boolean possuiJogador(Jogador jogador) {
        for (Time time : partidasEmAndamento) {
            if (time.getJogadores().contains(jogador)) {
                return true;
            }
        }
        return false;
    }

    public void limparArquivo() {
        try {
            FileWriter writer = new FileWriter("Jogadores.txt", false);
            writer.write("");
            writer.close();
            System.out.println("Arquivo limpo com sucesso!");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao limpar o arquivo: " + e.getMessage());
        }
    }
}
