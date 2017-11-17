/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import javax.swing.JOptionPane;
import rrhh.ConnectionDB;

/**
 *
 * @author Emmanuel
 */
public class DeleteUserPane extends javax.swing.JPanel {

    /**
     * Creates new form AddUserPane
     */
    public DeleteUserPane() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        username = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 153, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setViewportView(username);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 260, -1));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Username");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, -1, -1));

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int option = JOptionPane.showConfirmDialog(null, "Do you really want to delete this user?", "Warning", JOptionPane.YES_NO_OPTION);
        String username = this.username.getText();
        if(option == JOptionPane.YES_OPTION){
            ConnectionDB con = new ConnectionDB();
            con.deleteUser(username);
            this.username.setText("");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane username;
    // End of variables declaration//GEN-END:variables
}