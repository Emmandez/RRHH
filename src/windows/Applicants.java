/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import rrhh.ConnectionDB;
import rrhh.Render;
import rrhh.Validations;
import static windows.menu.mainPanel;

/**
 *
 * @author Emmanuel
 */
public class Applicants extends javax.swing.JPanel {

    /**
     * Stored Procedures candidatos
     * 
     * SP_Show_Candidatos
     * SP_Insert_Candidato
     * SP_Delete_Candidato
     * SP_Update_Candidato
     */
    public Applicants() {
        initComponents();
        CargarTabla("SP_Show_Candidatos");
        CargarTelTable("SELECT telefono.id_telefono, numero, extension, desc_tel FROM telefono JOIN tel_candidato ON telefono.id_telefono= tel_candidato.id_telefono");
        CargarEmailTable("SELECT correo.id_correo, email, desc_correo FROM correo JOIN correo_candidato ON correo.id_correo= correo_candidato.id_correo");        
        CargarAddressTable("SELECT direccion.id_direccion, calle, num_int, num_ext, nom_col, cp, nom_ciudad, nom_estado FROM candidato "
                + "JOIN dir_candidato ON dir_candidato.id_candidato = candidato.id_candidato "
                + "JOIN direccion ON direccion.id_direccion=dir_candidato.id_direccion "
                + "JOIN dir_colonia ON direccion.id_direccion=dir_colonia.id_direccion "
                + "JOIN colonia ON dir_colonia.id_colonia = colonia.id_colonia "
                + "JOIN colonia_ciudad ON colonia.id_colonia = colonia_ciudad.id_colonia "
                + "JOIN ciudad ON colonia_ciudad.id_ciudad = ciudad.id_ciudad "
                + "JOIN ciudad_estado ON ciudad.id_ciudad=ciudad_estado.id_ciudad "
                + "JOIN estado ON ciudad_estado.id_estado=estado.id_estado");
        
    }
    
    public void CargarEmailTable(String query){
        /*"SELECT numero, extension, desc_tel FROM telefono "
                + "JOIN tel_candidato ON telefono.id_telefono= tel_candidato.id_telefono "
                + "JOIN candidato on tel_candidato.id_candidato= candidato.id_candidato"
                        */
        emailTable.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel modelo = (DefaultTableModel) emailTable.getModel();
        emailTable.setRowHeight(25);
        //Comienza a contar 0 los renglones
        modelo.setRowCount(0);
        ConnectionDB.getConnection();
        ResultSet res = ConnectionDB.Query(query);
        try{
            while(res.next()){
                JButton btnDelete = new JButton("Delete");
                btnDelete.setBackground(Color.red);
                btnDelete.setName("btnDelete");

                JButton btnUpdate = new JButton("Update");
                btnUpdate.setBackground(Color.green);   
                btnUpdate.setName("btnUpdate");
                
                 Vector v = new Vector();
                //data user
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(btnUpdate);
                v.add(btnDelete);
                modelo.addRow(v);

                emailTable.setModel(modelo);            
                
                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla email", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void CargarTelTable(String query){
        phonesTable.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel modelo = (DefaultTableModel) phonesTable.getModel();
        phonesTable.setRowHeight(25);
        modelo.setRowCount(0);
        
        ConnectionDB.getConnection();
        ResultSet res = ConnectionDB.Query(query);
        
        try{
            while(res.next()){
                JButton btnDelete = new JButton("Delete");
                btnDelete.setBackground(Color.red);
                btnDelete.setName("btnDelete");

                JButton btnUpdate = new JButton("Update");
                btnUpdate.setBackground(Color.green);   
                btnUpdate.setName("btnUpdate");
                
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(btnUpdate);
                v.add(btnDelete);
                        
                modelo.addRow(v);
                
                phonesTable.setModel(modelo);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla teléfonos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    public void CargarAddressTable(String query){
        addressTable.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel modelo = (DefaultTableModel) addressTable.getModel();
        modelo.setRowCount(0);
        ConnectionDB.getConnection();
        
        ResultSet res = ConnectionDB.Query(query);
        try{
            while(res.next()){
                JButton btnDelete = new JButton("Delete");
                btnDelete.setBackground(Color.red);
                btnDelete.setName("btnDelete");
                
                JButton btnUpdate = new JButton("Update");
                btnUpdate.setBackground(Color.green);   
                btnUpdate.setName("btnUpdate");
                
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(4));
                v.add(res.getString(3));
                v.add(res.getString(5));
                v.add(res.getString(6));
                v.add(res.getString(7));
                v.add(res.getString(8));
                v.add(btnUpdate);
                v.add(btnDelete);
                
                modelo.addRow(v);
                addressTable.setModel(modelo);       
            }
        }
        catch(SQLException e){
        }
    }
    public void CargarTabla(String query){
        applicantsTable.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel modelo = (DefaultTableModel) applicantsTable.getModel();
        
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
                
                JButton btnHire = new JButton("Hire");
                btnHire.setBackground(Color.BLUE);
                btnHire.setName("btnHire");
                
                JButton btnPhone = new JButton("Phone");
                btnPhone.setName("btnPhone");
                
                JButton btnEmail = new JButton("Email");
                btnEmail.setName("btnEmail");
                
                JButton btnAddress = new JButton("Address");
                btnAddress.setName("btnAddress");
                
                Vector v = new Vector();
                //data user
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getString(5));
                v.add(res.getInt(6));
                v.add(res.getString(7));
                //contact, add email and phone
                v.add(btnAddress);
                v.add(btnEmail);
                v.add(btnPhone);
                //delete, hire and update
                v.add(btnUpdate);
                v.add(btnHire);
                v.add(btnDelete);
                
                modelo.addRow(v);

                applicantsTable.setModel(modelo);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla Candidatos", "Error", JOptionPane.ERROR_MESSAGE);
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
        nombre = new javax.swing.JTextPane();
        registerButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        applicantsTable = new javax.swing.JTable();
        buscarIdTxt = new javax.swing.JTextField();
        buscarID = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        buscarNombreTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        buscarNombre = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        showAllUsersBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        app = new javax.swing.JTextPane();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        apm = new javax.swing.JTextPane();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        expLab = new javax.swing.JTextPane();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        expSal = new javax.swing.JTextPane();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        puesto = new javax.swing.JTextPane();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        phonesTable = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        emailTable = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        addressTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Table.setBackground(new java.awt.Color(220, 204, 204));
        Table.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nombre");
        Table.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 340, -1, -1));

        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(nombre);

        Table.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 360, 260, -1));

        registerButton.setText("Register");
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerButtonMouseClicked(evt);
            }
        });
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        registerButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                registerButtonKeyPressed(evt);
            }
        });
        Table.add(registerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 650, -1, -1));

        applicantsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Apellido Paterno", "Apellido Materno", "Experiencla Lab.", "Expectativa Salarial", "Puesto que aspira", "Address", "Email", "Phone", "Update", "Hire", "Delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        applicantsTable.setRowHeight(25);
        applicantsTable.getTableHeader().setReorderingAllowed(false);
        applicantsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                applicantsTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(applicantsTable);
        if (applicantsTable.getColumnModel().getColumnCount() > 0) {
            applicantsTable.getColumnModel().getColumn(0).setResizable(false);
            applicantsTable.getColumnModel().getColumn(0).setPreferredWidth(7);
            applicantsTable.getColumnModel().getColumn(1).setResizable(false);
            applicantsTable.getColumnModel().getColumn(2).setResizable(false);
            applicantsTable.getColumnModel().getColumn(3).setResizable(false);
            applicantsTable.getColumnModel().getColumn(4).setResizable(false);
            applicantsTable.getColumnModel().getColumn(5).setResizable(false);
            applicantsTable.getColumnModel().getColumn(6).setResizable(false);
            applicantsTable.getColumnModel().getColumn(7).setResizable(false);
            applicantsTable.getColumnModel().getColumn(7).setPreferredWidth(10);
            applicantsTable.getColumnModel().getColumn(8).setResizable(false);
            applicantsTable.getColumnModel().getColumn(8).setPreferredWidth(10);
            applicantsTable.getColumnModel().getColumn(9).setResizable(false);
            applicantsTable.getColumnModel().getColumn(9).setPreferredWidth(10);
            applicantsTable.getColumnModel().getColumn(10).setResizable(false);
            applicantsTable.getColumnModel().getColumn(10).setPreferredWidth(10);
            applicantsTable.getColumnModel().getColumn(11).setResizable(false);
            applicantsTable.getColumnModel().getColumn(11).setPreferredWidth(10);
            applicantsTable.getColumnModel().getColumn(12).setResizable(false);
            applicantsTable.getColumnModel().getColumn(12).setPreferredWidth(10);
        }

        Table.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1340, 200));

        buscarIdTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarIdTxtActionPerformed(evt);
            }
        });
        buscarIdTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscarIdTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscarIdTxtKeyTyped(evt);
            }
        });
        Table.add(buscarIdTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 196, -1));

        buscarID.setText("Buscar");
        buscarID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarIDMouseClicked(evt);
            }
        });
        Table.add(buscarID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Buscar Candidato por ID");
        Table.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        buscarNombreTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscarNombreTxtKeyTyped(evt);
            }
        });
        Table.add(buscarNombreTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 200, -1));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Buscar Candidato por nombre");
        Table.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, -1, -1));

        buscarNombre.setText("Buscar");
        buscarNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarNombreMouseClicked(evt);
            }
        });
        Table.add(buscarNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, -1, -1));

        jLabel1.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Teléfonos");
        Table.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, -1, 30));

        showAllUsersBtn.setText("Mostrar Todos");
        showAllUsersBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showAllUsersBtnMouseClicked(evt);
            }
        });
        Table.add(showAllUsersBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 60, 120, -1));

        app.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                appKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(app);

        Table.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 410, 260, -1));

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Apellido Paterno");
        Table.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 390, -1, -1));

        apm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apmKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(apm);

        Table.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 460, 260, -1));

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Apellido Materno");
        Table.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 440, -1, -1));

        expLab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                expLabKeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(expLab);

        Table.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 510, 260, -1));

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Experiencia  Laboral");
        Table.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 490, -1, -1));

        expSal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                expSalKeyTyped(evt);
            }
        });
        jScrollPane6.setViewportView(expSal);

        Table.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 560, 260, -1));

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Expectativa Salarial");
        Table.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 540, -1, -1));

        puesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                puestoKeyTyped(evt);
            }
        });
        jScrollPane7.setViewportView(puesto);

        Table.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 610, 260, -1));

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Puesto Deseado");
        Table.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 590, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_user.png"))); // NOI18N
        Table.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 300, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Candidatos Activos");
        Table.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, -1, 30));

        jLabel2.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Email");
        Table.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 310, -1, 30));

        phonesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Teléfono", "Extensión", "Descripcion", "Update", "Delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        phonesTable.setRowHeight(25);
        phonesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                phonesTableMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(phonesTable);
        if (phonesTable.getColumnModel().getColumnCount() > 0) {
            phonesTable.getColumnModel().getColumn(0).setResizable(false);
            phonesTable.getColumnModel().getColumn(0).setPreferredWidth(3);
            phonesTable.getColumnModel().getColumn(1).setResizable(false);
            phonesTable.getColumnModel().getColumn(2).setResizable(false);
            phonesTable.getColumnModel().getColumn(2).setPreferredWidth(5);
            phonesTable.getColumnModel().getColumn(3).setResizable(false);
            phonesTable.getColumnModel().getColumn(4).setResizable(false);
            phonesTable.getColumnModel().getColumn(4).setPreferredWidth(8);
            phonesTable.getColumnModel().getColumn(5).setResizable(false);
            phonesTable.getColumnModel().getColumn(5).setPreferredWidth(8);
        }

        Table.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 530, 150));

        emailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Email", "Descripción", "Update", "Delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        emailTable.setRowHeight(25);
        emailTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emailTableMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(emailTable);
        if (emailTable.getColumnModel().getColumnCount() > 0) {
            emailTable.getColumnModel().getColumn(0).setResizable(false);
            emailTable.getColumnModel().getColumn(0).setPreferredWidth(3);
            emailTable.getColumnModel().getColumn(1).setResizable(false);
            emailTable.getColumnModel().getColumn(2).setResizable(false);
            emailTable.getColumnModel().getColumn(3).setResizable(false);
            emailTable.getColumnModel().getColumn(3).setPreferredWidth(8);
            emailTable.getColumnModel().getColumn(4).setResizable(false);
            emailTable.getColumnModel().getColumn(4).setPreferredWidth(8);
        }

        Table.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, 530, 150));

        addressTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Calle", "Número Ext.", "Número Int.", "Colonia", "CP", "Ciudad", "Estado", "Update", "Delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        addressTable.setRowHeight(25);
        addressTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addressTableMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(addressTable);
        if (addressTable.getColumnModel().getColumnCount() > 0) {
            addressTable.getColumnModel().getColumn(0).setResizable(false);
            addressTable.getColumnModel().getColumn(0).setPreferredWidth(7);
            addressTable.getColumnModel().getColumn(1).setResizable(false);
            addressTable.getColumnModel().getColumn(2).setResizable(false);
            addressTable.getColumnModel().getColumn(2).setPreferredWidth(5);
            addressTable.getColumnModel().getColumn(3).setResizable(false);
            addressTable.getColumnModel().getColumn(3).setPreferredWidth(5);
            addressTable.getColumnModel().getColumn(4).setResizable(false);
            addressTable.getColumnModel().getColumn(5).setResizable(false);
            addressTable.getColumnModel().getColumn(6).setResizable(false);
            addressTable.getColumnModel().getColumn(7).setResizable(false);
            addressTable.getColumnModel().getColumn(8).setResizable(false);
            addressTable.getColumnModel().getColumn(8).setPreferredWidth(8);
            addressTable.getColumnModel().getColumn(9).setResizable(false);
            addressTable.getColumnModel().getColumn(9).setPreferredWidth(8);
        }

        Table.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 1080, 200));

        jLabel6.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Dirección");
        Table.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 510, 100, 30));

        jButton1.setText("Limpiar Busquedas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Table.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 150, -1));

        add(Table, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));
    }// </editor-fold>//GEN-END:initComponents

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
        char validate = evt.getKeyChar();

        if(!Character.isLetter(validate) && !Character.isSpaceChar(validate) && !Character.isISOControl(validate)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo letras");
        }
    }//GEN-LAST:event_nombreKeyTyped

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        //Register new user
        ConnectionDB conexion = new ConnectionDB();
        conexion.getConnection();

        String username = this.nombre.getText();
        
        boolean exists = conexion.existingUser(username);


    }//GEN-LAST:event_registerButtonActionPerformed

    
    
    private void applicantsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_applicantsTableMouseClicked

        int column = applicantsTable.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/applicantsTable.getRowHeight();
        

        if(row < applicantsTable.getRowCount() && row >= 0 && column<applicantsTable.getColumnCount() && column>=0){
            Object  value = applicantsTable.getValueAt(row, column);
            Object id = applicantsTable.getValueAt(row, 0);
            int id_User = (int) id;
            
            CargarEmailTable("SP_Get_Email_Candidato '"+id_User+"'");
            CargarTelTable("SP_Get_Tel_Candidato '"+id_User+"'"); 
            CargarAddressTable("SP_Get_Add_Candidato '"+id_User+"'");
           
            if(value instanceof JButton){
                ((JButton) value).doClick();
                JButton boton = (JButton) value;
                switch (boton.getName()) {
                    case "btnDelete":
                        {
                            //Query para borrar
                            int option = JOptionPane.showConfirmDialog(null, "Do you really want to delete this user?",
                                    "Warning", JOptionPane.YES_NO_OPTION);
                            if(option == JOptionPane.YES_OPTION){
                                ConnectionDB.deleteApplicantId(id_User);
                                CargarTabla("SP_Show_Candidatos");
                            }       
                            break;
                        }
                    case "btnUpdate":
                        String nname = (String) applicantsTable.getValueAt(row, 1);
                        String nlastn = (String) applicantsTable.getValueAt(row, 2);
                        String nlastnm = (String) applicantsTable.getValueAt(row, 3);
                        String nexLab = (String) applicantsTable.getValueAt(row, 4);
                        String nexpSal = (String) applicantsTable.getValueAt(row, 5);
                        String puestoP = (String) applicantsTable.getValueAt(row, 6);
                        
                        if(Validations.LettersAndLength(nname) && Validations.LettersAndLength(nlastn) 
                                && Validations.LettersAndLength(nlastnm) && Validations.validateLettersAndNumbers(nexLab)
                                && Validations.validateNumbers(nexpSal) && Validations.LettersAndLength(puestoP)){
                            ConnectionDB.updateApplicant(nname, nlastn, nlastnm, nexLab, nexpSal, puestoP, id_User);
                            CargarTabla("SP_Show_Candidatos"); 
                        }else{
                            JOptionPane.showMessageDialog(null, "Uno de los datos actualizados no "
                                    + "corresponde en el formato requerido");
                        }
                        
                        break;
                    case "btnHire":
                        {
                            int option = JOptionPane.showConfirmDialog(null, "Do you really want to hire this applicant?",
                                    "Warning", JOptionPane.YES_NO_OPTION);
                            if(option == JOptionPane.YES_OPTION){
                                JFrame frame = new JFrame("Contratar Candidato");
                                JPanel pane = new JPanel();
                                pane.setLayout(new GridLayout(0, 2, 2, 2));                                
                                
                                JTextField rfc_J = new JTextField();
                                String[] genArray = {"Femenino","Masculino"};
                                JComboBox genero_J = new JComboBox(genArray);
                                
                                String[] horarios = {
                                    "7:00 AM - 3:00 PM",
                                    "8:00 AM - 4:00 PM",
                                    "9:00 AM - 5:00 PM",
                                    "10:00 AM - 6:00 PM",
                                    "11:00 AM - 7:00 PM"
                                };
                                JComboBox horario_J = new JComboBox(horarios);
                                
                                JTextField sueldo_J = new JTextField(5);
                                
                                JTextField puesto_J = new JTextField();
                                
                                pane.add(new JLabel("Género"));
                                pane.add(genero_J);
                                
                                pane.add(new JLabel("RFC:"));
                                pane.add(rfc_J);
                                
                                pane.add(new JLabel("Sueldo"));
                                pane.add(sueldo_J);
                                
                                pane.add(new JLabel("Horario"));
                                pane.add(horario_J);
                                
                                pane.add(new JLabel("Puesto"));
                                pane.add(puesto_J);
                                
                                
                                option = JOptionPane.showConfirmDialog(frame, pane, "Añadir datos faltantes", JOptionPane.OK_OPTION);
                                if(option==JOptionPane.OK_OPTION){
                                    String rfc = rfc_J.getText();
                                    String genero = String.valueOf(genero_J.getSelectedItem());
                                    String name = (String) applicantsTable.getValueAt(row, 1);
                                    String lastn = (String) applicantsTable.getValueAt(row, 2);
                                    String lastnm = (String) applicantsTable.getValueAt(row, 3);
                                    String puesto = puesto_J.getText();
                                    String sueldo = sueldo_J.getText();
                                    
                                    
                                    int x=0;
                                    
                                    //validar los campos obtenidos de la tabla y del jpane
                                    if(Validations.LettersAndLength(name) && Validations.LettersAndLength(lastn) 
                                            && Validations.LettersAndLength(lastnm) && Validations.LettersAndLength(puesto)
                                            && Validations.validateNumbers(sueldo) && Validations.validateLettersAndNumbers(rfc)){
                                        String consulta = "SP_Insert_Empleado '"+name+"', '"+lastn+"', '"+lastnm+"', '"
                                            +rfc+"', '"+sueldo+"','"+puesto+"', '"+genero+"'";
                                        x = ConnectionDB.CDU(consulta);
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Uno o más campos no coinciden con el patrón \n");
                                    }
                                    
                                    
                                    
                                    if(x!=0){
                                        JOptionPane.showMessageDialog(null, "Candidato contratado");
                                        
                                        //Agregar la información de contacto de este candidato con el nuevo el nuevo empleado
                                        
                                        //Se obtienen los id de los telefonos para después relacionarlos con los empleados
                                        ResultSet telefonos = ConnectionDB.Query("SP_Get_Tel_Candidato '"+id_User+"'");
                                        try{
                                            while(telefonos.next()){     
                                                ConnectionDB.CDU("SP_Insert_Rel_Tel_Emp '"+telefonos.getInt(1)+"'");
                                            }
                                            ConnectionDB.CDU("SP_Delete_Rel_Tel_Can '"+id_User+"'");
                                        }
                                        catch(SQLException e){
                                            JOptionPane.showMessageDialog(null, "Algo mal ha ocurrido. Conatacte al administrador");
                                        }
                                        
                                        ResultSet correos = ConnectionDB.Query("SP_Get_Email_Candidato '"+id_User+"'");
                                        try{
                                            while(correos.next()){                                                
                                                ConnectionDB.CDU("SP_Insert_Rel_Email_Emp '"+correos.getInt(1)+"'");
                                            }
                                            ConnectionDB.CDU("SP_Delete_Rel_Email_Can '"+id_User+"'");
                                        }
                                        catch(SQLException e){
                                            JOptionPane.showMessageDialog(null, "Algo mal ha ocurrido. Conatacte al administrador");
                                        }
                                        
                                        ResultSet direcciones = ConnectionDB.Query("SP_Get_Add_Candidato '"+id_User+"'");
                                        try{
                                            while(direcciones.next()){
                                                ConnectionDB.CDU("SP_Insert_Rel_Dir_Emp '"+direcciones.getInt(1)+"'");
                                            }
                                            ConnectionDB.CDU("SP_Delete_Rel_Dir_Can '"+id_User+"'");
                                        }catch(SQLException e){
                                            JOptionPane.showMessageDialog(null, "Algo mal ha ocurrido. Conatacte al administrador");
                                        }
                                        ConnectionDB.CDU("DELETE FROM candidato WHERE id_candidato = "+id_User);
                                        CargarTabla("SP_Show_Candidatos");
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Algo mal ha ocurrido. Conatacte al administrador");
                                    }
                                }
                                
                            }       break;
                        }
                    case "btnAddress":
                    {
                        JFrame frame = new JFrame("Agregar Dirección");
                        JPanel pane = new JPanel();
                        pane.setLayout(new GridLayout(0, 2, 2, 2));
                        
                        JTextField JTcalle = new JTextField();
                        JTextField JTnumInt = new JTextField();
                        JTextField JTnumExt = new JTextField();
                        JTextField JTcolonia = new JTextField();
                        JTextField JTciudad = new JTextField();
                        JTextField JTestado = new JTextField();
                        JTextField JCP = new JTextField();
                        
                        pane.add(new JLabel("Calle: "));
                        pane.add(JTcalle);
                        
                        pane.add(new JLabel("Número Int: "));
                        pane.add(JTnumInt);
                        
                        pane.add(new JLabel("Número Ext: "));
                        pane.add(JTnumExt);
                        
                        pane.add(new JLabel("Colonia: "));
                        pane.add(JTcolonia);
                        
                        pane.add(new JLabel("Código Postal: "));
                        pane.add(JCP);
                        
                        pane.add(new JLabel("Ciudad: "));
                        pane.add(JTciudad);
                        
                        pane.add(new JLabel("Estado: "));
                        pane.add(JTestado);
                        
                        int option = JOptionPane.showConfirmDialog(frame, pane, "Añadir Dirección", JOptionPane.OK_OPTION);
                        if(option==JOptionPane.OK_OPTION){
                            String calle = JTcalle.getText();
                            String nInt = JTnumInt.getText();
                            String nExt = JTnumExt.getText();
                            String colonia = JTcolonia.getText();
                            String cp = JCP.getText();
                            String ciudad = JTciudad.getText();
                            String estado = JTestado.getText();
                            
                            if(Validations.length50Empty(calle) && Validations.length5Empty(nInt) 
                                    && Validations.length5Empty(nExt) && Validations.length50Empty(colonia)
                                    && Validations.length50Empty(ciudad) && Validations.length50Empty(estado)){
                                ConnectionDB.CDU("SP_Insert_Address '"+calle+"', '"+nInt+"', '"+nExt+"', '"+colonia
                                        +"', '"+cp+"', '"+ciudad+"', '"+estado+"'");
                                ConnectionDB.CDU("SP_Insert_Add_Can '"+id_User+"'");
                                CargarAddressTable("SELECT direccion.id_direccion, calle, num_int, num_ext, nom_col, cp, nom_ciudad, nom_estado FROM candidato "
                                                    + "JOIN dir_candidato ON dir_candidato.id_candidato = candidato.id_candidato "
                                                    + "JOIN direccion ON direccion.id_direccion=dir_candidato.id_direccion "
                                                    + "JOIN dir_colonia ON direccion.id_direccion=dir_colonia.id_direccion "
                                                    + "JOIN colonia ON dir_colonia.id_colonia = colonia.id_colonia "
                                                    + "JOIN colonia_ciudad ON colonia.id_colonia = colonia_ciudad.id_colonia "
                                                    + "JOIN ciudad ON colonia_ciudad.id_ciudad = ciudad.id_ciudad "
                                                    + "JOIN ciudad_estado ON ciudad.id_ciudad=ciudad_estado.id_ciudad "
                                                    + "JOIN estado ON ciudad_estado.id_estado=estado.id_estado");
                            }                            
                        }
                        break;
                    }
                    case "btnPhone":
                    {
                        JFrame frame = new JFrame("Agregar Telefono");
                        JPanel pane = new JPanel();
                        pane.setLayout(new GridLayout(0, 2, 2, 2));
                        
                        JTextField phoneN = new JTextField(10);
                        JTextField desc = new JTextField();
                        JTextField ext = new JTextField();
                        
                        pane.add(new JLabel("Teléfono: "));
                        pane.add(phoneN);
                        
                        pane.add(new JLabel("Extensión: "));
                        pane.add(ext);
                        
                        pane.add(new JLabel("Descripción: "));
                        pane.add(desc);
                        
                        int option = JOptionPane.showConfirmDialog(frame, pane, "Añadir teléfono", JOptionPane.OK_OPTION);
                        
                        if(option==JOptionPane.OK_OPTION){
                            String numeroTel = phoneN.getText();
                            String extension = ext.getText();
                            String descTel = desc.getText();
                            
                            if(Validations.numericPhoneNumber(numeroTel) 
                                    && Validations.numericPhoneNumber(extension) && Validations.validateJustLetters(descTel)){
                                ConnectionDB.insertPhone(numeroTel, extension, descTel);   
                                ConnectionDB.phoneCandidato(id_User);
                                CargarTelTable("SELECT telefono.id_telefono, numero, extension, desc_tel FROM telefono JOIN tel_candidato ON telefono.id_telefono= tel_candidato.id_telefono");
                            }else{
                                JOptionPane.showMessageDialog(null, "Los campos número de teléfono y extensión solo admiten números \n"
                                        + "El campo descripción sólo admite letras\n"
                                        + "Intente de nuevo");
                            }
                        }
                        break;
                    }
                    case "btnEmail":
                    {
                        JFrame frame = new JFrame("Agregar Email");
                        JPanel pane = new JPanel();
                        pane.setLayout(new GridLayout(0, 2, 2, 2));
                        
                        JTextField email = new JTextField();
                        JTextField desc = new JTextField();
                        
                        pane.add(new JLabel("Email: "));
                        pane.add(email);
 
                        pane.add(new JLabel("Descripción: "));
                        pane.add(desc);
                        
                        int option = JOptionPane.showConfirmDialog(frame, pane, "Añadir Email", JOptionPane.OK_OPTION);
                        
                        if(option==JOptionPane.OK_OPTION){
                            String mEmail = email.getText();
                            String mDesc = desc.getText();
                            
                            if(Validations.validateEmail(mEmail) && Validations.length50Empty(mDesc)){
                                ConnectionDB.insertEmail(mEmail, mDesc);   
                                ConnectionDB.emailCandidato(id_User);
                                CargarEmailTable("SELECT correo.id_correo, email, desc_correo FROM correo JOIN correo_candidato ON correo.id_correo= correo_candidato.id_correo");                                
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"El formato del email no es correcto.",
                                    "Error",JOptionPane.ERROR_MESSAGE);
                            }
                        } 
                        break;
                    }
                    case "btnContact":
                    {
                        
                        /*CARGAR LAS TABLAS TELEFONO E EMAIL CON LOS 
                          DATOS DE CONTACTO DE LA PERSONA
                        */
                        
                        CargarEmailTable("SP_Get_Email_Candidato '"+id_User+"'");
                        CargarTelTable("SP_Get_Tel_Candidato '"+id_User+"'"); 
                                                
                        break;
                    }
                    default:
                        break;
                }
            }
            
        }
    }//GEN-LAST:event_applicantsTableMouseClicked

    private void buscarIdTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarIdTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarIdTxtActionPerformed

    private void buscarIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarIDMouseClicked
        int id_user = Integer.parseInt(buscarIdTxt.getText());
        CargarTabla("SELECT * FROM candidato WHERE id_candidato = "+id_user);
        CargarEmailTable("SP_Get_Email_Candidato '"+id_user+"'");
        CargarTelTable("SP_Get_Tel_Candidato '"+id_user+"'"); 
        buscarIdTxt.setText("");
    }//GEN-LAST:event_buscarIDMouseClicked

    private void buscarIdTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarIdTxtKeyPressed
       char validate = evt.getKeyChar();

            if(!Character.isDigit(validate) ){
                getToolkit().beep();
                evt.consume();
                JOptionPane.showMessageDialog(null, "Ingresar solo números");
            }
    }//GEN-LAST:event_buscarIdTxtKeyPressed

    private void showAllUsersBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showAllUsersBtnMouseClicked
        CargarTabla("SP_Show_Candidatos");
        CargarTelTable("SELECT telefono.id_telefono, numero, extension, desc_tel FROM telefono JOIN tel_candidato ON telefono.id_telefono= tel_candidato.id_telefono");
        CargarEmailTable("SELECT correo.id_correo, email, desc_correo FROM correo JOIN correo_candidato ON correo.id_correo= correo_candidato.id_correo");        
        CargarAddressTable("SELECT direccion.id_direccion, calle, num_int, num_ext, nom_col, cp, nom_ciudad, nom_estado FROM candidato "
                + "JOIN dir_candidato ON dir_candidato.id_candidato = candidato.id_candidato "
                + "JOIN direccion ON direccion.id_direccion=dir_candidato.id_direccion "
                + "JOIN dir_colonia ON direccion.id_direccion=dir_colonia.id_direccion "
                + "JOIN colonia ON dir_colonia.id_colonia = colonia.id_colonia "
                + "JOIN colonia_ciudad ON colonia.id_colonia = colonia_ciudad.id_colonia "
                + "JOIN ciudad ON colonia_ciudad.id_ciudad = ciudad.id_ciudad "
                + "JOIN ciudad_estado ON ciudad.id_ciudad=ciudad_estado.id_ciudad "
                + "JOIN estado ON ciudad_estado.id_estado=estado.id_estado");
        
    }//GEN-LAST:event_showAllUsersBtnMouseClicked

    private void buscarNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarNombreMouseClicked
        String Nnombre = buscarNombreTxt.getText();
        
        String consulta = "SP_Search_Candidato_Like '"+Nnombre+"'";
        
        buscarIdTxt.setText("");
        buscarNombreTxt.setText("");
        CargarTabla(consulta);
    }//GEN-LAST:event_buscarNombreMouseClicked

    private void appKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_appKeyTyped
        char validate = evt.getKeyChar();

        if(!Character.isLetter(validate) && !Character.isSpaceChar(validate) && !Character.isISOControl(validate)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo letras");
        }
    }//GEN-LAST:event_appKeyTyped

    private void apmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apmKeyTyped
        char validate = evt.getKeyChar();

        if(!Character.isLetter(validate) && !Character.isSpaceChar(validate) && !Character.isISOControl(validate)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo letras");
        }
    }//GEN-LAST:event_apmKeyTyped

    private void expLabKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_expLabKeyTyped
        char validate = evt.getKeyChar();

        if(!Character.isLetter(validate) && !Character.isDigit(validate) && !Character.isSpaceChar(validate) && !Character.isISOControl(validate)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo letras y números");
        }
    }//GEN-LAST:event_expLabKeyTyped

    private void expSalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_expSalKeyTyped
        char validate = evt.getKeyChar();

        if(!Character.isDigit(validate) && !Character.isSpaceChar(validate) && !Character.isISOControl(validate)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo números");
        }
    }//GEN-LAST:event_expSalKeyTyped

    private void puestoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_puestoKeyTyped
        char validate = evt.getKeyChar();

        if(!Character.isLetter(validate) && !Character.isSpaceChar(validate) && !Character.isISOControl(validate)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo letras");
        }
    }//GEN-LAST:event_puestoKeyTyped

    private void registerButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_registerButtonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerButtonKeyPressed

    private void registerButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseClicked
        ConnectionDB conexion = new ConnectionDB();
        conexion.getConnection();
        
        String name = nombre.getText();
        String lastn = app.getText();
        String lastnm = apm.getText();
        String expLabo = expLab.getText();
        String salario = expSal.getText();
        String puestoP = puesto.getText();
        
        if(Validations.length50Empty(name) && Validations.length50Empty(lastn) &&
                Validations.length50Empty(lastnm) && Validations.length50Empty(expLabo)
                && Validations.length50Empty(salario) && Validations.length50Empty(puestoP)){
            conexion.createApplicant(name, lastn, lastnm, expLabo, salario, puestoP);
            CargarTabla("SP_Show_Candidatos");
            nombre.setText("");
            app.setText("");
            apm.setText("");
            expLab.setText("");
            expSal.setText("");
            puesto.setText("");
        }

        
        
    }//GEN-LAST:event_registerButtonMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void phonesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phonesTableMouseClicked
        int column = phonesTable.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/phonesTable.getRowHeight();

        if(row < phonesTable.getRowCount() && row >= 0 && column<phonesTable.getColumnCount() && column>=0){
            Object  value = phonesTable.getValueAt(row, column);
            Object id = phonesTable.getValueAt(row, 0);
            int id_User = (int) id;
            if(value instanceof JButton){
                ((JButton) value).doClick();
                JButton boton = (JButton) value;
                switch (boton.getName()) {
                    case "btnUpdate":
                        String number = (String) phonesTable.getValueAt(row, 1);
                        String ext    = (String) phonesTable.getValueAt(row, 2);
                        String desc   = (String) phonesTable.getValueAt(row, 3);
                        if(Validations.validateNumbers(number) && Validations.validateNumbers(ext) && Validations.LettersAndLength(desc)){
                            ConnectionDB.updatePhone(id_User, number, ext, desc);
                            CargarTelTable("SELECT telefono.id_telefono, numero, extension, desc_tel FROM telefono JOIN tel_candidato "
                                + "ON telefono.id_telefono= tel_candidato.id_telefono");
                        }
                        
                        break;
                    case "btnDelete":
                        int option = JOptionPane.showConfirmDialog(null, "Do you really want to delete this phone?",
                                    "Warning", JOptionPane.YES_NO_OPTION);
                        if(option == JOptionPane.YES_OPTION){
                            ConnectionDB.CDU("SP_Delete_Tel_Candidato '"+id_User+"'");
                            CargarTelTable("SELECT telefono.id_telefono, numero, extension, desc_tel FROM telefono JOIN tel_candidato "
                                + "ON telefono.id_telefono= tel_candidato.id_telefono");
                        }       
                        break;
                            
                }
            }
        }
    }//GEN-LAST:event_phonesTableMouseClicked

    private void emailTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailTableMouseClicked
        int column = emailTable.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/emailTable.getRowHeight();

        if(row < emailTable.getRowCount() && row >= 0 && column<emailTable.getColumnCount() && column>=0){
            Object  value = emailTable.getValueAt(row, column);
            Object id = emailTable.getValueAt(row, 0);
            int id_User = (int) id;
            if(value instanceof JButton){
                ((JButton) value).doClick();
                JButton boton = (JButton) value;
                switch (boton.getName()) {
                    case "btnUpdate":
                        String email = (String) emailTable.getValueAt(row, 1);
                        String desc    = (String) emailTable.getValueAt(row, 2);
                        if(Validations.validateEmail(email)&& Validations.LettersAndLength(desc)){
                            ConnectionDB.updateEmail(id_User, email, desc);
                            CargarTelTable("SELECT correo.id_correo, email, desc_correo FROM correo "
                                + "JOIN correo_candidato ON correo.id_correo= correo_candidato.id_correo");
                        }
                        break;
                    case "btnDelete":
                        int option = JOptionPane.showConfirmDialog(null, "Do you really want to delete this email?",
                                    "Warning", JOptionPane.YES_NO_OPTION);
                        if(option == JOptionPane.YES_OPTION){
                            ConnectionDB.CDU("DELETE FROM telefono where id_telefono ="+id_User);
                            CargarTelTable("SELECT telefono.id_telefono, numero, extension, desc_tel FROM telefono JOIN tel_candidato "
                                + "ON telefono.id_telefono= tel_candidato.id_telefono");
                        }       
                        break;                   
                }
            }
        }
    }//GEN-LAST:event_emailTableMouseClicked

    private void addressTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addressTableMouseClicked
        int column = addressTable.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/addressTable.getRowHeight();
        
        if(row < addressTable.getRowCount() && row >= 0 && column<addressTable.getColumnCount() && column>=0){
            Object  value = addressTable.getValueAt(row, column);
            Object id = addressTable.getValueAt(row, 0);
            int id_User = (int) id;
            if(value instanceof JButton){
                ((JButton) value).doClick();
                JButton boton = (JButton) value;
                switch (boton.getName()) {
                    case "btnUpdate":
                        String calle   = (String) addressTable.getValueAt(row, 1);
                        String numExt  = (String) addressTable.getValueAt(row, 2);
                        String numInt  = (String) addressTable.getValueAt(row, 3);
                        String colonia = (String) addressTable.getValueAt(row, 4);
                        String cp      = (String) addressTable.getValueAt(row, 5);
                        String ciudad  = (String) addressTable.getValueAt(row, 6);
                        String estado  = (String) addressTable.getValueAt(row, 7);
                        
                        if(Validations.length50Empty(calle) && Validations.validateLettersAndNumbers(numExt)
                                && Validations.validateLettersAndNumbers(numExt) && Validations.validateLettersAndNumbers(colonia)
                                && Validations.validateNumbers(cp) && Validations.LettersAndLength(ciudad)
                                && Validations.validateJustLetters(estado)){
                            ConnectionDB.CDU("SP_Update_Address '"+id_User+"', '"+calle+"', '"+numInt+"', '"+numExt+"', '"
                            +colonia+"', '"+cp+"', '"+ciudad+"', '"+estado+"'");
                            CargarAddressTable("SELECT direccion.id_direccion, calle, num_int, num_ext, nom_col, cp, nom_ciudad, nom_estado "
                                            + "FROM direccion JOIN dir_colonia ON direccion.id_direccion=dir_colonia.id_direccion "
                                            + "JOIN colonia ON dir_colonia.id_colonia = colonia.id_colonia JOIN colonia_ciudad "
                                            + "ON colonia.id_colonia = colonia_ciudad.id_colonia JOIN ciudad "
                                            + "ON colonia_ciudad.id_ciudad = ciudad.id_ciudad JOIN ciudad_estado "
                                            + "ON ciudad.id_ciudad=ciudad_estado.id_ciudad JOIN estado "
                                            + "ON ciudad_estado.id_estado=estado.id_estado");
                        }
                        
                        break;
                    case "btnDelete":
                        int option = JOptionPane.showConfirmDialog(null, "Do you really want to delete this Address?",
                                    "Warning", JOptionPane.YES_NO_OPTION);
                        if(option == JOptionPane.YES_OPTION){
                            ConnectionDB.CDU("DELETE FROM direccion WHERE id_direccion ="+id_User);
                            CargarAddressTable("SELECT direccion.id_direccion, calle, num_int, num_ext, nom_col, cp, nom_ciudad, nom_estado "
                                            + "FROM direccion JOIN dir_colonia ON direccion.id_direccion=dir_colonia.id_direccion "
                                            + "JOIN colonia ON dir_colonia.id_colonia = colonia.id_colonia JOIN colonia_ciudad "
                                            + "ON colonia.id_colonia = colonia_ciudad.id_colonia JOIN ciudad "
                                            + "ON colonia_ciudad.id_ciudad = ciudad.id_ciudad JOIN ciudad_estado "
                                            + "ON ciudad.id_ciudad=ciudad_estado.id_ciudad JOIN estado "
                                            + "ON ciudad_estado.id_estado=estado.id_estado");
                        }       
                        break;                   
                }
            }
        }
        
        
    }//GEN-LAST:event_addressTableMouseClicked

    private void buscarIdTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarIdTxtKeyTyped
        char validate = evt.getKeyChar();

        if(!Character.isDigit(validate) && !Character.isSpaceChar(validate) && !Character.isISOControl(validate)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo números");
        }
    }//GEN-LAST:event_buscarIdTxtKeyTyped

    private void buscarNombreTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarNombreTxtKeyTyped
        char validate = evt.getKeyChar();

        if(!Character.isLetter(validate) && !Character.isSpaceChar(validate) && !Character.isISOControl(validate)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo letras");
        }
    }//GEN-LAST:event_buscarNombreTxtKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Table;
    private javax.swing.JTable addressTable;
    private javax.swing.JTextPane apm;
    private javax.swing.JTextPane app;
    private javax.swing.JTable applicantsTable;
    private javax.swing.JButton buscarID;
    private javax.swing.JTextField buscarIdTxt;
    private javax.swing.JButton buscarNombre;
    private javax.swing.JTextField buscarNombreTxt;
    private javax.swing.JTable emailTable;
    private javax.swing.JTextPane expLab;
    private javax.swing.JTextPane expSal;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextPane nombre;
    private javax.swing.JTable phonesTable;
    private javax.swing.JTextPane puesto;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton showAllUsersBtn;
    // End of variables declaration//GEN-END:variables
}
