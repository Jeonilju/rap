<!DOCTYPE HTML>
<html>
<!-- ìë¨ ë¤ë¹ê²ì´ì ë° ì¸í´ë£¨ë -->
<jsp:include page="nav.jsp" flush="false" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Highcharts Example</title>

<style type="text/css">
${
demo
.css
}
</style>
<script type="text/javascript">
$(document).ready(getOS());

function getOS() {
	$.ajax({
		url : "os_db",
		type : "POST",
		dataType : "JSON",
		success : function(data) {

			if (data != null && data != "") {

				var os = data.OS;
				var oslen = os.length;
				modify_chart(os,oslen);

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


		function modify_chart(os,oslen) {
		    // Create the chart
		   
		    
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
							text : 'title'
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
						   series: [{
					            name: "Brands",
					            colorByPoint: true,
					            data:os
					        }]
					});
		   
		}

		</script>
</head>
<body id="page-top" class="index">
	<script src="./resources/js/highcharts.js"></script>
	<script src="./resources/js/modules/data.js"></script>
	<script src="./resources/js/modules/drilldown.js"></script>

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
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<h2>Operating System</h2>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 text-center">
							<!-- chart -->
							<div id="container"
								style="min-width: 310px; max-width: 600px; height: 400px; margin: 0 auto"></div>
							<!-- /#chart -->
						</div>
					</div>
				</div>
			</div>
			<!--  #page-wrapper -->
		</div>
	</div>

	<pre id="tsv" style="display: none">Browser Version	Total Market Share
</pre>
</body>
</html>
