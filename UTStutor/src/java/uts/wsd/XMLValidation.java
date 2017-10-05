/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Vennwen
 */
public class XMLValidation {
    
    public String emailValidation(String email){
        String result = "";
        Pattern pattern = Pattern.compile("[A-Za-z0-9\\.]+@[a-z\\-]+(\\.[a-z]+)+");
        Matcher matcher = pattern.matcher(email);
        if(!matcher.find()){
            result = "The format of email is not correct! ";
        }
        return result;
    }
    
    public String nameValidation(String name){
        String result = "";
        Pattern pattern = Pattern.compile("[A-Z][a-z]+( [A-Z][a-z]+)*");
        Matcher matcher = pattern.matcher(name);
        if(!matcher.find()){
            result = "The format of email is not correct! ";
        }
        return result;
    }
    
    public String passwordValidation(String password){
        String result = "";
        Pattern pattern = Pattern.compile(".{6,16}");
        Matcher matcher = pattern.matcher(password);
        if(!matcher.find()){
            result = "The format of password is not correct! ";
        }
        return result;
    }
    
    public String validationAll(String email, String name, String password){
        return this.emailValidation(email) + this.nameValidation(name) + this.passwordValidation(password);
    }
}
