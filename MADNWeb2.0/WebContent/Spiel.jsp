<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MADN - Game</title>
</head>
<body>
<h1>Ich bin ein Platzhalter f�r die Spiel JSP</h1>
<% if (application.getAttribute("beendet")!=null){
		response.sendRedirect("spielBeendet.jsp");
		%>
		
		
	<% }%>
<% if (application.getAttribute("refresh")!=null){
		application.removeAttribute("refresh");   %>
		<center>Seite wurde neu geladen</center>
		
	<% }{%>
		
<%} %>

<% if (application.getAttribute("game")!=null){%>
		<h1>SpielBean angelegt</h1>
	<% }else{%>
		<h1>keine SpielBean angelegt</h1>
<%} %>

<table name="navi">

<td width="200">
	<tr>
		<form action="refreshServlet" method="post">
		<input type="submit" name="refresh" id="refresh" value="Refresh">
	</form>
	</tr>
	<tr>
	</tr>
</td>
<td>
</td>
<td width="200">
	ich hasse tabellen..
</td>
<td>
	
	<form action="wuerfelnServlet" method="post">
		<input type="submit" name="wuerfeln" id="wuerfeln" value="Wuerfeln">
	</form>
	
	
	<form action="neuesSpielServlet" method="post">
		<input type="submit" name="neuesSpiel" id="neuesSpiel" value="Neues Spiel">
	</form>
	
	
	<form action="speichernServlet" method="post">
		<input type="submit" name="speichern" id="speichern" value="Spiel Speichern">
	</form>
	
	
	<form action="ladenServlet" method="post">
		<input type="submit" name="laden" id="laden" value=" Spiel Laden">
	</form>
	
	
	<form action="sendGameServlet" method="post">
		<input type="submit" name="sendGame" id="sendGame" value="Spielstand versenden">
	</form>
	
	
	<form action="beendenServlet" method="post">
		<input type="submit" name="beenden" id="beenden" value="Spiel beenden">
	</form>
	
</td>

</table>


</body>
</html>