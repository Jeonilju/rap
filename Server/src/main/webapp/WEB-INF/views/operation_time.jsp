<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<jsp:include page="nav.jsp" flush = "false" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highcharts Example</title>

		<style type="text/css">
${demo.css}
		</style>
		
		
		
		<script type="text/javascript">
		$(function() {
			$("#basicuserinfo").attr('class','accordion-body collapse in');
			$("#appinfo2").attr('class','accordion-body collapse in');
			});


		function getoperation_time() {
			var param = "start=" + document.getElementById('Start').value;
			
			$.ajax({
				url : "operation_time_db",
				type : "POST",
				data : param,
				dataType : "JSON",
				success : function(data) {

					if (data != null && data != "") {

					//	var start_time=data.start_time;
						//var count=data.count;
						var result=data.result;
					//	alert(result.toString());
						modify_chart(result);
					}
				},

				error : function(request, status, error) {
					if (request.status != '0') {
						alert("code : " + request.status + "\r\nmessage : "
								+ request.reponseText + "\r\nerror : " + error);
					}
				}
			});
		}

		function modify_chart(result) {
			 $('#container').highcharts({
			        chart: {
			            type: 'column'
			        },
			        title: {
			          	text:' '
			        },
			        subtitle: {    },
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
			                text: 'Count'
			            }
			        },
			        legend: {
			            enabled: false
			        },
			        tooltip: {
			            pointFormat: '<b>{point.y:.1f}</b>'
			        },
			        series: [{
			            name: 'Population',
			            data: result,
			            dataLabels: {
			                enabled: true,
			                rotation: -45,
			                color: '#FFFFFF',
			                align: 'right',
			                format: '{point.y:.1f}', // one decimal
			                y: -15, // 10 pixels down from the top
			                style: {
			                    fontSize: '13px',
			                    fontFamily: 'Verdana, sans-serif'
			                }
			            }
			        }]
			    });
			
			
		    /* $('#container').highcharts({
		        chart: {
		            type: 'column'
		        },
		        title: {
		            text: 'title'
		        },
		        xAxis: {
		            categories: start_time
		        },
		        yAxis: {
		            min: 0,
		            title: {
		                text: ' '
		            },
		            stackLabels: {
		                enabled: true,
		                style: {
		                    fontWeight: 'bold',
		                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
		                }
		            }
		        },
		        
		        tooltip: {
		            formatter: function () {
		                return '<b>' + this.x + '</b><br/>' +
		                    this.series.name + ': ' + this.y + '<br/>' +
		                    'Total: ' + this.point.stackTotal;
		            }
		        },
		        plotOptions: {
		            column: {
		                stacking: 'normal',
		                dataLabels: {
		                    enabled: true,
		                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
		                    style: {
		                        textShadow: '0 0 3px black'
		                    }
		                }
		            }
		        },
		        
		        
		        series: [{
		        	name:"number of users",
		            data: count
		        }] 
		    });*/
		}
		
		
		
		
		
		
		</script>
		
		
		
	</head>
	<body id="page-top" class="index">
		<script src="./resources/js/highcharts.js"></script>
		<script src="./resources/js/modules/data.js"></script>
		<script src="./resources/js/modules/exporting.js"></script>
		
		<!-- Additional files for the Highslide popup effect -->
		<script type="text/javascript" src="http://www.highcharts.com/media/com_demo/highslide-full.min.js"></script>
		<script type="text/javascript" src="http://www.highcharts.com/media/com_demo/highslide.config.js" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="http://www.highcharts.com/media/com_demo/highslide.css" />
		
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
								<h2>Operation Time</h2>
							</div>
						</div>
				<div class="row">
							<div class="col-lg-12 text-center" >
							<div class="form-group">

								<div class='input-group date' id='datetimepicker1'>
																		
									<input id="Start" name="Start" type='text' class="form-control" /> <span
										class="input-group-addon"> <span
										class="fa fa-calendar" onClick="getoperation_time()"></span>
									</span>
								</div>
							</div>
							<!-- chart -->
								<div id="container" style="min-width: 200px; height: 400px; margin: 0 auto" ></div>
								<!-- /#chart -->
							</div>
						</div>
					</div>
				</div>
				<!--  #page-wrapper -->
			</div>
		</div>
	</body>
</html>
