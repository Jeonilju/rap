<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="en">
<!-- 네비게이션바 인클루드 -->
<jsp:include page="nav.jsp" flush="false" />


<body id="page-top" class="index">
	<div class="container">
		<!-- wrapper -->
		<div id="wrapper">
			<!-- sidebar-wrapper -->
			<div id="sidebar-wrapper">
				<ul class="sidebar-nav">
					<li><br> <br> <br></li>
					<li class="sidebar-brand"><a href="#"> Virtual Store </a></li>
					<li><a href="#">Item Categorization</a></li>
					<li><a href="itemmanagement">Item Management</a></li>
				</ul>
			</div>
			<!-- /#sidebar-wrapper -->

			<!-- page-content-wrapper -->
			<div id="page-content-wrapper">
				<div class="container-fluid">
					<!-- Item Categorization -->
					<div class="row">
						<div class="col-lg-12 text-center">
							<BR> <BR> <BR> <BR> <BR> <BR>
							<h2>Item Categorization</h2>
						</div>
					</div>
					<div class="row">
						<!-- form -->
						<form class="form-inline" name="ItemCategorization"
							id="ItemCategorization" novalidate>
							<!-- Large Category -->
							<div>
								<div class="form-group" style="padding:20px">
									<label>Large Category</label> 
									<input type="text"
										class="form-control" placeholder="Large Category"
										id="CategoryL" required
										data-validation-required-message="Please enter Large Category." style="width:300px">
								</div>
								<div class="form-group">
									<button class="btn btn-default dropdown-toggle" type="button"
										id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="true">
										Dropdown <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
										<li><a href="#">Action</a></li>
										<li><a href="#">Another action</a></li>
										<li><a href="#">Something else here</a></li>
										<li><a href="#">Separated link</a></li>
									</ul>
								</div>
								<button type="submit" class="btn">Register</button>
							</div>
							<br>
						</form>
					</div>
			<!-- /#form -->
		</div>
	</div>
	</div>
	</div>
	<!-- /#page-content-wrapper -->

	</div>
	<!-- /#ëí¼ -->

	</div>
</body>

</html>
