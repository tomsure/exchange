function getNewsTableData() {
	$.ajax({
		url: 'http://192.168.1.97:3000/acae-sys/examine/membership',
		type: 'get',
		async: true,
		dataType: 'jsonp',
		success: function(d) {
			$('#membershipApplicationRecordTable').bootstrapTable({
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
				columns: [{
						field: "memberName",
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
						field: 'companyCode',
						title: '代码'
					},
					{
						field: 'applicationTime',
						title: '提交时间'
					},
					{
						field: 'Status',
						title: '审核状态',
						formatter: function(value, row, index) {
							if(value == '0') {
								return '未通过'
							} else if(value == '1') {
								return '已通过-待审核'
							}
						},
					}

				],
				data: d.data.memberAll
			})

		},
		error: function(e) {
			console.log(e.status)
		}
	})
}

getNewsTableData()