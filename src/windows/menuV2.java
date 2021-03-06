/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Emmanuel
 */
public class menuV2 extends javax.swing.JFrame {

    
    public menuV2() {
        initComponents();
        this.setLocationRelativeTo(null);   
    }
    
    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/tecsosLogo.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        Users = new javax.swing.JMenu();
        AddUser = new javax.swing.JMenuItem();
        Applicants = new javax.swing.JMenuItem();
        employeesBtn = new javax.swing.JMenuItem();
        Logout = new javax.swing.JMenu();
        Exit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI Symbol", 2, 24)); // NOI18N
        jLabel1.setText("Bienvenido");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tecsos.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 2, 24)); // NOI18N
        jLabel3.setText("Seleccione una de las opciones del menú en la esquina superior izquierda");

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 2, 24)); // NOI18N
        jLabel4.setText("Departamento de Recursos Humanos");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(336, 336, 336)
                        .addComponent(jLabel2))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(372, 372, 372)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(jLabel4)))
                .addContainerGap(375, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addContainerGap(452, Short.MAX_VALUE))
        );

        Users.setText("Menu");
        Users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsersActionPerformed(evt);
            }
        });

        AddUser.setText("Users");
        AddUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddUserMouseClicked(evt);
            }
        });
        AddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddUserActionPerformed(evt);
            }
        });
        Users.add(AddUser);

        Applicants.setText("Applicants");
        Applicants.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ApplicantsMouseClicked(evt);
            }
        });
        Applicants.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApplicantsActionPerformed(evt);
            }
        });
        Users.add(Applicants);

        employeesBtn.setText("Employees");
        employeesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeesBtnActionPerformed(evt);
            }
        });
        Users.add(employeesBtn);

        menuBar.add(Users);

        Logout.setText("Log Out");
        Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutMouseClicked(evt);
            }
        });
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });
        menuBar.add(Logout);

        Exit.setText("Exit");
        Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitMouseClicked(evt);
            }
        });
        menuBar.add(Exit);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddUserActionPerformed
        usersMenu menuUser = new usersMenu();
        menuUser.setSize(1366, 768);
        menuUser.setLocation(5,5);
        
        mainPanel.removeAll();
        mainPanel.add(menuUser, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();        
    }//GEN-LAST:event_AddUserActionPerformed

    private void ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_ExitMouseClicked

    private void AddUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddUserMouseClicked
        
    }//GEN-LAST:event_AddUserMouseClicked

    private void UsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsersActionPerformed
        
    }//GEN-LAST:event_UsersActionPerformed

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        
    }//GEN-LAST:event_LogoutActionPerformed

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        login back = new login();
        this.dispose();
        back.setVisible(true);
    }//GEN-LAST:event_LogoutMouseClicked

    private void ApplicantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApplicantsActionPerformed
        Applicants applicants = new Applicants();
        applicants.setSize(1366, 768);
        applicants.setLocation(5,5);
        
        mainPanel.removeAll();
        mainPanel.add(applicants, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();   
    }//GEN-LAST:event_ApplicantsActionPerformed

    private void ApplicantsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ApplicantsMouseClicked
             
    }//GEN-LAST:event_ApplicantsMouseClicked

    private void employeesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeesBtnActionPerformed
        Empleados empleados = new Empleados();
        empleados.setSize(1366, 768);
        empleados.setLocation(5,5);
        
        mainPanel.removeAll();
        mainPanel.add(empleados, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();  
        
    }//GEN-LAST:event_employeesBtnActionPerformed

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
            java.util.logging.Logger.getLogger(menuV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuV2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AddUser;
    private javax.swing.JMenuItem Applicants;
    private javax.swing.JMenu Exit;
    private javax.swing.JMenu Logout;
    private javax.swing.JMenu Users;
    private javax.swing.JMenuItem employeesBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables
}
