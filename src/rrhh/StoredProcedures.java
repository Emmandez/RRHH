/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rrhh;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static rrhh.ConnectionDB.getConnection;

/**
 *
 * @author Emmanuel
 */
public class StoredProcedures {
    
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
     
    public static void insertUser(String consulta){
        Connection con = getConnection();
        Statement declara;
    }
    
}
