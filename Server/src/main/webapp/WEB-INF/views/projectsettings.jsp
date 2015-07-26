<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, com.rap.models.ProjectInfo, com.rap.models.MemberInfo, com.rap.models.CategoryLInfo, com.rap.models.Virtual_MainInfo, com.rap.models.Virtual_SubInfo, com.rap.models.SettingInfo"%>

<html lang="en">
<!-- 네비게이션바 인클루드 -->
<jsp:include page="nav.jsp" flush="false" />
<script src="./resources/js/itemcategorization.js"></script>
<script>
function getAllLcategory()
{
	var id = ['#Lcategory1','#Lcategory2','#Lcategory3'];
	getLcategory(id);
}
function registerVirtualMain()
{
	$.ajax({
		url : "registerVirtualMain",
		type : "POST",
		data :
			{
			virtual_main_name: document.getElementById('virtual_main_name').value,
			virtual_main_description: document.getElementById('virtual_main_description').value
			},
		dataType : "text",
		success : function(response) {
			if(response == "200")
			{
				alert("주화폐가 등록되었습니다.");
				coinlist_db();
			}
			else if(response == "Already Exist")
				alert("주화폐가 이미 존재합니다.");
			else if(response == "virtual_main_name")
				alert("이름을 입력해주세요.");
			else if(response == "virtual_main_description")
				alert("설명을 입력해주세요.");
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
function deleteVirtualMain()
{
	$.ajax({
		url : "deleteVirtualMain",
		type : "POST",
		data :
			{
			virtual_main_name: document.getElementById('virtual_main').value
			},
		dataType : "text",
		success : function(response) {
			if(response == "200")
			{
				alert("주화폐가 삭제되었습니다.");
				coinlist_db();
			}
			else if(response == "virtual_main_name")
				alert("주화폐를 선택해주세요.");
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
function registerVirtualSub()
{
	$.ajax({
		url : "registerVirtualSub",
		type : "POST",
		data :
			{
			virtual_sub_name: document.getElementById('virtual_sub_name').value,
			virtual_sub_description: document.getElementById('virtual_sub_description').value
			},
		dataType : "text",
		success : function(response) {
			if(response == "200")
			{
				alert("부화폐가 등록되었습니다.");
				coinlist_db();
			}
			else if(response == "Already Exist")
				alert("부화폐가 이미 존재합니다.");
			else if(response == "virtual_sub_name")
				alert("이름을 입력해주세요.");
			else if(response == "virtual_sub_description")
				alert("설명을 입력해주세요.");
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
function deleteVirtualSub()
{
	$.ajax({
		url : "deleteVirtualSub",
		type : "POST",
		data :
			{
			virtual_sub_name: document.getElementById('virtual_sub').value
			},
		dataType : "text",
		success : function(response) {
			if(response == "200")
			{
				alert("부화페가 삭제되었습니다.");
				coinlist_db();
			}
			else if(response == "virtual_sub_name")
				alert("부화폐를 선택해주세요.");
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
function coinlist_db()
{
	$('#virtual_main').html("<option value='' selected>주화폐</option>");
	$('#virtual_sub').html("<option value='' selected>부화폐</option>");

	$.ajax({
		url : "coinlist_db",
		type : "POST",
		dataType : "JSON",
		success : function(data) {
			
			if(data!=null || data!="")
			{
				var mainlist = data.mainlist;
				var mainlistLen = mainlist.length;
				var sublist = data.sublist;
				var sublistLen = sublist.length;
				
				for(var i=0;i<mainlistLen;i++)
				{
					$('#virtual_main').append("<option value='"+mainlist[i].name+"'>"+mainlist[i].name+"</option>");
				}
				for(var i=0;i<sublistLen;i++)
				{
					$('#virtual_sub').append("<option value='"+sublist[i].name+"'>"+sublist[i].name+"</option>");
				}
			}
		},

		error : function(request, status, error) {
			if (request.status != '0') {
				alert("code : " + request.status + "\r\nmessage : "
						+ request.reponseText + "\r\nerror : " + error);
			}
		}
	});
	$('#virtual_main').selectpicker('refresh');
	$('#virtual_sub').selectpicker('refresh');
}

function registerGradeMoney()
{
	$.ajax({
		url : "registerGradeMoney",
		type : "POST",
		data :
			{
			grade_moneyL: document.getElementById('grade_moneyL').value,
			grade_moneyM: document.getElementById('grade_moneyM').value,
			grade_moneyS: document.getElementById('grade_moneyS').value
			},
		dataType : "text",
		success : function(response) {
			if(response == "200")
			{
				alert("구매등급이 등록되었습니다.");
			}
			else if(response == "Not Number")
				alert("숫자만 입력 가능합니다.");
			else if(response == "Not Greater")
				alert("크기순으로 등급기준을 입력해주세요.");
			else if(response == "grade_moneyL")
				alert("상을 입력해주세요.");
			else if(response == "grade_moneyM")
				alert("중을 입력해주세요.");
			else if(response == "grade_moneyS")
				alert("하를 입력해주세요.");
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
function getGradeMoney()
{
	$.ajax({
	url : "getGradeMoney",
	type : "POST",
	data :
		{
		grade_money : document.getElementById('grade_money').value
		},
	dataType : "text",
	success : function(response) {
		if(response == "grade_money")
			alert("구매등급을 선택해주세요.");
		else if(response == "error")
			alert("에러가 발생했습니다.");
		else
		{
			document.getElementById('grade_money_input').placeholder = response;
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

function registerGradeTime()
{
	$.ajax({
		url : "registerGradeTime",
		type : "POST",
		data :
			{
			grade_timeL: document.getElementById('grade_timeL').value,
			grade_timeM: document.getElementById('grade_timeM').value,
			grade_timeS: document.getElementById('grade_timeS').value
			},
		dataType : "text",
		success : function(response) {
			if(response == "200")
			{
				alert("사용등급이 등록되었습니다.");
			}
			else if(response == "Not Number")
				alert("숫자만 입력 가능합니다.");
			else if(response == "Not Greater")
				alert("크기순으로 등급기준을 입력해주세요.");
			else if(response == "grade_timeL")
				alert("상을 입력해주세요.");
			else if(response == "grade_timeM")
				alert("중을 입력해주세요.");
			else if(response == "grade_timeS")
				alert("하를 입력해주세요.");
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
function getGradeTime()
{
	$.ajax({
	url : "getGradeTime",
	type : "POST",
	data :
		{
		grade_time : document.getElementById('grade_time').value
		},
	dataType : "text",
	success : function(response) {
		if(response == "grade_time")
			alert("사용등급을 선택해주세요.");
		else if(response == "error")
			alert("에러가 발생했습니다.");
		else
		{
			document.getElementById('grade_time_input').placeholder = response;
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

function EditGradeTime()
{
	
}

function ProjectEdit()
{

	$.ajax({
	url : "ProjectEdit",
	type : "POST",
	data :
		{
		project_name : document.getElementById('modal_project_name').value,
		project_summary : document.getElementById('modal_project_summary').value,
		project_description : document.getElementById('modal_project_description').value
		},
	dataType : "text",
	success : function(response) {
		if(response == "project_name")
			alert("프로젝트 이름을 선택해주세요.");
		else if(response == "project_summary")
			alert("프로젝트 요약을 선택해주세요.");
		else if(response == "project_description")
			alert("프로젝트 설명을 선택해주세요.");
		else if(response == "overlap")
			alert("같은 이름의 프로젝트가 존재합니다.");
		else if(response == "200")
			alert("프로젝트가 수정되었습니다.");
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

<!-- ProjectEditModal -->
<div class="modal fade" id="ProjectEditModal" tabindex="-1" role="dialog"
	aria-labelledby="ProjectEditModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="ProjectEditModalLabel">Edit Project</h4>
			</div>

			<form>
				<div class="modal-body">
					<div class="form-group">
						<label>Project Name</label> 
						<input
							name="modal_project_name" id="modal_project_name" type="text" class="form-control" placeholder="Project Name" />
					</div>
					<div class="form-group">
						<label>Project Summary</label> 
						<input
							name="modal_project_summary" id="modal_project_summary" type="text" class="form-control" placeholder="Project Summary" />
					</div>
					<div class="form-group">
						<label>Project Description</label> 
						<textarea
							name="modal_project_description" id="modal_project_description" class="form-control" placeholder="Project Description"></textarea>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						Close</button>											
					<button type="button"  class="btn btn-primary" onclick="ProjectEdit()">Edit</button>
						
				</div>
			</form>


		</div>
		<!-- /.modal-content -->
	</div>
</div>
<!-- /.modal -->
<body id="page-top" class="index">
	<div class="container">
		<!-- wrapper -->
		<div id="wrapper">
			<!-- sidebar-wrapper -->
			<jsp:include page="projectnav.jsp" flush="false" />
			<!-- /#sidebar-wrapper -->

			<!-- page-content-wrapper -->
			<div id="page-content-wrapper">
				<div class="container-fluid">
					<!-- Item Categorization -->
					<div class="row">
						<div class="col-lg-12 text-center">
							<BR> <BR> <BR> <BR> <BR>
						</div>
					</div>
					<%
						MemberInfo member = (MemberInfo) session.getAttribute("currentmember");
						String email = "";

						if (member != null)
							email = member.getEmail();

						if (email == null || email.isEmpty()) {
					%>
					<!-- 로그인하지 않은 경우 -->
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<h3 class="panel-title pull-left"></h3>
						</div>
						<div class="panel-body">
							<div>로그인해주세요.</div>
						</div>
					</div>

					<%
						} else {
							ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");
							String projectname = currentproject.getProject_name();
							String projectdescription = currentproject.getDescription();
							String projectsummary = currentproject.getSummary();
							String projectkey = currentproject.getPk();
							//Timestamp date = currentproject.getReg_date();
					%>
					<!-- project list 존재하는 경우 -->
					<div class="panel panel-default">
						<div class='panel-heading clearfix'>
							<h3 class='panel-title pull-left'><%=projectname%></h3>				
							<a data-toggle="modal" data-target="#ProjectEditModal" ><i class='fa fa-edit pull-right' style='margin-right: 4px;'></i></a>
							
						</div>
						<div class='panel-body'>
							<div>
								<div>summary : <%=projectsummary%></div>
	                            <div>description : <%=projectdescription%></div>
	                            <div>projectkey : <%=projectkey%></div>
							</div>
						</div>
						<div class='panel-footer'></div>	
					</div>

					<!-- Item Categorization -->
					<div class="row">
						<div class="col-lg-12 text-center">
							<h2>Item Categorization</h2>
						</div>
					</div>
					<div class="row">
						<!-- Large Category -->
						<form class="form-inline">
							<div class="col-md-8" style="padding: 5px">
								<input type="text" class="form-control"
									placeholder="Large Category" id="CategoryL" style="width: 200px">
								<button class="btn" onclick="registerLcategory()">Register</button>
							</div>
							<div class="col-md-4" style="padding: 5px">
								<button class="btn pull-right" onclick="deleteLcategory('Lcategory1')">Delete</button>
								<select id="Lcategory1" name="Lcategory1" class="selectpicker pull-right">
									<option value='' selected>대분류</option>
									<%
										List<CategoryLInfo> categoryLlist = (List<CategoryLInfo>) request.getAttribute("categoryLlist");

										int categoryLlistcount;
										if (categoryLlist == null)
											categoryLlistcount = 0;
										else
											categoryLlistcount = categoryLlist.size();

										for (int i = 0; i < categoryLlistcount; i++) {
											out.println("<option value='" + categoryLlist.get(i).getCategoryL() + "'>"
													+ categoryLlist.get(i).getCategoryL() + "</option>");
										}
									%>
								</select>
							</div>
						</form>
						<!-- Medium Category -->
						<form class="form-inline">
							<div class="col-md-8" style="padding: 5px">
								<select class="selectpicker" id="Lcategory2" name="Lcategory2"
									onchange="getMcategory('2')">
									<option value='' selected>대분류</option>
									<%
										for (int i = 0; i < categoryLlistcount; i++) {
												out.println("<option value='" + categoryLlist.get(i).getCategoryL() + "'>"
														+ categoryLlist.get(i).getCategoryL() + "</option>");
											}
									%>
								</select> <input type="text" class="form-control"
									placeholder="Medium Category" id="CategoryM" style="width: 200px">
								<button class="btn" onclick="registerMcategory()">Register</button>
							</div>
							<div class="col-md-4" style="padding: 5px">
								<button class="btn pull-right" onclick="deleteMcategory('Mcategory2')">Delete</button>
								<select class="selectpicker pull-right" id="Mcategory2" name="Mcategory2">
									<option value='' selected>중분류</option>
								</select>
							</div>
						</form>

						<!-- Small Category -->
						<form class="form-inline">
							<div class="col-md-8" style="padding: 5px">
								<select class="selectpicker" id="Lcategory3" name="Lcategory3"
									onchange="getMcategory('3')">
									<option value='' selected>대분류</option>
									<%
										for (int i = 0; i < categoryLlistcount; i++) {
												out.println("<option value='" + categoryLlist.get(i).getCategoryL() + "'>"
														+ categoryLlist.get(i).getCategoryL() + "</option>");
											}
									%>
								</select> <select class="selectpicker" id="Mcategory3" name="Mcategory3"
									onchange="getScategory('3')">
									<option value='' selected>중분류</option>
								</select> <input type="text" class="form-control"
									placeholder="Small Category" id="CategoryS" style="width: 200px">
								<button class="btn" onclick="registerScategory()">Register</button>
							</div>
							<div class="col-md-4" style="padding: 5px">
								<button class="btn pull-right" onclick="deleteScategory('Scategory3')">Delete</button>
								<select class="selectpicker pull-right" id="Scategory3" name="Scategory3">
									<option value="" selected>소분류</option>
								</select>
							</div>
						</form>
					</div>
					<!-- Coin List -->
					<br>
					<div class="row">
						<div class="col-lg-12 text-center">
							<h2>Coin List</h2>
						</div>
					</div>
					<div class="row">
						<!-- Virtual Main Coin -->
						<form class="form-inline">
							<div class="col-md-8" style="padding: 5px">
								<input placeholder="Main Coin Name" type="text" class="form-control" id="virtual_main_name" style="width: 200px">
								<textarea placeholder="Description" class="form-control" id="virtual_main_description" style="height: 45px"></textarea>
								<button class="btn" onclick="registerVirtualMain()">Register</button>
							</div>
							<div class="col-md-4" style="padding: 5px">
								<button class="btn pull-right" onclick="deleteVirtualMain()">Delete</button>
								<select id="virtual_main" name="virtual_main" class="selectpicker pull-right">
									<option value='' selected>주화폐</option>
									<%
										List<Virtual_MainInfo> mainlist = (List<Virtual_MainInfo>) request.getAttribute("mainlist");

											int mainlistcount;
											if (mainlist == null)
												mainlistcount = 0;
											else
												mainlistcount = mainlist.size();

											for (int i = 0; i < mainlistcount; i++) {
												out.println("<option value='" + mainlist.get(i).getName() + "'>"
														+ mainlist.get(i).getName()  + "</option>");
											}
									%>
								</select>
							</div>
						</form>
						<!-- Virtual Sub Coin -->
						<form class="form-inline">
							<div class="col-md-8" style="padding: 5px">
								<input placeholder="Sub Coin Name" type="text" class="form-control" id="virtual_sub_name" style="width: 200px">
								<textarea placeholder="Description" class="form-control" id="virtual_sub_description" style="height: 45px"></textarea>
								<button class="btn" onclick="registerVirtualSub()">Register</button>
							</div>
							<div class="col-md-4" style="padding: 5px">
								<button class="btn pull-right" onclick="deleteVirtualSub()">Delete</button>
								<select id="virtual_sub" name="virtual_sub" class="selectpicker pull-right">
									<option value='' selected>부화폐</option>
									<%
										List<Virtual_SubInfo> sublist = (List<Virtual_SubInfo>) request.getAttribute("sublist");

											int sublistcount;
											if (sublist == null)
												sublistcount = 0;
											else
												sublistcount = sublist.size();

											for (int i = 0; i < sublistcount; i++) {
												out.println("<option value='" + sublist.get(i).getName() + "'>"
														+ sublist.get(i).getName()  + "</option>");
											}
									%>
								</select>
							</div>
						</form>
					</div>
					<!-- User Class -->
					<br>
					<div class="row">
						<div class="col-lg-12 text-center">
							<h2>User Grade</h2>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6" style="padding: 5px">
						<form class="form-inline">
							<label style="padding:5px">Money</label>
							<input type="text" class="form-control" placeholder="상" id="grade_moneyL" style="width: 70px">
							<input type="text" class="form-control" placeholder="중" id="grade_moneyM" style="width: 70px">
							<input type="text" class="form-control" placeholder="하" id="grade_moneyS" style="width: 70px">
							<button class="btn" onclick="registerGradeMoney()">Register</button>
						</form>
						</div>
						
						<div class="col-lg-6" style="padding: 5px">
						<form class="form-inline">
							<button class="btn pull-right" onclick="EditGradeMoney()">Edit</button>
							 <input type="text" class="form-control pull-right"
								placeholder="" id="grade_money_input" style="width: 100px">
							<select class="selectpicker pull-right" id="grade_money" name="grade_money" onchange="getGradeMoney()">
								<option value='' selected>과금액</option>
								<option value='L'>상</option>
								<option value='M'>중</option>
								<option value='S'>하</option>
							</select>
						</form>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6" style="padding: 5px">
						<form class="form-inline">
							<label style="padding:5px">Time</label>
							<input type="text" class="form-control" placeholder="상" id="grade_timeL" style="width: 70px">
							<input type="text" class="form-control" placeholder="중" id="grade_timeM" style="width: 70px">
							<input type="text" class="form-control" placeholder="하" id="grade_timeS" style="width: 70px">
							<button class="btn" onclick="registerGradeTime()">Register</button>
						</form>
						</div>
						
						<div class="col-lg-6" style="padding: 5px">
						<form class="form-inline">
							<button class="btn pull-right" onclick="EditGradeTime()">Edit</button>
							<input type="text" class="form-control pull-right"
								placeholder="" id="grade_time_input" style="width: 100px">
							<select class="selectpicker pull-right" id="grade_time" name="grade_time" onchange="getGradeTime()">
								<option value='' selected>사용시간</option>
								<option value='L'>상</option>
								<option value='M'>중</option>
								<option value='S'>하</option>
							</select> 
						</form>
						</div>
					</div>
					<br><br>
					<%
						}
					%>
				</div>
			</div>
		</div>
	</div>
	<!-- /#page-content-wrapper -->

	</div>
	<!-- /#ëí¼ -->

	</div>
</body>

</html>
