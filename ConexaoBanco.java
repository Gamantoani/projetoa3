package projetoa3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Classe para estabelecer uma conexão com o banco de dados MySQL.
 */
public class ConexaoBanco {

    static Connection obterConexao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private final String usuario = "root";
    private final String senha = "Beteelias25@";
    private final String host = "localhost";
    private final String porta = "3306";
    private final String bd = "LRGMECH";
    
    /**
     * Método para obter uma conexão com o banco de dados.
     * @return Conexão com o banco de dados ou null em caso de falha.
     */
    public Connection obtemConexao() throws SQLException {
        String url = "jdbc:mysql://" + host + ":" + porta + "/" + bd + "?serverTimezone=UTC";
        return DriverManager.getConnection(url, usuario, senha);
}    

public static void main(String[] args) {
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        Connection conexao = null;
        try {
            conexao = conexaoBanco.obtemConexao();
            if (conexao != null) {
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
                // Aqui você pode adicionar lógica adicional, se necessário
            } else {
                System.out.println("Falha ao estabelecer conexão com o banco de dados.");
                // Se necessário, adicione lógica para lidar com a falha na conexão
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
                }
            }
        }
    }

    public String autenticar(String usuario, String senha) {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String tipoConta = null;
        
       
        try {
        // Estabelecer conexão com o banco de dados
        conexao = obtemConexao();
        
        // Consulta SQL para verificar o usuário e senha fornecidos
        String sql = "SELECT tipo_conta FROM usuarios WHERE usuario = ? AND senha = ?";
        
        // Preparar a consulta
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, usuario);
        stmt.setString(2, senha);
        
        // Executar a consulta
        rs = stmt.executeQuery();
        
        // Verificar se encontrou algum registro correspondente
        if (rs.next()) {
            // Obter o tipo de conta do resultado da consulta
            tipoConta = rs.getString("tipo_conta");
        }
    } catch (SQLException e) {
        System.err.println("Erro ao autenticar usuário: " + e.getMessage());
    } finally {
        // Fechar os recursos
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar ResultSet: " + e.getMessage());
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage());
            }
        }
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // Retornar o tipo de conta (ou null se a autenticação falhar)
    return tipoConta;
    }
}
 

    

    
    
    
    

        

    

        
    
    

