<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>MADN - Game</title>
	<style type="text/css">
  		.felder td {
 	 		width:50px;
     		border-spacing: 7px;
     		padding: 0.5em;
     		text-align:center;
  		}
	</style>
</head>

<body>

<%	if (application.getAttribute("beendet")!=null){
		response.sendRedirect("spielBeendet.jsp");
	}	
	if(application.getAttribute("gestartet")==null){
		response.sendRedirect("spielBeendet.jsp");
	}
	if (application.getAttribute("game")!=null){
		
		String amZug = (String) this.getServletContext().getAttribute("amZug");
		String s1 = (String) this.getServletContext().getAttribute("s1Farbe");
		String s2 = (String) this.getServletContext().getAttribute("s2Farbe");
		String s3 = (String) this.getServletContext().getAttribute("s3Farbe");
		String s4 = (String) this.getServletContext().getAttribute("s4Farbe");
	out.println("gleich: "+s1.equals(amZug));
	out.println("<br>");
	out.println(s1);
	out.println(s2);
	out.println(s3);
	out.println(s4);
	out.println(amZug);
	
		if ((amZug.contains("KI") && amZug.equals(s1)) || (amZug.contains("KI") && amZug.equals(s2)) || (amZug.contains("KI") && amZug.equals(s3)) || (amZug.contains("KI") && amZug.equals(s4))){
			response.sendRedirect("zugDurchfuehrenKIServlet");
		}

		
 		out.println("<h2>" + this.getServletContext().getAttribute("amZug") + " ist am Zug</h2>"); 
 		out.println("<h2>Wuerfelergebnis: "+application.getAttribute("wuerfel")+"<h2>");
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
	boolean zug = false;
	String amZugColor = this.getServletContext().getAttribute("amZug").toString();
	String myColor = session.getAttribute("farbe").toString();;
	if (myColor.equals(amZugColor)){
		zug = true;
	}
	
	if (sessCheck==true && zug==true){
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
					out.println("<form action='spielSpeichern.jsp' method='post'>");
						out.println("<input type='submit' name='speichern' id='speichern' value='Spiel Speichern'>");
					out.println("</form>");
				out.println("</td>");
				out.println("<td>");
					out.println("<form action='spielLaden.jsp' method='post'>");
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
<%				if(application.getAttribute("ROTS2")!=null){
					if(zug==false && application.getAttribute("ROTS2").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("ROTS2").toString().substring(48, 51));
					}
					else
						out.println(application.getAttribute("ROTS2"));
				}
				else{
					out.println("S2");
				}%>
			</td>
			<td bgcolor=#FA5858>
<%				if(application.getAttribute("ROTS1")!=null){
					if(zug==false && application.getAttribute("ROTS1").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("ROTS1").toString().substring(48, 51));
					}
					else
						out.println(application.getAttribute("ROTS1"));
				}
				else{
					out.println("S1");
				}%>
			</td>
			<td>
					
			</td>
			<td>
				
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("9")!=null){
					if(zug==false && application.getAttribute("9").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("9").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("9"));
				}
				else{
					out.println("9");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("10")!=null){
					if(zug==false && application.getAttribute("10").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("10").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("10"));
				}
				else{
					out.println("10");
				}%>
			</td>
			<td bgcolor=#00BFFF>
<%				if(application.getAttribute("11")!=null){
					if(zug==false && application.getAttribute("11").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("11").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("11"));
				}
				else{
					out.println("11");
				}%>
			</td>
			<td>
			
			</td>
			<td>
			
			</td>
			<td bgcolor=#00BFFF>
<%				if(application.getAttribute("BLAUS2")!=null){
					if(zug==false && application.getAttribute("BLAUS2").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("BLAUS2").toString().substring(49, 52));
					}
					else
						out.println(application.getAttribute("BLAUS2"));
				}
				else{
					out.println("S2");
				}%>
			</td>
			<td bgcolor=#00BFFF>
<%				if(application.getAttribute("BLAUS1")!=null){
					if(zug==false && application.getAttribute("BLAUS1").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("BLAUS1").toString().substring(49, 52));
					}
					else
						out.println(application.getAttribute("BLAUS1"));
				}
				else{
					out.println("S1");
				}%>
			</td>
		</tr>
		<tr>
			<td bgcolor=#FA5858>
<%				if(application.getAttribute("ROTS3")!=null){
					if(zug==false && application.getAttribute("ROTS3").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("ROTS3").toString().substring(48, 51));
					}
					else
						out.println(application.getAttribute("ROTS3"));
				}
				else{
					out.println("S3");
				}%>
			</td>
			<td bgcolor=#FA5858>
<%				if(application.getAttribute("ROTS4")!=null){
					if(zug==false && application.getAttribute("ROTS4").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("ROTS4").toString().substring(48, 51));
					}
					else
						out.println(application.getAttribute("ROTS4"));
				}
				else{
					out.println("S4");
				}%>
			</td>
			<td>
				
			</td>
			<td>	
			
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("8")!=null){
					if(zug==false && application.getAttribute("8").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("8").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("8"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td bgcolor=#00BFFF>
<%				if(application.getAttribute("BLAUE1")!=null){
					if(zug==false && application.getAttribute("BLAUE1").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("BLAUE1").toString().substring(48, 51));
					}
					else
						out.println(application.getAttribute("BLAUE1"));
				}
				else{
					out.println("E1");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("12")!=null){
					if(zug==false && application.getAttribute("12").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("12").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("12"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td>
			
			</td>
			<td>
			
			</td>
			<td bgcolor=#00BFFF>
<%				if(application.getAttribute("BLAUS3")!=null){
					if(zug==false && application.getAttribute("BLAUS3").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("BLAUS3").toString().substring(49, 52));
					}
					else
						out.println(application.getAttribute("BLAUS3"));
				}
				else{
					out.println("S3");
				}%>
			</td>
			<td bgcolor=#00BFFF>
<%				if(application.getAttribute("BLAUS4")!=null){
					if(zug==false && application.getAttribute("BLAUS4").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("BLAUS4").toString().substring(49, 52));
					}
					else
						out.println(application.getAttribute("BLAUS4"));
				}
				else{
					out.println("S4");
				}%>
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
<%				if(application.getAttribute("7")!=null){
					if(zug==false && application.getAttribute("7").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("7").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("7"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td bgcolor=#00BFFF>
<%				if(application.getAttribute("BLAUE2")!=null){
					if(zug==false && application.getAttribute("BLAUE2").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("BLAUE2").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("BLAUE2"));
				}
				else{
					out.println("E2");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("13")!=null){
					if(zug==false && application.getAttribute("13").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("13").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("13"));
				}
				else{
					out.println(" ");
				}%>
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
<%				if(application.getAttribute("6")!=null){
					if(zug==false && application.getAttribute("6").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("6").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("6"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td bgcolor=#00BFFF>
<%				if(application.getAttribute("BLAUE3")!=null){
					if(zug==false && application.getAttribute("BLAUE3").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("BLAUE3").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("BLAUE3"));
				}
				else{
					out.println("E3");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("14")!=null){
					if(zug==false && application.getAttribute("14").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("14").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("14"));
				}
				else{
					out.println(" ");
				}%>
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
<%				if(application.getAttribute("1")!=null){
					if(zug==false && application.getAttribute("1").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("1").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("1"));
				}
				else{
					out.println("1");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("2")!=null){
					if(zug==false && application.getAttribute("2").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("2").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("2"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("3")!=null){
					if(zug==false && application.getAttribute("3").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("3").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("3"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td bgcolor=#D8D8D8>	
<%				if(application.getAttribute("4")!=null){
					if(zug==false && application.getAttribute("4").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("4").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("4"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("5")!=null){
					if(zug==false && application.getAttribute("5").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("5").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("5"));
				}
				else{
					out.println("5");
				}%>
			</td>
			<td bgcolor=#00BFFF>
<%				if(application.getAttribute("BLAUE4")!=null){
					if(zug==false && application.getAttribute("BLAUE4").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("BLAUE4").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("BLAUE4"));
				}
				else{
					out.println("E4");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("15")!=null){
					if(zug==false && application.getAttribute("15").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("15").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("15"));
				}
				else{
					out.println("15");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("16")!=null){
					if(zug==false && application.getAttribute("16").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("16").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("16"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("17")!=null){
					if(zug==false && application.getAttribute("17").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("17").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("17"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("18")!=null){
					if(zug==false && application.getAttribute("18").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("18").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("18"));
				}
				else{
					out.println(" ");
				}%>
			</td>	
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("19")!=null){
					if(zug==false && application.getAttribute("19").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("19").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("19"));
				}
				else{
					out.println("19");
				}%>
			</td>	
		</tr>
		<tr>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("40")!=null){
					if(zug==false && application.getAttribute("40").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("40").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("40"));
				}
				else{
					out.println("40");
				}%>
			</td>
			<td bgcolor=#FA5858>
<%				if(application.getAttribute("ROTE1")!=null){
					if(zug==false && application.getAttribute("ROTE1").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("ROTE1").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("ROTE1"));
				}
				else{
					out.println("E1");
				}%>
			</td>
			<td bgcolor=#FA5858>
<%				if(application.getAttribute("ROTE2")!=null){
					if(zug==false && application.getAttribute("ROTE2").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("ROTE2").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("ROTE2"));
				}
				else{
					out.println("E2");
				}%>
			</td>
			<td bgcolor=#FA5858>	
<%				if(application.getAttribute("ROTE3")!=null){
					if(zug==false && application.getAttribute("ROTE3").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("ROTE3").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("ROTE3"));
				}
				else{
					out.println("E3");
				}%>
			</td>
			<td bgcolor=#FA5858>
<%				if(application.getAttribute("ROTE4")!=null){
					if(zug==false && application.getAttribute("ROTE4").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("ROTE4").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("ROTE4"));
				}
				else{
					out.println("E4");
				}%>
			</td>
			<td>
			
			</td>
			<td bgcolor=#3ADF00>
<%				if(application.getAttribute("GRUENE4")!=null){
					if(zug==false && application.getAttribute("GRUENE4").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("GRUENE4").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("GRUENE4"));
				}
				else{
					out.println("E4");
				}%>
			</td>
			<td bgcolor=#3ADF00>
<%				if(application.getAttribute("GRUENE3")!=null){
					if(zug==false && application.getAttribute("GRUENE3").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("GRUENE3").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("GRUENE3"));
				}
				else{
					out.println("E3");
				}%>
			</td>
			<td bgcolor=#3ADF00>
<%				if(application.getAttribute("GRUENE2")!=null){
					if(zug==false && application.getAttribute("GRUENE2").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("GRUENE2").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("GRUENE2"));
				}
				else{
					out.println("E2");
				}%>
			</td>
			<td bgcolor=#3ADF00>
<%				if(application.getAttribute("GRUENE1")!=null){
					if(zug==false && application.getAttribute("GRUENE1").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("GRUENE1").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("GRUENE1"));
				}
				else{
					out.println("E1");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("20")!=null){
					if(zug==false && application.getAttribute("20").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("20").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("20"));
				}
				else{
					out.println("20");
				}%>
			</td>
		</tr>
		<tr>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("39")!=null){
					if(zug==false && application.getAttribute("39").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("39").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("39"));
				}
				else{
					out.println("39");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("38")!=null){
					if(zug==false && application.getAttribute("38").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("38").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("38"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("37")!=null){
					if(zug==false && application.getAttribute("37").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("37").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("37"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td bgcolor=#D8D8D8>	
<%				if(application.getAttribute("36")!=null){
					if(zug==false && application.getAttribute("36").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("36").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("36"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("35")!=null){
					if(zug==false && application.getAttribute("35").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("35").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("35"));
				}
				else{
					out.println("35");
				}%>
			</td>
			<td bgcolor=#F4FA58>
<%				if(application.getAttribute("GELBE4")!=null){
					if(zug==false && application.getAttribute("GELBE4").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("GELBE4").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("GELBE4"));
				}
				else{
					out.println("E4");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("25")!=null){
					if(zug==false && application.getAttribute("25").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("25").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("25"));
				}
				else{
					out.println("25");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("24")!=null){
					if(zug==false && application.getAttribute("24").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("24").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("24"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("23")!=null){
					if(zug==false && application.getAttribute("23").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("23").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("23"));
				}
				else{
					out.println(" ");
				}%>			
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("22")!=null){
					if(zug==false && application.getAttribute("22").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("22").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("22"));
				}
				else{
					out.println(" ");
				}%>			
			</td>
			<td bgcolor=#3ADF00>
<%				if(application.getAttribute("21")!=null){
					if(zug==false && application.getAttribute("21").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("21").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("21"));
				}
				else{
					out.println("21");
				}%>
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
<%				if(application.getAttribute("34")!=null){
					if(zug==false && application.getAttribute("34").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("34").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("34"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td bgcolor=#F4FA58>
<%				if(application.getAttribute("GELBE3")!=null){
					if(zug==false && application.getAttribute("GELBE3").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("GELBE3").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("GELBE3"));
				}
				else{
					out.println("E3");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("26")!=null){
					if(zug==false && application.getAttribute("26").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("26").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("26"));
				}
				else{
					out.println(" ");
				}%>
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
<%				if(application.getAttribute("33")!=null){
					if(zug==false && application.getAttribute("33").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("33").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("33"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td bgcolor=#F4FA58>
<%				if(application.getAttribute("GELBE2")!=null){
					if(zug==false && application.getAttribute("GELBE2").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("GELBE2").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("GELBE2"));
				}
				else{
					out.println("E2");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("27")!=null){
					if(zug==false && application.getAttribute("27").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("27").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("27"));
				}
				else{
					out.println(" ");
				}%>
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
<%				if(application.getAttribute("GELBS2")!=null){
					if(zug==false && application.getAttribute("GELBS2").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("GELBS2").toString().substring(49, 52));
					}
					else
						out.println(application.getAttribute("GELBS2"));
				}
				else{
					out.println("S2");
				}%>
			</td>
			<td bgcolor=#F4FA58>
<%				if(application.getAttribute("GELBS1")!=null){
					if(zug==false && application.getAttribute("GELBS1").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("GELBS1").toString().substring(49, 52));
					}
					else
						out.println(application.getAttribute("GELBS1"));
				}
				else{
					out.println("S1");
				}%>
			</td>
			<td>
				
			</td>
			<td>	
				
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("32")!=null){
					if(zug==false && application.getAttribute("32").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("32").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("32"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td bgcolor=#F4FA58>
<%				if(application.getAttribute("GELBE1")!=null){
					if(zug==false && application.getAttribute("GELBE1").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("GELBE1").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("GELBE1"));
				}
				else{
					out.println("E1");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("28")!=null){
					if(zug==false && application.getAttribute("28").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("28").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("28"));
				}
				else{
					out.println(" ");
				}%>
			</td>
			<td>
				
			</td>
			<td>
			
			</td>
			<td bgcolor=#3ADF00>
<%				if(application.getAttribute("GRUENS2")!=null){
					if(zug==false && application.getAttribute("GRUENS2").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("GRUENS2").toString().substring(50, 53));
					}
					else
						out.println(application.getAttribute("GRUENS2"));
				}
				else{
					out.println("S2");
				}%>
			</td>
			<td bgcolor=#3ADF00>
<%				if(application.getAttribute("GRUENS1")!=null){
					if(zug==false && application.getAttribute("GRUENS1").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("GRUENS1").toString().substring(50, 53));
					}
					else
						out.println(application.getAttribute("GRUENS1"));
				}
				else{
					out.println("S1");
				}%>
			</td>
		</tr>
		<tr>
			<td bgcolor=#F4FA58>
<%				if(application.getAttribute("GELBS3")!=null){
					if(zug==false && application.getAttribute("GELBS3").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("GELBS3").toString().substring(49, 52));
					}
					else
						out.println(application.getAttribute("GELBS3"));
				}
				else{
					out.println("S3");
				}%>
			</td>
			<td bgcolor=#F4FA58>
<%				if(application.getAttribute("GELBS4")!=null){
					if(zug==false && application.getAttribute("GELBS4").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("GELBS4").toString().substring(49, 52));
					}
					else
						out.println(application.getAttribute("GELBS4"));
				}
				else{
					out.println("S4");
				}%>
			</td>
			<td>
				
			</td>
			<td>	
				
			</td>
			<td bgcolor=#F4FA58>
<%				if(application.getAttribute("31")!=null){
					if(zug==false && application.getAttribute("31").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("31").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("31"));
				}
				else{
					out.println("31");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("30")!=null){
					if(zug==false && application.getAttribute("30").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("30").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("30"));
				}
				else{
					out.println("30");
				}%>
			</td>
			<td bgcolor=#D8D8D8>
<%				if(application.getAttribute("29")!=null){
					if(zug==false && application.getAttribute("29").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("29").toString().substring(45, 48));
					}
					else
						out.println(application.getAttribute("29"));
				}
				else{
					out.println("29");
				}%>
			</td>
			<td>
				
			</td>
			<td>
				
			</td>
			<td bgcolor=#3ADF00>
<%				if(application.getAttribute("GRUENS3")!=null){
					if(zug==false && application.getAttribute("GRUENS3").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("GRUENS3").toString().substring(50, 53));
					}
					else
						out.println(application.getAttribute("GRUENS3"));
				}
				else{
					out.println("S3");
				}%>
			</td>
			<td bgcolor=#3ADF00>
<%				if(application.getAttribute("GRUENS4")!=null){
					if(zug==false && application.getAttribute("GRUENS4").toString().substring(0,1).equals("<")){
						out.println(application.getAttribute("GRUENS4").toString().substring(50, 53));
					}
					else
						out.println(application.getAttribute("GRUENS4"));
				}
				else{
					out.println("S4");
				}%>
			</td>
		</tr>	
	</table>
</body>
</html>