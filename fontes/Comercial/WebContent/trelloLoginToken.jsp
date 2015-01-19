<%@page import="br.com.sw2.comercial.service.trello.LoginTrello"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
LoginTrello loginTrello = new LoginTrello();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java Trello Login</title>
</head>
<body style="text-align: left; margin: 0 auto;">
	<div>
		<a href="<%=loginTrello.getLoginRedirectURL()%>"> <img
			style="margin-top: 50px; margin-left: 50px;" src="./img/trello-button.png"/>
		</a>
	</div>
</body>
</html>