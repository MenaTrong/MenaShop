function addToCart(productId, username) {
	const quantityInput = document.getElementById("quantityInput");
	const selectedSizeElement = document.querySelector("#sizeList .one.selected");
	if (selectedSizeElement) {
		const selectedSize = selectedSizeElement.getAttribute("data-size");
		$.ajax({
			type: 'GET',
			url: '/addcart',
			data: {
				id: productId,
				username: username,
				quantity: quantityInput.value,
				size: selectedSize
			},
			success: function(response) {
				console.log('Added to cart successfully');

				// Update the quantity on the UI
				var newQuantity = parseInt(response);
				$('#cartItemCount').text(newQuantity);

				Swal.fire({
					icon: 'success',
					title: 'Added to cart successfully',
					showConfirmButton: false,
					timer: 2000 // Auto close after 2 seconds
				});
				// Perform other actions if needed
			},
			error: function(error) {
				console.error('Error adding to cart:', error.responseText);
				Swal.fire({
					icon: 'error',
					title: 'Error adding to cart',
					text: 'Added to cart NOT successfully'
				});
			}
		});
	} else {
		Swal.fire({
			icon: 'error',
			title: 'Error',
			text: 'Please select a size before proceeding to Add To Cart.'
		});
	}
}

var cartItemCount = 0;

// Hàm cập nhật số lượng hàng trong giỏ
function updateCartItemCount(count) {
	cartItemCount = count;
	$("#cartItemCount").text(cartItemCount);
}

$(document).ready(function() {
	var isSizeSelected = false;
	// Sử dụng jQuery để xử lý sự kiện click
	$('#sizeList a').click(function() {
		// Loại bỏ class 'selected' từ tất cả các phần tử
		$('#sizeList a').removeClass('selected');

		// Thêm class 'selected' cho phần tử được click
		$(this).addClass('selected');
		var selectedSize = $(this).data('size');
		$('#selectedSizeSpan').text('Size: ' + selectedSize + ', ');
		$('#sizeBuy').val(selectedSize);
		isSizeSelected = true;
		return false;
	});

	// Xử lý sự kiện khi click vào nút "Buy Now"
	$('#openDialogButton').click(function() {
		// Kiểm tra nếu không có size được chọn, hiển thị thông báo và ngăn chặn mở modal
		if (!isSizeSelected) {
			Swal.fire({
				icon: 'error',
				title: 'Error',
				text: 'Please select a size before proceeding to Buy Now.'
			});
			modal.style.display = 'none';
			document.body.classList.remove('modal-open');
			var modalBackdrop = document.querySelector('.modal-backdrop');
			if (modalBackdrop) {
				modalBackdrop.style.display = 'none';
			}
			return false;
		}
		// Nếu có size được chọn, mở modal
		$('#checkoutModal').modal('show');
	});
});