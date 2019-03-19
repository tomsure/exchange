edit()

$('#eg').click(function(){ //点击编辑器开启编辑
	edit()
})
$('#saveBtn').click(function(){
	$.ajax({})
	
})
$('#publishBtn').click(function(){  //发布
	$.ajax({
		type:"get",
		url:"",
		async:true
	});
	location.href='../carousel.html'
})
