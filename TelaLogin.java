package projetoa3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TelaLogin extends javax.swing.JFrame {

    public TelaLogin() {
        initComponents();
        setLocationRelativeTo(null);
        Cadastro_btn.addActionListener((ActionEvent e) -> {
            TelaCadastro telaCadastros = new TelaCadastro();
            telaCadastros.setVisible(true);
            
        });
        dispose();
        Logar_btn.addActionListener((ActionEvent e) -> {
            try {
                realizarLogin();
            } catch (SQLException ex) {
                Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        dispose();
    }

     private String autenticar(String usuario, String senha) {
    String tipoConta = null;
    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/LRGMECH?useSSL=false", "root", "Beteelias25@");
        String sql = "SELECT tipo FROM usuarios WHERE usuario = ? AND senha = ?";
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, usuario);
        stmt.setString(2, senha);
        rs = stmt.executeQuery();
        
        

        
        if (rs.next()) {
          
            tipoConta = rs.getString("tipo");
            System.out.println(tipoConta);
        }
    } catch (SQLException e) {
        System.err.println("Erro ao autenticar usuário: " + e.getMessage());
    } finally {
        // Fechar as conexões e recursos
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

    return tipoConta;
}
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usuarioTextField = new javax.swing.JTextField();
        Cadastro_btn = new javax.swing.JButton();
        Logar_btn = new javax.swing.JButton();
        Sair_btn = new javax.swing.JButton();
        Login_LB = new javax.swing.JLabel();
        Senha_LB = new javax.swing.JLabel();
        senhaPasswordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        usuarioTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usuarioTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        usuarioTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioTextFieldActionPerformed(evt);
            }
        });

        Cadastro_btn.setText("Cadastro");
        Cadastro_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cadastro_btnActionPerformed(evt);
            }
        });

        Logar_btn.setText("Logar");

        Sair_btn.setText("Sair");
        Sair_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sair_btnActionPerformed(evt);
            }
        });

        Login_LB.setText("Login");

        Senha_LB.setText("Senha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(usuarioTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(Cadastro_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Logar_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Sair_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Login_LB, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Senha_LB, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(senhaPasswordField))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(Login_LB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Senha_LB)
                .addGap(18, 18, 18)
                .addComponent(senhaPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Cadastro_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(Logar_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Sair_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void usuarioTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioTextFieldActionPerformed

    private void Cadastro_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cadastro_btnActionPerformed
        // TODO add your handling code here:
        // Abrir a tela de cadastro
        TelaCadastro telaCadastros = new TelaCadastro();
        telaCadastros.setVisible(true);
        // Fechar a tela de login
        dispose();
        
    }//GEN-LAST:event_Cadastro_btnActionPerformed

    private void Sair_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sair_btnActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_Sair_btnActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
        
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cadastro_btn;
    private javax.swing.JButton Logar_btn;
    private javax.swing.JLabel Login_LB;
    private javax.swing.JButton Sair_btn;
    private javax.swing.JLabel Senha_LB;
    private javax.swing.JPasswordField senhaPasswordField;
    private javax.swing.JTextField usuarioTextField;
    // End of variables declaration//GEN-END:variables

    private void realizarLogin() throws SQLException {
        String usuario = usuarioTextField.getText();
    String senha = new String(senhaPasswordField.getPassword());
    
    String tipoUsuario = autenticar(usuario, senha);
        
    if (tipoUsuario != null) {
        abrirTelaCorrespondente(tipoUsuario, usuario);
    } else {
        JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos");
    }
}

    private void abrirTelaCorrespondente(String tipoUsuario, String usuario) throws SQLException {
        switch (tipoUsuario) {
        case "usuario" -> {
            // Abrir a tela do usuário comum
            TelaUsuario telaUsuario = new TelaUsuario(tipoUsuario, usuario);
            telaUsuario.setVisible(true);
            }
        case "funcionario" -> {
            // Abrir a tela do funcionário
            TelaFuncionario telaFuncionario = new TelaFuncionario();
            telaFuncionario.setVisible(true);
            }
        case "adm" -> {
                
            // Abrir a tela do administrador
            TelaADM telaADM = new TelaADM(tipoUsuario, usuario);
            telaADM.setVisible(true);
            }

        default -> // Tipo de usuário inválido
            JOptionPane.showMessageDialog(this, "Tipo de usuário inválido");
    }
    // Fechar a tela de login após abrir a tela correspondente
    dispose();
}
}



  
