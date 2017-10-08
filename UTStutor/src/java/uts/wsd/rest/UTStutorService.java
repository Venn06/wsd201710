/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.rest;

import uts.wsd.*;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import uts.wsd.*;

/**
 *
 * @author UNI
 */
@Path("/restService")
public class UTStutorService {

    @Context
    private ServletContext application;

    private DiaryApplication getApp() {
        // The web server can handle requests from different clients in parallel.
        // These are called "threads".
        //
        // We do NOT want other threads to manipulate the application object at the same
        // time that we are manipulating it, otherwise bad things could happen.
        //
        // The "synchronized" keyword is used to lock the application object while
        // we're manpulating it.
        synchronized (application) {
            DiaryApplication diaryApp;
            diaryApp = (DiaryApplication) application.getAttribute("diaryApp");
            if (diaryApp == null) {

                try {
                    diaryApp = new DiaryApplication();
                    diaryApp.setAllPath(application.getRealPath("WEB-INF/bookings.xml"), application.getRealPath("WEB-INF/students.xml"), application.getRealPath("WEB-INF/tutors.xml"));
                    application.setAttribute("diaryApp", diaryApp);
                } catch (JAXBException ex) {
                    Logger.getLogger(UTStutorService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(UTStutorService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return diaryApp;
        }

    }

    @Path("hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello World";
    }

    @Path("fetchBooking")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Booking> fetchBooking(@QueryParam("ID") String id,
            @QueryParam("studentEmail") String studentEmail,
            @QueryParam("subject") String subject,
            @QueryParam("status") String status) {
        int bookingID = -1;
        if (id != null) {
            bookingID = Integer.parseInt(id);
        }
        return getApp().getBookings().searchBookings(bookingID, null, null, subject, studentEmail, null, status);

    }

    @Path("fetchTutor")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Tutor> fetchTutor(@QueryParam("status") String status,
            @QueryParam("email") String email) {
        return getApp().getTutors().searchTutors(email, null, null, null, status);

    }

}
