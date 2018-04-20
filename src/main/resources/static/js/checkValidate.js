function validazione() {
	$.validator.addMethod("regx", function(value, element, regexpr) {
		return regexpr.test(value);
	});
	$('#form')
			.validate(
					{
						rules : {
							nome : {
								required : true,
							},
							cognome: {
								required : true
							},
							password: {
								required : true
							},
							eta: {
								required : true
							},
							email : {
								required : true,
								email : true
							},
							telefono: {
								required : true
							}
						},
						messages : {
							nome : {
								required : "Campo obbligatorio.",
							},
							cognome : {
								required : "Campo obbligatorio."
							},
							eta : {
								required : "Campo obbligatorio."
							},
							telefono : {
								required : "Campo obbligatorio."
							},
							email : {
								required : "Campo obbligatorio.",
								email : "L'email non è in un formato corretto."
							},
							password : {
								required : "Campo obbligatorio.",
							}
						}
					});
}
