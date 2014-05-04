var restaurantList;

function addCheckinMarkers() {

	var getCheckinUrl = "test";

	$
			.post(
					getCheckinUrl,
					{},
					function(data, status) {

						restaurantList = jQuery.parseJSON(data);

						getLocation();

					});
}
