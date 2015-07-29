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
				alert("부화폐가 수정되었습니다.");
				coinlist_db();
			}
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
function coinlist_db()
{
	$.ajax({
		url : "coinlist_db",
		type : "POST",
		dataType : "JSON",
		success : function(data) {
			
			if(data!=null && data!="")
			{
				var mainlist = data.mainlist;
				var mainlistLen = mainlist.length;
				var sublist = data.sublist;
				var sublistLen = sublist.length;
				
				for(var i=0;i<mainlistLen;i++)
				{
					document.getElementById('virtual_main_name').placeholder = mainlist[i].name;
					document.getElementById('virtual_main_description').value = mainlist[i].description;
				}
				for(var i=0;i<sublistLen;i++)
				{
					document.getElementById('virtual_sub_name').placeholder = sublist[i].name;
					document.getElementById('virtual_sub_description').value = sublist[i].description;
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
	$.ajax({
	url : "EditGradeTime",
	type : "POST",
	data :
		{
		grade_time_input : document.getElementById('grade_time_input').value,
		grade_time : document.getElementById('grade_time').value,
		},
	dataType : "text",
	success : function(response) {
		if(response == "grade_time_input")
			alert("값을 입력해주세요.");
		else if(response == "grade_time")
			alert("사용시간 등급을 선택해주세요.");
		else if(response == "Not Number")
			alert("숫자를 입력해주세요.");
		else if(response == "L")
			alert("상 등급은 중 등급보다 값이 커야합니다.");
		else if(response == "M")
			alert("중 등급은 하 등급보다 값이 커야하고, 상 등급보다 값이 작아야합니다.");
		else if(response == "S")
			alert("하 등급은 중 등급보다 값이 작아야합니다.");
		else if(response == "200")
			alert("등급이 수정되었습니다.");
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
function EditGradeMoney()
{
	$.ajax({
	url : "EditGradeMoney",
	type : "POST",
	data :
		{
		grade_money_input : document.getElementById('grade_money_input').value,
		grade_money : document.getElementById('grade_money').value,
		},
	dataType : "text",
	success : function(response) {
		if(response == "grade_money_input")
			alert("값을 입력해주세요.");
		else if(response == "grade_money")
			alert("과금액 등급을 선택해주세요.");
		else if(response == "Not Number")
			alert("숫자를 입력해주세요.");
		else if(response == "L")
			alert("상 등급은 중 등급보다 값이 커야합니다.");
		else if(response == "M")
			alert("중 등급은 하 등급보다 값이 커야하고, 상 등급보다 값이 작아야합니다.");
		else if(response == "S")
			alert("하 등급은 중 등급보다 값이 작아야합니다.");
		else if(response == "200")
			alert("등급이 수정되었습니다.");
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
			alert("프로젝트 이름을 입력해주세요.");
		else if(response == "project_summary")
			alert("프로젝트 요약을 입력해주세요.");
		else if(response == "project_description")
			alert("프로젝트 설명을 입력해주세요.");
		else if(response == "overlap")
			alert("같은 이름의 프로젝트가 존재합니다.");
		else if(response == "200")
			{
			$('#ProjectEditModal').modal('hide');
			alert("프로젝트가 수정되었습니다.");
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

function registerGoogleProjectNum()
{
	$.ajax({
		url : "registerGoogleProjectNum",
		type : "POST",
		data :
			{
			google_project_num : document.getElementById('google_project_num_input').value
			},
		dataType : "text",
		success : function(response) {
			if(response == "google_project_num")
				alert("값을 입력해주세요.");
			else if(response == "200")
				alert("구글 프로젝트 넘버가 등록되었습니다.");
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
						

						ProjectInfo currentproject = null;
						String projectname = "";
						String projectdescription = "";
						String projectsummary = "";
						String projectkey = "";
						
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
							currentproject = (ProjectInfo) session.getAttribute("currentproject");
							projectname = currentproject.getProject_name();
							projectdescription = currentproject.getDescription();
							projectsummary = currentproject.getSummary();
							projectkey = currentproject.getPk();
							//Timestamp date = currentproject.getReg_date();
					%>
					<!-- project list 존재하는 경우 -->
					<div class="panel panel-default">
						<div class='panel-heading clearfix'>
							<h3 class='panel-title pull-left'><%=projectname%></h3>				
							<a style="cursor:pointer" data-toggle="modal" data-target="#ProjectEditModal" ><i class='fa fa-edit pull-right' style='margin-right: 4px;'></i></a>
							
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

					<br>
					<!-- Item Categorization -->
					<div class="row">
						<div class="col-lg-12 text-center">
							<h2>Item Categorization</h2>
						</div>
					</div>
					<hr class="star-primary"></hr>
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
					<br>
					<!-- Coin List -->
					<div class="row">
						<div class="col-lg-12 text-center">
							<h2>Coin List</h2>
						</div>
					</div>
					<hr class="star-primary"></hr>
					<div class="row">
						<!-- Virtual Main Coin -->
						<form class="form-inline">
							<div style="padding: 5px">
							<%
							List<Virtual_MainInfo> mainlist = (List<Virtual_MainInfo>) request.getAttribute("mainlist");
							int mainlistcount;

							if (mainlist == null)
								mainlistcount = 0;
							else
								mainlistcount = mainlist.size();
							
							if(mainlistcount == 0){
								out.println("<div class='col-lg-6'><label>Main Coin Name</label><input placeholder='Main Coin Name' type='text' class='form-control' id='virtual_main_name'></div>");
								out.println("<div class='col-lg-6'><label>Description</label><textarea placeholder='Description' class='form-control' id='virtual_main_description' style='height: 45px'></textarea>"
										+"<button class='btn' onclick='registerVirtualMain()'>Edit</button></div>");
							}
							else{
								out.println("<div class='col-lg-6'><label>Main Coin Name</label><input placeholder="+mainlist.get(0).getName()+" type='text' class='form-control' id='virtual_main_name'></div>");
								out.println("<div class='col-lg-6'><label>Description</label><textarea placeholder="+mainlist.get(0).getDescription()+" class='form-control' id='virtual_main_description' style='height: 45px'></textarea>"
										+"<button class='btn' onclick='registerVirtualMain()'>Edit</button></div>");
							}
								
							%>
							</div>
						</form>
						<!-- Virtual Sub Coin -->
						<form class="form-inline">
							<div style="padding: 5px">
							
							<%
								List<Virtual_SubInfo> sublist = (List<Virtual_SubInfo>) request.getAttribute("sublist");
								int sublistcount;
								if (sublist == null)
									sublistcount = 0;
								else
									sublistcount = sublist.size();
									
								if(sublistcount == 0){
									out.println("<div class='col-lg-6'><label>Sub Coin Name</label><input placeholder='Sub Coin Name' type='text' class='form-control' id='virtual_sub_name'></div>");
									out.println("<div class='col-lg-6'><label>Description</label><textarea placeholder='Description' class='form-control' id='virtual_sub_description' style='height: 45px'></textarea>"
											+"<button class='btn' onclick='registerVirtualSub()'>Edit</button></div>");
								}
								else{
									out.println("<div class='col-lg-6'><label>Sub Coin Name</label><input placeholder="+sublist.get(0).getName()+" type='text' class='form-control' id='virtual_sub_name'></div>");
									out.println("<div class='col-lg-6'><label>Description</label><textarea placeholder="+sublist.get(0).getDescription()+" class='form-control' id='virtual_sub_description' style='height: 45px'></textarea>"
											+"<button class='btn' onclick='registerVirtualSub()'>Edit</button></div>");
								}
							%>
							</div>
						</form>
					</div>
					<br>
					<!-- User Class -->
					<div class="row">
						<div class="col-lg-12 text-center">
							<h2>User Grade</h2>
						</div>
					</div>
					<%
						SettingInfo setting= (SettingInfo)request.getAttribute("setting");
						int moneyl=0, moneym=0, moneys=0, timel=0, timem=0, times=0;
						String google_num="";
						
						if(setting!=null)
						{
							moneyl = setting.getGrade_moneyl();
							moneym = setting.getGrade_moneym();
							moneys = setting.getGrade_moneys();
							timel = setting.getGrade_timel();
							timem = setting.getGrade_timem();
							times = setting.getGrade_times();
							google_num = setting.getGoogle_project_num();
						}
					%>
					<hr class="star-primary"></hr>
					<div class="row">
						<div style="padding: 5px">
							<form class="form-inline">
								<label style="padding:5px">Money</label>
								<input type="text" class="form-control" placeholder="상(<%= moneyl %>)" id="grade_moneyL">
								<input type="text" class="form-control" placeholder="중(<%= moneym %>)" id="grade_moneyM">
								<input type="text" class="form-control" placeholder="하(<%= moneys %>)" id="grade_moneyS">
								<button class="btn" onclick="registerGradeMoney()">Edit</button>
							</form>
						</div>
					</div>
					<div class="row">
						<div style="padding: 5px">
						<form class="form-inline">
							<label style="padding:5px">Time</label>
							<input type="text" class="form-control" placeholder="상(<%= timel %>)" id="grade_timeL">
							<input type="text" class="form-control" placeholder="중(<%= timem %>)" id="grade_timeM">
							<input type="text" class="form-control" placeholder="하(<%= times %>)" id="grade_timeS">
							<button class="btn" onclick="registerGradeTime()">Edit</button>
						</form>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-lg-12 text-center">
							<h2>Google Project Num</h2>
						</div>
					</div>
					<hr class="star-primary"></hr>
					<div class="row">
						<form class="form-inline">
							<label style="padding:5px">Google Project Num</label>
							<input type="text" class="form-control" placeholder="<%=google_num %>" id="google_project_num_input" style="width:500px">
							<button class="btn" onclick="registerGoogleProjectNum()">Edit</button>
						</form>
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
						<input name="modal_project_name" id="modal_project_name" type="text" class="form-control" placeholder="<%=projectname %>" />
					</div>
					<div class="form-group">
						<label>Project Summary</label> 
						<input
							name="modal_project_summary" id="modal_project_summary" type="text" class="form-control" placeholder="<%=projectsummary%>" />
					</div>
					<div class="form-group">
						<label>Project Description</label> 
						<textarea
							name="modal_project_description" id="modal_project_description" class="form-control" placeholder="<%=projectdescription%>"></textarea>
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
</html>