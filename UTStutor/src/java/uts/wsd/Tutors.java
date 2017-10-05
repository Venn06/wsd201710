/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;


/**
 *
 * @author Vennwen
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tutors", namespace="http://www.uts.edu.au/31284/wsd-tutors")
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
    
    public ArrayList<Tutor> searchTutors(String email, String name, String DOB, String subject, String status){
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        for(Tutor tutor: list){
            if(email != null && !email.equals("")&& !tutor.getEmail().equals(email)){
                continue;
            }
            if(name != null && !tutor.getName().equals("") &&!tutor.getName().equals(name)){
                continue;
            }
            if(DOB != null && !tutor.getDOB().equals("") && !tutor.getDOB().equals(DOB)){
                continue;
            }
            if(subject != null && !tutor.getSubject().equals("") && !tutor.getSubject().equals(subject)){
                continue;
            }
            if(status != null && !tutor.getStatus().equals("") &&!tutor.getStatus().equals(status)){
                continue;
            }
            tutors.add(tutor);
        }
        return tutors;
    }
   
    
    public Tutor login(String email, String password){
        for(Tutor tutor: list){
            if(tutor.getEmail().equals(email) && tutor.getPassword().equals(password)){
                return tutor;
            }
        }
        return null;
    }
    
    public void removeTutor(String email){
        list.remove(this.getTutor(email));
    }
    
}
