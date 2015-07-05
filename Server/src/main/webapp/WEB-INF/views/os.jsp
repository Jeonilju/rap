<!DOCTYPE HTML>
<html>
<!-- ìë¨ ë¤ë¹ê²ì´ì ë° ì¸í´ë£¨ë -->
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
		    // Create the chart
		    $('#container').highcharts({
		        chart: {
		            type: 'pie'
		        },
		        title: {
		            text: ' '
		        },
		        subtitle: {
		            text: ' '
		        },
		        plotOptions: {
		            series: {
		                dataLabels: {
		                    enabled: true,
		                    format: '{point.name}: {point.y:.1f}%'
		                }
		            }
		        },

		        tooltip: {
		            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
		            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
		        },
		        series: [{
		            name: "Brands",
		            colorByPoint: true,
		            data: [{
		                name: "Android",
		                y: 56.33,
		                drilldown: "Android"
		            }, {
		                name: "iOS",
		                y: 24.030000000000005,
		                drilldown: "iOS"
		            }, {
		                name: "Symbian",
		                y: 10.38,
		                drilldown: "Symbian"
		            }, {
		                name: "RIM",
		                y: 4.77,
		                drilldown: "RIM"
		            }, {
		                name: "Bada",
		                y: 0.9100000000000001,
		                drilldown: "Bada"
		            }, {
		                name: "Proprietary or Undetectable",
		                y: 0.2,
		                drilldown: null
		            }]
		        }],
		        drilldown: {
		            series: [{
		                name: "Android",
		                id: "Android",
		                data: [
		                    ["v4.4", 24.13],
		                    ["v4.0", 17.2],
		                    ["v3.0", 8.11],
		                    ["v2.3", 5.33],
		                    ["v2.0", 1.06],
		                    ["v1.0", 0.5]
		                ]
		            }, {
		                name: "iOS",
		                id: "iOS",
		                data: [
		                    ["v40.0", 5],
		                    ["v41.0", 4.32],
		                    ["v42.0", 3.68],
		                    ["v39.0", 2.96],
		                    ["v36.0", 2.53],
		                    ["v43.0", 1.45],
		                    ["v31.0", 1.24],
		                    ["v35.0", 0.85],
		                    ["v38.0", 0.6],
		                    ["v32.0", 0.55],
		                    ["v37.0", 0.38],
		                    ["v33.0", 0.19],
		                    ["v34.0", 0.14],
		                    ["v30.0", 0.14]
		                ]
		            }, {
		                name: "Symbian",
		                id: "Symbian",
		                data: [
		                    ["v35", 2.76],
		                    ["v36", 2.32],
		                    ["v37", 2.31],
		                    ["v34", 1.27],
		                    ["v38", 1.02],
		                    ["v31", 0.33],
		                    ["v33", 0.22],
		                    ["v32", 0.15]
		                ]
		            }, {
		                name: "RIM",
		                id: "RIM",
		                data: [
		                    ["v8.0", 2.56],
		                    ["v7.1", 0.77],
		                    ["v5.1", 0.42],
		                    ["v5.0", 0.3],
		                    ["v6.1", 0.29],
		                    ["v7.0", 0.26],
		                    ["v6.2", 0.17]
		                ]
		            }, {
		                name: "Bada",
		                id: "Bada",
		                data: [
		                    ["v12.x", 0.34],
		                    ["v28", 0.24],
		                    ["v27", 0.17],
		                    ["v29", 0.16]
		                ]
		            }]
		        }
		    });
		});


		</script>
	</head>
	<body id="page-top" class="index">
		<script src="./resources/js/highcharts.js"></script>
		<script src="./resources/js/modules/data.js"></script>
		<script src="./resources/js/modules/drilldown.js"></script>
		
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
								<h2>Operating System</h2>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12 text-center">
								<!-- chart -->
								<div id="container" style="min-width: 310px; max-width: 600px; height: 400px; margin: 0 auto"></div>
								<!-- /#chart -->
							</div>
						</div>
					</div>
				</div>
				<!--  #page-wrapper -->
			</div>
		</div>
		
		<pre id="tsv" style="display:none">Browser Version	Total Market Share
Microsoft Internet Explorer 8.0	26.61%
Microsoft Internet Explorer 9.0	16.96%
Chrome 18.0	8.01%
Chrome 19.0	7.73%
Firefox 12	6.72%
Microsoft Internet Explorer 6.0	6.40%
Firefox 11	4.72%
Microsoft Internet Explorer 7.0	3.55%
Safari 5.1	3.53%
Firefox 13	2.16%
Firefox 3.6	1.87%
Opera 11.x	1.30%
Chrome 17.0	1.13%
Firefox 10	0.90%
Safari 5.0	0.85%
Firefox 9.0	0.65%
Firefox 8.0	0.55%
Firefox 4.0	0.50%
Chrome 16.0	0.45%
Firefox 3.0	0.36%
Firefox 3.5	0.36%
Firefox 6.0	0.32%
Firefox 5.0	0.31%
Firefox 7.0	0.29%
Proprietary or Undetectable	0.29%
Chrome 18.0 - Maxthon Edition	0.26%
Chrome 14.0	0.25%
Chrome 20.0	0.24%
Chrome 15.0	0.18%
Chrome 12.0	0.16%
Opera 12.x	0.15%
Safari 4.0	0.14%
Chrome 13.0	0.13%
Safari 4.1	0.12%
Chrome 11.0	0.10%
Firefox 14	0.10%
Firefox 2.0	0.09%
Chrome 10.0	0.09%
Opera 10.x	0.09%
Microsoft Internet Explorer 8.0 - Tencent Traveler Edition	0.09%</pre>
	</body>
</html>
