/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.innovative.telas;

import java.text.DateFormat;
import java.util.Date;
import java.sql.*;
import br.com.innovative.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author queir
 */
public class TelaUser extends javax.swing.JFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaUser
     */
    public TelaUser() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    // Metodo para cadastrar Alunos
    private void adicionar() {
        String sql = "insert into tbalunos (nomealu,endalu,fonealu,emailalu,ra,plano,senha,cpf)values(?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtAlunoNome.getText());
            pst.setString(2, txtAlunoEndereco.getText());
            pst.setString(3, txtAlunoFone.getText());
            pst.setString(4, txtAlunoEmail.getText());
            pst.setString(5, txtAlunoRA.getText());
            pst.setString(6, txtAlunoPlano.getText());
            pst.setString(7, txtAlunoSenha.getText());
            pst.setString(8, txtAlunoCPF.getText());
        
            //Validação dos campos obrigatórios
            if ((txtAlunoNome.getText().isEmpty()) || (txtAlunoFone.getText().isEmpty()) || (txtAlunoEndereco.getText().isEmpty()) || (txtAlunoRA.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                // A linha abaixo atualiza a tabela usuarios com os dados do formularios:
                // A estrutura abaixo é usada para confimrar a inserção dos dados a tabela:
                int adicionado = pst.executeUpdate();
                // A linha abaixo serve de apoio ao entendimento da lógica:
                //System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Aluno adicionado com sucesso");
                    // As linhas abaixo limpam os campos:
                    txtAlunoNome.setText(null);
                    txtAlunoEndereco.setText(null);
                    txtAlunoFone.setText(null);
                    txtAlunoEmail.setText(null);
                    txtAlunoRA.setText(null);
                    txtAlunoPlano.setText(null);
                    txtAlunoSenha.setText(null);
                    txtAlunoCPF.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Método de pesquisar Alunos:
    private void pesquisar_alunos() {
        String sql = "select*from tbalunos where nomealu like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //Passando o conteúdo da caixa para ?
            //atenção ao "%" -> continuação da String sql
            pst.setString(1, txtAlunoPesquisar.getText()+ "%");
            rs=pst.executeQuery();
            //a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            tblAlunos.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // Método para editar os campos do formulario com o conteudo da tabela:
    public void setar_campos(){
        int setar = tblAlunos.getSelectedRow();
        txtIdAluno.setText(tblAlunos.getModel().getValueAt(setar, 0).toString());
        txtAlunoNome.setText(tblAlunos.getModel().getValueAt(setar, 1).toString());
        txtAlunoEndereco.setText(tblAlunos.getModel().getValueAt(setar, 2).toString());
        txtAlunoFone.setText(tblAlunos.getModel().getValueAt(setar, 3).toString());
        txtAlunoEmail.setText(tblAlunos.getModel().getValueAt(setar, 4).toString());
        txtAlunoRA.setText(tblAlunos.getModel().getValueAt(setar, 5).toString());
        txtAlunoPlano.setText(tblAlunos.getModel().getValueAt(setar, 6).toString());
        txtAlunoSenha.setText(tblAlunos.getModel().getValueAt(setar, 7).toString());
        txtAlunoCPF.setText(tblAlunos.getModel().getValueAt(setar, 8).toString());
        // A alinha abaixo bloquea adicionar:
        btAlunoAdicionar.setEnabled(false);
    }
    
    //Criando o metodo para alterar dados do usuário
    private void alterar(){
        String sql = "update tbalunos set nomealu=?,endalu=?,fonealu=?,emailalu=?,ra=?,plano=?,senha=?, cpf=? where idalunos=?";
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, txtAlunoNome.getText());
            pst.setString(2, txtAlunoEndereco.getText());
            pst.setString(3, txtAlunoFone.getText());
            pst.setString(4, txtAlunoEmail.getText());
            pst.setString(5, txtAlunoRA.getText());
            pst.setString(6, txtAlunoPlano.getText());
            pst.setString(7, txtAlunoSenha.getText());
            pst.setString(8, txtAlunoCPF.getText());
            // Pega o IdAluno da grid aqui.
            int setar = tblAlunos.getSelectedRow();
            pst.setString(9, (tblAlunos.getModel().getValueAt(setar, 0).toString())); 
            
            if ((txtAlunoNome.getText().isEmpty()) || (txtAlunoFone.getText().isEmpty()) || (txtAlunoEndereco.getText().isEmpty()) || (txtAlunoRA.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                // A linha abaixo atualiza a tabela alunos com os dados do formularios:
                // A estrutura abaixo é usada para confimrar a inserção dos dados a tabela:
                int adicionado = pst.executeUpdate();
                // A linha abaixo serve de apoio ao entendimento da lógica:
                //System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do Aluno alterado com sucesso");
                    // As linhas abaixo limpam os campos:
                    txtAlunoNome.setText(null);
                    txtAlunoEndereco.setText(null);
                    txtAlunoFone.setText(null);
                    txtAlunoEmail.setText(null);
                    txtAlunoRA.setText(null);
                    txtAlunoPlano.setText(null);
                    txtAlunoSenha.setText(null);
                    btAlunoAdicionar.setEnabled(true);
                }
            }
        }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // Metodo responsavel pela remoção de aluno
    private void remover(){
    //A estrutura abaixo confirma a remoção do aluno
    int confirma=JOptionPane.showConfirmDialog(null,"Tem certeza que deseja remover este aluno","Atenção",JOptionPane.YES_NO_OPTION);
    if(confirma==JOptionPane.YES_OPTION){
        String sql="delete from tbalunos where idalunos=?";
        
        try {
          pst=conexao.prepareStatement(sql);
          pst.setString(1,txtIdAluno.getText());
          int apagado = pst.executeUpdate();
          if(apagado > 0){
              JOptionPane.showMessageDialog(null, "Aluno removido com sucesso");
              
              // As linhas abaixo limpam os campos:
                    txtAlunoNome.setText(null);
                    txtAlunoEndereco.setText(null);
                    txtAlunoFone.setText(null);
                    txtAlunoEmail.setText(null);
                    txtAlunoRA.setText(null);
                    txtAlunoPlano.setText(null);
                    txtAlunoSenha.setText(null);
                    btAlunoAdicionar.setEnabled(true);
          }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        btManual = new javax.swing.JButton();
        btAlunoAdicionar = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btRemover = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAlunos = new javax.swing.JTable();
        lblUsuario = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        btFechar = new javax.swing.JButton();
        txtIdAluno = new javax.swing.JTextField();
        txtAlunoNome = new javax.swing.JTextField();
        txtAlunoRA = new javax.swing.JTextField();
        txtAlunoSenha = new javax.swing.JTextField();
        txtAlunoEmail = new javax.swing.JTextField();
        txtAlunoPlano = new javax.swing.JTextField();
        txtAlunoFone = new javax.swing.JTextField();
        txtAlunoCPF = new javax.swing.JTextField();
        txtAlunoEndereco = new javax.swing.JTextField();
        txtAlunoPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(857, 697));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btManual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/innovative/icones/manual.png"))); // NOI18N
        btManual.setBorderPainted(false);
        btManual.setContentAreaFilled(false);
        btManual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btManual.setDefaultCapable(false);
        btManual.setFocusPainted(false);
        btManual.setFocusable(false);
        btManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btManualActionPerformed(evt);
            }
        });
        getContentPane().add(btManual, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, -1));

        btAlunoAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/innovative/icones/add.png"))); // NOI18N
        btAlunoAdicionar.setBorder(null);
        btAlunoAdicionar.setBorderPainted(false);
        btAlunoAdicionar.setContentAreaFilled(false);
        btAlunoAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAlunoAdicionar.setDefaultCapable(false);
        btAlunoAdicionar.setFocusPainted(false);
        btAlunoAdicionar.setFocusable(false);
        btAlunoAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlunoAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btAlunoAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/innovative/icones/editar.png"))); // NOI18N
        btAlterar.setBorder(null);
        btAlterar.setBorderPainted(false);
        btAlterar.setContentAreaFilled(false);
        btAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAlterar.setDefaultCapable(false);
        btAlterar.setFocusPainted(false);
        btAlterar.setFocusable(false);
        btAlterar.setRequestFocusEnabled(false);
        btAlterar.setRolloverEnabled(false);
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });
        getContentPane().add(btAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, -1, -1));

        btRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/innovative/icones/deletar.png"))); // NOI18N
        btRemover.setBorder(null);
        btRemover.setBorderPainted(false);
        btRemover.setContentAreaFilled(false);
        btRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btRemover.setDefaultCapable(false);
        btRemover.setFocusPainted(false);
        btRemover.setFocusable(false);
        btRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(btRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, -1, -1));

        tblAlunos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblAlunos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAlunosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAlunos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 840, 92));

        lblUsuario.setFont(new java.awt.Font("Arial", 1, 29)); // NOI18N
        lblUsuario.setText("User");
        getContentPane().add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 230, 50));

        lblData.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lblData.setText("Data");
        getContentPane().add(lblData, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 250, 50));

        btFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/innovative/icones/x.png"))); // NOI18N
        btFechar.setToolTipText("");
        btFechar.setBorder(null);
        btFechar.setBorderPainted(false);
        btFechar.setContentAreaFilled(false);
        btFechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btFechar.setDefaultCapable(false);
        btFechar.setFocusPainted(false);
        btFechar.setFocusable(false);
        btFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFecharActionPerformed(evt);
            }
        });
        getContentPane().add(btFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, -1, -1));

        txtIdAluno.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtIdAluno.setBorder(null);
        txtIdAluno.setEnabled(false);
        getContentPane().add(txtIdAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 70, 50));

        txtAlunoNome.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtAlunoNome.setBorder(null);
        getContentPane().add(txtAlunoNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 410, 400, 30));

        txtAlunoRA.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtAlunoRA.setBorder(null);
        getContentPane().add(txtAlunoRA, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 470, 130, -1));

        txtAlunoSenha.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtAlunoSenha.setBorder(null);
        txtAlunoSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlunoSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(txtAlunoSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 470, 300, -1));

        txtAlunoEmail.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtAlunoEmail.setBorder(null);
        getContentPane().add(txtAlunoEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 520, 220, 30));

        txtAlunoPlano.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtAlunoPlano.setBorder(null);
        getContentPane().add(txtAlunoPlano, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 522, 200, 30));

        txtAlunoFone.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtAlunoFone.setBorder(null);
        txtAlunoFone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlunoFoneActionPerformed(evt);
            }
        });
        getContentPane().add(txtAlunoFone, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 580, 230, 30));

        txtAlunoCPF.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtAlunoCPF.setBorder(null);
        getContentPane().add(txtAlunoCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 590, 200, 20));

        txtAlunoEndereco.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtAlunoEndereco.setBorder(null);
        getContentPane().add(txtAlunoEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 652, 550, 20));

        txtAlunoPesquisar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtAlunoPesquisar.setBorder(null);
        txtAlunoPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAlunoPesquisarKeyReleased(evt);
            }
        });
        getContentPane().add(txtAlunoPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 680, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/innovative/imagensTelas/cdt_Aluno.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(890, 710));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lblData.setText(formatador.format(data));
    }//GEN-LAST:event_formWindowActivated

    private void btFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFecharActionPerformed
        // Botão fechar:
        System.exit(0);
    }//GEN-LAST:event_btFecharActionPerformed

    private void txtAlunoSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlunoSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlunoSenhaActionPerformed

    private void txtAlunoFoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlunoFoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlunoFoneActionPerformed

    private void tblAlunosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlunosMouseClicked
        // Evento que sera usado para setar os campos da tabela clicando com o mouse:
        setar_campos();
    }//GEN-LAST:event_tblAlunosMouseClicked

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        // Botão alterar:
        alterar();
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btAlunoAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlunoAdicionarActionPerformed
        // Botão que adiciona o usuário:
        adicionar();
    }//GEN-LAST:event_btAlunoAdicionarActionPerformed

    private void btRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverActionPerformed
        // Botão Remover:
        remover();
        pesquisar_alunos();
    }//GEN-LAST:event_btRemoverActionPerformed

    private void txtAlunoPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlunoPesquisarKeyReleased
        // Pesquisar alunos pelo nome:
        pesquisar_alunos();
    }//GEN-LAST:event_txtAlunoPesquisarKeyReleased

    private void btManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btManualActionPerformed
        // Manual pdf:
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true);

    }//GEN-LAST:event_btManualActionPerformed

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
            java.util.logging.Logger.getLogger(TelaUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btAlunoAdicionar;
    private javax.swing.JButton btFechar;
    private javax.swing.JButton btManual;
    private javax.swing.JButton btRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tblAlunos;
    private javax.swing.JTextField txtAlunoCPF;
    private javax.swing.JTextField txtAlunoEmail;
    private javax.swing.JTextField txtAlunoEndereco;
    private javax.swing.JTextField txtAlunoFone;
    private javax.swing.JTextField txtAlunoNome;
    private javax.swing.JTextField txtAlunoPesquisar;
    private javax.swing.JTextField txtAlunoPlano;
    private javax.swing.JTextField txtAlunoRA;
    private javax.swing.JTextField txtAlunoSenha;
    private javax.swing.JTextField txtIdAluno;
    // End of variables declaration//GEN-END:variables
}
