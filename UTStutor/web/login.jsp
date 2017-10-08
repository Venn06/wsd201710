<%@page contentType="text/xml" pageEncoding="UTF-8"
        %><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>

<%-- 
    Document   : login
    Created on : Sep 30, 2017, 4:19:04 PM
    Author     : Vennwen
--%>
<page title="Login Page" h1="UTS Tutor|Login">

    <form action="loginAction.jsp" method="POST">
        <inputs>
            <input type="email" name="email"/>
            <input type="password" name="password"/>
            <input type="submit" value="Login"/>
        </inputs>
    </form>    
    <link page="index"/>
</page>