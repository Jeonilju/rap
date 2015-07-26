 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <!DOCTYPE HTML>
<html>
<!-- ÃÂ¬ÃÂÃÂÃÂ«ÃÂÃÂ¨ ÃÂ«ÃÂÃÂ¤ÃÂ«ÃÂ¹ÃÂÃÂªÃÂ²ÃÂÃÂ¬ÃÂÃÂ´ÃÂ¬ÃÂÃÂ ÃÂ«ÃÂ°ÃÂ ÃÂ¬ÃÂÃÂ¸ÃÂ­ÃÂÃÂ´ÃÂ«ÃÂ£ÃÂ¨ÃÂ«ÃÂÃÂ -->
<jsp:include page="nav.jsp" flush = "false" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highcharts Example</title>

		  <meta name="viewport" content="width=device-width, initial-scale=1">
		<style type="text/css">
${demo.css}
		</style>
		
        
		
<script type="text/javascript">
$( document ).ready(getsales_ranking());
function getsales_ranking() {
	$.ajax({
		url : "sales_ranking_db",
		type : "POST",
		dataType : "JSON",
		success : function(data) {

			if (data != null || data != "") {

				var item_name=data.item_name;
				var count=data.count;
				//alert(item_name.toString());
				add_to_table(item_name,count);

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



function add_to_table(item_name,count){
	
	//민수 여기 사이즈 체크 필요
	if(item_name.length>0){
		var a=item_name.length;
		if(a>10)a=10;
		
	for(i=0;i<a;i++)
	$('#table_body').append("<tr><td>"+(i+1)+"</td><td>"+item_name[i]+"</td><td>"+count[i]+"</td></tr>");
	}
}

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
							<h2>Sales ranking</h2>
						</div>
					</div>
						<table class="table table-striped">
						<thead>
							<tr>
								<th>Rank</th>
								<th>Item name</th>
								<th>Count</th>
							</tr>
						</thead>

						<tbody id="table_body">

						</tbody>

						</div>
						</div>
						</div>
						</div>


						<!--  #page-wrapper -->
			</div>
		</div>
	</body>
</html>
