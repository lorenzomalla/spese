function formValidation() {
	var isValid = false;
	validazione();
	if(grecaptcha.getResponse()!="") {
		$.ajax({
			async : false,
			type : "POST",
			url : "/validationCheck",
			ContentType : "application/json",
			dataType : "json",
			data : {
				response : grecaptcha.getResponse(),
			},
			success : function(response) {
				if (response) {
					isValid = true;
				} else {
					isValid = false;
				}
			},
			error : function(response) {
				isValid = false;
			}
		});		
	} else {
		isValid=false;
	}
//	if ($('#form-registrazione').valid()) {
//		validazione();
//		if ($('#email').val() != "") {
//			$('#email').css("border-color", "#ced4da");
//		} else {
//			$('#email').css("border-color", "#FF0000");
//		}
//		if ($('#codiceFiscale').val() != "") {
//			$('#codiceFiscale').css("border-color", "#ced4da");
//		} else {
//			$('#codiceFiscale').css("border-color", "#FF0000");
//		}
//		if ($('#problema').val() != "") {
//			$('#problema').css("border-color", "#ced4da");
//		} else {
//			$('#problema').css("border-color", "#FF0000");
//		}
//	}
	return isValid && $('#form-registrazione').valid();
}

function validazione() {
	$.validator.addMethod("regx", function(value, element, regexpr) {          
	    return regexpr.test(value);
	});
	$('#form-registrazione').validate({
		rules : {
			codiceFiscale : {
				required : true,
				regx: /(^[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1})|(^[0-9]{11})$/
			},
			email : {
				required : true,
				email : true
			},
			privacyPolicy: {
				required: true,
				equalTo: "#privacy-yes"
			}
		},
		messages : {
			codiceFiscale : {
				required : "Campo obbligatorio.",
				regx : "Inserire una Partita Iva o un Codice Fiscale corretto."
			},
			email : {
				required : "Campo obbligatorio.",
				email : "L'email non è in un formato corretto."
			},
			privacyPolicy: {
				required : "Campo obbligatorio.",
				equalTo: "Accettare il trattamento."
			}
		}
	});
}

