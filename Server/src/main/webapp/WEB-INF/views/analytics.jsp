<!DOCTYPE html>
<html lang="en">
<!-- 상단 네비게이션 바 인클루드 -->
<jsp:include page="nav.jsp" flush = "false" />

<style type="text/css">
	${demo.css}
</style>
<script type="text/javascript">
	$(function () {
	
	    // Get the CSV and create the chart
	    $.getJSON('http://www.highcharts.com/samples/data/jsonp.php?filename=analytics.csv&callback=?', function (csv) {
	
	        $('#chart').highcharts({
	
	            data: {
	                csv: csv
	            },
	
	            title: {
	                text: 'Daily visits at www.highcharts.com'
	            },
	
	            subtitle: {
	                text: 'Source: Google Analytics'
	            },
	
	            xAxis: {
	                tickInterval: 7 * 24 * 3600 * 1000, // one week
	                tickWidth: 0,
	                gridLineWidth: 1,
	                labels: {
	                    align: 'left',
	                    x: 3,
	                    y: -3
	                }
	            },
	
	            yAxis: [{ // left y axis
	                title: {
	                    text: null
	                },
	                labels: {
	                    align: 'left',
	                    x: 3,
	                    y: 16,
	                    format: '{value:.,0f}'
	                },
	                showFirstLabel: false
	            }, { // right y axis
	                linkedTo: 0,
	                gridLineWidth: 0,
	                opposite: true,
	                title: {
	                    text: null
	                },
	                labels: {
	                    align: 'right',
	                    x: -3,
	                    y: 16,
	                    format: '{value:.,0f}'
	                },
	                showFirstLabel: false
	            }],
	
	            legend: {
	                align: 'left',
	                verticalAlign: 'top',
	                y: 20,
	                floating: true,
	                borderWidth: 0
	            },
	
	            tooltip: {
	                shared: true,
	                crosshairs: true
	            },
	
	            plotOptions: {
	                series: {
	                    cursor: 'pointer',
	                    point: {
	                        events: {
	                            click: function (e) {
	                                hs.htmlExpand(null, {
	                                    pageOrigin: {
	                                        x: e.pageX || e.clientX,
	                                        y: e.pageY || e.clientY
	                                    },
	                                    headingText: this.series.name,
	                                    maincontentText: Highcharts.dateFormat('%A, %b %e, %Y', this.x) + ':<br/> ' +
	                                        this.y + ' visits',
	                                    width: 200
	                                });
	                            }
	                        }
	                    },
	                    marker: {
	                        lineWidth: 1
	                    }
	                }
	            },
	
	            series: [{
	                name: 'All visits',
	                lineWidth: 4,
	                marker: {
	                    radius: 4
	                }
	            }, {
	                name: 'New visitors'
	            }]
	        });
	    });
	
	});
	

</script>

<body id="page-top" class="index">

	<script src="./resources/js/highcharts.js"></script>
	<script src="./resources/js/modules/data.js"></script>
	<script src="./resources/js/modules/exporting.js"></script>
	
	<!-- Additional files for the Highslide popup effect -->
	<script type="text/javascript" src="http://www.highcharts.com/media/com_demo/highslide-full.min.js"></script>
	<script type="text/javascript" src="http://www.highcharts.com/media/com_demo/highslide.config.js" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="http://www.highcharts.com/media/com_demo/highslide.css" />
	
	<div class="container">
		<!-- 래퍼 -->
		<div id="wrapper">
			<!-- 사이드바 -->
			<div id="sidebar-wrapper">
				<ul class="sidebar-nav">
					<li><br><br><br></li>
					<li class="sidebar-brand">
						<a href="#">
							Register
						</a>
					</li>
					<li>
						<a href="register">Application Registration</a>
					</li>
				</ul>
			</div>
			<!-- /#사이드바 -->

			<!-- 페이지 컨텐트 -->
			<div id="page-content-wrapper">
				<div class="container-fluid">
					<!-- Application Registration -->
					<div class="row">
						<div class="col-lg-12 text-center">
							<BR><BR><BR><BR><BR><BR>
							<h2>Application Registration</h2>
							<hr class="star-primary">
						</div>
					</div>
					<div class="row">
						<!-- chart -->
						<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
						<!-- /#chart -->
					</div>
				</div>
			</div>
			<!-- /#페이지 컨텐트 -->

		</div>
		<!-- /#래퍼 -->
		
	</div>
	
	
	
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

</body>

</html>
