var redirecturl='';
//$(document).ready(function(){
//	var opt = "<option></option>";
//	$.ajax({
//		type: "GET",
//		url: "/findOptions",
//		ContentType: "application/json",
//		dataType: "json",
//		success: function(response){
//			var select = $('#singleselect');
//			$.each(response,function(key,pippo){
//				//Prende l'oggetto 
//			var pippo2 =	$.parseJSON(pippo);
//				console.log("VALORE : " + pippo2);
//				opt +="<option value="+pippo2.value+">"+pippo2.option+"</option>";
//			});
//			select.html(opt);
//			select.show();
//			opt = "";
//		},
//		error: function(){
//			console.log("Errore nella richiesta");
//		}
//	});
//});

$('.js-data-example-ajax').select2(
	{
		theme:'classic',
		placeholder:'Seleziona un valore dalla lista...',
		allowClear:true,
		ajax: {
			type: "GET",
			url: "/findOptions",
			ContentType: "application/json",
			dataType: "json",
			data: function (params) {
				var query = {
//					search: params.term,
//					type: 'public'
				}
				console.log(query);
				return query;
			},
			processResults: function (data, param) {
				console.log(data);
				console.log(param);
				return {
					results: data.items
				};
			},
			transport: function (params, success, failure) {
				var $request = $.ajax(params);
				$request.then(success);
				$request.fail(failure);
				return $request;
			}
		}
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
 	