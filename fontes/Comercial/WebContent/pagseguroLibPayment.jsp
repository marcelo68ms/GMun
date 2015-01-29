<%@page import="br.com.sw2.comercial.service.pagseguro.PaymentLibService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
PaymentLibService payment = new PaymentLibService();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java PagSeguro Payment</title>
</head>
<body style="text-align: left; margin: 10 auto;">
	<div>
		<a href="<%=payment.getPaymentApprovaltURL()%>"> 
			<img src="https://p.simg.uol.com.br/out/pagseguro/i/botoes/pagamentos/120x53-pagar.gif">
		</a>
	</div>
</body>
</html>