$(function() {
	$('#loginForm').validate({
		rules: {
			uname: {
				required: true,
			},
			upwd: {
				required: true
			}
		},
		success: function() {

		},
		submitHandler: function() {

			$.ajax({
				// 	  url:'http://192.168.1.97:3000/acae-sys/admin/sysLogin',
				url: 'http://192.168.1.79:8080/acae-sys/admin/sysLogin',
				type: 'POST',
				async: true,
				dataType: 'json',
				beforeSend: function(XMLHttpRequest) {
					XMLHttpRequest.setRequestHeader("Content-Type", "application/json");
				},
				data: JSON.stringify({
					"data": {
						"loginName": $('#loginName').val(),
						"loginPassword": $('#loginPassword').val()
					}
				}),
				success: function(d) {
					if(d.code == 0) {
						$.cookie("userName",$("#loginName").val(),{path:'/'})
						$.cookie("userId",d.data.id,{path:'/'})
						location.href = 'home.html?type=' + d.data.type
					}
				},
				error: function(e) {
					console.log(e.status)
				}
			})
		}
	})
})