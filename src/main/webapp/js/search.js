/**
 * 
 */


$(document).ready(function() {

      $( '#keywords' ).autocomplete({
      //define callback to format results
        source: function (request, response) { 
            jQuery.get('/autocomplete', {
                query: request.term
            }, function (data) {
                // assuming data is a JavaScript array such as
                // ["one@abc.de", "onf@abc.de","ong@abc.de"]
                // and not a string
                response(data);
            });
        },
        minLength: 3,
        delay: 500,
      //define select handler
        select : function(event, ui) {
            if (ui.item) {
				// prevent autocomplete from updating the textbox
				event.preventDefault();
				// manually update the textbox and hidden field
				//$(this).val(ui.item.label.split(":")[0]);
				searchByKeywords(ui.item.label.split(":")[0]);
            }
        },
        open: function() {

        },
        close: function() {

        },
        focus:function(event,ui) {
			// prevent autocomplete from updating the textbox
			event.preventDefault();
			// manually update the textbox
			$(this).val(ui.item.label.split(":")[0]);
        },
      });


	$('.navbar-form').submit(function(event){
		  
		  // prevent default browser behaviour
		  event.preventDefault();
		  //do stuff with your form here
		  if ($('#keywords').val()) {
			  window.location.href = '/search?keywords=' + $('#keywords').val();
		  } else {
			  $('#keywords').focus();
		  }
		});
});

function searchByKeywords(keywords) {
	window.location.href = '/search?keywords=' + keywords;
}

function searchByType(type) {
	$('#about').find('.modal-body').find('p').text('Bạn phải login vào để xem bản đồ bạn bè của mình');
	$('#about').modal('show');
}

