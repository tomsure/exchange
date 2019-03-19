$.ajax({
	type:"get",
	url:"../../mockJson/processConfig.json",//这里路径是参考主页面相对于json文件路径
	async:true,
	success:function(d){
		 
	    $('#processConfigTable').bootstrapTable({
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
                    field: 'delete',
                    title: '删除',
                    formatter:function(name,row,index){
			            return '<i class="glyphicon glyphicon-minus-sign delete" style="color:red;font-size:18px;">'+ name +'</i>'
				  	}
                },
                {
                    field: 'order',
                    title: '顺序',
                },
                {
                    field: 'verifier',
                    title: '审核人',
                    formatter:function(handle,row,index){
			            return '<input type="checkbox" value="" name="" class="i-checks" /><span class="m-l-xs" style="margin-right:15px;">王小明</span>' + 
			                   '<input type="checkbox" value="" name="" class="i-checks" /><span class="m-l-xs" style="margin-right:15px;">白起</span>' + 
			                   '<input type="checkbox" value="" name="" class="i-checks" /><span class="m-l-xs" style="margin-right:15px;">司马懿</span>' + 
			                   '<input type="checkbox" value="" name="" class="i-checks" /><span class="m-l-xs" style="margin-right:15px;">小乔</span>' + 
			                   '<input type="checkbox" value="" name="" class="i-checks" /><span class="m-l-xs" style="margin-right:15px;">周瑜</span>'
				  	},
				  	events:{
				  		'click input':function(event, handle, row, index){
				  		    console.log('获取的行数据111111是：',row);
				        	
				  		},
				  	}
                    
                },
                {
                    field: 'operate',
                    title: '操作',
                    formatter:function(operate,row,index){
//			            return '<a class="tableTrFourTd" id="click3" href="#" data-toggle="modal" data-target="#myModal">编辑</a>'
//				  	},
//				  	events:{
//				  		'click #click3':function(event, operate, row, index){
//				  			//$('#myModal').modal('hide')
//				  		    //console.log(1111111)
//				  		    console.log('获取的行数据22222是：',row);
//				  		    //if($('.firstModalCont li input').attr("checked")){
//				        		var spanValue = $('.firstModalCont li span').text();
//					        	console.log('获取span的值是：',spanValue);
//					        	$("table tr").eq(1).find("td").eq(1).addClass('red').html('0');
//					        	$('#processConfigTable tbody tr td:nth-child(3)').text(spanValue)
//					        	var data = {delete: '', order: '',verifier:'', operate: '',throughRule:'',handle:''}; 
//					      	    $('#processConfigTable').bootstrapTable('updateCell',{index:index,field:data.verifier,value:spanValue});
//				        	
//				  		},
//				  	}
                },
                {
                    field: 'throughRule',
                    title: '通过规则',
//                  formatter:function(throughRule,row,index){
//			            return  '<i class="glyphicon glyphicon-minus-sign" style="font-size:20px;"></i>' + 
//								'<span class="iconValue" style="font-size:18px;margin:0px 8px">1</span>' + 
//								'<i class="glyphicon glyphicon-plus-sign" style="font-size:20px;"></i>'
//				  	},
//				  	events:{
//				  		'click i':function(event, throughRule, row, index){
//				  		    console.log('获取的行数据3333333是：',row);
//				        	//审核人员加减数量
//			                $('.glyphicon-plus-sign').click(function(){
//			                	var n1=$(this).prev().text();
//			                	var num1=parseInt(n1)+1;
//			                	if(num1 > 5){
//			                		return n1;
//			                	}
//			                	$(this).prev().html((num1));
//			                }),
//			                $('.glyphicon-minus-sign').click(function(){
//			                	var n2=$(this).next().text();
//			                	var num2=parseInt(n2)-1;
//			                	if(num2 < 1){
//			                		return n2;
//			                	}
//			                	$(this).next().html((num2));
//			                })
//				  		}
//                 }
				  		
                },
                {
                    field: 'handle',
                    title: '操作',
                    formatter:function(handle,row,index){
                    	console.log('获取的行数据44444444444是：',row)
			            return '<a class="tableTrFiveTd" id="click4" href="#" data-toggle="modal" data-target="#myModal2">编辑</a>'
				  	},
				  	events:{
				  		'click #click4':function(event, handle, row, index){//操作的事件都要放在events里面
				  		    console.log('获取到对应列操作数据5555555是：',row.throughRule);
				  		    
				  		    $('.modalTwoBtn').click(function(){
				  		    	alert(7777777777);
				  		    	var spanVal = $('#myModal2 .iconValue').text();
				  		    	console.log('获取到icon的值是：',spanVal);
				  		    	
			                    var throughVal = row.throughRule;
			                    console.log('获取到指定行对应的操作规则一列数据是：',throughVal);
			                	row.throughRule='spanVal';
				  		    	$('#myModal2').modal('hide');
				  		    	
			                })
				  		    if(row.handle == handle){
				  		    }
				  		 	
				  		}
				  	}
                },

            ],
            data: d,
           
	    }),
	    //添加表格一行数据
	    $('.createUser').click('clickBtn',function(){
	        var data = {delete: '', order: '',verifier:'', operate: '',throughRule:'',handle:''}; 
		    $('#processConfigTable').bootstrapTable('append',data); 
//	    	$('#processConfigTable tbody').append("<tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>")
	    }),
	    //删除表格一行数据
	    $('.delete').click(function(){
            $(this).parent().parent().remove();
	    }),
	    $('.modalOneBtn').click(function(index){
        	console.log(5555555555);
        	if($('.firstModalCont li input').attr("checked")){
        		var spanValue = $('.firstModalCont li span').text();
	        	console.log('获取span的值是：',spanValue);
	        	//$("table tr").eq(1).find("td").eq(1).addClass('red').html('0');
	        	$('#processConfigTable tbody tr td:nth-child(3)').text(spanValue)
	        	var data = {delete: '', order: '',verifier:'', operate: '',throughRule:'',handle:''}; 
	//      	$('#processConfigTable').bootstrapTable(‘updateCell’,{index:index,field:data.verifier,value:inputValue});
        	}
//      	location.href="process_config.html"
        })
	    
	},
	error:function(err){
		console.log(err);
	}
});
