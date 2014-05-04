function addCheckinMarkers() {

	var getCheckinUrl = "test";

	$
			.post(
					getCheckinUrl,
					{},
					function(data, status) {

						restaurantList = jQuery.parseJSON(data);

						$
								.each(
										restaurantList,
										function(key, val) {

											var point = new google.maps.LatLng(
													val.latitude, val.longitude);

											var marker = new google.maps.Marker(
													{
														map : map,
														position : point,
														title : val.restaurantName,
														icon : "http://maps.gstatic.com/intl/en_ALL/mapfiles/drag_cross_67_16.png"
													});

										});

					});
}
