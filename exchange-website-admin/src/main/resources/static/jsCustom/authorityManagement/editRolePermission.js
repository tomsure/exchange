$(function() {
	function getRequest() { //编获取跳转数据
		var url = location.search;
		if(url.indexOf("?") != -1) {
			var str = url.substr(1);
			roleName = (decodeURI(str))

		}
	}
	getRequest()

	function editSuccess() {
		$.ajax({
			url: 'http://192.168.1.97:3000/acae-sys/sysRole/updRole',
			type: 'get',
			async: true,
			dataType: 'jsonp',
			success: function(d) {
            $('#layerRoleName').text($('#roleName').val())
            $('#createRoleSuccTable').bootstrapTable({
			checkboxHeader: true,
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
					field: "name",
					title: '权限名称',
				},
				{
					field: 'description',
					title: '业务描述',
				}
			],
			data: d.data.menuList
		})

			},
		    error:function(e){
		    	console.log(e.status)
		    }
		})
	}
//
	function editSuccessTable() {
		$('#createRoleSuccTable').bootstrapTable({
			checkboxHeader: true,
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
					field: "name",
					title: '权限名称',
				},
				{
					field: 'description',
					title: '业务描述',
				}
			],
			data: d.data.menuList
		})
	}

	function permissionsListTableData() {
		$.ajax({
			url: 'http://192.168.1.97:3000/acae-sys/sysRole/goUpdate',
			type: 'get',
			async: true,
			dataType: 'jsonp',
			success: function(d) {
				$('#roleName').val(roleName)
				$('#permissionTable').bootstrapTable({
					pagination: false,
					
					checkboxHeader: true,
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
					columns: [{
							field: "",
							title: '选择',
							checkbox: true,
							formatter: function(value, row, index) {
								console.log(row.rowstate)
								if(row.rowstate == true) {
									return {
	                  				checked:true //设置选中
									};
								} else if(row.rowstate ==false){
									return {
										checked:false //设置选中
									};
								}
								return value;
							}
						},
						{
							field: "name",
							title: '权限名称',
						},
						{
							field: 'description',
							title: '业务描述',
						}
					],
					data: d.data.menuList
				})
			}
		})
	}

	$('#saveBtn').click(function() {

		var getSelections = $('#permissionTable').bootstrapTable('getSelections')

		if(getSelections.length <= 0) {
			deleteRole()
		} else {

			$('#createRoleSuccBox').removeClass('hide')
			layer.open({
				title: '编辑角色权限成功',
				type: 1,
				content: $('#createRoleSuccBox'),
				area:['40%','500px'],
				success: function() {
					     editSuccess()                		

				},
				btn:['关闭'],
				yes: function() {
					location.href='roleAuthorityTable.html'
                      layer.closeAll()
				}
			})

		}

	})

//	}

	permissionsListTableData()

	function deleteRole() { //当没有选择角色权限的时候调用

		layer.confirm('没有赋予除角色xxx任何权限，继续操作将会删除该角色', {
			btn: ['确认', '取消'],
			btn1: function(index, layero) {
				layer.alert('已删除')
			},
			btn2: function() {
				layer.alert('已取消')
			},

		});

	}
	$('#cancelBtn').click(function() {
		location.href = 'roleAuthorityTable.html'
	})
})