function removeFromCart(productId, buttonElement) {
	$.ajax({
		type: 'GET',
		url: '/removeFromCart',
		data: { productId: productId },
		success: function(response) {
			console.log('Response:', response);
			var totalPrice = response.totalPrice;
			var newQuantity = response.newQuantity;

			console.log('Total Price:', totalPrice);
			console.log('New Quantity:', newQuantity);

			$('#cartItemCount').text(parseInt(newQuantity));
			$('#totalPrice').text(parseInt(totalPrice) + '.00 VND');

			location.reload();

			var row = $(buttonElement).closest('tr');
			row.fadeOut(300, function() {
				$(this).remove();
			});
		},
		error: function(error) {
			console.error('Error removing product:', error);
		}
	});
}

function sendEmail(productId, buttonElement) {
	$.ajax({
		type: 'GET',
		url: '/send',
		data: { productId: productId },
		
		success: function(response) {
			console.log('Response:', response);
			var totalPrice = response.totalPrice;
			var newQuantity = response.newQuantity;

			console.log('Total Price:', totalPrice);
			console.log('New Quantity:', newQuantity);

			$('#cartItemCount').text(parseInt(newQuantity));
			$('#totalPrice').text(parseInt(totalPrice) + '.00 VND');

			location.reload();

			var row = $(buttonElement).closest('tr');
			row.fadeOut(300, function() {
				$(this).remove();
			});
		},
		
		error: function(error) {
			console.error('Error removing product:', error);
		}
	});
}