<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.rap.models.PromotionInfo, com.rap.models.ProjectInfo, com.rap.models.MemberInfo"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
	<jsp:include page="nav.jsp" flush = "false" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highcharts Example</title>
		<style type="text/css">
			${demo.css}
		</style>
		<%
			ProjectInfo currentproject = (ProjectInfo)session.getAttribute("currentproject");
			List<PromotionInfo> promotionList = (List<PromotionInfo>) request.getAttribute("promotionList");
		%>

		<script src="./resources/js/highcharts.js"></script>
		<script src="./resources/js/modules/data.js"></script>
		<script src="./resources/js/modules/exporting.js"></script>
		
		<!-- Additional files for the Highslide popup effect -->
		<script type="text/javascript" src="http://www.highcharts.com/media/com_demo/highslide-full.min.js"></script>
		<script type="text/javascript" src="http://www.highcharts.com/media/com_demo/highslide.config.js" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="http://www.highcharts.com/media/com_demo/highslide.css" />
		
		<script type="text/javascript">

				function getoperation_count() {
					
					var param = "promotion_pk=" + document.getElementById('promotion_list').value;
					
					//alert('param= '+param);
					$.ajax({
						url : "promotions_analysis_db",
						type : "POST",
						data : param,
						dataType : "JSON",
						success : function(data) {
				
							if (data != null && data != "") {
								var result=data.result;
								modify_chart(result);
							}
						},
				
						error : function(request, status, error) {
							if(request.status == '200'){
								
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
				    chart: {
				        type: 'column'
				    },
				    title: {
				    	text:' '
				    },
				    subtitle: {
				    	
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
				        pointFormat: '<b>{point.y:.1f} </b>'
				    },
				    series: [{
				        name: 'Count',
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
				
				
			}
		</script>
		
	</head>
	
	<body id="page-top" class="index">
		
		<div class="container">
			<div id="wrapper">
				<!--  sidebar-wrapper -->
				<div id="sidebar-wrapper">
					<ul class="sidebar-nav">
						<jsp:include page="sidebar-nav.jsp" flush="false" />
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
								<h2>Promotions Analysis</h2>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12 text-center" >
								<div class="form-group">
									<select class="selectpicker show-tick"  id="promotion_list" name="promotion_list" >
										<%
											for(PromotionInfo info : promotionList){
												out.println("<option value=" + info.getPk() + ">" + info.getName() + "</option>");
											}
										%>
									</select>
								</div>
								<div id="container" style="min-width: 200px; height: 400px; margin: 0 auto" ></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
