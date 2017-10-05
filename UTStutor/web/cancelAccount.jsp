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
    
    User user = (User) session.getAttribute("user");
    if(user != null){
        if(user.getType() == UserType.Student){
            diaryApp.removeStudent(user.getEmail());
        }
        else if(user.getType() == UserType.Tutor){
            diaryApp.removeTutor(user.getEmail());
        }
        
%>
<page title="Cancel Account" h1="Your account has been cancelled">
    <link page="index"/>
</page>
<%
    }
    else{
%>
<page title="Need login" h1="You are not logged in">
    <link page="index"/>
</page>
<%    
    }
%>