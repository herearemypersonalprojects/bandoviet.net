/**
 * 
 */


$(document).ready(function() {
	var lang = getUrlParameter('lang');
	if (!lang) lang = "vn";
	$( '.language' ).each(function() {
		var that = $( this );
	    if (that.hasClass( lang )) {
	    	that.hide();
	    };
	  });
	
	$('.navbar-brand').on('click', function() {
		$('#about').modal('show');
	});
	
	$('#eventCategory').on('click', function() {

		  console.log($('#scroll').offset().top);

		  
		  $("html, body").animate({ scrollTop: $('#scroll').offset().top }, 1000);
	    return true;
	});
	
	/* RESPONSIVE */
	responsive();
	$( window ).resize(function() {
		responsive();
	});
	
	
});

function responsive() {
	  if ($( window ).width() < 1000) {
		  $('.responsive').hide();
		  $('#map-canvas').width( '100%'); 
	  } else {
		  $('.responsive').show();
		  $('#map-canvas').width( '50%'); 
	  }	
}

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};