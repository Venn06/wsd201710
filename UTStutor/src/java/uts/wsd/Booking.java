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
public class Booking implements Serializable{
    @XmlElement
    private int bookingID;
    private String tutorEmail;
    private String tutorName;
    private String subjectName;
    private String studentEmail;
    private String studentName;
    private String status;

    public Booking(int bookingID, String tutorEmail, String tutorName, String subjectName, String studentEmail, String studentName, String status) {
        this.bookingID = bookingID;
        this.tutorEmail = tutorEmail;
        this.tutorName = tutorName;
        this.subjectName = subjectName;
        this.studentEmail = studentEmail;
        this.studentName = studentName;
        this.status = status;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getTutorEmail() {
        return tutorEmail;
    }

    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
