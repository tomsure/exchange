function getNewsTableData(){
		 $.ajax({
   	  url:'http://192.168.1.97:3000/acae-sys/sysRole/list',
   	  type:'get',
   	  async: true,
   	  dataType:'jsonp',
   	  data:JSON.stringify({"data":{ "name": "管理员角色", "code": "role_001"}}),
   	  success:function(d){
   	  	   console.log(d)
   	  	 $('#roleAuthorityTable').bootstrapTable({
   	  		 rowStyle: function(row, index) {
					var style = {};
					style = {
						css: {
							'text-align': 'center'
						}
					};
					return style;
				},
		 sortable: true,                     //是否启用排序
         sortOrder: "asc",    
		 columns:[
		  {
		  field:"name",
		  title:'角色名称',
		  },
		  {
		  	field:'description',
		  	title:'可操作的功能权限',
		  },
		  {
		  	field:'operation',
		  	title:'操作',
		  	formatter:function(value,row,index){
		  		return '<button >编辑</button>'
		  	},
		  	events:{
		  		"click button":function(ev, value, row, index){
		  			location.href='rolePermissionEditor.html?'+row.name
		  		}
                 
		  	}
		  }
	
		 ],
		 data:d.data
   	  	 })
   	  	
   	  	
   	  },
   	  error:function(e){
   	  	console.log(e.status)
   	  }
   })
	}

getNewsTableData()