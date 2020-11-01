/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.innovative.telas;

import java.sql.*;
import br.com.innovative.dal.ModuloConexao;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author queir
 */
public class TelaAdmin extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaAdmin
     */
    public TelaAdmin() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    // Adicionar Usuarios
    private void adicionar() {
        String sql = "insert into tbusuarios(iduser,nome,fone,login,senha,email,perfil)values(?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            pst.setString(2, txtUsuNome.getText());
            pst.setString(3, txtUsuFone.getText());
            pst.setString(4, txtUsuLogin.getText());
            pst.setString(5, txtUsuSenha.getText());
            pst.setString(6, txtUsuEmail.getText());
            pst.setString(7, cboUsuPerfil.getSelectedItem().toString());
            //Validação dos campos obrigatórios
            if ((txtUsuId.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty()) || (txtUsuSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                // A linha abaixo atualiza a tabela usuarios com os dados do formularios:
                // A estrutura abaixo é usada para confimrar a inserção dos dados a tabela:
                int adicionado = pst.executeUpdate();
                // A linha abaixo serve de apoio ao entendimento da lógica:
                //System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
                    // As linhas abaixo limpam os campos:
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuFone.setText(null);
                    txtUsuEmail.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Criando o metodo para alterar dados do usuário
    private void alterar() {
        String sql = "update tbusuarios set nome=?, fone=?, login=?, senha=?, email=?, perfil=? where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuFone.getText());
            pst.setString(3, txtUsuLogin.getText());
            pst.setString(4, txtUsuSenha.getText());
            pst.setString(5, txtUsuEmail.getText());
            pst.setString(6, cboUsuPerfil.getSelectedItem().toString());
            pst.setString(7, txtUsuId.getText());

            if ((txtUsuId.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty()) || (txtUsuSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                // A linha abaixo atualiza a tabela usuarios com os dados do formularios:
                // A estrutura abaixo é usada para confimrar a inserção dos dados a tabela:
                int adicionado = pst.executeUpdate();
                // A linha abaixo serve de apoio ao entendimento da lógica:
                //System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterado com sucesso");
                    // As linhas abaixo limpam os campos:
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuFone.setText(null);
                    txtUsuEmail.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Metodo responsavel pela remoção de usuarios
    private void remover() {
        //A estrutura abaixo confirma a remoção do usuário
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbusuarios where iduser=?";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");

                    // As linhas abaixo limpam os campos:
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuFone.setText(null);
                    txtUsuEmail.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    // Metodo para consultar Usuarios
    private void consultar() {
        String sql = "select * from tbusuarios where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsuNome.setText(rs.getString(2));
                txtUsuFone.setText(rs.getString(3));
                txtUsuEmail.setText(rs.getString(4));
                txtUsuLogin.setText(rs.getString(5));
                txtUsuSenha.setText(rs.getString(6));
                // A linha abaixo se refere ao combobox:
                cboUsuPerfil.setSelectedItem(rs.getString(7));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
                // As linhas abaixo limpam os campos:
                txtUsuNome.setText(null);
                txtUsuFone.setText(null);
                txtUsuEmail.setText(null);
                txtUsuLogin.setText(null);
                txtUsuSenha.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        lblUsuario = new javax.swing.JLabel();
        btFechar = new javax.swing.JButton();
        lblData = new javax.swing.JLabel();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuEmail = new javax.swing.JTextField();
        btBuscar = new javax.swing.JButton();
        txtUsuSenha = new javax.swing.JTextField();
        txtUsuFone = new javax.swing.JTextField();
        btUsuAdicionar = new javax.swing.JButton();
        txtUsuId = new javax.swing.JTextField();
        btRemover = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btManual = new javax.swing.JButton();
        TelaDesign = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador");
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(855, 718));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsuario.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        lblUsuario.setText("Admin");
        getContentPane().add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 290, 40));

        btFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/innovative/icones/x.png"))); // NOI18N
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
        getContentPane().add(btFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 30, -1));

        lblData.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lblData.setText("Data");
        getContentPane().add(lblData, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 240, 60));

        cboUsuPerfil.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));
        cboUsuPerfil.setBorder(null);
        cboUsuPerfil.setFocusable(false);
        cboUsuPerfil.setLightWeightPopupEnabled(false);
        cboUsuPerfil.setRequestFocusEnabled(false);
        getContentPane().add(cboUsuPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 110, 30));

        txtUsuLogin.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtUsuLogin.setBorder(null);
        txtUsuLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuLoginActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 460, 520, 30));

        txtUsuNome.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtUsuNome.setBorder(null);
        txtUsuNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuNomeActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, 520, 40));

        txtUsuEmail.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtUsuEmail.setBorder(null);
        txtUsuEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuEmailActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 630, 520, 30));

        btBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/innovative/icones/pesquisar.png"))); // NOI18N
        btBuscar.setBorderPainted(false);
        btBuscar.setContentAreaFilled(false);
        btBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btBuscar.setDefaultCapable(false);
        btBuscar.setFocusPainted(false);
        btBuscar.setFocusable(false);
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 70, -1));

        txtUsuSenha.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtUsuSenha.setBorder(null);
        getContentPane().add(txtUsuSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 510, 520, 40));

        txtUsuFone.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtUsuFone.setBorder(null);
        getContentPane().add(txtUsuFone, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 560, 520, 40));

        btUsuAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/innovative/icones/add.png"))); // NOI18N
        btUsuAdicionar.setBorder(null);
        btUsuAdicionar.setBorderPainted(false);
        btUsuAdicionar.setContentAreaFilled(false);
        btUsuAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btUsuAdicionar.setDefaultCapable(false);
        btUsuAdicionar.setFocusPainted(false);
        btUsuAdicionar.setFocusable(false);
        btUsuAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUsuAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btUsuAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        txtUsuId.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtUsuId.setBorder(null);
        getContentPane().add(txtUsuId, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 352, 50, 30));

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
        getContentPane().add(btRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, -1, -1));

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
        getContentPane().add(btAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, -1));

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

        TelaDesign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/innovative/imagensTelas/admin.png"))); // NOI18N
        getContentPane().add(TelaDesign, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(855, 714));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lblData.setText(formatador.format(data));
    }//GEN-LAST:event_formWindowActivated

    private void txtUsuNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuNomeActionPerformed

    private void txtUsuLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuLoginActionPerformed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        // Botão para pesquisar o usuário:
        consultar();

    }//GEN-LAST:event_btBuscarActionPerformed

    private void btFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFecharActionPerformed
        //Botão Fechar:
        System.exit(0);
    }//GEN-LAST:event_btFecharActionPerformed

    private void txtUsuEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuEmailActionPerformed

    private void btUsuAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUsuAdicionarActionPerformed
        // Botão que adiciona o usuário:
        adicionar();
    }//GEN-LAST:event_btUsuAdicionarActionPerformed

    private void btRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverActionPerformed
        // Botão Remover:
        remover();
    }//GEN-LAST:event_btRemoverActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        // Botão alterar:
        alterar();
    }//GEN-LAST:event_btAlterarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaAdmin.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAdmin.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAdmin.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAdmin.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JLabel TelaDesign;
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btFechar;
    private javax.swing.JButton btManual;
    private javax.swing.JButton btRemover;
    private javax.swing.JButton btUsuAdicionar;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtUsuEmail;
    private javax.swing.JTextField txtUsuFone;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    // End of variables declaration//GEN-END:variables
}
