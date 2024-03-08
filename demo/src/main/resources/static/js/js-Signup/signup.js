document.addEventListener('DOMContentLoaded', function() {
	// Lấy đường dẫn hiện tại
	var currentUrl = window.location.href;

	if (currentUrl.includes('failure')) {
		showErrorAlert();
	}
	if (currentUrl.includes('success')) {
		showSuccessAlert();
	}
});

// Hàm hiển thị thông báo lỗi
function showErrorAlert() {
	Swal.fire({
		icon: 'error',
		title: 'Error',
		text: 'Create account fail'
	}).then((result) => {
		if (result.isConfirmed) {
			window.location.href = '/signup';
		}
	});
}

// Hàm hiển thị thông báo thanh cong
function showSuccessAlert() {
	Swal.fire({
		icon: 'success',
		title: 'Success',
		text: 'Create account successfully',
	}).then((result) => {
		if (result.isConfirmed) {
			window.location.href = '/login';
		}
	});
}