/**
 * 
 */
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
	
	var param = "categoryL=" + document.getElementById('Lcategory'+id).value + 
		"&categoryM=" + document.getElementById('Mcategory'+id).value;
	
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

	var Lcategory = document.getElementById('CategoryL');
	
	var param = "Lcategory" + "=" + Lcategory.value;
	
	$.ajax({
		url : "registerLcategory",
		type : "POST",
		data : param,
		dataType : "text",
		success : function(data) {
			if(data == "200")
				alert("대분류가 추가되었습니다.");
			else if(data == "Project Not Found")
				alert("프로젝트가 존재하지 않습니다.");
			else if(data == "Enter Lcategory")
				alert("대분류명을 입력해주세요.");
			else if(data == "Lcategory already exist")
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

	var Mcategory = document.getElementById('CategoryM');
	var Lcategory = document.getElementById('Lcategory2');
	
	var param = "Lcategory" + "=" + Lcategory.value + "&" 
				+ "Mcategory" + "=" + Mcategory.value;
	
	$.ajax({
		url : "registerMcategory",
		type : "POST",
		data : param,
		dataType : "text",
		success : function(data) {
			if(data == "200")
				alert("중분류가 추가되었습니다.");
			else if(data == "Project Not Found")
				alert("프로젝트가 존재하지 않습니다.");
			else if(data == "Enter Lcategory")
				alert("대분류명을 입력해주세요");
			else if(data == "Enter Mcategory")
				alert("중분류명을 입력해주세요");
			else if(data == "Mcategory already exist")
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

	var Scategory = document.getElementById('CategoryS');
	var Mcategory = document.getElementById('Mcategory3');
	var Lcategory = document.getElementById('Lcategory3');
	
	var param = "Scategory" + "=" + Scategory.value + "&" 
				+ "Lcategory" + "=" + Lcategory.value + "&" 
				+ "Mcategory" + "=" + Mcategory.value;
	
	$.ajax({
		url : "registerScategory",
		type : "POST",
		data : param,
		dataType : "text",
		success : function(data) {
			if(data == "200")
			{
				alert("소분류가 추가되었습니다.");
			}
			else if(data == "Project Not Found")
				alert("프로젝트가 존재하지않습니다.");
			else if(data == "Enter Lcategory")
				alert("대분류명을 입력해주세요");
			else if(data == "Enter Mcategory")
				alert("중분류명을 입력해주세요");
			else if(data == "Enter Scategory")
				alert("소분류명을 입력해주세요");
			else if(data == "Scategory already exist")
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


function deleteLcategory(id) {

	var Lcategory = document.getElementById(id);
	
	var param = "Lcategory" + "=" + Lcategory.value;
	
	$.ajax({
		url : "deleteLcategory",
		type : "POST",
		data : param,
		dataType : "text",
		success : function(data) {
			if(data == "200")
				alert("대분류가 삭제되었습니다.");
			else if(data == "Project Not Found")
				alert("프로젝트가 존재하지않습니다.");
			else if(data == "Enter Lcategory")
				alert("대분류명을 입력해주세요");
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

function deleteMcategory(id) {

	var Mcategory = document.getElementById(id);
	var Lcategory = document.getElementById('Lcategory2');
	
	var param = "Lcategory" + "=" + Lcategory.value + "&" 
				+ "Mcategory" + "=" + Mcategory.value;
	
	$.ajax({
		url : "deleteMcategory",
		type : "POST",
		data : param,
		dataType : "text",
		success : function(data) {
			if(data == "200")
				alert("중분류가 삭제되었습니다.");
			else if(data == "Project Not Found")
				alert("프로젝트가 존재하지않습니다.");
			else if(data == "Enter Lcategory")
				alert("대분류명을 입력해주세요");
			else if(data == "Enter Mcategory")
				alert("중분류명을 입력해주세요");
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


function deleteScategory(id) {

	var Scategory = document.getElementById(id);
	var Mcategory = document.getElementById('Mcategory3');
	var Lcategory = document.getElementById('Lcategory3');
	
	var param = "Lcategory" + "=" + Lcategory.value + "&" 
				+ "Mcategory" + "=" + Mcategory.value + "&" 
				+ "Scategory" + "=" + Scategory.value;
	
	$.ajax({
		url : "deleteScategory",
		type : "POST",
		data : param,
		dataType : "text",
		success : function(data) {
			if(data == "200")
				alert("소분류가 삭제되었습니다.");
			else if(data == "Project Not Found")
				alert("프로젝트가 존재하지않습니다.");
			else if(data == "Enter Lcategory")
				alert("대분류명을 입력해주세요");
			else if(data == "Enter Mcategory")
				alert("중분류명을 입력해주세요");
			else if(data == "Enter Scategory")
				alert("소분류명을 입력해주세요");
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