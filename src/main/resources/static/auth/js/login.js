$(document).ready(function() {

	var message = $('#login-form').attr('data-message');

	if (message === 'true') {
		$('#messageModal').modal('show');
	}


	/* Validate form */
	$('#login-form').validate({
		rules: {
			username: "required",
			password: {
				required: true,
				minlength: 3
			}
		},

		messages: {
			username: "Fill in username",
			password: {
				required: "Fill in password",
				minlength: "Password must be at least 3 characters long"
			}
		},

		errorPlacement: function(error, element) {
			var placement = $(element).data('error');
			if (placement) {
				$(placement).append(error)
			} else {
				error.insertAfter(element);
			}
		}

	});
	/* ========================================== */

	/* Remember label click event */
	$('#login-form').on('click', '#remember-label', function() {
		$('#remember-check').trigger('click');
	})
	/* ========================================== */
	
	
	
});