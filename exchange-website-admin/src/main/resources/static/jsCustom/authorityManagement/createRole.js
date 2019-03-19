function createRoleSucc() {
	$.ajax({
		url: 'http://192.168.1.97:3000/acae-sys/sysRole/addRole',
		type: 'get',
		async: true,
		dataType: 'jsonp',
		data:JSON.stringify({"data":
		{ "menuIdList": ["123","234","345"],"roleName": "管理员角色"}
                           }),
		success: function(d) {
			$('#createSuccessTable').bootstrapTable({
				checkboxHeader: false,
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
<<<<<<< HEAD
				columns: [{
						field: "",
						title: '选择',
						checkbox: true,

					},
					{
=======
				columns: [
				 					{
>>>>>>> branch 'Dev' of http://192.168.1.137:8888/root/ACAE.git
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
		error: function(e) {
			console.log(e.status)
		}
	})
}

function permissionsListTable() {
	$.ajax({
		url: 'http://192.168.1.97:3000/acae-sys/sysRole/goAdd',
		type: 'get',
		async: true,
		dataType: 'jsonp',
		success: function(d) {
			$('#createRoleTable').bootstrapTable({

				checkboxHeader: false,
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
						checkbox: true
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
			$('#saveBtn').click(function() {

				var getSelections = $('#createRoleTable').bootstrapTable('getSelections')

				if(getSelections.length <= 0) {
					deleteRole()
				} else {
					
					layer.open({
						type: 1,
						title: '创建成功',
						content: $('#tableModal'),
						area: ['800px', '400px'],
						btn: ['关闭'],
						yes: function() {
							
							location.href = 'roleAuthorityTable.html'
						}
					})
					$('#tableModal').removeClass('hide')
					createRoleSucc()
					
					$('#roleName').text($('#roleNameText').val())
				}

			})

		},
		error: function(e) {
			console.log(e.status)
		}
	})
}

permissionsListTable()

function deleteRole() { //当没有选择角色权限的时候调用

	layer.alert('请选择输入角色名称并选择角色权限！');

}

$('#cancelBtn').click(function() {
	location.href = 'roleAuthorityTable.html'
})