<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Spiel beendet</title>
</head>
<body>
	<h1>Spiel ist beendet!</h1>
	<form action="neuesSpielServlet" method="post">
		<input type="submit" name="newGame" id="newGame" value="neues Spiel Starten">
	</form>
</body>
</html>