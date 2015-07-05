<!DOCTYPE html>
<html lang="en">
<!-- 상단 네비게이션 바 인클루드 -->
<jsp:include page="nav.jsp" flush = "false" />

<body id="page-top" class="index">
	<div class="container">
		<!-- 래퍼 -->
		<div id="wrapper">
			<!-- 사이드바 -->
			<div id="sidebar-wrapper">
				<ul class="sidebar-nav">
					<li><br><br><br></li>
					<li class="sidebar-brand">
						<a href="#">
							Register
						</a>
					</li>
					<li>
						<a href="register">Application Registration</a>
					</li>
				</ul>
			</div>
			<!-- /#사이드바 -->

			<!-- 페이지 컨텐트 -->
			<div id="page-content-wrapper">
				<div class="container-fluid">
					<!-- Application Registration -->
					<div class="row">
						<div class="col-lg-12 text-center">
							<BR><BR><BR><BR><BR><BR>
							<h2>Application Registration</h2>
							<hr class="star-primary">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
							<!-- form -->
							<form name="AppRegister" id="AppRegister" novalidate>
								<!-- Application Name -->
								<div class="row control-group">
									<div class="form-group col-xs-12 floating-label-form-group controls">
										<label>Application Name</label>
										<input type="text" class="form-control" placeholder="Application Name" id="AppName" required data-validation-required-message="Please enter Application name.">
										<p class="help-block text-danger"></p>
									</div>
								</div>
								<!-- Summary -->
								<div class="row control-group">
									<div class="form-group col-xs-12 floating-label-form-group controls">
										<label>Summary</label>
										<input type="text" class="form-control" placeholder="Summary" id="Summary" required data-validation-required-message="Please enter Summary.">
										<p class="help-block text-danger"></p>
									</div>
								</div>
								<!-- Description -->
								<div class="row control-group">
									<div class="form-group col-xs-12 floating-label-form-group controls">
										<label>Description</label>
										<textarea rows="5" class="form-control" placeholder="Description" id="Description" required data-validation-required-message="Please enter Description."></textarea>
										<p class="help-block text-danger"></p>
									</div>
								</div>
								<br>
								<!-- Register button -->
								<div id="success"></div>
								<div class="row">
									<div class="form-group col-xs-12">
										<center><button type="submit" class="btn btn-success btn-lg">Register</button></center>
									</div>
								</div>
							</form>
							<!-- /#form -->
						</div>
					</div>
				</div>
			</div>
			<!-- /#페이지 컨텐트 -->

		</div>
		<!-- /#래퍼 -->
		
	</div>
	
    <!-- jQuery -->
    <script src="./resources/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="./resources/js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="./resources/js/classie.js"></script>
    <script src="./resources/js/cbpAnimatedHeader.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="./resources/js/jqBootstrapValidation.js"></script>
    <script src="./resources/js/contact_me.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="./resources/js/freelancer.js"></script>

</body>

</html>
