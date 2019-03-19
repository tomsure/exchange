$(function() {
	$('.noticeList li:even').css("backgroundColor", "#eff2f9");

})

function imgRoomAnimation() {
	$('.imgRoom').mouseover(function() {
		$(this).find(".title-wide").hide()
		$(this).find('.hoverText').removeClass('hide')
	})
	$('.imgRoom').mouseout(function() {
		$(this).find(".title-wide").show()
		$(this).find('.hoverText').addClass('hide')
	})
}