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
		alert('�������� ��ϵǾ����ϴ�.');
		location.href='itemmanagement';
	}
	else if(result == 'index')
	{
		location.href='index';
	}
	else if(result=='error')
	{
		alert("������ �߻��߽��ϴ�.");
		location.href='itemmanagement';
	}	
	else if(result=='Lcategory')
	{
		alert("��з��� �Է����ּ���.");
		location.href='itemmanagement';
	}	
	else if(result=='Mcategory')
	{
		alert("�ߺз��� �Է����ּ���.");
		location.href='itemmanagement';
	}	
	else if(result=='Scategory')
	{
		alert("�Һз��� �Է����ּ���.");
		location.href='itemmanagement';
	}	
	else if(result=='ItemName')
	{
		alert("�������̸��� �Է����ּ���.");
		location.href='itemmanagement';
	}	
	else if(result=='ItemDescription')
	{
		alert("�����ۼ����� �Է����ּ���.");
		location.href='itemmanagement';
	}	
	else if(result=='ItemPrice')
	{
		alert("������ ������ ���������� �Է����ּ���.");
		location.href='itemmanagement';
	}	
	else if(result=='GoogleID')
	{
		alert("���������� ��� ���� ���̵� �Է����ּ���.");
		location.href='itemmanagement';
	}
	else if(result=='LongItemPrice'){
		alert("�ݾ��� �ʹ� Ů�ϴ�");
		location.href='itemmanagement';
	}
	else if(result=='LongItemName'){
		alert("�̸��� �ʹ� ��ϴ�.");
		location.href='itemmanagement';}
	else if(result=='LongItemDescription'){
		alert("������ �ʹ� ��ϴ�.");
		location.href='itemmanagement';}
	else if(result=='LongGoogleID'){
		alert("���� ���̵� �ʹ� ��ϴ�.");
		location.href='itemmanagement';}
	else if(result=='Coin')
	{
		alert("Coin�� �������ּ���.");
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