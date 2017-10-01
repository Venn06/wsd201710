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
    User user=null;
    String email = request.getParameter("email");
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    String DOB = request.getParameter("DOB");
    String UserType = request.getParameter("User Type");
    String subject = request.getParameter("subject");
    String error = null;
    Students students = diaryApp.getStudents();
    Tutors tutors = diaryApp.getTutors();
    if(email == null){
        error = "The email is null";
    }
    else if(email.equals("")){
        error = "The email is empty";
    }
    else if(name.equals("")){
        error = "The name is empty";
    }
    
    else if(password.equals("")){
        error = "The password is empty";
    }
    else if(DOB.equals("")){
        error = "The DOB is empty";
    }
    else if(students.getStudent(email) != null || tutors.getTutor(email) != null){
        error = "The email has been used";
    }
    if(error == null){
        if(UserType.equals("Student")){
            Student student = new Student(email,name,password,DOB);
            students.addStudent(student);
            diaryApp.updateStudentsXML();
            session.setAttribute("user", student);        
        }
        else{
            Tutor tutor = new Tutor(email,name,password,DOB,subject);
            tutors.addTutor(tutor);
            diaryApp.updateTutorsXML();
            session.setAttribute("user", tutor);
            
        }
        user = (User) session.getAttribute("user");
    }
    
    if(user != null){
%>
<page title="Welcom Page" h1="Welcome,<%=user.getType()%> <%=user.getName()%>">
        <details>
            <% for(int i = 0; i < user.getDetails().length; i=i+2){%>
            <detail name="<%=user.getDetails()[i]%>" value="<%=user.getDetails()[i+1]%>"/>
            <%}%>           
        </details>
    <link page="main"/>
    <link page="logout"/>
</page>

<%
    }
    else{
%>
<page title="Welcome Page" h1="Sorry.<%=error%>">
    <link page="index"/>
</page>
<%    
    }
%>