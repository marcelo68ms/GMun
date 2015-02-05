<%@page import="br.com.sw2.comercial.service.instagram.LoginInstagram"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
LoginInstagram loginInsta = new LoginInstagram();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java LinkedIn Login</title>
</head>
<body style="text-align: left; margin: 10 auto;">
<%if(null == session.getAttribute("username")){%>
	<div>
		<a href="<%=loginInsta.getLoginRedirectURL()%>"> 
			<img style="margin-top: 10px;" src="./img/instagram-button.png">
		</a>
	</div>
<%} else {%>
	<h1>Instagram Login</h1>
	<div>Olá ${username}, seu login foi efetuado com sucesso!</div>
	<br>
	<a href="viewMedia">Visualizar fotos recentes do perfil do Google (@google)</a>
	<br>
	<%if(null == session.getAttribute("follows")){%>
		<a href="listFollows">Listar perfis que você segue</a>
	<%} else {%>
		<div style="width: 500px"> Perfis seguidos: ${follows}</div>
	<%}%>
<%}%>

</body>
</html>