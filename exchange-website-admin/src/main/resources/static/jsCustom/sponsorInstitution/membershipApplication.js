$(function(){
	
	$('#submitBtn').click(function(){
			$.ajax({
			type:"get",
			dataType:'jsonp',
			daya:JSON.stringify( 
			    {"data": { 
			        " applyId ": "1", 
			        "sponsorId":"111",  
			
			        "text": "usd234"
			    }}
                  ),
			url:"http://192.168.1.97:3000/acae-sys/examine/submission",
			async:true,
			success:function(d){
                layer.open({
						type:0,
						content:'公司名称'+'的会员申请已提交至ACAE审核，请耐心等待审核结果。',
						yes:function(){
							location.href='membershipApplicationRecordTable.html'
						}
					})
			}
		});
	})
})
