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
								<h2>SDK 설정</h2>
							</div>
						</div>
						<br />
						<div class="row">
							
							<h3>SDK 설정</h3>
							
							<p>
							SDK를 통해 쉽고 빠르게 RAP를 사용하실 수 있습니다. 
							</p>
							
							<p>
								<br/>
								<img src="./resources/image/started/sdk1.png" width="500" height="300" style="margin: 10px;">
								<br/>
								먼저 SDK를 다운로드 받기위해 상단 탭중 Resourse의 Download를 선택하면 위와같은 페이지를 확인할 수 있습니다.
								SDK다운로드 버튼을 클릭하여 SDK를 다운로드 받습니다.
								
							</p>
							
							<p>
								<img src="./resources/image/started/sdk2.png" width="200" height="500" style="margin: 10px;">
								<br/>
								다운로드한 SDK를 Android 프로젝트의 libs 폴더에 추가합니다.
							</p>
							
							<p>
								<img src="./resources/image/started/sdk3.png" width="700" height="100" style="margin: 10px;">
								<br/>
								다음은 권한을 설정해야합니다. RAP는 네트워크 통신을 하므로 <br/>
								<b>"android.permission.INTERNET"</b><br/>
								그리고 사용자를 분류하기 위해 기기의 상태를 가져오므로 
								<b>"android.permission.READ_PHONE_STATE"</b><br/>
								를 Manifest에 추가합니다.
							</p>
							
							<p>
								<img src="./resources/image/started/sdk4.png" width="700" height="300" style="margin: 10px;">
								<br/>
								마지막으로 프로젝트 Key를 설정해야합니다. 위의 코드를 시작 Activity에 삽입해주세요.
								프로젝트 Key는 데쉬보드에서 발급받은 Key입니다.
							</p>
							
							<p>
								<img src="./resources/image/started/sdk5.png" width="500" height="200" style="margin: 10px;">
								<br/>
								위의 스크린샷과 같이 해당 프로젝트에 들어가면 프로젝트의 고유 Key를 확인하실 수 있습니다.
							</p>
							
							<hr/>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>