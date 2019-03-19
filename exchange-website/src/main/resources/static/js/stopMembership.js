$.ajax({
	type:"get",
	url:"../mockJson/stopVip.json",
	async:true,
	success:function(d){
		console.log(d)
	  $('#stopMembershipTable').bootstrapTable({
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
                        field: 'companyNum',
                        title: '公司代码',
                    },

                    {
                    	field:"shortName",
                    	title:'公司简称'
                    },
                     {
                    	field:"joinDate",
                    	title:'入会日期'
                    },
                     {
                    	field:"stopType",
                    	title:'停止类型'


                     },
                     {
                    	field:"stopDate",
                    	title:'停止日期'
                     }


                ],
                data: d
	  })
	},
	error:function(err){
		console.log(err)
	}
});
