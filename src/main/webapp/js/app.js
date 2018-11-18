/**
 * 
 */


$(document).ready(function() {
	if(jQuery.browser.mobile)
	{
		window.location.href = "/unsupported";
	}
	// create a new group
	$('#createtype').click(function() {
		$('#form-createtype').modal('show');
	});
	
	// feedback
	$('#feedback').click(function() {
		$('form.contact').show();
		$("#thanks").html('')
		$('#form-feedback').modal('show');
	});
	/*
    $('form.contact').validate({
        rules: {
            name: {
                minlength: 2,
                required: true
            },
            email: {
                required: true,
                email: true
            },
            message: {
                minlength: 2,
                required: true
            }
        },
        highlight: function (element) {
            $(element).closest('.form-group').removeClass('success').addClass('error');
        },
        success: function (element) {
            element.text('OK!').addClass('valid')
                .closest('.form-group').removeClass('error').addClass('success');
        }
    });
    */
    $('form.contact').submit(function(){
    	if ($('form.contact').valid()) {
            $.ajax({
                type: "POST",
                url: "/feedback", //process to mail
                data: $('form.contact').serialize(),
                success: function(msg){
                	$('form.contact').hide();
                	$("#thanks").html(msg); //hide button and show thank you
                	$('#thanks').removeClass('hide');
                	//$('#thanks').delay(5000).fadeOut('slow');
                	setTimeout(function() {
                		$("#form-feedback").modal('hide');
                    }, 1000);
                    //$("#form-feedback").modal('hide'); //hide popup  
                },
                error: function(){
                    //alert("failure");
                }
            });   		
    	}

        return false;
    });
    
	//alert(navigator.language.substr (0, 2));
	// restore from cookie
	var params = readCookie(name);
	if (params) {
		window.location.href = params;
	}
	// locate the user to search
	$.get("http://ipinfo.io", function(response) {
		if (response.city) currentAddress = response.city;
		if (response.region) currentAddress = currentAddress + " " + response.region;
		if (response.country) currentAddress = currentAddress + " " + response.country;
		currentAddress = $.trim(currentAddress);
		$('#searchCity').val(currentAddress); 
		//SetMapAddress(currentAddress, map); 
	}, "jsonp");
	
	$('.navbar').removeClass('hide');
	
	$('.navbar-brand').click(function() {
		//$('#selectCountry').modal('show');
	});
	
	/*
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
	*/
	$('.newplace').click(function() { 
		window.location.href = "/create";
	});
	
	var lang = getUrlParameter('lang'); 
	if (!lang) lang = "vn";
	$( '.language' ).each(function() {
		var that = $( this );
	    if (that.hasClass( lang )) {
	    	that.hide();
	    };
	  });
	$('.language').on('click', function() { 
		var lang = $(this).attr('lang')
		var currentUrl = window.location.href;
		if (currentUrl.indexOf('?lang=') >= 0) { 
			window.location.href = '?lang=' + lang;
		} else
		if (currentUrl.indexOf('?') >= 0) { 
			window.location.href = currentUrl.split('#')[0].split('&lang=')[0] + '&lang=' + lang; 
		} else {
			window.location.href = '?lang=' + lang;
		}
		
	});
	
	
	$('#abouticon').on('click', function() {
		$('#about').modal('show');
	});
	
	$('#eventCategory').on('click', function() {

		  console.log($('#scroll').offset().top);

		  
		  $("html, body").animate({ scrollTop: $('#scroll').offset().top }, 1000);
	    return true;
	});
	
	/* RESPONSIVE
	responsive();
	$( window ).resize(function() {
		responsive();
	});
	 */
	//window.setTimeout("$('#keywords').focus();", 1000);
});

function responsive() {
	  if (window.mobileAndTabletcheck() || detectmob()) {
		  //window.location.href = "/unsupported";
		  $('#results').addClass('hide');
		  $('#map-canvas').width('100%');
	  } else {
		  $('#results').removeClass('hide');
		  $('#map-canvas').width('50%');
	  }
	  
	  /*
	    var w2m="http://m.theworldmap.org";
		var devices = /Android|webOS|iPhone|iPod|BlackBerry|RIM|Touch|KFTIEMobile|Opera Mini/i;
		if( devices.test(navigator.userAgent)){ 
		window.location.href=w2m+window.location.pathname
		}
	   */
	  
	  /*
	  if ($( window ).width() < 1000) {
		  $('.responsive').hide();
		  $('#map-canvas').width( '100%'); 
	  } else {
		  $('.responsive').show();
		  $('#map-canvas').width( '50%'); 
	  }	
	  */
}

/* get a parameter from the current url */
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