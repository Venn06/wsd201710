<%-- 
    Document   : logout
    Created on : 10/08/2017, 8:03:53 PM
    Author     : UNI
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>logout.jsp</title>
    </head>
    <body>
        <p>You have been logged out. Click <a href='index.jsp'>here</a> to return to the main page.</p>
        <%session.invalidate();%>
    </body>
</html>
