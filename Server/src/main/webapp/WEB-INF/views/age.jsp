<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
	<jsp:include page="nav.jsp" flush="false" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>RAP</title>

		<script src="./resources/js/highcharts.js"></script>
		<script src="./resources/js/modules/data.js"></script>
		<script src="./resources/js/modules/exporting.js"></script>
	
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
			});
			$(document).ready(getAge());

			function getAge() {
				$.ajax({
					url : "age_db",
					type : "POST",
					dataType : "JSON",
					success : function(data) {
	
						if (data != null && data != "") {
	
							var baby=data.baby;
							var ten=data.ten;
							var twenty=data.twenty;
							var thirty=data.thirty;
							var forty=data.forty;
							var old=data.old;
							modify_chart(baby,ten,twenty,thirty,forty,old);
	
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
		
			function modify_chart(baby,ten,twenty,thirty,forty,old) {
			    $('#container').highcharts({
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: '연령 분포'
			        },
			        
			        xAxis: {
			            type: 'category',
			            labels: {
			                rotation: -45,
			                style: {
			                    fontSize: '13px',
			                    fontFamily: 'Verdana, sans-serif'
			                }
			            }
			        },
			        yAxis: {
			            min: 0,
			            title: {
			                text: 'Counts'
			            }
			        },
			        legend: {
			            enabled: false
			        },
			        tooltip: {
			            pointFormat: 'Counts : <b>{point.y:.1f} </b>'
			        },
			        series: [{
			            name: 'Population',
			            data: [
							['10s 이하', baby],   
			                ['10s', ten],
			                ['20s', twenty],
			                ['30s', thirty],
			                ['40s', forty],
			                ['50s 이상', old]
			            ],
			            dataLabels: {
			                enabled: true,
			                rotation: -90,
			                color: '#FFFFFF',
			                align: 'right',
			                format: '{point.y:.1f}', // one decimal
			                y: 10, // 10 pixels down from the top
			                style: {
			                    fontSize: '13px',
			                    fontFamily: 'Verdana, sans-serif'
			                }
			            }
			        }]
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
	
				<div id="page-content-wrapper">
					<div class="container-fluid">
						<!-- Application Registration -->
						<div class="row">
							<div class="col-lg-12 text-center">
								<BR><BR><BR><BR><BR><BR>
								<h2>Age ratio</h2>
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
