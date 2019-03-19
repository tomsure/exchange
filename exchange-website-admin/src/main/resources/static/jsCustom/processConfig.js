$(function() {

	$.ajax({
		type: "get",
		url: "../../mockJson/processConfig.json", //这里路径是参考主页面相对于json文件路径
		async: true,
		success: function(d) {
			var moduleLinkArr = []
			var newUsers = []
			var moduleLink = d.data.moduleLink
			$('#processConfigTable').bootstrapTable({
				rowStyle: function(row, index) {
					var style = {};
					style = {
						css: {
							'text-align': 'center',
						}
					};
					return style;
				},
				pagination: false,
				pageSize: 10,
				columns: [

					{
						field: 'delete',
						title: '删除',
						formatter: function(name, row, index) {
							return '<i class="glyphicon glyphicon-minus-sign delete" style="color:red;font-size:18px;">删除</i>'
						}
					},
					{
						field: 'level',
						title: '顺序',
					},
					{
						field: 'users',
						title: '审核人',
						formatter: function(value, row, index) {
							var arr = []
							var arr1 = []
							$.each(value, function(i, el) {
								arr.push(el.userName)
								arr1.push(el.id)
							})

							return arr

						}

					},
					{
						field: 'operate',
						title: '操作',
						formatter: function(value, row, index) {

							var a = '<a class="tableTrFourTd"   href="#"  data-target="#myModal">编辑</a>'
							return a
						},
						events: {
							'click .tableTrFourTd': function(event, value, row, index) {

								var setIdArr = []
								var setArr = []
								layer.open({
									type: 1,
									content: $('#auditorLayer'),
									area: ['300px', '200px'],
									success: function() {
										$('#auditorLayer ul').html('')
										$.each(d.data.jurisdictionUser, function(i, el) {

											$('#auditorLayer ul').append('<li>' +
												'<input type="checkbox" value=' + el.id + ' name="" />' +
												'<span class="m-l-xs">' + el.userName + '</span>' +
												'</li>')
										})

									}

								})
								$('input[type="checkbox"]').change(function() {

									if($(this).prop("checked") == true) {
										setIdArr.push($(this).val())
										setArr.push($(this).next().text())
										console.log(setIdArr)
										console.log(setArr)
									}else if($(this).prop("checked") == false){
										setIdArr.pop($(this).val())
										setArr.pop($(this).next().text())
										console.log(setIdArr)
										console.log(setArr)
									}

								})

								$('#okBtn').click(function(e) {

									setArr = []
									setIdArr = []
									$("input[type='checkbox']:checked").each(function(i, el) {
										setIdArr.push($(el).val())
										setArr.push($(el).next().text())

									})

									console.log(setArr)

									// 									$(event.currentTarget).parent().prev().text(setArr)
									$(event.currentTarget).parent().prev().text(setArr)
									console.log(setArr)

									for(var i = 0; i < setIdArr.length; i++) { //获取要发送到后台的新的users
										var json = {};
										for(var j = 0; j < setArr.length; j++) {
											if(i == j) {
												json.value = setIdArr[j];
												json.name = setArr[i];
												newUsers.push(json);
											}
										}
									}

									$("input:checkbox").each(function(i) { //清空checkbox选中
										//										$(this).prop("checked", false);
									});
									//         							          layer.closeAll();

								})

							},
						}
					},
					{
						field: 'arriveNum',
						title: '通过规则',
						formatter: function(value, row, index) {

						}
					},
					{
						field: 'handle',
						title: '操作',
						formatter: function(value, row, index) {

							return '<a class="tableTrFiveTd" id="click4" href="#" data-toggle="modal" data-target="#myModal2">编辑</a>'
						},
						events: {
							'click #click4': function(event, value, row, index) {},
						}
					},

				],
				data: d.data.moduleLink,
				//				onClickCell:function(field, value, row, $element){
				//					 if($element.text()=='编辑'){
				//					 	  layer.open({
				//									type: 1,
				//									content: $('#auditorLayer'),
				//									area: ['300px', '200px'],
				//									success: function() {
				//										$('#auditorLayer ul').html('')
				//										$.each(d.data.jurisdictionUser, function(i, el) {
				//
				//											$('#auditorLayer ul').append('<li>' +
				//												'<input type="checkbox" value=' + el.id + ' name="" />' +
				//												'<span class="m-l-xs">' + el.userName + '</span>' +
				//												'</li>')
				//										})
				//
				//									}
				//
				//								})
				//					 	  $('#okBtn').click(function(e) {
				//									setArr = []
				//									setIdArr = []
				//									$("input[type='checkbox']:checked").each(function(i, el) {
				//										setIdArr.push($(el).val())
				//										setArr.push($(el).next().text())
				//									})
				//									console.log(setArr)
				//
				//									// 									$(event.currentTarget).parent().prev().text(setArr)
				//									$(event.currentTarget).parent().prev().text(setArr)
				//									console.log(setArr)
				//
				//									for(var i = 0; i < setIdArr.length; i++) { //获取要发送到后台的新的users
				//										var json = {};
				//										for(var j = 0; j < setArr.length; j++) {
				//											if(i == j) {
				//												json.value = setIdArr[j];
				//												json.name = setArr[i];
				//												newUsers.push(json);
				//											}
				//										}
				//									}
				//
				//                               
				//									$("input:checkbox").each(function(i) { //清空checkbox选中
				//										$(this).prop("checked", false);
				//										
				//									});
				//									//         							          layer.closeAll();
				//
				//								})
				//					 }
				//				}

			})

			$('#saveBtn').click(function() {
				console.log(newUsers)
				$.ajax({
					// url:''
					data: JSON.stringify({
						"data": {
							"createUserId": d.data.createUserId,
							"moduleId": d.data.moduleId,
							"moduleCode": d.data.moduleCode,
							"typeId": d.data.typeId,
							"moduleLink": [{
								"linkId": "4363",
								"arriveNum": 1,
								"level": 1,
								"users": newUsers
							}]
						}

					})
				})
			})
		},
		error: function(err) {
			//		console.log(err);
		}
	});

});