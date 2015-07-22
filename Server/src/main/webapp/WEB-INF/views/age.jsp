<!DOCTYPE HTML>
<html>
<!-- Ã¬ÂÂÃ«ÂÂ¨ Ã«ÂÂ¤Ã«Â¹ÂÃªÂ²ÂÃ¬ÂÂ´Ã¬ÂÂ Ã«Â°Â Ã¬ÂÂ¸Ã­ÂÂ´Ã«Â£Â¨Ã«ÂÂ -->
<jsp:include page="nav.jsp" flush="false" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Highcharts Example</title>

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<style type="text/css">
${
demo
.css
}
</style>



<script type="text/javascript">

		$(document).ready(getAge());

		function getAge() {
			$.ajax({
				url : "age_db",
				type : "POST",
				dataType : "JSON",
				success : function(data) {

					if (data != null || data != "") {

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
					if (request.status != '0') {
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
		            text: 'title'
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
		                text: 'Counts (millions)'
		            }
		        },
		        legend: {
		            enabled: false
		        },
		        tooltip: {
		            pointFormat: 'Counts : <b>{point.y:.1f} millions</b>'
		        },
		        series: [{
		            name: 'Population',
		            data: [
						['under 10s', baby],   
		                ['10s', ten],
		                ['20s', twenty],
		                ['30s', thirty],
		                ['40s', forty],
		                ['over 50s', old]
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
	<script src="./resources/js/highcharts.js"></script>
	<script src="./resources/js/modules/data.js"></script>
	<script src="./resources/js/modules/exporting.js"></script>

	<!-- Additional files for the Highslide popup effect -->
	<script type="text/javascript"
		src="http://www.highcharts.com/media/com_demo/highslide-full.min.js"></script>
	<script type="text/javascript"
		src="http://www.highcharts.com/media/com_demo/highslide.config.js"
		charset="utf-8"></script>
	<link rel="stylesheet" type="text/css"
		href="http://www.highcharts.com/media/com_demo/highslide.css" />

	<div class="container">
		<div id="wrapper">
			<!--  sidebar-wrapper -->
			<div id="sidebar-wrapper">
				<ul class="sidebar-nav">
					<li><br>
					<br>
					<br></li>
					<li class="sidebar-brand"><a href="#"> Analysis </a></li>
					<li><a href="operation_count">Operation count</a> <a
						href="operation_time">Operation time</a> <a href="sex">Sex
							ratio</a> <a href="#">Age</a> <a href="os">OS</a> <a href="device">Device</a>
					</li>
				</ul>
			</div>
			<!--  #sidebar-wrapper -->

			<!--  page-wrapper -->
			<div id="page-content-wrapper">
				<div class="container-fluid">
					<!-- Application Registration -->
					<div class="row">
						<div class="col-lg-12 text-center">
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<h2>Age ratio</h2>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 text-center">
							<!-- chart -->
							<div id="container"
								style="min-width: 200px; height: 400px; margin: 0 auto"></div>
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
