<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@
    	page import="java.util.Date"
     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hallo Welt</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%
if(tb.getName()!=null)
	out.println("Name: "+tb.getName());
else
	out.println("kein Name vorhanden");

	System.out.println("Neuer Zugriff");

%>
<br>
<br>
Hallo Benutzer, heite ist

<%	

	Date d = new Date();
	out.println(d+".");
%>
<form method="post" action="doIt.jsp">
Name: <input type="text" size="20" name="name" value="">
<input type="submit" name="submit" value="send den shit!">
</form>
<br>
<br>
<%@ include file="footer.jsp" %>

</body>
</html>