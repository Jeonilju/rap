<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.rap.models.ProjectInfo, com.rap.models.MemberInfo"%>

<html lang="en">
<!-- 네비게이션바 인클루드 -->
<jsp:include page="nav.jsp" flush="false" />
<style>
body { 
  font-family: Tahoma, 돋움, Geneva, sans-serif;
}
</style>
<script>
function selectProject(projectname){
	
	$.ajax({
		url : "selectProject",
		type : "POST",
		data : {currentprojectname: projectname},
		cache : false,
		async : false,
		dataType : "text",

		success : function(response) {				
			location.href='projectsettings';
		},
		
		error : function(request, status, error) {
			if (request.status != '0') {
				alert("code : " + request.status + "\r\nmessage : "
						+ request.reponseText + "\r\nerror : " + error);
			}
		}


	});
}
function deleteProject(projectname)
{
	if(confirm(projectname+' 프로젝트를 삭제하시겠습니까?'))
	{
		var param = "projectname" + "=" + projectname;
		$.ajax({
			url : "projectdelete",
			type : "POST",
			data : param,
			cache : false,
			async : false,
			dataType : "text",
	
			success : function(response) {								
				if(response=='200')
				{
					alert('삭제가 완료되었습니다.');
					location.href='projecthome';
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

function register()
{
	location.href='projectregister';	
}
</script>

<body id="page-top" class="index">
	<div class="container">
		<!-- wrapper -->
		<div id="wrapper">
			<!-- sidebar-wrapper -->
			<div id="sidebar-wrapper">
				<ul class="sidebar-nav">
					<li><br> <br> <br></li>
					<li class="sidebar-brand"><a href="#"> Project </a></li>
					<li role="presentation"><a role="menuitem" href="projecthome">Project Home</a></li>
					<li role="presentation"><a role="menuitem" href="projectregister">Register</a></li>
				</ul>
			</div>
			<!-- /#sidebar-wrapper -->

			<!-- page-content-wrapper -->
			<div id="page-content-wrapper">
				<div class="container-fluid">
					<!-- Item Categorization -->
					<div class="row">
						<div class="col-lg-12 text-center">
							<BR> <BR> <BR> <BR> <BR> <BR>
							<h2> Project HOME </h2>
						</div>
					</div>
					<br>
					<%
					MemberInfo member = (MemberInfo)session.getAttribute("currentmember");
					String email = "";
				
					if(member != null) email = member.getEmail();
				
					if(email == null || email.isEmpty())
					{
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
					}
					else
					{
						List<ProjectInfo> projectlist = (List<ProjectInfo>) request.getAttribute("projectlist");
						int projectcount;
						if(projectlist == null) projectcount = 0;
						else projectcount = (Integer)request.getAttribute("projectcount");
						
						for(int i=0;i<projectcount;i++)
						{
					%>
					<!-- project list 존재하는 경우 -->
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<h3 class="panel-title pull-left" style="cursor:pointer">
							<a onclick="selectProject('<%= projectlist.get(i).getProject_name() %>')"><%= projectlist.get(i).getProject_name() %></a>
							</h3>
							<a onclick="deleteProject('<%= projectlist.get(i).getProject_name() %>')" style="cursor:pointer"><i class="fa fa-trash pull-right"></i></a>
							
						</div>
						<div class="panel-body">
							<div style="font-family:sans-serif;">summary : <%= projectlist.get(i).getSummary() %></div>
							<div>description : <%= projectlist.get(i).getDescription() %></div>
							<div>projectkey : <%= projectlist.get(i).getPk() %></div>
						</div>
						<div class="panel-footer">
							<div class="pull-right"><%= projectlist.get(i).getReg_date() %></div>
							<br>
						</div>
					</div>
					
					<%
						}
						if(projectcount == 0){
							out.println("<div class='row'><center>");
							out.println("<center><button type='button' class='btn btn-success btn-lg' onclick='register();'>프로젝트 등록하기</button></center>");
							out.println("</center></div>");
						}
					}
					 %>
			<!-- /#form -->
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
