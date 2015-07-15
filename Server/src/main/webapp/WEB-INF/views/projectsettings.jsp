<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.rap.models.ProjectInfo"%>

<html lang="en">
<!-- 네비게이션바 인클루드 -->
<jsp:include page="nav.jsp" flush="false" />


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
							<BR> <BR> <BR> <BR> <BR> <BR>
							<h2> Project settings </h2>
						</div>
					</div>
					<br>
					<%
					String email = (String) session.getAttribute("email");
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
					}
					else
					{
						ProjectInfo currentproject = (ProjectInfo)session.getAttribute("currentproject");
						String projectname = currentproject.getProject_name();
						String projectdescription = currentproject.getDescription();
						String projectsummary = currentproject.getSummary();
					%>
					<!-- project list 존재하는 경우 -->
					<div class="row">
						<div class="panel-body">
							<div>수정가능하게 바꾸기~~~~~~~~~~~</div>
							<div>projectname : <%= projectname %></div>
							<div>summary : <%= projectsummary %></div>
							<div>description : <%= projectdescription %></div>
						</div>
					</div>
					
					<%
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
