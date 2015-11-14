/**
 * Blue style, no high way signs, no POIs
 */

var styles = [ {
	"featureType" : "landscape.man_made",
	"elementType" : "geometry.fill",
	"stylers" : [ {
		"color" : "#ece2d9"
	} ]
}, {
	"featureType" : "landscape.natural",
	"elementType" : "all",
	"stylers" : [ {
		"visibility" : "simplified"
	} ]
}, {
	"featureType" : "landscape.natural",
	"elementType" : "geometry.fill",
	"stylers" : [ {
		"visibility" : "off"
	} ]
}, {
	"featureType" : "poi",
	"elementType" : "all",
	"stylers" : [ {
		"visibility" : "off"
	} ]
}, {
	"featureType" : "road",
	"elementType" : "geometry.fill",
	"stylers" : [ {
		"hue" : "#ff0000"
	}, {
		"saturation" : -100
	}, {
		"lightness" : 99
	} ]
}, {
	"featureType" : "road",
	"elementType" : "geometry.stroke",
	"stylers" : [ {
		"color" : "#808080"
	}, {
		"lightness" : 54
	} ]
}, {
	"featureType" : "road",
	"elementType" : "labels.text.fill",
	"stylers" : [ {
		"color" : "#767676"
	} ]
}, {
	"featureType" : "road",
	"elementType" : "labels.text.stroke",
	"stylers" : [ {
		"color" : "#ffffff"
	} ]
}, {
	"featureType" : "road.highway",
	"elementType" : "all",
	"stylers" : [ {
		"visibility" : "simplified"
	} ]
}, {
	"featureType" : "road.highway",
	"elementType" : "labels",
	"stylers" : [ {
		"visibility" : "off"
	} ]
}, {
	"featureType" : "road.highway.controlled_access",
	"elementType" : "all",
	"stylers" : [ {
		"visibility" : "off"
	} ]
}, {
	"featureType" : "road.arterial",
	"elementType" : "geometry",
	"stylers" : [ {
		"hue" : "#ffb900"
	} ]
}, {
	"featureType" : "road.arterial",
	"elementType" : "geometry.fill",
	"stylers" : [ {
		"visibility" : "on"
	} ]
}, {
	"featureType" : "road.arterial",
	"elementType" : "geometry.stroke",
	"stylers" : [ {
		"visibility" : "on"
	} ]
}, {
	"featureType" : "road.arterial",
	"elementType" : "labels",
	"stylers" : [ {
		"hue" : "#00c0ff"
	}, {
		"visibility" : "off"
	} ]
}, {
	"featureType" : "road.local",
	"elementType" : "all",
	"stylers" : [ {
		"visibility" : "simplified"
	} ]
}, {
	"featureType" : "water",
	"elementType" : "all",
	"stylers" : [ {
		"saturation" : 43
	}, {
		"lightness" : -11
	}, {
		"hue" : "#116c9e"
	} ]
}, {
	"featureType" : "water",
	"elementType" : "geometry.fill",
	"stylers" : [ {
		"saturation" : "-62"
	}, {
		"lightness" : "-15"
	}, {
		"gamma" : "0.89"
	} ]
} ]