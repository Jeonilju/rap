<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<jsp:include page="nav.jsp" flush = "false" />
	<head>
		<title>판매 순위</title>

		<meta name="viewport" content="width=device-width, initial-scale=1">
		<style type="text/css">
			${demo.css}
		</style>
		
        <script src="./resources/js/bootstrap-datepicker.js"></script>
		<script src="./resources/js/highcharts.js"></script>
		<script src="./resources/js/modules/data.js"></script>
		<script src="./resources/js/modules/exporting.js"></script>
		
<<<<<<< HEAD
		<!-- Additional files for the Highslide popup effect -->
		<script type="text/javascript" src="http://www.highcharts.com/media/com_demo/highslide-full.min.js"></script>
		<script type="text/javascript" src="http://www.highcharts.com/media/com_demo/highslide.config.js" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="http://www.highcharts.com/media/com_demo/highslide.css" />		
		
	<script type="text/javascript">
			//$( document ).ready(getsales_ranking());
			function getsales_ranking() {
				
				var start_date = document.getElementById('start_date').value;
				var end_date = document.getElementById('end_date').value;
				var sex = document.getElementsByName('sex');
				var sex_num = 0;
				for (var i = 0, length = sex.length; i < length; i++) {
				    if (sex[i].checked) {
				    	sex_num = i + 1;
				        break;
				    }
				}
				var age = document.getElementById('age').value;
				var grade_using = document.getElementById('grade_using').value;
				var grade_time = document.getElementById('grade_time').value;
				
				var param = "start_date=" + start_date +
							"&end_date=" + end_date +
							"&sex_num=" + sex_num +
							"&age=" + age +
							"&grade_using=" + grade_using +
							"&grade_time=" + grade_time ;
				
				$.ajax({
					url : "sales_ranking_db",
					type : "POST",
					data : param,
					dataType : "JSON",
					success : function(data) {
			
						if (data != null && data != "") {
			
							var item_name=data.item_name;
							var count=data.count;
							add_to_table(item_name,count);
			
						}
					},
			
					error : function(request, status, error) {
						if(request.status == '200'){
							
						}
						else if (request.status != '0') {
							alert("code : " + request.status + "\r\nmessage : "
									+ request.reponseText + "\r\nerror : " + error);
						}
					}
				});
			}

			function add_to_table(item_name,count){
				$("#table_body").empty();
				
				if(item_name.length>0){
					var a=item_name.length;
					
					if(a>10)
						a=10;
		
					for(i=0;i<a;i++)
						$('#table_body').append("<tr><td>"+(i+1)+"</td><td>"+item_name[i]+"</td><td>"+count[i]+"</td></tr>");
				}
			}

		</script>
		
		<script type="text/javascript">
			
			function onLoading(){
				$('#start_date').datepicker("setDate", new Date());
				$('#end_date').datepicker("setDate", new Date());
				
				$("#basicuserinfo").attr('class','accordion-body collapse in');
				$("#iapinfo2").attr('class','accordion-body collapse in');
				
				getsales_ranking();
			}
		
		</script>
		
		
	</head>
	<body id="page-top" class="index" onload="onLoading()">
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
								<BR><BR><BR><BR><BR><BR>
								<h2>Sales ranking</h2>
							</div>
						</div>
						
						<div>
							<div class="span6" style="margin: 10px">
								<label>기간</label>
								<input class="datepicker" id="start_date" name="start_date" data-date-format="yyyy-mm-dd" onchange="getsales_ranking()">
								<label> ~ </label>
								<input class="datepicker" id="end_date" name="end_date" data-date-format="yyyy-mm-dd"  onchange="getsales_ranking();">	
							</div>
							<div class="span6" style="margin: 10px">
								<label>성별</label>
								<input type="radio" id="sex_man" name="sex" value="남자"  onchange="getsales_ranking();">남자
								<input type="radio" id="sex_woman" name="sex" valud="여자"  onchange="getsales_ranking();">여자	
							</div>
							<div class="span6" style="margin: 10px">
								<label>연령</label>
								<select class="selectpicker" id="age" name="age"  onchange="getsales_ranking();">
									<option value="0">전체</option>
									<option value="10">10대</option>
									<option value="20">20대</option>
									<option value="30">30대</option>
									<option value="40">40대</option>
									<option value="50">50대</option>
									<option value="60">60대</option>
									<option value="70">70대</option>
									<option value="80">80대</option>
									<option value="90">90대</option>
								</select>	
							</div>
							<div class="span6" style="margin: 10px">
								<label>사용시간 등급</label>
								<select class="selectpicker" id="grade_time" name="grade_time"  onchange="getsales_ranking();">
									<option value="0">전체</option>
									<option value="1">1등급</option>
									<option value="2">2등급</option>
									<option value="3">3등급</option>
									<option value="4">4등급</option>
								</select>	
							</div>
							<div class="span6" style="margin: 10px">
								<label>사용시간 등급</label>
								<select class="selectpicker" id="grade_using" name="grade_using"  onchange="getsales_ranking();">
									<option value="0">전체</option>
									<option value="1">1등급</option>
									<option value="2">2등급</option>
									<option value="3">3등급</option>
									<option value="4">4등급</option>
								</select>	
							</div>
						</div>
				
						<table class="table table-striped">
							<thead>
								<tr>
									<th>순위</th>
									<th>아이탬 이름</th>
									<th>판매량</th>
								</tr>
							</thead>

							<tbody id="table_body">
							</tbody>
						</table>
						<!--  #page-wrapper -->
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
