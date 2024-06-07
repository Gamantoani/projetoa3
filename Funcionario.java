package projetoa3;

import java.util.List;

public class Funcionario extends Usuario {
    
    public Funcionario(int id, String tipo, String nome, String email, String senha, String telefone, String usuario) {
        super(id, tipo, nome, email, senha, telefone, usuario);
    }

    // Métodos específicos para funcionários

    public void atualizacaoProjeto(Projeto projeto) {
        // Lógica para atualizar um projeto, dependendo dos requisitos
    }

    /**
     *
     * @param codigo
     * @return
     */
    
    public Projeto consultaProjeto(String codigo) {
        List<Projeto> projetos = super.getProjetos();
        for (Projeto projeto : projetos) {
            if (projeto.getCodigo().equals(codigo) ) {
                return projeto;
            }
        }
        return null; // Retorna null se o projeto não for encontrado
    }
}
