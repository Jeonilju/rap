<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
<%   
response.setHeader("Cache-Control","no-store");   
response.setHeader("Pragma","no-cache");   
response.setDateHeader("Expires",0);   
if (request.getProtocol().equals("HTTP/1.1")) 
        response.setHeader("Cache-Control", "no-cache"); 
%> 
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>R.A.P</title>

<!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
<link href="./resources/css/bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="./resources/css/freelancer.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="./resources/css/simple-sidebar.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="./resources/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->


<!-- jQuery -->
<script src="./resources/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="./resources/js/bootstrap.min.js"></script>

<!-- Plugin JavaScript -->
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
<script src="./resources/js/classie.js"></script>
<script src="./resources/js/cbpAnimatedHeader.js"></script>

<!-- Contact Form JavaScript -->
<script src="./resources/js/jqBootstrapValidation.js"></script>
<script src="./resources/js/contact_me.js"></script>
<!-- Custom Theme JavaScript -->
<script src="./resources/js/sb-admin-2.js"></script>

<!-- Custom Theme JavaScript -->
<script src="./resources/js/freelancer.js"></script>
</head>

<script language="javascript" charset='UTF-8'>

var count =0;

function idCheck(){
	
	var text = $("#email").val();
	
    var regexp = /[0-9a-zA-Z]/; // 숫자,영문,특수문자
    // var regexp = /[0-9]/; // 숫자만
//     var regexp = /[a-zA-Z]/; // 영문만
    
    for(var i=0; i<text.length; i++){
        if(text.charAt(i) != " " && regexp.test(text.charAt(i)) == false ){
			alert("한글이나 특수문자는 입력불가능 합니다.");
			return false;
		}
    }
    
    count=1;
    overlapCheck();
    
    
}

function overlapCheck(){
	
	var param = "email" + "=" + $("#email").val();
	if($("#email").val() == '' || $("#email").val()==null)
	{
		alert("아이디를 입력하세요");
		return false;
	}
	
	$.ajax({
		url : "overlaptest_db",
		type : "POST",
		data : param,
		cache : false,
		async : false,
		dataType : "text",

		success : function(response) {				
			if(response=='0')
			{
				
				count = 1;
				alert("아이디가 중복이 되지 않습니다. 쓰셔도 됩니다.")
				
			}
			else
			{
				alert("아이디가 중복이 됩니다. 다시 입력 해주세요");
				count=0;
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

function formCheck() {
	var email = document.getElementById('email');
	var password = document.getElementById('password');
	var password_check = document.getElementById('password_check');

    count=1;
	if (email.value == '' || email.value == null) {
		alert('email를 입력하세요');
		focus.email;
		return false;
	}

	if (password.value == '' || password.value == null) {
		alert('비밀번호를 입력하세요');
		focus.password;
		return false;
	}
	
	if (password_check.value == '' || password_check.value == null) {
		alert('비밀번호확인란을 입력하세요');
		//focus.password_hint;
		return false;
	}
	
	/*비밀번호와 비밀번호확인란 같은지 확인*/
	if (password.value != password_check.value){
		alert("비밀번호와 비밀번호 확인란이 다릅니다.");
		focus.passowrd;
		return false;
	}
	
	if(count == 0)
	{
		alert("중복확인을 눌러주세요");
		return false;
	}
	else{
		save();
		//signupsuccess();
	}
	
	
}


function init(){
	count=0;
}

function signupsuccess(){
	history.go(0);

}

function save() {
	var str3 = document.getElementById("joinForm");
	str3.submit();
	alert("가입이 완료되었습니다. 재로그인해주세요");
}

function countCheck(){
	if(count==1){
		count=0;
	}
}


</script>
<!-- SignUpModal -->
<div class="modal fade" id="SignUpModal" tabindex="-1" role="dialog"
	aria-labelledby="SignUpModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="SignUpModalLabel">SIGN UP</h4>
			</div>

			<form action="signup_db" method="POST" id="joinForm" name="joinForm">
				<div class="modal-body">
					<div class="form-group">
						<label for="email" cond="">Email Address</label> <input
							name="email" id="email" type="email" required cond=""
							class="form-control" placeholder="Email Address" />
						<input type="button" value="중복확인" onclick='idCheck()'>
					</div>
					<div class="form-group">
						<label for="password" cond="">Password</label> <input
							name="password" id="password" type="password" required cond=""
							class="form-control" placeholder="Password" />
					</div>
					<div class="form-group">
						<label for="user_id" cond="">Password Confirm</label> <input
							name="password_check" id="password_check" type="password" required cond=""
							class="form-control" placeholder="Password Confirm" />
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						Close</button>											
					<button type="button"  class="btn btn-primary" onclick="javascript:formCheck();">Sign Up</button>
						
				</div>
			</form>





		</div>
		<!-- /.modal-content -->
	</div>
</div>
<!-- /.modal -->

<script language="JavaScript">

	function loginsuccess(){
		history.go(0);
	}

	function loginCheck(){
		var param = "Signin_email" + "=" + $("#Signin_email").val() + "&" +"Signin_password" + "="+ $("#Signin_password").val();
		$.ajax({
			url : "login_db",
			type : "POST",
			data : param,
			cache : false,
			async : false,
			dataType : "text",
	
			success : function(response) {								
				if(response=='1')
				{
					loginsuccess();
				}
				else
				{
					alert("아이디 또는 비번이 틀렸습니다. 다시 입력하세요.")
					return false;
				}	
				
				alert(check);
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

<!-- SignInModal -->
<div class="modal fade" id="SignInModal" tabindex="-1" role="dialog"
	aria-labelledby="SignInModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form name="frm" id = "frm" action = "/login_db">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="SignInModalLabel">SIGN IN</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="user_id" cond="">Email Address</label> <input
							name="Signin_email" id="Signin_email" type="email" required cond=""
							class="form-control" placeholder="Email Address" />
					</div>
					<div class="form-group">
						<label for="user_id" cond="">Password</label> <input name="Signin_password"
							id="Signin_password" type="password" required cond="" class="form-control"
							placeholder="Password" />
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						Close</button>
					<button type="button" class="btn btn-primary" onclick='loginCheck()'>Sign In</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
</div>
<!-- /.modal -->

<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index">R.A.P</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="nav nav-tabs"><a href="index">Home</a></li>

				<li class="dropdown"><a id="drop1" href="#" role="button"
					class="dropdown-toggle" data-toggle="dropdown">Developer <b
						class="caret"></b></a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
						<li role="presentation"><a role="menuitem" href="register">Project</a></li>
						<li role="presentation"><a role="menuitem"
							href="itemcategorization">Virtual Store</a></li>
						<li role="presentation"><a role="menuitem" href="promotions">Promotions</a></li>
						<li role="presentation"><a role="menuitem"
							href="operation_count">Analytics</a></li>
					</ul></li>
				<li class="dropdown"><a id="drop2" href="#" role="button"
					class="dropdown-toggle" data-toggle="dropdown">Resources <b
						class="caret"></b></a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
						<li role="presentation"><a role="menuitem" href="#">Download</a></li>
						<li role="presentation"><a role="menuitem" href="#">Documentation</a></li>
					</ul></li>
				
				<%
					String email = "";
					email = (String)session.getAttribute("email");
				
					if(email == null || email.isEmpty())
					{
				%>
				<li role="presentation"><button type="button"
						class="btn btn-default" data-toggle="modal"
						data-target="#SignUpModal" style="margin: 5px;">Sign Up</button></li>
				<li role="presentation"><button type="button" class="btn"
						data-toggle="modal" data-target="#SignInModal"
						style="margin: 5px;">Sign In</button></li>
				<%
					}
					else
					{
				%>
				<li role="presenxtation"><a><%=(String)session.getAttribute("email") %></a></li>
				<li role="presentation"><button type="button" class="btn"
						onclick="window.location.href='logout'" style="margin: 5px;">LogOut</button></li>
				<%
					}
				%>
			</ul>

		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
