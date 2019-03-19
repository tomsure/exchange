$.ajax({
	type:"get",
	url:"../../mockJson/joinApplicate.json",//这里路径是参考主页面相对于json文件路径
	async:true,
	success:function(d){
		console.log(d)
	    $('#joinApplicateTable').bootstrapTable({
            rowStyle: function (row, index) {
                var style = {};
                style = {
                    css: {
                        'text-align': 'center',
                    }
                };
                return style;
            },
            pagination:true,
            pageSize:10,
            columns: [

                {
                    field: 'agent',
                    title: '保荐商/经纪人',
                    formatter:function(name,row,index){
			            return '<div class="tableTrSixTd" style="cursor:pointer;">'+ name +'</div>'
				  	},
				  	events:{
				  		'click div':function(event, agent, row, index){
				  		
				  		    if(row.agent == agent){
				  		    	location.href='./applicate_details.html'//参考路径都是当前html主页面的当前路径
				  		    }
				  		 	
				  		}
				  	}
                },
                {
                    field: 'businessType',
                    title: '业务类型',
                },
                {
                    field: 'applicateTime',
                    title: '申请时间',
                },

            ],
            data: d
	    })
	},
	error:function(err){
		console.log(err);
	}
});
