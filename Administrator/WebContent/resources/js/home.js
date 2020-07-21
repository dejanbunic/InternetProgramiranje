
function myFunction() {
  var input, filter, table, tr, td, i, txtValue;
  
  table = document.getElementById("form:myTable");
  tr = table.getElementsByTagName("tr");

input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}
$(document).ready( function() {
  $('#example').dataTable( {
    "sDom": "Rlfrtip"
  } );
} );
$(document).ready(function() {
    var table = $("#user\\:myTable").DataTable( {
        scrollY:        "300px",
        scrollX:        true,
        scrollCollapse: true,
        paging:         false,
        columnDefs: [
            { width: '20%', targets: 0 }
        ],
        fixedColumns: true
    } );
} );
$(document).ready(function() {
    var table = $("#notification\\:myTable").DataTable( {
        scrollY:        "300px",
        scrollX:        true,
        scrollCollapse: true,
        paging:         false,
        columnDefs: [
            { width: '20%', targets: 0 }
        ],
        fixedColumns: true
    } );
} );


$(document).ready(function() {
   //var textInput = document.getElementById("hiddenButton").value;
	var jsonData = JSON.parse(document.getElementById("hiddenButton").value);
	var counter = jsonData.result;
	console.log("vrijednost je"+counter);
	
	
	
	
	var ctx = document.getElementById("myChart").getContext('2d');
var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'bar',

    // The data for our dataset
    data: {
        labels: ['0','1', '2', '3', '4', '5', '6', '7' ,'8' , '9', '10', '11', '12', '13', '14' ,'15' , '16', '17', '18', '19' ,'20' , '21', '22', '23'],
        datasets: [{
            label: 'Broj korisnika po satu',
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(255, 99, 132)',
            data: counter
        }]
    },

    // Configuration options go here
    options: {
	scales: {
    yAxes: [{
      scaleLabel: {
        display: true,
        labelString: 'broj korisnika'
      }
    }],
xAxes: [{
      scaleLabel: {
        display: true,
        labelString: 'sati'
      }
    }]
  }    
}
});
	
 } );


