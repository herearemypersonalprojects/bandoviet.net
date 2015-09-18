/**
 * 
 */

$(document).ready(function() {
	$('.newplace').click(function() {
		var button = $('.newplace');
		var form = $('#newplaceform');
		if (!form.is(":visible") ) {
			form.show();
			button.html('-');
			$('#title').focus();
		} else {
			form.hide();
			button.html('+');			
		}
	});
});