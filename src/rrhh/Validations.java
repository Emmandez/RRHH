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
    
    /**
    * Método para verificar que el nombre de usuario y la contraseña tengan una longitud entre 0 y 20
    * @param username  Nombre de usuario
    * @param password  contraseña
    * @author: Emmanuel Hernández Morán
    * @return Boolean. Regresa un valor booleano si la cadena coincide con el formato
    * @version: 1
    */
    public static boolean userLength(String username, String password){
        boolean flag = false;
        
        if(username.length() <= 20 && username.length()>0 ){
            if(password.length() <= 20 && password.length() >0){
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
    
    /**
    * Método para verificar que una cadena contenga sólo letras y tenga una longitud menor a 50. 
    * Se hace uso de Matcher
    * @param string es la cadena que se evaluará
    * @author: Emmanuel Hernández Morán
    * @return Boolean. Regresa un valor booleano si la cadena coincide con el formato
    * @version: 1
    */
    public static boolean length50Empty(String string){
        boolean flag = false;
        
        if(string != null && !string.isEmpty()){
            if(validateJustLetters(string) && string.length()<=50){
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
    
    /**
    * Método para verificar que una cadena contenga sólo letras y tenga una longitud menor a la establecida. 
    * Se hace uso de Matcher
    * @param str es la cadena que se evaluará
    * @author: Emmanuel Hernández Morán
    * @return Boolean. Regresa un valor booleano si la cadena coincide con el formato
    * @version: 1
    */
    public static boolean LettersAndLength(String str){
        boolean flag = false;
        if(str.matches(".*[a_zA_ZáéíóúÁÉÍÓÚ]*.") && str.length()<20)
            flag = true;
        return flag;
    }
    
  
    /**
    * Método para verificar que una cadena tenga una longitud menor o igual a 5. Se hace uso de Matcher
    * @param string es la cadena que se evaluará
    * @author: Emmanuel Hernández Morán
    * @return Boolean. Regresa un valor booleano si la cadena coincide con el formato
    * @version: 1
    */
    public static boolean length5Empty(String string){
        boolean flag = false;
        
        if(string != null && !string.isEmpty()){
            if(string.length()<=5){
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
    
    /**
    * Método para verificar que una cadena contenga sólo números. Se hace uso de Matcher
    * @param str es la cadena que se evaluará
    * @author: Emmanuel Hernández Morán
    * @return Boolean. Regresa un valor booleano si la cadena coincide con el formato
    * @version: 1
    * @deprecated Se puede reemplazar por el método validateNumbers
    */
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
    
    /**
    * Método para verificar que una cadena contenga sólo Letras. Se hace uso de Matcher
    * @param str es la cadena que se evaluará
    * @author: Emmanuel Hernández Morán
    * @return Boolean. Regresa un valor booleano si la cadena coincide con el formato
    * @version: 1
    */
    public static boolean validateJustLetters(String str){
        boolean flag = false;
        if(str.matches(".*[a_zA_Z]*."))
            flag = true;
            
        return flag;
    }
    
    /**
    * Método para verificar que una cadena contenga sólo números. Se hace uso de Matcher
    * @param str es la cadena que se evaluará
    * @author: Emmanuel Hernández Morán
    * @return Boolean. Regresa un valor booleano si la cadena coincide con el formato
    * @version: 1
    */
    public static boolean validateNumbers(String str){
        boolean flag=false;
        if(str.matches(".*[0_9]*."))
            flag=true;
        
        return flag;
    }
    
    
    /**
    * Método para verificar que una cadena sólo contenga letras y números. Se hace uso de Matcher
    * @param str es la cadena que se evaluará
    * @author: Emmanuel Hernández Morán
    * @return Boolean. Regresa un valor booleano si la cadena coincide con el formato
    * @version: 1
    */
    public static boolean validateLettersAndNumbers(String str){
        boolean flag = false;
        if(str.matches(".*[a_zA_Z0_9]*."))
            flag=true;
        return flag;
    }
    
    /**
    * Método para verificar que un email tenga el formato x@x.com. Se hace uso de Matcher
    * @param emailStr es la cadena que se evaluará
    * @author: Emmanuel Hernández Morán
    * @version: 1
    */
    public static boolean validateEmail(String emailStr) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
            return matcher.find();
    }
    
}
