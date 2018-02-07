$(document).ready(function() {
	for(i=0; i<3; i++) {
		createCard("","Domanda "+i,"Risposta"+i);
	}
});

function createCard(icon, title, description) {
	$("#cardList").append("<div class=\"offset-3 offset-sm-4 col-2 col-sm-4\">"+
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
}