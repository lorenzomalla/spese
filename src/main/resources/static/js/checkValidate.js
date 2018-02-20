function formValidation() {
	var isValid = false;
			$.ajax({
				async: false,
				type: "POST",
				url: "/validationCheck",
				ContentType: "application/json",
				dataType: "json",
				data: {
					response: grecaptcha.getResponse(),
				},
			success: function(response){
				if(response == true){
				alert("successo");
				isValid = true;
				}
			},
			error: function(response){
				if(response == false){
					alert("Errore");
					isValid = false;
				}
			}
		});
			if($('#form-registrazione').valid()){
				validazione();
				if($('#email').val() != ""){
					$('#email').css("border-color", "#ced4da");
				}else{
					$('#email').css("border-color", "#FF0000");					
				}
				if($('#codiceFiscale').val() != ""){
					$('#codiceFiscale').css("border-color", "#ced4da");
				}else{
					$('#codiceFiscale').css("border-color", "#FF0000");					
				}
				if($('#problema').val() != ""){
					$('#problema').css("border-color", "#ced4da"); 
				}else{	
					$('#problema').css("border-color", "#FF0000");
				}
		}
			controllConditionCheck(event);
		return isValid;
}

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
