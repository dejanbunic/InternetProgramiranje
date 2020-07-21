<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/register.css"%></style>
<title>Registracija</title>
</head>
<body>
	<form action="register?action=register" method="post">
		<div class="container">
			Korisni&#x10Dko ime:<input type="text" name="username" required />
			E-mail:<input type="text" name="email" required /> 
			Ime: <input type="text" name="firstName" required/>
			Prezime: <input type="text" name="lastName" required/>
			Lozinka:<input type="password" name="password1" required />
			Ponovo unesi lozinku:<input type="password" name="password2" required/>
			<button type="submit" >Prijavi se </button>
			<%
				request.getSession().getAttribute("poruka");
			%>
		</div>
	</form>
</body>
</html>