$.ajax({
	type:"get",
	url:"../../mockJson/saveSuccess.json",//这里路径是参考主页面相对于json文件路径
	async:true,
	success:function(d){
		console.log(d)
	    $('#saveSuccessTable').bootstrapTable({
            rowStyle: function (row, index) {
                var style = {};
                style = {
                    css: {
                        'text-align': 'center',
                    }
                };
                return style;
            },
            pagination:false,
            pageSize:10,
            columns: [

                {
                    field: 'order',
                    title: '顺序',
                },
                {
                    field: 'verifier',
                    title: '审核人',
                    
                },
                {
                    field: 'throughRule',
                    title: '通过规则',
                },

            ],
            data: d
           
	    })
	    
	},
	error:function(err){
		console.log(err);
	}
});
