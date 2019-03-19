var stompClient = null;

var sessionId;

function connect() {
	var socket = new SockJS('/websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
		console.log('Connected: ' + frame);

		sessionId = socket._transport.url.split("/")[5];

		stompClient.subscribe('/gateway/kick', function (data) {
			var newData = jQuery.parseJSON(data.body); // 解析数据
			//			alert("你好，我是一个警告框！"+newData);
			console.log(newData.kickSessionId)
			console.log($.cookie('sessionId'))
			if (newData.kickSessionId == $.cookie('sessionId')) {
				alert('其他地方已登录！')
				tradePadelogout()
			}
			console.log("newData: " + newData)
		})



		//               如果有userid
		if ($.cookie('UserId')) {
			$('#loginLi').html('<span>' + $.cookie('email') + '</span>')
			$('#liLast').html('<span id="logout">Logout</span>')
			$('#userItem').removeClass('hide')
		}
		//
		//如果没有userID
		customValidate($('#lgPassWordForm'), {
			"lgEmail": {
				required: true,
				email: true
			},
			"lgPassword": {
				required: true
			}
		}, {}, function () {
			//
			stompClient.subscribe('/gateway/login-' + sessionId, function (data) {

				var kickSessionId = jQuery.parseJSON(data.body).kickSessionId
				console.log("11:" + kickSessionId)
				if (kickSessionId != '' && kickSessionId != undefined && kickSessionId != null) {
					stompClient.send("/ws/user/kick", {}, JSON.stringify({

						"UserID": parseInt($.cookie('UserId')),
						"kickSessionId": kickSessionId
					}))
				}

				// TODO store userId
				$.cookie('email', $('#lgEmail').val(), {
					path: '/'
				})
				$.cookie('sessionId', sessionId, {
					path: '/'
				})
				$.cookie('UserId', jQuery.parseJSON(data.body).UserID, {
					path: '/'
				})
				var status = jQuery.parseJSON(data.body).Status
				if (status == 0) {
					$('#myModal').modal('hide')
					$('#loginLi').html('<span>' + $.cookie('email') + '</span>')
					$('#liLast').html('<span id="logout">Logout</span>')

					resetStyle()
					$('#userItem').removeClass('hide')
				} else if (status == -1) {
					alert('Login Error!')
				}

			})
			//
			sendLoginData() //login	
		})
		$('#liLast').click(function () { //logout

			sendLogoutData() //send logout Data
			$.removeCookie('UserId', {
				path: '/'
			})
			$.removeCookie('email', {
				path: '/'
			})

			$('#loginLi').html('<a id="loginBtn" data-toggle="modal" data-target="#myModal"> Login </a>')
			$('.ulList li:last').html('<a href="Register.html">  Register  </a>')
			$('#userItem').addClass('hide')
			$('#c2cUserOpenOrderTable').find('tbody').html('')
			$('#c2cUserPendingOrderTable').find('tbody').html('')
			$('#c2cUserHistoryOrderTable').find('tbody').html('')

			$('#userItem').removeClass('hide')
			console.log($.cookie('UserId'))
		})

		//

		init();
		stompClient.subscribe('/gateway/kick' + sessionId, function (data) {
			alert('被提出')
		})
		stompClient.subscribe('/gateway/c2cGetOpenOrders-' + sessionId, function (data) {
			var response = jQuery.parseJSON(data.body);
			$('#buyBox').find('tbody').html('')
			$('#sellBox').find('tbody').html('')
			$.each(response.Sell, function (i, el) {
				var buyBox = document.getElementById("buyBox")
				var buyBoxTbody = document.getElementById("buyBoxTbody")
				buyBoxTbody.innerHTML += '<tr><td>' + response.Sell[i].Id + '</td>' + '<td>' + response.Sell[i].Price + '</td>' + '<td>' + response.Sell[i].Amount + '</td>' + '<td>' + response.Sell[i].CoinName + '</td>' + '<td>' + response.Sell[i].Amount * response.Sell[i].Price + '</td>' + '<td><button class="buttonColor tradingButton cancelBtn cancelAll" >Buy</button></td>' + '</tr>'
			});
			$('#buyBox tbody tr button').each(function (i, el) {
				$(el).click(function (e) {
					$('#buyModal').modal('show')
					$('#buyCoinPrice').text($(e.target).parents('tr').find('td:nth-child(2)').text())
					$('#buyCoinText').text($(e.target).parents('tr').find('td:nth-child(4)').text())
					$('#buyCoinBtn').text($(e.target).parents('tr').find('td:nth-child(4)').text())
					$("#otcBuyEntrustId").val($(e.target).parents('tr').find('td:nth-child(1)').text())
				})
			})
			$.each(response.Buy, function (i, el) {
				var sellBox = document.getElementById("sellBox")
				var sellBoxTbody = document.getElementById("sellBoxTbody")
				sellBoxTbody.innerHTML += '<tr><td>' + response.Buy[i].Id + '</td>' + '<td>' + response.Buy[i].Price + '</td>' + '<td>' + response.Buy[i].Amount + '</td>' + '<td>' + response.Buy[i].CoinName + '</td>' + '<td>' + response.Buy[i].Amount * response.Buy[i].Price + '</td>' + '<td><button class="buttonColor tradingButton cancelBtn cancelAll" >Sell</button></td>' + '</tr>'
			});
			$('#sellBox tbody tr button').each(function (i, el) {
				$(el).click(function (e) {
					$('#sellModal').modal('show')
					$('#sellCoinPrice').text($(e.target).parents('tr').find('td:nth-child(2)').text())
					$('#sellCoinText').text($(e.target).parents('tr').find('td:nth-child(4)').text())
					$('#sellCoinBtn').text($(e.target).parents('tr').find('td:nth-child(4)').text())
				})
			})
		});
		stompClient.subscribe('/gateway/c2cGetUserPendingOrder-' + sessionId, function (data) {
			var response = jQuery.parseJSON(data.body);
			$('#c2cUserPendingOrderTable').bootstrapTable('destroy')
			var openOrderData = response.OrderList
			$('#c2cUserPendingOrderTable').bootstrapTable({
				rowStyle: function (row, index) {
					var style = {};
					style = {
						css: {
							'text-align': 'center'
						}
					};
					return style;
				},

				striped: true,
				pagination: 'true',
				pageSize: 6,
				columns: [{
						field: 'Id',
						title: 'OrderId'
					},
					{
						field: 'CoinName',
						title: 'CoinName',

					}, {
						field: 'EntrustType',
						title: 'TradeType',
						formatter: function (val, row, index) {
							if (row.EntrustType == 0) {
								return 'Buy'
							} else if (row.EntrustType == 1) {
								return 'Sell'
							}
						}
					},
					{
						field: 'Price',
						title: 'Price',

					},
					{
						field: 'Total',
						title: 'Total',

					},
					{
						field: '',
						title: 'Handel',
						formatter: function (val, row, index) {
							return '<button   class="deleteBtn" >Cancel</button>'
						},
						events: {
							'click .deleteBtn': function (ev, value, row, index) {
								$('#cancelModal').modal('show')

								$('#cancelBtn').click(function () {
									$('#cancelModal').modal('hide')

									stompClient.send("/ws/c2c/entrust", {}, JSON.stringify({ //用户取消订单
										'Tag': 20739,
										'RequestID': 'testtesttest',
										'UserID': $.cookie('UserId'),
										'id': row.Id,
									}));
								})
							}
						}
					}


				],
				data: openOrderData,

			});

		});
		stompClient.subscribe('/gateway/c2cGetUserOpenOrder-' + sessionId, function (data) {

			var response = jQuery.parseJSON(data.body);
			var c2cUserOpenOrderTable = document.getElementById("c2cUserOpenOrderTable")
			$('#c2cUserOpenOrderTable').bootstrapTable('destroy')
			var openOrderData = response.OrderList
			$('#c2cUserOpenOrderTable').bootstrapTable({
				rowStyle: function (row, index) {
					var style = {};
					style = {
						css: {
							'text-align': 'center'
						}
					};
					return style;
				},

				striped: true,
				pagination: 'true',
				pageSize: 6,
				columns: [{
						field: 'EntrustId',
						title: 'OrderId'
					},
					{
						field: 'CoinName',
						title: 'CoinName',

					}, {
						field: 'EntrustType',
						title: 'TradeType',
						formatter: function (val, row, index) {
							if (row.EntrustType == 0) {
								return 'Buy'
							} else if (row.EntrustType == 1) {
								return 'Sell'
							}
						}
					},
					{
						field: 'Price',
						title: 'Price',

					},
					{
						field: '',
						title: 'Total',

					},


				],
				data: openOrderData,

			});

			//




		});
		stompClient.subscribe('/gateway/c2cGetUserHistoricalOrder-' + sessionId, function (data) {
			var response = jQuery.parseJSON(data.body);

			var historyOrderData = response.HistoryMarket

			console.log(historyOrderData)
			$('#c2cUserHistoryOrderTable').bootstrapTable('destroy')

			$('#c2cUserHistoryOrderTable').bootstrapTable({
				rowStyle: function (row, index) {
					var style = {};
					style = {
						css: {
							'text-align': 'center'
						}
					};
					return style;
				},

				striped: true,
				pagination: 'true',
				pageSize: 6,
				columns: [{
						field: 'Id',
						title: 'OrderId'
					},
					{
						field: 'CoinName',
						title: 'CoinName',

					}, {
						field: 'EntrustType',
						title: 'TradeType',
						formatter: function (val, row, index) {
							if (row.EntrustType == 0) {
								return 'Buy'
							} else if (row.EntrustType == 1) {
								return 'Sell'
							}
						}
					},
					{
						field: 'Price',
						title: 'Price',

					},
					{
						field: 'Total',
						title: 'Total',

					},


				],
				data: historyOrderData
			});
		});
		stompClient.subscribe('/gateway/c2cCoinList-' + sessionId, function (data) {
			var response = jQuery.parseJSON(data.body);
			var coinlist = document.getElementById("coinlist") //CoinList
			$('#coinlist').html('')
			$.each(response.CoinList, function (i, el) {
				coinlist.innerHTML += '<li ><a href="#' + response.CoinList[i].CoinName + '"' + 'data-toggle="tab">' + response.CoinList[i].CoinName + '</li>'

			})
			$('#coinlist').find('li a').click(function (e) {
				$('#selfBuyBtn').text($(e.target).text())
				$('#selfSellBtn').text($(e.target).text())
				$('#selfSellTabText').text($(e.target).text())
				$('#selfBuyTabText').text($(e.target).text())

				stompClient.send("/ws/c2c/home", {}, JSON.stringify({
					'RequestID': 'testtesttest',
					'UserID': $.cookie('UserId'),
					'Token': $(e.target).text(),
				}));

			})
		});
		stompClient.subscribe('/gateway/c2cCoinMarket-' + sessionId, function (data) {
			var response = jQuery.parseJSON(data.body);
			$('#newPrice').html(response.CurrentPrice);
			$('#ydTop').html(response.Yesterday.Top);
			$('#tdTop').html(response.Today.Top);
			$('#ydBottom').html(response.Yesterday.Bottom);
			$('#tdBottom').html(response.Today.Bottom);
			$('#ydVol').html(response.Yesterday.Volume);
			$('#tdVol').html(response.Today.Volume);
		});
		stompClient.subscribe('/gateway/c2cCoinHistoricalMarket-' + sessionId, function (data) {
			var response = jQuery.parseJSON(data.body);
			var coinMarketTab = document.getElementById("coinMarketTab")

		});

		otcBuyform(function () {
			trade(0)
			$('#buyModal').modal('hide')
		})
		otcSellForm(function () {
			trade(1)
			$('#sellModal').modal('hide')
		})
		entrustBuyForm(function () {
			entrust(0)
			$('#releaseModal').modal('hide')
		})
		entrustSellFrom(function () {
			entrust(1)
			$('#releaseModal').modal('hide')
		})
		$('#buyAmount').blur(function () {
			$('#otcBuyTotal').text(Number($('#buyPrice').val()) * Number($('#buyAmount').val()))
		})
		$('#buyPrice').blur(function () {
			$('#otcBuyTotal').text(Number($('#buyPrice').val()) * Number($('#buyAmount').val()))
		})
		$('#sellPrice').change(function (e) {
			$('#otcSellTotal').text(Number($('#sellPrice').val()) * Number($('#sellAmount').val()))
		})
		$('#sellAmount').blur(function () {
			$('#otcSellTotal').text(Number($('#sellPrice').val()) * Number($('#sellAmount').val()))
		})
		stompClient.subscribe('/gateway/c2cTrade-' + sessionId, function (data) {
			var res = jQuery.parseJSON(data.body);
			if (res.Result == true) {
				//   alert('成功')
				$('#confirmModal').modal('show')
				$('#modalBankNumber').text(res.Banklist.Bankaccount)
				$('#modalBankName').text(res.Banklist.BankName)
				$('#modalUserName').text(res.Banklist.UserName)
				$('#modalTradeId').text(res.Banklist.TradeId)
				$('#modalTelPhone').text(res.Banklist.MobilePhoneNum)
				var data1 = 10
				var id = setInterval(frame, 1000);
				function frame() {
					
					clearInterval(id);
					$('#timeData').text(data1)
					if (data1 > 0){
						$('#confirmBtn').click(function () {
							confirm()
						})
					}else {
						data1--
						// $('#timeData').text(data1)
						if ($('#timeData').text() == 0) {
							TimeoutOutCancel()
						}
					}
				}
				// function frame() {
				// 	if (data1 == 0) {
				// 		clearInterval(id);
				// 	}else {
				// 		data1--
				// 		$('#timeData').text(data1)
				// 		if ($('#timeData').text() == 0) {
				// 			TimeoutOutCancel()
				// 		}
				// 	}
				// }


			} else {
				alert('失败')
			}

		});
		stompClient.subscribe('/gateway/c2cCancelTrade-' + sessionId, function (data) { //订阅超时自动取消
			var response = jQuery.parseJSON(data.body);
			alert(' timeout cancel')
			$('#cancelModal').modal('hide')
		});
		stompClient.subscribe('/gateway/c2cPay-' + sessionId, function (data) { //订阅确认
			var response = jQuery.parseJSON(data.body);
			 console.log("已确认")
		});
		stompClient.subscribe('/gateway/c2cPayConfirm-' + sessionId, function (data) {
			var response = jQuery.parseJSON(data.body);

		});
		stompClient.subscribe('/gateway/c2cCancelOpenOrder-' + sessionId, function (data) { //订阅用户取消
			var response = jQuery.parseJSON(data.body);
		});
		stompClient.subscribe('/gateway/c2cOpenOrder-' + sessionId, function (data) {
			var response = jQuery.parseJSON(data.body);
		});
		stompClient.subscribe('/gateway/confirmationPrompt-' + sessionId, function (data) {

			$('#sellStatusModal').modal('show')

		})
	});
}

function init() {
	stompClient.send("/ws/c2c/home", {}, JSON.stringify({
		'RequestID': 'testtesttest',
		'UserID': $.cookie('UserId'),
		'Token': null,
	}));
}

function trade(type) {
	var amount
	if (type == 0) {
		amount = $('#otcBuyAmount').val();
	} else {
		amount = $('#cotSellAmount').val();
	}
	stompClient.send("/ws/c2c/trade", {}, JSON.stringify({
		'Tag': 20773,
		'requestID': '123456789',
		'entrustId': $("#otcBuyEntrustId").val(),
		'userID': $.cookie('UserId'),
		'amount': amount
	}));

}

function cancelPendingOrder() { //用户取消

	stompClient.send("/ws/c2c/cancelPendingOrder", {}, JSON.stringify({
		'Tag': 20739,
		'RequestID': 'testtesttest',
		'UserID': $.cookie('UserId'),
		'tradeId': $(this).parents('tr').find('td').eq(0).text(),
	}));
}

function TimeoutOutCancel() {
	stompClient.send("/ws/c2c/cancel", {}, JSON.stringify({ //超时取消
		'Tag': 20775,
		'RequestID': 'testtesttest',
		'UserID': $.cookie('UserId'),
		'tradeId': $("#otcBuyEntrustId").val(),
	}));
}

function entrust(type) {
	var price;
	var amount;
	if (type == 0) {
		price = $('#buyPrice').val();
		//amount = $('#entrustBuyAmount').val();
		amount = $('#buyAmount').val();
	} else {
		price = $('#sellPrice').val();
		//amount = $('#entrustSellAmount').val();
		amount = $('#sellAmount').val();
	}
	stompClient.send("/ws/c2c/entrust", {}, JSON.stringify({
		'Tag': 20481,
		'RequestID': 'testtesttest',
		'UserID': $.cookie('UserId'),
		'price': price,
		'entrustType': type,
		'amount': amount,
		'coinName': $('#selfBuyTabText').text()
	}));
}

function sendLoginData() {
	stompClient.send("/ws/user/login", {}, JSON.stringify({ //login
		"Tag": 8193,
		"username": $.trim($('#lgEmail').val()),
		"loginpassword": $.trim($('#lgPassword').val()),
		"srcip": "192.168.0.1",
		"UserID": parseInt($.cookie('UserId')),
		"RequestID": RequestId
	}));
}

function sendLogoutData() {
	stompClient.send("/ws/user/logout", {}, JSON.stringify({ //logout
		"Tag": 131089,
		"UserID": parseInt($.cookie('UserId')),
		"srcip": "192.168.0.1",
		"RequestID": RequestId
	}));
}



function confirm() {
	stompClient.send("/ws/c2c/entrust", {}, JSON.stringify({ //确认
		"Tag": 20489,
		'UserID': $.cookie('UserId'),
		// 'tradeId': $("#otcBuyEntrustId").val(),
		tradeId:$('#modalTradeId').text()

	}));
}

function tradePadelogout() {
	//	         $('#availableBalance_buy').text('')
	//			$('#availableBalance_sell').text('')
	//			$('#TradeCoinAvailable').text('')
	//			$('#BaseCoinAvailable').text('')
	//
	//			$('#loginLi').html('<a id="loginBtn" data-toggle="modal" data-target="#myModal"> Login </a>')
	//			$('.ulList li:last').html('<a href="Register.html">  Register  </a>')
	//			$('#userItem').addClass('hide')
	//			$('#openOrderTable').bootstrapTable('removeAll')
	//			$('#historyOrderTable').bootstrapTable('removeAll')
	$.removeCookie('UserId', {
		path: '/'
	})
	$.removeCookie('email', {
		path: '/'
	})
	$.removeCookie('sessionId', {
		path: '/'
	})
	alert($.cookie('sessionId'))
}
// function buy() {
// 	alert("buy");
// }

function sell() {
	s
	alert("sell");
}

$(function () {
	connect();

});