<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page
   import="com.rap.models.MemberInfo"%>
<head>
<%   
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=utf-8"); 

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
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
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
<!-- Custrom CSS -->
<link rel="stylesheet" href="./resources/css/bootstrap-select.css">
  
<!-- jQuery -->
<script src="./resources/js/jquery.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<!-- Select js -->
  <script src="./resources/js/bootstrap-select.js"></script>
 <script src="//cdnjs.cloudflare.com/ajax/libs/metisMenu/1.1.0/metisMenu.js"></script>

<!-- Plugin JavaScript -->
<script
   src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
<script src="./resources/js/classie.js"></script>
<!-- <script src="./resources/js/cbpAnimatedHeader.js"></script>-->

<!-- Contact Form JavaScript -->
<script src="./resources/js/jqBootstrapValidation.js"></script>
<!-- <script src="./resources/js/contact_me.js"></script>-->
<!-- Custom Theme JavaScript -->
<script src="./resources/js/sb-admin-2.js"></script>

<!-- Custom Theme JavaScript -->
<script src="./resources/js/freelancer.js"></script>
</head>

<script language="javascript" charset='UTF-8'>

var count =0;
var email = '';

function idCheck(){
   
   var email = $("#email").val();
   var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;   
     
   if(regex.test(email) === false) {  
       alert("잘못된 이메일 형식입니다.");  
       return false;  
   }
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
            email = $("#email").val();
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


function signup_db() {

   if(count==0 || email != $("#email").val())
   {
      alert('중복확인을 눌러주세요');
      return false;
   }
   
   $.ajax({
      url : "signup_db",
      type : "POST",
      data : 
      {
         email : document.getElementById('email').value,
         password : document.getElementById('password').value,
         password_check : document.getElementById('password_check').value
      },
      cache : false,
      async : false,
      dataType : "text",

      success : function(response) {                        
         if(response=='success')
         {
            location.href='index';
            alert('회원가입이 완료되었습니다.');
         }
         else if(response=='email')
         {
            alert('email을 입력해주세요.');
         }
         else if(response=='password')
         {
            alert('password를 입력해주세요.');
         }
         else if(response=='longemail')
         {
            alert('email이 너무 깁니다.');
         }
         else if(response=='longpassword')
         {
            alert('password가 너무 깁니다');
         }
         else if(response=='email')
         {
            alert('email을 입력해주세요.');
         }
         else if(response=='password')
         {
            alert('password를 입력해주세요.');
         }
         else if(response=='password_check')
         {
            alert('password confirm을 입력해주세요.');
         }
         else if(response=='password equality')
         {
            alert('패스워드가 일치하지 않습니다.');
         }
         else if(response=='overlap')
         {
            alert('이메일이 중복됩니다.');
         }
         else if(response=='error')
         {
            alert('에러가 발생했습니다.');
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
function onEnterSignUp()
{
   var keyCode = window.event.keyCode;
   if(keyCode==13) signup_db();
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
               <div class="form-inline" style="padding:3px">
                     <label for="email"  class="form-inline" style="padding-right:14px;padding-left:14px;">Email Address</label> 
                     <input name="email" id="email" type="email" class="form-control" placeholder="Email Address" style="width:300px"/>
                     <input type="button" class="btn"  value="중복확인" onclick='idCheck()'>
                  <br>
               </div>
               <div class="form-inline" style="padding:3px">
                  <label for="password" style="padding-right:29px;padding-left:29px;">Password</label>
                   <input name="password" id="password" type="password" style="width:390px" class="form-control" placeholder="Password" />
               </div>
               <div class="form-inline" style="padding:3px">
                  <label for="user_id" cond="">Password Confirm</label> <input
                     name="password_check" id="password_check" type="password" required cond=""
                     class="form-control" placeholder="Password Confirm" style="width:390px" onkeydown='onEnterSignUp()'  />
               </div>
            </div>

            <div class="modal-footer">
               <button type="button" class="btn btn-default" data-dismiss="modal">
                  Close</button>                                 
               <button type="button"  class="btn btn-primary" onclick="signup_db()">Sign Up</button>
                  
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
            else if(response=='0')
            {
               alert("이메일이 존재하지 않습니다.");
            }
            else if(response=='3')
            {
               alert("비밀번호가 틀렸습니다.");
            }
            else if(response=='email')
            {
               alert("이메일이 너무 깁니다.");
            }
            else if(response=='password')
            {
               alert("비밀번호가 너무 깁니다.");
            }
            else
            {
               return false;
            }   
            
            //alert(check);
         },
         error : function(request, status, error) {
            if (request.status != '0') {
               alert("code : " + request.status + "\r\nmessage : "
                     + request.reponseText + "\r\nerror : " + error);
            }
         }
   
      });
   }
   function onEnterSignIn()
   {
      var keyCode = window.event.keyCode;
      if(keyCode==13) loginCheck();
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
                     placeholder="Password" onkeydown='onEnterSignIn()' />
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
            <li class="nav">
               <a href="index">Home</a>
            </li>

            <li class="dropdown">
               <a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Developer<b class="caret"></b></a>
               
               <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
                  <li role="presentation">
                     <a role="menuitem" href="projecthome">Project</a>
                  </li>
               </ul>
            </li>
            <li class="dropdown">
               <a id="drop2" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Resources <b
                  class="caret"></b></a>
                  
               <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
                  <li role="presentation"><a role="menuitem" href="sdk">Download</a></li>
                  <li role="presentation"><a role="menuitem" href="ApiDoc">Documentation</a></li>
                  <li role="presentation"><a role="menuitem" href="getStart">Get Started</a></li>
                  <li role="presentation"><a role="menuitem" href="FAQ">FAQ</a></li>
               </ul></li>
            
            <%
               MemberInfo member = (MemberInfo)session.getAttribute("currentmember");
               String email = "";
            
               if(member != null) email = member.getEmail();
            
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
            <li role="presenxtation"><a><%=email %></a></li>
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