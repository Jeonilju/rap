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
						<a href="projectsettings">Project Settings</a>
					</li>
					<li>
						<a href="itemmanagement">Item Management</a>
					</li>
				</ul>
			</div>
			<!-- /#사이드바 -->

			<!-- page wrapper -->
			<div id="page-content-wrapper">
				<div class="container-fluid">
					<!-- Item Categorization -->
					<div class="row">
						<div class="col-lg-12 text-center">
							<BR><BR><BR><BR><BR><BR>
							<h2>Item Management</h2>
							<BR>
						</div>
					</div>
					<div class = "row">
						<div class="dropdown form-group col-lg-2">
							<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								L Category
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li><a href="#">Separated link</a></li>
							</ul>
						</div>
						<div class="dropdown form-group col-lg-2">
							<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								M Category
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li><a href="#">Separated link</a></li>
							</ul>
						</div>
						<div class="dropdown form-group col-lg-2">
							<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								S Category
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li><a href="#">Separated link</a></li>
							</ul>
						</div>
						<div class="col-lg-2 pull-right">
							<button type="button" class="btn" data-toggle="modal" data-target="#AddItemModal">Add an Item</button>
						</div>
					</div>
					<div class="row">
						<div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3 text-center">
											<i class="fa fa-comments fa-5x"></i>
										</div>
										<div class="col-xs-9">
											<div class="huge"><h4>Kakao Emoticon</h4></div>
											<div>Peach Emoticon!</div>
										</div>
									</div>
								</div>
								<div>
									<div class="panel-body">
										<span class="pull-left">Users Purchased : 10,000</span>
										<span class="pull-right">20 Coins</span>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
			<!-- /#page wrapper -->
		</div>
		<!-- /#래퍼 -->
	</div>
	
	<!-- AddItemModal -->
	<div class="modal fade" id="AddItemModal" tabindex="-1" role="dialog" aria-labelledby="AddItemModalLabel">
	  <div class="modal-dialog" role="document">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="myModalLabel">Add an Item</h4>
		  </div>
		  <div class="modal-body">
			<div class="row">
				<div class="col-md-4 portfolio-item">
					<a href="#">
						<img class="img-responsive" src="http://placehold.it/700x400" alt="">
					</a>
					<p class="text-center">Item Image</p>
				</div>
				<div class="col-md-8 portfolio-item">
					<div class = "row">
						<div class="dropdown form-group col-md-4">
							<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								L Category
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li><a href="#">Separated link</a></li>
							</ul>
						</div>
						<div class="dropdown form-group col-md-4">
							<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								M Category
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li><a href="#">Separated link</a></li>
							</ul>
						</div>
						<div class="dropdown form-group col-md-4">
							<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								S Category
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
					<div class="row">
						<label>Item Name</label>
						<input type="text" class="form-control" placeholder="Item Name" id="ItemName" required data-validation-required-message="Please enter Item Name.">
					</div>
					<div class="row">
						<label>Item Description</label>
						<input type="text" class="form-control" placeholder="Item Description" id="ItemDescription" required data-validation-required-message="Please enter Item Description.">
					</div>
					<div class="row">
						<label>Google ID</label>
						<input type="text" class="form-control" placeholder="Google ID" id="GoogleID" required data-validation-required-message="Please enter Google ID.">
					</div>
					<div class="row">
						<label>Item Price</label>
						<input type="text" class="form-control" placeholder="Item Price" id="ItemPrice" required data-validation-required-message="Please enter Item Price.">
					</div>
					<div class="row">
						<label>Coin to use</label>
						<div class="dropdown form-group">
							<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								Coin List
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
				</div>
			</div>
		  </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		</div>
	  </div>
	</div>
</body>

</html>
