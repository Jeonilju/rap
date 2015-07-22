<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page
	import="java.util.List, com.rap.models.PromotionInfo, com.rap.models.ProjectInfo, com.rap.models.MemberInfo"%>
<%
	ProjectInfo currentproject = (ProjectInfo)session.getAttribute("currentproject");
	String currentprojectname = "";
	if(currentproject != null)
		currentprojectname = currentproject.getProject_name();
		
%>

<!DOCTYPE html>
<html lang="en">
<!-- 네비게이션바 인클루드 -->
<jsp:include page="nav.jsp" flush="false" />
<script src="./resources/js/itemcategorization.js"></script>
<!-- <link rel="stylesheet" href="./resources/css/bootstrap-select.css">-->
<!-- <script src="./resources/js/bootstrap-select.js"></script>-->
<script type="text/javascript">
$(document).ready(function(){getAllLcategory()});
function getAllLcategory()
{
	var id = ['#Lcategory1','#Lcategory2','#Lcategory3'];
	getLcategory(id);
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
							<BR> <BR> <BR> <BR> <BR> <BR>
							<h2>Item Categorization</h2>
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
					<div class="row">
						<center>
							<BR><p>로그인해주세요.</p>
						</center>
					</div>
					<%
						}
						else
						{
					%>
					
					<div class="row">
						<!-- form -->
						<form class="form-inline" name="ItemCategorization"
							id="ItemCategorization" novalidate>
							<!-- Large Category -->
							<div>
								<div class="form-group" style="padding:20px">
									
									<input type="text"
										class="form-control" placeholder="Large Category"
										id="CategoryL" required
										data-validation-required-message="Please enter Large Category." style="width:200px">
									<button class="btn" onclick="registerLcategory()">Register</button>
									
									<select id="Lcategory1" name="Lcategory1">
									</select>
									<button class="btn" onclick="deleteLcategory('Lcategory1')">Delete</button>
								</div>
								
								
							</div>
							<br>
							
							<!-- Medium Category -->
							<div>
								
								<div class="form-group" style="padding:20px">
									<select id="Lcategory2" name="Lcategory2" onchange="getMcategory('2')">
									</select>
									<input type="text"
										class="form-control" placeholder="Medium Category"
										id="CategoryM" required
										data-validation-required-message="Please enter Medium Category." style="width:200px">
									<button class="btn" onclick="registerMcategory()">Register</button>
									<select id="Mcategory2" name="Mcategory2">
										<option value='' selected>해당없음</option>
									</select>
									<button class="btn" onclick="deleteMcategory('Mcategory2')">Delete</button>
								</div>
							</div>
							<br>
							
							<!-- Small Category -->
							<div>
								<div class="form-group" style="padding:20px">
									<select id="Lcategory3" name="Lcategory3" onchange="getMcategory('3')">
									</select>
									<select id="Mcategory3" name="Mcategory3" onchange="getScategory('3')">
										<option value='' selected>해당없음</option>
									</select>
									<input type="text"
										class="form-control" placeholder="Small Category"
										id="CategoryS" required
										data-validation-required-message="Please enter Small Category." style="width:200px">
									<button class="btn" onclick="registerScategory()">Register</button>
									<select id="Scategory3" name="Scategory3">
										<option value="" selected>해당없음</option>
									</select>
									<button class="btn" onclick="deleteScategory('Scategory3')">Delete</button>
								</div>
							</div>
							<br>
						</form>
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
