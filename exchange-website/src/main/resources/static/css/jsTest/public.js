$('body').prepend(headerBox)
$('body').append(footerBox)
$(function() {

	$('#webMarkList').mouseenter(function(e) {
//
		$('#selectBox').slideDown()
//
		$('#side-menu').metisMenu();

		$('.infoList').find('.nav-second-level li').click(function(e) {
             e.stopPropagation()
			$(this).find("a span[class^='icon']").toggleClass('hidden')
		})
	})
	$('#side-menu').mouseleave(function(e) {
		 if(e.target.id=='side-menu'){
		 			$("#selectBox").slideUp()

		 }

	})
       



	   $('.nav.nav-second-level').find('li a span.nav-label').each(function(i,el){
//	   	   console.log(el)
	   	     if(el.textContent=='ACAE平台规则'){
	   	     	console.log(el.textContent)
	   	     }
	   })

     $('.headerContent').find('img').click(function(){
     	location.href='home1.html'
     })
})