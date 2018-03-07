var servizio='';

function select2init() {
	var branch = getUrlParameter("path");
	$.ajax({
		type: "GET",
		url: "/findOptions/"+branch,
		ContentType: "application/json",
		dataType: "json",
		success: function(response){
			var select = $('#singleselect');
			$.each(response,function(key,value){
				//Prende l'oggetto 
				$(select).append("<option value='"+value.id+"'>"+value.oOption+"</option>");				
			});
			$('.js-example-basic-single').on('change',function(e){
				var data=$(this).val();
				var result = setContatti(data, true);
//				var data=e.params.data;
//				var result = setContatti(data.id, true);
			});
			$('.js-example-basic-single').select2({
					width: '100%',	
					theme:'classic'
			});
			
		},
		error: function(){
		}
	}); 
	$('#button-select').click(function(){
		var path = document.servizio>=215?16:12;
		history.pushState({ url: "/" }, "/", "?path="+path+"&servizio="+document.servizio);
		getCards();
	});
}

