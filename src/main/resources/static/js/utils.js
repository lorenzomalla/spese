$(document).ready(function() {
	$('#datepicker').datepicker({
        uiLibrary: 'bootstrap4',
    	format: 'yyyy/mm/dd',
    	startDate: '-3d'
    });
	
	$('#btnSave').click(function(){
		validazione();
	})
});

function clearDate(){
	$('#btnDelete').click(function(){
		$('#importo').val("");
		$('#datepicker').val("");
		$('#descrizione').val("");
	});
}

function validazione() {
	$.validator.addMethod("regx", function(value, element, regexpr) {
		return regexpr.test(value);
	});
	$('#form')
				.validate({
						rules : {
							importo: {
								required : true,
								regx : "^(0|(([1-9]{1}|[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{2}){1}(\\ [0-9]{3}){0,}))?([.](([0-9]{0,2})|\\-\\-))?([\\ ]{0,1})$"
							},
							data :{
								required : true,
								regx : "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$"
							},
							descrizione: {
								required : true
							}
						},
						messages : {
							data :{
								required : "",
								regx : ""
							},
							importo : {
								required : "Campo obbligatorio.",
									regx : "Importo errato."
							},
							descrizione : {
								required : "Campo obbligatorio."
							}
						}
					});
}