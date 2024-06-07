package projetoa3;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String tipo;

    
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String usuario;
    private final List<Projeto> projetos;

    public Usuario(int ID, String tipo, String nome, String email, String senha, String telefone, String usuario) {
        this.id = ID;
        this.tipo = tipo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.usuario = usuario;
        this.projetos = new ArrayList<>();
    }

    public Usuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters e Setters para cada atributo
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

   

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
     public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    // Métodos de manipulação de projetos

    public void cadastroProjeto(Projeto projeto) {
        projetos.add(projeto);
    }

    public Projeto consultaProjeto(String codigo) {
        for (Projeto projeto : projetos) {
            if (projeto.getCodigo().equals(codigo)) {
                return projeto;
            }
        }
        return null; // Retorna null se o projeto não for encontrado
    }

    public void alteracaoProjeto(Projeto projeto) {
        // Lógica para alterar um projeto, dependendo dos requisitos
    }

    public void exclusaoProjeto(String codigo) {
        projetos.removeIf(projeto -> projeto.getCodigo() == codigo);
    }
}

