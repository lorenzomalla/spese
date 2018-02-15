var redirecturl='';

$('.js-data-example-ajax').select2(
		{theme:'classic',placeholder:'Seleziona un valore dalla lista...',
		allowClear:true,
		ajax: {
			url: 'https://dgs-backend.herokuapp.com/findOptions',
			dataType: 'json',
			data: function (params) {
				var query = {
					search: params.term,
					type: 'public'
				};
				console.log(query);
				return query;
			},
			processResults: function (data) {
				console.log(data);
				return {
					results: data.items
				};
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
 	