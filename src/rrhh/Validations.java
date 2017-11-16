/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rrhh;

import javax.swing.JOptionPane;

/**
 *
 * @author Emmanuel
 */
public class Validations {
    
    public static boolean userLength(String username, String password){
        boolean flag = false;
        
        if(username.length() <= 20 ){
            if(password.length() <= 20){
                flag = true;
            }
            else{
                JOptionPane.showMessageDialog(null, "La contraseña debe de ser menor de 20 caracteres");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "El nombre de usuario debe de ser menor de 20 caracteres");
        }
        
        return flag;
    }
    
}
