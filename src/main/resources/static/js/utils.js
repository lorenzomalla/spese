var servizio='';



function select2init() {
	$.ajax({
		type: "GET",
		url: "/findOptions",
		ContentType: "application/json",
		dataType: "json",
		success: function(response){
			var select = $('#singleselect');
			$.each(response,function(key,value){
				//Prende l'oggetto 
				$(select).append("<option value='"+value.id+"'>"+value.oOption+"</option>");				
			});
			$('.js-example-basic-single').select2({
					width: '100%',	
					theme:'classic',
//					allowClear:true
			}).on('select2:select',function(e){
			    var data=e.params.data;
//			    if(data.id!=null){
//		        	document.servizio = data.id;
//		        	$('#button-select').prop('disabled',false);
//		        }else{
//		            $('#button-select').prop('disabled',true);
//		        }
			    var result = setContatti(data.id);
		    });
		},
		error: function(){
			console.log("Errore nella richiesta");
		}
	}); 
//	$('.js-data-example-ajax').select2(
//			{
//			width: '100%',	
//			ajax: {
//				delay: 500,
//				url: '/findOptions',
//				type: "GET",
//				dataType: "json",
//				data: function (params) {
//					var query = {
//						search: params.term,
//						type: 'public'
//					}
//					console.log(query);
//					return query;
//				},
//				processResults: function (data) {
//					console.log(data);
//					var opt = '{"results":['; 
//					$.each(data,function(key,value){ 
//						console.log("VALORE : " + value);
//						opt +='{"id":"'+value.value+'","text":"'+value.oOption+'","phone":"'+value.phone+'","mail":"'+value.email+'"},';
//					});
//					opt = opt.substring(0, opt.length-1);
//					opt +=']}'; 
//					console.log(opt);
//					return opt;
//				}
//			},
//			theme:'classic',
//			allowClear:true,
//			minimumInputLength: 0
//		}).on('select2:select',function(e){
//		    var data=e.params.data;
//		    if(data.id!=null){
//	        	document.redirecturl = data.id;
//	        	$('#button-select').prop('disabled',false);
//	        }else{
//	            $('#button-select').prop('disabled',true);
//	        }
//	        console.log(document.redirecturl);
//	    }
//	);
	
	$('#button-select').click(function(){
//	    if(document.redirecturl.indexOf('https://dgs-backend.herokuapp.com') !== -1){
//	    	window.open(document.redirecturl, '_blank');
//	    }else{
//	    	window.location.href = document.redirecturl;
//	    }
		window.location.href = "https://dgs-backend.herokuapp.com/?path=9&servizio="+document.servizio;
	});
}	 	