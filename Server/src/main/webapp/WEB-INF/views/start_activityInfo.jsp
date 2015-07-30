<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<jsp:include page="nav.jsp" flush = "false" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Get Start</title>
	</head>
	
	<body id="page-top" class="index">
		<div class="container">
			<div id="wrapper">
				<!--  sidebar-wrapper -->
				<div id="sidebar-wrapper">
					<ul class="sidebar-nav">
						<jsp:include page="getStart-nav.jsp" flush="false" />
					</ul>
				</div>
				
				<!--  page-wrapper -->
				<div id="page-content-wrapper">
					<div class="container-fluid">
						<!-- Application Registration -->
						<div class="row">
							<div class="col-lg-12 text-center">
								<BR><BR><BR><BR><BR><BR>
								<h2>Activity 정보 수집</h2>
							</div>
						</div>
						<br />
						<div class="row">
							
							<h4>RAPBaseActivity 상속 </h4>
							
							<p >
							R.A.P를 통해 사용자가 앱에서 어떤 페이지를 가장 많이 보고있는지<br>
							그리고 어떻게 이동하고있는지 그 흐름을 보다 쉽고 간편하게 확인할 수 있습니다.<br>
							<br><br>
							사용자에게 Activity에 대한 정보를 받기 위해선 2가지의 방법이 있습니다.<br>
							첫번쨰로 RAPBaseActivity를 상속받는것입니다.<br>
							</p>
							
							<p align="center">
								<br/>
								<img src="./resources/image/started/activity_extends.png" width="354" height="22" style="margin: 10px;">
								<br/>
								위와같이 extends를 통해 RAPBaseActivity를 상속받습니다.<br>
							</p>
							
							<p align="center">
								<img src="./resources/image/started/onCreate.png" width="445" height="120" style="margin: 10px;">
								<img src="./resources/image/started/onDestroy.png" width="445" height="120" style="margin: 10px;">
								<br/>
								다음으로 <b>onCreate</b>와 <b>onDestroy</b>에 super 함수를 호출하는 코드를 삽입합니다.<br>
								위와같이 코드 3줄을 삽입하는것을 통해 사용자가 어느 페이지에 자주 방문하는지, 그리고 어떤 경로를 통해<br>
								이동하는지 확인할 수 있습니다.<br>
							</p>
							
							<p align="center">
								<img src="./resources/image/started/best_activity.png" width="883" height="514" style="margin: 10px;">
								<br/>
								Best Activity는 위와같이 사용자가 가장 많이 방문한 Activity를 순서대로 볼 수 있습니다. <br>
							</p>
							
							<p align="center">
								<img src="./resources/image/started/activity_path.png" width="687" height="529" style="margin: 10px;">
								<br/>
								Activity Path는 어떤 경로로 이동하는지 볼 수 있습니다.<br>
								Activity 이름을 클릭하시면 얼마나  이동했는가를 보실 수 있습니다.<br>
							</p>
							
							<hr/>
							
							<h4>Activity 정보 수집</h4>
							<p align="center">
								<img src="./resources/image/started/activity_info.png" width="692" height="32" style="margin: 10px;">
								<br/>
								두번쨰 방법은 사용자 정보를 수집했던 방식과 동일한 방식으로 Activity 정보를 수집하는것입니다.<br>
								<br>
								<b>RAPAPIs.ActivityInfo_Move(String 이전 Activity 이름, String 현재 Activiy 이름)</b><br>
								<br>
								위의 코드를 삽입함을 통해 사용자의 Activity간의 정보를 얻어낼 수 있습니다.<br>
								Activity간의 관계를 잘 정의해야 하므로 RAPBaseActivity를 상속받아 사용하는것을 권장합니다.<br>
							</p>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>