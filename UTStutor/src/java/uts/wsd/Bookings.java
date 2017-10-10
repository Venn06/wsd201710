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
 *  Bookings have a arraylist to store all bookings. It has methods to add, complete, cancel and search booking.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "bookings", namespace = "http://www.uts.edu.au/31284/wsd-bookings")
public class Bookings implements Serializable {

    @XmlElement(name = "booking")
    private ArrayList<Booking> list;

    public Bookings() {
        this.list = new ArrayList<Booking>();
    }

    public Bookings(ArrayList<Booking> list) {
        this.list = list;
    }

    public ArrayList<Booking> getList() {
        return list;
    }

    public void setList(ArrayList<Booking> list) {
        this.list = list;
    }

    public boolean addBooking(Tutor tutor, Student student) {
        int id = list.size();
        if (tutor.getStatus().equals("available")) {
            this.addBooking(new Booking(id, tutor, student));
            return true;
        }
        return false;

    }

    public void addBooking(Booking booking) {
        list.add(booking);
    }

    public boolean cancelBooking(int bookingID) {
        for (Booking booking : list) {
            if (booking.getBookingID() == bookingID) {
                booking.setStatus("cancelled");
                return true;
            }
        }
        return false;
    }

    public boolean completeBooking(int bookingID) {
        for (Booking booking : list) {
            if (booking.getBookingID() == bookingID) {
                booking.setStatus("completed");
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param bookingID - the id of the booking
     * @return
     */
    public Booking getBooking(int bookingID) {
        for (Booking booking : list) {
            if (booking.getBookingID() == bookingID) {
                return booking;
            }
        }
        return null;
    }

    public ArrayList<Booking> searchBookings(
            int id,
            String tutorEmail,
            String tutorName,
            String subjectName,
            String studentEmail,
            String studentName,
            String status) {

        ArrayList bookings = new ArrayList<Booking>();
        for (Booking booking : list) {
            if (id != -1 && id != booking.getBookingID()) {
                continue;
            }
            if (tutorEmail != null && !tutorEmail.equals(booking.getTutorEmail())) {
                continue;
            }
            if (tutorName != null && !tutorName.equals(booking.getTutorName())) {
                continue;
            }
            if (subjectName != null && !subjectName.equals(booking.getSubjectName())) {
                continue;
            }
            if (studentEmail != null && !studentEmail.equals(booking.getStudentEmail())) {
                continue;
            }
            if (studentName != null && !studentName.equals(booking.getStudentName())) {
                continue;
            }
            if (status != null && !status.equals(booking.getStatus())) {
                continue;
            }
            bookings.add(booking);
        }
        return bookings;
    }
}
