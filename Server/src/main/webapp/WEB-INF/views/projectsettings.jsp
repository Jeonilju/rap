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
						<a href="">
							Virtual Store
						</a>
					</li>
					<li>
						<a href="#">Project Settings</a>
					</li>
					<li>
						<a href="ItemManagement.jsp">Item Management</a>
					</li>
				</ul>
			</div>
			<!-- /#사이드바 -->

			<!-- 페이지 컨텐트 -->
			<div id="page-content-wrapper">
				<div class="container-fluid">
					<!-- Item Categorization -->
					<div class="row">
						<div class="col-lg-12 text-center">
							<BR><BR><BR><BR><BR><BR>
							<h2>Item Categorization</h2>
						</div>
					</div>
					<div>
						<div>
							<!-- form -->
							<form class="form-horizontal" name="ItemCategorization" id="ItemCategorization" novalidate>
								<!-- Large Category -->
								<div class="form-group form-inline">
									<div class="col-lg-8">
										<label>Large Category</label>
										<input type="text" class="form-control" placeholder="Large Category" id="CategoryL" required data-validation-required-message="Please enter Large Category.">
									</div>
									<div class="col-lg-2">
										<!--<p class="help-block text-danger"></p>-->
										<button type="submit" class="btn">Register</button>
									</div>
									<div class="dropdown form-group col-lg-2">
										<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
											Dropdown
											<span class="caret"></span>
										</button>
										<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
											<li><a href="#">Action</a></li>
											<li><a href="#">Another action</a></li>
											<li><a href="#">Something else here</a></li>
											<li><a href="#">Separated link</a></li>
										</ul>
									</div>
									<br>
								</div>
								<!-- Medium Category -->
								<div class="row form-group">
									<input type="text" class="form-control" placeholder="Medium Category" id="CategoryM" required data-validation-required-message="Please enter Medium Category.">
									<!--<p class="help-block text-danger"></p>-->
									<button type="submit" class="btn">Register</button>
									<div class="dropdown form-group">
										<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
											Dropdown
											<span class="caret"></span>
										</button>
										<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
											<li><a href="#">Action</a></li>
											<li><a href="#">Another action</a></li>
											<li><a href="#">Something else here</a></li>
											<li><a href="#">Separated link</a></li>
										</ul>
									</div>
								</div>
								<!-- Small Category -->
								<div class="row form-group">
									<input type="text" class="form-control" placeholder="Small Category" id="CategoryS" required data-validation-required-message="Please enter Small Category.">
									<!--<p class="help-block text-danger"></p>-->
									<button type="submit" class="btn">Register</button>
									<div class="dropdown form-group">
										<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
											Dropdown
											<span class="caret"></span>
										</button>
										<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
											<li><a href="#">Action</a></li>
											<li><a href="#">Another action</a></li>
											<li><a href="#">Something else here</a></li>
											<li><a href="#">Separated link</a></li>
										</ul>
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
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="js/classie.js"></script>
    <script src="js/cbpAnimatedHeader.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="js/jqBootstrapValidation.js"></script>
    <script src="js/contact_me.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/freelancer.js"></script>

</body>

</html>
