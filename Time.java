import java.util.ArrayList;

public class Time {
    private int id;
    private ArrayList<Jogador> jogadores;

    public Time(int id) {
        this.id = id;
        jogadores = new ArrayList<>();
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public int calcularHabilidade() {
        int habilidade = 0;
        for (Jogador jogador : jogadores) {
            habilidade += jogador.getPontuacaoHabilidade();
        }
        return habilidade;
    }

    public int calcularPontuacaoTotal() {
        int pontuacaoTotal = 0;
        for (Jogador jogador : jogadores) {
            pontuacaoTotal += jogador.getPontuacaoHabilidade();
        }
        return pontuacaoTotal;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public boolean possuiRoleRepetida(Jogador jogador) {
        for (Jogador j : jogadores) {
            if (j.getRole().equals(jogador.getRole())) {
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        String str = "";
        for (Jogador jogador : jogadores) {
            str += jogador.getId() + " (" + jogador.getRole() + ") ";
        }
        return str;
    }
}