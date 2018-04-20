$(document).ready(function(){

	getDataFromDb()
	filterTable();
	
});

function filterTable(){
	$("#searchInput").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#content tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
}

function getDataFromDb(){
	$.ajax({
		type: "GET",
		url: "/listSpesa",
		"dataSrc":"tableData",
		dataType: "json",
		success: function(data){
//			console.log("prova");
//			console.log(data);
			   $.each(data, function(i,value){
                   $("#content tbody").append("<tr>" +
                		   "<td>" + value.data + "</td>" +
                           "<td>" + value.descrizione + "</td>" +
                           "<td>" + value.importo + " € "+"</td>" + "</tr>")
               });
			   

		},
		error: function(response){
			alert("Errore");
		}
	});
}
