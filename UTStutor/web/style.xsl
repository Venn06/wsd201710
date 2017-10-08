<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : style.xsl
    Created on : September 30, 2017, 4:00 PM
    Author     : Vennwen
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    
    
    <xsl:template match="page">
        <html>
            <head>
                <link rel="stylesheet" href="styles.css"/>
                <title>
                    <xsl:value-of select="@title"/>
                </title>
            </head>
            <body>
                <h1>
                    <xsl:value-of select="@h1"/>
                </h1>
                <xsl:apply-templates></xsl:apply-templates>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="link">
        <a href="{@page}.jsp">
            <xsl:value-of select="@page"/>
        </a>
    </xsl:template>
    
    <xsl:template match="form">
        <form action="{@action}" method="{@method}">
            <xsl:apply-templates/>
        </form>
    </xsl:template>
    
    <xsl:template match="inputs">
        <table>
            <xsl:apply-templates></xsl:apply-templates>
        </table>
    </xsl:template>
    
    <xsl:template match="input">
        <tr>
            <th>
                <xsl:value-of select="@name"/>
            </th>
            <td>
                <input type="{@type}" name="{@name}" value="{@value}"/>
            </td>
        </tr>
    </xsl:template>
    
    <xsl:template match="details">
        <table>
            <xsl:apply-templates></xsl:apply-templates>
        </table>
    </xsl:template>
    
    <xsl:template match = "detail">
        <tr>
            <th>
                <xsl:value-of select="@name"/>
            </th>
            <td>
                <xsl:value-of select="@value"/>
            </td>
        </tr>        
    </xsl:template>
    
    <xsl:template match="select">
        <tr>
            <th>
                <xsl:value-of select="@name"/>
            </th>
            <td>
                <select name="{@name}" style="{@style}" id="{@id}" onchange="{@onchange};">
                    <xsl:apply-templates></xsl:apply-templates>
                </select>
            </td>
        </tr>
    </xsl:template>

    <xsl:template match="option">
        <option>
            <xsl:value-of select="@name"/>
        </option>
    </xsl:template>

    <xsl:template match="tutors">
        <table>
            <tr>
                <th>Email</th>
                <th>Name</th>
                <th>Subject</th>
                <th>Status</th>
            </tr>
            <xsl:apply-templates></xsl:apply-templates>
        </table>
    </xsl:template>
    
    <xsl:template match="tutor">
        <tr>
            <xsl:apply-templates/>
        </tr>
    </xsl:template>
    
    <xsl:template match="email|name|subject|status|tutorEmail|tutorName|subjectName|studentEmail|studentName">
        <td>
            <xsl:apply-templates/>
        </td>
    </xsl:template>
    
    <xsl:template match="tutor/status">
        <xsl:choose>
            <xsl:when test="@value='available'">
                <td>
                    <a href="booking.jsp?tutorEmail={@email}&amp;action=view">
                        <xsl:apply-templates/>
                    </a>
                </td>
            </xsl:when>
            <xsl:otherwise>
                <td>
                    <xsl:apply-templates/>
                </td>
            </xsl:otherwise>
            <td>
                <xsl:apply-templates/>
            </td>
        </xsl:choose>
    </xsl:template>    
    
    <xsl:template match="bookings">
        <table>
            <tr>
                <th>Subject</th>
                <th>Tutor Name</th>
                <th>Tutor Email</th>
                <th>Student Name</th>
                <th>Student Email</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <xsl:apply-templates/>
        </table>
    </xsl:template>
    
    <xsl:template match="booking">
        <tr>
            <xsl:apply-templates/>
        </tr>
    </xsl:template>
    
    <xsl:template match="action">
        <xsl:choose>
            <xsl:when test="@status='notbooked'">
                <td>
                    <a href="booking.jsp?tutorEmail={@tutorEmail}&amp;action=create">Book</a>
                </td>
            </xsl:when>
            <xsl:when test="@status='active' and @userType='Tutor'">
                <td>
                    <a href="booking.jsp?action=cancel&amp;bookingID={@bookingID}">Cancel</a>
                    <br/>
                    <a href="booking.jsp?action=complete&amp;bookingID={@bookingID}">Complete</a>
                </td>
            </xsl:when>
            <xsl:when test="@status='active' and @userType='Student'">
                <td>
                    <a href="booking.jsp?action=cancel&amp;bookingID={@bookingID}">Cancel</a>
                    <br/>                    
                </td>
            </xsl:when>            
        </xsl:choose>
    </xsl:template>
    
    <xsl:template match="script" >
        <script type="text/javascript">
            <xsl:value-of select="@content"/>
        </script>
    </xsl:template>
</xsl:stylesheet>
