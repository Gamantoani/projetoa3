package projetoa3;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import projetoa3.TelaLogin;


public class TelaCadastro extends javax.swing.JFrame {
    
    private final ArrayList<String> opcoesCadastro = new ArrayList<>(Arrays.asList("Usuário", "Funcionário"));

    public TelaCadastro() {
        initComponents();
        setLocationRelativeTo(null);
        Voltar_btn.addActionListener((ActionEvent e) -> {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
            dispose();
            adicionarItensAoComboBox();
            
        });
        Confirmar_btn.addActionListener((ActionEvent e) -> {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
            String tipo = (String) Tipo_CB.getSelectedItem();
            String nome = NomeTextField.getText();
            String email = EmailTextField.getText();
            String telefone = TelefoneTextField.getText();
            String usuario = UsuarioTextField.getText();
            String senha = SenhaTextField.getText();
            try {
                inserirDados(tipo, nome, email, telefone, usuario, senha);
            } catch (SQLException ex) {
                Logger.getLogger(TelaCadastro.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose(); 
            
          
            
    });
  }
    private void adicionarItensAoComboBox() {
        // Remover todos os itens do JComboBox
        Tipo_CB.removeAllItems();
        
        // Adicionar os itens ao JComboBox
        for (String opcao : opcoesCadastro) {
            Tipo_CB.addItem(opcao);
           
        }
    }
    
    
    private Connection obterConexao() throws SQLException {
        String usuario = "root";
        String senha = "Beteelias25@";
        String url = "jdbc:mysql://localhost:3306/LRGMECH";
        return DriverManager.getConnection(url, usuario, senha);
    }

    private void inserirDados(String tipo, String nome, String email, String telefone, String usuario, String senha) throws SQLException {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = obterConexao();
            String sql = "INSERT INTO usuarios (tipo, nome, email, telefone, usuario, senha) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, tipo);
            stmt.setString(2, nome);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            stmt.setString(5, usuario);
            stmt.setString(6, senha);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao realizar cadastro.");
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
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NomeTextField = new javax.swing.JTextField();
        EmailTextField = new javax.swing.JTextField();
        UsuarioTextField = new javax.swing.JTextField();
        SenhaTextField = new javax.swing.JTextField();
        TelefoneTextField = new javax.swing.JTextField();
        Confirmar_btn = new javax.swing.JButton();
        Voltar_btn = new javax.swing.JButton();
        Tipo_CB = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        NomeTextField.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome"));
        NomeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomeTextFieldActionPerformed(evt);
            }
        });

        EmailTextField.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));
        EmailTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailTextFieldActionPerformed(evt);
            }
        });

        UsuarioTextField.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuario"));
        UsuarioTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuarioTextFieldActionPerformed(evt);
            }
        });

        SenhaTextField.setBorder(javax.swing.BorderFactory.createTitledBorder("Senha"));
        SenhaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SenhaTextFieldActionPerformed(evt);
            }
        });

        TelefoneTextField.setBorder(javax.swing.BorderFactory.createTitledBorder("Telefone"));

        Confirmar_btn.setText("Confirmar Cadastro");

        Voltar_btn.setText("Voltar");

        Tipo_CB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuario", "Funcionario" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EmailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Tipo_CB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(UsuarioTextField)
                                    .addComponent(TelefoneTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                    .addComponent(SenhaTextField)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(Confirmar_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Voltar_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(NomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tipo_CB, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(NomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(EmailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TelefoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(UsuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SenhaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Confirmar_btn)
                    .addComponent(Voltar_btn))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UsuarioTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuarioTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsuarioTextFieldActionPerformed

    private void SenhaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SenhaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SenhaTextFieldActionPerformed

    private void EmailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailTextFieldActionPerformed

    private void NomeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomeTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaCadastro().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Confirmar_btn;
    private javax.swing.JTextField EmailTextField;
    private javax.swing.JTextField NomeTextField;
    private javax.swing.JTextField SenhaTextField;
    private javax.swing.JTextField TelefoneTextField;
    private javax.swing.JComboBox<String> Tipo_CB;
    private javax.swing.JTextField UsuarioTextField;
    private javax.swing.JButton Voltar_btn;
    // End of variables declaration//GEN-END:variables

}

