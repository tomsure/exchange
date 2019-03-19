$.ajax({
	type:"get",
	url:"../../mockJson/userManagement.json",//这里路径是参考主页面相对于json文件路径
	async:true,
	success:function(d){
		console.log('获取到的数据是：',d.data)
		var Data = d.data;
	    $('#createUserTable').bootstrapTable({
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
                    field: 'userName',
                    title: '姓名',
                    formatter:function(userName,row,index){
			            return '<div class="tableTrFirstTd" style="cursor:pointer;">'+ userName +'</div>'
				  	},
				  	events:{
				  		'click div':function(event, userName, row, index){
				  		    console.log('获取到的行数据是：',row);
				  		    $.ajax({
								type:"get",
								url:"../../mockJson/userManagement.json",//这里路径是参考主页面相对于json文件路径
								async:true,
								success:function(d){
									console.log('获取的数据应该是：',d.data[0]);
									loginName = d.data[0].loginName,
					    		    userName = d.data[0].userName,
					    		    email = d.data[0].email,
					    		    roleList = d.data[0].roleList[0].name,
					    			console.log('获取roleList的值是：',roleList),
									location.href='user_info.html' + '?inputUser=' + loginName + '&inputUserName=' + userName + '&inputEmail=' + email + '&(#selects option:selected)=' + roleList;
									localStorage.setItem("#inputUser",loginName);
									localStorage.setItem("#inputUserName",userName);
									localStorage.setItem("#inputEmail",email);
									localStorage.setItem("#selects option:selected",roleList);
								},
								error:function(err){
									console.log(err);
								}
							});
				  		    
				  		    if(row.userName == userName){
				  		    	
				  		    }
				  		 	
				  		}
				  	}
                },
                {
                    field: 'userCode',
                    title: '工号',
                    
                },
                {
                    field: 'loginName',
                    title: '用户名',
                    
                },
                {
                    field: 'roleList',
                    title: '角色',
                    formatter:function(value,row,index){
                    	return '<div class="tableTrSixTd">'+ value[0].name +'</div>'
                    },
                    events:{
                    	
                    }
                },
                {
                    field: 'handle',
                    title: '操作',
                    formatter:function(handle,row,index){
			            return '<a class="tableTrSecondTd" id="click1" href="#">编辑</a>'+'<a class="tableTrThirdTd" id="click2" href="#">重置密码 </a>'
				  	},
				  	events:{
				  		'click #click1':function(event, handle, row, index){
				  		    console.log('获取的行数据是：',row)
				  		    $.ajax({
								type:"get",
								url:"../../mockJson/userManagement.json",//这里路径是参考主页面相对于json文件路径
								async:true,
								success:function(d){
									console.log('获取的数据应该是：',d.data[0]);
									loginName = d.data[0].loginName,
					    		    userName = d.data[0].userName,
					    		    email = d.data[0].email,
					    		    roleList = d.data[0].roleList[0].name,
					    			console.log('获取roleList的值是：',roleList),
									location.href='change_user.html' + '?inputUser=' + loginName + '&inputUserName=' + userName + '&inputEmail=' + email + '&(#selects option:selected)=' + roleList;
									localStorage.setItem("#inputUser",loginName);
									localStorage.setItem("#inputUserName",userName);
									localStorage.setItem("#inputEmail",email);
									localStorage.setItem("#selects option:selected",roleList);
								},
								error:function(err){
									console.log(err);
								}
							});
				  		    
				  		    if(row.handle == handle){
				  		    	
				    			
				  		    }
				  		 	
				  		},
				  		'click #click2':function(event, handle, row, index){
				  		    //console.log(222222)
				  		    console.log('获取的行数据是：',row)
				  		 	if(row.handle == handle){
				  		    	alert('重置密码的网页已发送至XXXXXXXXX邮箱');
				  		    }
				  		}
				  	}
                },



            ],
            data: d.data//data需取到[[]]类似组成的数组
	    })
	},
	error:function(err){
		console.log(err);
	}
});
