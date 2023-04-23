public class Jogador {
    private int id;
    private String role;
    private int pontuacaoHabilidade;
    
    
    public Jogador(int id, String role, int pontuacaoHabilidade) {
        this.id = id;
        this.role = role;
        this.pontuacaoHabilidade = pontuacaoHabilidade;
        
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public int getPontuacaoHabilidade() {
        return pontuacaoHabilidade;
    }
    
    public void setPontuacaoHabilidade(int pontuacaoHabilidade) {
        this.pontuacaoHabilidade = pontuacaoHabilidade;
    }
}
