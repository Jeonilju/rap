<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="en">
<!-- 네비게이션바 인클루드 -->
<jsp:include page="nav.jsp" flush="false" />

<!-- Custom CSS -->
<link href="../dist/css/sb-admin-2.css" rel="stylesheet">
   
    <!-- Custom Fonts -->
    <link href="./resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script>
function registerProject(){
	
	var param = "project_name" + "=" + $("#project_name").val()
	 + "&" + "summary" + "=" + $("#summary").val()
	 + "&" + "description" + "=" + $("#description").val();
	
	
	$.ajax({
		url : "register_db",
		type : "POST",
		data : param,
		cache : false,
		async : false,
		dataType : "text",

		success : function(response) {				
			//중복되는 프로젝트 이름 존재않음
			if(response=='0')
			{
				location.href='projectsettings';
				alert("프로젝트가 등록되었습니다.")
				
			}		
			//한 사용자내에서 똑같은 프로젝트 이름 존재
			else if(response=='1')
			{
				alert("프로젝트 이름이 중복이 됩니다. 다시 입력 해주세요");
				return false;
			}
			//이메일 값이 존재하지 않는 경우
			else if(response=='2')
			{
				alert("로그인해주세요.");
				return false;
			}
			//이메일 값이 존재하지 않는 경우
			else if(response=='3')
			{
				alert("등록가능한 프로젝트 수를 초과했습니다.");
				return false;
			}
			else
			{
				alert("예기치못한 에러 발생.");
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

function registerFormCheck()
{
	//폼 체크
	if($("#project_name").val() == '' || $("#project_name").val()==null)
	{
		alert("프로젝트 이름을 입력하세요");
		return false;
	}
	if($("#summary").val() == '' || $("#summary").val()==null)
	{
		alert("프로젝트 요약을 입력하세요");
		return false;
	}
	if($("#description").val() == '' || $("#description").val()==null)
	{
		alert("프로젝트 설명을 입력하세요");
		return false;
	}
	
	registerProject();
	
}

</script>

<body id="page-top" class="index">
	<div class="container">
		<!-- wrapper -->
		<div id="wrapper">
			<!-- sidebar-wrapper -->
			<div id="sidebar-wrapper">
				<ul class="sidebar-nav">
					<li><br>
					<br>
					<br></li>
					<li class="sidebar-brand"><a href="#"> Project </a></li>
					<li><a href="register">Project Registration</a></li>
					<li><a href="projectsettings">Project Settings</a></li>
				</ul>
			</div>
			<!-- #sidebar-wrapper -->

			<!-- page-content-wrapper -->
			<div id="page-content-wrapper">
				<div class="container-fluid">
					<!-- Application Registration -->
					<div class="row">
						<div class="col-lg-12 text-center">
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<h2>Project Registration</h2>
							<hr class="star-primary">
						</div>
					</div>
					
					
					<div class="row" align="center">
						<div style="width:400px">
		                    <div class="panel panel-primary">
		                        <div class="panel-heading">
		                            <div class="row">
		                                <div class="col-xs-3">
		                                    <i class="fa fa-tasks fa-5x"></i>
		                                </div>
		                                <div class="col-xs-9 text-right">
		                                <%
		                                	String email = (String)session.getAttribute("email");
		                                	Integer projectcount = (Integer)session.getAttribute("projectcount");
                                		
		                                	if(email == null || email.isEmpty())
		                                	{
		                                		out.println("<div>로그인해주세요.</div>");	
		                                	}
		                                	else{
		                                		
		                                		out.println("<div>User Projects</div>");
		                                		out.println("<div style='font-size: 40px;'>"+projectcount.intValue()+"/3</div>");
		                                	}
		                                %>
		                                </div>
		                            </div>
		                        </div>
		                    </div>
		                </div>
	                </div>
	                
	                <%
	                //로그인 상태일때
	                if(email != null || !email.isEmpty())
                	{
	                	//프로젝트 개수가 3개 이하일 경우 폼 활성화
	                	if(projectcount.intValue()<3)
	                	{
	                %>
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
							<!-- form -->
							<form action="register_db" method="POST" name="AppRegister"
								id="AppRegister" novalidate>
								<!-- Application Name -->
								<div class="row control-group">
									<div
										class="form-group col-xs-12 floating-label-form-group controls">
										<label>Application Name</label> <input type="text"
											name="project_name" class="form-control"
											placeholder="Application Name" id="project_name" required
											data-validation-required-message="Please enter Application name.">
										<p class="help-block text-danger"></p>
									</div>
								</div>
								<!-- Summary -->
								<div class="row control-group">
									<div
										class="form-group col-xs-12 floating-label-form-group controls">
										<label>Summary</label> <input type="text" name="summary"
											class="form-control" placeholder="Summary" id="summary"
											required
											data-validation-required-message="Please enter Summary.">
										<p class="help-block text-danger"></p>
									</div>
								</div>
								<!-- Description -->
								<div class="row control-group">
									<div
										class="form-group col-xs-12 floating-label-form-group controls">
										<label>Description</label>
										<textarea name="description" rows="5" class="form-control"
											placeholder="Description" id="description" required
											data-validation-required-message="Please enter Description."></textarea>
										<p class="help-block text-danger"></p>
									</div>
								</div>

								<br>
								<!-- Register button -->
								<div class="row">
									<div class="form-group col-xs-12">
										<center>
											<button type="button" class="btn btn-success btn-lg" id="register" name="register" onclick="javascript:registerFormCheck();">Register</button>
										</center>
									</div>
								</div>
							</form>
							<!-- /#form -->
						</div>
					</div>
					
					<%
                		}
                	}
					%>
				</div>
			</div>
			<!-- #page-content-wrapper -->

		</div>
		<!-- #wrapper -->

	</div>

</body>

</html>
