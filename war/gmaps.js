var map;
var infowindow;
var geoPositionLatitude;
var geoPositionLongitude;

function initialize() {
	

/*	var pyrmont = new google.maps.LatLng(-33.8665433, 151.1956316);

	map = new google.maps.Map(document.getElementById('map-canvas'), {
		center : pyrmont,
		zoom : 15
	});

	var request = {
		location : pyrmont,
		radius : 500,
		types : [ 'store' ]
	};
	infowindow = new google.maps.InfoWindow();
	var service = new google.maps.places.PlacesService(map);
	service.nearbySearch(request, callback);*/
}

function callback(results, status) {

	if (status == google.maps.places.PlacesServiceStatus.OK) {
		for (var i = 0; i < results.length; i++) {
			createMarker(results[i]);
		}
	}
}

function createMarker(place) {
	var placeLoc = place.geometry.location;
	var marker = new google.maps.Marker({
		map : map,
		position : place.geometry.location
	});
	google.maps.event.addListener(marker, 'click', function() {
		infowindow.setContent(place.name);
		infowindow.open(map, this);
	});
}

function getLocation() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition);
	}
}
function showPosition(position) {
	// x.innerHTML = "Latitude: " + position.coords.latitude
	// + "<br>Longitude: " + position.coords.longitude;

	var pyrmont = new google.maps.LatLng(position.coords.latitude,
			position.coords.longitude);

	map = new google.maps.Map(document.getElementById('map-canvas'), {
		center : pyrmont,
		zoom : 17
	});
	
	var marker = new google.maps.Marker({
		map : map,
		position : pyrmont,
		title: "You are Here!",
		icon: "http://labs.google.com/ridefinder/images/mm_20_blue.png"
	});

	var request = {
		location : pyrmont,
		radius : 500,
		types : [ 'restaurant' ]
	};
	infowindow = new google.maps.InfoWindow();
	var service = new google.maps.places.PlacesService(map);
	service.nearbySearch(request, callback);
}

google.maps.event.addDomListener(window, 'load', initialize);
getLocation();
