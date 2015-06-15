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
	}	
	if(application.getAttribute("gestartet")==null){
		response.sendRedirect("Login.jsp");
	}
	if (application.getAttribute("game")!=null){
 		out.println("<h2>" + this.getServletContext().getAttribute("amZug") + " ist am Zug</h2>"); 
		out.println("Ihre Sitzung: ");
 		out.println("Name = "+session.getAttribute("name")+" ");
		out.println(session.getAttribute("farbe")+"<br>");
		out.println("Session ID: "+session.getId()+"<br><br>");  
	 }
	 else{
	 	out.println("<h3>Kein Spielbean angelegt</h3>");
	 }%>
	<form action="refreshServlet" method="post">
		<input type="submit" name="refresh" id="refresh" value="Refresh">     <%if (application.getAttribute("refresh")!=null){
			application.removeAttribute("refresh");  
			out.println("Seite wurde neu geladen");
		}%>
	</form>
	<br>
<%	boolean sessCheck = false;
	String myId=session.getId();
	String s1 = (String)application.getAttribute("s1Session");
	String s2 = (String)application.getAttribute("s2Session");
	String s3 = (String)application.getAttribute("s3Session");
	String s4 = (String)application.getAttribute("s4Session");
	if (myId == s1){
		sessCheck=true;
	}
	else if (myId == s2){
		sessCheck=true;
	}
	else if (myId == s3){ 
		sessCheck=true;
	}
	else if (myId == s4){ 
		sessCheck=true;
	}
	else{
		sessCheck=false;
		session.setAttribute("name","GAST");
		session.setAttribute("farbe","ZUSCHAUER");
	}
	if (sessCheck==true){
		out.println("<table name='buttons'>");
			out.println("<tr>");
				out.println("<td>");
					out.println("<form action='wuerfelnServlet' method='post'>");
						out.println("<input type='submit' name='wuerfeln' id='wuerfeln' value='Wuerfeln'>");
					out.println("</form>");
				out.println("</td>");
				out.println("<td>");
					out.println("<form action='neuesSpielServlet' method='post'>");
						out.println("<input type='submit' name='neuesSpiel' id='neuesSpiel' value='Neues Spiel'>");
					out.println("</form>");
				out.println("</td>");
				out.println("<td>");
					out.println("<form action='spielSpeichern' method='post'>");
						out.println("<input type='submit' name='speichern' id='speichern' value='Spiel Speichern'>");
					out.println("</form>");
				out.println("</td>");
				out.println("<td>");
					out.println("<form action='spielLaden' method='post'>");
						out.println("<input type='submit' name='laden' id='laden' value='Spiel Laden'>");
					out.println("</form>");
				out.println("</td>");
				out.println("<td>");
					out.println("<form action='sendGameServlet' method='post'>");
						out.println("<input type='submit' name='sendGame' id='sendGame' value='Spielstand versenden'>");
					out.println("</form>");
				out.println("</td>");
				out.println("<td>");
					out.println("<form action='beendenServlet' method='post'>");
						out.println("<input type='submit' name='beenden' id='beenden' value='Spiel beenden'>");
					out.println("</form>");
				out.println("</td>");
			out.println("</tr>");
		out.println("</table>");
		}
		else{
			out.println("Zum verfolgen des Spiels, nutzen Sie den REFRESH-Button");
		}%>
		<br>
	<table class="felder" border="1" id="test">
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
			<td>
			
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
			<td>
			
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