/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rrhh;

/**
 *
 * @author Emmanuel
 */

import java.sql.Connection; 
import javax.swing.JOptionPane; 
public class conexion {
    public static void main(String[] args){
        Connection miConexion;
        miConexion=ConnectionDB.getConnection();
        if(miConexion!=null){   
           JOptionPane.showMessageDialog(null, "Conexión Realizada Correctamente");
        }
    }
}
