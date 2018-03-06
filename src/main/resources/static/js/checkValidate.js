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
					grecaptcha.reset();
				}
			},
			error : function(response) {
				isValid = false;
				grecaptcha.reset();
			}
		});
	} else {
		isValid = false;
		grecaptcha.reset();
	}

	return isValid && $('#form').valid();
}

function validazione() {
	$.validator.addMethod("regx", function(value, element, regexpr) {
		return regexpr.test(value);
	});
	var options = {
		success : function(responseText, statusText, xhr, $form) {
			if(responseText=="success") {
				history.pushState({ url: "/" }, "/", "?path=21&servizio="+document.servizio);
				getCards();			
			} else {
				history.pushState({ url: "/" }, "/", "?path=22&servizio="+document.servizio);
				getCards();
			}
		},
		error : function() {
			history.pushState({ url: "/" }, "/", "?path=22&servizio="+document.servizio);
			getCards();
		}
	};
	$('#form').ajaxForm(options); 
	
	var richiestaRequired = $("#richiesta").attr("data-required");
	
	$('#form')
			.validate(
					{
						errorPlacement : function(error, element) {
							if (element.attr("name") == "privacyPolicy") {
								error.insertAfter($("#privacyPolicyError"));
							} else if (element.attr("name") == "fasciaOraria") {
								error.insertAfter($("#fasciaOrariaError"));
							} else {
								error.insertAfter(element);
							}
						},
						rules : {
							codiceFiscale : {
								required : true,
								regx : /(^[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1})|(^[0-9]{11})$/i
							},
							name: {
								required : true
							},
							surname: {
								required : true
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
								required : richiestaRequired
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
								regx : "Inserire una Partita Iva o un Codice Fiscale valido."
							},
							name : {
								required : "Campo obbligatorio."
							},
							surname : {
								required : "Campo obbligatorio."
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
