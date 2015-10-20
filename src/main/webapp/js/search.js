/**
 * 
 */


$(document).ready(function() {
	/*
	if (!$('#cityLat').val()) { 
		$('#cityLat').val(0);
		$('#cityLng').val(0);
		$('#countrySearch').val('0');
		$.get("http://ipinfo.io", function(response) { 
			$('#locationSearch').val(response.city + " " + response.postal + ", " + response.region + ", " + response.country);
			$('#countrySearch').val(response.country);
			$('#cityLat').val(response.loc.split(',')[0]);
			$('#cityLng').val(response.loc.split(',')[1]);
		}, "jsonp");
	}
	*/
	
	$('.my_location').on( "click", function() {			
		if (navigator.geolocation) navigator.geolocation.getCurrentPosition(function(pos) {
		    showLocationLatLng(pos.coords.latitude, pos.coords.longitude);
		    searchByKeywords($('#keywords').val(), $('#cityLat').val(), $('#cityLng').val(), $('#countrySearch').val(), $('#locationSearch').val());
		 // TODO: Dung de lay dia diem nguoi dung luon
		    
		}, function(error) {							
			/*		
			$.get("http://ipinfo.io", function(response) {
			    //$("#ip").html(response.ip);
			    //$("#address").html(response.city + ", " + response.region);									
				showLocationLatLng(response.loc.split(',')[0], response.loc.split(',')[1]);
				
				var alert = $('#myLocationAlert');
				$('#myLocationAlertcity').html(response.city);
				$('#myLocationAlertregion').html(response.region);
				$('#myLocationAlertcountry').html(response.country);
				//alert.modal('show');
				
			}, "jsonp");
			*/
			$.get("http://ipinfo.io", function(response) { 
				$('#locationSearch').val(response.city + " " + response.postal + ", " + response.region + ", " + response.country);
				$('#countrySearch').val(response.country);
				$('#cityLat').val(response.loc.split(',')[0]);
				$('#cityLng').val(response.loc.split(',')[1]);
				
				showLocationLatLng(response.loc.split(',')[0], response.loc.split(',')[1]);
				searchByKeywords($('#keywords').val(), $('#cityLat').val(), $('#cityLng').val(), $('#countrySearch').val(), $('#locationSearch').val());

			}, "jsonp");
			
		});			
	});					
	
    $('#locationSearch').blur(function () { 
    	$(this).val($(this).val().trim());
        showLocation($(this).val());
    });
    
    $('#locationSearch').bind("enterKey",function(e){
    	$(this).val($(this).val().trim());
    	showLocation($(this).val());
    	});
	$('#locationSearch').keyup(function(e){
	    if(e.keyCode == 13)
	    {
	        $(this).trigger("enterKey");
	    }
	});
	
    $('#keywords').bind("enterKey",function(e){
    	$('#searchSubmit').trigger('click'); 
    	});
	$('#keywords').keyup(function(e){
	    if(e.keyCode == 13)
	    {
	        $(this).trigger("enterKey");
	    }
	});
    	
	$('.selectedType').click(function() {
		var that = $(this);
		window.location.href = '/places/category/' + this.id + '/1';
	});

      $( '#keywords' ).autocomplete({
      //define callback to format results
        source: function (request, response) { 
            jQuery.get('/autocomplete', {
                query: request.term,
                lat: $('#cityLat').val(),
                lng: $('#cityLng').val(),
                country: $('#countrySearch').val()
            }, function (data) {
                // assuming data is a JavaScript array such as
                // ["one@abc.de", "onf@abc.de","ong@abc.de"]
                // and not a string
                response(data);
            });
        },
        minLength: 2,
        delay: 500,
      //define select handler
        select : function(event, ui) {
            if (ui.item) {
				// prevent autocomplete from updating the textbox
				event.preventDefault();
				// manually update the textbox and hidden field
				//$(this).val(ui.item.label.split(":")[0]);
				searchByKeywords('"' + ui.item.label.split(":")[0] + '"', $('#cityLat').val(), $('#cityLng').val(), $('#countrySearch').val(), $('#locationSearch').val());
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
			$(this).val('"' + ui.item.label.split(':')[0] + '"');
        },
      });

    $('#searchSubmit').click(function() {
		  var keywords = $('#keywords').val(); 
		  searchByKeywords(keywords, $('#cityLat').val(), $('#cityLng').val(), $('#countrySearch').val(), $('#locationSearch').val());
		  /*
		  if (keywords) {
			  searchByKeywords(keywords, $('#cityLat').val(), $('#cityLng').val(), $('#countrySearch').val(), $('#locationSearch').val());
		  } else {
			  $('#keywords').focus();
		  } 
		  */   	
    })  ;

	$('.navbar-form').submit(function(event){ 
		  // prevent default browser behaviour
		  event.preventDefault();
		  //do stuff with your form here
		  var keywords = $('#keywords').val();
		  searchByKeywords(keywords, $('#cityLat').val(), $('#cityLng').val(), $('#countrySearch').val(), $('#locationSearch').val());
		  /*
		  if (keywords) {
			  searchByKeywords(keywords, $('#cityLat').val(), $('#cityLng').val(), $('#countrySearch').val(), $('#locationSearch').val());
		  } else {
			  $('#keywords').focus();
		  }
		  */
		});
	
});

function searchByKeywords(keywords, lat, lng, country, address) {
	if (keywords && address) {
		window.location.href = '/places/searchterms/' + keywords + '/' + lat + '/' + lng + '/' + country + '/' + address + '/1';
	} else {
		if (keywords) {
			window.location.href = '/places/searchterms/' + keywords + '/1';
		} else {
			window.location.href = '/places/searchterms/' + lat + '/' + lng + '/' + country + '/' + address + '/1';
		}
	}
	
}

function searchByType(type) {
	$('#about').find('.modal-body').find('p').text('Bạn phải login vào để xem bản đồ bạn bè của mình');
	$('#about').modal('show');
}

