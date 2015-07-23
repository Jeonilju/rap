<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.rap.models.ProjectInfo, com.rap.models.MemberInfo"%>

<html lang="en">
<!-- 네비게이션바 인클루드 -->
<jsp:include page="nav.jsp" flush="false" />

<!-- Custom Js -->
<script src="./resources/js/projectRegister.js"></script>

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
										MemberInfo member = (MemberInfo)session.getAttribute("currentmember");
										String email = "";
									
										if(member != null) email = member.getEmail();
									
										if(email == null || email.isEmpty())
										{
											out.println("<div>로그인해주세요.</div>");	
										}
										else
										{
											ProjectInfo currentproject = (ProjectInfo)session.getAttribute("currentproject");
											Integer projectcount = (Integer) request.getAttribute("projectcount");
											out.println("<div>User Projects</div>");
											
											if(projectcount == null)
					                			out.println("<div style='font-size: 40px;'>0/3</div>");
					                		else
					                			out.println("<div style='font-size: 40px;'>"+projectcount.intValue()+"/3</div>");
					                	
										%>
		                                </div>
		                            </div>
		                        </div>
		                    </div>
		                </div>
	                </div>
	                
	                <%
	                	if(projectcount !=null && projectcount.intValue()<3)
		                {
	                %>
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
							<!-- form -->
							<form action="projectregister_db" method="POST" name="AppRegister"
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
