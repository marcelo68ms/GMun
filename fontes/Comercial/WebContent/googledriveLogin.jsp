<%@page import="br.com.sw2.comercial.service.googledrive.LoginDrive,br.com.sw2.comercial.service.googledrive.FileUploadServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	LoginDrive loginDrive = new LoginDrive();
	FileUploadServlet fileManager = new FileUploadServlet();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java Google Drive Login</title>
</head>
<body style="text-align: left;">
<%if(null == session.getAttribute("user")){%>
	<div
		style=" height: 100px; width: 200px;">
		<a href="<%=loginDrive.getAuthorizationUrl()%>"> <img
			style="margin-top: 50px; margin-left: 50px" src="./img/drive_share_button.png" />
		</a>
	</div>
<%} else {%>
    <h2>Usuário do Drive: ${user.given_name}</h2>
    <p>E-mail: ${user.email}</p>
    <br>
    <%if(null == session.getAttribute("fileId")){%>
 	   <a href="fileupload">Inserir arquivo TesteDrive.txt</a>
 	   <br>
    <%} else {%>
  	  <p>Arquivo TesteDrive.txt inserido com sucesso!</p>
  	  <%if(null == session.getAttribute("updated")){%>
  		  <a href="fileupdate">Alterar conteúdo do arquivo TesteDrive.txt</a>
  		  <br>
  	  <%} else { %>
  	 	 <p>Arquivo TesteDrive.txt alterado com sucesso!</p>
  	  <%}%>
  	  <%if(null == session.getAttribute("moved")){%>
  	  	<a href="changefolder">Criar pasta Teste e mover o arquivo TesteDrive.txt para ela</a>
  	  	<br>
  	  <%} else { %>
  	 	 <p>Pasta Teste criada e arquivo TesteDrive.txt movido para ela com sucesso!</p>
  	  <%}%>
  	  <a href="filedownload">Baixar arquivo TesteDrive.txt</a>
    <%}%>

<%}%>
</body>
</html>