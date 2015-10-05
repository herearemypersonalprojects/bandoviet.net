/**
 * item.js
 */

$(document).ready(function() {
	
	/** EDIT ITEM */
	$('.edit').on('click', function() {
		var id = $(this).data('id');
		window.location.href = "/update?id=" + id;
	});
	
	/*
	
	$('.item').on('click', function() {
		var item = $(this);
        if (!item.hasClass("item_active")) {
            var lastActive = item.closest("#results").children(".item_active");
            lastActive.removeClass("item_active");
            item.addClass("item_active");
            item.find('.panel').$(this).css('background-color', 'white');
        }
	});
	*/

});

function getPOI(id) {
	for (i = 0, len = poiList.length; i < len; i++) { 
		if (poiList[i].id == id) return poiList[i];
	}
	return null;
}