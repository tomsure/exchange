$.ajax({
<<<<<<< HEAD
	type:"get",
	url:"../../mockJson/processConfig.backup.json",//这里路径是参考主页面相对于json文件路径
=======
	type:'post',
	url:"http://192.168.1.118:8080/acae-sys/examine/initialData",//发送服务器请求地址
//  url: '../../mockJson/processConfig.json',
>>>>>>> branch 'Dev' of http://192.168.1.137:8888/root/ACAE.git
	async:true,
<<<<<<< HEAD
	success:function(d){
		console.log('获取到的数据是aaaaaaa：',d[0].throughRule);//至少n人同意
=======
	data: JSON.stringify({"moduleId":"1","moduleCode":"J-1001","typeId":"1"}),//请求的数据类型
//	dataType: 'jsonp',//服务器返回的数据类型
	success:function(d){//请求成功的函数
		console.log('获取到的数据是aaaaaaa：',d);
		var tableDatas = d.data.moduleLink[0].level;
		console.log('打印出来的顺序level8888888是：',tableDatas);
		var tableData = d.data.jurisdictionUser;
		console.log('获取到操作权限用户数据是：',tableData);
>>>>>>> branch 'Dev' of http://192.168.1.137:8888/root/ACAE.git
	    $('#processConfigTable').bootstrapTable({
	    	pagination:false,
            pageSize:10,
            sortable:true,
            sortorder:"async",
            rowStyle: function (row, index) {
            	console.log('获取的表格的行数据eeeeee是：',row);
                var style = {};
                style = {
                    css: {
                        'text-align': 'center',
                    }
                };
                return style;
            },
<<<<<<< HEAD
            pagination:false,
            pageSize:10,
            sortable:true,
=======
>>>>>>> branch 'Dev' of http://192.168.1.137:8888/root/ACAE.git
            columns: [

                {
                    field: 'delete',
                    title: '删除',
                    formatter:function(name,row,index){
			            return '<i class="glyphicon glyphicon-minus-sign delete" style="color:red;font-size:18px;"></i>'
				  	}
                },
                {
                    field: 'tableDatas',
                    title: '环节顺序',
                    formatter:function(name,row,index){
                    	console.log('打印出的行的数据aaa是：',row);
			            return '<span>'+ (tableDatas) +'</span>'
				  	}
                },
                {
                    field: 'jurisdictionUser',
                    title: '审核人',
                    formatter:function(handle,row,index){
<<<<<<< HEAD
			            return '<input type="checkbox" value="" name="" class="i-checks" /><span class="m-l-xs" style="margin-right:15px;">王小明</span>' + 
			                   '<input type="checkbox" value="" name="" class="i-checks" /><span class="m-l-xs" style="margin-right:15px;">白起</span>' + 
			                   '<input type="checkbox" value="" name="" class="i-checks" /><span class="m-l-xs" style="margin-right:15px;">司马懿</span>' + 
			                   '<input type="checkbox" value="" name="" class="i-checks" /><span class="m-l-xs" style="margin-right:15px;">小乔</span>' + 
			                   '<input type="checkbox" value="" name="" class="i-checks" /><span class="m-l-xs" style="margin-right:15px;">周瑜</span>'
=======
			            return '<input type="checkbox" class="i-checks"/><span class="m-l-xs" style="margin-right:15px;">'+ tableData[0].userName +'</span>' + 
			                   '<input type="checkbox" class="i-checks"/><span class="m-l-xs" style="margin-right:15px;">'+ tableData[1].userName +'</span>' + 
			                   '<input type="checkbox" class="i-checks"/><span class="m-l-xs" style="margin-right:15px;">'+ tableData[2].userName +'</span>' + 
			                   '<input type="checkbox" class="i-checks"/><span class="m-l-xs" style="margin-right:15px;">'+ tableData[3].userName +'</span>' 
>>>>>>> branch 'Dev' of http://192.168.1.137:8888/root/ACAE.git
				  	},
				  	events:{
				  		'click input':function(event, handle, row, index){
<<<<<<< HEAD
				  		    console.log('获取的行数据111111是：',row);
=======
				  		    console.log('获取的行数据7777777是：',row);
>>>>>>> branch 'Dev' of http://192.168.1.137:8888/root/ACAE.git
				        	
				  		}
				  	}
                    
                },
//              {
//                  field: 'operate',
//                  title: '操作',
//                  formatter:function(operate,row,index){
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
//              },
                {
                    field: 'throughRule',
                    title: '通过规则',
           
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
				  		    
<<<<<<< HEAD
				  		    $('.modalTwoBtn').click(function(){
				  		    	alert(7777777777);
				  		    	var spanVal = $('#myModal2 .iconValue').text();
				  		    	console.log('获取到icon的值是：',spanVal);
				  		    	
			                    var throughVal = row.throughRule;
			                    console.log('获取到指定行对应的操作规则一列数据是：',throughVal);
			                	row.throughRule='spanVal';
=======
			                $('.secondBtn').click(function(){
			                	location.href="./processSave.html"
			                })
				  		    $('.modalTwoBtn').click(function(){
				  		    	var spanVal = $('#myModal2 .iconValue').text();
				  		    	var spanVals = d.data.moduleLink[0].arriveNum;
				  		    	var spanValue = d.data.moduleLink[0].users;
				  		    	console.log('当前环节配置所有用户是eeeeee是:',d.data.moduleLink[0].users);
			                    var throughVal = row.throughRule;
			                    console.log('获取到指定行对应的操作规则一列数据是：',throughVal);
			                    var rows = {
						            index : index,  //更新列所在行的索引
						            field : "throughRule", //要更新列的field
						            value : "至少" + spanVals + "人同意" //要更新列的数据
						        }     
						        $('#processConfigTable').bootstrapTable("updateCell",rows);//更新表格数据   
						        $('.delete').click(function(row,index){
						            $(this).parent().parent().remove();
						            console.log('获取到的row是11111：',$(this).parent().parent().find("td").eq(2).prevObject[1].innerText);
						            if($(this).parent().parent().find("td").eq(2).prevObject[1].innerText == 1){
						            	location.href='./empty_check.html';
						            }
							    });
>>>>>>> branch 'Dev' of http://192.168.1.137:8888/root/ACAE.git
				  		    	$('#myModal2').modal('hide');
				  		    	
			                })
				  		    if(row.handle == handle){
				  		    	
				  		    }
				  		 	
				  		}
				  	}
                }

            ],
            data: d.data.moduleLink  //获取表格的数据一定是一个数组
	    }),
	    //删除表格一行数据
	    $('.delete').click(function(){
            $(this).parent().parent().remove();
	    }),
	    //添加表格一行数据
<<<<<<< HEAD
	    $('.createUser').click('clickBtn',function(){
	        var data = {delete: '', order: '',verifier:'', operate: '',throughRule:'',handle:''}; 
		    $('#processConfigTable').bootstrapTable('append',data); 
//	    	$('#processConfigTable tbody').append("<tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>")
	    })
	    
=======
	    $('.createUser').click(function(index,row){
	    	console.log('打印出的列的数据rowdddddd是：',row);
	        var datas = {"delete": "", "tableDatas": "tableDatas","jurisdictionUser":"jurisdictionUser","throughRule":"","handle":"handle"};
		    $('#processConfigTable').bootstrapTable('append',datas); 
//		    var rows = {
//	            index : index,  //更新列所在行的索引
//	            field : tableDatas, //要更新列的field
//	            value : tableDatas++ //要更新列的数据
//	        }//更新表格数据        
//	        $('#processConfigTable').bootstrapTable("updateCell",rows);
            $('.delete').click(function(row,index){
		    	console.log('获取到的row是bbbbbbbb：',row);
	            $(this).parent().parent().remove();
	            console.log('获取到的row是1：',$(this).parent().parent().find("td").eq(2).prevObject[1].innerText);
	            if($(this).parent().parent().find("td").eq(2).prevObject[1].innerText == 1){
	            	location.href='./empty_check.html';
	            }
		    })
	    }),
        $('.delete').click(function(row,index){
	    	console.log('获取到的row是bbbbbbbb：',row);
            $(this).parent().parent().remove();
            console.log('获取到的row是111：',$(this).parent().parent().find("td").eq(2).prevObject[1].innerText);
            if($(this).parent().parent().find("td").eq(2).prevObject[1].innerText == 1){
            	location.href='./empty_check.html';
            }
	    }),
	    //审核人员加减数量
        $('.glyphicon-plus-sign').click(function(){
        	var n1 = $(this).prev().text();
        	var num1 = parseInt(n1)+1;
        	var numVal = d.data.moduleLink[0].users.length;
        	if(num1 > numVal){
        		return;
        	}
        	$(this).prev().html((num1));
        }),
        $('.glyphicon-minus-sign').click(function(){
        	var n2 = $(this).next().html();
        	var num2 = parseInt(n2)-1;
        	if(num2 < 1){
        		return;
        	}
        	$(this).next().html((num2));
        })

>>>>>>> branch 'Dev' of http://192.168.1.137:8888/root/ACAE.git
	},
	error:function(err){
		console.log(err);
	}
});
