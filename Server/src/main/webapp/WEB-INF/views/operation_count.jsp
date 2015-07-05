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
		            type: 'column'
		        },
		        title: {
		            text: 'title'
		        },
		        xAxis: {
		            categories: ['05/01', '05/02', '05/03', '05/04', '05/05']
		        },
		        yAxis: {
		            min: 0,
		            title: {
		                text: 'Total operations counts'
		            },
		            stackLabels: {
		                enabled: true,
		                style: {
		                    fontWeight: 'bold',
		                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
		                }
		            }
		        },
		        legend: {
		            align: 'right',
		            x: -30,
		            verticalAlign: 'top',
		            y: 25,
		            floating: true,
		            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
		            borderColor: '#CCC',
		            borderWidth: 1,
		            shadow: false
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
		            name: '1st class',
		            data: [5, 3, 4, 7, 2]
		        }, {
		            name: '2nd class',
		            data: [2, 2, 3, 2, 1]
		        }, {
		            name: '3rd class',
		            data: [3, 4, 4, 2, 5]
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
								<h2>Operation Count</h2>
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
