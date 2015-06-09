<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spieler Hinzufuegen Fehler</title>
</head>
<body>
<h1> Fehler: </h1>
<% out.println(application.getAttribute("error")); %>
<form action="Login.jsp" method="post">
<input type="submit" name="ok" id="ok" value="Zurueck zum LoginScreen!">
</form>

</body>
</html>