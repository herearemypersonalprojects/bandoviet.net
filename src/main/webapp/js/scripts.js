$(document).ready(
		function() {/* google maps -----------------------------------------------------*/
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
			            position: google.maps.ControlPosition.LEFT_TOP
			        },
			        panControl: false,
			        mapTypeControl: false,
			        scaleControl: false,
			        mapTypeId: google.maps.MapTypeId.ROADMAP
				};

				var marker = new google.maps.Marker({
					position : latlng,
					url : '/',
					animation : google.maps.Animation.DROP
				});

				var map = new google.maps.Map(document
						.getElementById("map-canvas"), mapOptions);
				marker.setMap(map);

				// STYLE
				$.getScript( 'js/map_style.js', function() {
					map.setOptions({
						styles : styles
					});
				});
				
			}
			;
			/* end google maps -----------------------------------------------------*/
		});