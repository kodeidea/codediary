<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<title>Registration Page</title>
		<link href="webjars/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet" />
	</head>
	
	<body>
		<div class="container">
			<div class="span12">
				<form class="form-horizontal" action='' method="POST">
				  <fieldset>
				    <legend class="">Register</legend>
				    <div class="alert alert-danger hide"></div>
				    <div class="control-group">
				      <!-- Username -->
				      <label class="control-label"  for="username">Username</label>
				      <div class="controls">
				        <input type="text" id="username" name="username" placeholder="" class="input-xlarge" />
				        <p class="help-block">Username can contain any letters or numbers, without spaces</p>
				        <p id="usernameError" class="alert alert-danger col-sm-4" style="display:none"></p>
				      </div>
				    </div>
				 
				    <div class="control-group">
				      <!-- E-mail -->
				      <label class="control-label" for="email">E-mail</label>
				      <div class="controls">
				        <input type="text" id="email" name="email" placeholder="" class="input-xlarge" />
				        <p class="help-block">Please provide your E-mail</p>
				        <p id="emailError" class="alert alert-danger col-sm-4" style="display:none"></p>
				      </div>
				    </div>
				 
				    <div class="control-group">
				      <!-- Password-->
				      <label class="control-label" for="password">Password</label>
				      <div class="controls">
				        <input type="password" id="password" name="password" placeholder="" class="input-xlarge" />
				        <p class="help-block">Password should be at least 4 characters</p>
				        <p id="passwordError" class="alert alert-danger col-sm-4" style="display:none"></p>
				      </div>
				    </div>
				 
				    <div class="control-group">
				      <!-- Password -->
				      <label class="control-label"  for="password_confirm">Password (Confirm)</label>
				      <div class="controls">
				        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="" class="input-xlarge" />
				        <p class="help-block">Please confirm password</p>
				        <p id="confirmPasswordError" class="alert alert-danger col-sm-4" style="display:none"></p>
				      </div>
				    </div>
				 
				    <div class="control-group">
				      <!-- Button -->
				      <div class="controls">
				        <button class="btn btn-success">Register</button>
				      </div>
				    </div>
				  </fieldset>
				</form>
			</div>	
		</div>
		<script src="webjars/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<script src="webjars/jquery/2.1.3/jquery.min.js"></script>
		<script type="text/javascript">
			$("form").submit(function(event) {
				register(event);
			});
	
			var register = function(event) {
				event.preventDefault();
				var formData = $('form').serialize();
				$(".alert-danger").addClass("hide");
				$.post("/account/register", formData, function(data) {
					if (data.message == "success") {
						window.location.href = "/home";
					} else {
						$(".alert-danger").removeClass("hide");
						var errors = $.parseJSON(data.message);
						var messages = "";
						$.each(errors, function(index, item) {
							messages += item.field + " : " + item.defaultMessage + "<br/>";
						});
						
						$(".alert-danger").html(messages);
					}
	
				}).fail(function(data) {
							alert(data.responseJSON.error);
							var errors = $.parseJSON(data.responseJSON.message);
							var messages;
							$.each(errors, function(index, item) {
								messages += item.defaultMessage + "<br />";
							});
							
							errors = $.parseJSON(data.responseJSON.error);
							$.each(errors, function(index, item) {
									messages += item.defaultMessage + "<br />"
							});
						});
			};
		</script>
</body>
</html>