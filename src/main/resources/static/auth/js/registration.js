$(document).ready(function() {

	const $form = $('#registration-form');

	$form.on('change', '#username', function() {
		var username = $(this).val(), error = $('#usernameError');
		if (username.length === 0) {
			status.empty();
			return;
		}
		setTimeout(function() {
			$.ajax({
				type: 'GET',
				contentType: 'application/json;charset=UTF-8',
				url: 'registration/isUnique/' + username,
				dataType: "json",
				data: username
			}).done(function(data) {
				if (data === true) {
					$('#username').focus();
					error.html('This username already exists ');
					$('#btn-submit').prop('disabled', true);
				} else {
					error.empty();
					$('#btn-submit').prop('disabled', false);
				}
			});
		}, 150);
	});

	/*
		('#registration-form').on('submit', function(e) {
		e.preventDefault();

		var user = {
			username: $('input[name = username]').val(),
			password: $('input[name = password]').val(),
			firstName: $('input[name = firstName]').val(),
			lastName: $('input[name = lastName]').val(),
			email: $('input[name = email]').val(),
			phoneNum: $('input[name = phoneNum]').val()
		}
		var token = $("input[name='_csrf']").val();

		$.ajax({
			method: 'POST',
			contentType: 'application/json; charset=UTF-8',
			url: 'registration/check',
			data: JSON.stringify(user),
			encode: true,
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-Token', token);
			}
		})
		.done(function(data) {
			console.log(true);
		})
		;
	}); 
	*/

	$.validator.addMethod("regex", function(value, element, regexpr) {
		if (value.length === 0) {
			return true;
		}
		return regexpr.test(value);
	});


	$form.validate({
		rules: {
			username: {
				required: true,
				minlength: 6
			},
			firstName: "required",
			lastName: "required",
			email: {
				required: true,
				email: true
			},
			phoneNum: {
				regex: /[0-9]{9,10}/
			},
			password: {
				required: true,
				minlength: 6
			},
			matchingPassword: {
				required: true,
				equalTo: 'input[name=password]'
			}
		},

		messages: {
			username: {
				required: "Username is required",
				minlength: "Username must be at least 6 characters long"
			},
			firstName: "First name is required",
			lastName: "Last name is required",
			email: {
				required: "Email is required",
				email: "Invalid email address"
			},
			phoneNum: "Invalid phone number",
			password: {
				required: "Password is required",
				minlength: "Password must be at least 6 characters long"
			},
			matchingPassword: {
				required: "Repeat password is required",
				equalTo: "Password fields must be matched"
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


});