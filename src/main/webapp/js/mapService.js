/**
 * Map Service provides methods to work with the Google map
 */

			/* map service for display results*/
			
			function createMarkerButton(m, idx) {
				var place = document.getElementById("item"+idx);
				var item = document.getElementById(idx);
				  //Trigger a click event to marker when the button is clicked.
				  google.maps.event.addDomListener(place, "click", function(){ //mouseover
					  
					  createInfoBox(item, m);
					  /*
					    infoWnd.setContent(
					    		'<span><b>' + $(item).data('title') + '</b></span>' + 
					    		'<br>'+
					    		'<img class="img-rounded photo_item" src="' + $(item).data('img') + '" alt="Photo">' + 
					    		'<br>'+
					    		'<span><b>' + bds_lang.GoogleMaps.Address + ' : </b>' + $(item).data('address') + '</span>');
					    				    
					    infoWnd.open(map, m);		
					   */			 
					  if (map.getZoom() < detailZoom) {
						  map.setZoom(detailZoom);
					  }
					  var latlng = new google.maps.LatLng(poiList[idx].lat, poiList[idx].lng);
					  map.setCenter(latlng);	
				  });
				  
				  google.maps.event.addDomListener(place, "mouseover", function(){ //
					  createInfoBox(item, m);
					  /*
					    infoWnd.setContent(
					    		'<span><b>' + $(item).data('title') + '</b></span>' + 
					    		'<br>'+
					    		'<img class="img-rounded photo_item" src="' + $(item).data('img') + '" alt="Photo">' + 
					    		'<br>'+
					    		'<span><b>' + bds_lang.GoogleMaps.Address + ' : </b>' + $(item).data('address') + '</span>');
					    infoWnd.open(map, m);	
					    */
				  });
			}
			
			function createMarker(map, latlng, title, idx) {
				var item = document.getElementById(idx);
				var place = document.getElementById("item"+idx);
				  //Creates a marker
				var iconImage = "/img/flags/vietnammarker.png"; 
				  var m = new google.maps.Marker({
					    position : latlng,
					    map : map,
					    title : title,
					    icon: iconImage,
					  });
				/*
				  if ((poiList[idx].type == 'INDIVIDUAL' ||  poiList[idx].type == 'FRIENDSMAP')&&  poiList[idx].iconPath) {
					var icon = {
						    url: poiList[idx].iconPath
						};
					var i = new Image();
					i.src = poiList[idx].iconPath;

					i.onload = function () {
					    m.setIcon(icon); //If icon found go ahead and show it
					}
					i.onerror = function () {
					    marker.setIcon(null); //This displays brick colored standard marker icon in case image is not found.
					}
				} */

				  //The infoWnd is opened when the sidebar button is clicked
				  google.maps.event.addListener(m, 'click', function(){
					  google.maps.event.trigger(m, "mouseover");	
					  if (map.getZoom() < detailZoom) {
						  map.setZoom(detailZoom);
					  }
					  map.setCenter(m.position);
					  window.location.href ="#item" + idx; 
				  });
				 
				  google.maps.event.addListener(m, 'mouseover', function(){
					  createInfoBox(item, m);
			            /*
				    infoWnd.setContent(
				    		'<span><b>' + title + '</b></span>' + 
				    		'<br>'+
				    		'<img class="img-rounded photo_item" src="' + $(item).data('img') + '" alt="Photo">' + 
				    		'<br>'+
				    		'<span><b>' + bds_lang.GoogleMaps.Address + ' : </b>' + $(item).data('address') + '</span>');
				    infoWnd.open(map, m);
				    */
				    
		            if (!$(place).hasClass("item_active")) {
		                var lastActive = $(place).closest("#left").children(".item_active");
		                lastActive.removeClass("item_active");
		                $(place).addClass("item_active");		                
		            }
		            
				  });
				  return m;
			}
			
			function createInfoBox(item, marker) {
				
				 infoWnd.setContent(
				    		'<span><b>' + $(item).data('title') + '</b></span>' + 
				    		'<br>'+
				    		'<span>'+ $(item).data('address') + '</span>');
				 infoWnd.open(map, marker);
				 /*   
				  
	            var infoboxContent = document.createElement("div");
	            var infoboxOptions = {
	                content: infoboxContent,
	                disableAutoPan: false,
	                pixelOffset: new google.maps.Size(-18, -42),
	                zIndex: null,
	                alignBottom: true,
	                boxClass: "infobox",
	                enableEventPropagation: true,
	                closeBoxMargin: "0px 0px -30px 0px",
	                closeBoxURL: "/img/close.png",
	                infoBoxClearance: new google.maps.Size(1, 1)
	            };
	            infoboxContent.innerHTML = 
	                '<div class="infobox">' +
	                '<div class="inner">' +
	                    '<div class="image">' +
	                        '<div class="overlay">' +
	                            '<div class="wrapper">' +
	                                '<a href="#" onclick="javascript:quickView('+$(item).attr('id')+');"  class="quick-view" data-toggle="modal" data-target="#modal" id="'+$(item).data('id')+'">Xem nhanh</a>' +
	                                '<hr>' +
	                                '<a href="/place/'+$(item).data('title')+'/'+$(item).data('id')+'" class="detail">Xem chi tiết</a>' +
	                            '</div>' +
	                        '</div>' +
	                        '<a href="/place/'+$(item).data('title')+'/'+$(item).data('id')+'" class="description">' +
	                            '<div class="meta">' +			                                
	                                '<h2>'+$(item).data('title')+'</h2>' +
	                                '<figure>'+$(item).data('address')+'</figure>' +
	                                '<i class="fa fa-angle-right"></i>' +
	                            '</div>' +
	                        '</a>' +
	                        '<img src="'+$(item).data('img')+'">' +
	                    '</div>' +
	                '</div>' +
	            '</div>';
	            
	         
	            if (infobox != null) infobox.close();
	            infobox = new InfoBox(infoboxOptions);
	            infobox.open(map, marker);
	            infobox.setOptions({ boxClass:'fade-in-marker'});		
	            
	            		*/
			}
			
			
			function showLocationLatLng(lat, lng) {
			    var me = new google.maps.LatLng(lat, lng);
			    showLocation1(me);				
			}
			
			function showLocation1(location) {
			    //marker.setAnimation(google.maps.Animation.BOUNCE);
				
			    marker.setPosition(location);
			    map.setCenter(location);			
			    marker.setVisible(true);
			}
			
			function SetMapAddress(address) {  // "London, UK" for example 
				   var geocoder = new google.maps.Geocoder();
				   if (geocoder) {
				      geocoder.geocode({ 'address': address }, function (results, status) {
				        if (status == google.maps.GeocoderStatus.OK) {
				          map.fitBounds(results[0].geometry.viewport);				          
				        }
				      });
				   }
				 }
			// START LOCATION WIHT LATLNG
			  function codeLatLng(lat, lng) {
				    var latlng = new google.maps.LatLng(lat, lng);
				    geocoder.geocode({'latLng': latlng}, function(results, status) {
			            if (status == google.maps.GeocoderStatus.OK) {
			            	
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
			                document.getElementById('locationSearch').value = addressReturn;
			                document.getElementById('cityLat').value = lat;
			                document.getElementById('cityLng').value = lng;
			                getCity(results);	
			                if (infoWnd != null) {
			                	infoWnd.setContent(address);
			                	infoWnd.open(map, marker);
			                }
			                
			            } else {
			            	alert("The given address is not found. Please correct it!");
			                console.log("Geocode was not successful for the following reason: " + status);
			            }
			        });
			  }
			// START LOCATION WITH ADDRESS
			function showLocation(address) {
			    if (address != null && address != '') {
			        var add = address.split(',');
			        address = add.join(',');

			        if (marker != null) marker.setMap(null);
			        geocoder.geocode({'address': address}, function (results, status) {
			        	
			            if (status == google.maps.GeocoderStatus.OK) {
			            	
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
			                document.getElementById('locationSearch').value = addressReturn;
			                document.getElementById('cityLat').value = lat;
			                document.getElementById('cityLng').value = lng;
			                getCity(results);	
			                if (infoWnd != null) {
			                	infoWnd.setContent(address);
			                	infoWnd.open(map, marker);
			                }
			            } else {
			            	alert("The given address is not found. Please correct it!");
			                console.log("Geocode was not successful for the following reason: " + status);
			            }
			        });
			    } else {
			        //alert(bds_lang.GoogleMaps.AddressIncorrect);
			    }
			} // END SHOW LOCATION WITH ADDRESS
			// [START getCity]
			function getCity(results) {
			    // RESET
			    $('#countrySearch').val("");
			    $('#citySearch').val("");
		   
			    //break down the three dimensional array into simpler arrays
			    for (i = 0; i < results.length; ++i) {
			        var super_var1 = results[i].address_components;
			        for (j = 0; j < super_var1.length; ++j) {
			            var super_var2 = super_var1[j].types;
			            for (k = 0; k < super_var2.length; ++k) {  //console.log(super_var2[k] + ":" + super_var1[j].short_name + ":" + super_var1[j].long_name);
			                //find city
			                if (super_var2[k] == "locality" && $('#citySearch').val() == "") {
			                    //put the city name in the form
			                    $('#citySearch').val(super_var1[j].long_name);
			                }
			                //find county
			                if (super_var2[k] == "country" && $('#countrySearch').val() == "") {
			                    //put the county name in the form
			                    $('#countrySearch').val(super_var1[j].short_name); //alert(super_var1[j].long_name);alert(super_var1[j].short_name);
			                    searchByKeywords($('#keywords').val(), $('#cityLat').val(), $('#cityLng').val(), $('#countrySearch').val(), $('#locationSearch').val());
			                }
			 
			            }
			        }
			    }
			} // [START getCity]
			/* end map service to display results */	