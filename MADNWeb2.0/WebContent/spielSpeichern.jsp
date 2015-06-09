<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ich kann speichern!</title>
</head>
<body>

	<form action="speichernServlet" method="post">
		<select name="Zugriffstyp" size="1">
			<option selected value="CSV">CSV</option>
			<option value="SER">SER</option>
			<option value="XML">XML</option>
			<option value="PDF">PDF</option>
		</select>
		Dateipfad: <input type="text" name="path" size="20">
		 <input type="submit" name="typ" id="speichern" value="Speichern">
	</form>

</body>
</html>