function formValidation() {
	var isValid = false;
	if (grecaptcha.getResponse() != "") {
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
		isValid = false;
	}

	return isValid && $('#form').valid();
}

function validazione() {
	$.validator.addMethod("regx", function(value, element, regexpr) {
		return regexpr.test(value);
	});
	var options = {
		success : function(responseText, statusText, xhr, $form) {
			console.log(responseText);
		}
	};
	$('#form').ajaxForm(options); 
	
	$('#form')
			.validate(
					{
						errorPlacement : function(error, element) {
							if (element.attr("name") == "privacyPolicy") {
								error.insertAfter($("#privacyPolicyYes"));
							} else {
								error.insertAfter(element);
							}
						},
						rules : {
							codiceFiscale : {
								required : true,
								regx : /(^[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1})|(^[0-9]{11})$/i
							},
							ragioneSociale: {
								required : true
							},
							email : {
								required : true,
								email : true
							},
							telefono: {
								required : true
							},
							richiesta: {
								required : true
							},
							privacyPolicy : {
								required : true,
								equalTo : "#privacy-yes"
							},
							fasciaOraria: {
								required:true
							}
						},
						messages : {
							codiceFiscale : {
								required : "Campo obbligatorio.",
								regx : "Inserire una Partita Iva o un Codice Fiscale corretto."
							},
							ragioneSociale : {
								required : "Campo obbligatorio."
							},
							email : {
								required : "Campo obbligatorio.",
								email : "L'email non è in un formato corretto."
							},
							telefono : {
								required : "Campo obbligatorio.",
							},
							richiesta : {
								required : "Campo obbligatorio.",
							},
							privacyPolicy : {
								required : "Campo obbligatorio.",
								equalTo : "Accettare il trattamento."
							},
							fasciaOraria: {
								required : "Campo obbligatorio."
							}
						}
					});
}
