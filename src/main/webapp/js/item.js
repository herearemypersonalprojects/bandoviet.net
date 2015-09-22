/**
 * item.js
 */

$(document).ready(function() {
	$('.item').on('click', function() {
		var item = $(this);
        if (!item.hasClass("item_active")) {
            var lastActive = item.closest("#results").children(".item_active");
            lastActive.removeClass("item_active");
            item.addClass("item_active");
            item.find('.panel').$(this).css('background-color', 'white');
        }
	});
});