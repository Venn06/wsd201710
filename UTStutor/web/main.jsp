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
<page title="Main Page" h1="Welcome,<%=user.getType()%> <%=user.getName()%>">
    <%
    if (user.getType() == UserType.Student) {
    %>
    <form action="main.jsp" method="GET">
        <inputs>
            <select name="subject">
                <option name="any"/>
                <option name="WSD"/>
                <option name="USP"/>
                <option name="AppProg"/>
                <option name="SEP"/>
                <option name="MobileApp"/>
            </select>
            <input type="name" name="name"/>
            <select name="status">
                <option name="any"/>
                <option name="available"/>
                <option name="unavailable"/>
            </select>                
            <input type="submit" value="Search"/>
        </inputs>
    </form>
    <%if (request.getParameter("name") != null && (request.getParameter("subject") != null) && (request.getParameter("status") != null)) {
            String name = request.getParameter("name");
            if (name.equals("")) {
                name = null;
            }
            
            String subject = request.getParameter("subject");
            if (subject.equals("any")) {
                subject = null;
            }
            String status = request.getParameter("status");
            if (status.equals("any")) {
                status = null;
            }
            ArrayList<Tutor> tutorlist = diaryApp.getTutors().searchTutors(null, name, null, subject, status);
    %>
    <tutors>
        <%
            for (Tutor tutor : tutorlist) {
        %>
        <tutor>
            <email><%=tutor.getEmail()%></email>
            <name><%=tutor.getName()%></name>
            <subject><%=tutor.getSubject()%></subject>
            <status email="<%=tutor.getEmail()%>" value="<%=tutor.getStatus()%>"><%=tutor.getStatus()%></status>
        </tutor>
        <%}%>
    </tutors>
    <%}
            }
        %>
    <link page="myAccount"/>
    <link page="booking"/>
    <link page="logout"/>
</page>

<%
    }
else {
%>
<page title="Main Page" h1="You are not logged in">
    <link page="index"/>
</page>
<%
    }
%>