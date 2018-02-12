$(document).ready(function(){
		$('#continue').click(function(){
			validazione();
			if(!$('#form-registrazione').valid()){
				$('#email').css("border-color", "#FF0000");
				$('#codicefiscale').css("border-color", "#FF0000");
				$('#problema').css("border-color", "#FF0000");
			}else{
				$('#email').css("border-color", "#ced4da");
				$('#codicefiscale').css("border-color", "#ced4da");
				$('#problema').css("border-color", "#ced4da");
			}
		});
 
});
function validazione(){
	$.validator.addMethod("regx", function(value, element, regexpr) {          
	    return regexpr.test(value);
	});
	$('#form-registrazione').validate({
		rules:{
			codiceFiscale:{
				required:true,
				regx: /^[a-zA-z] ?([a-zA-z]|[a-zA-z] )*[a-zA-z]$/,
				maxlength : 15
			},
			email:{
				required:true,
				email: true
			}
		},
			messages:{
				codiceFiscale:{
					required:"",
				},
				email:{
					required : "",
				},
			}
	});
}

