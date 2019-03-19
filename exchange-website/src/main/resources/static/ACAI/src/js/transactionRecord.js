

function pendingOrderTable(){  //委托中
		 $.ajax({
   	  url:'http://192.168.1.97:3000/pendingOrder',
   	  type:'get',
   	  async: true,
   	  dataType:'jsonp',
   	  data:JSON.stringify({"data":{ "name": "管理员角色", "code": "role_001"}}),
   	  success:function(d){
   	  	                 $("#pendingOrderTable").bootstrapTable('destroy'); 
     	  				var tableData=$('#pendingOrderTable').bootstrapTable({
				pagination: true,
				pageSize: 6,
				rowStyle: function(row, index) {
					var style = {};
					style = {
						css: {
							'text-align': 'center'
						     }
					         };
					return style;
				},
				sortable: true, //是否启用排序
				sortOrder: "asc",
				columns: [
				    {
						field: "entrustmentNumber",
						title: '委托编号',
					},
				     {
						field: "companyName",
						title: '公司',
					 },
					{
						field: "code",
						title: '代码',
					},
					{
						field: "type",
						title: '类型',
					},
					{
						field: "price",
						title: '价格',
					},
					{
						field: "number",
						title: '数量',
					},
					{
						field: "money",
						title: '金额',
					},
					{
						field: "status",
						title: '状态',
					},
					{
						field:"time",
						title:'委托时间',
					},
					{
						field:"operate",
						title:'',
						formatter:function(){
                          return [ '<div><button id="withdrawBtn" class="operationBtn">撤单</button></div>'
                          ]
						},
						events:{
							'click #withdrawBtn':function(ev,value,row,index){
							layer.open({
								type:1,
								title:'确认撤单',
								area:['300px','250px'],
								skin:'layer-common',
								content:$('#transactionModal'),
								btn:['确认撤单','取消'],
								success:function(){
									$('#transactionModal').removeClass('hide')
									$('#companyName').text(row.companyName)
									$('#type').text(row.type)
									$('#price').text(row.price)
									$('#number').text(row.number)
									$('#money').text(row.money)
									
									
								},
								yes:function(){
//									$.ajax({
//										type:"get",
//										url:"",
//										async:true
//									});
                                   layer.msg('您的撤单申请已提交成功')
                                   function closrLayer(){
                                   	  layer.closeAll()
                                   	  clearTimeout(closrLayer)
                                   }
                                   setTimeout(closrLayer,1000)
                                  
								}
							})
						}
						}
						
						
					}
					

				],
				data: d.data
			})
   	  	
   	  	               tableData.bootstrapTable('hideLoading')
   	  },
   	  error:function(e){
   	  	console.log(e.status)
   	  }
   })
	}

pendingOrderTable()

function completedOrderTable(){
	 $.ajax({
   	  url:'http://192.168.1.97:3000/completed',
   	  type:'get',
   	  async: true,
   	  dataType:'jsonp',
   	  data:JSON.stringify({"data":{ "name": "管理员角色", "code": "role_001"}}),
   	  success:function(d){
   	  	                 $("#completedOrderTable").bootstrapTable('destroy'); 
     	  				var tableData=$('#completedOrderTable').bootstrapTable({
				pagination: true,
				pageSize: 6,
				rowStyle: function(row, index) {
					var style = {};
					style = {
						css: {
							'text-align': 'center'
						     }
					         };
					return style;
				},
				sortable: true, //是否启用排序
				sortOrder: "asc",
				columns: [
				    {
						field: "entrustmentNumber",
						title: '委托编号',
					},
				     {
						field: "companyName",
						title: '公司',
					 },
					{
						field: "code",
						title: '代码',
					},
					{
						field: "type",
						title: '类型',
					},
					{
						field: "price",
						title: '价格',
					},
					{
						field: "number",
						title: '数量',
					},
					{
						field: "money",
						title: '金额',
					},
					{
						field: "status",
						title: '状态',
					},
					{
						field:"time",
						title:'委托时间',
					}
					

				],
				data: d.data
			})
   	  	
   	  	               tableData.bootstrapTable('hideLoading')
   	  },
   	  error:function(e){
   	  	console.log(e.status)
   	  }
   })
}

completedOrderTable()

function withdrawalOrderTable(){
	 $.ajax({
   	  url:'http://192.168.1.97:3000/withdrawalOrder',
   	  type:'get',
   	  async: true,
   	  dataType:'jsonp',
   	  data:JSON.stringify({"data":{ "name": "管理员角色", "code": "role_001"}}),
   	  success:function(d){
   	  	                 $("#withdrawalOrderTable").bootstrapTable('destroy'); 
     	  				var tableData=$('#withdrawalOrderTable').bootstrapTable({
				pagination: true,
				pageSize: 6,
				rowStyle: function(row, index) {
					var style = {};
					style = {
						css: {
							'text-align': 'center'
						     }
					         };
					return style;
				},
				sortable: true, //是否启用排序
				sortOrder: "asc",
				columns: [
				 {
						field: "entrustmentNumber",
						title: '委托编号',
					},
				     {
						field: "companyName",
						title: '公司',
					 },
					{
						field: "code",
						title: '代码',
					},
					{
						field: "type",
						title: '类型',
					},
					{
						field: "price",
						title: '价格',
					},
					{
						field: "number",
						title: '数量',
					},
					{
						field: "money",
						title: '金额',
					},
					{
						field: "status",
						title: '状态',
					},
					{
						field:"time",
						title:'委托时间',
					}
					

				],
				data: d.data
			})
   	  	
   	  	    tableData.bootstrapTable('hideLoading')
   	  },
   	  error:function(e){
   	  	console.log(e.status)
   	  }
   })
}

withdrawalOrderTable()