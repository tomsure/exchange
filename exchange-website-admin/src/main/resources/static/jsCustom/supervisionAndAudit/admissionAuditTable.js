function getNewsTableData() {
	$.ajax({
		url: 'http://192.168.1.97:3000/acae-sys/examine/listAdmission',
		type: 'get',
		async: true,
		dataType: 'jsonp',
		success: function(d) {
			$('#admissionAuditTable').bootstrapTable({
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
						title: '公司全称',
						formatter: function(value, row, index) {
							return '<div class="tableTitleStyle">' + value + '</div>'
						},
						events: {
							'click div': function(ev, value, row, index) {
								location.href = 'auditDetails.html?memberId='+row.memberId
							}
						}
					},
					{
						field: 'applicationTime',
						title: '申请时间',
					},
					{
						field: "auditStatus",
						title: '审核状态',
						formatter:function(value, row, index){
							
							if(value==0){
								return '待审核'
							}else if(value==1){
								return '已通过'
							}else if(value==2){
								return '审核中'
							}else if(value==3){
								return '被驳回'
							}
						}
					},
					{
						field: 'sponsorName',
						title: '保荐商经纪人'
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