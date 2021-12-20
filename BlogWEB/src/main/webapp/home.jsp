<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	java.util.ArrayList<String> kategorije = (java.util.ArrayList<String>) request.getSession().getAttribute("kategorije");
	pageContext.setAttribute("kategorije", kategorije);
	%>
	<form action="/BlogWEB/UnosServlet" method="post">
		<c:if test="${!empty kategorije}">
			<select name="kategorija">
				<c:forEach items="${kategorije}" var="kat">
					<option value="${kat.id}">${kat.naziv}</option>
				</c:forEach>
			</select>
			<br>
			<input type="text" name="tekst">
			<input type="submit" value="Unesi">
		</c:if>
	</form>
	<br> -------------------------------------------------------------
	<h3>Pretraga</h3>
	<br>
	<form action="/BlogWEB/PretragaServlet" method="post">
		<input type="text" name="pretraga"> <br> <input
			type="submit" value="Nadji"> <br>

	</form>
	<c:if test="${! empty blogovi}">
		sad
				<c:forEach items="${blogovi}" var="blog">
					${blog.text}  ${blog.datum}  ${blog.blogKateg.naziv}  ${blog.blogKorisnik.username}
					<br>
		</c:forEach>
		<br>
	</c:if>
</body>
</html>