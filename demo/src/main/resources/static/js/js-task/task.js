document.getElementById("cartIcon").addEventListener("click", function() {
	window.location.href = "cart";
});
document.getElementById("infUserIcon").addEventListener("click", function() {
	window.location.href = "information";
});

//Close pay cart
// Lấy modal và nút đóng
var modal = document.getElementById('checkoutModal');
var closeButton = document.querySelector('#checkoutModal .exit-button');
var openButton = document.getElementById('openDialogButton');

// Hàm đóng modal và overlay
function closeModal() {
	modal.style.display = 'none';
	document.body.classList.remove('modal-open');
	var modalBackdrop = document.querySelector('.modal-backdrop');
	if (modalBackdrop) {
		modalBackdrop.style.display = 'none';
	}
}

// Hàm mở lại modal
function openModal() {
	modal.style.display = 'block';
	document.body.classList.add('modal-open');
	var modalBackdrop = document.querySelector('.modal-backdrop');
	if (modalBackdrop) {
		modalBackdrop.style.display = 'block';
	}
}

// Thêm sự kiện click cho nút đóng
if (closeButton) {
	closeButton.addEventListener('click', function() {
		closeModal();
	});
}

// Thêm sự kiện click cho trang chính
document.addEventListener('click', function(event) {
	if (event.target === modal) {
		closeModal();
	}
});

// Thêm sự kiện click cho nút mở dialog
if (openButton) {
	openButton.addEventListener('click', function() {
		openModal();
	});
}


// Lấy phần tử dropdown toggle và dropdown content
const dropdownToggle = document.getElementById('dropdownToggle');
const dropdownContent = document.getElementById('dropdowninpaycart');

// Thêm sự kiện click để mở hoặc đóng dropdown
dropdownToggle.addEventListener('click', function(event) {
	event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ a (nếu có)

	// Toggle class 'active' để hiển thị hoặc ẩn dropdown content
	dropdownToggle.classList.toggle('active');

	// Nếu muốn focus vào dropdown content sau khi mở
	if (dropdownToggle.classList.contains('active')) {
		dropdownContent.focus();
	}
});

// Chuyển trang trong informationuser
document.getElementById("iconhistory").addEventListener("click", function() {
	window.location.href = "cart"; // Chuyển hướng đến trang "cart"
});

function changeImage(imageUrl) {
	var productImage = document.getElementById('productImage');
	productImage.src = imageUrl;
}

// Lắng nghe sự kiện click vào biểu tượng search
document.getElementById('searchIcon').addEventListener('click', function() {
	toggleDropdown();
});

function toggleDropdown() {
	var dropdown = document.getElementById("dropdown");
	dropdown.classList.toggle("show");
}