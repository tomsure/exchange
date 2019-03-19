$(function(){
	$('#rejectBtn').click(function() {
		$('#layerApplicateReject').removeClass('hide')
		layer.open({
			type: 1,
			title: '驳回详情',
			area: ['600px', '400px'],
			content: $('#layerApplicateReject'),
		})
		$('.cancelBtn').click(function() {
			location.href='./applicate_details.html'

		});

	})
	$('#passBtn').click(function(event) {

		$('#layerApplicateOk').removeClass('hide')
		$('#layerCompanyText').text($('#auditedCompany').text())
		layer.open({
			title: '审核通过',//通过审核页面
			type: 1,
			content: $('#layerApplicateOk'),
			area: ['500px', '400px'],
			offset: '100px'//偏移值

		})

		$('.cancelBtn').click(function() {
			location.href='./applicate_details.html'
		})
	})
	$('#confirmBtn').click(function() { //通过审核
		location.href="./pass_create_account.html"
	})
	$('#dismissalBtn').click(function() { //驳回
//		location.href="./after_reject.html"
		$.ajax({
			type: 'get',
			url: "../../mockJson/admissionApplicate.json",
			async: true,
			success: function(d) {

				if(d.status == 0) {

				} else if(d.status == 1) {
					$('#rejectSuc').removeClass('hide')
					layer.open({
						type: 1,
						offset:'100px',
						area: ['400px', '250px'],
						content: $('#rejectSuc'),
					})

				}

			}
		});
		location.href="./after_reject.html"
       
		$('#closeBtn').click(function() {
			location.href = './applicate_details.html'
		})
	})
})
