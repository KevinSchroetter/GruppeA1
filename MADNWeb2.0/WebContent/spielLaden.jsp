<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="ladenServlet" method="post">
		<select name="ladenTyp" size="1">
			<option selected value="CSV">CSV</option>
			<option value="SER">SER</option>
			<option value="XML">XML</option>
			<option value="PDF">PDF</option>
			Dateipfad: <input type="text" name="path" size="20">
		</select> <input type="submit" name="typ" id="laden" value="Laden">
	</form>


</body>
</html>