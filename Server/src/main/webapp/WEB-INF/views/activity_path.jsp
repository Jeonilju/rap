<!DOCTYPE HTML>
<html>
<!-- Ã¬ÂÂÃ«ÂÂ¨ Ã«ÂÂ¤Ã«Â¹ÂÃªÂ²ÂÃ¬ÂÂ´Ã¬ÂÂ Ã«Â°Â Ã¬ÂÂ¸Ã­ÂÂ´Ã«Â£Â¨Ã«ÂÂ -->
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
$(function() {
	$("#basicuserinfo").attr('class','accordion-body collapse in');
	$("#appinfo2").attr('class','accordion-body collapse in');
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
					if (request.status != '0') {
						alert("code : " + request.status + "\r\nmessage : "
								+ request.reponseText + "\r\nerror : " + error);
					}
				}
			});
		}

		
		</script>



</head>


<link type="text/css" href="./resources/css/ForceDirected.css" rel="stylesheet" />

<!--[if IE]><script language="javascript" type="text/javascript" src="../../Extras/excanvas.js"></script><![endif]-->

<!-- JIT Library File -->
<script language="javascript" type="text/javascript" src="./resources/js/jit.js"></script>

<!-- Example File -->
<script language="javascript" type="text/javascript" src="./resources/js/activity1.js"></script>





	<link type="text/css" href="./resources/css/base.css" rel="stylesheet" />

<body id="page-top" class="index" onload="init();">
	
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
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<h2>Activity Path</h2>
						</div>
					</div>
					<div class="row">
																		<div id="container">

																		
																			
																			<div id="center-container">
																			    <div id="infovis"></div>    
																			</div>
																			
																			<div id="right-container">
																			
																			<div id="inner-details"></div>
																			
																			</div>
																			
																			<div id="log"></div>
																			</div>
					
						</div>
					</div>
				</div>
			</div>
			<!--  #page-wrapper -->
		</div>
</body>
</html>
