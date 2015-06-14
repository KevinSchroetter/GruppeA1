<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MADN - Game</title>


<style type="text/css">


  .felder td {
 	 width:30px;
     border-spacing: 7px;
     padding: 0.5em;
     text-align:center;
  }

</style>
</head>
<body>
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
		<h3>SpielBean angelegt</h3>
		<% out.println("<h2>" + this.getServletContext().getAttribute("amZug") + " ist am Zug</h2>"); 
		out.println(this.getServletContext().getSessionCookieConfig().getName());
		out.println(session.getAttribute("name"));
		out.println(session.getAttribute("farbe"));
		out.println(session.getId());  %>
	<% }else{%>
		<h3>keine SpielBean angelegt</h3>
<%} %>

<table name="refreshButton">
	<tr>
		<form action="refreshServlet" method="post">
		<input type="submit" name="refresh" id="refresh" value="Refresh">
		</form>
	</tr>
</table>
<br>

<table name="buttons">
	<tr>
		<td>
			<form action="wuerfelnServlet" method="post">
				<input type="submit" name="wuerfeln" id="wuerfeln" value="Wuerfeln">
			</form>
		</td>
		<td>
			<form action="neuesSpielServlet" method="post">
				<input type="submit" name="neuesSpiel" id="neuesSpiel" value="Neues Spiel">
			</form>
		</td>
		<td>
			<form action="spielSpeichern.jsp" method="post">
				<input type="submit" name="speichern" id="speichern" value="Spiel Speichern">
			</form>
		</td>
		<td>
			<form action="spielLaden.jsp" method="post">
				<input type="submit" name="laden" id="laden" value=" Spiel Laden">
			</form>
		</td>
		<td>
			<form action="sendGameServlet" method="post">
				<input type="submit" name="sendGame" id="sendGame" value="Spielstand versenden">
			</form>
		</td>
		<td>
			<form action="beendenServlet" method="post">
				<input type="submit" name="beenden" id="beenden" value="Spiel beenden">
			</form>
		</td>
	</tr>
</table>

<br><br>

<table class="felder" border="1" name="test">
	<tr>
		<td bgcolor=#FA5858>
			S2
		</td>
		<td bgcolor=#FA5858>
			S1
		</td>
		<td>
			
		</td>
		<td>	
			
		</td>
		<td bgcolor=#D8D8D8>
			9
		</td>
		<td bgcolor=#D8D8D8>
			10
		</td>
		<td bgcolor=#00BFFF>
			11
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
		<td bgcolor=#00BFFF>
			S2
		</td>
		<td bgcolor=#00BFFF>
			S1
		</td>
	</tr>
	<tr>
		<td bgcolor=#FA5858>
			S3
		</td>
		<td bgcolor=#FA5858>
			S4
		</td>
		<td>
			
		</td>
		<td>	
			
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td bgcolor=#00BFFF>
			E1
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
		<td bgcolor=#00BFFF>
			S3
		</td>
		<td bgcolor=#00BFFF>
			S4
		</td>
	</tr>
	<tr>
		<td>
			
		</td>
		<td >
			
		</td>
		<td>
			
		</td>
		<td>	
			
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td bgcolor=#00BFFF>
			E2
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
	</tr>
	<tr>
		<td>
			
		</td>
		<td >
			
		</td>
		<td>
			
		</td>
		<td>	
			
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td bgcolor=#00BFFF>
			E3
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
	</tr>
	<tr>
		<td bgcolor=#FA5858>
			1
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td bgcolor=#D8D8D8>	
			
		</td>
		<td bgcolor=#D8D8D8>
			5
		</td>
		<td bgcolor=#00BFFF>
			E4
		</td>
		<td bgcolor=#D8D8D8>
			15
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td bgcolor=#D8D8D8>
			19
		</td>
	</tr>
	<tr>
		<td bgcolor=#D8D8D8>
			40
		</td>
		<td bgcolor=#FA5858>
			E1
		</td>
		<td bgcolor=#FA5858>
			E2
		</td>
		<td bgcolor=#FA5858>	
			E3
		</td>
		<td bgcolor=#FA5858>
			E4
		</td>
		<td>
			
		</td>
		<td bgcolor=#3ADF00>
			E4
		</td>
		<td bgcolor=#3ADF00>
			E3
		</td>
		<td bgcolor=#3ADF00>
			E2
		</td>
		<td bgcolor=#3ADF00>
			E1
		</td>
		<td bgcolor=#D8D8D8>
			20
		</td>
	</tr>
	<tr>
		<td bgcolor=#D8D8D8>
			39
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td bgcolor=#D8D8D8>	
			
		</td>
		<td bgcolor=#D8D8D8>
			35
		</td>
		<td bgcolor=#F4FA58>
			E4
		</td>
		<td bgcolor=#D8D8D8>
			25
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td bgcolor=#3ADF00>
			21
		</td>
	</tr>
		<tr>
		<td>
			
		</td>
		<td >
			
		</td>
		<td>
			
		</td>
		<td>	
			
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td bgcolor=#F4FA58>
			E3
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
	</tr>
			<tr>
		<td>
			
		</td>
		<td >
			
		</td>
		<td>
			
		</td>
		<td>	
			
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td bgcolor=#F4FA58>
			E2
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
	</tr>
	<tr>
		<td bgcolor=#F4FA58>
			S2
		</td>
		<td bgcolor=#F4FA58>
			S1
		</td>
		<td>
			
		</td>
		<td>	
			
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td bgcolor=#F4FA58>
			E1
		</td>
		<td bgcolor=#D8D8D8>
			
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
		<td bgcolor=#3ADF00>
			S2
		</td>
		<td bgcolor=#3ADF00>
			S1
		</td>
	</tr>
	<tr>
		<td bgcolor=#F4FA58>
			S3
		</td>
		<td bgcolor=#F4FA58>
			S4
		</td>
		<td>
			
		</td>
		<td>	
			
		</td>
		<td bgcolor=#F4FA58>
			31
		</td>
		<td bgcolor=#D8D8D8>
			30
		</td>
		<td bgcolor=#D8D8D8>
			29
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
		<td bgcolor=#3ADF00>
			S3
		</td>
		<td bgcolor=#3ADF00>
			S4
		</td>
	</tr>
	
</table>
</body>
</html>