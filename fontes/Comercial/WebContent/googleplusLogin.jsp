<%@page import="br.com.sw2.comercial.service.googleplus.LoginGoogleplus"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	LoginGoogleplus loginGoogle = new LoginGoogleplus();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java Google+ Login</title>
</head>
<body style="text-align: left;">
	<div
		style=" height: 100px; width: 200px;">
		<a href="<%=loginGoogle.getLoginRedirectURL()%>"> <img
			style="margin-top: 50px; margin-left: 50px" src="./img/google-signin-button.png" />
		</a>
	</div>
</body>
</html>