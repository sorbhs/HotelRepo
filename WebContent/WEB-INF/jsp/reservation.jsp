<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Insert title here</title>
</head>
<body>
<a href="search"><button>Search Again</button></a>
	<a href="back"><button>Home</button></a>
	<a href="logout"><button>Logout</button></a>
<h2>Current Hotel Bookings</h2>
Customer :<p>${username}</p>
<p style="color:blue;">A booking confirmation notification has been sent to your registered email ID.</p>
	<table border="1">
		<tr style="font-weight:bold">
			<td>Hotel Name</td>
			<td>Address</td>
			<td>City</td>
			<td>State</td>
			<td>Check-In Date</td>
			<td>Check-out Date</td>
			<td>Confirmation Number</td>			
		</tr>
		<c:forEach items="${bookingDetails}" var="x" >
		<tr>
			<td>${x.getHotel().getHotelName()}</td>
			<td>${x.getHotel().getHotelAddress()}</td>
			<td>${x.getHotel().getHotelCity()}</td>
			<td>${x.getHotel().getHotelState()}</td>
			<td>${x.getCheckin()}</td>
			<td>${x.getCheckout()}</td>
			<td>${x.getBookingid()}</td>
		</tr>
	<br></c:forEach>
	</table>
	
	
	
</body>
</html>