/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Vennwen
 */
public interface DiaryDAO {

    public void setAllPath(String bookingsPath, String studentsPath, String tutorsPath) throws JAXBException, IOException;

    public void setBookingsPath(String bookingsPath) throws JAXBException, FileNotFoundException, IOException;

    public void setStudentsPath(String studentsPath) throws JAXBException, FileNotFoundException, IOException;

    public void setTutorsPath(String tutorsPath) throws JAXBException, FileNotFoundException, IOException;

    public void updateStudentsXML() throws JAXBException, FileNotFoundException, IOException;

    public void updateTutorsXML() throws JAXBException, FileNotFoundException, IOException;

    public void updateBookingsXML() throws JAXBException, FileNotFoundException, IOException;

    public void addTutor(Tutor tutor) throws JAXBException, FileNotFoundException, IOException;

    public void addStudent(Student student) throws JAXBException, FileNotFoundException, IOException;

    public boolean addBooking(String tutorEmail, String studentEmail) throws JAXBException, FileNotFoundException, IOException;

    public boolean cancelBooking(int bookingID) throws JAXBException, FileNotFoundException, IOException;

    public boolean completeBooking(int bookingID) throws JAXBException, FileNotFoundException, IOException;

    public void removeStudent(String email) throws JAXBException, FileNotFoundException, IOException;

    public void removeTutor(String email) throws JAXBException, FileNotFoundException, IOException;
}
