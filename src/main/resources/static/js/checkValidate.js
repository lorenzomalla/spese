$(document).ready(function(){
		$('#continue').click(function(event){
			controllConditionCheck(event);
			validazione();
			if(!$('#form-registrazione').valid()){
				$('#email').css("border-color", "#FF0000");
				$('#codiceFiscale').css("border-color", "#FF0000");
				$('#problema').css("border-color", "#FF0000");
			}else{
				$('#email').css("border-color", "#ced4da");
				$('#codiceFiscale').css("border-color", "#ced4da");
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
					maxlength: ""
				},
				email:{
					required : "",
					email: ""
				},
			}
	});
}
function controllConditionCheck(){
	var selValue = $('input[name=policy]:checked').val(); 
	if(selValue == "no"){
		event.preventDefault();
		alert("Per favore prima di proseguire accetta le condizioni");
//		$('#policy').css("border-color", "#FF0000");
		$('#error-mess').html("Per favore prima di proseguire accetta le condizioni");
		return false;
	}else
		$('#error-mess').html("");
		return true;
}

