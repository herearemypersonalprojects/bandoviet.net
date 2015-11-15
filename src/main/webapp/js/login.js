/**
 * 
 */
var locationSearchAutocomplete;
var geocoder = new google.maps.Geocoder();

$(document).ready(function() {
	
	google.maps.event.addDomListener(window, 'load', initialize);

	function initialize() {
		locationSearchAutocomplete = new google.maps.places.Autocomplete(document.getElementById('address'));
		
		if ($('#confidentLevel').val() != 80) {
			showCurrentLocation();
		} else {
			$('#addressBox').hide();
		}
	}
	
    $('#address').blur(function () { 
    	$(this).val($(this).val().trim());
    	if (address != null && address != '') {
    		showLocation($(this).val(), false);
    		
    	}
    });
    
    $('#address').keypress(function(event) {
        if (event.keyCode == 13) {
        	$(this).trigger('blur');
            event.preventDefault();
        }
    });
    
    function showCurrentLocation() {
  			if (navigator.geolocation) navigator.geolocation.getCurrentPosition(function(pos) {
					latitude = pos.coords.latitude;
					longitude = pos.coords.longitude;
					$('#addressBox').hide();
					$('#confidentLevel').val(80);
					showLocation(new google.maps.LatLng(latitude, longitude), true);
					
				 // TODO: Dung de lay dia diem nguoi dung luon
				    
				}, function(error) {							
					

					
					
				});	
    }
    
    
	function showLocation(address, isLatlng) {
	        if (isLatlng) {
		        geocoder.geocode({'latLng': address}, function (results, status) {
		        	getLocation(results, status) ;
		        });	        	
	        } else {
		        geocoder.geocode({'address': address}, function (results, status) {
		        	getLocation(results, status) ;
		        	
		    		// Kiem tra xem dia chi nhap co dung khong (by country)
					$.get("http://ipinfo.io", function(response) { 
						if (response.country != $('#country').val()) {
							alert('Địa chỉ hoặc thành phố không đúng!');
							
							// thong bao cho admin biet truong hop nay
							$.ajax({url: "/public/trysignup?addressinput=" + $('#address').val() + "&addressfound=" + response.country, success: function(result){
						        
						    }});
							
							// reset
							$('#address').val('');
						}
						
					    //$("#ip").html(response.ip);
					    //$("#address").html(response.city + ", " + response.region);									
						//latitude = response.loc.split(',')[0];
						//longitude = response.loc.split(',')[1]; 
						//showLocation(new google.maps.LatLng(latitude, longitude), true);
						//var alert = $('#myLocationAlert');
						//$('#myLocationAlertcity').html(response.city);
						//$('#myLocationAlertregion').html(response.region);
						//$('#myLocationAlertcountry').html(response.country);
						//alert.modal('show');
					}, "jsonp");		        	
		        });        	
	        }

	   
	} // END SHOW LOCATION WITH ADDRESS
	function getLocation(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
        	
            var lat = '';
            var lng = '';
            lat = results[0].geometry.location.lat();
            lng = results[0].geometry.location.lng();
            addressReturn = results[0].formatted_address;
            
            document.getElementById('address').value = addressReturn;
            document.getElementById('latitude').value = lat;
            document.getElementById('longitude').value = lng;
            getCity(results);	
           
        } else {
        	document.getElementById('address').value = "";
        	alert("Địa chỉ không tìm thấy. Hãy nhập lại!");
        }		
	}
	
	// [START getCity]
	function getCity(results) {
	    // RESET
	    $('#country').val("");
	    $('#city').val("");
   
	    //break down the three dimensional array into simpler arrays
	    for (i = 0; i < results.length; ++i) {
	        var super_var1 = results[i].address_components;
	        for (j = 0; j < super_var1.length; ++j) {
	            var super_var2 = super_var1[j].types;
	            for (k = 0; k < super_var2.length; ++k) {  //console.log(super_var2[k] + ":" + super_var1[j].short_name + ":" + super_var1[j].long_name);
	                //find city
	                if (super_var2[k] == "locality" && $('#city').val() == "") {
	                    //put the city name in the form
	                    $('#city').val(super_var1[j].long_name);
	                }
	                //find county
	                if (super_var2[k] == "country" && $('#country').val() == "") {
	                    //put the county name in the form
	                    $('#country').val(super_var1[j].short_name); //alert(super_var1[j].long_name);alert(super_var1[j].short_name);
	        
	                }
	 
	            }
	        }
	    }
	} // [START getCity]
	/* end map service to display results */	
});