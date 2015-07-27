<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, com.rap.models.PromotionInfo, com.rap.models.ProjectInfo, com.rap.models.MemberInfo"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<!-- ÃÂ¬ÃÂÃÂÃÂ«ÃÂÃÂ¨ ÃÂ«ÃÂÃÂ¤ÃÂ«ÃÂ¹ÃÂÃÂªÃÂ²ÃÂÃÂ¬ÃÂÃÂ´ÃÂ¬ÃÂÃÂ ÃÂ«ÃÂ°ÃÂ ÃÂ¬ÃÂÃÂ¸ÃÂ­ÃÂÃÂ´ÃÂ«ÃÂ£ÃÂ¨ÃÂ«ÃÂÃÂ -->
<jsp:include page="nav.jsp" flush = "false" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highcharts Example</title>

<<<<<<< HEAD
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
=======
>>>>>>> 4b25dd1172f00a37fa2d64c6934849e83e66ce4e
		<style type="text/css">
${demo.css}
		</style>
		
        
<%
	ProjectInfo currentproject = (ProjectInfo)session.getAttribute("currentproject");
%>
		
<script type="text/javascript">


$(document).ready(function(){getpromotionlist()});

function getpromotionlist()
{
	var param = "project_name" + "=" + "<%=(String)currentproject.getProject_name() %>";
	
	$.ajax({
		url : "promotionlist_db",
		type : "POST",
		data : param,
		dataType : "JSON",
		success : function(data) {
<<<<<<< HEAD
			$('#plist').html("");
=======
			$('#promotion_list').html("");
>>>>>>> 4b25dd1172f00a37fa2d64c6934849e83e66ce4e
			
			if(data!=null || data!="")
			{
				var list = data.promotionlist;
				var listLen = list.length;
				
				for(var i=0;i<listLen;i++)
				{
					$('#promotion_list').append("<option>"+list[i].name+"</option>");
				}
				
				if(listLen==0)
				{	$('#promotion_list').append("<option>"+"No promotion"+"</option>");
					
				}

				
			}
			else
			{	$('#promotion_list').append("<option>"+"No promotion"+"</option>");

			
				
			}
<<<<<<< HEAD
=======
			
			$('#promotion_list').selectpicker('refresh');
>>>>>>> 4b25dd1172f00a37fa2d64c6934849e83e66ce4e
		}
	});
	
}



function getoperation_count() {
	
	
	var param = 
				"start=" + document.getElementById('Start').value+
				"&promotion=" + document.getElementById('promotion_list').value;
	
	//alert('param= '+param);
	$.ajax({
		url : "promotions_analysis_db",
		type : "POST",
		data : param,
		dataType : "JSON",
		success : function(data) {

			if (data != null || data != "") {

				//var start_time=data.start_time;
				var result=data.result;
				//alert(start_time.toString());
				modify_chart(result);

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



 function modify_chart(result) {
$('#container').highcharts({
    chart: {
        type: 'column'
    },
    title: {
    	text:' '
    },
    subtitle: {
    	
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
            text: 'Counts'
        }
    },
    legend: {
        enabled: false
    },
    tooltip: {
        pointFormat: '<b>{point.y:.1f} </b>'
    },
    series: [{
        name: 'Count',
        data: result,
        dataLabels: {
            enabled: true,
            rotation: -45,
            color: '#FFFFFF',
            align: 'right',
            format: '{point.y:.1f}', // one decimal
            y: -15, // 10 pixels down from the top
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
								<BR><BR><BR><BR><BR><BR>
								<h2>Promotions Analysis</h2>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12 text-center" >
						

							<div class="form-group">
	<select class="selectpicker show-tick"  id="promotion_list" name="promotion_list" ></select> 
								<div class='input-group date' id='datetimepicker1'>
								
									
									<input id="Start" name="Start" type='text' class="form-control" /> <span
										class="input-group-addon"> <span
										class="fa fa-calendar" onClick="getoperation_count()"></span>
									</span>
								</div>
							</div>



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
