$(document).on("pageinit", function(event) {

	var getCheckinUrl = "test";

	$.post(getCheckinUrl, {}, function(data, status) {

		restaurantList = jQuery.parseJSON(data);
		
		alert(restaurantList);

	});

});
