$(function(){
	$('.meunSelect').each(function(i,el){
		$(el).click(function(){
		$(this).addClass('meunSelectText')
		$(this).parent().siblings().find('p.meunSelect').removeClass('meunSelectText')
		$(this).siblings('p.meunSelect').removeClass('meunSelectText')
    
	})
	})
})
