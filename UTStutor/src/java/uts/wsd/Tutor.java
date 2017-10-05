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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Tutor extends User implements Serializable{

    @XmlElement
    private String email;
    private String name;
    private String password;
    private String DOB;
    private String subject;
    private String status;

    public Tutor(){
    
    }
    
    public Tutor(String email, String name, String password, String DOB, String subject, String status) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.DOB = DOB;
        this.subject = subject;
        this.status = status;
    }
    
        public Tutor(String email, String name, String password, String DOB, String subject) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.DOB = DOB;
        this.subject = subject;
        this.status = "available";
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

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public UserType getType() {
        return UserType.Tutor;
    }
    
    public String[] getDetails(){
        return new String[] {"Email", this.email, "Name", this.name,"Password", this.password, "DOB", this.DOB, "Subject", this.subject, "Status", this.status};
    }
    

}
