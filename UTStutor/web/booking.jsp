<%@page contentType="text/xml" pageEncoding="UTF-8"
        %><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@page import="uts.wsd.*"%>
<%@page import="java.util.ArrayList"%>
<%

    String bookingsPath = application.getRealPath("WEB-INF/bookings.xml");
    String studentsPath = application.getRealPath("WEB-INF/students.xml");
    String tutorsPath = application.getRealPath("WEB-INF/tutors.xml");
%>

<jsp:useBean id="diaryApp" class="uts.wsd.DiaryApplication" scope="application">
    <jsp:setProperty name="diaryApp" property="tutorsPath" value="<%=tutorsPath%>"/>
    <jsp:setProperty name="diaryApp" property="bookingsPath" value="<%=bookingsPath%>"/>
    <jsp:setProperty name="diaryApp" property="studentsPath" value="<%=studentsPath%>"/>
</jsp:useBean>

<%

    User user = (User) session.getAttribute("user");
    if (user != null) {
%>
<page title="Booking" h1="Welcome,<%=user.getType()%> <%=user.getName()%>">
    <bookings>
        <%
            String action = request.getParameter("action");

            if (user.getType() == UserType.Student) {
                if (action != null && action.equals("cancel") && request.getParameter("bookingID") != null) {
                    int bookingID = Integer.parseInt(request.getParameter("bookingID"));
                    if (diaryApp.getBookings().getBooking(bookingID) != null
                            && diaryApp.getBookings().getBooking(bookingID).getStudentEmail().equals(user.getEmail())) {
                        diaryApp.cancelBooking(bookingID);
                    }
                }
                if (action != null && action.equals("complete") && request.getParameter("bookingID") != null) {
                    int bookingID = Integer.parseInt(request.getParameter("bookingID"));
                    if (diaryApp.getBookings().getBooking(bookingID) != null
                            && diaryApp.getBookings().getBooking(bookingID).getStudentEmail().equals(user.getEmail())) {
                        diaryApp.completeBooking(bookingID);
                    }
                }                
                if (action != null && action.equals("create")) {
                    diaryApp.addBooking(request.getParameter("tutorEmail"), user.getEmail());
                }

                String tutorEmail = request.getParameter("tutorEmail");
                if (request.getParameter("action") != null
                        && tutorEmail != null
                        && diaryApp.getTutors().getTutor(tutorEmail) != null
                        && request.getParameter("action").equals("view")) {
                    Tutor tutor = diaryApp.getTutors().getTutor(tutorEmail);
        %>

        <booking>
            <tutorEmail><%=tutor.getEmail()%></tutorEmail>
            <tutorName><%=tutor.getName()%></tutorName>
            <subjectName><%=tutor.getSubject()%></subjectName>  
            <studentEmail><%=user.getEmail()%></studentEmail>
            <studentName><%=user.getName()%></studentName>
            <status>Not Booked yet</status>
            <action status="notbooked" tutorEmail="<%=tutor.getEmail()%>"></action>
        </booking>

        <%
                }
            }
            if(user.getType() == UserType.Tutor){
                System.out.println("User is tutor");                
                if (action != null && action.equals("cancel") && request.getParameter("bookingID") != null) {
                    int bookingID = Integer.parseInt(request.getParameter("bookingID"));
                    System.out.println(" Going to check whether to cancel");
                    if (diaryApp.getBookings().getBooking(bookingID) != null
                            && diaryApp.getBookings().getBooking(bookingID).getTutorEmail().equals(user.getEmail())) {
                        diaryApp.cancelBooking(bookingID);
                    }
                }                
                if (action != null && action.equals("complete") && request.getParameter("bookingID") != null) {
                    int bookingID = Integer.parseInt(request.getParameter("bookingID"));
                    if (diaryApp.getBookings().getBooking(bookingID) != null
                            && diaryApp.getBookings().getBooking(bookingID).getTutorEmail().equals(user.getEmail())) {
                        diaryApp.completeBooking(bookingID);
                    }
                }                
            }
            ArrayList<Booking> myBookings;
            if (user.getType() == UserType.Student) {
                myBookings = diaryApp.getBookings().searchBookings(-1, null, null, null, user.getEmail(), null, null);
            } else {
                myBookings = diaryApp.getBookings().searchBookings(-1, user.getEmail(), null, null, null, null, null);
            }
            for (Booking booking : myBookings) {%>
        <booking>
            <tutorEmail><%=booking.getTutorEmail()%></tutorEmail>
            <tutorName><%=booking.getTutorName()%></tutorName>
            <subjectName><%=booking.getSubjectName()%></subjectName>
            <studentEmail><%=booking.getStudentEmail()%></studentEmail>
            <studentName><%=booking.getStudentName()%></studentName>
            <status><%=booking.getStatus()%></status>
            <action bookingID="<%=booking.getBookingID()%>" status="<%=booking.getStatus()%>" studentEmail="<%=booking.getStudentEmail()%>" tutorEmail="<%=booking.getTutorEmail()%>" userType="<%=user.getType()%>"><%=booking.getStatus()%></action>                
        </booking>


        <%}
        %>
    </bookings>
    <link page="main"/>
</page>

<%
} else {
%>
<page title="Booking Page" h1="You are not logged in">
    <link page="index"/>
</page>
<%
    }
%>