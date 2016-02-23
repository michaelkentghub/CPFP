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
RegisterChange.jsp
<form action="LogOutServlet" method="get">  
<br><br><input type = "submit" value="LogOut" />
</form>

<form action="CPFPSQLChangeServlet2" onsubmit="return validateForm()" method="get">
	<br><label> Change Registration Information Form</label>
	<br><label> Account email = ${sessionScope.EMAIL}</label>
	
	<br><br><label> Change First Name?</label>
	<input type = "text" name="fname" value = "${sessionScope.FNAME}" /><br>
	<br><label> Change Last Name?:</label>
	<input type = "text" name="lname"  value = "${sessionScope.LNAME}"/><br>
	<br><label> Change Address?:</label>
	<input type = "text" name="address"  value = "${sessionScope.ADDRESS}"/><br>
	
	
	<br><label> Change your password?</label>
	<input type = "password" name="password1" value = "${sessionScope.PWORD}" /><br>
	<br><label> Please confirm your password change</label>
	<input type = "password" name="password2" value = "${sessionScope.PWORD}" /><br>
	
	
	<br><br><input type = "submit" value="Submit your account changes" />
</form>

	<br><br><button onclick="location.href = 'http://localhost:8080/CPFP/LoginOrRegister.html'" id="myButton" class="float-left submit-button" >Discontinue Registration Form and Return to the "Login or Register" Page</button>	

	



</body>
</html>

