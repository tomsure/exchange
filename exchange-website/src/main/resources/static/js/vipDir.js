$.ajax({
	type:"get",
	url:"../mockJson/vipList.json",
	async:true,
	success:function(d){
		console.log(d)
	  $('#vipDirTable').bootstrapTable({
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
                        field: 'companyNum',
                        title: '公司代码',
                    },
                    
                    {
                    	field:"shortName",
                    	title:'公司简称'
                    },
                     {
                    	field:"fullName",
                    	title:'公司全称'
                    },
                     {
                    	field:"industry",
                    	title:'所属行业'
                    },
                    {
                    	field:"website",
                    	title:'网址'
                    },


                ],
                data: d
	  })
	},
	error:function(err){
		console.log(err)
	}
});
