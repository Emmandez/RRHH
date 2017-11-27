/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rrhh;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;


/**
 *
 * @author Emmanuel
 */
public class Validations {
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
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
    
    public static boolean length50Empty(String string){
        boolean flag = false;
        
        if(string != null && !string.isEmpty()){
            if(string.length()<=50){
                flag = true;
            }
            else{
                JOptionPane.showMessageDialog(null, "La cadena: "+string+" supera el límite de caracteres aceptados");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Hay por lo menos un campo vacío");
        }
        
        return flag;
    }
    
    public static boolean numericPhoneNumber(String str){
        boolean flag = true;
        try{
            Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Introducir sólo números");
            flag=false;
        }
        return flag;
    }
    
    public static boolean validateEmail(String emailStr) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
            return matcher.find();
    }
    
}
