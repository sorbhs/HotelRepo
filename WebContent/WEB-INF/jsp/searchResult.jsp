<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Hotel Search Results</h1>
<a href="backsearchResult">Change Search</a>

<table border="1">
<tr style="font-weight:bold"><td>Name</td><td>Address</td><td>City</td><td>State</td><td>Zip</td><td>Action</td></tr>
<c:forEach items="${hotelList}" var="x" >
<tr>
	<td>${x.getHotelName()}</td><td>${x.getHotelAddress()}</td><td>${x.getHotelCity()}</td><td>${x.getHotelState()}</td><td>${x.getHotelZip()}</td>
	
	<td><a href="hotelInfo?hotelId=${x.getHotelId()}">View Hotel</a></td>
</tr>
<br></c:forEach>
</table>

</body>
</html>	