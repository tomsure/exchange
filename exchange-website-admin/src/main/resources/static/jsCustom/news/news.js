//function getNewsTableData(){
		 $.ajax({
   	  url:'http://192.168.1.97:3000/mockapi1',
   	  type:'get',
   	  async: true,
   	  dataType:'jsonp',
   	  success:function(d){

   	  	 $('#newsTable').bootstrapTable({
   	  	 pagination:true,
		 pageSize:6,
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
		  field:"title",
		  title:'标题',
		  formatter:function(value,row,index){
	        return'<div class="tableTitleStyle">'+value +'</div>'
		  	},
		  	events:{
		  		'click div':function(ev, value, row, index){
		  		 if(row.status=='0'){
		  		 	 location.href='news/add_news.html'
		  		 }else if(row.status=='1'){
		  		 	location.href='news/edit_news.html'
		  		 }else if(row.status=='-1'){
		  		 	location.href='news/withdraw_news.html'
		  		 }
		  	       
		  		}
		  	}
		  },
		  {
		  	field:'status',
		  	title:'发布状态',
		  	formatter:function(value,row,index){
		  		if(row.status==0){
		  			return '待发布'
		  		}else if(row.status==1){
		  			return '已发布'
		  		}else if(row.status==-1){
		  			return '已撤回'
		  		}
		  	}
		  	
		  },
		  {
		  	field:'time',
		  	title:'发布时间'
		  }
	
		 ],
		 data:d.newsList
   	  	 })
   	  	
   	  	
   	  },
   	  error:function(e){
   	  	console.log(e.status)
   	  }
   })
		 
		  
//	}