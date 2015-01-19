<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java Todoist Login</title>
</head>
<body style="text-align: left; margin: 0 auto;">

<%if(null == session.getAttribute("user")){%>
	<h2>Não foi possível efetuar o login</h2>
<%} else {%>
	<h2>Olá ${user}</h2>
	<%if(null == session.getAttribute("itemId")){%>
		<div><a href="addItem">Criar Tarefa</a></div>
	<%} else {%>
		<div>Tarefa criada com sucesso! (Id = ${itemId})</div>
		<div><a href="getItems">Recuperar Tarefas</a></div>
		<%if(null == session.getAttribute("updated")){%>
			<div><a href="updateItem">Alterar data da tarefa para hoje</a></div>
		<%} else {%>
			<div>Data da Tarefa alterada com sucesso!</div>
			<%if(null == session.getAttribute("moved")){%>
				<div><a href="moveItem">Mover tarefa para projeto</a></div>
			<%} else {%>
				<div>Tarefa movida com sucesso!</div>
				<%if(null == session.getAttribute("completed")){%>
					<div><a href="completeItems">Completar tarefa</a></div>
				<%} else {%>
					<div>Tarefa marcada como completa com sucesso!</div>
					<%if(null == session.getAttribute("removed")){%>
						<div><a href="deleteItems">Excluir tarefa</a></div>
					<%} else {%>
						<div>Tarefa excluída com sucesso!</div>
					<%}%>
				<%}%>
			<%}%>
		<%}%>
	<%}%>
<%}%>
</body>
</html>