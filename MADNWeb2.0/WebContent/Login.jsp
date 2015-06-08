<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Willkommen zu MADN</h1>


<form action="loginServlet" method="post">
<% 
if (session.getAttribute("anzahlSpieler")==null){ int anzahl = 1;%>
   <p>Spieleranzahl</p> <select name="spielerAnzahl" size="4">
    <option selected value="1">1 Spieler</option>
    <option value="2">2 Spieler</option>
    <option value="3">3 Spieler</option>
    <option value="4">4 Spieler</option>
  </select>
  <br> 
  <h2>Spieler <%out.println(anzahl);%></h2>
  Name: <input type="text" size="20">
  <p>Spielerfarbe</p>
  <select name="spielerFarbe" size="4">
  <option selected value="rot">ROT</option>
  <option value="blau">BLAU</option>
  <option value="gruen">GRUEN</option>
  <option value="gelb">GELB</option>
  </select>
  
  <% anzahl++;}
else {int anzahl = 2; %>
<br> 
  <h2>Spieler <% out.println(anzahl);%></h2>
  Name: <input type="text" size="20">
  <p>Spielerfarbe</p>
  <select name="spielerFarbe" size="4">
  <option selected value="rot">ROT</option>
  <option value="blau">BLAU</option>
  <option value="gruen">GRUEN</option>
  <option value="gelb">GELB</option>
  </select>
<hr>
  <%} 
 
  
 
  if (session.getAttribute("anzahlSpieler")==null) 
	  out.println("kein Spieler vorhanden");
  else
	  out.println("Spieler anzahl: "+session.getAttribute("anzahlSpieler"));
  if (application.getAttribute("anzahlSpieler")==null) 
	  out.println("kein Spieler vorhanden");
  else
	  out.println("Spieler anzahl: "+application.getAttribute("anzahlSpieler"));   %></p>
  
  
  
  <input type="submit" name="submit" value="Spieler anlegen">
</form>

</body>
</html>