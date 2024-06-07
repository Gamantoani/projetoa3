package projetoa3;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

public class TelaUsuario extends javax.swing.JFrame {

    private List<Projeto> projetos = new ArrayList<>();
    private int qtdProjeto = 0;
    
    

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario(String tipo, String usuario) throws SQLException {
        initComponents();
        // Centralizar a janela
        setLocationRelativeTo(null);
        SelecionarProjeto(tipo, usuario);
        AtualizarProjeto_btn.addActionListener((ActionEvent e) -> {
            int i = tabelaprojetos.getSelectedRow();
        tabelaprojetos.getValueAt(i, 6);
            //System.out.println(tabelaprojetos.getValueAt(i, 6));
        int id = (int) tabelaprojetos.getValueAt(i, 6);
        String codigo = (String) CodigoOds_CB.getSelectedItem();
        String descricao = DescriçaoTextField.getText();
        String nomeUsuario = NomeUsuarioTextField.getText();
        String telefone = TelefoneTextField.getText();
        String dataCriacao = DataDeCriaçaoTextField.getText();
        String status = (String) Status_CB.getSelectedItem();
        AtualizarProjeto( codigo, descricao, nomeUsuario, telefone, dataCriacao, status, id);
        
        
         });
        ExcluirProjeto_btn.addActionListener((ActionEvent e) -> {
            int i = tabelaprojetos.getSelectedRow();
            int id = (int) tabelaprojetos.getValueAt(i, 6);
            DeletarProjeto(id);
            
        });
        

       CriarProjeto_btn.addActionListener((ActionEvent e) -> {
    //System.out.println(qtdProjeto);
    if (this.qtdProjeto < 5) {
        String codigo = (String) CodigoOds_CB.getSelectedItem();
        String descricao = DescriçaoTextField.getText();
        String nomeUsuario = NomeUsuarioTextField.getText();
        String telefone = TelefoneTextField.getText();
        String dataCriacao = DataDeCriaçaoTextField.getText();
        String status = (String) Status_CB.getSelectedItem();
        
        
        System.out.println("AAAAA");
        // Chamar o método para adicionar o novo projeto ao banco de dados
        NovoProjeto(codigo, descricao, nomeUsuario, telefone, dataCriacao, status);
        
        
    } else { 
        JOptionPane.showMessageDialog(this, "Chegou ao limite de 5 projetos");
    }
});
      
}
    private void DeletarProjeto(int id) {
        Connection conexao = null;
        PreparedStatement stmt = null;
         try {
            conexao = obterConexao();
            //System.out.println("123");
            String sql = "DELETE FROM projeto WHERE id =  ? ";
            stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, id);
            
           

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(this, "Projeto excluido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possivel excluir.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao verificar dados no banco de dados: " + e.getMessage());
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
    private void AtualizarProjeto(String codigo, String descriçao, String nomeusuario, String telefone, String datadecriaçao, String status, int id) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = obterConexao();
            //System.out.println("123");
            String sql = "UPDATE projeto SET codigo= ?, descriçao= ? , usuario= ? , telefone= ? , datadecriaçao= ? , statusprojeto= ? WHERE id= ? ";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, codigo);
            stmt.setString(2, descriçao);
            stmt.setString(3, nomeusuario);
            stmt.setString(4, telefone);
            stmt.setString(5, datadecriaçao);
            stmt.setString(6, status);
            stmt.setInt(7, id);
            
           

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(this, "Projeto atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possivel atualizar.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar dados no banco de dados: " + e.getMessage());
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
   
    

    private void NovoProjeto(String codigo, String descriçao, String nomeusuario, String telefone, String datacriaçao, String status) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = obterConexao();
            //System.out.println("123");
            String sql = "INSERT INTO projeto (codigo, descriçao, usuario, telefone, datadecriaçao, statusprojeto) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, codigo);
            stmt.setString(2, descriçao);
            stmt.setString(3, nomeusuario);
            stmt.setString(4, telefone);
            stmt.setString(5, datacriaçao);
            stmt.setString(6, status);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(this, "Projeto cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Não há projetos cadastrados.");
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

    private void adicionarProjeto(Projeto projeto) {
        projetos.add(projeto);
        DefaultTableModel model = (DefaultTableModel) tabelaprojetos.getModel();
        model.addRow(new Object[]{
            projeto.getCodigo(),
            projeto.getDescricao(),
            projeto.getNomeUsuario(),
            projeto.getTelefone(),
            projeto.getDataCriacao(),
            projeto.getStatus(),
            projeto.getId()
        });
    }

    private Connection obterConexao() throws SQLException {
        String usuario = "root";
        String senha = "Beteelias25@";
        String url = "jdbc:mysql://localhost:3306/LRGMECH";
        return DriverManager.getConnection(url, usuario, senha);
    }

    private void SelecionarProjeto(String tipo, String usuario) throws SQLException {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = obterConexao();
            //System.out.println("123");

            if (tipo.equals("usuario")) {
                String sql = "SELECT * FROM projeto WHERE usuario = ?";
                //System.out.println("123");
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, usuario);
                ResultSet resultSet = stmt.executeQuery();
                //int size = 0;
                //if (resultSet != null) {
                    //resultSet.last();    // moves cursor to the last row
                    //size = resultSet.getRow(); // get row id 
                //}
                
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String codigo = resultSet.getString("codigo");
                    String descriçao = resultSet.getString("descriçao");
                    String nomeusuario = resultSet.getString("usuario");
                    String telefone = resultSet.getString("telefone");
                    String datadecriaçao = resultSet.getString("datadecriaçao");
                    String statusprojeto = resultSet.getString("statusprojeto");

                    System.out.println("ID: " + id + ", codigo: " + codigo + ", descriçao: "
                            + descriçao + ", nomeusuario " + nomeusuario + ",telefone" + telefone + ",datadecriaçao" + datadecriaçao + ",statusprojeto" + statusprojeto);
                    Projeto prj = new Projeto(id, codigo, descriçao, nomeusuario, telefone, datadecriaçao, statusprojeto);
                    adicionarProjeto(prj);
                    this.qtdProjeto = this.qtdProjeto + 1;
                }

            } else {
                String sql = "SELECT * FROM projeto";
                stmt = conexao.prepareStatement(sql);

            }

            //int linhasAfetadas = stmt.executeUpdate();
            //if (linhasAfetadas > 0) {
            // JOptionPane.showMessageDialog(this, "Projeto cadastrado com sucesso!");
            //} else {
            // JOptionPane.showMessageDialog(this, "Não há projetos cadastrados.");
            //}
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

        CriarProjeto_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaprojetos = new javax.swing.JTable();
        MeusProjetos_LB = new javax.swing.JLabel();
        DescriçaoTextField = new javax.swing.JTextField();
        NomeUsuarioTextField = new javax.swing.JTextField();
        TelefoneTextField = new javax.swing.JTextField();
        DataDeCriaçaoTextField = new javax.swing.JTextField();
        CodigoOds_CB = new javax.swing.JComboBox<>();
        Status_CB = new javax.swing.JComboBox<>();
        CodigoOds_LB = new javax.swing.JLabel();
        Descriçao_LB = new javax.swing.JLabel();
        NomeUsuario_LB = new javax.swing.JLabel();
        Telefone_LB = new javax.swing.JLabel();
        DataDeCriaçao_LB = new javax.swing.JLabel();
        Status_LB = new javax.swing.JLabel();
        AtualizarProjeto_btn = new javax.swing.JButton();
        ExcluirProjeto_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        CriarProjeto_btn.setText("Criar Novo Projeto");
        CriarProjeto_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CriarProjeto_btnActionPerformed(evt);
            }
        });

        tabelaprojetos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        tabelaprojetos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabelaprojetos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo/ODS", "Descrição", "Nome Usuario", "Telefone", "Data de criação", "Status", "ID Banco"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaprojetos.setRowHeight(50);
        jScrollPane1.setViewportView(tabelaprojetos);
        if (tabelaprojetos.getColumnModel().getColumnCount() > 0) {
            tabelaprojetos.getColumnModel().getColumn(0).setResizable(false);
            tabelaprojetos.getColumnModel().getColumn(1).setResizable(false);
            tabelaprojetos.getColumnModel().getColumn(2).setResizable(false);
            tabelaprojetos.getColumnModel().getColumn(3).setResizable(false);
            tabelaprojetos.getColumnModel().getColumn(4).setResizable(false);
            tabelaprojetos.getColumnModel().getColumn(5).setResizable(false);
            tabelaprojetos.getColumnModel().getColumn(6).setResizable(false);
        }

        MeusProjetos_LB.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        MeusProjetos_LB.setText("Meus Projetos");

        TelefoneTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelefoneTextFieldActionPerformed(evt);
            }
        });

        CodigoOds_CB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "\"1- Erradicação da Pobreza\"", "\"2- Fome Zero e Agricultura Sustentável\"", "\"3- Saúde e Bem-Estar\"", "\"4- Educação de Qualidade\"", "\"5- Igualdade de Gênero\"", "\"6- Água Potável e Saneamento\"", "\"7- Energia Limpa e Acessível\"", "\"8- Trabalho Decente e Crescimento Econômico\"", "\"9- Indústria  Inovação e Infraestrutura\"", "\"10- Redução das Desigualdades\"", "\"11- Cidades e Comunidades Sustentáveis\"", "\"12- Consumo e Produção Responsáveis\"", "\"13- Ação Contra a Mudança Global do Clima\"", "\"14- Vida na Água\"", "\"15- Vida Terrestre\"", "\"16- Paz  Justiça e Instituições Eficazes\"", "\"17- Parcerias e Meios de Implementação\"" }));
        CodigoOds_CB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodigoOds_CBActionPerformed(evt);
            }
        });

        Status_CB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Em Progresso" }));

        CodigoOds_LB.setText("Codigo/ODS");

        Descriçao_LB.setText("Descriçao");

        NomeUsuario_LB.setText("NomeUsuario");

        Telefone_LB.setText("Telefone");

        DataDeCriaçao_LB.setText("DataDeCriaçao");

        Status_LB.setText("Status");

        AtualizarProjeto_btn.setText("Atualizar Projeto");

        ExcluirProjeto_btn.setText("Excluir Projeto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MeusProjetos_LB, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(CodigoOds_LB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CodigoOds_CB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DataDeCriaçao_LB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NomeUsuario_LB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Descriçao_LB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Telefone_LB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(DescriçaoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(NomeUsuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TelefoneTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(DataDeCriaçaoTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CriarProjeto_btn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(Status_LB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Status_CB, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AtualizarProjeto_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ExcluirProjeto_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(MeusProjetos_LB, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CodigoOds_LB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CodigoOds_CB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Descriçao_LB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DescriçaoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NomeUsuario_LB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NomeUsuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Telefone_LB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TelefoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DataDeCriaçao_LB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DataDeCriaçaoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Status_CB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Status_LB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CriarProjeto_btn)
                            .addComponent(AtualizarProjeto_btn)
                            .addComponent(ExcluirProjeto_btn))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TelefoneTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TelefoneTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TelefoneTextFieldActionPerformed

    private void CodigoOds_CBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodigoOds_CBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodigoOds_CBActionPerformed

    private void CriarProjeto_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CriarProjeto_btnActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_CriarProjeto_btnActionPerformed

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
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AtualizarProjeto_btn;
    private javax.swing.JComboBox<String> CodigoOds_CB;
    private javax.swing.JLabel CodigoOds_LB;
    private javax.swing.JButton CriarProjeto_btn;
    private javax.swing.JTextField DataDeCriaçaoTextField;
    private javax.swing.JLabel DataDeCriaçao_LB;
    private javax.swing.JTextField DescriçaoTextField;
    private javax.swing.JLabel Descriçao_LB;
    private javax.swing.JButton ExcluirProjeto_btn;
    private javax.swing.JLabel MeusProjetos_LB;
    private javax.swing.JTextField NomeUsuarioTextField;
    private javax.swing.JLabel NomeUsuario_LB;
    private javax.swing.JComboBox<String> Status_CB;
    private javax.swing.JLabel Status_LB;
    private javax.swing.JTextField TelefoneTextField;
    private javax.swing.JLabel Telefone_LB;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaprojetos;
    // End of variables declaration//GEN-END:variables
}
