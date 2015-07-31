<!DOCTYPE HTML>
<html>
<!-- Ã¬ÂÂÃ«ÂÂ¨ Ã«ÂÂ¤Ã«Â¹ÂÃªÂ²ÂÃ¬ÂÂ´Ã¬ÂÂ Ã«Â°Â Ã¬ÂÂ¸Ã­ÂÂ´Ã«Â£Â¨Ã«ÂÂ -->
<jsp:include page="nav.jsp" flush="false" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<style type="text/css">
${
demo


.css


}
</style>

	<script src="http://code.highcharts.com/maps/highmaps.js"></script>
	<script src="http://code.highcharts.com/maps/modules/exporting.js"></script>
	<script src="http://code.highcharts.com/mapdata/countries/kr/kr-all.js"></script>



<script type="text/javascript">
		$(function() {
			$("#basicuserinfo").attr('class','accordion-body collapse in');
			$("#userinfo2").attr('class','accordion-body collapse in');
			});
			
		$.ajax({
			url : "map_db",
			type : "POST",
			dataType : "JSON",
			success : function(data) {

				if (data != null && data != "") {

					var Location = data.Location;
					modify_chart(Location);

				}
			},

			error : function(request, status, error) {
				if (request.status != '0') {
					alert("code : " + request.status + "\r\nmessage : "
							+ request.reponseText + "\r\nerror : " + error);
				}
			}
		});
	


			function modify_chart(Location) {

		    // Prepare demo data
		    var data=Location;
		  

		    // Initiate the chart
		    $('#container').highcharts('Map', {

		        title: {
		            text: ' '
		        },


		        mapNavigation: {
		            enabled: true,
		            buttonOptions: {
		                verticalAlign: 'bottom'
		            }
		        },

		        colorAxis: {
		            min: 0
		        },

		        series: [{
		            data: data,
		            mapData: Highcharts.maps['countries/kr/kr-all'],
		            joinBy: 'hc-key',
		            name: 'Random data',
		            states: {
		                hover: {
		                    color: '#BADA55'
		                }
		            },
		            dataLabels: {
		                enabled: true,
		                format: '{point.name}'
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
	<link rel="stylesheet" type="text/css"
		href="http://www.highcharts.com/media/com_demo/highslide.css" />
		
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
							<BR> <BR> <BR> <BR> <BR> <BR>
							<h2>User Location</h2>
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
