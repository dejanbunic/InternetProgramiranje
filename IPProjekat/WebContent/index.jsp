 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>Login</title>
	<meta  charset="utf-8" />
	<style><%@include file="/WEB-INF/css/login.css"%></style>
	<link rel="stylesheet" type="text/css" href="css/bluebliss.css" />
</head>
<body>
		<form action="login?action=login" method="post">
			<div class="container">
				Korisni&#x10Dko ime:<input type="text" name="username" placeholder="Unesi korisni&#x10Dko ime" required/>
				Lozinka:<input type="password" name="password" placeholder="Unesi lozinku" required/>
				         <button type="submit">Potvrdi</button>
						 <a href="register.jsp">Registracija</a> 	
			</div>
			<p>${poruka}</p>
		</form>
		

</body>
</html>  
