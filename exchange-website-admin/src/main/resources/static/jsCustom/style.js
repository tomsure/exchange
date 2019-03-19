	function carouselStyle(){
			$('.carouselColor').width($('.newsTitle').width()).height($('.imgChildBox').height()).css('margin-left','15px')
			$('.carouselEdit').css({
				"margin-top":"10%",
				"margin-left":"25%"
			})
				}
  
   function imgStyle(){
   	$('.imgBox,.imgParentBox').mouseover(function(){
		    $(this).find('.editBar').removeClass('hide')
		    $(this).find('.styleColor,.carouselColor').removeClass('hide')
		    
		    $(this).find('.imgNewsTitle').css('background','none')
		    carouselStyle()
	})
	$('.imgBox,.imgParentBox').mouseout(function(){
           $(this).find('.editBar').addClass('hide')
			$(this).find('.styleColor').addClass('hide')
			$(this).find('.imgNewsTitle').css('background','rgba(0,0,0,0.4)')
		
	})
	$('.circle').mouseover(function(){

	$(this).css('background-color','rgba(255,255,255,0.4)')
})
	 $('.lookBtn').click(function(){
		location.href='../html/carousel/withdrawCarousel.html'
	})
	 $('.editBtn').click(function(){
	 	 location.href='../html/carousel/editCarousel.html'
	 })

$('.circle .icon-look2').mouseout(function(){
	$(this).css('background-color','rgba(255,255,255,0.2)')
})
     
$('.carouselCreateBox.createBox').width($('.imgParentBox').find('.center-block').width()) 
  $(window).resize(function(){
  	$('.carouselCreateBox.createBox').width($('.imgParentBox').find('.center-block').width()) 
  })
	$('.positionBox .createBox').width($('.imgNewsStatus').width()).css({
		"margin-left":'0'
	})
   	   }
	
	
	
  


	
	
  
  

