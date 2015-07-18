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

<script type="text/javaScript">

$(document).ready(function(){getLcategory()});

function getLcategory()
{
	$('#Lcategory1').html("<option value='' selected>해당없음</option>");
	$('#Lcategory2').html("<option value='' selected>해당없음</option>");
	$('#Lcategory3').html("<option value='' selected>해당없음</option>");
	
	$.ajax({
		url : "Lcategory_db",
		type : "POST",
		dataType : "JSON",
		success : function(data) {
			
			if(data!=null || data!="")
			{
				var list = data.categoryLlist;
				var listLen = list.length;
				
				for(var i=0;i<listLen;i++)
				{
					$('#Lcategory1').append("<option value='"+list[i].categoryL+"'>"+list[i].categoryL+"</option>");
					$('#Lcategory2').append("<option value='"+list[i].categoryL+"'>"+list[i].categoryL+"</option>");
					$('#Lcategory3').append("<option value='"+list[i].categoryL+"'>"+list[i].categoryL+"</option>");
					
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
function getMcategory(id)
{
	$('#Mcategory'+id).html("<option value='' selected>해당없음</option>");
	$('#Scategory'+id).html("<option value='' selected>해당없음</option>");
	
	var param = "categoryL=" + document.getElementById('Lcategory'+id).value;
	
	$.ajax({
		url : "Mcategory_db",
		type : "POST",
		data : param,
		dataType : "JSON",
		success : function(data) {
			
			if(data!=null || data!="")
			{
				var list = data.categoryMlist;
				var listLen = list.length;
				
				for(var i=0;i<listLen;i++)
				{
					$('#Mcategory'+id).append("<option value='"+list[i].categoryM+"'>"+list[i].categoryM+"</option>");
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
function getScategory(id)
{
	$('#Scategory'+id).html("<option value='' selected>해당없음</option>");
	
	var param = "categoryM=" + document.getElementById('Mcategory'+id).value;
	
	$.ajax({
		url : "Scategory_db",
		type : "POST",
		data : param,
		dataType : "JSON",
		success : function(data) {
			
			if(data!=null || data!="")
			{
				var list = data.categorySlist;
				var listLen = list.length;
				
				for(var i=0;i<listLen;i++)
				{
					$('#Scategory'+id).append("<option value='"+list[i].categoryS+"'>"+list[i].categoryS+"</option>");
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

function registerLcategory() {

	var project_name = "<%= currentprojectname %>";
	var Lcategory = document.getElementById('CategoryL');
	
	var param = "project_name" + "=" + project_name + "&" 
				+ "Lcategory" + "=" + Lcategory.value;
	
	$.ajax({
		url : "registerLcategory",
		type : "POST",
		data : param,
		success : function(data) {
			if(data == "200")
				alert("대분류가 추가되었습니다.");
			else if(data == "2")
				alert("대분류명을 입력해주세요");
			else if(data == "3")
				alert("이미 같은 이름의 카테고리가 존재합니다.");
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

function registerMcategory() {

	var project_name = "<%= currentprojectname %>";
	var Mcategory = document.getElementById('CategoryM');
	var Lcategory = document.getElementById('Lcategory2');
	
	var param = "project_name" + "=" + project_name + "&" 
				+ "Lcategory" + "=" + Lcategory.value + "&" 
				+ "Mcategory" + "=" + Mcategory.value;
	
	$.ajax({
		url : "registerMcategory",
		type : "POST",
		data : param,
		success : function(data) {
			if(data == "200")
				alert("중분류가 추가되었습니다.");
			else if(data == "2")
				alert("대분류명을 입력해주세요");
			else if(data == "3")
				alert("중분류명을 입력해주세요");
			else if(data == "4")
				alert("이미 같은 이름의 카테고리가 존재합니다.");
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

function registerScategory() {

	var project_name = "<%= currentprojectname %>";
	var Scategory = document.getElementById('CategoryS');
	var Mcategory = document.getElementById('Mcategory3');
	var Lcategory = document.getElementById('Lcategory3');
	
	var param = "project_name" + "=" + project_name + "&" 
				+ "Scategory" + "=" + Scategory.value + "&" 
				+ "Lcategory" + "=" + Lcategory.value + "&" 
				+ "Mcategory" + "=" + Mcategory.value;
	
	$.ajax({
		url : "registerScategory",
		type : "POST",
		data : param,
		success : function(data) {
			if(data == "200")
			{
				alert("소분류가 추가되었습니다.");
			}			
			else if(data == "2")
				alert("대분류명을 입력해주세요");
			else if(data == "3")
				alert("중분류명을 입력해주세요");
			else if(data == "4")
				alert("소분류명을 입력해주세요");
			else if(data == "5")
				alert("이미 같은 이름의 카테고리가 존재합니다.");
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


function deleteLcategory() {

	var project_name = "<%= currentprojectname %>";
	var Lcategory = document.getElementById('Lcategory1');
	
	var param = "project_name" + "=" + project_name + "&" 
				+ "Lcategory" + "=" + Lcategory.value;
	
	$.ajax({
		url : "deleteLcategory",
		type : "POST",
		data : param,
		success : function(data) {
			if(data == "200")
				alert("대분류가 추가되었습니다.");
			else if(data == "2")
				alert("대분류명을 입력해주세요");
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
									<label>Large Category</label> 
									<input type="text"
										class="form-control" placeholder="Large Category"
										id="CategoryL" required
										data-validation-required-message="Please enter Large Category." style="width:300px">
									<button class="btn" onclick="registerLcategory()">Register</button>
									
									<select id="Lcategory1" name="Lcategory1">
									</select>
									<button class="btn" onclick="deleteLcategory()">Delete</button>
								</div>
								
								
							</div>
							<br>
							
							<!-- Medium Category -->
							<div>
								
								<div class="form-group" style="padding:20px">
									<label>Medium Category</label> 
									<select id="Lcategory2" name="Lcategory2" onchange="getMcategory('2')">
									</select>
									<input type="text"
										class="form-control" placeholder="Medium Category"
										id="CategoryM" required
										data-validation-required-message="Please enter Medium Category." style="width:300px">
									<button class="btn" onclick="registerMcategory()">Register</button>
									<select id="Mcategory2" name="Mcategory2">
										<option value='' selected>해당없음</option>
									</select>
									<button class="btn" onclick="deleteMcategory()">Delete</button>
								</div>
							</div>
							<br>
							
							<!-- Small Category -->
							<div>
								<div class="form-group" style="padding:20px">
									<label>Small Category</label> 
									<select id="Lcategory3" name="Lcategory3" onchange="getMcategory('3')">
									</select>
									<select id="Mcategory3" name="Mcategory3" onchange="getScategory('3')">
										<option value='' selected>해당없음</option>
									</select>
									<input type="text"
										class="form-control" placeholder="Small Category"
										id="CategoryS" required
										data-validation-required-message="Please enter Small Category." style="width:300px">
									<button class="btn" onclick="registerScategory()">Register</button>
									<select id="Scategory3" name="Scategory3" >
										<option value="" selected>해당없음</option>
									</select>
									<button class="btn" onclick="deleteScategory()">Delete</button>
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
