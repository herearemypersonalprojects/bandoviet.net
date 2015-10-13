/* Global variables */
var currentAddress = "Paris, France";
var marker = new google.maps.Marker();
var map;
var infoWnd = new google.maps.InfoWindow();
var infobox;
var poiList = [];
var detailZoom = 15;

$(document).ready(
		function() {
			/* google maps -----------------------------------------------------*/
			google.maps.event.addDomListener(window, 'load', initialize);

			function initialize() {
				// Hien thi cac ket qua tren ban do
				poiList = [];			
				$('.item_content').each(function( index, element ) {
					var item = {
						"id": 	element.getAttribute("data-id"),
						"lat": 	element.getAttribute("data-lat"),
						"lng": element.getAttribute("data-lng"),
						"title": element.getAttribute("data-title"),
						"imagePath": element.getAttribute("data-img"),
						"iconPath": element.getAttribute("data-icon"),
						"type": element.getAttribute("data-type"),
						"address": element.getAttribute("data-address")
					};
					poiList.push(item);
				});
				
				/* position Paris */
				var latlng = new google.maps.LatLng(48.856614, 2.3522219000000177);

				var mapOptions = {
					center : latlng,
					scrollWheel : false,
					zoom : 13,
					streetViewControl: true,
					zoomControlOptions: {
			            style: google.maps.ZoomControlStyle.SMALL,
			            position: google.maps.ControlPosition.RIGHT_BOTTOM
			        },
			        panControl: false,
			        mapTypeControl: false,
			        scaleControl: false,
			        mapTypeId: google.maps.MapTypeId.ROADMAP
				};

				//Creates a marker object
				marker = new google.maps.Marker({
					position : latlng,
					url : '/',
					animation : google.maps.Animation.DROP
				});

			    //Creates a map object.
				map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
				marker.setMap(map);

				// STYLE
				$.getScript( '/js/mapstyle.js', function() {
					map.setOptions({
						styles : styles
					});
				});
				
				//Mapping markers on the map
				  var bounds = new google.maps.LatLngBounds();
				  var station, i, latlng;
				  var idx = 0;
				  for (i in poiList) {
				    //Creates a marker
				    station = poiList[i];
				    latlng = new google.maps.LatLng(station.lat, station.lng);
				    bounds.extend(latlng);
				    var m = createMarker(map, latlng, station.title, idx);
				    
				    //Creates a sidebar button for the marker
				    createMarkerButton(m, idx);
				    idx = idx + 1;
			
				  }
				  //Fits the map bounds
				  if (poiList.length > 1) {
					    // Don't zoom in too far on only one marker
					    if (bounds.getNorthEast().equals(bounds.getSouthWest())) {
					       var extendPoint1 = new google.maps.LatLng(bounds.getNorthEast().lat() + 0.01, bounds.getNorthEast().lng() + 0.01);
					       var extendPoint2 = new google.maps.LatLng(bounds.getNorthEast().lat() - 0.01, bounds.getNorthEast().lng() - 0.01);
					       bounds.extend(extendPoint1);
					       bounds.extend(extendPoint2);
					    }
					  map.fitBounds(bounds); 
					  marker.setVisible(false);
					
					  if (window.location.href.indexOf('#item') < 0) { 
						  //$('#0').trigger('hover');
					  } else {
						  //$('#0').trigger('click');
					  }
					  
				  } else if (poiList.length == 1) {
					  $('#0').trigger('click');
					  //showLocationLatLng(poiList[0].lat, poiList[0].lng);
				  }			 
				  
				  
			}
			;
			/* end google maps -----------------------------------------------------*/
			
			/* map controller */
			$('.my_location').on( "click", function() {			
				if (navigator.geolocation) navigator.geolocation.getCurrentPosition(function(pos) {
				    showLocationLatLng(pos.coords.latitude, pos.coords.longitude);
				    
				 // TODO: Dung de lay dia diem nguoi dung luon
				    
				}, function(error) {							

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
					
					
				});			
			});					
			/* end map controller */
	
		});

