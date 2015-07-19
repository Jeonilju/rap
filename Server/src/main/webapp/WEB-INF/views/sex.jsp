<!DOCTYPE HTML>
<html>
<!-- Ã¬ÂÂÃ«ÂÂ¨ Ã«ÂÂ¤Ã«Â¹ÂÃªÂ²ÂÃ¬ÂÂ´Ã¬ÂÂ Ã«Â°Â Ã¬ÂÂ¸Ã­ÂÂ´Ã«Â£Â¨Ã«ÂÂ -->
<jsp:include page="nav.jsp" flush = "false" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highcharts Example</title>

		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<style type="text/css">
${demo.css}
		</style>
		<script type="text/javascript">
		
$(function () {
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'title'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            name: "Brands",
            colorByPoint: true,
            data: [{
                name: "Male",
                y: 56.33
            }, {
                name: "Female",
                y: 43.67,
                sliced: true,
                selected: true
            }
            
            ]
        }]
    });
});
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
						<li><br><br><br></li>
						<li class="sidebar-brand">
							<a href="#">
								Analysis
							</a>
						</li>
						<li>
							<a href="operation_count">Operation count</a>
							<a href="operation_time">Operation time</a>
							<a href="sex">Sex ratio</a>
							<a href="age">Age</a>
							<a href="os">OS</a>
							<a href="device">Device</a>
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
								<BR><BR><BR><BR><BR><BR>
								<h2>Sex ratio</h2>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12 text-center">
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
