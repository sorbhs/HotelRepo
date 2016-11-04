<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Login Page</h1>
<%-- 
	<h3>${hotelfo.getHotelAddress()}</h3>
	<h3>${hotelfo.getHotelId()}</h3> --%>

	<form action="afterLogin" method="get">
		Name : <input type="text" name="username"><br> Email : <input
			type="text" name="email"><br> password:<input
			type="text" name="password"> <input type="submit"
			value="Login">
	</form>
	<h4 style="color:red">${msg}</h4>
	
</body>
</html>
