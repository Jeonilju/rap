<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
						<jsp:include page="faq-nav.jsp" flush="false" />
					</ul>
				</div>
				
				<!--  page-wrapper -->
				<div id="page-content-wrapper">
					<div class="container-fluid">
						<!-- Application Registration -->
						<div class="row">
							<div class="col-lg-12 text-center">
								<BR><BR><BR><BR><BR><BR>
								<h2>FAQ</h2>
							</div>
						</div>
						<br />
						<div class="row">
							
							<h4>Q1. 프로젝트 Key를 초기화 하지 않으면 어떻게 되나요?</h4>
							
							<p>
								<br/>
								<img src="./resources/image/started/project_key1.png" width="1021" height="58" style="margin: 10px;">
								<br/>
								위와같이 에러로그가 나타나게됩니다.<br> 
								프로젝트 Key를 꼭 초기화 한 뒤 사용해주세요<br>
							</p>
							
							<hr style="margin: 20">
							
							<h4>Q2. 프로젝트 Key를 잘못 입력하면 어떻게 되나요?</h4>
							
							<p>
								<br/>
								<br/>
								모든 요청에 대해서 Http Status 401을 받게됩니다.<br>
								401 상태를 받으시면 프로젝트 Key에 문제가 발생된것을 의미합니다.<br>
							</p>
							
							<hr style="margin: 20">
							
							<h4>Q3. 네트워크 상태가 불안정하면 어떻게되나요?</h4>
							
							<p>
								글세요...
							</p>
							
							<hr style="margin: 20">
							
							<h4>Q4. GPS에서 현재 위치를 가져올 수 없는 경우엔 어떻게 되나요?</h4>
							
							<p>
								GPS에서 위치를 불러올 수 없는 경우 네트워크를 통해 현재 위치를 받아옵니다.<br>
								네트워크를 통해서도 위치를 알 수 없다면 현재 사용자 위치를 설정하지 않습니다.<br>
								이럴경우 잠시후에 다시 호출해주시면 잘 작동할 수 있습니다.
							</p>
							
							<hr>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>