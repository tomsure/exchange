$('#logOut').click(function(){
	$.ajax({
		
		url:"http://192.168.1.79:8080/acae-sys/admin/sysLogout",
		type: 'POST',
				async: true,
				dataType: 'json',
				success: function(d) {
					if(d.data.code==0){
						console.log(d)
						location.href='login.html'
					}
				},
	});
	
})
