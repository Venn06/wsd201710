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
@XmlRootElement(name = "students", namespace = "http://www.uts.edu.au/31284/wsd-students")
@XmlAccessorType(XmlAccessType.FIELD)
public class Students implements Serializable {

    @XmlElement(name = "student")
    private ArrayList<Student> list;

    public Students() {
        this.list = new ArrayList<Student>();
    }

    public ArrayList<Student> getList() {
        return list;
    }

    public void setList(ArrayList<Student> list) {
        this.list = list;
    }

    public void addStudent(Student student) {
        list.add(student);
    }

    /**
     *
     * @param email - email id
     * @return
     */
    public Student getStudent(String email) {
        for (Student student : list) {
            if (student.getEmail().equals(email)) {
                return student;
            }
        }
        return null;
    }

    /**
     *
     * @param email - email id
     * @param password - password id
     * @return
     */
    public Student login(String email, String password) {
        for (Student student : list) {
            if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }

    public void removeStudent(String email) {
        list.remove(this.getStudent(email));
    }
}
