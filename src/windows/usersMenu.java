/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import rrhh.ConnectionDB;
import rrhh.Render;
import rrhh.Validations;
import static windows.menu.mainPanel;

/**
 *
 * @author Emmanuel
 */
public class usersMenu extends javax.swing.JPanel {

    /**
     * Creates new form usersMenu
     */
    public usersMenu() {
        initComponents();
        CargarTabla("SP_Search_Active_Users");
        loadInactiveUsers();
        
    }
    
    public void loadInactiveUsers(){
        inactiveUsers.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel modelo = (DefaultTableModel) inactiveUsers.getModel();
        inactiveUsers.setRowHeight(20);
        
        //Comienza a contas 0 los renglones
        modelo.setRowCount(0);
        
        ConnectionDB conexion = new ConnectionDB();
        conexion.getConnection();
        
        ResultSet res = ConnectionDB.Query("SELECT * FROM users WHERE active_us = "+0);
        try{
            while(res.next()){
                JButton btnActivate = new JButton("Activate");
                btnActivate.setBackground(Color.BLUE);
                btnActivate.setName("btnActivate");
                
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(btnActivate);
                
                modelo.addRow(v);
                inactiveUsers.setModel(modelo);
                
            }
        }catch(SQLException e){
        }
    }
    
    public void CargarTabla(String query){
        actUsTa.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel modelo = (DefaultTableModel) actUsTa.getModel();
        actUsTa.setRowHeight(20);
        //Comienza a contar 0 los renglones
        modelo.setRowCount(0);
        
        ConnectionDB conexion = new ConnectionDB();
        conexion.getConnection();

        ResultSet res = ConnectionDB.Query(query); 
        try{
            while(res.next()){
                JButton btnDelete = new JButton("Delete");
                btnDelete.setBackground(Color.red);
                btnDelete.setName("btnDelete");

                JButton btnUpdate = new JButton("Update");
                btnUpdate.setBackground(Color.green);   
                btnUpdate.setName("btnUpdate");
                
                JButton btnDeactivate = new JButton("Deactivate");
                btnDeactivate.setBackground(Color.BLUE);
                btnDeactivate.setName("btnDeactivate");
                
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(btnDelete);
                v.add(btnUpdate);
                v.add(btnDeactivate);
                modelo.addRow(v);

                actUsTa.setModel(modelo);
            }
        }
        catch(SQLException e){

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

        Table = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        username = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        cpassword = new javax.swing.JPasswordField();
        roleList = new javax.swing.JComboBox<>();
        registerButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        actUsTa = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        inactiveUsers = new javax.swing.JTable();
        buscarIdTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        buscarID = new javax.swing.JButton();
        buscarNombreTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        buscarNombre = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Table.setBackground(new java.awt.Color(51, 0, 255));
        Table.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Username");
        Table.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 0, -1, -1));

        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usernameKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(username);

        Table.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 20, 260, -1));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Password");
        Table.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 50, -1, -1));

        password.setText("123");
        Table.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 70, 260, -1));

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Confirm Password");
        Table.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 100, -1, -1));

        cpassword.setText("321");
        Table.add(cpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 120, 260, -1));

        roleList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ROLE", "ADMIN", "USER" }));
        Table.add(roleList, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 160, 260, -1));

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        Table.add(registerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 200, -1, -1));

        actUsTa.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Username", "Password", "Role", "Delete", "Update", "Deactivate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        actUsTa.getTableHeader().setReorderingAllowed(false);
        actUsTa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actUsTaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(actUsTa);
        if (actUsTa.getColumnModel().getColumnCount() > 0) {
            actUsTa.getColumnModel().getColumn(0).setResizable(false);
            actUsTa.getColumnModel().getColumn(0).setPreferredWidth(10);
            actUsTa.getColumnModel().getColumn(1).setResizable(false);
            actUsTa.getColumnModel().getColumn(2).setResizable(false);
            actUsTa.getColumnModel().getColumn(3).setResizable(false);
            actUsTa.getColumnModel().getColumn(4).setResizable(false);
            actUsTa.getColumnModel().getColumn(5).setResizable(false);
            actUsTa.getColumnModel().getColumn(6).setResizable(false);
        }

        Table.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 690, 260));

        inactiveUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Username", "Password", "ROLE", "Activate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        inactiveUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inactiveUsersMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(inactiveUsers);
        if (inactiveUsers.getColumnModel().getColumnCount() > 0) {
            inactiveUsers.getColumnModel().getColumn(0).setResizable(false);
            inactiveUsers.getColumnModel().getColumn(1).setResizable(false);
            inactiveUsers.getColumnModel().getColumn(2).setResizable(false);
            inactiveUsers.getColumnModel().getColumn(3).setResizable(false);
            inactiveUsers.getColumnModel().getColumn(4).setResizable(false);
        }

        Table.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 300, 510, 260));

        buscarIdTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarIdTxtActionPerformed(evt);
            }
        });
        Table.add(buscarIdTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 196, -1));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Buscar Usuario por ID");
        Table.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        buscarID.setText("Buscar");
        buscarID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarIDMouseClicked(evt);
            }
        });
        Table.add(buscarID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));
        Table.add(buscarNombreTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 200, -1));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Buscar Usuario por nombre");
        Table.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        buscarNombre.setText("Buscar");
        Table.add(buscarNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Usuarios Activos");
        Table.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, -1, -1));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Usuarios Inactivos");
        Table.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 270, -1, -1));

        add(Table, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 570));
    }// </editor-fold>//GEN-END:initComponents

    private void usernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyTyped
        char validate = evt.getKeyChar();

        if(!Character.isLetter(validate)){
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(null, "Ingresar solo letras");
        }
    }//GEN-LAST:event_usernameKeyTyped

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        //Register new user
        ConnectionDB conexion = new ConnectionDB();
        conexion.getConnection();

        String username = this.username.getText();
        String pass = new String(password.getPassword());
        String cpass = new String(cpassword.getPassword());
        String role = String.valueOf(this.roleList.getSelectedItem());
        boolean exists = conexion.existingUser(username);

        if(Validations.userLength(username, pass)){
            if(!username.equals("admin")){
                //Validate if the username already exists
                if(exists){
                    //Create new user
                    if(pass.equals(cpass)){
                        if(!role.equals("ROLE")){
                            conexion.createUser(username, pass, role);
                            this.username.setText("");
                            this.password.setText("");
                            this.cpassword.setText("");
                            CargarTabla("SP_Search_Active_Users");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Selecciona un Rol", null, JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", null, JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Ya existe ese nombre de usuario");
                }
            }
        }

    }//GEN-LAST:event_registerButtonActionPerformed

    private void actUsTaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actUsTaMouseClicked

        int column = actUsTa.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/actUsTa.getRowHeight();

        if(row < actUsTa.getRowCount() && row >= 0 && column<actUsTa.getColumnCount() && column>=0){
            Object  value = actUsTa.getValueAt(row, column);
            Object id = actUsTa.getValueAt(row, 0);
            int id_User = (int) id;
            if(value instanceof JButton){
                ((JButton) value).doClick();
                JButton boton = (JButton) value;
                if(boton.getName().equals("btnDelete")){
                    //Query para borrar
                    int option = JOptionPane.showConfirmDialog(null, "Do you really want to delete this user?",
                        "Warning", JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION){
                        ConnectionDB con = new ConnectionDB();
                        con.deleteUserId(id_User);
                        CargarTabla("SP_Search_Active_Users");
                    }
                }
                else if(boton.getName().equals("btnUpdate")){
                    String nUsername = (String) actUsTa.getValueAt(row, 1);
                    String nPassword = (String) actUsTa.getValueAt(row, 2);
                    
                    
                    
                    JOptionPane.showMessageDialog(null, "User Updated");
                }
                else if(boton.getName().equals("btnDeactivate")){
                    //query desactivar user
                    int option = JOptionPane.showConfirmDialog(null, "Do you really want to deactivate this user?",
                        "Warning", JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION){
                        ConnectionDB con = new ConnectionDB();
                        con.changeStatus(id_User);
                        CargarTabla("SP_Search_Active_Users");
                        loadInactiveUsers();
                    }
                }
            }
        }
    }//GEN-LAST:event_actUsTaMouseClicked

    private void buscarIdTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarIdTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarIdTxtActionPerformed

    private void buscarIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarIDMouseClicked
        
    }//GEN-LAST:event_buscarIDMouseClicked

    private void inactiveUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inactiveUsersMouseClicked
        int column = inactiveUsers.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/inactiveUsers.getRowHeight();

        if(row < inactiveUsers.getRowCount() && row >= 0 && column<inactiveUsers.getColumnCount() && column>=0){
            Object  value = inactiveUsers.getValueAt(row, column);
            Object id = inactiveUsers.getValueAt(row, 0);
            int id_User = (int) id;
            if(value instanceof JButton){
                ((JButton) value).doClick();
                JButton boton = (JButton) value;
                if(boton.getName().equals("btnActivate")){
                    //Query para activar
                    int option = JOptionPane.showConfirmDialog(null, "Do you really want to reactivate this user?",
                        "Warning", JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION){
                        ConnectionDB con = new ConnectionDB();
                        con.changeStatus(id_User);
                        CargarTabla("SP_Search_Active_Users");
                        loadInactiveUsers();
                    }
                }
            }
        }
        
        
    }//GEN-LAST:event_inactiveUsersMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Table;
    private javax.swing.JTable actUsTa;
    private javax.swing.JButton buscarID;
    private javax.swing.JTextField buscarIdTxt;
    private javax.swing.JButton buscarNombre;
    private javax.swing.JTextField buscarNombreTxt;
    private javax.swing.JPasswordField cpassword;
    private javax.swing.JTable inactiveUsers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton registerButton;
    private javax.swing.JComboBox<String> roleList;
    private javax.swing.JTextPane username;
    // End of variables declaration//GEN-END:variables
}
