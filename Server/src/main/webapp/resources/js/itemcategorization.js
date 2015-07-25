/**
 * 
 */
function getLcategory(id)
{
	var idLen = id.length;
	for(var i=0;i<idLen;i++)
		{
			$(id[i]).html("<option value='' selected>해당없음</option>");
		}
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
					for(var j=0;j<idLen;j++)
					{
					$(id[j]).append("<option value='"+list[i].categoryL+"'>"+list[i].categoryL+"</option>");
					}
				}
				
				for(var j=0;j<idLen;j++)
					$(id[j]).selectpicker('refresh');
					
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
	
	$.ajax({
		url : "Mcategory_db",
		type : "POST",
		data : 
		{
			categoryL:document.getElementById('Lcategory'+id).value
		},
		dataType : "JSON",
		success : function(data) {
			
			if(data!=null || data!="")
			{
				var list = data.categoryMlist;
				var listLen = list.length;
				
				for(var i=0;i<listLen;i++)
				{
					$('#Mcategory'+id).append("<option value='"+list[i].categoryM+"'>"+list[i].categoryM+"</option>");
					$('#Mcategory'+id).selectpicker('refresh');
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
	
	$.ajax({
		url : "Scategory_db",
		type : "POST",
		data : 
		{
			categoryL : document.getElementById('Lcategory'+id).value,
			categoryM : document.getElementById('Mcategory'+id).value
		},
		dataType : "JSON",
		success : function(data) {
			
			if(data!=null || data!="")
			{
				var list = data.categorySlist;
				var listLen = list.length;
				
				for(var i=0;i<listLen;i++)
				{
					$('#Scategory'+id).append("<option value='"+list[i].categoryS+"'>"+list[i].categoryS+"</option>");
					$('#Scategory'+id).selectpicker('refresh');
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
	
	$.ajax({
		url : "registerLcategory",
		type : "POST",
		data : 
		{
			Lcategory: document.getElementById('CategoryL').value
		},
		dataType : "text",
		success : function(response) {
			if(response == "200")
				{
				alert("대분류가 추가되었습니다.");
				}
			else if(response == "Project Not Found")
				alert("프로젝트가 존재하지 않습니다.");
			else if(response == "Enter Lcategory")
				alert("대분류명을 입력해주세요.");
			else if(response == "Lcategory already exist")
				alert("이미 같은 이름의 카테고리가 존재합니다.");
			else
				alert('에러가 발생했습니다.');
		},

		error : function(request, status, error) {
			if (request.status != '0') {
				alert("code : " + request.status + "\r\nmessage : "
						+ request.reponseText + "\r\nerror : " + error);
			}
		}
	});
	getAllLcategory();
	
}

function registerMcategory() {
	
	$.ajax({
		url : "registerMcategory",
		type : "POST",
		data : 
		{
			Lcategory : document.getElementById('Lcategory2').value,
			Mcategory : document.getElementById('CategoryM').value
		},
		dataType : "text",
		success : function(response) {
			if(response == "200")
				{
				alert("중분류가 추가되었습니다.");
				
				}
			else if(response == "Project Not Found")
				alert("프로젝트가 존재하지 않습니다.");
			else if(response == "Enter Lcategory")
				alert("대분류명을 입력해주세요");
			else if(response == "Enter Mcategory")
				alert("중분류명을 입력해주세요");
			else if(response == "Mcategory already exist")
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

	$.ajax({
		url : "registerScategory",
		type : "POST",
		data : 
		{
			Lcategory : document.getElementById('Lcategory3').value,
			Mcategory : document.getElementById('Mcategory3').value,
			Scategory : document.getElementById('CategoryS').value
		},
		dataType : "text",
		success : function(response) {
			if(response == "200")
			{
				alert("소분류가 추가되었습니다.");
			}
			else if(response == "Project Not Found")
				alert("프로젝트가 존재하지않습니다.");
			else if(response == "Enter Lcategory")
				alert("대분류명을 입력해주세요");
			else if(response == "Enter Mcategory")
				alert("중분류명을 입력해주세요");
			else if(response == "Enter Scategory")
				alert("소분류명을 입력해주세요");
			else if(response == "Scategory already exist")
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

	$.ajax({
		url : "deleteLcategory",
		type : "POST",
		data : 
		{
			Lcategory : document.getElementById(id).value
		},
		dataType : "text",
		success : function(response) {
			if(response == "200")
			{
				alert("대분류가 삭제되었습니다.");
				$('#'+id).selectpicker('refresh');

			}
			else if(response == "Project Not Found")
				alert("프로젝트가 존재하지않습니다.");
			else if(response == "Enter Lcategory")
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

	$.ajax({
		url : "deleteMcategory",
		type : "POST",
		data : 
		{
			Lcategory : document.getElementById('Lcategory2').value,
			Mcategory : document.getElementById(id).value
		},
		dataType : "text",
		success : function(response) {
			if(response == "200")
				alert("중분류가 삭제되었습니다.");
			else if(response == "Project Not Found")
				alert("프로젝트가 존재하지않습니다.");
			else if(response == "Enter Lcategory")
				alert("대분류명을 입력해주세요");
			else if(response == "Enter Mcategory")
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

	$.ajax({
		url : "deleteScategory",
		type : "POST",
		data : 
		{
			Lcategory : document.getElementById('Lcategory3').value,
			Mcategory : document.getElementById('Mcategory3').value,
			Scategory : document.getElementById(id).value
		},
		dataType : "text",
		success : function(response) {
			if(response == "200")
				alert("소분류가 삭제되었습니다.");
			else if(response == "Project Not Found")
				alert("프로젝트가 존재하지않습니다.");
			else if(response == "Enter Lcategory")
				alert("대분류명을 입력해주세요");
			else if(response == "Enter Mcategory")
				alert("중분류명을 입력해주세요");
			else if(response == "Enter Scategory")
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