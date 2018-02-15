var redirecturl='';
$(document).ready(function(){
	var opt = "<option value=\"\"></option>";
	$.ajax({
		type: "GET",
		url: "/findOptions",
		ContentType: "application/json",
		dataType: "json",
		success: function(response){
			var select = $('select2:select');
			$.each(response,function(key,valore){
				//Prende l'oggetto 
//			JSON.stringify(value);
				opt +="<option value="+valore.value+">"+valore.option+"</option>";
			});
			select.html(opt);
			select.show();
			opt = "";
		},
		error: function(){
			console.log("Errore nella richiesta");
		}
	});
});

$('.js-data-example-ajax').select2(
		{theme:'classic',placeholder:'Seleziona un valore dalla lista...',
		allowClear:true,
//		ajax: {
//			url: 'https://dgs-backend.herokuapp.com/findOptions',
//			data: function (params) {
//				var query = {
////					search: params.term,
////					type: 'public'
//				}
//				console.log(query);
//				return query;
//			},
//			processResults: function (data) {
//				console.log(data);
//				return {
//					results: data.items
//				};
//			},
//			transport: function (params, success, failure) {
//				var $request = $.ajax(params);
//				$request.then(success);
//				$request.fail(failure);
//				return $request;
//			}
//		}
	}).on('select2:select',function(e){
	    var data=e.params.data;
	    if(data.id!=null){
        	document.redirecturl = data.id;
        	$('#button-select').prop('disabled',false);
        }else{
            $('#button-select').prop('disabled',true);
        }
        console.log(document.redirecturl);
    }
);

$('#button-select').click(function(){
    if(document.redirecturl.indexOf('https://dgs-backend.herokuapp.com') !== -1){
    	window.open(document.redirecturl, '_blank');
    }else{
    	window.location.href = document.redirecturl;
    } 
});
 	