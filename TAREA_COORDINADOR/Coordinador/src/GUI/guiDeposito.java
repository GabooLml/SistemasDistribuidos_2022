/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import coordinador.Conector;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Ángel
 */
public class guiDeposito extends javax.swing.JFrame {

    /**
     * Creates new form guiDeposito
     */
    public guiDeposito() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        BTNDEPOSITAR = new javax.swing.JButton();
        cuentadep = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        montoDep = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        mensajeDep = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cuenta a depositar:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        BTNDEPOSITAR.setText("Depositar");
        BTNDEPOSITAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNDEPOSITARActionPerformed(evt);
            }
        });
        getContentPane().add(BTNDEPOSITAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, -1, -1));

        cuentadep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuentadepActionPerformed(evt);
            }
        });
        getContentPane().add(cuentadep, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 170, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Depositos");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, -1, -1));
        getContentPane().add(montoDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 170, -1));

        mensajeDep.setColumns(20);
        mensajeDep.setRows(5);
        jScrollPane1.setViewportView(mensajeDep);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 290, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Monto a depositar:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/fondo.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cuentadepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuentadepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuentadepActionPerformed

    private void BTNDEPOSITARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNDEPOSITARActionPerformed
        Conector cliente = new Conector();
        String numcta = cuentadep.getText();
        float monto = Float.parseFloat(montoDep.getText());
        float saldoFinal = 0;
        String sfinal =null;
        cliente.conectar('C');
        try {
            saldoFinal = cliente.deposito(numcta,monto);
        } catch (SQLException ex) {
            Logger.getLogger(guiDeposito.class.getName()).log(Level.SEVERE, null, ex);
        }
        sfinal = Float.toString(saldoFinal);
        String mensaje ="Saldo final de la Cuenta:\n"+ sfinal;
        JOptionPane.showMessageDialog(null,mensaje);
        mensajeDep.setText(mensaje);
    }//GEN-LAST:event_BTNDEPOSITARActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        guiCoord guiCoordi = new guiCoord();
        guiCoordi.setVisible(true);
        guiCoordi.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(guiDeposito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(guiDeposito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(guiDeposito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(guiDeposito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new guiDeposito().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNDEPOSITAR;
    private javax.swing.JTextField cuentadep;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea mensajeDep;
    private javax.swing.JTextField montoDep;
    // End of variables declaration//GEN-END:variables
}
