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
import java.util.List;

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
}
