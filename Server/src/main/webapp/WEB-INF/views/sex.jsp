<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
	<jsp:include page="nav.jsp" flush="false" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>RAP</title>

		<script src="./resources/js/highcharts.js"></script>
		<script src="./resources/js/modules/data.js"></script>
		<script src="./resources/js/modules/exporting.js"></script>

		<!-- Additional files for the Highslide popup effect -->
		<script type="text/javascript" src="http://www.highcharts.com/media/com_demo/highslide-full.min.js"></script>
		<script type="text/javascript" src="http://www.highcharts.com/media/com_demo/highslide.config.js" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="http://www.highcharts.com/media/com_demo/highslide.css" />

		<style type="text/css">
			${
				demo.css
			}
		</style>

		<script type="text/javascript">
			$(function() {
				$("#basicuserinfo").attr('class','accordion-body collapse in');
				$("#userinfo2").attr('class','accordion-body collapse in');
				}
			);
			
			$(document).ready(getSex());

			function getSex() {
				$.ajax({
					url : "sex_db",
					type : "POST",
					dataType : "JSON",
					success : function(data) {
						if (data != null && data != "") {
							var man = data.man;
							var woman = data.woman;
							modify_chart(man,woman);
						}
					},
					error : function(request, status, error) {
						if(request.status == '200'){
							alert("데이터가 존재하지 않습니다.");
						}
						else if (request.status != '0') {
								alert("code : " + request.status + "\r\nmessage : "
								+ request.reponseText + "\r\nerror : " + error);
						}
					}
				});
			}
			
			function modify_chart(man,woman) {
		
				$('#container')
						.highcharts(
								{
									chart : {
										plotBackgroundColor : null,
										plotBorderWidth : null,
										plotShadow : false,
										type : 'pie'
									},
									title : {
										text : ' '
									},
									tooltip : {
										pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
									},
									plotOptions : {
										pie : {
											allowPointSelect : true,
											cursor : 'pointer',
											dataLabels : {
												enabled : true,
												format : '<b>{point.name}</b>: {point.percentage:.1f} %',
												style : {
													color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
															|| 'black'
												}
											}
										}
									},
									series : [ {
										name : "Brands",
										colorByPoint : true,
										data : [ {
											name : "남자",
											y : man
										}, {
											name : "여자",
											y : woman,
											sliced : true,
											selected : true
										}
		
										]
									} ]
								});
			}
		</script>



</head>
	<body id="page-top" class="index">

		<div class="container">
			<div id="wrapper">
				<!--  sidebar-wrapper -->
				<div id="sidebar-wrapper">
					<ul class="sidebar-nav">
						<jsp:include page="projectnav.jsp" flush="false" />
					</ul>
				</div>
				<!--  #sidebar-wrapper -->

				<!--  page-wrapper -->
				<div id="page-content-wrapper">
					<div class="container-fluid">
						<!-- Application Registration -->
						<div class="row">
							<div class="col-lg-12 text-center">
								<BR> <BR> <BR> <BR> <BR> <BR>
								<h2>Sex ratio</h2>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12 text-center">
								<div id="container" style="min-width: 200px; height: 400px; margin: 0 auto"></div>
							</div>
						</div>
					</div>
				</div>
			<!--  #page-wrapper -->
			</div>
		</div>
	</body>
</html>
