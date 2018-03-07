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

function setContatti(name, _async){
	if(!!name) {
		$.ajax({
			type: "POST",
			url: "/getByRef",
			async: _async,
	  		contentType:"application/json; charset=utf-8",
			data: '{"id": '+name+'}',
			success: function(data){
				contatti = {};
				contatti.email=data.email;
				contatti.phone=data.phone;
				contatti.fax=data.fax;
				contatti.web=data.web;
				contatti.bcc=data.bcc;
				contatti.callback=data.callback;
				contatti.infoCallback=data.infoCallback;
				
				if(name!=null && name!="" ){
		        	document.servizio = name;
		        	$('#button-select').prop('disabled',false);
		        }else{
		            $('#button-select').prop('disabled',true);
		        }
				
			},
			error: function(data){
				contatti={};
			}
		});
	}
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
		var question = data.questions[0];
		var template = data.template[0];
		if(!!question) {
			$("#question").text(question.pageTitle);
			$("#question-subtitle").text(question.pageSubtitle);			
		}
		if(!!template) {
			var templateMarkup = template.markup;
			
			var servizio = getUrlParameter('servizio');
			if(servizio!=null){
				setContatti(servizio, false);
				$("#servizioform").remove();
				$("#form").append("<input type='hidden' id='servizioform'  name='servizio' value='"+servizio+"' />");
			}
			
			if(!!contatti){
				
				templateMarkup = templateMarkup.replace(/#email#/g,contatti.email);
				templateMarkup = templateMarkup.replace(/#phone#/g, contatti.phone);
				templateMarkup = templateMarkup.replace(/#fax#/g, contatti.fax);
				templateMarkup = templateMarkup.replace(/#web#/g, contatti.web);
				templateMarkup = templateMarkup.replace(/#bcc#/g, contatti.bcc);
				templateMarkup = templateMarkup.replace(/#infoCallback#/g, contatti.infoCallback);
				templateMarkup = templateMarkup.replace(/#callback#/g, contatti.callback);
				templateMarkup = templateMarkup.replace(/#captcha#/g, $("#captchakey").text());
				templateMarkup = templateMarkup.replace(/#privacyPdf#/g, $("#privacyPdf").text());
			}
			$("#template").html(templateMarkup);
			if(servizio!=null){
				$("#servizioform").remove();
				$("#form").append("<input type='hidden' id='servizioform'  name='servizio' value='"+servizio+"' />");
			}
		} else {
			$.each(data.treeStructures, function(index, element) {
				var servizio = getUrlParameter('servizio');
				if(servizio!=null && $.isEmptyObject(contatti)){
					setContatti(servizio, false);
				}
				
				var answer = element.answers[0];
				var servizio = getUrlParameter("servizio");
				if(!!answer) {
					if(servizio>0){
						if(answer.id==12 && contatti.phone!="null"){
							createCard(element.id, answer.image, answer.title, answer.description);
						}
						if((answer.id==13 || answer.id==16 || answer.id==18) && contatti.email!="null"){
							createCard(element.id, answer.image, answer.title, answer.description);
						}
						if((answer.id==14 || answer.id==17 || answer.id==19) && contatti.callback!="null"){
							createCard(element.id, answer.image, answer.title, answer.description);
						}
					}else{
						createCard(element.id, answer.image, answer.title, answer.description);
					}
					
				}
			});
		}
	});
}

function change(state) {
	getCards();
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

