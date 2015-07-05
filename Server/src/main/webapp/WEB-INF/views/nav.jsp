<head>
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
    <link href="./resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

</head>

<!-- SignUpModal -->
<div class="modal fade" id="SignUpModal" tabindex="-1" role="dialog" 
   aria-labelledby="SignUpModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="SignUpModalLabel">
               	SIGN UP
            </h4>
         </div>
         <div class="modal-body">
			<div class="form-group">
				<label for="user_id" cond="">Email Address</label>
				<input name="user_id" id="user_id" type="email" required cond="" class="form-control" placeholder="Email Address" />
			</div>
			<div class="form-group">
				<label for="user_id" cond="">Password</label>
				<input name="user_pw" id="user_pw" type="password" required cond="" class="form-control" placeholder="Password" />
			</div>
			<div class="form-group">
				<label for="user_id" cond="">Password Confirm</label>
				<input name="user_pw" id="user_pw" type="password" required cond="" class="form-control" placeholder="Password Confirm" />
			</div>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal"> Close
            </button>
            <button type="button" class="btn btn-primary">
               	Sign Up
            </button>
         </div>
      </div><!-- /.modal-content -->
	</div>
</div><!-- /.modal -->

<!-- SignInModal -->
<div class="modal fade" id="SignInModal" tabindex="-1" role="dialog" 
   aria-labelledby="SignInModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="SignInModalLabel">
               	SIGN IN
            </h4>
         </div>
         <div class="modal-body">
			<div class="form-group">
				<label for="user_id" cond="">Email Address</label>
				<input name="user_id" id="user_id" type="email" required cond="" class="form-control" placeholder="Email Address" />
			</div>
			<div class="form-group">
				<label for="user_id" cond="">Password</label>
				<input name="user_pw" id="user_pw" type="password" required cond="" class="form-control" placeholder="Password" />
			</div>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal"> Close
            </button>
            <button type="button" class="btn btn-primary">
               	Sign In
            </button>
         </div>
      </div><!-- /.modal-content -->
	</div>
</div><!-- /.modal -->

<!-- Navigation -->
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header page-scroll">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index">R.A.P</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="nav nav-tabs">
                            <a href="index">Home</a>
                        </li>         

                        <li class="dropdown">                    
                            <a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Developer
                                <b class="caret"></b></a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
                                   <li role="presentation"><a role="menuitem"  href="register">Register</a></li>
                                   <li role="presentation"><a role="menuitem"  href="projectsettings">Virtual Store</a></li>
                                   <li role="presentation"><a role="menuitem"  href="promotions">Promotions</a></li>
                                   <li role="presentation"><a role="menuitem"  href="operation_count">Analytics</a></li>
                               </ul>

                           </li>
                           <li class="dropdown">
                            <a id="drop2" href="#"  role="button" class="dropdown-toggle" data-toggle="dropdown"   >Resources
                                <b class="caret"></b></a>
                                <ul class="dropdown-menu"  role="menu" aria-labelledby="drop1">
                                   <li role="presentation"><a role="menuitem"  href="#">Download</a></li>
                                   <li role="presentation"><a role="menuitem"  href="#">Documentation</a></li>
                               </ul>

                           </li>   
                            <li role="presentation"><button type="button" class="btn btn-default" data-toggle="modal" data-target="#SignUpModal" style=" margin: 5px;" >Sign Up</button></li>
                            <li role="presentation"><button type="button" class="btn" data-toggle="modal" data-target="#SignInModal" style=" margin: 5px;" >Sign In</button></li>
                       </ul>

                   </div>
                   <!-- /.navbar-collapse -->
               </div>
               <!-- /.container-fluid -->
           </nav>
