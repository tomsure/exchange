$('body').prepend(navComponent)
$('body').prepend(headerBox)
$('body').append(footerBox)

$(function() {

	$('#webMarkList').mouseenter(function(e) {

		$('#selectBox').slideDown()

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
	   	   console.log(el)
	   	     if(el.textContent=='ACAE平台规则'){
	   	     	console.log(el.textContent)
	   	     }
	   })

     $('.headerContent,.logoImg').find('img').click(function(){
     	location.href='home1.html'
     })

    $('.infoList').mouseleave(function(){
     	$("#selectBox").slideUp()
     })

    
       
       
     
})
 function showFullText(){
     	 var contentWidth=$('.contentBox p').width() //元素宽度
     var textWdith=document.querySelector(".contentBox p").scrollWidth //文本内容宽度

       if(textWdith>contentWidth){ 
       	    $('.contentBox p').mouseenter(function(e){
       	    	$(e.target).attr('title',$(e.target).text())
       	    })
       }
     }