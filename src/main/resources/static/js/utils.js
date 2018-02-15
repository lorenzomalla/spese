var redirecturl='';
function select2init() {
	$('.js-data-example-ajax').select2(
			{
			width: '100%',	
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
			ajax: {
		        url: "/findOptions",
		        dataType: 'json',
		        delay: 250,
		        data: function (params) {
		            return {
		                q: params.term // search term
		            };
		        },
		        processResults: function (data) {
		            // parse the results into the format expected by Select2.
		            // since we are using custom formatting functions we do not need to
		            // alter the remote JSON data
		            return {
		                results: data
		            };
		        },
		        cache: true
		    },
		    minimumInputLength: 2
			theme:'classic',
			allowClear:true,
//			minimumInputLength: 0
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
}	 	