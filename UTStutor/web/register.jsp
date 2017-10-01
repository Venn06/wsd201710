<%@page contentType="text/xml" pageEncoding="UTF-8"
        %><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>

<%-- 
    Document   : login
    Created on : Sep 30, 2017, 4:19:04 PM
    Author     : Vennwen
--%>
<page title="Register" h1="UTS Tutor | Register">

    <form action="welcome.jsp" method="POST">
        <inputs>
            <input type="email" name="email"/>
            <input type="name" name="name"/>
            <input type="password" name="password"/>
            <input type="date" name="DOB"/>
            <select name="User Type">
                <option name="Tutor"/>
                <option name="Student"/>
            </select>
            <select name="subject">
                <option name="WSD"/>
                <option name="USP"/>
                <option name="AppProg"/>
                <option name="SEP"/>
                <option name="MobileApp"/>
            </select>
            <input type="Submit" value="Register"/>
        </inputs>
    </form>    
</page>