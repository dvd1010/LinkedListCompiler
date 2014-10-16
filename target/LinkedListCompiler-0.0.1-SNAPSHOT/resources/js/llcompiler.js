$( document ).ready(function() {
;(function($) {
   $(function() {
       $('#linkListImpl').bind('click', function(e) {
           e.preventDefault();
           $('#linkedListCode').bPopup({
               	 zIndex: 20
               , opacity:1
           });
       });
   });
})(jQuery);

;(function($) {
	   $(function() {
	       $('#nodeImpl').bind('click', function(e) {
	           e.preventDefault();
	           $('#nodeCode').bPopup({
	               	 zIndex: 20
	               , opacity:1
	           });
	       });
	   });
	})(jQuery);

$('#linked').click(function(){
	
	var length = $('#ll-length').val();
	if(length == ""){
		alert("Enter a value between 1 and 10");
		return false;
	}
	$('#linked').attr('disabled','disabled');
	$('#parent-ll').append("<br/><div class='llvalues'>");
	for(var i=0; i<length; i++){
		$('#parent-ll').append("<input type='text' name='text_name' class='ll-data' style='width: 40px;'>");
		if(i!= length-1){
			$('#parent-ll').append("<img src='https://s3.amazonaws.com/llcompiler/arrow-1.jpg' width=3% height=12px padding>");
		}
	}	
	$('#parent-ll').append("</div>");
	$("#submit-code").css("display", "block");
	
});

$('#submit-code').click(function(){
	var code = $('#code').val();
	var length = $('#ll-length').val();
	var ll_values = [];
	$("input[class=ll-data]").each(function() {
	    var val = $(this).val();
	    if(val != ""){
	    	ll_values.push(val);	
	    }
	});
	if(length != ll_values.length){
		alert("Fill data in all Nodes");
		return false;
	}
	$('#submit-code').val('Wait...');
	
	$.ajax({
		headers: { 
            'Content-Type': 'application/x-www-form-urlencoded' 
        },
		url:"/linkedlistcompiler/submitCode",
		data : code+'\n\n'+ll_values.join(","),
		type : 'POST',
		success: function(response){
			alert(response.result);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert(errorThrown);
		}
	});
});
});
