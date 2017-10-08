/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.soap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBException;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import uts.wsd.DiaryApplication;
import uts.wsd.*;

/**
 *
 * @author Vennwen
 */
@WebService(serviceName = "soapService")
public class soapService {

    @Resource
    private WebServiceContext context;

    private String userType;
    private Tutor tutor;
    private Student student;

    private DiaryApplication getApp() {
        // The web server can handle requests from different clients in parallel.
        // These are called "threads".
        //
        // We do NOT want other threads to manipulate the application object at the same
        // time that we are manipulating it, otherwise bad things could happen.
        //
        // The "synchronized" keyword is used to lock the application object while
        // we're manpulating it.
        ServletContext application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        synchronized (application) {
            DiaryApplication diaryApp = (DiaryApplication) application.getAttribute("diaryApp");
            if (diaryApp == null) {
                try {
                    diaryApp = new DiaryApplication();
                    diaryApp.setAllPath(application.getRealPath("WEB-INF/bookings.xml"), application.getRealPath("WEB-INF/students.xml"), application.getRealPath("WEB-INF/tutors.xml"));
                    application.setAttribute("diaryApp", diaryApp);
                } catch (JAXBException ex) {
                    Logger.getLogger(soapService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(soapService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return diaryApp;
        }
    }

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    public String Login(String email, String password) {
        if (getApp().getStudents().login(email, password) != null) {
            this.student = getApp().getStudents().login(email, password);
            this.userType = "student";
            return "Login successfully, Student " + student.getName();
        } else if (getApp().getTutors().login(email, password) != null) {
            this.tutor = getApp().getTutors().login(email, password);

            this.userType = "tutor";
            return "Login successfully, Tutor " + tutor.getName();
        }
        return "Invalid email or password";

    }

    public String Logout() {
        if (this.userType != null && this.userType.equals("student")) {
            String str = "Logout successfully, student " + student.getName();
            this.userType = null;
            this.student = null;
            return str;
        } else if (this.userType != null && this.userType.equals("tutor")) {
            String str = "Logout successfully, Tutor " + tutor.getName();
            this.userType = null;
            this.tutor = null;
            return str;
        } else if (this.userType == null) {
            return "You cant logout since you did not login.";
        }
        return "Invalid logout";
    }

    public String createBooking(String tutorEmail) {
        if (this.userType != null && this.userType.equals("student")) {
            try {
                if (getApp().addBooking(tutorEmail, student.getEmail())) {
                    return "The booking is created";
                }
                return "Sorry, booking failed, please check the tutor email and tutor's status";

            } catch (JAXBException ex) {
                Logger.getLogger(soapService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(soapService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "Sorry, you need to login as student to create booking";
    }

    public ArrayList<Booking> fetchBooking(int bookingID, String studentEmail, String subjectName, String status) {
        return getApp().getBookings().searchBookings(bookingID, null, null, subjectName, studentEmail, null, status);
    }

    public String cancelBooking(int bookingID) {
        Booking booking = getApp().getBookings().getBooking(bookingID);

        if (this.userType != null && booking != null) {
            if (this.userType.equals("student") && booking.getStudentEmail().equals(this.student.getEmail())) {
                try {
                    if (getApp().cancelBooking(bookingID)) {
                        return "The booking has been cancelled.";
                    }
                    return "Something wrong when cancel the booking";
                } catch (JAXBException ex) {
                    Logger.getLogger(soapService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(soapService.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (this.userType.equals("tutor") && booking.getTutorEmail().equals(this.tutor.getEmail())) {
                try {
                    if (getApp().cancelBooking(bookingID)) {
                        return "The booking has been calcelled.";
                    }
                    return "Something wrong when cancel the booking";
                } catch (JAXBException ex) {
                    Logger.getLogger(soapService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(soapService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return "Sorry, you can only cancel your own booking";
        }
        if (userType == null) {
            return "Sorry, you need to login";
        }
        if (booking == null) {
            return "Sorry, the booking ID is invalid.";
        }
        return "Sorry, complete booking failed";
    }

    public String completeBooking(int bookingID) {
        if (this.userType != null && this.userType.equals("tutor")) {
            Booking booking = getApp().getBookings().getBooking(bookingID);
            if (booking != null && booking.getTutorEmail() == this.tutor.getEmail()) {
                try {
                    if (getApp().completeBooking(bookingID)) {
                        return "The booking is completed.";
                    }

                } catch (JAXBException ex) {
                    Logger.getLogger(soapService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(soapService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return "Sorry, the booking does not exist or it is not your own booking";
        }
        return "Sorry, you have to login as tutor to complete booking.";
    }

    public String cancelAccount() {
        if (this.userType != null) {
            if (this.userType.equals("student")) {
                try {
                    getApp().removeStudent(this.student.getEmail());
                    Logout();
                    return "The account has be cancelled.";
                } catch (JAXBException | FileNotFoundException ex) {
                    Logger.getLogger(soapService.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (this.userType.equals("tutor")) {
                try {
                    getApp().removeTutor(this.tutor.getEmail());
                    Logout();
                    return "The account has be cancelled.";
                } catch (JAXBException ex) {
                    Logger.getLogger(soapService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(soapService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return "Sorry, you have to login first.";
    }

    public String editInformation(String feild, String value) {
        String str = "You need to login first";
        try {
            XMLValidation xv = new XMLValidation();
            if (this.userType != null) {
                if (this.userType.equals("student")) {
                    switch (feild) {
                        case "name":
                            if (xv.nameValidation(value).equals("")) {
                                this.student.setName(value);
                                str = "The name has been changed to " + value;
                                break;
                            }
                            str = xv.nameValidation(value);
                            break;
                        case "password":
                            if (xv.passwordValidation(value).equals("")) {
                                this.student.setPassword(value);
                                str = "The password has been changed to " + value;
                                break;
                            }
                            str = xv.passwordValidation(value);
                            break;
                        case "DOB":
                            if (xv.DOBValidation(value).equals("")) {
                                this.student.setDOB(value);
                                str = "The DOB has been changed to" + value;
                                break;
                            }
                            str = xv.DOBValidation(value);
                            break;
                        default:
                            str = "Sorry, you can only edit name, password and DOB(date of birth)";
                    }

                } else if (this.userType.equals("tutor")) {
                    switch (feild) {
                        case "name":
                            if (xv.nameValidation(value).equals("")) {
                                this.tutor.setName(value);
                                str = "The name has been changed to " + value;
                                break;
                            }
                            str = xv.nameValidation(value);
                            break;
                        case "password":
                            if (xv.passwordValidation(value).equals("")) {
                                this.tutor.setPassword(value);
                                str = "The password has been changed to " + value;
                                break;
                            }
                            str = xv.passwordValidation(value);
                            break;
                        case "DOB":
                            if (xv.DOBValidation(value).equals("")) {
                                this.tutor.setDOB(value);
                                str = "The DOB has been changed to" + value;
                                break;
                            }
                            str = xv.DOBValidation(value);
                            break;
                        default:
                            str = "Sorry, you can only edit name, password and DOB(date of birth)";
                    }
                }
            }
            getApp().updateStudentsXML();
            getApp().updateTutorsXML();
        } catch (JAXBException ex) {
            Logger.getLogger(soapService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(soapService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }
}
