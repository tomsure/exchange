$.ajax({
	type:"get",
	url:"../mockJson/changeName.json",
	async:true,
	success:function(d){
		console.log(d)
	  $('#changeNameTable').bootstrapTable({
                rowStyle: function (row, index) {
                    var style = {};
                    style = {
                        css: {
                            'text-align': 'center',
//                          "border":'0'
                        }
                    };
                    return style;
                },
                pagination:true,
                pageSize:10,
                columns: [

                    {
                        field: 'changeTime',
                        title: '变更时间',
                    },
                    {
                        field: 'companyNum',
                        title: '时间',
                    },
                    
                    {
                    	field:"changeType",
                    	title:"<select><option>变更类型</option><option>全部</option><option>简称</option><option>全称</option></select>"
                    	
                    },
                     {
                    	field:"beforeChangeName",
                    	title:'变更前全称'
                    },
                     {
                    	field:"afterChangeName",
                    	title:'变更后全称'
                    },


                ],
                data: d
	  })
	},
	error:function(err){
		console.log(err)
	}
});
