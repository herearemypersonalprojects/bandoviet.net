/**
 * item.js
 */

$(document).ready(function() {
    if ($('.item').length > 1) {
    	$('article').readmore({
  		  speed: 75,
  		  lessLink: '<a href="#" class="less">Thu nhỏ</a>',
  		  moreLink: '<a href="#" class="more">Đọc tiếp</a>',
  		  collapsedHeight: 128
  		});   	
    }
	
	/** EDIT ITEM */
	$('.edit').on('click', function() {
		var id = $(this).data('id');
		window.location.href = "/update?id=" + id;
	});
	
	/* EXPAND if only one item */
	if ($('.item').length == 1) {
		$('.item').trigger('click'); 
	}
	
	
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

function quickView(id) { 
    loadDataForModal(id);
}

function getPOI(id) {
	for (i = 0, len = poiList.length; i < len; i++) { 
		if (poiList[i].id == id) return poiList[i];
	}
	return null;
}


function loadDataForModal(id){
	$('#'+id).find('.more').trigger('click'); 
    var modal =
        '<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">' +
            '<div class="modal-dialog">' +
                '<div class="modal-content">' +
                    '<div class="modal-header">' +
                        '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true"><img src="/img/close.png"></span></button>' +
                        '<div class="left">' +
                            '<h2>' + poiList[id].title + '</h2>' +
                            '<figure>' + poiList[id].address + '</figure>' +
                        '</div>' +
                    '</div>' +
                    '<div class="modal-body">' +
                    $('#'+id).find('article').html();
                    '</div>' +
                    '<div class="modal-footer">' +
                        '<a href="/place/' + poiList[id].title + '/' + poiList[id].id + '" class="btn btn-default btn-large">Show Detail</a>' +
                    '</div>' +
                '</div>' +
            '</div>' +
        '</div>';

    // Draw Modal gallery --------------------------------------------------------------------------------------------------


    $('body').append(modal);
    var $modal = $('.modal');
    $modal.on('hidden.bs.modal', function (e) {
        $('.modal').remove();
    });
}