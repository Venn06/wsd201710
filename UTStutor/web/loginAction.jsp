<%@page contentType="text/xml" pageEncoding="UTF-8"
        %><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>

<%@page import="uts.wsd.*"%>
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

    String email = request.getParameter("email");
    String password = request.getParameter("password");
    Students students = (Students) diaryApp.getStudents();
    
    Tutors tutors = (Tutors) diaryApp.getTutors();
    
    Student student = students.login(email, password);
    Tutor tutor = tutors.login(email, password);
    if(student != null){
        session.setAttribute("user",student);
    }
    else if(tutor != null){
        session.setAttribute("user",tutor);
    }
    
    
    if(session.getAttribute("user") != null){
%>
<page title="LoginAction Page" h1="Login successfully">
    <link page="main"/>
</page>
<%
    }
    else{
%>
<page title="LoginAction Page" h1="Login unsuccessfully">
    <link page="index"/>
</page>
<%
    }
%>