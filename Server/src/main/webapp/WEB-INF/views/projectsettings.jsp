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
			<div id="sidebar-wrapper">
				<ul class="sidebar-nav">
					<li><br> <br> <br></li>
					<li class="sidebar-brand"><a href="#"> Project </a></li>
					<li><a href="register">Project Registration</a></li>
					<li><a href="projectsettings">Project Settings</a></li>
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
							<h2> Project Settings </h2>
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
						List<ProjectInfo> projectlist = (List<ProjectInfo>) session.getAttribute("projectlist");
						int projectcount = (Integer) session.getAttribute("projectcount");
						
						for(int i=0;i<projectcount;i++)
						{
					%>
					<!-- project list 존재하는 경우 -->
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<h3 class="panel-title pull-left"><%= projectlist.get(i).getProject_name() %></h3>
							<i class="fa fa-trash pull-right"></i> <i
								class="fa fa-edit pull-right" style="margin-right: 4px;"></i>

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
					<!-- 
					<div class="row">
						<form class="form-inline" name="ItemCategorization"
							id="ItemCategorization" novalidate>
							<div>
								<div class="form-group" style="padding:20px">
									<label>Large Category</label> 
									<input type="text"
										class="form-control" placeholder="Large Category"
										id="CategoryL" required
										data-validation-required-message="Please enter Large Category." style="width:300px">
								</div>
								<div class="form-group">
									<button class="btn btn-default dropdown-toggle" type="button"
										id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="true">
										Dropdown <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
										<li><a href="#">Action</a></li>
										<li><a href="#">Another action</a></li>
										<li><a href="#">Something else here</a></li>
										<li><a href="#">Separated link</a></li>
									</ul>
								</div>
								<button type="submit" class="btn">Register</button>
							</div>
							<br>
						</form>
					</div>
					 -->
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
