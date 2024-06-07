package projetoa3;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    // Método para inserir um novo usuário no banco de dados
    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO usuarios (usuario, nome, email, senha, telefone) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getTelefone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário no banco de dados: " + e.getMessage());
        }
    }

    // Método para buscar um usuário pelo seu nome de usuário
    public Usuario buscarPorUsuario(String username) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ?";
        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirUsuario(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por nome de usuário: " + e.getMessage());
        }
        return null;
    }

    // Método auxiliar para construir um objeto Usuario a partir do ResultSet
    private Usuario construirUsuario(ResultSet rs) throws SQLException {
        return null;
        //return new Usuario(
          //  rs.getString("usuario"),
            //rs.getString("nome"),
            //rs.getString("email"),
            //rs.getString("senha"),
            //rs.getString("telefone")
        //);
    }

    // Outros métodos CRUD podem ser adicionados conforme necessário
}