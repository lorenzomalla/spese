//(function(original) {
//        history.pushState = function(state) {
//            change(state);
//            return original.apply(this, arguments);
//        };
//})(history.pushState);

var contatti = {};

function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

function setContatti(name){
	//var branch = getUrlParameter('path');
	//console.log("-------> branch:"+branch+" --- option:"+name);

	/*
	$.post("/getByRef", {"branch":"1", "option":"Self Virtual Server"}, function(data) {
		console.log(data);
		contatti.email=data.email;
		contatti.phone=data.phone;
		contatti.fax=data.fax;
		contatti.web=data.web;
		contatti.web=data.bcc;
	});
	*/
	$.ajax({
		type: "POST",
		url: "/getByRef",
  		contentType:"application/json; charset=utf-8",
		data: '{"id": '+name+'}',
		success: function(data){
			contatti = {};
			console.log(data);
			contatti.email=data.email;
			contatti.phone=data.phone;
			contatti.fax=data.fax;
			contatti.web=data.web;
			contatti.bcc=data.bcc;
			contatti.callback=data.callback;
			console.log(contatti);
			
			if(name!=null && name!="" ){
	        	document.servizio = name;
	        	$('#button-select').prop('disabled',false);
	        }else{
	            $('#button-select').prop('disabled',true);
	        }
			
		},
		error: function(data){
			console.log("Errore nella richiesta",data);
			contatti={};
		}
	});
};

$(document).ready(function() {
	$(window).on("popstate", function(e) {
        change(e.originalEvent.state);
    });
		
	getCards();
	
	$("#cardList").on("click", ".card-column", function(event) {
		event.stopPropagation();
		var id = $(this).data("node-id");
		var servizio = getUrlParameter('servizio');
		var tipoassistenza = getUrlParameter('tipoassistenza');
		var channel = getUrlParameter('channel');
//		alert("called " + id);	
		var historypushurl = "?path="+id;
		if(servizio!=null){
			historypushurl += "&servizio="+servizio;
		}
		if(tipoassistenza!=null){
			historypushurl += "&tipoassistenza="+tipoassistenza;
		}if(channel!=null){
			historypushurl += "&channel="+channel;
		}
		history.pushState({ url: "/" }, "/", historypushurl);
		getCards();
//		alert(id);
	});
		
});

function getCards() {
	$("#cardList").html("");
	$("#template").html("");
	$("#question").text("");
	$("#question-subtitle").text("");
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
	
    if(params["path"] == rootNodeId) {
    	$("#backButton").removeClass("d-block").addClass("d-none");
		$("#logo-row").addClass("d-md-flex");
    } else {
    	$("#backButton").removeClass("d-none").addClass("d-block");
		$("#logo-row").removeClass("d-md-flex");
    }
	
	$.get("/getNodeById/"+params["path"], function(data) {
		console.log(data);
		var question = data.questions[0];
		var template = data.template[0];
		if(!!template) {
			var templateMarkup = template.markup;
			console.log(contatti);
			if(!!contatti){
				templateMarkup = templateMarkup.replace("#email#",contatti.email);
				templateMarkup = templateMarkup.replace("#phone#", contatti.phone);
				templateMarkup = templateMarkup.replace("#fax#", contatti.fax);
				templateMarkup = templateMarkup.replace("#web#", contatti.web);
				templateMarkup = templateMarkup.replace("#bcc#", contatti.bcc);
			}
			$("#template").html(templateMarkup);
		} else {
			if(!!question) {
				$("#question").text(question.pageTitle);
				$("#question-subtitle").text(question.pageSubtitle);			
			}
			$.each(data.treeStructures, function(index, element) {
				var answer = element.answers[0];
				var servizio = getUrlParameter("servizio");
				console.log("CONTATTI --> "+String(contatti));
				console.log("SERVIZIO --> "+servizio);
				if(!!answer) {
					if(servizio>0){
						if(answer.title=='Telefono' && contatti.phone!="NULL"){
							createCard(element.id, answer.image, answer.title, answer.description);
						}
						if(answer.title=='Email' && contatti.email!="NULL"){
							createCard(element.id, answer.image, answer.title, answer.description);
						}
						if(answer.title=='Fax' && contatti.fax!="NULL"){
							createCard(element.id, answer.image, answer.title, answer.description);
						}
						if(answer.title=='Web' && contatti.web!="NULL"){
							createCard(element.id, answer.image, answer.title, answer.description);
						}
						if(answer.title=='Richiamami' && contatti.callback!="NULL"){
							createCard(element.id, answer.image, answer.title, answer.description);
						}
					}else{
						createCard(element.id, answer.image, answer.title, answer.description);
					}
					
				}
			});
		}
	});
	
//	for(i=0; i<getRandomInt(1, 6); i++) {
//		createCard(i, "ico:fa-wrench", "Domanda "+i,"Risposta"+i);
//	}
}

function change(state) {
	getCards();
//    if(state === null) {
//    	$("#backButton").removeClass("d-block").addClass("d-none");
//		$("#logo-row").addClass("d-md-flex");
//    } else {
//    	$("#backButton").removeClass("d-none").addClass("d-block");
//		$("#logo-row").removeClass("d-md-flex");
//    }
}

function createCard(id, imageOrIcon, title, description) {
	var blueSquareContent = "";
	if(imageOrIcon.startsWith("ico:")) {
		blueSquareContent = "<i class=\"fas "+imageOrIcon.substr(4)+" pt-2 display-4\"></i>";
	} else {
		blueSquareContent = "<img src=\""+imageOrIcon+"\" />";
	}
	$("#cardList").append("<div class=\"card-column\" data-node-id=\""+id+"\">"+
			                "<div class=\"card text-center\">"+
			                    "<div class=\"card-body\">"+
			                        "<div class=\"ml-2 blue-square\">"+
			                            "<h3 class=\"title\">"+blueSquareContent+"</h3>"+
			                        "</div>"+
			                        "<h5 class=\"card-title mt-2\">"+title+"</h5>"+
			                        "<h6 class=\"card-subtitle mb-2 text-muted\">"+description+"</h6>"+
			                    "</div>"+
			                "</div>"+
			            "</div>");
}

function captchaExpiredCallback() {
	disableButton("#continue");
}

function captchaCallback() {
	if(grecaptcha.getResponse().length==0) {
		disableButton("#continue");
	} else {
		enableButton("#continue");
	}
}

function disableButton(selector) {
	$(selector).attr("disabled", "disabled");
}

function enableButton(selector) {
	$(selector).removeAttr("disabled");
}
