<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<!-- ÃÂ¬ÃÂÃÂÃÂ«ÃÂÃÂ¨ ÃÂ«ÃÂÃÂ¤ÃÂ«ÃÂ¹ÃÂÃÂªÃÂ²ÃÂÃÂ¬ÃÂÃÂ´ÃÂ¬ÃÂÃÂ ÃÂ«ÃÂ°ÃÂ ÃÂ¬ÃÂÃÂ¸ÃÂ­ÃÂÃÂ´ÃÂ«ÃÂ£ÃÂ¨ÃÂ«ÃÂÃÂ -->
<jsp:include page="nav.jsp" flush="false" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<style type="text/css">
${
demo.css}
</style>




</head>


<link type="text/css" href="./resources/css/ForceDirected.css"
	rel="stylesheet" />

<!--[if IE]><script language="javascript" type="text/javascript" src="../../Extras/excanvas.js"></script><![endif]-->

<!-- JIT Library File -->
<script language="javascript" type="text/javascript"
	src="./resources/js/jit.js"></script>

<!-- Example File -->
<script language="javascript" type="text/javascript"
	src="./resources/js/activity.js"></script>
<link type="text/css" href="./resources/css/base.css" rel="stylesheet" />

<script type="text/javascript">
	$(function() {
		$("#basicuserinfo").attr('class', 'accordion-body collapse in');
		$("#appinfo2").attr('class', 'accordion-body collapse in');
	});

	//$(document).ready(getAge());
</script>

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
							<BR> <BR> <BR> <BR> <BR> <BR>
							<h2>Activity Path</h2>
						</div>

						<div class="row">
							<!-- <div id="center-container"> -->
								<div id="infovis" style="background-color: #1a1a1a"></div>
							<!-- </div> -->
						</div>

					</div>
				<div id="right-container">

<div id="inner-details"></div>

</div>

					<div id="log"></div>
					
					
				</div>
			</div>
		</div>
		<!--  #page-wrapper -->
	</div>
</body>
</html>
