var latitude = 48.856614;
var longitude = 2.3522219000000177;
var paris = new google.maps.LatLng(latitude, longitude);
var zoomLevel = 13;
var zoomMax = 17;
var zoomMin = 2;
var map;
var geocoder;
var marker;
var isMakerDrag = false; // User move marker to choose a place
var infowindow;
var lstPlaces = new Array();
var realTimeUpdateController;
var autocomplete;
var componentForm = {
    street_number: 'short_name',
    route: 'long_name',
    locality: 'long_name',
    administrative_area_level_1: 'short_name',
    country: 'long_name',
    postal_code: 'short_name'
};

google.maps.event.addDomListener(window, 'load', initialize);

// [START initialize]
function initialize() {
    map = new google.maps.Map(document.getElementById('map-canvas'), {
        zoom: zoomLevel,
        minZoom: zoomMin,
        maxZoom: zoomMax,
        //scrollwheel: false,
        panControl: false,
        zoomControl: true,
        mapTypeControl: false,
        scaleControl: false,
        streetViewControl: false,
        overviewMapControl: true,
        zoomControlOptions: {
            style: google.maps.ZoomControlStyle.SMALL,
            position: google.maps.ControlPosition.LEFT_TOP
        },
        center: paris,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
	// STYLE
	$.getScript( '/js/mapstyle.js', function() {
		map.setOptions({
			styles : styles
		});
	});


    geocoder = new google.maps.Geocoder();

    infowindow = new google.maps.InfoWindow();

    // Create the autocomplete object, restricting the search to geographical location types.
    autocomplete = new google.maps.places.Autocomplete(
        /** @type {HTMLInputElement} */(document.getElementById('address')),
        {types: ['geocode']});


    google.maps.event.addListener(map, 'click', function (event) {
        placeMarker(event.latLng);
    });
    
    if ($('#latitude').val()) {
    	latitude = $('#latitude').val();
    	longitude = $('#longitude').val();
    	showMarker(15);
    } else {
    	//showMyLocation();
    }
    

    $("#title").focus();
} // [END initialize]

$('.my_location').on( "click", function() {	
	showMyLocation(); 
});
function showMyLocation() {
			/* map controller */
					
				if (navigator.geolocation) navigator.geolocation.getCurrentPosition(function(pos) {
					latitude = pos.coords.latitude;
					longitude = pos.coords.longitude;
					showMarker(15);
				 // TODO: Dung de lay dia diem nguoi dung luon
				    
				}, function(error) {							
					
					$.get("http://ipinfo.io", function(response) {
					    //$("#ip").html(response.ip);
					    //$("#address").html(response.city + ", " + response.region);									
						latitude = response.loc.split(',')[0];
						longitude = response.loc.split(',')[1]; 
						placeMarker(new google.maps.LatLng(latitude, longitude));
						//var alert = $('#myLocationAlert');
						//$('#myLocationAlertcity').html(response.city);
						//$('#myLocationAlertregion').html(response.region);
						//$('#myLocationAlertcountry').html(response.country);
						//alert.modal('show');
						
					}, "jsonp");
					
					
				});			
			};	
		/* end map controller */

// START PLACE MARKER
//Add maker
function placeMarker(location) {
    try {
        if (marker != null) marker.setMap(null);
        marker = new google.maps.Marker({
            position: location,
            map: map,
            draggable: true
        });
        google.maps.event.addListener(marker, 'dragstart', function () {
            if (infowindow != null)
                infowindow.close();
        });
        google.maps.event.addListener(marker, 'dragend', getAddress);
        //map.setCenter(location);
        getAddress();
    } catch (ex) {
        console.log(ex);
    }
} // END PLACE MARKER

function realtimeUpdate() {
    clearInterval(realTimeUpdateController);
    realTimeUpdateController = setInterval(function () {
        updateInformation()
    }, 1000);
}


// START attachInformation
function attachInformation(placemarker, content) {
    var info = new google.maps.InfoWindow({
        content: content
    });

    marker.addListener('click', function () {
        info.open(placemarker.get('map'), placemarker);
    });
} // END attachInformation


// [START region_fillform]
function fillInAddress(z) {
    // Get the place details from the autocomplete object.
    var place = autocomplete.getPlace();


    if (!place.geometry) {
        window.alert("Autocomplete's returned place contains no geometry");
        return;
    }

    // If the place has a geometry, then present it on a map.
    if (place.geometry.viewport) {
        map.fitBounds(place.geometry.viewport);
    } else {
        map.setCenter(place.geometry.location);
        map.setZoom(zoomLevel);
    }


    latitude = place.geometry.location.lat();
    longitude = place.geometry.location.lng();
    showMarker(zoomLevel);
    for (var component in componentForm) {
        document.getElementById(component).value = '';
        document.getElementById(component).disabled = false;
    }

    // Get each component of the address from the place details
    // and fill the corresponding field on the form.
    for (var i = 0; i < place.address_components.length; i++) {
        var addressType = place.address_components[i].types[0];
        if (componentForm[addressType]) {
            var val = place.address_components[i][componentForm[addressType]];
            document.getElementById(addressType).value = val;
        }
    }
}
// [END region_fillform]

// [START region_geolocation]
// Bias the autocomplete object to the user's geographical location,
// as supplied by the browser's 'navigator.geolocation' object.
function geolocate() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var geolocation = new google.maps.LatLng(
                position.coords.latitude, position.coords.longitude);
            var circle = new google.maps.Circle({
                center: geolocation,
                radius: position.coords.accuracy
            });
            autocomplete.setBounds(circle.getBounds());
        });
    }
}
// [END region_geolocation]


// [START getCity]
function getCity(results) {
    latitude = results[0].geometry.location.lat();
    longitude = results[0].geometry.location.lng();

    // RESET
    $('#locality').val("");
    $('#country').val("");
    $('#postal_code').val("");
    $('#street_number').val("");
    $('#route').val("");

    //break down the three dimensional array into simpler arrays
    for (i = 0; i < results.length; ++i) {
        var super_var1 = results[i].address_components;
        for (j = 0; j < super_var1.length; ++j) {
            var super_var2 = super_var1[j].types;
            for (k = 0; k < super_var2.length; ++k) {
                //find city
                if (super_var2[k] == "locality" && $('#locality').val() == "") {
                    //put the city name in the form
                    $('#locality').val(super_var1[j].long_name);
                }
                //find county
                if (super_var2[k] == "country" && $('#country').val() == "") {
                    //put the county name in the form
                    $('#country').val(super_var1[j].short_name); //alert(super_var1[j].long_name);alert(super_var1[j].short_name);
                }
                //find State
                if (super_var2[k] == "postal_code" && $('#postal_code').val() == "") {
                    //put the state abbreviation in the form
                    $('#postal_code').val(super_var1[j].short_name); 
                }
                // street_number 66:  i
                if (super_var2[k] == "street_number" && $('#street_number').val() == "") {
                    $('#street_number').val(super_var1[j].short_name);
                }

                if (super_var2[k] == "route" && $('#route').val() == "") {
                    $('#route').val(super_var1[j].short_name);
                }

            }
        }
    }
} // [START getCity]

// [START showMarker]
function showMarker(z) {
    var latlng = new google.maps.LatLng(latitude, longitude);
    map.setCenter(new google.maps.LatLng(latitude, longitude));
    map.setZoom(z);
    if (marker != null) marker.setMap(null);
    
    marker = new google.maps.Marker({
        position: latlng,
        icon: "/img/flags/vietnammarker.png",
        map: map,
        draggable: true
    });
    marker.addListener('click', toggleBounce);
    
    google.maps.event.addListener(marker, 'dragstart', function () {
        if (infowindow != null)
            infowindow.close();
    });
    google.maps.event.addListener(marker, 'dragend', getAddress);
    
    document.getElementById('latitude').value = latitude;
    document.getElementById('longitude').value = longitude;

    if (infowindow != null) {
        var imagePath = "";
        if ($('#imagePath').val()) {
        	imagePath = "<img height='128' src=" + $('#imagePath').val() +"><br>" ;
        }
        infowindow.setContent(imagePath + "<span id='address'><b>" + bds_lang.GoogleMaps.Address + " : </b>" + $('#address').val() + "</span>");
        infowindow.open(map, marker);
    }
} // [END showMarker]

// START toggleBounce marker animation
function toggleBounce() {
    if (marker.getAnimation() !== null) {
        marker.setAnimation(null);
    } else {
        marker.setAnimation(google.maps.Animation.BOUNCE);
    }
} // END toggleBounce

// START SHOWADDRESS
function showAdd(address) {
    try {
        var point = marker.getPosition();
//alert(point);
        var lat = point.lat();
        var lng = point.lng();
        document.getElementById('latitude').value = lat;
        document.getElementById('longitude').value = lng;
        var latlng = new google.maps.LatLng(lat, lng);
//alert(latlng);
        geocoder.geocode({'latLng': latlng}, function (results2, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                if (results2[0]) {
                    if (infowindow != null) {
                        if (address != '') {
                            infowindow.setContent("<span id='address'><b>" + bds_lang.GoogleMaps.Address + " : </b>" + address + "</span>");
                        } else {
                            infowindow.setContent("<span id='address'><b>" + bds_lang.GoogleMaps.Address + " : </b>" + results2[0].formatted_address + "</span>");
                        }
                        infowindow.open(map, marker);
                    }
                    addressReturn = results2[0].formatted_address;
                }
            }
            else {
                console.log("Geocoder failed due to: " + status);
            }
        });
        map.setCenter(point);
    } catch (ex) {
        console.log(ex);
    }
} // END SHOWADDRESS

// START SHOW LOCATION
function ShowLocation() {
    var address = "";
    var country = $('#country').val();
    var cityCode = $('#locality').val();
    var cp = $('#postal_code').val();
    var street = $('#route').val();
    var number = $('#street_number').val();

    if (number != '' && number != 0) {
        address = number;
    }

    address = address + street + ", ";

    if (cityCode != '' && cityCode != 0) {
        address = address + $('#city').children('option:selected').text() + " " + cp + ", ";
    }
    address = address + $('#country').children('option:selected').text();

    var mySplitResult = strLatLng().split(",");
    $("#latitude").val(mySplitResult[0]);
    $("#longitude").val(mySplitResult[1]);
    showLocation(address);
} // END SHOW LOCATION

// START LOCATION WITH ADDRESS
function showLocation(address) {
    if (address != null && address != '') {
        var add = address.split(',');
        address = add.join(',');

        if (marker != null) marker.setMap(null);
        geocoder.geocode({'address': address}, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) { 
                getCity(results);
                var lat = '';
                var lng = '';
                lat = results[0].geometry.location.lat();
                lng = results[0].geometry.location.lng();
                addressReturn = results[0].formatted_address;
                var latlng = new google.maps.LatLng(lat, lng);
                map.setCenter(results[0].geometry.location);
                marker = new google.maps.Marker({
                    map: map,
                    position: latlng,
                    draggable: true
                });
                document.getElementById('address').value = addressReturn;
                document.getElementById('latitude').value = lat;
                document.getElementById('longitude').value = lng;

                if (infowindow != null) {
                	var imagePath = "";
                    if ($('#imagePath').val()) {
                    	imagePath = "<img height='128' src=" + $('#imagePath').val() +"><br>" ;
                    }
                    infowindow.setContent(imagePath + "<span id='address'><b>" + bds_lang.GoogleMaps.Address + " : </b>" + address + "</span>");
                    infowindow.open(map, marker);
                }
            } else {
                console.log("Geocode was not successful for the following reason: " + status);
                if (address.indexOf(',') > 0) {
                    var add = address.substring(address.indexOf(',') + 1);
                    showLocation(add);
                }
            }
            google.maps.event.addListener(marker, 'dragstart', function () {
                if (infowindow != null)
                    infowindow.close();
            });
            google.maps.event.addListener(marker, 'dragend', function () {
                getAddress(true);
            });
        });
        isMakerDrag = false;
    } else {
        //alert(bds_lang.GoogleMaps.AddressIncorrect);
    }
} // END SHOW LOCATION WITH ADDRESS

// START GETADDRESS WITH DRAGGED MARKER
function getAddress(makerDrag) {
    try {
        var point = marker.getPosition();

        var lat = point.lat();
        var lng = point.lng();
        document.getElementById('latitude').value = lat;
        document.getElementById('longitude').value = lng;
        var latlng = new google.maps.LatLng(lat, lng);
        geocoder.geocode({'latLng': latlng}, function (results2, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                if (results2 != null && results2[0] != null) {
                    getCity(results2);

                    addressReturn = results2[0].formatted_address;
                    $('#address').val(addressReturn);
                    if (infowindow != null) {
                    	var imagePath = "";
                        if ($('#imagePath').val()) {
                        	imagePath = "<img height='128' src=" + $('#imagePath').val() +"><br>" ;
                        }
                        infowindow.setContent(imagePath + "<span id='address'><b>" + bds_lang.GoogleMaps.Address + " : </b>" + results2[0].formatted_address + "</span>");
                        infowindow.open(map, marker);
                    }
                }
            } else {
                console.log("Geocoder failed due to: " + status);
            }
        });
        map.setCenter(point);
        if (typeof (makerDrag) != 'undefined') {
            isMakerDrag = makerDrag;
            // TODO: update address for #address

        }
    } catch (ex) {
        console.log(ex);
    }
} // END GETADDRESS