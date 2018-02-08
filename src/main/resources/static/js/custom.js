(function(original) {
        history.pushState = function(state) {
            change(state);
            return original.apply(this, arguments);
        };
})(history.pushState);

$(document).ready(function() {
	$(window).on("popstate", function(e) {
        change(e.originalEvent.state);
    });

	getCards();
});

function getCards() {
	$("#cardList").html("");
	for(i=0; i<getRandomInt(1, 6); i++) {
		createCard(i, "","Domanda "+i,"Risposta"+i);
	}
}

function change(state) {
	getCards();
//    if(state === null) {
//         $("div").text("Original"); 
//    } else {
//        $("div").text(state.url);
//    }
}

function createCard(id, icon, title, description) {
	$("#cardList").append("<div class=\"col-xs-12 col-md-4 col-lg-2 card-column\" data-node-id=\""+id+"\">"+
			                "<div class=\"card text-center\">"+
			                    "<div class=\"card-body\">"+
			                        "<div class=\"blue-square\">"+
			                            "<h3 class=\"title\"><i class=\"fas fa-wrench\"></i></h3>"+
			                        "</div>"+
			                        "<h5 class=\"card-title\">"+title+"</h5>"+
			                        "<h6 class=\"card-subtitle mb-2 text-muted\">"+description+"</h6>"+
			                    "</div>"+
			                "</div>"+
			            "</div>");
	$(".card-column").click(function() {
		var id = $(this).data("node-id");
		history.pushState({ url: "/" }, "/", "?path="+id);
//		alert(id);
	});
}

//remove in prod
function getRandomInt(min, max) {
  min = Math.ceil(min);
  max = Math.floor(max);
  return Math.floor(Math.random() * (max - min)) + min; //Il max è escluso e il min è incluso
}

