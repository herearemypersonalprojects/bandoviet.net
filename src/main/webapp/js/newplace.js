/**
 * 
 */

$(document).ready(function() {
	$('.newplace').click(function() {
		var button = $('.newplace');
		var form = $('#newplaceform');
		if (!form.is(":visible") ) {
			$('#newplaceform').show();
			button.html('-');
			window.location.href ="#newplace";
		} else {
			form.hide();
			button.html('+');			
		}
	});
});