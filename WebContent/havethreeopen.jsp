<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:out value="${sessionScope.name}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>Home Page</title>
    
<body>
<%@ page session="true" %>
havethreeopen.jsp
<form action="LogOutServlet" method="get">  
<br><br><input type = "submit" value="LogOut" />
</form>	

<br>account email is ${sessionScope.EMAIL}
<br><br>You currently have no scheduled appointments:

<br>${sessionScope.DATE1}
<br>${sessionScope.DATE2}
<br>${sessionScope.DATE3}


<br><br>Would you like to schedule an appointment?
<form action="HaveThreeOpenServlett" method="get">  
<br><br><label> Appointment Date #1: </label><input id="meeting" input type="date" name="passdate" value="2000-01-01"  />
<br><br><input type = "submit" value="Fill in the date in the box, then submit your appointment." />	
</form>
<br><br>


<br>Or choose one of the following two options

<form action="RegisterChangeTransferServlet" method="get">  
<br><br><label> Change your account information: </label>
<br><br><input type = "submit" name = "two" value="Change Info" />	
</form>
<br><br>

<br>We welcome your feedback
<br>You may provide feedback here.
<form action="FeedbackServlet" method="get"> 
<textarea class="longInput" cols="60" rows="20" input type="text" name="feedback"> ${sessionScope.FEEDBACK} </textarea>
<br><br><input type = "submit" value="Submit feedback." />	
</form>
<br><br>

