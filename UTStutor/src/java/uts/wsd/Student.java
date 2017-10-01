/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Vennwen
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Student extends User implements Serializable{
    @XmlElement
    private String email;
    private String name;
    private String password;
    private String DOB;
    
    public Student(){
        
    }
    
    public Student(String email, String name, String password, String DOB) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.DOB = DOB;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDOB() {
        return DOB;
    }

    @Override
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

 
    public UserType getType() {
        return UserType.Student;
    }
    
    public String[] getDetails(){
        return new String[] {"Email", this.email, "Name", this.name,"Password", this.password, "DOB", this.DOB};
    }
}