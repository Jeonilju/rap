function registerProject(){
	
	$.ajax({
		url : "projectregister_db",
		type : "POST",
		data : 
		{
			project_name:$("#project_name").val(),
			summary:$("#summary").val(),
			description:$("#description").val()
		},
		cache : false,
		async : false,
		dataType : "text",

		success : function(response) {				
			//중복되는 프로젝트 이름 존재않음
			if(response=='0')
			{
				location.href='projecthome';
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
			//길이 체크
			else if(response=='project_name')
			{
				alert("프로젝트 이름이 너무 깁니다.");
				return false;
			}
			//길이 체크
			else if(response=='summary')
			{
				alert("프로젝트 요약이 너무 깁니다.");
				return false;
			}
			//길이 체크
			else if(response=='description')
			{
				alert("프로젝트 설명이 너무 깁니다.");
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
