<%@page import="net.etfbl.project.bean.LogBean"%>
<%@page import="javax.json.JsonObject"%>
<%@page import="net.etfbl.project.bean.DangerBean"%>
<%@page import="net.etfbl.project.bean.UserBean"%>
<%@page import="net.etfbl.project.bean.DangerCategoryBean"%>
<%@page import="net.etfbl.project.dto.Notification"%>
<%@page import="net.etfbl.project.bean.NotificationBean"%>
<%@page import="org.json.JSONArray"%>
<%@page import="net.etfbl.project.utility.JSONUtils"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<%@include file="header.jsp" %>
	<%@page import="java.util.ArrayList"%> 
	<script  src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDpyOQ3qrRERHaD6K_sDiMMnJpAMi0YSi0&callback=initMap">
    </script>
  
	<script><%@include file="../../WEB-INF/js/home.js"%> </script>
	<style><%@include file="../../WEB-INF/css/home.css"%></style>
</head>
<body>
	<div id="fb-root"></div>
	<script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v7.0" nonce="TOlB82S4"></script>
	 <% UserBean userBean = new UserBean();
	 	DangerCategoryBean dangerCategoryBean = new DangerCategoryBean();
	 	ArrayList<Integer> name= (ArrayList<Integer>) session.getAttribute("citiesId"); 
		int city1 = name.get(0);
		int city2 = name.get(1);
		int city3 = name.get(2);
		DangerBean dangerBean = new DangerBean();
		LogBean logBean = new LogBean();
	 %>
	 <input type="hidden"  id="dagnerCategory" name="dagnerCategory" value='<%=dangerCategoryBean.getAll()%>'/>
	<input type="hidden"  id="rssFeed" name="rssFeed" value='<%=userBean.getRssFeed50()%>'/>
	<input type="hidden" id="username" name="username" value=<%=session.getAttribute("username") %>>
	 <div>
				<input type="hidden" id ="city1" name="city1" value= <%=city1 %> >
			</div>
			<div>
				<input type="hidden" id="city2" name="city2" value= <%=city2 %> >
			</div>
			<div>
				<input type="hidden" id="city3" name="city3" value= <%=city3 %> >
	 </div>
	 

	<div class="container-fluid">
  		<div class="row">
		    <div class="col-xs-2 col-sm-2 col-md-2" style="background-color:#E0FFFF;">
		      <div>
			<br/>
				<img class="profile-pic"  src=<%=session.getAttribute("picture") %> />
				<b> <%= session.getAttribute("firstName") %></b>
				<b> <%=session.getAttribute("lastName") %></b>
			<br/>
			<br/>
			Prijava broj <%=logBean.getUserNumberLogs((int)session.getAttribute("id")) %>
			</div>
			<div>
						<br>
						
			</div>
			<div>
				<input type="hidden" name="alpha2Code" value= <%= session.getAttribute("alpha2Code") %> >
				<input type="hidden" id="dangers" name="dangers"  value= <%= dangerBean.getAll() %> >
			</div>
			<%
				//if(userBean.isUserRegistered((int)session.getAttribute("id"))){
					
				if(userBean.getUserById((int)session.getAttribute("id")).isRegistered()){
			%>
			<br/>
			<button class="danger-button" onclick="openForm()">Hitno upozorenje</button>
			<br/>
			<br/>
			<% } %>
			<div id="dangersContent">
				<% if(userBean.getUserById((int)session.getAttribute("id")).getMailNotification().equals("Notifikacija")){
				for(int i=0;i<dangerBean.getAll().length();i++) {
					JSONObject o=dangerBean.getAll().getJSONObject(i); 
			//	System.out.println(o.getString("name"));
				%>
				<b>Naziv</b> 
				<br/>
				 <%=o.getString("name") %>
				<br/>
				<b>Opis</b>
				<br/>
				 <%=o.getString("description") %>
				<br/>
				<b>Kategorije</b> 
				<br/>
				<%
				JSONArray categoriesArray = o.getJSONArray("categories");
			     for(int j=0;j<categoriesArray.length();j++) {
			    	 JSONObject c= categoriesArray.getJSONObject(j);
				%>
				<%=c.getString("name") %>
				<br/>
				<%
				}
				%><% if(!(o.getDouble("lat")==0.0 && o.getDouble("lng")==0)) {%>
				<b>Latitude </b>  <%=o.getDouble("lat") %>
				<br/>
				<b>Longitude</b>  <%=o.getDouble("lng") %>
				<br/>
				<%
				}
				%>
				<b>Datum</b>  <%=o.getString("date") %>
				<br/>
				<hr>
				<%
					}
				}
				%>
			</div>
				<%
				//}
				%>
		    </div>
		    <div class="col-xs-8 col-sm-8 col-md-8" style="background-color:#ADD8E6;">
		       <br/>
			
    				<img class ="icon" src="https://cdn2.iconfinder.com/data/icons/interactive-shadow-pack/100/_-2-512.png" onclick="openVideoForm()"/>
  				    <img class ="icon" src="https://img.icons8.com/cotton/2x/upload.png" onclick="openLinkForm()"/>
					<img class ="icon" src="https://i7.uihere.com/icons/485/223/698/file-upload-19bc9749954cc52275ac04e6f5977104.png" onclick="openTextForm()"/>
				    <br/>
					<br/>
			
			
		<!--  <form id="formContent">-->	
			
			
	 	<div id="content">
			
			<%
			int k=0;
			JSONArray array= userBean.getRssFeed50();
			for(int i=0;i<array.length();i++) {
				
				JSONObject o=array.getJSONObject(i);
				if(o.getString("type").equals("rss")){%> 
				
				<%=o.getString("title") %>
				<br/>
				<%=o.getString("pubDate") %>
				<br/>
				<%=o.getString("description") %>
				<br/>
				<a href=<%=o.getString("link") %>> <%=o.getString("link") %> </a>
				<br/>
				<hr/>
				<%  
			}else{
				
				%><form  action="home?action=uploadComment" method="post" enctype="multipart/form-data">
					<img class="profile-pic" src= <%=o.getString("avatar") %>></img>
					<br/>
					<%=o.getString("title") %>
					<br/>
					<%=o.getString("pubDate") %>
					<br/>
					<% 
					    if(o.getString("link").contains("../../video")){
					    %>
					    	<video width="400" controls><source src=<%=o.getString("link") %>></video>
					    	<% 
					    }
					    else if(o.getString("link").contains("https://")){
							%>
							<a href=<%=o.getString("link") %>> <%=o.getString("link") %> </a>
							
							<%
						}
						else{
							%>
								<%=o.getString("link") %>
								<br/>
								
							<%
								JSONArray pictures = o.getJSONArray("pictures");
							     for(int j=0;j<pictures.length();j++){
							    	 JSONObject picture = pictures.getJSONObject(j);
							    	 %>
							    	 <img  width="100" src=<%= picture.getString("link") %>> 
							    	 
							    	 <%
							     }
							    
							    
							    	
						}%><div class="fb-share-button" data-href="https://developers.facebook.com/docs/plugins/" data-layout="button_count" data-size="small"><a target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fdevelopers.facebook.com%2Fdocs%2Fplugins%2F&amp;src=sdkpreparse" class="fb-xfbml-parse-ignore">Share</a></div>
						<a class="twitter-share-button"
 						 href="https://twitter.com/intent/tweet?text=Hello%20world">
						Tweet</a>
						<br/><br/>Komentari
							<%
							
							JSONArray comments= o.getJSONArray("comments");
							for(int z=0;z<comments.length();z++){
						    	JSONObject comment = comments.getJSONObject(z);
						    	%><br/>
						    		<br/>
					    	    <img class="profile-pic" src=<%=comment.getString("avatar") %> />
					    	    <% if(comment.has("text")){
					    	    %>
					    	    <%=comment.getString("text") %>
					    	    <br/>
					    	    <%}
					    	       if(comment.has("picture")){
					    	    %>
					    		<img  width="100" src=<%= comment.getString("picture") %>> 
					    	<%
					    	       }
					    }
					%>
					<br/>
						<textarea name="comment" placeholder="Unesi komentar"></textarea>
						<label for=<%=k %>>
    					<img class ="icon" src="https://uploadpicture.gobbler.nl/wp-content/uploads/2018/09/upload_logo.png"/>
    					</label>
    					<input id=<%=k %> name=<%=k %> class=<%=k %> type="file" onchange="return pictureCommentValidation()"/>
    					<input type="hidden" name="idNotification" value=<%= o.getInt("id") %>> 
						<input type="hidden" name="idCommentPicture" value=<%= k %>>
						<input type="hidden" name="idUser" value=<%=session.getAttribute("id") %>>
						<button type="submit"  class="btnComment">Potvrdi</button>
					</form>
					<br/>
					
					<hr/>	
				<%
				k++;
			}
			}
			%>
			
			
			</div>	
			<!--  </form>-->
		    </div>
		    <div class="col-xs-2 col-sm-2 col-md-2" style="background-color:#E0FFFF; justify-content:center;">
		    	<br/>
		    	<h5 style="font-weight: bold;">Vremenska prognoza</h5>
		    	<br/>
		      	<div class="country" id="country">
				</div>
		    </div>
  		</div>
	</div>
	
	<div class="form-popup" id="myForm">
    <form action="home?action=createDanger" class="form-container">
    <h1>Opasnost</h1>

    <label for="name"><b>Naziv</b></label>
    <input type="text" placeholder="Unesi naziv" name="name" required>
	
	<input type="hidden"  id="lat" name="lat"/>
	<input type="hidden"  id="lng" name="lng"/>
	<label for="description"><b>Opis</b></label> <br/>
	<textarea id="description" name="description" required></textarea>
	
	<label for="category"><b>Kategorije</b></label>
	<div  id="category"></div>
    <label for="map"><b>Mapa</b></label>
    <div id="map"></div>
    <br/>
    <button type="submit"  class="btn">Potvrdi</button>
    <button type="button" class="btn cancel" onclick="closeForm()">Zatvori</button>
 	 </form>
	</div>
	
		<div id="videoUpload" class="videoUpload">
	 	 <form action="home?action=uploadVideo" id="formVideoUpload" class="formVideoUpload" method="post" enctype="multipart/form-data">
				<label for="nameVideoUpload"><b>Naziv</b></label>
    			<input type="text" placeholder="Unesi naziv" name="nameVideoUpload" required>

    			<label for="videoFile">
    			<img class ="icon" src="https://cdn2.iconfinder.com/data/icons/interactive-shadow-pack/100/_-2-512.png"/>
    			</label>
    			<input id="videoFile" name="videoFile" class="videoFile" type="file" onchange="return videoValidation()"/>
    			
				<button type="submit"  class="btn" onclick="videoValidation()">Potvrdi</button>
    			<button type="button" class="btn cancel" onclick="closeVideoForm()">Zatvori</button>	
		</form>
       </div>
       
            <div id="linkUpload" class="linkUpload">
             <form  action="home?action=uploadLink" id="formLinkUpload" class="formLinkUpload" method="post" enctype="multipart/form-data">
             		<label for="nameLinkUpload"><b>Naziv</b></label>
    				<input type="text" placeholder="Unesi naziv" name="nameLinkUpload" required>
    				<label for="link"><b>Link</b></label>
					<input type="text" name="link" id="linkUpload">
					<button type="submit"  class="btn">Potvrdi</button>
    				<button type="button" class="btn cancel" onclick="closeLinkForm()">Zatvori</button>​
			 </form>
			</div>


		<div id="textUpload" class="textUpload">
			<form action="home?action=uploadText" id="formTextUpload" method="post" enctype="multipart/form-data">
				<label for="nameTextUpload"><b>Naziv</b></label>
    			<input type="text" placeholder="Unesi naziv" name="nameTextUpload" required>
    			<label for="desctiptionTextUpload"><b>Opis</b></label> <br/>
    			<textarea type="text" placeholder="Unesi opis" name="descriptionTextUpload" required></textarea>
    			<br/>
				<label for="textFile">
    			<img class ="icon" src="https://i7.uihere.com/icons/485/223/698/file-upload-19bc9749954cc52275ac04e6f5977104.png"/>
    			</label> 
				<input id="textFile" name="textFile" class="textFile" type="file" multiple/>
				<br/>
				<br/>
				<button type="submit"  class="btn">Potvrdi</button>
    			<button type="button" class="btn cancel" onclick="closeTextForm()">Zatvori</button>
			</form>
		</div>
</body>
</html>