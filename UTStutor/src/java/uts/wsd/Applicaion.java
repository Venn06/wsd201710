/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.xml.bind.*;

/**
 *
 * @author Vennwen
 */
public class Applicaion implements Serializable{
    private String bookingsPath;
    private String studentsPath;
    private String tutorsPath;
    private Bookings bookings;
    private Students students;
    private Tutors tutors;

    public String getBookingsPath() {
        return bookingsPath;
    }

    public void setBookingsPath(String bookingsPath) throws JAXBException, FileNotFoundException, IOException {
        this.bookingsPath = bookingsPath;
        // Create the unmarshaller
        JAXBContext jc = JAXBContext.newInstance(Bookings.class);
        Unmarshaller u = jc.createUnmarshaller();
        // Now unmarshal the object from the file
        FileInputStream fin = new FileInputStream(bookingsPath);
        setBookings((Bookings)u.unmarshal(fin)); // This loads the "bookings" object
        fin.close();
    }

    public String getStudentsPath() {
        return studentsPath;
    }

    public void setStudentsPath(String studentsPath) throws JAXBException, FileNotFoundException, IOException {
        this.studentsPath = studentsPath;
        
        // Create the unmarshaller
        JAXBContext jc = JAXBContext.newInstance(Students.class);
        Unmarshaller u = jc.createUnmarshaller();
        // Now unmarshal the object from the file
        FileInputStream fin = new FileInputStream(studentsPath);
        setStudents((Students)u.unmarshal(fin)); // This loads the "bookings" object
        fin.close();
    }

    public String getTutorsPath() {
        return tutorsPath;
    }

    public void setTutorsPath(String tutorsPath) throws JAXBException, FileNotFoundException, IOException {
        this.tutorsPath = tutorsPath;
        // Create the unmarshaller
        JAXBContext jc = JAXBContext.newInstance(Tutors.class);
        Unmarshaller u = jc.createUnmarshaller();
        // Now unmarshal the object from the file
        FileInputStream fin = new FileInputStream(tutorsPath);
        setTutors((Tutors)u.unmarshal(fin)); // This loads the "bookings" object
        fin.close();
    }

    public Bookings getBookings() {
        return bookings;
    }

    public void setBookings(Bookings bookings) {
        this.bookings = bookings;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public Tutors getTutors() {
        return tutors;
    }

    public void setTutors(Tutors tutors) {
        this.tutors = tutors;
    }
    
    public void updateStudentsXML()throws PropertyException, JAXBException, FileNotFoundException{
        JAXBContext jc = JAXBContext.newInstance(Students.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(this.getStudents(), new  FileOutputStream(studentsPath));
    }
    
    public void updateTutorsXML()throws PropertyException, JAXBException, FileNotFoundException{
        JAXBContext jc = JAXBContext.newInstance(Tutors.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(this.getTutors(), new  FileOutputStream(tutorsPath));
    }
        
    public void updateBookingsXML()throws PropertyException, JAXBException, FileNotFoundException{
        JAXBContext jc = JAXBContext.newInstance(Bookings.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(this.getBookings(), new  FileOutputStream(bookingsPath));
    }
}
