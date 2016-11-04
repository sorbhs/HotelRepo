<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.mindtree.entity.Users" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Booking Details</h1>
<p>	
${hotelfo.getHotelName()}<br>
${hotelfo.getHotelAddress()}<br>
${hotelfo.getHotelCity() }<br>
${hotelfo.getHotelZip()}<br>
${hotelfo.getHotelState()}<br><br>
Rate : ${hotelfo.getHotelPrice()}<br>
Email ID :${user.getEmail()}<br>
Password :${user.getPassword()}<br>
</p>

<form action="place" method="get">
	Checkin:<input type="date" name="checkin" >
	Checkout<input type="date" name="checkout" >
	<input type="hidden" value="${user.getEmail()}" name="email">
	<input type="hidden" value="${user.getPassword()}" name="pass">
	<input type="hidden" value="${hotelfo}" name="hotel">
	<input type="hidden" value="${hotelfo.getHotelId()}" name="id">
	<input type="submit" name="Place Reservation">
</form>
</body>
</html>