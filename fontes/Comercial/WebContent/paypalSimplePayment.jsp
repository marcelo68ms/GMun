<%@page import="br.com.sw2.comercial.service.paypal.SimplePaymentService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
SimplePaymentService payment = new SimplePaymentService();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java PayPal Payment</title>
</head>
<body style="text-align: left; margin: 0 auto;" bgcolor=#F0F0F0>
	<div>
		<a href="<%=payment.getPaymentApprovaltURL()%>"> 
			<img style="margin-top: 10px; background-color: #F0F0F0" src="./img/paypal-button.png">
		</a>
	</div>
</body>
</html>