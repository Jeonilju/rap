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
						<div class="row">
							
							<h4>사용자 정보 수집</h4>
							
							<p>
							수집 가능한 사용자 정보는 Device정보, OS 버전, 현재 위치, 성별, 나이가 있습니다.
							간단하게 API하나 호출하는것으로 원하는 정보를 수집하고 시각화하여 볼 수 있으며 필터링까지도 가능합니다.   
							</p>
							
							<p>
								<br/>
								<img src="./resources/image/started/SignUp1.png" width="500" height="300" style="margin: 10px;">
								<br/>
								우측 상단에 있는 Sign Up 버튼을 클릭하시면 회원가입을 진행 할 수 있는 창이 나타나게 됩니다.<br/>
								해당 창에서 회원가입을 진행해주세요.
							</p>
							
							<p>
								<img src="./resources/image/started/SignUp2.png" width="500" height="300" style="margin: 10px;">
								<br/>
								위의 창이 나타나게되면 이메일과 비밀번호를 입력하여 회원가입을 합니다.
							</p>
							
							<p>
								<img src="./resources/image/started/SignUp3.png" width="500" height="300" style="margin: 10px;">
								<br/>
								우측 상단에 있는 Sign In 버튼을 클릭하여 회원가입했던 이메일과 비밀번호를 통해 로그인합니다.
							</p>
							
							<hr/>
							<h4>프로젝트 등록</h4>
							<p>
								<img src="./resources/image/started/Project1.png" width="500" height="300" style="margin: 10px;">
								<br/>
								로그인 후에 프로젝트를 등록해야합니다.<br/> 
								Developer 탭을 클릭하시면 현재 등록된 프로젝트 리스트들이 나타나게됩니다.<br/> 
								좌측 메뉴에 Register를 클릭하여 프로젝트를 등록합니다.<br/><br/>
							</p>
							
							<p>
								<img src="./resources/image/started/Project2.png" width="500" height="300" style="margin: 10px;">
								<br/>
								프로젝트는 프로젝트 이름과 Summary 그리고 Description을 입력하여 등록합니다.
							</p>
							
							<p>
								<img src="./resources/image/started/Project3.png" width="500" height="300" style="margin: 10px;">
								<br/>
								위와같이 등록된 프로젝트들의 리스트를 볼 수 있습니다.<br/>
								프로젝트는 최대 3개까지 등록이 가능합니다.
							</p>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>