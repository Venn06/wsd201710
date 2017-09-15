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
@XmlRootElement(name = "bookings")
public class Bookings implements Serializable{
    
    @XmlElement(name = "booking")
    private ArrayList<Booking> list;
    
    public Bookings(){
        this.list= new ArrayList<Booking>();
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
    
    public void addBooking(Booking booking){
        list.add(booking);
    }
    
    
    public void canceleBooking(int bookingID){
        for(Booking booking: list){
            if(booking.getBookingID() == bookingID){
                booking.setStatus("cancelled");
            }
        }
    }
}
