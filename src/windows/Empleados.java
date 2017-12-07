/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.ParseConversionEvent;
import rrhh.ConnectionDB;
import rrhh.Render;
import rrhh.Validations;
import static windows.menu.mainPanel;

/**
 *
 * @author Emmanuel
 */
public class Empleados extends javax.swing.JPanel {

    /**
     * 
     * 
     * 
     */
    public Empleados() {
        initComponents();
        CargarTabla("SELECT * FROM empleado WHERE active_em = 1");
        CargarTelTable("SELECT telefono.id_telefono, numero, extension, desc_tel FROM telefono JOIN tel_empleado ON telefono.id_telefono= tel_empleado.id_telefono");
        CargarEmailTable("SELECT correo.id_correo, email, desc_correo FROM correo JOIN correo_empleado ON correo.id_correo= correo_empleado.id_correo");        
        CargarAddressTable("SELECT direccion.id_direccion, calle, num_int, num_ext, nom_col, cp, nom_ciudad, nom_estado FROM empleado "
                + "JOIN dir_empleado ON dir_empleado.id_empleado = empleado.id_empleado "
                + "JOIN direccion ON direccion.id_direccion=dir_empleado    .id_direccion "
                + "JOIN dir_colonia ON direccion.id_direccion=dir_colonia.id_direccion "
                + "JOIN colonia ON dir_colonia.id_colonia = colonia.id_colonia "
                + "JOIN colonia_ciudad ON colonia.id_colonia = colonia_ciudad.id_colonia "
                + "JOIN ciudad ON colonia_ciudad.id_ciudad = ciudad.id_ciudad "
                + "JOIN ciudad_estado ON ciudad.id_ciudad=ciudad_estado.id_ciudad "
                + "JOIN estado ON ciudad_estado.id_estado=estado.id_estado");
        CargarFaltasTable("SELECT * FROM faltas");
        CargarAsisTable("SELECT * FROM asistencia");
        
    }
    
    public void CargarAsisTable(String query){
        AsistenciasTable.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel modelo = (DefaultTableModel) AsistenciasTable.getModel();
        
        AsistenciasTable.setRowHeight(25);
        
        modelo.setRowCount(0);
        ConnectionDB.getConnection();
        ResultSet res = ConnectionDB.Query(query);
        
        try{
            while(res.next()){
                JButton btnDelete = new JButton("Delete");
                btnDelete.setBackground(Color.red);
                btnDelete.setName("btnDelete");
                
                Vector v = new Vector();
                
                v.add(res.getInt(1));
                v.add(res.getDate(3));
                v.add(res.getInt(2));
                v.add(btnDelete);
                
                modelo.addRow(v);
                AsistenciasTable.setModel(modelo);
            }
        }catch(SQLException e){
            
        }
    }
    
    public void CargarFaltasTable(String query){
        FaltasTable.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel modelo = (DefaultTableModel) FaltasTable.getModel();
        FaltasTable.setRowHeight(25);
        
        modelo.setRowCount(0);
        ConnectionDB.getConnection();
        ResultSet res = ConnectionDB.Query(query);
        
        try{
            while(res.next()){
                JButton btnDelete = new JButton("Delete");
                btnDelete.setBackground(Color.red);
                btnDelete.setName("btnDelete");

                Vector v = new Vector();
                
                v.add(res.getInt(3));
                v.add(res.getInt(1));
                v.add(res.getDate(2));
                
                
                v.add(btnDelete);
                
                
                modelo.addRow(v);
                FaltasTable.setModel(modelo);
            }
        }catch(SQLException e){
        }
        
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
        phonesTable.setDragEnabled(true);
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
        addressTable.setRowHeight(25);
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
        empleadosTable.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel modelo = (DefaultTableModel) empleadosTable.getModel();
        empleadosTable.setRowHeight(25);
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
                v.add(res.getString(6));
                v.add(res.getString(7));
                v.add(res.getString(8));
                v.add(res.getString(9));
                //add email, phone and address
                v.add(btnAddress);
                v.add(btnEmail);
                v.add(btnPhone);
                //delete, hire and update
                v.add(btnUpdate);
                v.add(btnDelete);
                
                modelo.addRow(v);

                empleadosTable.setModel(modelo);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        empleadosTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        buscarIdTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        buscarID = new javax.swing.JButton();
        buscarNombreTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        buscarNombre = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        showAllUsersBtn = new javax.swing.JButton();
        AddEmployee = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        phonesTable = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        emailTable = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        addressTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        FaltasTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        AsistenciasTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        AddFalta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        cantidadAsistencias = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        FaltasTotals = new javax.swing.JTextField();

        setBackground(new java.awt.Color(0, 0, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Table.setBackground(new java.awt.Color(153, 153, 255));
        Table.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        empleadosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Apellido P", "Apellido M", "RFC", "Sueldo", "Puesto", "Horario", "Genero", "Address", "Email", "Phone", "Update", "Delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        empleadosTable.getTableHeader().setReorderingAllowed(false);
        empleadosTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empleadosTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(empleadosTable);
        if (empleadosTable.getColumnModel().getColumnCount() > 0) {
            empleadosTable.getColumnModel().getColumn(0).setResizable(false);
            empleadosTable.getColumnModel().getColumn(0).setPreferredWidth(5);
            empleadosTable.getColumnModel().getColumn(1).setResizable(false);
            empleadosTable.getColumnModel().getColumn(2).setResizable(false);
            empleadosTable.getColumnModel().getColumn(3).setResizable(false);
            empleadosTable.getColumnModel().getColumn(4).setResizable(false);
            empleadosTable.getColumnModel().getColumn(4).setPreferredWidth(10);
            empleadosTable.getColumnModel().getColumn(5).setResizable(false);
            empleadosTable.getColumnModel().getColumn(5).setPreferredWidth(10);
            empleadosTable.getColumnModel().getColumn(6).setResizable(false);
            empleadosTable.getColumnModel().getColumn(7).setResizable(false);
            empleadosTable.getColumnModel().getColumn(8).setResizable(false);
            empleadosTable.getColumnModel().getColumn(9).setResizable(false);
            empleadosTable.getColumnModel().getColumn(9).setPreferredWidth(9);
            empleadosTable.getColumnModel().getColumn(10).setResizable(false);
            empleadosTable.getColumnModel().getColumn(10).setPreferredWidth(9);
            empleadosTable.getColumnModel().getColumn(11).setResizable(false);
            empleadosTable.getColumnModel().getColumn(11).setPreferredWidth(9);
            empleadosTable.getColumnModel().getColumn(12).setResizable(false);
            empleadosTable.getColumnModel().getColumn(12).setPreferredWidth(9);
            empleadosTable.getColumnModel().getColumn(13).setResizable(false);
            empleadosTable.getColumnModel().getColumn(13).setPreferredWidth(9);
        }

        Table.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1350, 100));

        jLabel2.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Teléfonos");
        Table.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, -1, 30));

        buscarIdTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarIdTxtActionPerformed(evt);
            }
        });
        buscarIdTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscarIdTxtKeyPressed(evt);
            }
        });
        Table.add(buscarIdTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 196, -1));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Buscar Empleado por ID");
        Table.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        buscarID.setText("Buscar");
        buscarID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarIDMouseClicked(evt);
            }
        });
        Table.add(buscarID, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, -1, -1));
        Table.add(buscarNombreTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 200, -1));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Buscar Empleado por nombre");
        Table.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        buscarNombre.setText("Buscar");
        buscarNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarNombreMouseClicked(evt);
            }
        });
        buscarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarNombreActionPerformed(evt);
            }
        });
        Table.add(buscarNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel1.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Empleados Activos");
        Table.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, -1, 30));

        showAllUsersBtn.setText("Mostrar Todos");
        showAllUsersBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showAllUsersBtnMouseClicked(evt);
            }
        });
        showAllUsersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showAllUsersBtnActionPerformed(evt);
            }
        });
        Table.add(showAllUsersBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 120, -1));

        AddEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_user.png"))); // NOI18N
        AddEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddEmployeeMouseClicked(evt);
            }
        });
        AddEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddEmployeeActionPerformed(evt);
            }
        });
        Table.add(AddEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 10, -1, -1));

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
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
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
            phonesTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        Table.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 410, 150));

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
            emailTable.getColumnModel().getColumn(0).setPreferredWidth(7);
        }

        Table.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 400, 150));

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
            addressTable.getColumnModel().getColumn(0).setPreferredWidth(6);
        }

        Table.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 1080, 150));

        jLabel6.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Dirección");
        Table.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 570, 100, -1));

        FaltasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Folio", "Empleado", "Fecha", "Delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        FaltasTable.setRowHeight(25);
        FaltasTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FaltasTableMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(FaltasTable);
        if (FaltasTable.getColumnModel().getColumnCount() > 0) {
            FaltasTable.getColumnModel().getColumn(0).setResizable(false);
            FaltasTable.getColumnModel().getColumn(1).setResizable(false);
            FaltasTable.getColumnModel().getColumn(2).setResizable(false);
            FaltasTable.getColumnModel().getColumn(3).setResizable(false);
        }

        Table.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 240, 310, 150));

        jLabel4.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Email");
        Table.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, -1, 30));

        AsistenciasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Folio", "Fecha", "Empleado", "Delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        AsistenciasTable.setRowHeight(25);
        AsistenciasTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AsistenciasTableMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(AsistenciasTable);
        if (AsistenciasTable.getColumnModel().getColumnCount() > 0) {
            AsistenciasTable.getColumnModel().getColumn(0).setResizable(false);
            AsistenciasTable.getColumnModel().getColumn(1).setResizable(false);
            AsistenciasTable.getColumnModel().getColumn(2).setResizable(false);
            AsistenciasTable.getColumnModel().getColumn(3).setResizable(false);
        }

        Table.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 240, 310, 150));

        jLabel5.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Faltas");
        Table.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, -1, 30));

        jLabel7.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Asistencias");
        Table.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 210, -1, 30));

        AddFalta.setText("Agregar Falta");
        AddFalta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddFaltaMouseClicked(evt);
            }
        });
        Table.add(AddFalta, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 390, 140, -1));

        jScrollPane1.setViewportView(cantidadAsistencias);
        cantidadAsistencias.setEnabled(false);

        Table.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 250, 160, -1));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Asistencias Totales");
        Table.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 230, -1, -1));

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Faltas Totales");
        Table.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 320, -1, -1));
        Table.add(FaltasTotals, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 340, 150, -1));
        FaltasTotals.setEnabled(false);

        add(Table, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));
    }// </editor-fold>//GEN-END:initComponents

    
    private void showAllUsersBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showAllUsersBtnMouseClicked
        CargarTabla("SELECT * FROM empleado WHERE active_em = 1");
        CargarTelTable("SELECT telefono.id_telefono, numero, extension, desc_tel FROM telefono JOIN tel_empleado ON telefono.id_telefono= tel_empleado.id_telefono");
        CargarEmailTable("SELECT correo.id_correo, email, desc_correo FROM correo JOIN correo_empleado ON correo.id_correo= correo_empleado.id_correo");        
        CargarAddressTable("SELECT direccion.id_direccion, calle, num_int, num_ext, nom_col, cp, nom_ciudad, nom_estado FROM empleado "
                + "JOIN dir_empleado ON dir_empleado.id_empleado = empleado.id_empleado "
                + "JOIN direccion ON direccion.id_direccion=dir_empleado    .id_direccion "
                + "JOIN dir_colonia ON direccion.id_direccion=dir_colonia.id_direccion "
                + "JOIN colonia ON dir_colonia.id_colonia = colonia.id_colonia "
                + "JOIN colonia_ciudad ON colonia.id_colonia = colonia_ciudad.id_colonia "
                + "JOIN ciudad ON colonia_ciudad.id_ciudad = ciudad.id_ciudad "
                + "JOIN ciudad_estado ON ciudad.id_ciudad=ciudad_estado.id_ciudad "
                + "JOIN estado ON ciudad_estado.id_estado=estado.id_estado");
        
        CargarAsisTable("SELECT * FROM asistencia");
        CargarFaltasTable("SELECT * FROM faltas");
        
        
        cantidadAsistencias.setText("");
        FaltasTotals.setText("");
        
    }//GEN-LAST:event_showAllUsersBtnMouseClicked

    private void buscarNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarNombreMouseClicked
        String username = buscarNombreTxt.getText();
        String consulta = "SP_Search_Empleado_Like '"+username+"'";
        
        ResultSet res = ConnectionDB.Query(consulta);
        try{
            if(res.next()){
                CargarTabla(consulta);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No hay nadie con ese nombre", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        buscarIdTxt.setText("");
        buscarNombreTxt.setText("");
        
        
    }//GEN-LAST:event_buscarNombreMouseClicked

    private void buscarIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarIDMouseClicked

        int id_user = Integer.parseInt(buscarIdTxt.getText());
        String consulta = "SELECT * FROM empleado WHERE id_empleado = "+id_user;
        
        ResultSet res = ConnectionDB.Query(consulta);
        try{
            if(res.next()){
                CargarTabla(consulta);
                CargarEmailTable("SP_Get_Email_Empleado '"+id_user+"'");
                CargarTelTable("SP_Get_Tel_Empleado '"+id_user+"'"); 
                CargarAddressTable("SP_Get_Add_Empleado '"+id_user+"'");
                buscarIdTxt.setText("");
                buscarNombreTxt.setText("");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No hay nadie con ese ID", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_buscarIDMouseClicked

    private void buscarIdTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarIdTxtKeyPressed
        char validate = evt.getKeyChar();

        //        if(!Character.isDigit(validate)){
            //            getToolkit().beep();
            //            evt.consume();
            //            JOptionPane.showMessageDialog(null, "Ingresar solo números");
            //        }
    }//GEN-LAST:event_buscarIdTxtKeyPressed

    private void buscarIdTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarIdTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarIdTxtActionPerformed

    private void empleadosTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empleadosTableMouseClicked
        int column = empleadosTable.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/empleadosTable.getRowHeight();

        if(row < empleadosTable.getRowCount() && row >= 0 && column<empleadosTable.getColumnCount() && column>=0){
            
            Object  value = empleadosTable.getValueAt(row, column);//Casilla click
            Object id = empleadosTable.getValueAt(row, 0);//ID
            int id_User = (int) id; //convirtiendo objeto a int
            
            CargarEmailTable("SP_Get_Email_Empleado '"+id_User+"'");
            CargarTelTable("SP_Get_Tel_Empleado '"+id_User+"'"); 
            CargarAddressTable("SP_Get_Add_Empleado '"+id_User+"'");
            CargarFaltasTable("SELECT * FROM faltas WHERE id_empleado = "+id_User);
            CargarAsisTable("SELECT * FROM asistencia WHERE id_empleado ="+id_User);
            
            
            if(value instanceof JButton){
                ((JButton) value).doClick();
                JButton boton = (JButton) value;//Crear botón y pasar el valor 
                
                if(boton.getName().equals("btnEquipo")){

                }
                
                switch (boton.getName()) {
                    case "btnDelete":
                    {
                        //Query para borrar
                        int option = JOptionPane.showConfirmDialog(null, "Do you really want to delete this user?",
                            "Warning", JOptionPane.YES_NO_OPTION);
                        if(option == JOptionPane.YES_OPTION){
                            ConnectionDB.deleteApplicantId(id_User);
                            CargarTabla("SELECT * FROM empleado WHERE active_em=1");
                        }
                        break;
                    }
                    case "btnUpdate":
                    String nname = (String) empleadosTable.getValueAt(row, 1);
                    String nlastn = (String) empleadosTable.getValueAt(row, 2);
                    String nlastnm = (String) empleadosTable.getValueAt(row, 3);
                    String rfc = (String) empleadosTable.getValueAt(row, 4);
                    String nsueldo = (String) empleadosTable.getValueAt(row, 5);
                    String puestoP = (String) empleadosTable.getValueAt(row, 6);
                    //Campo para horario
                    String genero = (String) empleadosTable.getValueAt(row, 7);
                    
                    if(Validations.LettersAndLength(nname) && Validations.LettersAndLength(nlastn) 
                            && Validations.LettersAndLength(nlastnm) && Validations.validateLettersAndNumbers(rfc)
                            && Validations.validateNumbers(nsueldo) && Validations.LettersAndLength(puestoP) 
                            ){
                        if((genero.equals("Masculino") || genero.equals("Femenino"))){
                            int sal = Integer.parseInt(nsueldo);
                            String consulta = "SP_Update_Empleado '"+id_User+"', '"+nname+"', '"+nlastn
                                    +"', '"+nlastnm+"', '"+rfc+"', '"+sal+"', '"+puestoP+"', '"+genero+"'";
                            ConnectionDB.CDU(consulta);
                            CargarTabla("SELECT * FROM empleado WHERE active_em=1");
                            JOptionPane.showMessageDialog(null, "Empleado Actualizado", "Éxito!" , JOptionPane.OK_OPTION);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "El género debe de ser Femenino o Masculino"
                                    + "","Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    
                    
                    break;
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
                            
                            //obtener datos de los campos
                            //String nombreVariable = nombreCampo.getText();
                            String numeroTel = phoneN.getText();
                            String descTel = desc.getText();
                            String extension = ext.getText();
                            
                            String x = String.valueOf(genero_J.getSelectedItem());
                            
                            //conexion.metodoEquipo();

                            if(Validations.numericPhoneNumber(numeroTel)){
                                ConnectionDB.insertPhone(numeroTel, extension, descTel);
                                //conexión.consulta()
                                ConnectionDB.phoneCandidato(id_User);
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

                            if(Validations.validateEmail(mEmail)){
                                ConnectionDB.insertEmail(mEmail, mDesc);
                                ConnectionDB.emailCandidato(id_User);
                            }
                        }
                        break;
                    }
                    case "btnContact":
                    {

                        break;
                    }
                    default:
                    break;
                }
            }

        }
    }//GEN-LAST:event_empleadosTableMouseClicked

    private void AddEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddEmployeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddEmployeeActionPerformed

    private void showAllUsersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showAllUsersBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showAllUsersBtnActionPerformed

    private void AddEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddEmployeeMouseClicked
        //JOPTIONPANE PARA MOSTRAR CAMPOS PARA AÑADIR AL EMPLEADO
        //vALIDAR LOS CAMPOS CON LOS MÉTODOS EN VALIDATIONS
        JFrame frame = new JFrame("Agregar Empleado");
        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(0, 2, 2, 2));
        
        JTextField nombre_J = new JTextField();
        JTextField app_J = new JTextField();
        JTextField apm_J = new JTextField();
        JTextField rfc_j = new JTextField();
        JTextField sueldo_J = new JTextField();
        JTextField puesto_J = new JTextField();
        
        String[] genArray = {"Género","Femenino","Masculino"};//Opciones
        JComboBox genero_J = new JComboBox(genArray);
        
        pane.add(new JLabel("Nombre: "));
        pane.add(nombre_J);
        
        pane.add(new JLabel("Apellido Paterno: "));
        pane.add(app_J);
        
        pane.add(new JLabel("Apellido Materno: "));
        pane.add(apm_J);
        
        pane.add(new JLabel("RFC: "));
        pane.add(rfc_j);
        
        pane.add(new JLabel("Sueldo: "));
        pane.add(sueldo_J);
        
        pane.add(new JLabel("Puesto: "));
        pane.add(puesto_J);
        
        pane.add(genero_J);
        
        int option = JOptionPane.showConfirmDialog(frame, pane, "Agregar Empleado", JOptionPane.OK_OPTION);
        
        if(option==JOptionPane.OK_OPTION){
            String nombre = nombre_J.getText();
            String app = app_J.getText();
            String apm = apm_J.getText();
            String rfc = rfc_j.getText();
            String sueldo = sueldo_J.getText();
            String puesto = puesto_J.getText();
            
            //String variable = String.valueOf(X.getSelectedItem());
            String genero = String.valueOf(genero_J.getSelectedItem());
            
            if(Validations.LettersAndLength(nombre) && Validations.LettersAndLength(app)
                    && Validations.LettersAndLength(apm) && Validations.validateLettersAndNumbers(rfc)
                    && Validations.validateNumbers(sueldo) && Validations.LettersAndLength(puesto)){
                if(!genero.equals("Género")){
                    String consulta = "SP_Insert_Empleado '"+nombre+"', '"+app+"', '"+apm+"', '"
                                            +rfc+"', '"+sueldo+"','"+puesto+"', '"+genero+"'";
                    ConnectionDB.CDU(consulta);
                    CargarTabla("SELECT * FROM empleado WHERE active_em = 1");
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"Por favor selecciona un Género de la lista","Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        
    }//GEN-LAST:event_AddEmployeeMouseClicked

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
                
                JTextField x = new JTextField(); //campo de texto
                
                String[] status = {"bueno", "regular", "malo"};
                JComboBox status_J = new JComboBox(genArray);
                
                switch (boton.getName()) {
                    case "btnUpdate":
                    String number = (String) phonesTable.getValueAt(row, 1);
                    String ext    = (String) phonesTable.getValueAt(row, 2);
                    String desc   = (String) phonesTable.getValueAt(row, 3);
                    if(Validations.validateNumbers(number) && Validations.validateNumbers(ext) && Validations.LettersAndLength(desc)){
                        ConnectionDB.updatePhone(id_User, number, ext, desc);
                        CargarTelTable("SELECT telefono.id_telefono, numero, extension, desc_tel FROM telefono JOIN tel_empleado "
                            + "ON telefono.id_telefono= tel_empleado.id_telefono");
                        
                    }

                    break;
                    case "btnDelete":
                    int option = JOptionPane.showConfirmDialog(null, "Do you really want to delete this phone?",
                        "Warning", JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION){
                        ConnectionDB.CDU("SP_Delete_Tel_Candidato '"+id_User+"'");
                        CargarTelTable("SELECT telefono.id_telefono, numero, extension, desc_tel FROM telefono JOIN tel_empleado "
                            + "ON telefono.id_telefono= tel_empleado.id_telefono");
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
                        CargarEmailTable("SELECT correo.id_correo, email, desc_correo FROM correo "
                            + "JOIN correo_empleado ON correo.id_correo= correo_empleado.id_correo");
                    }
                    break;
                    case "btnDelete":
                    int option = JOptionPane.showConfirmDialog(null, "Do you really want to delete this email?",
                        "Warning", JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION){
                        ConnectionDB.CDU("DELETE FROM telefono where id_telefono ="+id_User);
                        CargarEmailTable("SELECT correo.id_correo, email, desc_correo FROM correo "
                            + "JOIN correo_empleado ON correo.id_correo= correo_empleado.id_correo");
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

    private void FaltasTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FaltasTableMouseClicked
        int column = FaltasTable.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/FaltasTable.getRowHeight(); 

        if(row < FaltasTable.getRowCount() && row >= 0 && column<FaltasTable.getColumnCount() && column>=0){
            Object value = FaltasTable.getValueAt(row, column);
            Object id = FaltasTable.getValueAt(row, 1);
            Object folio = FaltasTable.getValueAt(row, 0);
            
            int fol = (int) folio;
            
            int id_user = (int) id;
            
            CargarTabla("SELECT * FROM empleado WHERE id_empleado = "+id_user+" and active_em = 1");
            CargarFaltasTable("SELECT * FROM faltas WHERE id_empleado = "+id_user);
            CargarEmailTable("SP_Get_Email_Empleado '"+id_user+"'");
            CargarTelTable("SP_Get_Tel_Empleado '"+id_user+"'"); 
            CargarAddressTable("SP_Get_Add_Empleado '"+id_user+"'");
            CargarAsisTable("SELECT * FROM asistencia WHERE id_empleado ="+id_user);
            
            ResultSet res = ConnectionDB.Query("SP_COUNT_FALTAS '"+id_user+"'");
            
            try{
                if(res.next()){
                    FaltasTotals.setText(String.valueOf(res.getInt(1)));
                }
            }catch(SQLException e){
            }
            
            if(value instanceof JButton){
                ((JButton) value).doClick();
                JButton boton = (JButton) value;
                
                switch(boton.getName()){
                    case "btnDelete":
                        
                        int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar la falta?", "Falta", JOptionPane.YES_NO_OPTION);
                        
                        if(option == JOptionPane.YES_OPTION){
                            ConnectionDB.CDU("DELETE FROM faltas WHERE folio_falta = "+fol);
                        }
                        break;       
                }
            }   
        }
    }//GEN-LAST:event_FaltasTableMouseClicked

    private void AsistenciasTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AsistenciasTableMouseClicked
        int column = AsistenciasTable.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/AsistenciasTable.getRowHeight(); 
        
        if(row < AsistenciasTable.getRowCount() && row >= 0 && column<AsistenciasTable.getColumnCount() && column>=0){
            Object value = AsistenciasTable.getValueAt(row, column);
            Object id = AsistenciasTable.getValueAt(row, 2);
            
            int id_user = (int) id;
            
            CargarTabla("SELECT * FROM empleado WHERE id_empleado = "+id_user+" and active_em = 1");
            CargarFaltasTable("SELECT * FROM faltas WHERE id_empleado = "+id_user);
            CargarEmailTable("SP_Get_Email_Empleado '"+id_user+"'");
            CargarTelTable("SP_Get_Tel_Empleado '"+id_user+"'"); 
            CargarAddressTable("SP_Get_Add_Empleado '"+id_user+"'");
            
            ResultSet res = ConnectionDB.Query("SP_COUNT_ASISTENCIAS '"+id_user+"'");
            
            try{
                if(res.next()){
                    cantidadAsistencias.setText(String.valueOf(res.getInt(1)));
                }
            }catch(SQLException e){
            }
            
            if(value instanceof JButton){
                ((JButton) value).doClick();
                JButton boton = (JButton) value;
                
                switch(boton.getName()){
                    case "btnDelete":
                        
                        int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar la falta?", "Falta", JOptionPane.YES_NO_OPTION);
                        
                        if(option == JOptionPane.YES_OPTION){
                            ConnectionDB.CDU("DELETE FROM asistencias WHERE  = "+id_user);
                            
                        }
                        break;       
                }
            }   
            
            
        
        }
    }//GEN-LAST:event_AsistenciasTableMouseClicked

    private void AddFaltaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddFaltaMouseClicked
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        //String consulta = "INSERT INTO faltas"
        JFrame frame = new JFrame("Agregar Telefono");
        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(0, 2, 2, 2));
        
        JTextField idEmpleadoJ = new JTextField();
        JTextField fecha_J = new JTextField();
        fecha_J.setText("2017-12-4");
        
        pane.add(new JLabel("id Empleado: "));
        pane.add(idEmpleadoJ);
        
        pane.add(new JLabel("Fecha Falta"));
        pane.add(fecha_J);
        
        int option = JOptionPane.showConfirmDialog(frame, pane, "Falta", JOptionPane.OK_OPTION);
        if(option==JOptionPane.OK_OPTION){
            int id_empleado = Integer.parseInt(idEmpleadoJ.getText());
            String fecha = fecha_J.getText();
            
            String consulta = "SP_Insert_Falta '"+id_empleado+"', '"+fecha+"'";
            
            ConnectionDB.CDU(consulta);
            CargarFaltasTable("SELECT * FROM faltas");
            
        }
        else{
            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
    }//GEN-LAST:event_AddFaltaMouseClicked

    private void buscarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarNombreActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddEmployee;
    private javax.swing.JButton AddFalta;
    private javax.swing.JTable AsistenciasTable;
    private javax.swing.JTable FaltasTable;
    private javax.swing.JTextField FaltasTotals;
    private javax.swing.JPanel Table;
    private javax.swing.JTable addressTable;
    private javax.swing.JButton buscarID;
    private javax.swing.JTextField buscarIdTxt;
    private javax.swing.JButton buscarNombre;
    private javax.swing.JTextField buscarNombreTxt;
    private javax.swing.JTextPane cantidadAsistencias;
    private javax.swing.JTable emailTable;
    private javax.swing.JTable empleadosTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable phonesTable;
    private javax.swing.JButton showAllUsersBtn;
    // End of variables declaration//GEN-END:variables
}
