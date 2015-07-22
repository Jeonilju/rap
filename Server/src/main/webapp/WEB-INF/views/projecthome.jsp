<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.rap.models.ProjectInfo, com.rap.models.MemberInfo"%>

<html lang="en">
<!-- 네비게이션바 인클루드 -->
<jsp:include page="nav.jsp" flush="false" />
<script>

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
					alert("에러")
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
						int projectcount = (Integer) request.getAttribute("projectcount");
						
						for(int i=0;i<projectcount;i++)
						{
					%>
					<!-- project list 존재하는 경우 -->
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<h3 class="panel-title pull-left"><a href="projectsettings?currentprojectname=<%= projectlist.get(i).getProject_name() %>"><%= projectlist.get(i).getProject_name() %></a></h3>
							<a onclick="deleteProject('<%= projectlist.get(i).getProject_name() %>')"><i class="fa fa-trash pull-right"></i></a>
							<a><i class="fa fa-edit pull-right" style="margin-right: 4px;"></i></a>

						</div>
						<div class="panel-body">
							<div>summary : <%= projectlist.get(i).getSummary() %></div>
							<div>description : <%= projectlist.get(i).getDescription() %></div>
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
							out.println("<br>등록된 프로젝트가 없습니다.");
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
