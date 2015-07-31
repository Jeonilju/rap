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
								<h2>아이템 결제</h2>
							</div>
						</div>
						<br />
						<div class="row">
							
							<h4>가상화폐를 통한 결제</h4>
							
							<p>
							RAP에선 Main화폐와 Sub화폐를 제공합니다. <br>
							사용자별로 각각 Main화폐와 Sub화폐를 가지고있습니다.<br>
							먼저 화폐에 관련된 API를 살펴보면 다음과 같습니다.<br>
							</p>
							
							<hr>
							
							<p style="margin: 20">
								<br/>
								<b>CheckVirtual_Main()</b><br/>
								-Main 화폐에 대한 정보 조회-
							</p>
							
							<p style="margin: 20">
								<br/>
								<b>CheckVirtual_Sub()</b><br/>
								-Sub 화폐에 대한 정보 조회-
							</p>
							
							<p style="margin: 20">
								<br/>
								<img src="./resources/image/started/curreny_json.png" width="351" height="115" style="margin: 10px;">
								<br>
								화폐정보를 조회했을 떄의 받는 정보이다. <br>
								pk는 화폐의 id를 의미하고<br>
								key는 프로젝트의 Key,<br>
								name은 화폐의 이름으로 프로젝트 설정에서 설정한 값이다.<br>
								image는 등록한 화폐의 이미지의 경로,<br>
								description은 화폐의 설명,<br>
								reg_date는 화폐 등록일을 의미한다.<br>
							</p>
							
							<hr>
							
							<p style="margin: 20">
								<br/>
								<b>GetVirtual_Main()</b><br/>
								-사용자의 Main 화폐 정보 조회-
							</p>
							
							<p style="margin: 20">
								<br/>
								<b>CheckVirtual_Sub()</b><br/>
								-사용자의 Sub 화폐 정보 조회-
							</p>
							
							<p style="margin: 20">
								<br/>
								<img src="./resources/image/started/point_json.png" width="241" height="100" style="margin: 10px;">
								사용자의 화폐정보를 조회했을 떄의 받는 정보이다. <br>
								Point는 사용자의 잔여 화폐량을 의미한다.<br>
							</p>
							
							<hr>
							
							<p style="margin: 20">
								<br/>
								<b>UseVirtual_Main(int 차감량)</b><br/>
								-사용자의 Main 화폐 차감-
							</p>
							
							<p style="margin: 20">
								<br/>
								<b>UseVirtual_Sub(int 추가량)</b><br/>
								-사용자의 Sub 화폐 차감-
							</p>
							
							<hr>
							
							<p style="margin: 20">
								<br/>
								<b>TakeVirtual_Main(int 추가량)</b><br/>
								-사용자의 Main 화폐 추가-
							</p>
							
							<p style="margin: 20">
								<br/>
								<b>TakeVirtual_Sub(int 추가량)</b><br/>
								-사용자의 Sub 화폐 추가-
							</p>
							
							<hr>
							
							<p>
								<br/>
								다음은 아이템을 구매하는 API입니다. 
								<br/>
								<b>BuyItemByMain(int 아이템 ID)</b><br/>
								
								<br/>
								<b>BuyItemBySub(int 아이템 ID)</b><br/>
							</p>
							
							<hr>
							
							<p>
								<img src="./resources/image/started/item_rank.png" width="673" height="414" style="margin: 10px;">
								<br/>
								이렇게 누적된 아이템 판매는 위와같이 사용자가 어떤 아이템을 많이 구매하였는지 확인하실 수 있습니다.<br>
								기본 사용자 정보를 통해 필터링하여 특정 분류의 사용자의 소비패턴도 확인하실 수 있습니다.<br>
							</p>
							
							<p>
								<img src="./resources/image/started/item_among.png" width="673" height="568" style="margin: 10px;">
								<br/>
								또한 기간별로 얼마나 사용자가 구매하였는지도 확인하실 수 있습니다.<br>
							</p>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>