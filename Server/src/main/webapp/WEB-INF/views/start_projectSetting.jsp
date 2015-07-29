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
								<h2>Get Start</h2>
							</div>
						</div>
						<br />
						<div class="row">
							
							<h3>프로젝트 설정</h3>
							
							<p>
							각각의 프로젝트별로 프로젝트를 설정할 수 있습니다.<br/>
							프로젝트 설정은 크게 3가지로 나뉘게 되는데 <b>아이템설정</b>, <b>가상화폐 설정</b>, 그리고 <b>사용자 설정</b>으로 나뉘게 됩니다.
							</p>
							
							<p>
								<br/>
								<img src="./resources/image/started/Project_setting1.png" width="500" height="300" style="margin: 10px;">
								<br/>
								먼저 아이템설정에서는 아이템의 대분류, 중분류, 소분류를 등록합니다.
								후에 아이템을 등록할 때 사용될 카테고리를 등록할 수 있습니다.<br/><br/>
								등록된 카테고리를 삭제하려면 우측의 삭제버튼을 클릭하면 삭제할 수 있습니다.
							</p>
							
							<p>
								<img src="./resources/image/started/Project_setting2.png" width="500" height="300" style="margin: 10px;">
								<br/>
								다음은 가상화폐 설정입니다. 가상화폐는 크게 Main화폐와 Sub화폐로 나뉘게 되고 두 화폐의 기능은 동일합니다.<br>
								각 화폐의 이름과 설명을 등록한 후, 앱에서 이를 받아 사용하실 수 있습니다.
							</p>
							
							<p>
								<img src="./resources/image/started/Project_setting3.png" width="500" height="300" style="margin: 10px;">
								<br/>
								마지막으로 사용자 등급 설정이 있습니다.
								사용자를 관리할 때, 사용시간별 또는 과금액별로 사용자를 1등급에서 4등급까지 분류가 가능합니다. <br>
								프로젝트를 등록하면 기본설정으로 시간은 5시간, 3시간, 1시간 이 설정되어 있으며 <br>
								과금액 기준은 30000원, 20000원, 10000원으로 설정되어있습니다.
							</p>
							
							<hr/>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>