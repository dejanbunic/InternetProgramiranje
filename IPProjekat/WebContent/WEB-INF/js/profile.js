
$(document).ready(function() {
	
	var readURL = function(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('.profile-pic').attr('src', e.target.result);
            }
    
            reader.readAsDataURL(input.files[0]);
        }
    }
   
    $(".file-upload").on('change', function(){
        readURL(this);
    });
    
    $(".upload-button").on('click', function() {
       $(".file-upload").click();
    });

  //-------------------------------SELECT CASCADING-------------------------//
	var flag="";
  var selectedCountry = (selectedRegion = selectedCity = "");
  // This is a demo API key that can only be used for a short period of time, and will be unavailable soon. You should rather request your API key (free)  from http://battuta.medunes.net/
  var BATTUTA_KEY = "53760e09c5335655b259999242bfe9e9";
  // Populate country select box from battuta API
  url = "https://restcountries.eu/rest/v2/region/europe";

  // EXTRACT JSON DATA.
  $.getJSON(url, function(data) {
    //console.log(data);
    $.each(data, function(index, value) {
      // APPEND OR INSERT DATA TO SELECT ELEMENT.
      $("#country").append(
        '<option value="' + value.alpha2Code + '">' + value.name + "</option>"
      );
    });
  });
  // Country selected --> update region list .
  $("#country").change(function() {
    selectedCountry = this.options[this.selectedIndex].text;
    countryCode = $("#country").val();
    // Populate country select box from battuta API
    url =
      "https://geo-battuta.net/api/region/" +
      countryCode +
      "/all/?key=" +
      BATTUTA_KEY +
      "&callback=?";
    $.getJSON(url, function(data) {
      $("#region option").remove();
      $.each(data, function(index, value) {
        // APPEND OR INSERT DATA TO SELECT ELEMENT.
        $("#region").append(
          '<option value="' + value.region + '">' + value.region + "</option>"
        );
      });
    });
	//console.log(countryCode); radi
	url1="https://restcountries.eu/rest/v2/alpha/"+countryCode;
	 $.getJSON(url1, function(data) {
		console.log(data);
      $.each(data, function(index, value) {
        // APPEND OR INSERT DATA TO SELECT ELEMENT.
        $("#avatar").attr("src",data.flag);
		//$("#avatar").load();
      });
    });
	//console.log(flag);
	
  });
	
  // Region selected --> updated city list
  $("#region").on("change", function() {
    selectedRegion = this.options[this.selectedIndex].text;
    // Populate country select box from battuta API
    countryCode = $("#country").val();
    region = $("#region").val();
    url =
      "https://geo-battuta.net/api/city/" +
      countryCode +
      "/search/?region=" +
      region +
      "&key=" +
      BATTUTA_KEY +
      "&callback=?";
    $.getJSON(url, function(data) {
    //  console.log(data);
      $("#city option").remove();
      $.each(data, function(index, value) {
        // APPEND OR INSERT DATA TO SELECT ELEMENT.
        $("#city").append(
          '<option value="' + value.city + '">' + value.city + "</option>"
        );
      });
    });
  });
  // city selected --> update location string
  $("#city").on("change", function() {
    selectedCity = this.options[this.selectedIndex].text;
    $("#location").html(
      "Locatation: Country: " +
        selectedCountry +
        ", Region: " +
        selectedRegion +
        ", City: " +
        selectedCity
    );
  });
	$([name=submitPassword]).on('click', function(e) {

   if (($('#oldPassword').val() == "")||($('#newPassword').val() == "")||($('#confirmPassword').val() == "")) {//check 2
       e.preventDefault();
  }
  if ($('#oldPassword').val() == $('#newPassword').val()) {//check 1
       e.preventDefault();
   }
   if ($('#newPassword').val() != $('#confirmPassword').val()) {//check 3
       e.preventDefault();
   }
	});
});
