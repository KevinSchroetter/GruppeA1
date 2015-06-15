<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login Finished</title>
</head>
<body>
	<h1>Alle Spieler angelegt. Spiel wurde gestartet!</h1>
<%	application.setAttribute("wuerfel","DEFAULT");%>
	<form action="Spiel.jsp" method="post">
		<input type="submit" name="ok" value="Okay, auf geht's!">
	</form>
</body>
</html>