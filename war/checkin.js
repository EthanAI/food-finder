$(document).on("pageinit", function(event) {
alert('test');
	var getCheckinUrl = "test";

	$.post(getCheckinUrl, {}, function(data, status) {
		alert(data);
		restaurantList = jQuery.parseJSON(data);
		
		alert(restaurantList);

	});

});
