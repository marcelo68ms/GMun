<%@page import="br.com.sw2.comercial.service.paypal.LoginPayPal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
LoginPayPal loginPaypal = new LoginPayPal();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java PayPal Login</title>
</head>
<body style="text-align: left; margin: 10 auto;">
	<div>
		<a href="<%=loginPaypal.getLoginRedirectURL()%>"> <img
			style="margin-top: 10px;" src="https://www.paypalobjects.com/webstatic/en_US/developer/docs/lipp/loginwithpaypalbutton.png" />
		</a>
	</div>
</body>
</html>