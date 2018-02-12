//(function(original) {
//        history.pushState = function(state) {
//            change(state);
//            return original.apply(this, arguments);
//        };
//})(history.pushState);

$(document).ready(function() {
	$(window).on("popstate", function(e) {
        change(e.originalEvent.state);
    });

	getCards();
});

function getCards() {
	$("#cardList").html("");
	var rootNodeId = 1;
	var params = {};
	if(location.search!="") {
		location.search.substr(1)
		$.each(location.search.substr(1).split("&"), function(index, elem) {
			var curr = elem.split("=");
			params[curr[0]] = curr[1]; 
		});
	} else {
		params["path"] = rootNodeId;
	}
	
	$.get("/getNodeById/"+params["path"], function(data) {
		console.log(data);
		var question = data.questions[0];
		if(!!question) {
			$("#question").text(question.pageTitle);
			$("#question-subtitle").text(question.pageSubtitle);			
		}
		$.each(data.treeStructures, function(index, element) {
			var answer = element.answers[0];
			if(!!answer) {
				createCard(answer.id, answer.image, answer.title, answer.description);
			}
		});
	});
	
//	for(i=0; i<getRandomInt(1, 6); i++) {
//		createCard(i, "ico:fa-wrench", "Domanda "+i,"Risposta"+i);
//	}
}

function change(state) {
	getCards();
    if(state === null) {
    	$("#backButton").removeClass("d-block").addClass("d-none");
		$("#logo-row").addClass("d-md-flex");
    } else {
    	$("#backButton").removeClass("d-none").addClass("d-block");
		$("#logo-row").removeClass("d-md-flex");
    }
}

function createCard(id, imageOrIcon, title, description) {
	var blueSquareContent = "";
	if(imageOrIcon.startsWith("ico:")) {
		blueSquareContent = "<i class=\"fas "+imageOrIcon.substr(4)+"\"></i>";
	} else {
		blueSquareContent = "<img src=\""+imageOrIcon+"\" />";
	}
	$("#cardList").append("<div class=\"card-column\" data-node-id=\""+id+"\">"+
			                "<div class=\"card text-center\">"+
			                    "<div class=\"card-body\">"+
			                        "<div class=\"blue-square\">"+
			                            "<h3 class=\"title\">"+blueSquareContent+"</h3>"+
			                        "</div>"+
			                        "<h5 class=\"card-title\">"+title+"</h5>"+
			                        "<h6 class=\"card-subtitle mb-2 text-muted\">"+description+"</h6>"+
			                    "</div>"+
			                "</div>"+
			            "</div>");
	$(".card-column").click(function(event) {
		event.stopPropagation();
		var id = $(this).data("node-id");
		alert("called " + id);	
		history.pushState({ url: "/" }, "/", "?path="+id);
		getCards();
//		alert(id);
	});
}

//remove in prod
function getRandomInt(min, max) {
  min = Math.ceil(min);
  max = Math.floor(max);
  return Math.floor(Math.random() * (max - min)) + min; //Il max è escluso e il min è incluso
}

