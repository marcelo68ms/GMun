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
<title>Java Trello OAuth Login</title>
</head>
<body style="text-align: left; margin: 0 auto;">
<%if(null == session.getAttribute("user")){%>
	<div>
		<a href="<%=loginTrello.getLoginRedirectOAuthURL()%>"> <img
			style="margin-top: 50px; margin-left: 50px;" src="./img/trello-button.png"/>
		</a>
	</div>
<%} else {%>
	<h1>Olá ${user}</h1>
	<%if(null == session.getAttribute("boardId")){%>
		<div><a href="createBoard">Criar um board no Trello</a></div>
	<%} else {%>
		<div>Board criado com sucesso! Nome: Projeto Fapesp (Id: ${boardId})</div>
		<div><a href="createCard">Criar um card no board</a></div>
		<%if(null != session.getAttribute("cardId")){%>
			<div>Card criado com sucesso! Nome: Teste CRUD (Id: ${cardId})</div>
			<%if(null == session.getAttribute("moved") && null == session.getAttribute("removed")){%>
				<div><a href="moveCard">Mover card de To Do para Done</a></div>
			<%} else {%>
				<div>Card movido para Done com sucesso!</div>
				<%if(null == session.getAttribute("removed")){%>
					<div><a href="removeCard">Apagar card</a></div>
				<%} else {%>
					<div>Card removido com sucesso!</div>
				<%}%>
			<%}%>
		<%}%>
	<%}%>
<%}%>	
</body>
</html>