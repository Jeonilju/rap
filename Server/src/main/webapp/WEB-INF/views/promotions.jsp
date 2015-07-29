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
				else if(response == 'google_project_num')
					alert('프로젝트 세팅에서 구글 프로젝트 키를 입력해주세요.');
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
			
			if(data!=null && data!="")
			{
				var list = data.promotionlist;
				var listLen = list.length;
				var time,money,target;
				for(var i=0;i<listLen;i++)
				{
					if(list[i].grade_time == 0) time = '전체';
					else time = list[i].grade_time+' 등급';

					if(list[i].grade_money == 0) money = '전체';
					else money = list[i].grade_money+' 등급';
					
					if(list[i].target_activity == '') target = '해당없음';
					else target = list[i].target_activity;
					
					$('#plist').append("<div class='panel-heading clearfix'><h3 class='panel-title pull-left'>"
							+list[i].name
							+"</h3><a onclick='deletePromotion(\""+list[i].name+"\")' style='cursor:pointer'><i class='fa fa-trash pull-right'></i></a>"
							+"<a onclick='getPromotion(\""+list[i].name+"\")' style='cursor:pointer'><i class='fa fa-edit pull-right' style='margin-right: 4px;'></i></a></div>"
							+"<div class='panel-body'><div>summary : "
							+list[i].summary
							+"</div><div>사용시간 등급 : "+time+"</div>"
							+"<div>과금액 등급 : "+money+"</div>"
							+"<div>Target Activity : "+target+"</div>"
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

function deletePromotion(promotionname)
{
	if(confirm(promotionname+' 프로모션을 삭제하시겠습니까?'))
	{
		$.ajax({
			url : "deletePromotion",
			type : "POST",
			data : 
			{
				promotionname : promotionname
			},
			cache : false,
			async : false,
			dataType : "text",
	
			success : function(response) {								
				if(response=='200')
				{
					alert('삭제가 완료되었습니다.');
					location.reload();
				}
				else
				{
					alert("에러가 발생했습니다.")
					return false;
				}	
			},
			error : function(request, status, error) {
				if (request.status != '0') {
					alert("code : " + request.status + "\r\nmessage : "
							+ request.reponseText + "\r\nerror : " + error);
				}
			}
	
		});
	}
	else
	{
		alert('삭제가 취소되었습니다.');
		return;
	}
}
var promotionname;

function getPromotion(name)
{
	promotionname = name;

	$.ajax({
		url : "getpromotion",
		type : "POST",
		data : 
		{
			name : name
		},
		dataType : "JSON",
		success : function(data) {
			if(data!=null && data!="")
			{
				var promotion = data.promotion;
				
				document.getElementById('EditPromotionName').placeholder = promotion.name;
				document.getElementById('EditPromotionSummary').placeholder = promotion.summary;
				
				getEditGrade(promotion.grade_money, promotion.grade_time);
				getEditActivityList(promotion.target_activity);
			}
			else
			{
				getEditGrade(0, 0);
				getEditActivityList('');
			}
		}
	});

	$('#EditPromotionModal').modal();
}

function getEditGrade(money, time)
{
	if(money == 0)
		$('#Editgrade_money').html("<option value='0' selected>전체</option>");
	else
		$('#Editgrade_money').html("<option value='0'>전체</option>");
	for(var i=1;i<=4;i++)
		{
		if(money == i)
			$('#Editgrade_money').append("<option value = '"+i+"' selected>"+i+"</option>");
		else
			$('#Editgrade_money').append("<option value = '"+i+"'>"+i+"</option>");
		}
	
	if(time == 0)
		$('#Editgrade_time').html("<option value='0' selected>전체</option>");
	else
		$('#Editgrade_time').html("<option value='0'>전체</option>");
	for(var i=1;i<=4;i++)
		{
		if(time == i)
			$('#Editgrade_time').append("<option value = '"+i+"' selected>"+i+"</option>");
		else
			$('#Editgrade_time').append("<option value = '"+i+"'>"+i+"</option>");
		}
		
	$('#Editgrade_money').selectpicker('refresh');
	$('#Editgrade_time').selectpicker('refresh');
}
function getEditActivityList(val)
{
	$.ajax({
		url : "getactivitylist",
		type : "POST",
		dataType : "JSON",
		success : function(data) {
			
			if(data!=null && data!="")
			{
				var list = data.activitylist;
				var listLen = list.length;

				if(val == '')
 					$('#Edittarget_activity').html("<option value='' selected>해당없음</option>");
				else
					$('#Edittarget_activity').html("<option value=''>해당없음</option>");
				for(var i=0;i<listLen;i++)
				{
					if(val == list[i])
						$('#Edittarget_activity').append("<option value = '"+list[i]+"' selected>"+list[i]+"</option>");
					else
						$('#Edittarget_activity').append("<option value = '"+list[i]+"'>"+list[i]+"</option>");
						
					$('#Edittarget_activity').selectpicker('refresh');
				}
				
			}
		}
	});
}

function editPromotion()
{
	
	$.ajax({
		url : "editPromotion",
		type : "POST",
		data :
			{
			promotionname : promotionname,
			name : document.getElementById('EditPromotionName').value,
			summary : document.getElementById('EditPromotionSummary').value,
			grade_money : document.getElementById('Editgrade_money').value,
			grade_time : document.getElementById('Editgrade_time').value,
			target_activity : document.getElementById('Edittarget_activity').value
			},
		dataType : "text",
		success : function(response) {
			if(response == "name")
				alert("프로모션 이름을 입력해주세요.");
			else if(response == "summary")
				alert("프로모션 요약을 입력해주세요.");
			else if(response == "overlap")
				alert("같은 이름의 프로모션이 존재합니다.");
			else if(response == "200")
				{
				$('#EditPromotionModal').modal('hide');
				alert("프로모션이 수정되었습니다.");
				location.reload();
				}
			else
				alert("에러가 발생했습니다.");
		},
		error : function(request, status, error) {
			if (request.status != '0') {
				alert("code : " + request.status + "\r\nmessage : "
						+ request.reponseText + "\r\nerror : " + error);
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
				
				if(data!=null && data!="")
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
							<p class="text-center">Promotion Image</p>
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
							<div class="row form-inline" style="padding:5px">
									<label style="padding-left:38px;padding-right:38px">과금액</label>
									<select class="selectpicker" id="grade_money" name="grade_money">
										<option value="0" selected>전체</option>
										<option value="1">1 등급</option>
										<option value="2">2 등급</option>
										<option value="3">3 등급</option>
										<option value="4">4 등급</option>
									</select>
							</div>
							<div class="row form-inline" style="padding:5px">
									<label style="padding-left:30px;padding-right:30px">사용시간</label> 
									<select class="selectpicker" id="grade_time" name="grade_time">
										<option value="0" selected>전체</option>
										<option value="1">1 등급</option>
										<option value="2">2 등급</option>
										<option value="3">3 등급</option>
										<option value="4">4 등급</option>
									</select>
							</div>
							
							<div class="row form-inline" style="padding:5px">
									<label style="padding-left:10px;padding-right:10px">Target Activity</label> 
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

<!-- EditPromotionModal -->
<div class="modal fade" id="EditPromotionModal" tabindex="-1" role="dialog"
	aria-labelledby="EditPromotionModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="EditPromotionModalLabel">Promotion Edit</h4>
			</div>

			<form>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-4 portfolio-item">
							<a href="#"> <img class="img-responsive"
								src="http://placehold.it/700x400" alt="">
							</a>
							<p class="text-center">Promotion Image</p>
						</div>
						<div class="col-md-8 portfolio-item">

							<div class="row">
								<label>Promotion Name</label> <input type="text"
									class="form-control" placeholder="" id="EditPromotionName"
									name="EditPromotionName" required
									data-validation-required-message="Please enter Promotion Name.">
							</div>
							<div class="row">
								<label>Promotion Description</label> <input type="text"
									class="form-control" placeholder=""
									id="EditPromotionSummary" name="EditPromotionSummary" required
									data-validation-required-message="Please enter Promotion Description.">
							</div>
							<div class="row form-inline" style="padding:5px">
									<label style="padding-left:38px;padding-right:38px">과금액</label>
									<select class="selectpicker" id="Editgrade_money" name="Editgrade_money">
										<option value="0" selected>전체</option>
										<option value="1">1 등급</option>
										<option value="2">2 등급</option>
										<option value="3">3 등급</option>
										<option value="4">4 등급</option>
									</select>
							</div>
							<div class="row form-inline" style="padding:5px">
									<label style="padding-left:30px;padding-right:30px">사용시간</label> 
									<select class="selectpicker" id="Editgrade_time" name="Editgrade_time">
										<option value="0" selected>전체</option>
										<option value="1">1 등급</option>
										<option value="2">2 등급</option>
										<option value="3">3 등급</option>
										<option value="4">4 등급</option>
									</select>
							</div>
							
							<div class="row form-inline" style="padding:5px">
									<label style="padding-left:10px;padding-right:10px">Target Activity</label> 
									<select class="selectpicker" id="Edittarget_activity" name="Edittarget_activity"></select>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" onclick="editPromotion();">Edit</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
</div>
</html>
