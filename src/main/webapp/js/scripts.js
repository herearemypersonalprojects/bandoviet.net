/* Global variables */
var currentAddress = "Ho Chi Minh, Vietnam";
var marker = new google.maps.Marker();
var map;
var infoWnd = new google.maps.InfoWindow({disableAutoPan: false  });
var infobox;
var locationSearchAutocomplete;
var poiList = [];
var markerList = [];
var previousMarker = 0;
var detailZoom = 15;
var geocoder = new google.maps.Geocoder();


$(document).ready(
		function() {
			

			/* google maps -----------------------------------------------------*/
			google.maps.event.addDomListener(window, 'load', initialize);

			  
			function initialize() {

				
				/* position Paris */
				var latlng = new google.maps.LatLng(10.8230989, 106.6296638);

				var mapOptions = {
					center : latlng,
					scrollWheel : false,
					zoom : 13,
					 minZoom: 1, maxZoom: 17,
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
				/*
				marker = new google.maps.Marker({
					position : latlng,
					url : '/',
					animation : google.maps.Animation.DROP
				});
				*/
				
				

			    //Creates a map object.
				map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
				marker.setMap(map);
				google.maps.event.addListener(map, 'click', function(event) {
					infoWnd.close();
				});
				google.maps.event.addListener(map, 'idle', function() {
					   //Do something when the user has stopped zooming/panning
				});
				
				google.maps.event.addListener(map, 'dragend', function(){
					getAddress();
				});
	
			    locationSearchAutocomplete = new google.maps.places.Autocomplete(document.getElementById('locationSearch'));
			    locationSearchAutocomplete.bindTo('bounds', map);
	
				// STYLE
				$.getScript( '/js/mapstyle.js', function() {
					map.setOptions({
						styles : styles
					});
				});
				
				displayMarkers();
				  
			};
			
			function displayMarkers() {
				// Hien thi cac ket qua tren ban do
				poiList = [];			
				$('.item_content').each(function( index, element ) {
					var item = {
						"id": 	element.getAttribute("data-id"),
						"label": element.getAttribute("data-label"),
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
				
				//Mapping markers on the map
				  var bounds = new google.maps.LatLngBounds();
				  var station, i, latlng;
				  var idx = 0;
				  for (i in poiList) {
				    //Creates a marker
				    station = poiList[i];
				    latlng = new google.maps.LatLng(station.lat, station.lng);
				    bounds.extend(latlng);
				    
				    /*
				    var m = createMarker(map, latlng, station.title, idx);
				    
				    //Creates a sidebar button for the marker
				    createMarkerButton(m, idx);
				    */
				      var infoBubble=new InfoBubble(
			                {
			                    map: map,
			                    content: getTitle(station.title.substring(0,10)),
			                    position: latlng,
			                    shadowStyle: 0,
			                    padding: 0,
			                    backgroundColor: '#FF5A5F',
			                    borderRadius: 4,
			                    arrowSize: 7,
			                    borderWidth: 1,
			                    borderColor: '#ccc',
			                    disableAutoPan: true,
			                    hideCloseButton: true,
			                    arrowPosition: 50,
			                    backgroundClassName: 'infoBubbleBackground',
			                    arrowStyle: 0,
			                    baseZIndex_: 100,
			                    disableAnimation: false,
			                    idx: idx
			                }
			            );

				       infoBubble.open();
				 
				       markerList.push(infoBubble);
				    
				    idx = idx + 1;
			
				  }
				  
				  // display the user's given location
				  if ($('#locationSearch').val()) {
					  latlng = new google.maps.LatLng($('#cityLat').val(), $('#cityLng').val());
					  bounds.extend(latlng);
					  var here = new google.maps.Marker({
		                    map: map,
		                    position: latlng,
		                    draggable: false
		                });
					 infoWnd.setContent('<span>'+ $('#locationSearch').val() + '</span>');
					 infoWnd.open(map, here);
				  }
				  
				  // my location button
				  $('.my_location').html('<img alt="" width="28" height="28" src="/img/mylocation.png">');
				  
				  
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
				  				
			};
			

			
			/* end google maps -----------------------------------------------------*/
			
			/* map controller */

			/* end map controller */
	
		});

function getTitle(title) {
	return '<center style="color: #fff;">' + title + '</center>';
}