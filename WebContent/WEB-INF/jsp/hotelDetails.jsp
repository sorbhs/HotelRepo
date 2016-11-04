<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ page import="com.mindtree.entity.Hotel" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Hotel hot=(Hotel)request.getAttribute("hotelInfo");
session.setAttribute("hotelfo",hot);
%>

<h2>${hotelInfo.getHotelName()}</h2>
<p>	
${hotelInfo.getHotelAddress()}<br>
${hotelInfo.getHotelCity() }<br>
${hotelInfo.getHotelZip()}<br>
${hotelInfo.getHotelState()}<br><br>
Rate : ${hotelInfo.getHotelPrice()}

</p>

<a href="login"><button>Book Hotel</button></a>


</body>
</html>