<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
    <title>Java Twitter Login</title>
</head>
<body style="text-align: left; margin: 0 auto;">
<%if(null == session.getAttribute("twitter")){%>
	<div>
    	<a href="twittersignin">
    		<img style="margin-top: 50px; margin-left: 50px;" src="./img/Sign-in-with-Twitter-darker.png"/>
    	</a>
    </div>
<%}%>

<%if(null != session.getAttribute("twitter")){%>
    <h2>Usuário do Twitter: ${twitter.screenName} (ID: ${twitter.id})</h2>
<%}%>
</body>
</html>