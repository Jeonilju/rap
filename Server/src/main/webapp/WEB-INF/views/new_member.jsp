<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<jsp:include page="nav.jsp" flush = "false" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>신규 사용자 통계</title>
		
		<script src="./resources/js/highcharts.js"></script>
		<script src="./resources/js/modules/data.js"></script>
		<script src="./resources/js/modules/exporting.js"></script>
		
		<!-- Additional files for the Highslide popup effect -->
		<script type="text/javascript" src="http://www.highcharts.com/media/com_demo/highslide-full.min.js"></script>
		<script type="text/javascript" src="http://www.highcharts.com/media/com_demo/highslide.config.js" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="http://www.highcharts.com/media/com_demo/highslide.css" />
		
		<style type="text/css">
			${demo.css}
		</style>
		
		<script type="text/javascript">
			$(function() {
				$("#basicuserinfo").attr('class','accordion-body collapse in');
				$("#appinfo2").attr('class','accordion-body collapse in');
				getoperation_count();
			});
			
			function getoperation_count() {
				var param = "during=" + document.getElementById('during').value;
		
				$.ajax({
					url : "new_member_db",
					type : "POST",
					data : param,
					dataType : "JSON",
					success : function(data) {
		
						if (data != null && data != "") {
							var result = data.result;
							modify_chart(result);
						}
					},
		
					error : function(request, status, error) {
						if(request.status != '200'){
							alert("데이터가 존재하지 않습니다.");
						}
						else if (request.status != '0') {
							alert("code : " + request.status + "\r\nmessage : "
									+ request.reponseText + "\r\nerror : " + error);
						}
					}
				});
			}

			function modify_chart(result) {
				$('#container').highcharts({
					chart : {
						type : 'column'
					},
					title : {
						text : ' '
					},
					subtitle : {
		
					},
					xAxis : {
						type : 'category',
						labels : {
							rotation : -45,
							style : {
								fontSize : '13px',
								fontFamily : 'Verdana, sans-serif'
							}
						}
					},
					yAxis : {
						min : 0,
						title : {
							text : 'Counts'
						}
					},
					legend : {
						enabled : false
					},
					tooltip : {
						pointFormat : '<b>{point.y:.1f} </b>'
					},
					series : [ {
						name : 'Count',
						data : result,
						dataLabels : {
							enabled : true,
							rotation : -45,
							color : '#FFFFFF',
							align : 'right',
							format : '{point.y:.1f}', // one decimal
							y : -15, // 10 pixels down from the top
							style : {
								fontSize : '13px',
								fontFamily : 'Verdana, sans-serif'
							}
						}
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
								<BR><BR><BR><BR><BR><BR>
								<h2>New user</h2>
							</div>
						</div>
						<div class="row">
							<div class="pull-right" >
								<select class="selectpicker" id="during" name="during"  onchange="getoperation_count()">
									<option value="0">최근 7일</option>
									<option value="1">최근 14일</option>
									<option value="2">최근 21일</option>
									<option value="3">최근 30일</option>
									<option value="4">최근 3개월</option>
									<option value="5">최근 6개월</option>
								</select>
							</div>
							<br>
							<div id="container" style="min-width: 200px; height: 400px; margin: 0 auto" ></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
