/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.*;


/**
 *
 * @author Vennwen
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tutors")
public class Tutors implements Serializable{
    @XmlElement(name = "tutor")
    private ArrayList<Tutor> list;
    
    public Tutors(){
        this.list = new ArrayList<Tutor>();
    }

    public ArrayList<Tutor> getList() {
        return list;
    }

    public void setList(ArrayList<Tutor> list) {
        this.list = list;
    }
    
    public void addTutor(Tutor tutor){
        list.add(tutor);
    }
    
    public Tutor getTutor(String email){
        for(Tutor tutor: list){
            if(tutor.getEmail().equals(email)){
                return tutor;
            }
        }
        return null;
    }
    
    public Tutor login(String email, String password){
        for(Tutor tutor: list){
            if(tutor.getEmail().equals(email) && tutor.getPassword().equals(password)){
                return tutor;
            }
        }
        return null;
    }
}
