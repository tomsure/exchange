//$(function(){

function getNoticeTable() {
	$.ajax({
		type: "get",
		url: "http://192.168.1.97:3000/mockapi2",
		async: true,
		dataType: 'jsonp',
		success: function(d) {
			$('#noticeTable').bootstrapTable({
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
						field: "title",
						title: '标题',
						formatter: function(value, row, index) {
							return '<div class="tableTitleStyle">' + value + '</div>'
						},
						events: {
							'click div': function(ev, value, row, index) {
								if(row.status == '0') {
									location.href = 'news/add_news.html'
								} else if(row.status == '1') {
									location.href = 'news/edit_news.html'
								} else if(row.status == '-1') {
									location.href = 'news/withdraw_news.html'
								}

							}
						}
					},
					{
						field: 'status',
						title: '发布状态',
						formatter: function(value, row, index) {
							if(row.status == 0) {
								return '待发布'
							} else if(row.status == 1) {
								return '已发布'
							} else if(row.status == -1) {
								return '已撤回'
							}
						},

					},

					{
						field: 'time',
						title: '发布时间'
					}
				],
				data: d.dataList

			})
		}
	});
}

if($('#newsTable').length > 0) {
	getNewsTableData()
} else if($('#noticeTable').length > 0) {
	getNoticeTable()
}

function carousel() {
	$.ajax({ //轮播
		type: "get",
		url: "http://192.168.1.97:3000/mockapi4",
		async: true,
		dataType: 'jsonp',
		success: function(d) {

			$.each(d.imgData, function(i, ele) {
				if(ele.status == 0) {
					ele.status = '未发布'

				} else if(ele.status == 1) {
					ele.status = '已发布'
				}

				$('#imgDataContent').prepend('<div class="imgParentBox col-lg-4 col-md-4 col-sm-4 ">' +
					'<div class="editBar carouselEdit hide">' +
					'<div class="circle lookBtn"><span class="icon icon-look2"></span></div>' +
					'	<div class="circle editBtn"><span class="icon icon-edit"></span></div>' +
					'</div>' +
					'<div class="imgChildBox col-lg-12 col-md-12 col-sm-12">' +
					'<img class="img-responsive" src="' + ele.url + '"/>' +
					'</div>' +
					'<div class="styleColor carouselColor  hide "></div>' +
					'<div class=" center-block col-lg-12 col-md-12 col-sm-12">' +
					'<div class="newsTitle">' +
					' <p>' + ele.title + '</p>' +
					'<div class="publicStatus">' +
					'<span class="icon icon-publish"></span><span class="statusText">' + ele.status + '</span>' +
					'</div>' +
					'</div>' +
					'</div>' +
					'</div>')

			});

			imgStyle()
			var box = $('.imgParentBox').find('.statusText')
			$.each(box, function(i, el) {
				if($(el).text() == '未发布') {
					$(el).parent().find('.icon-publish').addClass('red')
					$(el).parents('.imgParentBox').find('.lookBtn').remove()
				}else if($(el).text()=='已发布'){
					$(el).parents('.imgParentBox').find('.editBtn').remove()
				}
			});

		}

	});

}

function enterprise() {
	$.ajax({
		type: 'get',
		url: 'http://192.168.1.97:3000/enterprise',
		async: true,
		dataType: 'jsonp',
		success: function(d) {
			$.each(d.imgData, function(i, ele) {

				if(ele.status == 0) {
					ele.status = '未发布'
				} else if(ele.status == 1) {
					ele.status = '已发布'
				}

				$('#enterpriseImgDataContent').prepend('<div class="imgContent col-lg-3 col-md-4 col-sm-6 ">' +
					'<div class="imgBox">' +
					'<div class="editBar hide">' +
					'<div class="circle"><span class="icon icon-look2"></span></div>' +
					'<div class="circle"><span class="icon icon-edit"></span></div>' +
					'</div>' +
					'<div class="imgBox1">' +
					'<img src="' + ele.url + '"/>' +
					'</div>' +
					'<div class="imgNewsTitle">' +
					'<span>' + ele.title + '</span>' +
					'</div>' +
					'<div class="styleColor hide "></div>' +
					'</div>' +
					'<div class="imgNewsStatus">' +
					'<span class="icon icon-publish"></span><span class="statusText">' + ele.status + '</span>' +
					'</div>' +
					'</div>')

			});
			imgStyle()
			var box = $('.imgNewsStatus').find('.statusText')
			$.each(box, function(i, el) {
				if($(el).text() == '未发布') {
					$(el).parent().find('.icon-publish').addClass('red')
				}
			});
		}

	})
}
if($('#imgDataContent').length > 0) {
	carousel()

}
if($('#enterpriseImgDataContent').length > 0) {
	enterprise()
}

//})