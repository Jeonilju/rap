<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="nav.jsp" flush = "false" />
<link href="./resources/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css">

<body>

	<div class="container">
		<div id="wrapper">
			<!-- Sidebar -->
			<div id="sidebar-wrapper">
				<ul class="sidebar-nav">
					<li><br><br><br></li>
					<li class="sidebar-brand">
						<a>
							Promotions
						</a>
					</li>
					<li>
						<a href="#">Project Administration</a>
					</li>
				</ul>
			</div>
			<!-- /#sidebar-wrapper -->



			
			<!--contents-->
			<div id="page-content-wrapper">
				<div class="container-fluid">			
					

					<div class="col-lg-12 text-center">
						<BR><BR><BR><BR><BR><BR>
							<h2>Project Administration</h2>
						</div>
					</div>



					<div class="bootcards-list">
						<div class="panel panel-default">
							<div class="panel-body">
								<form>
									<div class="row">
										<div class="col-lg-9">
											<div class="form-group">
												<input type="text" class="form-control" placeholder="Search">
											</div>
										</div>
										<div class="col-lg-3">



											<button type="button" class="btn btn-success btn-block" data-toggle="modal" data-target="#card_add" href="#">
												<i class="fa fa-plus"></i>
												Add
											</button>
										</div>
									</div>
								</form>
							</div>

							<!--...list markup goes here...-->


							<div class="panel panel-default">
								<div class="panel-heading clearfix">
									<h3 class="panel-title pull-left">2015/05/04 공지사항</h3>
									<i class="fa fa-trash pull-right"></i>
									<i class="fa fa-edit pull-right"  style="margin-right:4px;"></i>
									

								</div>
								<div class="panel-body">
									<div >
										 <img src="" alt="img" width="100" height="100" style="margin-right:10px;">
										
										공지사항을 확인해주세요.
									</div>



								</div>
								<div class="panel-footer" >
									<a class="btn btn-default pull-right" href="#" >
										푸시 알림
									</a>
								</br></br>
							</div>

							<div class="panel panel-default" style="margin-top:10px;">
								<div class="panel-heading clearfix">
									<h3 class="panel-title pull-left">2015/05/05 공지사항</h3>									
									<i class="fa fa-trash pull-right"></i>
									<i class="fa fa-edit pull-right"  style="margin-right:4px;"></i>

								</div>
								<div class="panel-body">
									<p>Card content...</p>
								</div>
								<div class="panel-footer" >
									<a class="btn btn-default pull-right" href="#" >
										푸시 알림
									</a>
								</br></br>
							</div>


						</div>



					</div>
				</div>








			</div>
			<!--/.container-fluid-->
		</div>
		<!--/.contents-->
	</div>
</div>



<!-- AddItemModal -->
<div class="modal fade" id="card_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<!--<div class="modal fade" id="AddItemModal" tabindex="-1" role="dialog" aria-labelledby="AddItemModalLabel">-->
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">Promotion Add</h4>
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

						<div class="row">
							<label>Promotion Name</label>
							<input type="text" class="form-control" placeholder="Item Name" id="ItemName" required data-validation-required-message="Please enter Promotion Name.">
						</div>
						<div class="row">
							<label>Promotion Description</label>
							<input type="text" class="form-control" placeholder="Item Description" id="ItemDescription" required data-validation-required-message="Please enter Promotion Description.">
						</div>
						<div class="row">
								<label>사용자 분류</label>
								<div class="dropdown form-group">
									<button class="btn dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										Select
										<span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
										<li><a href="#">앱 결제 금액</a></li>
									</ul>
								</div>
							</div>

						<div class="row">
								<label>사용자 등급</label>
								<div class="dropdown form-group">
									<button class="btn dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" >
										Select
										<span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
										<li><a href="#">1 등급</a></li>
										<li><a href="#">2 등급</a></li>
										<li><a href="#">3 등급</a></li>
									</ul>
								</div>
						</div>

					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success" data-dismiss="modal">Add</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>





<!-- Modal -->
<div class="modal fade" id="card_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">Promotion Add</h4>
			</div>


			<div class="modal-footer">
				<button type="button" class="btn btn-success" data-dismiss="modal">Add</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>

			</div>
		</div>
	</div>
</div>






</body>


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

</html>
<!-- Header -->
