$('#widthDrawBtn').click(function(){
	$.ajax({
		type:"get",
		url:"",
		async:true,
		success:function(){
			alert('已撤回')
			location.href='editCarousel.html'
		}
		
	});
	
	
})
