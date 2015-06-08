<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MADN Player Login</title>
</head>
<body>



<form action="loginServlet" method="post">

	
<% 
	if((application.getAttribute("maxSpieler")!=null)&&((application.getAttribute("anzahlSpieler")!=null) && (Integer.parseInt(application.getAttribute("anzahlSpieler").toString())>Integer.parseInt(application.getAttribute("maxSpieler").toString())))){
	%>
	<h1>Spiel wurde bereits mit <%out.println(application.getAttribute("maxSpieler"));%> Spielern angelegt. Kein weiterer Login moeglich!</h1>
	<h1><a href='Spiel.jsp'>zurueck zum Spiel..</a></h1>
<%}else{
	
	String s1 = "";
	String s2 = "";
	String s3 = "";
	if (application.getAttribute("anzahlSpieler")==null){ 
		int anzahl = 1;
		application.setAttribute("anzahlSpieler",anzahl);%>
		<h1>Willkommen zu MADN</h1>
		<h2>Bitte wählen Sie die Anzahl der Spieler und legen den ersten Spieler an</h2>
   		<p>Spieleranzahl</p> 
   		<select name="maxSpielerAnzahl" size="1">
   			<option selected value="1">1 Spieler</option>
   			<option value="2">2 Spieler</option>
    		<option value="3">3 Spieler</option>
    		<option value="4">4 Spieler</option>
  		</select>
  		<br> 
  		<h2>Spieler <%out.println(anzahl);%> anlegen</h2>
  		Name: <input type="text" name="name" size="20">
  		<p>Spielerfarbe</p>
  		<select name="farbe" size="1">
  			<option selected value="rot">ROT</option>
  			<option value="blau">BLAU</option>
  			<option value="gruen">GRUEN</option>
  			<option value="gelb">GELB</option>
  		</select>
  		<select name="verhalten" size="1">
  			<option selected value="mensch">Mensch</option>
  			<option value="ki_aggressiv">KI Aggressiv</option>
  			<option value="ki_defensiv">KI Defensiv</option>
  		</select>
<%}
	else {%>
		<br> 
  		<h2>Spieler <% out.println(application.getAttribute("anzahlSpieler"));%> anlegen</h2>
  		Name: <input type="text" name="name" size="20">
  		<p>Spielerfarbe</p>
  		<select name="farbe" size="1">
  		<%
  		if(application.getAttribute("s1Farbe")!=null){
  			s1 = (String)application.getAttribute("s1Farbe");
  		}
  		if(application.getAttribute("s2Farbe")!=null){
  			s2 = (String)application.getAttribute("s2Farbe");
  		}
  		if(application.getAttribute("s3Farbe")!=null){
  			s3 = (String)application.getAttribute("s3Farbe");
  		}
  		
  		if((!s1.equals("rot")) && (!s2.equals("rot")) && (!s3.equals("rot"))){ %>
  			<option selected value="rot">ROT</option>
  			<%} 
  		 if((!s1.equals("blau")) && (!s2.equals("blau")) && (!s3.equals("blau"))){ %>
  			<option value="blau">BLAU</option>
  			<%}
   		if((!s1.equals("gruen")) && (!s2.equals("gruen")) && (!s3.equals("gruen"))){ %>
			<option value="gruen">GRUEN</option>
			<%} 
  		if((!s1.equals("gelb")) && (!s2.equals("gelb")) && (!s3.equals("gelb"))){ %>
			<option value="gelb">GELB</option>
			<%} %>
  		</select>
  		  <select name="verhalten" size="1">
  			<option selected value="mensch">Mensch</option>
  			<option value="ki_aggressiv">KI Aggressiv</option>
  			<option value="ki_defensiv">KI Defensiv</option>
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
<%} %>

</body>
</html>