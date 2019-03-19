$(function(){
	 function getRequest() { //编获取跳转数据
          var url = location.search;
          console.log(decodeURI(url))
              
          if (url.indexOf("?") != -1) {
            var str = url.substr(1);
              console.log(str)
            
             strs = str.split(',');
              console.log(strs)
              var merberIdArr=strs[0].split('=')
              var companyCode=strs[1].split('=')
                 locationInfo={
                 	  memberId:merberIdArr[1],
                 	  companyCode:companyCode[1]
                     }
                            }
}
	    
	getRequest()
	   
	function resubmit(company){
		
        $.ajax({
			type:"get",
			dataType:'jsonp',
			url:"http://192.168.1.97:3000/acae-sys/examine/submission",
			async:true,
            data:JSON.stringify({ 
					    "data":{ 
					             "memberId": locationInfo.memberId, 
					             "companyCode":locationInfo.companyCode
					           }}
								),
			success:function(d){
                  console.log(d)
				if(d.code==0){
					layer.open({
						type:0,
						content:company+'的会员申请已提交至ACAE审核，请耐心等待审核结果。',
						yes:function(){
							location.href='membershipApplicationRecordTable.html'
						}
					})

				}
				
			},
			error:function(e){
				console.log(e)
			}
		});
	
	}
	   $.ajax({
			type:"get",
			url:"http://192.168.1.97:3000/acae-sys/examine/memberDetails",
			async:true,
			dataType:'jsonp',
			data:JSON.stringify({ 
					    "data": { 
					         "memberId": locationInfo.memberId, 
					           "companyCode":locationInfo.companyCode
					    }}
								),
			success:function(d){
                  console.log(d)
                  if(d.data.Status==0){
                  	$('#status').text('未通过')
                  	$('#dismissal').text(d.data.dismissal+','+d.data.dismissDetails)
                  	$('#closeBtn').hide()
                  	 $('#submitBtn').click(function(){
                  		resubmit(d.data.memberName)
                  	})
                  }else if(d.data.Status==1){
                  	$('#status').text('已通过-待审核')
                  	$('.dismissalContent').css('display','none')
                  	$('#submitBtn').hide()
                  	$('#closeBtn').click(function(){
                  		location.href='membershipApplicationRecordTable.html'
                  	})
                  	
                  }
      			},
			error:function(e){
				console.log(e)
			}
		});
})
