<%@page import="br.com.sw2.comercial.service.flickr.LoginFlickr"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
LoginFlickr loginFlickr = new LoginFlickr();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java Flickr Login</title>
</head>
<body style="text-align: left; margin: 10 auto;">
<%if(null == session.getAttribute("username")){%>
	<div>
		<a href="<%=loginFlickr.getLoginRedirectOAuthURL()%>"> 
			<img style="margin-top: 10px;" src="./img/flickr-button.jpg">
		</a>
	</div>
<%} else {%>
	<h1>Flickr Login</h1>
	<div>Olá ${username}, seu login foi efetuado com sucesso!</div>
	<br>
	<a href="viewPhotos">Visualizar fotos recentes</a>
	<br>
	<a href="uploadPhoto">Upload de foto</a>
	<br>
	<a href="uploadVideo">Upload de vídeo</a>
<%}%>

</body>
</html>