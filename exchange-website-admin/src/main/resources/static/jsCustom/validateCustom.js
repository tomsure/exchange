$(function(){
      		
      		function checkCount(inputText,textCount,textCountMaxLength){
      				inputText.on('input',function(){
      						textCount.text(inputText.val().length)
      					if(inputText.val().length>textCountMaxLength.text()){
       				textCount.css('color','red')
                                     			  }
      				})
      			
      		}
      		checkCount($('#titleText'),$('#titleTextCount'),$('#titleTextCountMaxLength'))
      		checkCount($('#resumeText'),$('#resumeTextCount'),$('#resumeTextCountMaxLength'))
      		checkCount($('#authorText'),$('#authorTextCount'),$('#authorTextCountMaxLength'))
      		
      	})