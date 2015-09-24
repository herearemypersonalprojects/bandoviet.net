/* Global variables */
var marker;
var map;
var infoWnd = new google.maps.InfoWindow();
var markerBounds = new google.maps.LatLngBounds();
var markerArray = [];

var stationList = [
                   {"latlng":[35.681382,139.766084],name:"Tokyo Station"},
                   {"latlng":[35.630152,139.74044],name:"Shinagawa Station"},
                   {"latlng":[35.507456,139.617585],name:"Shin-Yokohama Station"},
                   {"latlng":[35.25642,139.154904],name:"Odawara Station"},
                   {"latlng":[35.103217,139.07776],name:"Atami Station"},
                   {"latlng":[35.127152,138.910627],name:"Mishima Station"},
                   {"latlng":[35.142015,138.663382],name:"Shin-Fuji Station"},
                   {"latlng":[34.97171,138.38884],name:"Shizuoka Station"},
                   {"latlng":[34.769758,138.014928],name:"Kakegawa Station"},
                   {"latlng":[34.703741,137.734442],name:"Hamamatsu Station"},
                   {"latlng":[34.762811,137.381651],name:"Toyohashi Station"},
                   {"latlng":[34.96897,137.060662],name:"Mikawa-Anjyo Station"},
                   {"latlng":[35.170694,136.881637],name:"Nagoya Station"},
                   {"latlng":[35.315705,136.685593],name:"Gifu-Hashima Station"},
                   {"latlng":[35.314188,136.290488],name:"Yonehara Station"},
                   {"latlng":[34.985458,135.757755],name:"Kyoto Station"},
                   {"latlng":[34.73348,135.500109],name:"Shin-Osaka Station"}
                 ];
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
				$.getScript( 'js/mapstyle.js', function() {
					map.setOptions({
						styles : styles
					});
				});
				
				//Mapping markers on the map
				  var bounds = new google.maps.LatLngBounds();
				  var station, i, latlng;
				  var idx = 0;
				  for (i in stationList) {
				    //Creates a marker
				    station = stationList[i];
				    latlng = new google.maps.LatLng(station.latlng[0], station.latlng[1]);
				    bounds.extend(latlng);
				    var m = createMarker(map, latlng, station.name, idx);
				    
				    //Creates a sidebar button for the marker
				    createMarkerButton(m, idx);
				    idx = idx + 1;
				  }
				  //Fits the map bounds
				  map.fitBounds(bounds);
				
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
						alert.modal('show');
						
					}, "jsonp");
					
					
				});			
			});					
			/* end map controller */
			
			/* map service */
			
			function createMarkerButton(m, idx) {
				var item = document.getElementById(idx);
				  //Trigger a click event to marker when the button is clicked.
				  google.maps.event.addDomListener(item, "click", function(){ //mouseover
				    google.maps.event.trigger(m, "click");		
				  });
			}
			
			function createMarker(map, latlng, title, idx) {
				  //Creates a marker
				  var m = new google.maps.Marker({
				    position : latlng,
				    map : map,
				    title : title,
				    icon: "img/flags/vietnammarker.png",
				  });
				  
				  //The infoWindow is opened when the sidebar button is clicked
				  google.maps.event.addListener(m, 'click', function(){
				    infoWnd.setContent(
				    		'<strong>' + title + '</title>' + 
				    		'<br>'+
				    		'<img class="photo_item" src="img/test.jpg" alt="Item test">');
				    infoWnd.open(map, m);
				    
				    var item = document.getElementById(idx);
		            if (!$(item).hasClass("item_active")) {
		                var lastActive = $(item).closest("#results").children(".item_active");
		                lastActive.removeClass("item_active");
		                $(item).addClass("item_active");		                
		            }
		            window.location.href ="#" + idx;
				  });
				  return m;
			}
			
			function showLocationLatLng(lat, lng) {
			    var me = new google.maps.LatLng(lat, lng);
			    showLocation(me);				
			}
			
			function showLocation(location) {
			    //marker.setAnimation(google.maps.Animation.BOUNCE);
			    marker.setPosition(location);
			    map.setCenter(location);				
			}
			/* end map service */
		});