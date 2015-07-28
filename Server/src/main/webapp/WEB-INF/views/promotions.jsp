<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, com.rap.models.PromotionInfo, com.rap.models.ProjectInfo, com.rap.models.MemberInfo"%>


<!DOCTYPE html>
<html lang="en">
<jsp:include page="nav.jsp" flush="false" />
<link href="./resources/font-awesome/css/font-awesome.css"
	rel="stylesheet" type="text/css">

<%
	ProjectInfo currentproject = (ProjectInfo)session.getAttribute("currentproject");
	String project_name;
	if(currentproject == null) project_name = "";
	else project_name = (String)currentproject.getProject_name();
%>
<script type="text/javaScript">

$(document).ready(function(){getpromotionlist()});

function sendpushmsg(promotionname)
{
	if(confirm('해당 프로모션으로 푸시메세지를 전송하시겠습니까?'))
	{
		$.ajax({
			url : "sendpushmsg",
			type : "POST",
			data : 
			{
				name : promotionname
			},
			dataType : "text",
			success : function(response) {
				if(response == '200')
				{
					alert('푸시 메세지를 전송했습니다.');
				}
				else
					alert('에러가 발생했습니다.');
			}
		});
	}
}
function getpromotionlist()
{
	$.ajax({
		url : "getpromotionlist",
		type : "POST",
		dataType : "JSON",
		success : function(data) {
			$('#plist').html("");
			
			if(data!=null || data!="")
			{
				var list = data.promotionlist;
				var listLen = list.length;
				var time,money;
				for(var i=0;i<listLen;i++)
				{
					if(list[i].grade_time == 0) time = '전체';
					else time = list[i].grade_time+' 등급';

					if(list[i].grade_money == 0) money = '전체';
					else money = list[i].grade_money+' 등급';
					
					$('#plist').append("<div class='panel-heading clearfix'><h3 class='panel-title pull-left'>"
							+list[i].name
							+"</h3><i class='fa fa-trash pull-right'></i> <i class='fa fa-edit pull-right' style='margin-right: 4px;'></i></div>"
							+"<div class='panel-body'><div>summary : "
							+list[i].summary
							+"</div><div>사용시간 등급 : "+time+"</div>"
							+"<div>과금액 등급 : "+money+"</div>"
							+"<div>타겟 액티비티 : "+list[i].target_activity+"</div>"
							+"</div><div class='panel-footer'><a class='btn btn-default pull-right' onclick=\"sendpushmsg('"+ list[i].name + "')\"> 푸시 알림 </a> <br><br></div><br>");
				}
				
				if(listLen==0)
				{
					$('#plist').append("<div class='panel panel-default' style='margin-top: 10px;'>"
					+"<div class='panel-heading clearfix'></div><div class='panel-body'>"
					+"<center><p>No Promotion</p></center></div><div class='panel-footer'></div></div>");
					
				}
			}
			else
			{
				$('#plist').append("<div class='panel panel-default' style='margin-top: 10px;'>"
				+"<div class='panel-heading clearfix'></div><div class='panel-body'>"
				+"<center><p>No Promotion</p></center></div><div class='panel-footer'></div></div>");
				
			}
		}
	});
	
}
	
</script>

<body>

	<div class="container">
		<div id="wrapper">
			<!-- sidebar-wrapper -->
			<jsp:include page="projectnav.jsp" flush="false" />
			<!-- /#sidebar-wrapper -->
			<!--contents-->
			<div id="page-content-wrapper">
				<div class="container-fluid">


					<div class="col-lg-12 text-center">
						<BR> <BR> <BR> <BR> <BR> <BR>
						<h2>Promotions Administration</h2>
					</div>
				</div>

				<%
				MemberInfo member = (MemberInfo)session.getAttribute("currentmember");
				String email = "";
			
				if(member != null) email = member.getEmail();
			
				if(email == null || email.isEmpty())
				{
				%>
				<!-- 이메일이 세션에 존재하지 않는 경우 -->
				<div class="panel panel-default" style="margin-top: 10px;">
					<div class="panel-heading clearfix"></div>
					<div class="panel-body">
						<center>
							<p>로그인해주세요.</p>
						</center>
					</div>
					<div class="panel-footer"></div>
				</div>
				<%
					}
					else
					{
				%>
				<div class="bootcards-list">
					<div class="panel panel-default">
						<div class="panel-body">
							<form id="promotionForm" name="promotionForm">
								<div class="row">
									<div class="col-lg-9">
										<div class="form-group">
											<div class="row">
												
											</div>
										</div>
									</div>
									<div class="col-lg-3">
										<button id='addbutton' type='button' class='btn btn-success btn-block'
										data-toggle='modal' data-target='#PromotionModal'>
										<i class='fa fa-plus'></i>Add</button>
									</div>
								</div>
							</form>
						</div>
						<!-- promotion list 존재하는 경우 -->
						<div class="panel panel-default" id="plist"></div>

						<%
								
							}
						%>
					</div>
				</div>
			</div>
			<!--/.container-fluid-->
		</div>
		<!--/.contents-->
	</div>
</body>

<script>

$(function(){
	$("#addbutton").click(function(){
		$('#target_activity').html("<option value='' selected>해당없음</option>");
		
		$.ajax({
			url : "getactivitylist",
			type : "POST",
			dataType : "JSON",
			success : function(data) {
				
				if(data!=null || data!="")
				{
					var list = data.activitylist;
					var listLen = list.length;
					
					for(var i=0;i<listLen;i++)
					{
						$('#target_activity').append("<option value = '"+list[i]+"'>"+list[i]+"</option>");	
						$('#target_activity').selectpicker('refresh');
					}
					
				}
			}
		});
		
		
	})
})
function registerPromotion() {

	$.ajax({
		url : "registerPromotion",
		type : "POST",
		data : 
		{
			name : document.getElementById('PromotionName').value,
			summary : document.getElementById('PromotionSummary').value,
			grade_money : document.promotionAddForm.grade_money.value,
			grade_time : document.promotionAddForm.grade_time.value,
			target_activity : document.promotionAddForm.target_activity.value
		},
		dataType : "text",
		success : function(response) {
			if(response == '200')
			{
				getpromotionlist();
				$('#PromotionModal').modal('hide');	
				alert('프로모션이 등록되었습니다.');
			}
			else if(response == 'overlap')
				alert('같은 이름의 프로모션이 존재합니다.');
			else if(response == 'name')
				alert('이름을 입력해주세요.');
			else if(response == 'summary')
				alert('요약을 입력해주세요.');
			else if(response == 'target_activity')
				alert('타겟 액티비티를 선택해주세요.');
			else
				alert('에러가 발생했습니다.');
		}
	});
	
}
</script>

<!-- AddPromotionModal -->
<div class="modal fade" id="PromotionModal" tabindex="-1" role="dialog"
	aria-labelledby="PromotionModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Promotion Add</h4>
			</div>

			<form action="promotion_db" method="POST" id="promotionAddForm"
				name="promotionAddForm">
				<div class="modal-body">
					<div class="row">
						<div class="col-md-4 portfolio-item">
							<a href="#"> <img class="img-responsive"
								src="http://placehold.it/700x400" alt="">
							</a>
							<p class="text-center">Item Image</p>
						</div>
						<div class="col-md-8 portfolio-item">

							<div class="row">
								<label>Promotion Name</label> <input type="text"
									class="form-control" placeholder="Promotion Name" id="PromotionName"
									name="PromotionName" required
									data-validation-required-message="Please enter Promotion Name.">
							</div>
							<div class="row">
								<label>Promotion Description</label> <input type="text"
									class="form-control" placeholder="Promotion Description"
									id="PromotionSummary" name="PromotionSummary" required
									data-validation-required-message="Please enter Promotion Description.">
							</div>
							<div class="row" style="padding:5px">
								<label>과금액</label>
								<select class="selectpicker" id="grade_money" name="grade_money">
									<option value="0" selected>전체</option>
									<option value="1">1 등급</option>
									<option value="2">2 등급</option>
									<option value="3">3 등급</option>
									<option value="4">4 등급</option>
								</select>
							</div>
							<div class="row" style="padding:5px">
								<label>사용시간</label> 
								<select class="selectpicker" id="grade_time" name="grade_time">
									<option value="0" selected>전체</option>
									<option value="1">1 등급</option>
									<option value="2">2 등급</option>
									<option value="3">3 등급</option>
									<option value="4">4 등급</option>
								</select>
							</div>
							
							<div class="row" style="padding:5px">
								<label>타겟 액티비티</label> 
								<select class="selectpicker" id="target_activity" name="target_activity"></select>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" onclick="registerPromotion();">Add</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
</div>

</html>
