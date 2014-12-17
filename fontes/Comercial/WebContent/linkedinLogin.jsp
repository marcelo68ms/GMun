<%@page import="br.com.sw2.comercial.service.linkedin.LoginLinkedin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	LoginLinkedin loginLI = new LoginLinkedin();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java LinkedIn Login</title>
</head>
<body style="text-align: left; margin: 0 auto;" bgcolor=#F0F0F0>
	<div
		style="margin: 0 auto; height: 360px; width: 610px;">
		<a href="<%=loginLI.getLoginRedirectURL()%>"> 
			<img style="margin-top: 50px; background-color: #F0F0F0" src="./img/linkedin-login-button.png">
		</a>
	</div>
</body>
</html>