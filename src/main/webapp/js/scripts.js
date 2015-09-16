/* Global variables */
var marker;
var map;

$(document).ready(
		function() {
			/* google maps -----------------------------------------------------*/
			google.maps.event.addDomListener(window, 'load', initialize);

			function initialize() {

				/* position Paris */
				var latlng = new google.maps.LatLng(48.856614, 2.3522219000000177);

				var mapOptions = {
					center : latlng,
					scrollWheel : false,
					zoom : 13,
					streetViewControl: false,
					zoomControlOptions: {
			            style: google.maps.ZoomControlStyle.SMALL,
			            position: google.maps.ControlPosition.RIGHT_TOP
			        },
			        panControl: false,
			        mapTypeControl: false,
			        scaleControl: false,
			        mapTypeId: google.maps.MapTypeId.ROADMAP
				};

				marker = new google.maps.Marker({
					position : latlng,
					url : '/',
					animation : google.maps.Animation.DROP
				});

				map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
				marker.setMap(map);

				// STYLE
				$.getScript( 'js/mapstyle.js', function() {
					map.setOptions({
						styles : styles
					});
				});				
			}
			;
			/* end google maps -----------------------------------------------------*/
			
			/* map controller */
			$('.my_location').on( "click", function() {				
				if (navigator.geolocation) navigator.geolocation.getCurrentPosition(function(pos) {
				    showLocationLatLng(pos.coords.latitude, pos.coords.longitude);
				    
				 // TODO: Dung de lay dia diem nguoi dung luon
				    
				}, function(error) {									
					$('#myLocationAlert').modal('show');
					$.get("http://ipinfo.io", function(response) {
					    //$("#ip").html(response.ip);
					    //$("#address").html(response.city + ", " + response.region);						
						showLocationLatLng(response.loc.split(',')[0], response.loc.split(',')[1]);
					}, "jsonp");
				});			
			});					
			/* end map controller */
			
			/* map service */
			function showLocationLatLng(lat, lng) {
			    var me = new google.maps.LatLng(lat, lng);
			    showLocation(me);				
			}
			function showLocation(location) {
			    marker.setAnimation(google.maps.Animation.BOUNCE);
			    marker.setPosition(location);
			    map.setCenter(location);				
			}
			/* end map service */
		});