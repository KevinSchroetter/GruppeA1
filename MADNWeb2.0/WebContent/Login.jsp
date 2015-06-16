<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>MADN Player Login</title>
</head>
<body>

<%	if((application.getAttribute("maxSpieler")!=null)&&((application.getAttribute("anzahlSpieler")!=null) && (Integer.parseInt(application.getAttribute("anzahlSpieler").toString())>Integer.parseInt(application.getAttribute("maxSpieler").toString())))){
		response.sendRedirect("SpielVoll.jsp");
	}
    if(application.getAttribute("sessID")!=null && application.getAttribute("sessID")!=session.getId()){
    	response.sendRedirect("Warten.jsp");
    }
	else if(application.getAttribute("s1Session")!=null && application.getAttribute("s1Session")==session.getId()){
		response.sendRedirect("Warten.jsp");
	}
	else if(application.getAttribute("s2Session")!=null && application.getAttribute("s2Session")==session.getId()){
		response.sendRedirect("Warten.jsp");
	}
	else if(application.getAttribute("s3Session")!=null && application.getAttribute("s3Session")==session.getId()){
		response.sendRedirect("Warten.jsp");
	}
	else if(application.getAttribute("s4Session")!=null && application.getAttribute("s4Session")==session.getId()){
		response.sendRedirect("Warten.jsp");
	}
	else if(application.getAttribute("sessID")==null){
		application.setAttribute("sessID",session.getId());
	}
   	out.println(session.getId());
	String s1 = "";
	String s2 = "";
	String s3 = "";%>	
	<form action="loginServlet" method="post">
<%	if (application.getAttribute("s1Session")==null && application.getAttribute("exist")==null){ 
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
  			<option selected value="ROT">ROT</option>
  			<option value="BLAU">BLAU</option>
  			<option value="GRUEN">GRUEN</option>
  			<option value="GELB">GELB</option>
  		</select>
  		<p>Spielerverhalten</p>
  		<select name="verhalten" size="1">
  			<option selected value="mensch">Mensch</option>
  			<option value="ki_aggressiv">KI Aggressiv</option>
  			<option value="ki_defensiv">KI Defensiv</option>
  		</select>
<%	}
	else{%>	
		<br> 
  		<h2>Spieler <%out.println(application.getAttribute("anzahlSpieler"));%> anlegen</h2>
  		Name: <input type="text" name="name" size="20">
  		<p>Spielerfarbe</p>
  		<select name="farbe" size="1">
 <%		if(application.getAttribute("s1Farbe")!=null){
  			s1 = (String)application.getAttribute("s1Farbe");
  		}
  		if(application.getAttribute("s2Farbe")!=null){
  			s2 = (String)application.getAttribute("s2Farbe");
  		}
  		if(application.getAttribute("s3Farbe")!=null){
  			s3 = (String)application.getAttribute("s3Farbe");
  		}
   		if( ((!s1.equals("ROT")) && (!s2.equals("ROT")) && (!s3.equals("ROT"))) && ((!s1.equals("ROT-KI")) && (!s2.equals("ROT-KI")) && (!s3.equals("ROT-KI"))) ){%>
   			<option selected value="ROT">ROT</option>
<%		} 
   		if( ((!s1.equals("BLAU")) && (!s2.equals("BLAU")) && (!s3.equals("BLAU"))) && ((!s1.equals("BLAU-KI")) && (!s2.equals("BLAU-KI")) && (!s3.equals("BLAU-KI"))) ){%>
  			<option value="BLAU">BLAU</option>
<% 		}
   		if( ((!s1.equals("GRUEN")) && (!s2.equals("GRUEN")) && (!s3.equals("GRUEN"))) && ((!s1.equals("GRUEN-KI")) && (!s2.equals("GRUEN-KI")) && (!s3.equals("GRUEN-KI"))) ){%>
   			<option value="GRUEN">GRUEN</option>
<%		} 
   		if( ((!s1.equals("GELB")) && (!s2.equals("GELB")) && (!s3.equals("GELB"))) && ((!s1.equals("GELB-KI")) && (!s2.equals("GELB-KI")) && (!s3.equals("GELB-KI"))) ){%>
  			<option value="GELB">GELB</option>
<%		}%>
 		</select>
  		<p>Spielerverhalten</p>
  		  <select name="verhalten" size="1">
  			<option selected value="mensch">Mensch</option>
  			<option value="ki_aggressiv">KI Aggressiv</option>
  			<option value="ki_defensiv">KI Defensiv</option>
  		</select>
		<hr>
<%
		}%>
  		<br>
  		<br>
  
  
  		<input type="submit" name="submit" value="Spieler anlegen">
	</form>
</body>
</html>