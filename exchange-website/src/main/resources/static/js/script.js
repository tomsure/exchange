$(function() {
	$('#searchBtn').click(function() {
		$('.header').find('.customViw').hide()
		$('.header').append("<div class='headerSearchBox' ><input class='searchContent' placeholder='搜索...' id='' /><span class='icon-close' id='searchMarektBtn'></span></div>")
		$('.searchContent').focus()
		$('#searchMarektBtn').click(function() {
			$('.headerSearchBox').remove()
//          $('.headerSearchBox').css('visibility',"hidden")
			$('.header').find('.logo,.customViw').show()
		})
	})
    $('.infoTitle').mouseover(function(){
    	$(this).parent().children('.info').slideDown()

    })

})


$.ajax({
		type: "get",
		url: "../mockJson/informationList.json",
		async: true,
		success: function(data) {
			var arr1 = data.slice(0, 3)
			function box(title, content,bgImg) {
				return '<div class="imgRoom" id="imgRoom1" style="background-image:url('+ bgImg+ ');"><div class="title-wide"><p>'+title+'</p></div><div class="hoverText hide"><p><span class="titleMark1"></span><strong class="hoverTextTitle">' + title + '</strong></p><p class="newsSummary">' + content + '</p><span class="readMoreBtn readMorePosition"><a href="carousel2.html">阅读更多</a></span></div></div>'
			}
			$.each(arr1, function(a, b) {
				$('#informationBox1').append(box(b.title, b.content,b.url))
			});
			var arr2 = [data[3], data[4], data[5]]
			$.each(arr2, function(a, b) {
				$('#informationBox2').append(box(b.title, b.content,b.url))
			});
			imgRoomAnimation() //显示和隐藏磁贴的动画
		}
	}),
	$.ajax({
		type: "get",
		url: "../mockJson/lunbo.json",

		async: true,
		success: function(data) {
            console.log(data)
			$.each(data, function(a, b) {
				$('.carousel-inner').append('<div class="item " ><div class="bannerImg" ><a href="'+b.href +'"><img src=' + b.url + '/></a></div><div class="bannerText"><span class="bannerNewsTitle">'+ '<a href="'+b.href +'">'+ b.title+'</a>'+'</span><p><span class="iconBanner"></span></p><p  class="baanerTtxtContent">' + '<a href=" '+ b.href + '">'+  b.content +'</a>' + '</p><div><span class="readMoreBtn bannerBtnColor">' +'<a href="'+b.href +'">阅读更多</a></span></div> </div></div>')
				$('.carousel-indicators').append('<li data-target="#myCarousel" data-slide-to=' + a + '></li>')
			});
			$('.carousel-inner').find('.item:first-child').addClass('active')

		},
		error:function(e){
			console.log(e)
		}
	});
$.ajax({
	type: "get",
	url: "../mockJson/news.json",

	async: true,
	success: function(data) {
		$.each(data, function(a, b) {
			$('.newsRightList').append('<div class="newsContainer"><div class="newsImage"><a href="carousel2.html"><img class="img-responsive" src=' + b.url + '/></a></div><div class="newsTitle"><div><span class="iconTip"></span><span class="newsTitleText">' +
			'<a href="carousel2.html">' + b.title +'</a>' + '</span></div><p class="nContent">' + '<a href="carousel2.html">'+ b.content + '</a> '+' </p></div></div>')

		})

	}
});
$.ajax({
	type:"get",
	url:"../mockJson/marketInfo.json ",
	async:true,
	success:function(d){
	  $('#marketInfoTable').bootstrapTable({
                rowStyle: function (row, index) {
                    var style = {};
                    style = {
                        css: {
                            'text-align': 'left',
                            "border":'0'
                        }
                    };
                    return style;
                },
                columns: [

                    {
                        field: 'companynAame',
                        title: '公司名称',
                    },
                    {
                        field: 'price',
                        title: '现价',
                    },
                    {
                        field: 'riseAndFall',
                        title: '涨跌幅',
//                      formatter:function(val,row,index){
//                        	if(val<1){
//                        		return '<span class="">'++'</span>'
//                        	}else if(val==0){
//                        		 return '<span>'++'</span>'
//                        	}
//                        }

                    },
                    {
                        field: 'percentage',
                        title: '涨跌额',
                    }


                ],
                data: d
	  })
	}
});
