package projetoa3;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static projetoa3.ConexaoBanco.obterConexao;

/**
 *
 * @author lucas
 */
public class TelaADM extends javax.swing.JFrame {
    private List<Usuario> usuarios = new ArrayList<>();

    private final ADM adm;

    /**
     * Creates new form TelaADM
     * @param adm
     */
    public TelaADM(String tipoUsuario,String usuarioadm) throws SQLException {
         initComponents();
        setLocationRelativeTo(null);
        this.adm = new ADM(usuarioadm, tipoUsuario);
        CarregarUsuarios();
        
        
        
        
    
    CriarUsuario_btn.addActionListener((ActionEvent e) -> {
    //System.out.println(qtdProjeto);
        String tipo = (String) Tipo_CB.getSelectedItem();
        String usuario = (String) UsuarioTextField.getText();
        String nome = NomeTextField.getText();
        String senha = SenhaTextField.getText();
        String email = EmailTextField.getText();
        String telefone = TelefoneTextField.getText();
       
        
        
        
        System.out.println("AAAAA");
        // Chamar o método para adicionar o novo usuario ao banco de dados
        NovoUsuario( tipo, usuario, nome, senha, email, telefone);
    });
    AtualizarUsuario_btn.addActionListener ((ActionEvent e) -> {
        int i = TabelaUsuarios_TB.getSelectedRow();
        TabelaUsuarios_TB.getValueAt(i, 0);
        int id = (int) TabelaUsuarios_TB.getValueAt(i, 0);
        String tipo = (String) Tipo_CB.getSelectedItem();
        String usuario = (String) UsuarioTextField.getText();
        String nome = NomeTextField.getText();
        String senha = SenhaTextField.getText();
        String email = EmailTextField.getText();
        String telefone = TelefoneTextField.getText();
        AtualizarUsuario(id , tipo, usuario, nome, senha, email, telefone);
                
    });
    ExcluirUsuario_btn.addActionListener((ActionEvent e) -> {
            int i = TabelaUsuarios_TB.getSelectedRow();
            int id = (int) TabelaUsuarios_TB.getValueAt(i, 0);
            DeletarUsuario(id);
            
        });
    }
    
    
    
     private void adicionarUsuario(Usuario usuario) {
         
        usuarios.add(usuario);
        DefaultTableModel model = (DefaultTableModel) TabelaUsuarios_TB.getModel();
        model.addRow(new Object[]{
            usuario.getId(),
            usuario.getUsuario(),
            usuario.getTipo(),
            usuario.getNome(),
            usuario.getSenha(),
            usuario.getEmail(),
            usuario.getTelefone()
            
        });
    }
     
     private void AtualizarUsuario(int id , String tipo, String usuario, String nome, String senha, String email, String telefone) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = obterConexao();
            //System.out.println("123");
            String sql = "UPDATE usuarios SET tipo= ?, nome= ? , email= ? , senha= ? , telefone= ? , usuario= ? WHERE id= ? ";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, tipo);
            stmt.setString(2, nome);
            stmt.setString(3, email);
            stmt.setString(4, senha);
            stmt.setString(5, telefone);
            stmt.setString(6, usuario);
            stmt.setInt(7, id);
            
           

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(this, "Usuario atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possivel atualizar.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar dados do Usuario no banco de dados: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Erro ao fechar PreparedStatement: " + e.getMessage());
                }
            }
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }

    }
    
     private void DeletarUsuario(int id) {
        Connection conexao = null;
        PreparedStatement stmt = null;
         try {
            conexao = obterConexao();
            //System.out.println("123");
            String sql = "DELETE FROM usuarios WHERE id =  ? ";
            stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, id);
            
           

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(this, "Usuario excluido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possivel excluir.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao verificar dados do Usuario no banco de dados: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Erro ao fechar PreparedStatement: " + e.getMessage());
                }
            }
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
        
    }
     
     private Connection obterConexao() throws SQLException {
        String usuario = "root";
        String senha = "Beteelias25@";
        String url = "jdbc:mysql://localhost:3306/LRGMECH";
        return DriverManager.getConnection(url, usuario, senha);
    }
    
    
     private void NovoUsuario(String tipo, String nome, String senha, String email, String telefone, String usuario) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = obterConexao();
            System.out.println(tipo + " " + nome + " " + senha + " " + email + " " + telefone + " " + usuario);
            String sql = "INSERT INTO usuarios (tipo, nome, senha, email, telefone, usuario) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, tipo);
            stmt.setString(2, nome);
            stmt.setString(3, senha);
            stmt.setString(4, email);
            stmt.setString(5, telefone);
            stmt.setString(6, usuario);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(this, "Usuario cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Não há usuarios cadastrados.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao inserir dados do usuario no banco de dados: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Erro ao fechar PreparedStatement: " + e.getMessage());
                }
            }
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }

    }
    

    private TelaADM() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // Lógica para carregar os usuários na tabela

    private void criarNovoUsuario() throws SQLException {
        // Lógica para criar um novo usuário
        Usuario novoUsuario = new Usuario(/* parâmetros do novo usuário */);
        adm.cadastroUsuario(novoUsuario);
        CarregarUsuarios(); // Recarrega a tabela após adicionar o novo usuário
    }
    private void excluirUsuario() throws SQLException {
        // Lógica para excluir um usuário selecionado na tabela
        int linhaSelecionada = TabelaUsuarios_TB.getSelectedRow();
        if (linhaSelecionada != -1) {
            String usuario = (String) TabelaUsuarios_TB.getValueAt(linhaSelecionada, 0);
            adm.exclusaoUsuario(usuario);
            CarregarUsuarios(); // Recarrega a tabela após excluir o usuário
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para excluir.");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CriarUsuario_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaUsuarios_TB = new javax.swing.JTable();
        ExcluirUsuario_btn = new javax.swing.JButton();
        AtualizarUsuario_btn = new javax.swing.JButton();
        UsuarioTextField = new javax.swing.JTextField();
        Usuario_LB = new javax.swing.JLabel();
        Nome_LB = new javax.swing.JLabel();
        NomeTextField = new javax.swing.JTextField();
        Senha_LB = new javax.swing.JLabel();
        SenhaTextField = new javax.swing.JTextField();
        Email_LB = new javax.swing.JLabel();
        EmailTextField = new javax.swing.JTextField();
        Telefone_LB = new javax.swing.JLabel();
        TelefoneTextField = new javax.swing.JTextField();
        Tipo_LB = new javax.swing.JLabel();
        Tipo_CB = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        CriarUsuario_btn.setText("Criar Novo Usuario");

        TabelaUsuarios_TB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Banco", "Tipo", "Usuario", "Nome", "Senha", "Email", "Telefone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TabelaUsuarios_TB);
        if (TabelaUsuarios_TB.getColumnModel().getColumnCount() > 0) {
            TabelaUsuarios_TB.getColumnModel().getColumn(1).setResizable(false);
            TabelaUsuarios_TB.getColumnModel().getColumn(2).setResizable(false);
            TabelaUsuarios_TB.getColumnModel().getColumn(3).setResizable(false);
            TabelaUsuarios_TB.getColumnModel().getColumn(4).setResizable(false);
            TabelaUsuarios_TB.getColumnModel().getColumn(5).setResizable(false);
            TabelaUsuarios_TB.getColumnModel().getColumn(6).setResizable(false);
        }

        ExcluirUsuario_btn.setText("Excluir Usuario");

        AtualizarUsuario_btn.setText("Atualizar Usuario");

        Usuario_LB.setText("Usuario");

        Nome_LB.setText("Nome");

        Senha_LB.setText("Senha");

        Email_LB.setText("Email");

        Telefone_LB.setText("Telefone");

        Tipo_LB.setText("Tipo");

        Tipo_CB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuario", "Funcionario" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CriarUsuario_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Usuario_LB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Nome_LB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Senha_LB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Email_LB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Telefone_LB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Tipo_LB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ExcluirUsuario_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AtualizarUsuario_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Tipo_CB, javax.swing.GroupLayout.Alignment.LEADING, 0, 270, Short.MAX_VALUE)
                        .addComponent(TelefoneTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(EmailTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(SenhaTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(NomeTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(UsuarioTextField, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Tipo_LB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Tipo_CB, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Usuario_LB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UsuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Nome_LB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Senha_LB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SenhaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Email_LB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EmailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Telefone_LB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TelefoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AtualizarUsuario_btn)
                            .addComponent(ExcluirUsuario_btn)
                            .addComponent(CriarUsuario_btn))))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaADM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaADM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaADM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaADM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaADM().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AtualizarUsuario_btn;
    private javax.swing.JButton CriarUsuario_btn;
    private javax.swing.JTextField EmailTextField;
    private javax.swing.JLabel Email_LB;
    private javax.swing.JButton ExcluirUsuario_btn;
    private javax.swing.JTextField NomeTextField;
    private javax.swing.JLabel Nome_LB;
    private javax.swing.JTextField SenhaTextField;
    private javax.swing.JLabel Senha_LB;
    private javax.swing.JTable TabelaUsuarios_TB;
    private javax.swing.JTextField TelefoneTextField;
    private javax.swing.JLabel Telefone_LB;
    private javax.swing.JComboBox<String> Tipo_CB;
    private javax.swing.JLabel Tipo_LB;
    private javax.swing.JTextField UsuarioTextField;
    private javax.swing.JLabel Usuario_LB;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    
    private void CarregarUsuarios() throws SQLException {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            
            conexao = obterConexao();
            //System.out.println("123");

            
                String sql = "SELECT * FROM usuarios";
                //System.out.println("123");
                stmt = conexao.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery();
                
                
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String tipo = resultSet.getString("tipo");
                    String nome = resultSet.getString("nome");
                    String senha = resultSet.getString("senha");
                    String email = resultSet.getString("email");
                    String telefone = resultSet.getString("telefone");
                    String usuario = resultSet.getString("usuario");

                    System.out.println("ID: " + id + ", tipo: " + tipo + ", nome: "
                            + nome + ", senha " + senha + ",email" + email + ",telefone" + telefone + ",usuario" + usuario);
                    
                    Usuario user = new Usuario(id, tipo, nome, senha, email, telefone, usuario);
                    adicionarUsuario(user);
                    
                }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao inserir dados no banco de dados: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Erro ao fechar PreparedStatement: " + e.getMessage());
                }
            }
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
        
    }
    }

