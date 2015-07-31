<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script>
window.onload = aaa;

function aaa()
{
	<% String result = (String)request.getAttribute("result");%>
	var result = "<%=result %>";
	
	if(result=='200')
	{
		alert('아이템이 등록되었습니다.');
		location.href='itemmanagement';
	}
	else if(result == 'index')
	{
		location.href='index';
	}
	else if(result=='error')
	{
		alert("에러가 발생했습니다.");
		location.href='itemmanagement';
	}	
	else if(result=='Lcategory')
	{
		alert("대분류를 입력해주세요.");
		location.href='itemmanagement';
	}	
	else if(result=='Mcategory')
	{
		alert("중분류를 입력해주세요.");
		location.href='itemmanagement';
	}	
	else if(result=='Scategory')
	{
		alert("소분류를 입력해주세요.");
		location.href='itemmanagement';
	}	
	else if(result=='ItemName')
	{
		alert("아이템이름을 입력해주세요.");
		location.href='itemmanagement';
	}	
	else if(result=='ItemDescription')
	{
		alert("아이템설명을 입력해주세요.");
		location.href='itemmanagement';
	}	
	else if(result=='ItemPrice')
	{
		alert("아이템 가격을 정상적으로 입력해주세요.");
		location.href='itemmanagement';
	}	
	else if(result=='GoogleID')
	{
		alert("실제결제의 경우 구글 아이디를 입력해주세요.");
		location.href='itemmanagement';
	}
	else if(result=='LongItemPrice'){
		alert("금액이 너무 큽니다");
		location.href='itemmanagement';
	}
	else if(result=='LongItemName'){
		alert("이름이 너무 깁니다.");
		location.href='itemmanagement';}
	else if(result=='LongItemDescription'){
		alert("설명이 너무 깁니다.");
		location.href='itemmanagement';}
	else if(result=='LongGoogleID'){
		alert("구글 아이디가 너무 깁니다.");
		location.href='itemmanagement';}
	else if(result=='Coin')
	{
		alert("Coin을 선택해주세요.");
		location.href='itemmanagement';
	}
	else
		{
		location.href='itemmanagement';
		}
}

</script>
<body>
</body>
</html>