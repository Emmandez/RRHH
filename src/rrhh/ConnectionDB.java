/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rrhh;


import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;


/**
 *
 * @author Emmanuel
 */
public class ConnectionDB {
    
    public static Connection getConnection(){
        Connection conexion = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url ="jdbc:sqlserver://localhost;databaseName=TecSos;user=sa;password=1234567;";             
            conexion= DriverManager.getConnection(url); 
        }
        catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, ex, "Error1 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);             
            conexion=null;               
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex, "Error2 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);             
            conexion=null; 
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex, "Error3 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);             
            conexion=null; 
        }
        finally{
            return conexion;
        }
    }
    
    //To obtain data from the database because it returns a resultset
    public static ResultSet Query(String consulta){
        Connection con = getConnection();
        Statement declara;
        
        try{
            declara = con.createStatement();
            ResultSet respuesta = declara.executeQuery(consulta);
            return respuesta;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public static boolean Login(String user, String password){ 
        //Stored Procedure
        String consulta = "SP_Login '"+user+"' , '"+password+"'";
        boolean resultado = false;
        
        try{
            ResultSet respuesta = Query(consulta);
            if(respuesta.next()){
                resultado = true;                
            }
            else{
                JOptionPane.showMessageDialog(null, "Usuario o Contraseña Erroneos");
                resultado = false;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
    
    public static boolean existingUser(String user){
        //Stored Procedure
        String consulta = "SP_Search_User '"+user+"'";
        boolean resultado=false;
        try{
            ResultSet respuesta = Query(consulta);
            if(respuesta.next()){
                //Already exists
                resultado=false;
            }
            else{
                //we can create a new user
                resultado = true;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);            
        }
        
        return resultado;
    }
    
    public static boolean existingUserId(int id_user){
        String consulta = "SP_Search_User_ID '"+id_user+"'";
        boolean flag = false;
        try{
            ResultSet respuesta = Query(consulta);
            if(respuesta.next()){
                //Already exists
                flag=false;
            }
            else{
                //we can create a new user
                flag = true;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);            
        }
        
        return flag;
        
    }
    
    //To insert, delete and update. Create, Delete, Update
    public static int CDU(String consulta){
        Connection con = getConnection();
        int respuesta=0;
        
        try{
            PreparedStatement pstmt = con.prepareStatement(consulta);
            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated!=0){
                respuesta=rowsUpdated;
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
        }
        return respuesta;
        //Entasdasdasd ''
        
    }
    
    public static void deleteUser(String user){
        //Stored Procedure
        String consulta = "SP_Delete_User '"+user+"'";
        boolean flag = existingUser(user);
        
        //It exists
        if(flag==false){
            int succeful = CDU(consulta);
            if(succeful!=0){
            JOptionPane.showMessageDialog(null, "Usuario Eliminado correctamente");
            }
            else{
                JOptionPane.showMessageDialog(null, "Algo mal ocurrió");
            }
        }
        //It doesn't exists
        else{
            JOptionPane.showMessageDialog(null,"El usuario no existe");
        }
    }
    
    public static void deleteUserId(int id_user){
        String consulta = "SP_Delete_User_ID '"+id_user+"'";
        boolean flag = existingUserId(id_user);
        
        //it exists
        if(flag==false){
            int succeful = CDU(consulta);
            if(succeful!=0){
            JOptionPane.showMessageDialog(null, "Usuario Eliminado correctamente");
            }
        }
    }
    
    public static void createUser(String username, String password, String role){
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());

        //Stored procedure
        String consulta = "SP_Insert_User '"+username+"' , '"+password+"' , '"+role+"' , '"+timeStamp+"'";
        boolean flag = existingUser(username);
        
        if(flag==true){
            int succeful = CDU(consulta);
            if(succeful!=0){
                JOptionPane.showMessageDialog(null, "Ususario insertado correctamente");
            }
            else{
                JOptionPane.showMessageDialog(null, "Algo mal ocurrió");
            }
        }else{
            JOptionPane.showConfirmDialog(null, "El usuario ya existe");
        }
    }
    
    public static void changeStatus(int id_user){
        String consulta = "SP_CHANGE_STATUS_USER '"+id_user+"'";
        
        int succeful = CDU(consulta);
            if(succeful!=0){
                JOptionPane.showMessageDialog(null, "Usuario actualizado");
            }
            else{
                JOptionPane.showMessageDialog(null, "Algo mal ocurrió");
            }   
    }
    
    public static void updateUser(String username, String password, int id_user){
        String consulta = "SP_Update_User '"+id_user+"', '"+username+"', '"+password+"'";Connection con = getConnection();
        
        int succeful = CDU(consulta);
            if(succeful!=0){
                JOptionPane.showMessageDialog(null, "Usuario actualizado");
            }
            else{
                JOptionPane.showMessageDialog(null, "Algo mal ocurrió");
            }
    }
    
    public static void createApplicant(String name, String lastn, String lastnm, String expLabo, String salario,
            String puesto){
        

        //Stored procedure
        String consulta = "SP_Insert_Candidato '"+name+"' , '"+lastn+"' , '"+lastnm+"' , '"+expLabo+"', '"+salario+"', '"+puesto+"'";

        int succeful = CDU(consulta);
        if(succeful!=0){
            JOptionPane.showMessageDialog(null, "Candidato insertado correctamente");
        }
        else{
            JOptionPane.showMessageDialog(null, "Algo mal ocurrió");
        }
    }
    
    public static void deleteApplicantId(int id_applicant){
        String consulta = "SP_Delete_Candidato '"+id_applicant+"'";
        int succeful = CDU(consulta);
        if(succeful!=0){
        JOptionPane.showMessageDialog(null, "Candidato Eliminado correctamente");
        }
    }
    
    public static void updateApplicant(String name, String lastn, String lastnm, String expLabo, int salario,
            String puesto, int id_candidato){
        String consulta = "SP_Update_Candidato '"+id_candidato+"', '"+name+"', '"+lastn+"', '"+lastnm+"', '"
                +expLabo+"', '"+salario+"', '"+puesto+"'";
        
        int succeful = CDU(consulta);
            if(succeful!=0){
                JOptionPane.showMessageDialog(null, "Candidato actualizado");
            }
            else{
                JOptionPane.showMessageDialog(null, "Algo mal ocurrió");
            }
    }
    
    public static void insertPhone(String number, String ext, String desc){
        String consulta = "SP_Insert_Phone '"+number+"', '"+ext+"', '"+desc+"'";
        int succeful = CDU(consulta);
        if(succeful!=0){

        }
    }
    
    public static void insertEmail(String email, String desc){
        String consulta = "SP_Insert_Email '"+email+"', '"+desc+"'";
        int succeful = CDU(consulta);
        if(succeful==0){
            JOptionPane.showMessageDialog(null, "Algo mal ocurrió");
        }
    }
    
    public static void emailCandidato(int id_candidato){
        String consulta = "SP_Email_Candidato '"+id_candidato+"'";
        int succeful = CDU(consulta);
        if(succeful!=0){
            JOptionPane.showMessageDialog(null, "Se agregó un Email a este candidato");
        }
    }
    
    public static void updatePhone(int id_tel, String number, String ext, String desc){
        String consulta = "SP_Update_Phone '"+id_tel+"', '"+number+"', '"+desc+"', '"+ext+"'";
        int succeful = CDU(consulta);
        if(succeful!=0){
            JOptionPane.showMessageDialog(null, "Teléfono actualizado correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "Error al actualizar Teléfono. Contacte al Administrador");
        }
        
    }
    
    public static void updateEmail(int id_email, String email, String desc){
        String consulta = "SP_Update_Email '"+id_email+"', '"+email+"', '"+desc+"'";
        int succeful = CDU(consulta);
        if(succeful!=0){
            JOptionPane.showMessageDialog(null, "Email Actualizado Correctamente");
        }
        else{
            JOptionPane.showMessageDialog(null, "Error al actualizar Email. Contacte al Administrador");
        }
    }
    
    
    public static void phoneCandidato(int id_candidato){
        String consulta = "SP_Phone_Candidato '"+id_candidato+"'";
        int succeful = CDU(consulta);
        if(succeful!=0){
            JOptionPane.showMessageDialog(null, "Se agregó un número de teléfono a este candidato");
        }
    }
}
