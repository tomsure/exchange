

function getPositionTableData(){
		 $.ajax({
   	  url:'http://192.168.1.97:3000/position',
   	  type:'get',
   	  async: true,
   	  dataType:'jsonp',
   	  data:JSON.stringify({"data":{ "name": "管理员角色", "code": "role_001"}}),
   	  success:function(d){
   	  	                 $("#positionTable").bootstrapTable('destroy'); 
     	  				var tableData=$('#positionTable').bootstrapTable({
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
						field: "companyName",
						title: '公司',
						formatter: function(value, row, index) {
							return '<div class="tableTitleStyle">' + value + '</div>'
						},
						events: {
							'click div': function(ev, value, row, index) {
								location.href = 'applicationDetails.html?memberId=' + row.memberId + ',' + 'companyCode=' + row.companyCode
							}
						}
					},
					{
						field: "code",
						title: '代码',
					},
					{
						field: "price",
						title: '现价',
					},
					{
						field: "upDown",
						title: '涨跌',
					},
					{
						field: "open",
						title: '开盘',
					},
					{
						field: "high",
						title: '最高',
					},
					{
						field: "low",
						title: '最低',
					},
					{
						field: "lmr",
						title: '量比',
					},
					{
						field: "appointment",
						title: '委比',
					},
					{
						field: "volume",
						title: '成交量',
					},
					{
						field: "turnover",
						title: '成交额',
					},
					{
						field: "costPrice",
						title: '成本价',
					},
					{
						field: "number",
						title: '持仓数量',
					},
					{
						field: "total",
						title: '持仓总额',
					},
					{
						field: "floatProfitAndLoss",
						title: '浮动盈亏',
					},
					{
						field: "memberName",
						title: '',
						formatter:function(){

                          return [ '<div><button id="transaction" class="operationBtn">交易</button></div>'
                          ]
						},
						events:{
							'click #transaction':function(ev,value,row,index){
							layer.open({
								
								type:"1",
								content:$('#transactionModal'),
								area:['9.65rem','5rem'],
								success:function(){
									$('#transactionModal').removeClass('hide')
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

getPositionTableData()

function getSelfSelectionTable(){
	 $.ajax({
   	  url:'http://192.168.1.97:3000/selfSelection',
   	  type:'get',
   	  async: true,
   	  dataType:'jsonp',
   	  data:JSON.stringify({"data":{ "name": "管理员角色", "code": "role_001"}}),
   	  success:function(d){
   	  	                 $("#selfSelectionTable").bootstrapTable('destroy'); 
     	  				var tableData=$('#selfSelectionTable').bootstrapTable({
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
						field: "companyName",
						title: '公司',
						formatter: function(value, row, index) {
							return '<div class="tableTitleStyle">' + value + '</div>'
						
						
						}
					},
					{
						field: "code",
						title: '代码',
					},
					{
						field: "price",
						title: '现价',
					},
					{
						field: "upDown",
						title: '涨跌',
					},
					{
						field: "open",
						title: '开盘',
					},
					{
						field: "high",
						title: '最高',
					},
					{
						field: "low",
						title: '最低',
					},
					{
						field: "lmr",
						title: '量比',
					},
					{
						field: "appointment",
						title: '委比',
					},
					{
						field: "volume",
						title: '成交量',
					},
					{
						field: "turnover",
						title: '成交额',
					},
					
					
					{
						field: "operate",
						title: '',
						formatter:function(){

                          return [
                             '<button class="operationBtn">交易</button>',
                              
                             '<button class="operationBtn removeBtn">移除</button>'
                          ].join('')
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

getSelfSelectionTable()