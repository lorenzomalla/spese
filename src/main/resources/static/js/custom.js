$(document).ready(function() {
	for(i=0; i<3; i++) {
		createCard(i, "","Domanda "+i,"Risposta"+i);
	}
});

function createCard(id, icon, title, description) {
	$("#cardList").append("<div class=\"card-column\" data-node-id=\""+id+"\">"+
			                "<div class=\"card text-center\">"+
			                    "<div class=\"card-body\">"+
			                        "<div class=\"blue-square\">"+
			                            "<h3 class=\"title\"><i class=\"fa fa-wrench\"></i></h3>"+
			                        "</div>"+
			                        "<h5 class=\"card-title\">"+title+"</h5>"+
			                        "<h6 class=\"card-subtitle mb-2 text-muted\">"+description+"</h6>"+
			                    "</div>"+
			                "</div>"+
			            "</div>");
	$(".card-column").click(function() {
		var id = $(this).data("id");
		alert(id);
	});
//	$(".card-column:eq(0)").addClass("offset-md-3");
//	$(".card-column:not(:eq(0))").addClass("offset-md-0");
}