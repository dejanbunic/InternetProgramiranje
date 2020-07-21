$(document).ready(function() {
	var city1 = document.getElementById("city1").value;
	var city2 = document.getElementById("city2").value;
	var city3 = document.getElementById("city3").value;
	var apiKey =  "4cbc790a596555a4c20f16cf39448d3c";
	var url = "http://api.openweathermap.org/data/2.5/group?id="+city1+","+city2+","+city3+"&units=metric&APPID="+apiKey;

    $.getJSON(url, function(data) {

    $.each(data.list, function(index, value) {
      // APPEND OR INSERT DATA TO SELECT ELEMENT.\
		var elem = document.getElementById("country");

  		elem.innerHTML+="<label>"+value.name+"</label><br/>Opis: <label>"+ value.weather[0].main+ "</label><br/>Temp: <label>"+value.main.temp+"&#8451;</label><br/>Padavine: </label>"+value.main.humidity+"%</label><br/><hr/>";
		
 });
  });
	

	
	
});

function pictureCommentValidation(){
	var textInput = document.getElementByName("commentFile");
	var textPath = textInput.value;
	var allowedExtensions = /(\.jpg|\.png)$/i;
	if(!allowedExtensions.exec(textPath)){
		alert('Izaberi format slike');
		textInput.value='';
		return false;
	}
}
function videoValidation(){
	var videoInput = document.getElementById("videoFile");
	var videoPath = videoInput.value;
	var allowedExtensions = /(\.webm|\.mkv|\.flv|\.vob|\.ogv|\.ogg|\.drc|\.gif|\.gifv|\.mng|\.avi|\.mov\|.qt|\.wmv|\.yuv|\.rm|\.rmvb|\.asf|\.amv|\.mp4|\.m4p|\.m4v|\.mpg|\.mp2|\.mpeg|\.mpe|\.mpv|\.svi)$/i;
	if(!allowedExtensions.exec(videoPath)){
		alert('Izaberi video format');
		videoInput.value='';
		return false;
	}else{
		
		
	}
}
function textValidation(){
	var textInput = document.getElementById("textFile");
	var textPath = textInput.value;
	var allowedExtensions = /(\.jpg|\.png|\.docx)$/i;
	if(!allowedExtensions.exec(textPath)){
		alert('Izaberi format slike');
		textInput.value='';
		return false;
	}else{
		document.getElementById("formTextUpload").submit(); 
		
	}
}window.onload = function() {
document.getElementById('linkUpload').onkeydown = function(e){
   if(e.keyCode == 13){
     // submit
	document.getElementById("formLinkUpload").submit();
   }
}
}


$(document).ready(function() {
	//<input type="checkbox" id="vehicle1" name="vehicle1" value="Bike">
	var jsonData= JSON.parse(document.getElementById("dagnerCategory").value);
	 var pom="";
	for (var i = 0; i < jsonData.length; i++) {
			pom+="<input type=\"checkbox\" id="+jsonData[i].id+" name=\"checkbox\" value="+jsonData[i].id+" />"+jsonData[i].name+"<br/>"
		}
		document.getElementById("category").innerHTML=pom;
});

/*function ispis(){
	var pom="";
	var jsonData = JSON.parse(document.getElementById("rssFeed").value);
	for (var i = 0; i < jsonData.length; i++) {
    var counter = jsonData[i];
   // var pom = counter.tite.localeCompare("undefined");
	//console.log(counter.avatar);
	if( typeof counter.title==='undefined'){
		if( counter.link.includes("https://"))
			pom+="<br/> <img class=\"profile-pic\" src="+counter.avatar+  "><br/>"+counter.title+"<br/>"+counter.pubDate+"<br/>"+"<a href="+counter.link+">"+counter.link+"</a><br/>";
		else if (counter.link.includes("../../video"))
			pom+="<br/>"+counter.title+"<br/>"+counter.pubDate+"<br/>"+"<video width=\"400\" controls><source src="+counter.link+"></video>";
		else if(counter.link.includes("C:\\apache-tomcat-9.0.31\\bin\\work\\Catalina\\localhost\\wtpwebapps\\IPProjekat\\text"))
			pom+="<br/>"+counter.title+"<br/>"+counter.pubDate+"<br/>"+"<a href="+counter.link+">"+counter.link+"</a><br/>";
	}else{
		pom+="<br/>"+counter.title+"<br/>"+counter.pubDate+"<br/>"+counter.description+"<br/>"+"<a href="+counter.link+"/>"+counter.link+"</a><br/>";
	}
	document.getElementById("content").innerHTML=pom;
	}
}*/
//var createClock = setInterval(ispis, 10000);
$(document).ready(
            function() {
                setInterval(function() {
                   // var randomnumber = Math.floor(Math.random() * 100);
                    

					$("#content").load(location.href + " #content");
                }, 30000);
            });
function openForm() {
  document.getElementById("myForm").style.display = "block";
	initMap();
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}

function openVideoForm(){
	document.getElementById("videoUpload").style.display = "block";
}
function closeVideoForm(){
	document.getElementById("videoUpload").style.display = "none";
}
function openLinkForm(){
	document.getElementById("linkUpload").style.display = "block";
}
function closeLinkForm(){
	document.getElementById("linkUpload").style.display = "none";
}
function openTextForm(){
	document.getElementById("textUpload").style.display = "block";
}
function closeTextForm(){
	document.getElementById("textUpload").style.display = "none";
}
var marker;
function initMap() {
  // The location of Uluru
  var uluru = {lat: 43.9159, lng: 17.6791};
  // The map, centered at Uluru
	 map = new google.maps.Map(
      document.getElementById('map'), {zoom: 4, center: uluru});
  // The marker, positioned at Uluru
   

	map.addListener('click', function(e) {
    placeMarkerAndPanTo(e.latLng, map);
  });


}
function placeMarkerAndPanTo(latLng, map) {
	if (marker)
        marker.setMap(null);
   marker = new google.maps.Marker({
    position: latLng,
    map: map
  });
	document.getElementById("lat").value=latLng.lat();
	document.getElementById("lng").value=latLng.lng();
  map.panTo(latLng);
}

$(document).ready(function () {
    $('#checkBtn').click(function() {
      checked = $("input[type=checkbox]:checked").length;

      if(!checked) {
        alert("Izaberi kategoriju opasnosti");
        return false;
      }

    });
});

