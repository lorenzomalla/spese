$(document).ready(function() {
	var modello = $('#modello');
	var cilindrata = $('#cilindrata');
	var colore = $('#colore')
//	$('#btnInsert').prop('disabled',true);
//	$('#datepicker').datepicker({
//        uiLibrary: 'bootstrap4',
//    	format: 'yyyy/mm/dd',
//    	startDate: '-3d'
//    });
	$('#btnInsert').click(function(){
		validazione();
		if(!$('#form').valid()){
			if(modello.val() == ""){
				modello.css("border-color","red");
			}else{
				modello.css("border-color","#ced4da");
			}
			if(cilindrata.val() == ""){
				cilindrata.css("border-color","red");
			}else{
				cilindrata.css("border-color","#ced4da");
			}
			if(colore.val() == ""){
				colore.css("border-color","red");
			}else{
				colore.css("border-color","#ced4da");
			}
		}
	});
});

function validazione() {
	$.validator.addMethod("regx", function(value, element, regexpr) {
		return regexpr.test(value);
	});
	$('#form')
				.validate({
						rules : {
							modello: {
								required : true
							},
							colore: {
								required : true
							},
							cilindrata: {
								required : true
							}
						},
						messages : {
							modello : {
								required : "Campo obbligatorio."
							},
							colore : {
								required : "Campo obbligatorio."
							},
							cilindrata : {
								required : "Campo obbligatorio."
							}
						}
					});
}
