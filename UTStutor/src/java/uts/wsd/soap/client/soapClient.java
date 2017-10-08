/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.soap.client;

import java.util.Scanner;
import uts.wsd.Input;

/**
 *
 * @author Vennwen
 */
public class soapClient {

    public static void main(String[] args) {
        SoapService_Service locator = new SoapService_Service();
        SoapService soapService = locator.getSoapServicePort();
        System.out.println("Welcome to soap client. Support commands are: login, logout, cancel account, cancel booking, complete booking, create booking, edit information, fetch booking and exit");
        String command = Input.askString("Please enter your command: ");
        while (!command.equals("exit")) {
            switch (command) {
                case "login":
                    String email = Input.askString("Please enter your email: ");
                    String password = Input.askString("Please enter your password");
                    System.out.println(soapService.login(email, password));
                    break;
                case "logout":
                    System.out.println(soapService.logout());
                    break;
                case "cancel account":
                    System.out.println(soapService.cancelAccount());
                    break;
                case "cancel booking":
                    int bookingID0;
                    bookingID0 = Input.askInt("Please enter the bookingID: ");
                    System.out.println(soapService.cancelBooking(bookingID0));
                    break;
                case "complete booking":
                    int bookingID1;
                    bookingID1 = Input.askInt("Please enter the bookingID: ");
                    System.out.println(soapService.completeBooking(bookingID1));
                    break;
                case "create booking":
                    String tutorEmail = Input.askString("Please enter the tutor email: ");
                    System.out.println(soapService.createBooking(tutorEmail));
                    break;
                case "edit information":
                    String field = Input.askString("Please enter the field you want to change: ");
                    String value = Input.askString("Please enter the value you want to change: ");
                    System.out.println(soapService.editInformation(field, value));
                    break;
                case "fetch booking":
                    int bookingID2;
                    String studentEmail = null,
                     subjectName = null,
                     status = null;
                    System.out.println("Please enter parameters, enter -1 to ignore bookingID and any for other parameters");
                    bookingID2 = Input.askInt("Please enter the booking ID");
                    studentEmail = Input.askString("Please enter the student email");
                    subjectName = Input.askString("Please enter the subject name");
                    status = Input.askString("Please enter the status");
                    if (studentEmail.equals("any")) {
                        studentEmail = null;
                    }
                    if (subjectName.equals("any")) {
                        subjectName = null;
                    }
                    if (status.equals("any")) {
                        status = null;
                    }
                    for (Booking booking : soapService.fetchBooking(bookingID2, studentEmail, subjectName, status)) {
                        System.out.println("Booking ID: " + booking.getId() + ", Booking status: " + booking.getStatus());

                    }
                default:
                    System.out.println("Invalid command.");
            }
            command = Input.askString("Please input your command: ");
        }
        System.out.println("Bye!");

    }
}
