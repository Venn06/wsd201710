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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.*;

/**
 *
 * @author Vennwen
 */
public class DiaryApplication implements Serializable{
    private String bookingsPath;
    private String studentsPath;
    private String tutorsPath;
    private Bookings bookings;
    private Students students;
    private Tutors tutors;

    
    public DiaryApplication(){
    
    }
    
    public void setAllPath(String bookingsPath, String studentsPath, String tutorsPath) throws JAXBException, IOException{
        setBookingsPath(bookingsPath);
        setStudentsPath(studentsPath);
        setTutorsPath(tutorsPath);
    }
    
    public String getBookingsPath() {
        return bookingsPath;
    }

    public void setBookingsPath(String bookingsPath) throws JAXBException, FileNotFoundException, IOException {
        System.out.println("Setting bookingsPath: "+ bookingsPath);
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
        System.out.println("Setting studentsPath: "+ studentsPath);
        this.studentsPath = studentsPath;
        
        // Create the unmarshaller
        JAXBContext jc = JAXBContext.newInstance(Students.class);
        Unmarshaller u = jc.createUnmarshaller();
        // Now unmarshal the object from the file
        FileInputStream fin = new FileInputStream(studentsPath);
        setStudents((Students)u.unmarshal(fin)); // This loads the "students" object
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
        setTutors((Tutors)u.unmarshal(fin)); // This loads the "tutors" object
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
    
    public void addTutor(Tutor tutor) throws JAXBException, PropertyException, FileNotFoundException{
        tutors.addTutor(tutor);
        this.updateTutorsXML();
    }
    public void addStudent(Student student) throws JAXBException, PropertyException, FileNotFoundException{
        students.addStudent(student);
        this.updateStudentsXML();
    }
    
    public void addBooking(String tutorEmail, String studentEmail) throws JAXBException, FileNotFoundException, PropertyException{
        Tutor tutor = this.tutors.getTutor(tutorEmail);
        Student student = this.students.getStudent(studentEmail);
        
        if(tutor != null && student!= null && tutor.getStatus().equals("available")){
            this.bookings.addBooking(tutor, student);
        }
        this.updateBookingsXML();
        this.updateTutorsXML();
    }
    
    public void cancelBooking(int bookingID) throws JAXBException, PropertyException, FileNotFoundException{
        if(bookings.getBooking(bookingID) != null){
            this.bookings.cancelBooking(bookingID);
            this.tutors.getTutor(bookings.getBooking(bookingID).getTutorEmail()).setStatus("available");
            this.updateBookingsXML();
            this.updateTutorsXML();
        }
    }
    
    public void completeBooking(int bookingID) throws JAXBException, PropertyException, FileNotFoundException{
        if(bookings.getBooking(bookingID) != null){
            this.bookings.completeBooking(bookingID);
            this.tutors.getTutor(bookings.getBooking(bookingID).getTutorEmail()).setStatus("available");
            this.updateBookingsXML();
            this.updateTutorsXML();
        }
    }
    
    public void removeStudent(String email) throws JAXBException, FileNotFoundException, PropertyException{
        students.removeStudent(email);
        this.updateStudentsXML();
        this.updateTutorsXML();
    }
    
    public void removeTutor(String email) throws JAXBException, PropertyException, FileNotFoundException{
        ArrayList<Booking> relatedBookings = bookings.searchBookings(
                -1, 
                email,
                null,
                null,
                null,
                null,
                null);
        for(Booking booking : relatedBookings){
            if(booking.getStatus().equals("active")){
                booking.setStatus("cancelled");
            }
        }
        this.updateBookingsXML();
        this.updateTutorsXML();
    }
}