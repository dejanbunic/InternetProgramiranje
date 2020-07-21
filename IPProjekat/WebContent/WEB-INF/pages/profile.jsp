<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="net.etfbl.project.dto.User"%>
<%@page import="net.etfbl.project.bean.UserBean"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta charset="utf-8"/>

<title>Profil</title>
<style><%@include file="../../WEB-INF/css/profile.css"%></style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src=https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js></script>
<script><%@include file="../../WEB-INF/js/profile.js"%> </script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"> 
</head>
<body>
  	

	<!--	<div class="wrapper">
		<div class="topnav">
  		<a  href="#home">Home</a>
 	 	<a class="active" href="#profile">Profil</a>
 	 	<br>
		</div> 
		<br>-->
		<%
		UserBean userBean = new UserBean();
		System.out.println(session.getAttribute("id"));
		String username = request.getParameter("username");
		User user = userBean.getUserByUsername(username);
		session.setAttribute("id",user.getId());
			%>
		<input type="hidden" name="id" value= <%= user.getId() %> >
	<div class="container">
	<form action="profile?action=profile" method="post" enctype="multipart/form-data">
		<div class="avatar-wrapper">
			<img class="profile-pic" src="https://i7.pngguru.com/preview/136/22/549/user-profile-computer-icons-girl-customer-avatar-thumbnail.jpg" />
		</div>
		<label for="profileImageUpload">
    		<img class ="icon" src="https://uploadpicture.gobbler.nl/wp-content/uploads/2018/09/upload_logo.png"/>
    	</label>	
		<input id="profileImageUpload"class="file-upload" name="file-upload" type="file" accept="image/*"/> <br> <br>
				
				Korisni&#x10Dko ime:<input type="text" name="username" value= <%= user.getUsername() %>   required/>
				Email <input type="text" name="email" value= <%= user.getEmail() %>   required/>
				Ime: <input type="text" name="firstName" value= <%= user.getFirstName() %> required/ >
				Prezime: <input type="text" name="lastName" value= <%= user.getLastName() %> required/ >
				Mail/Notifikacija: <select name="mail_notification" id="mail_notification">
    								<option value="Mail">Mail</option>
    								<option value="Notifikacija">Notifikacija</option>	
    								</select>
				Dr&zcaron;ava: <select id="country" name="country" required></select>   
				Regija: <select id="region" name="region" >-- Regija --</select>   
				Grad: <select id="city" name="city" >-- Grad --</select>  
				<!--      <button type="submit" name="submitProfil">Potvrdi</button> -->
		<input type="hidden" name="username1" value= <%= user.getUsername() %>   required/>
		Stara lozinka:<input type="password" name="oldPassword" placeholder="Unesi staru lozinku"/>
		Nova lozinka:<input type="password" name="newPassword" placeholder="Unesi lozinku"/>
		Unesi novu lozinku ponovo:<input type="password" name="confirmPassword" placeholder="Unesi lozinku ponovo"/>
		<button type="submit" name="submitPassword">Potvrdi</button>
	</form>
	</div>
	</div>
</body> 
</html>