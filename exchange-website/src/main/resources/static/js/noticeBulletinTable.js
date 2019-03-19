$.ajax({
	type:"get",
	url:"../mockJson/noticeBulletinTable.json",
	async:true,
	success:function(d){

	  $('#noticeBulletinTable').bootstrapTable({
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
                        field: 'title',
                        title: '标题',
                    },
                    {
                        field: 'time',
                        title: '时间',
                    },



                ],
                data: d
	  })
	},
	error:function(err){
		console.log(err)
	}
});