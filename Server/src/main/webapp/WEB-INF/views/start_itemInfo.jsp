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
								<h2>IAP</h2>
							</div>
						</div>
						<br />
						<div class="row">
							
							<h4>Item 등록</h4>
							
							<p>
							아이템을 등록하고 관리할 수 있습니다. <br>
							등록된 아이템은 SDK에서 호출을 통해 별도의 업데이트 없이 동적으로 변경이 가능합니다.<br>
							</p>
							
							<p>
								<br/>
								<img src="./resources/image/started/item_menu.png" width="350" height="350" style="margin: 10px;">
								<br/>
								Virtual Store 탭을 클릭하시면 아이템을 관리할 수 있는 페이지로 이동하게 됩니다.<br>
								이동한 페이지에서 Add an Item 버튼을 클릭하시면 아이템 등록을 할 수 있는 창이 나타납니다.<br>
							</p>
							
							<p>
								<img src="./resources/image/started/item1.png" width="614" height="602" style="margin: 10px;">
								<br/>
								아이템의 대분류, 중분류, 소분류를 선택하신 후 아이템의 이름, 설명을 입력합니다.<br>
								<br>
								RAP에서는 2가지의 가상화폐도 지원을 하는데 이 아이템을 어떤 화폐로 구매할지 선택할 수 있습니다.<br>
								만약 현금결제를 원하신다면 Google Item Id를 입력하시면 됩니다.<br>
								아이템의 가격도 입력해주시고 추가버튼을 누르시면 정상 등록된것을 확인하실 수 있습니다. 
							</p>
							
							<p>
								<img src="./resources/image/started/item2.png" width="937" height="379" style="margin: 10px;">
								<br/>
								등록된 아이템은 카테고리를 설정하시면 보실 수 있습니다.
							</p>
							
							<hr/>
							
							<h4>Item 조회</h4>
							<p>
								아이템 조회는 4가지로 나뉘게 되는데<br> 
								전체 아이템 조회, 대분류를 통한 아이템 조회, 중분류를 통한 아이템 조회, 그리고 소분류를 통한 아이템 조회가 가능합니다.<br>
								각각은 다음과 같습니다.
							</p>
							
							<p>
								<b>RAPAPIs.getIAP_AllItems()</b><br>
								-전체 아이템 조회-
							</p>
							
							<p>
								<b>RAPAPIs.getIAP_CategoryL(String 대분류명)</b><br>
								-대분류 아이템 조회-
							</p>
							
							<p>
								<b>RAPAPIs.getIAP_CategoryM(String 대분류명, String 중분류명)</b><br>
								-중분류 아이템 조회-
							</p>
							
							<p>
								<b>RAPAPIs.getIAP_CategoryS(String 대분류명, String 중분류명, String 소분류명)</b><br>
								-소분류 아이템 조회-
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>