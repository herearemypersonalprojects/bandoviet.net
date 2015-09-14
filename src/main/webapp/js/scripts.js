$(document).ready(
		function() {/* google maps -----------------------------------------------------*/
			google.maps.event.addDomListener(window, 'load', initialize);

			function initialize() {

				/* position Paris */
				var latlng = new google.maps.LatLng(48.856614, 2.3522219000000177);

				var mapOptions = {
					center : latlng,
					scrollWheel : false,
					zoom : 13
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
				/*
				var styles = [ {
					stylers : [ {
						hue : "#002467"
					}, {
						saturation : -20
					} ]
				}, {
					featureType : "road",
					elementType : "geometry",
					stylers : [ {
						lightness : 100
					}, {
						visibility : "simplified"
					} ]
				}, {
					featureType : "road",
					elementType : "labels",
					stylers : [ {
						visibility : "off"
					} ]
				} ];
				*/
				var styles = [				    
				    {
				        "featureType": "landscape.man_made",
				        "elementType": "geometry.fill",
				        "stylers": [
				            {
				                "color": "#ece2d9"
				            }
				        ]
				    },
				    {
				        "featureType": "landscape.natural",
				        "elementType": "all",
				        "stylers": [
				            {
				                "visibility": "simplified"
				            }
				        ]
				    },
				    {
				        "featureType": "landscape.natural",
				        "elementType": "geometry.fill",
				        "stylers": [
				            {
				                "visibility": "off"
				            }
				        ]
				    },
				    {
				        "featureType": "poi",
				        "elementType": "all",
				        "stylers": [
				            {
				                "visibility": "off"
				            }
				        ]
				    },
				    {
				        "featureType": "road",
				        "elementType": "geometry.fill",
				        "stylers": [
				            {
				                "hue": "#ff0000"
				            },
				            {
				                "saturation": -100
				            },
				            {
				                "lightness": 99
				            }
				        ]
				    },
				    {
				        "featureType": "road",
				        "elementType": "geometry.stroke",
				        "stylers": [
				            {
				                "color": "#808080"
				            },
				            {
				                "lightness": 54
				            }
				        ]
				    },
				    {
				        "featureType": "road",
				        "elementType": "labels.text.fill",
				        "stylers": [
				            {
				                "color": "#767676"
				            }
				        ]
				    },
				    {
				        "featureType": "road",
				        "elementType": "labels.text.stroke",
				        "stylers": [
				            {
				                "color": "#ffffff"
				            }
				        ]
				    },
				    {
				        "featureType": "road.highway",
				        "elementType": "all",
				        "stylers": [
				            {
				                "visibility": "simplified"
				            }
				        ]
				    },
				    {
				        "featureType": "road.highway",
				        "elementType": "labels",
				        "stylers": [
				            {
				                "visibility": "off"
				            }
				        ]
				    },
				    {
				        "featureType": "road.highway.controlled_access",
				        "elementType": "all",
				        "stylers": [
				            {
				                "visibility": "off"
				            }
				        ]
				    },
				    {
				        "featureType": "road.arterial",
				        "elementType": "geometry",
				        "stylers": [
				            {
				                "hue": "#ffb900"
				            }
				        ]
				    },
				    {
				        "featureType": "road.arterial",
				        "elementType": "geometry.fill",
				        "stylers": [
				            {
				                "visibility": "on"
				            }
				        ]
				    },
				    {
				        "featureType": "road.arterial",
				        "elementType": "geometry.stroke",
				        "stylers": [
				            {
				                "visibility": "on"
				            }
				        ]
				    },
				    {
				        "featureType": "road.arterial",
				        "elementType": "labels",
				        "stylers": [
				            {
				                "hue": "#00c0ff"
				            },
				            {
				                "visibility": "off"
				            }
				        ]
				    },
				    {
				        "featureType": "road.local",
				        "elementType": "all",
				        "stylers": [
				            {
				                "visibility": "simplified"
				            }
				        ]
				    },
				    {
				        "featureType": "water",
				        "elementType": "all",
				        "stylers": [
				            {
				                "saturation": 43
				            },
				            {
				                "lightness": -11
				            },
				            {
				                "hue": "#007bb3"
				            }
				        ]
				    },
				    {
				        "featureType": "water",
				        "elementType": "geometry.fill",
				        "stylers": [
				            {
				                "saturation": "-32"
				            },
				            {
				                "lightness": "-15"
				            },
				            {
				                "gamma": "0.89"
				            }
				        ]
				    }
				]

				map.setOptions({
					styles : styles
				});
			}
			;
			/* end google maps -----------------------------------------------------*/
		});