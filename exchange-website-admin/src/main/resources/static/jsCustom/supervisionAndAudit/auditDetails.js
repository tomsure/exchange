$(function() {
	function GetRequest() { //编获取跳转数据
		var url = location.search;
		if(url.indexOf("?") != -1) {
			var str = url.substr(1);
			strs = str.split('=');
			memberIdData = {
				memberId: decodeURI(strs[1])
			}
		}
	}
	GetRequest();
	$.ajax({
		url: 'http://192.168.1.97:3000/acae-sys/examine/detailsAdmission',
		type: 'get',
		dataType: 'jsonp',
		async: true,
		data: JSON.stringify({
			"data": {
				"memberId": memberIdData.memberId
			}
		}),
		success: function(d) {
			if(d.data.auditStatus == -1 || 0 || 1 || 2) {
				$('#rejectBtn').hide()
				$('#adoptBtn').hide()
				$('#closePageBtn').show()
				$('#closePageBtn').click(function() {
					location.href = 'admissionAuditTable.html'
				})
			}
			if(d.data.auditStatus == -1) {
				$('#auditStatus').text('未通过')
			} else if(d.data.auditStatus == 0) {
				$('#auditStatus').text('待审核')
				$('#rejectBtn').show()
				$('#adoptBtn').show()
				$('#closePageBtn').hide()
			} else if(d.data.auditStatus == 1) {
				$('#auditStatus').text('审核中')
			} else if(d.data.auditStatus == 2) {
				$('#auditStatus').text('已通过')
			}
			$('#memberName').text(d.data.memberName)
			$('#companyCode').text(d.data.companyCode)
			$('#industry').text(d.data.industry)
			$('#companyPhone').text(d.data.companyPhone)
			$('#linkEmail').text(d.data.linkEmail)

		}
	})
	$('#adoptBtn').click(function(e) { //点击通过"审核按钮"后的提示
		$('#layerAuditOk').removeClass('hide')
		$('#layerComplanyText').text($('#auditedCompany').text())
		layer.open({
			title: '通过审核',
			type: 1,
			content: $('#layerAuditOk'),
			offset: '100px'
		})
		$('#layerComplanyText').text($('#memberName').text())
		$('.cancelBtn').click(function() {
			layer.closeAll()
		})
	})
	$('#okBtn').click(function() { //通过审核
		$.ajax({
			type: "get",
			url: "http://192.168.1.97:3000/acae-sys/examine/passThrough",
			async: true,
			dataType: 'jsonp',
			data: JSON.stringify({
				"data": {
					"updateId": "1",
					"updateTime": "@time()",
					"applyId": "1"
				}
			}),
			success: function(d) {
				if(d.code == '0') {
					layer.confirm($('#memberName').text() + '已成为ACAE会员。', {
						btn: ['确认'],
						btn1: function(index, layero) {
							location.href = 'admissionAuditTable.html'
						},
					})
				}
			}
		});
	})
	$('#rejectBtn').click(function() {
		$('#layerAuditReject').removeClass('hide')
		layer.open({
			type: 1,
			title: '驳回详情',
			area: ['500px', '400px'],
			offset: '100px',
			content: $('#layerAuditReject'),
		})
		$('.cancelBtn').click(function() {
			layer.closeAll()
		});
	})
	$('#dismissalBtn').click(function() { //驳回
		$.ajax({
			type: 'get',
			url: "http://192.168.1.97:3000/acae-sys/examine/dismissal",
			async: true,
			dataType: 'jsonp',
			data: JSON.stringify({
				"data": {
					"updateId": "1",
					"applyId": "1",
					"dismissal": $('#dismissal').val(),
					"dismissDetails": $('#dismissDetails').text()
				}
			}),
			success: function(d) {
				$('#rejectSucc').removeClass('hide')
				layer.open({
					type: 1,
					offset: '100px',
					area: ['400px', '250px'],
					content: $('#rejectSucc'),
				})
				$('#rejectTipsText').text($('#memberName').text())
				$('#dismissalText').text($('#dismissal').val())
				$('#dismissDetailsText').text($('#dismissDetails').val())
			}
		});
		$('#closeBtn').click(function() {
			layer.closeAll()
			location.href = 'admissionAuditTable.html'
		})
	})

})