/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

/**
 *
 * @author Vennwen
 */
public abstract class User {

    public abstract String[] getDetails();

    public abstract String getName();

    public abstract void setName(String name);

    public abstract String getEmail();

    public abstract void setEmail(String email);

    public abstract String getPassword();

    public abstract void setPassword(String password);

    public abstract String getDOB();

    public abstract void setDOB(String DOB);

    public String getSubject() {
        return null;
    }

    public void setSubject(String subject) {

    }

    public String getStatus() {
        return null;
    }

    public void setStatus(String status) {
    }

    public abstract UserType getType();
}
