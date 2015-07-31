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
								<h2>사용자 정보</h2>
							</div>
						</div>
						<br />
						<div class="row" >
							
							<h4>사용자 정보 수집</h4>
							<br/>
							<p>
							수집 가능한 사용자 정보는 Device정보, OS 버전, 현재 위치, 성별, 나이가 있습니다.<br/>
							간단하게 API하나 호출하는것으로 원하는 정보를 수집하고 시각화하여 볼 수 있으며 필터링까지도 가능합니다.<br/>   
							</p>
							
							<p align="center">
								<br/>
								<b>성별 정보 수집</b><br/>
								<img src="./resources/image/started/a_sex.png" width="500" height="300" style="margin: 10px;" >
								<br/>
								위의 코드와 같이 <br/>
								<b>RAPAPIs.UserInfo_Sex(boolean true)</b> 또는 <b>RAPAPIs.UserInfo_Sex(boolean false)</b><br/>
								를 호출하면 HttpRequestBase를 받을 수 있습니다.<br/>
								<br/>
								<br/>인자로 true를 넘기면 남자, false를 넘기시면 여자로 설정됩니다.<br/>
								편의성을 위해 본래 사용하고 계신 통신 모듈이 있으시면 이를 활용하여 시용하실 수 있습니다.<br/>
								만약 없으시면 <br/>
								<b>RAPHttpClient.getInstance().background(HttpRequestBase req, Handler null)</b><br/>
								와 같이 제공되는 통신모듈을 이용하실 수 있습니다.<br/>
							</p>
							
							<p align="center">
								<br/>
								<h8>나이 정보 수집</h8><br/>
								<img src="./resources/image/started/a_age.png" width="500" height="300" style="margin: 10px;">
								<br/>
								성별 정보를 수집할때와 마찬가지 방법으로 <b>RAPAPIs.UserInfo_Age(int age)</b> 를 통해<br/> 
								HttpRequestBase를 받아 요청을 보내면 사용자의 나이가 설정됩니다.<br/>
							</p>
							
							<p align="center">
								<br/>
								<h8>위치 정보 수집</h8><br/>
								<img src="./resources/image/started/a_age.png" width="500" height="300" style="margin: 10px;">
								<br/>
								위치정보는 Android 기기 내의 GPS 센서를 이용하여 현재 사용자의 위치를 설정합니다.<br/>
								위치정보를 받기위해선 2가지 API가 제공되는데 첫번째는 <br/>
								<b>RAPAPIs.UserInfo_Location(Context mContext)</b> 입니다.<br/>
								이 API는 Context를 인자로 받아 내부에서 GPS값을 읽어 위치를 설정합니다.<br/>
								<br>
								두번쨰 방법은 <b>UserInfo_Location(double lat, double lon)</b> 입니다.<br/>
								개발자가 직접 위도와 경도를 인자로 보내 사용자의 위치를 설정할 수 있습니다.<br/>
								<br>
								첫번째 API를 활용하기 위해선 권한이 필요한데<br/>
								Manifest.xml에 다음과 같은 권한을 추가해야합니다.<br/>
								<b>android.permission.ACCESS_FINE_LOCATION</b><br/>
							</p>
							
							<p align="center">
								<br/>
								<h8>OS 정보 수집</h8><br/>
								<img src="./resources/image/started/a_os.png" width="500" height="300" style="margin: 10px;">
								<br/>
								사용자의 OS의 정보를 수집할 수 있습니다.<br/>
								호출 API는 <b>RAPAPIs.UserInfo_OS()</b> 이고 <br/>
								이용방법은 위의 방법과 동일하게 사용하시면 됩니다. <br/>
							</p>
							
							<p align="center">
								<br/>
								<h8>Device 정보 수집</h8><br/>
								<img src="./resources/image/started/a_device.png" width="500" height="300" style="margin: 10px;">
								<br/>
								사용자의 Device의 정보를 수집할 수 있습니다.<br/>
								호출 API는 <b>RAPAPIs.UserInfo_Device()</b> 이고 <br/>
								이용방법은 위의 방법과 동일하게 사용하시면 됩니다. <br/>
							</p>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>