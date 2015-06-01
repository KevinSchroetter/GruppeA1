<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Name setzen...</title>
</head>
<body>
<%
String name = request.getParameter("name");
tb.setName(name);

%>
<h2>Name gesetzt!</h2>
<form method="post" action="HalloWelt.jsp">
<input type="submit" name="submit" value="submit">
</form>

<%@include file="footer.jsp" %>
</body>
</html>