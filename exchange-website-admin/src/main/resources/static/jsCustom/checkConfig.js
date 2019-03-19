//var checkConfigBox = '<div class="container containers">' +
//			'<div class="checkConfig">' +
//				'<h3 class="checkTitle">监管审核</h3>' +
//				'<div class="panel fl">' +
//				    '<div class="panel-heading">' +
//				        '<h3 class="panel-title">股票发行</h3>' +
//				    '</div>' +
//				    '<div class="panel-body">' +
//				    	'<i class="glyphicon glyphicon-circle-arrow-right"></i>' +
//				                     需审核
//				    '</div>' +
//			    '</div>' +
//			    '<div class="panel fl">' +
//				    '<div class="panel-heading">' +
//				        '<h3 class="panel-title">债券发行</h3>' +
//				    '</div>' +
//				    '<div class="panel-body">' +
//				    	'<i class="glyphicon glyphicon-circle-arrow-right"></i>' +
//				                     无需审核
//				    '</div>' +
//			    '</div>' +
//			    '<div class="panel fl">' +
//				    '<div class="panel-heading">' +
//				        '<h3 class="panel-title">停牌审核</h3>' +
//				    '</div>' +
//				    '<div class="panel-body">' +
//				    	'<i class="glyphicon glyphicon-circle-arrow-right"></i>' +
//				                    无需审核
//				    '</div>' +
//			    '</div>' +
//			    '<div class="panel fl">' +
//				    '<div class="panel-heading">' +
//				        '<h3 class="panel-title">停牌审核</h3>' +
//				    '</div>' +
//				    '<div class="panel-body">' +
//				    	'<i class="glyphicon glyphicon-circle-arrow-right"></i>' +
//				                    无需审核
//				    '</div>' +
//			    '</div>' +
//			    '<div class="panel fl">' +
//				    '<div class="panel-heading">' +
//				        '<h3 class="panel-title">停牌审核</h3>' +
//				    '</div>' +
//				    '<div class="panel-body">' +
//				    	'<i class="glyphicon glyphicon-circle-arrow-right"></i>' +
//				                    无需审核
//				    '</div>' +
//			    '</div>' +
//			'</div>' +
//			'<div class="checkBottom"></div>' +
//			'<div class="checkConfig">' +
//				'<h3 class="checkTitle">会员管理</h3>' +
//				'<div class="panel fl">' +
//				    '<div class="panel-heading">' +
//				        '<h3 class="panel-title">股票发行</h3>' +
//				    '</div>' +
//				    '<div class="panel-body">' +
//				    	'<i class="glyphicon glyphicon-circle-arrow-right"></i>' +
//				                     需审核
//				    '</div>' +
//			    '</div>' +
//			    '<div class="panel fl">' +
//				    '<div class="panel-heading">' +
//				        '<h3 class="panel-title">债券发行</h3>' +
//				    '</div>' +
//				    '<div class="panel-body">' +
//				    	'<i class="glyphicon glyphicon-circle-arrow-right"></i>' +
//				                     无需审核
//				    '</div>' +
//			    '</div>' +
//			'</div>' +
//			'<div class="checkBottom check2"></div>' +
//			'<div class="checkConfig">' +
//				'<h3 class="checkTitle">保荐机构管理</h3>' +
//				'<div class="panel fl">' +
//				    '<div class="panel-heading">' +
//				        '<h3 class="panel-title">股票发行</h3>' +
//				    '</div>' +
//				    '<div class="panel-body">' +
//				    	'<i class="glyphicon glyphicon-circle-arrow-right"></i>' +
//				                     需审核
//				    '</div>' +
//			    '</div>' +
//			    '<div class="panel fl">' +
//				    '<div class="panel-heading">' +
//				        '<h3 class="panel-title">债券发行</h3>' +
//				    '</div>' +
//				    '<div class="panel-body">' +
//				    	'<i class="glyphicon glyphicon-circle-arrow-right"></i>' +
//				                     无需审核
//				    '</div>' +
//			    '</div>' +
//			'</div>' +
//		'</div>'
$(function() {
	$.ajax({
		url: '../../mockJson/checkConfig1.json',
		type: 'get',
		async: true,
		success: function(d) {

			$.each(d.data.moduleClassification, function(i, el) {
				$('#divBox').append('<div class="checkConfig clearfix" style="width:1230px;" data-typeId=' + el.typeId + '><h3 class="checkTitle">' + el.typeName + '</h3></div>')
			});
			$('#divBox .checkConfig').each(function(i, el) {
				$.each(d.data.moduleAll, function(i, ele) {
					if(ele.state == 1) {
						ele.state = '需审核'
					} else if(ele.state == 0) {
						ele.state = '无需审核'
					}
					if($(el).attr('data-typeid') == ele.typeId) {//对应父类id
						$(el).append('<div class="panel fl" style="margin-top:30px;">' + '<div class="panel-heading">' +
							'<h3 class="panel-title">' + ele.moduleName + '</h3>' +
							'</div>' +
							'<div class="panel-body">' +
							'<i class="glyphicon glyphicon-circle-arrow-right"></i>' +
							'<span>' + ele.state + '</span>'+
							'</div>' +
							'</div>')
					}

				});
			})
			$('.panel').click('clickPanel', function() {
				location.href = "./process_config.html"
			})
			
				
			$('#divBox .checkConfig .panel-body span').each(function(i,ele){
//				if($(ele).text()=="需审核"){
//				  	$(ele).css('color','red');
//				}else if($(ele).text()=="需审核"){
//				  	$(ele).css('color','blue');
//				  	
//				}
				console.log(ele);
				if(ele.innerText == "需审核"){
				    $(ele).css('color','#2D7DFA');
				}else if(ele.innerText == "无需审核"){
					$(ele).css('color',' #7F94B1');
				}
			});
        
		}
	})
})