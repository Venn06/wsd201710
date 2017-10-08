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
        if(request.getParameter("name") != null){
            user.setName(request.getParameter("name"));
            user.setPassword(request.getParameter("password"));
            user.setDOB(request.getParameter("DOB"));
            diaryApp.updateStudentsXML();
        }
        
%>
<page title="My Account" h1="Welcome,<%=user.getType()%> <%=user.getName()%>">
    <details>
        <% for(int i = 0; i < user.getDetails().length; i=i+2){%>
        <detail name="<%=user.getDetails()[i]%>" value="<%=user.getDetails()[i+1]%>"/>
        <%}%>           
    </details>
    <form action="myAccount.jsp" method="POST">
        <inputs>
            <input type="name" name="name" value="<%=user.getName()%>"/>
            <input type="password" name="password" value="<%=user.getPassword()%>"/>
            <input type="date" name="DOB" value="<%=user.getDOB()%>"/>
            <input type="submit" value="Save"/>
        </inputs>
    </form>

    <link page="cancelAccount"/>
    <link page="main"/>
</page>

<%
    }
    else{
%>
<page title="Main Page" h1="You are not logged in">
    <link page="index"/>
</page>
<%    
    }
%>