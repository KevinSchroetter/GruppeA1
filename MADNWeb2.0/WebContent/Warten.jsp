<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Warte auf Spieler...</title>
</head>
<body>
	<h1>Warte auf andere Spieler</h1>
<%	response.setIntHeader("refresh",5); 
	out.println(application.getAttribute("anzahlSpieler"));
	out.println(" Nummer: "+session.getAttribute("spielerNummer"));
	if(application.getAttribute("gestartet")!=null){
		if(application.getAttribute("s1Session")!=null && application.getAttribute("s1Session")==session.getId()){
			response.sendRedirect("Spiel.jsp");
		}
		else if(application.getAttribute("s2Session")!=null && application.getAttribute("s2Session")==session.getId()){
			response.sendRedirect("Spiel.jsp");
		}
		else if(application.getAttribute("s3Session")!=null && application.getAttribute("s3Session")==session.getId()){
			response.sendRedirect("Spiel.jsp");
		}
		else if(application.getAttribute("s4Session")!=null && application.getAttribute("s4Session")==session.getId()){
			response.sendRedirect("Spiel.jsp");
		}
		else{
			session.setAttribute("name","GAST");
			session.setAttribute("farbe","ZUSCHAUER");
			response.sendRedirect("SpielVoll.jsp");
		}
	}
	else if(application.getAttribute("s4Session")!=null && session.getId()!=application.getAttribute("s4Session")&&application.getAttribute("sessID")==null){
		response.sendRedirect("Login.jsp");
	}
	else if(application.getAttribute("s3Session")!=null && session.getId()!=application.getAttribute("s3Session")&&application.getAttribute("sessID")==null){
		response.sendRedirect("Login.jsp");
	}
	else if(application.getAttribute("s2Session")!=null && session.getId()!=application.getAttribute("s2Session")&&application.getAttribute("sessID")==null){
		response.sendRedirect("Login.jsp");
	}
	else if(application.getAttribute("s1Session")!=null && session.getId()!=application.getAttribute("s1Session")&&application.getAttribute("sessID")==null){
		response.sendRedirect("Login.jsp");
	}
	else{
		out.println("wartend..");
	}%>
</body>
</html>