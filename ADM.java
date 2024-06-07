package projetoa3;
public class ADM {
    
    private String usuario;
    private String tipo;

    public ADM(String usuario, String tipo) {
        this.usuario = usuario;
        this.tipo = tipo;
        
    }

    // Métodos específicos para administradores

    public void cadastroUsuario(Usuario novoUsuario) {
        // Lógica para cadastrar um novo usuário
    }

    public void alteracaoUsuario(Usuario usuario) {
        // Lógica para alterar os dados de um usuário existente
    }

    public Usuario consultaUsuario(String usuario) {
        // Lógica para consultar um usuário pelo nome de usuário
        return null; // Retorna null se o usuário não for encontrado
    }

    public void exclusaoUsuario(String usuario) {
        // Lógica para excluir um usuário pelo nome de usuário
    }

    // Métodos getters e setters

    
    public String getUsuario() {
        return this.usuario;
    }

    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
    public String getTipo(){
        return this.tipo;
    }
   public void setTipo(String tipo){
       this.tipo = tipo;
   }

    }

