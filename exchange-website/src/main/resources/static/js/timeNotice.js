
$.ajax({
	type:"get",
	url:"../mockJson/timeNotice.json",
	async:true,
	success:function(d){
		console.log(d)
	  $('#timeNoticeTable').bootstrapTable({
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
                    	field:"title",
                    	title:'标题'
                    },
                     {
                    	field:"company",
                    	title:'公司'
                    },
                      {
                        field: 'companyNum',
                        title: '代码',
                    },

                     {
                    	field:"time",
                    	title:'时间'
                     }


                ],
                data: d
	  })
	},
	error:function(err){
		console.log(err)
	}
});

