<!-- sidebar-wrapper” -->
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<li><br> <br> <br></li>
		<li class="sidebar-brand"><a href="#"> Project </a></li>
		<%
			String email = (String)session.getAttribute("email");
    		if(email != null)
    			if(!email.isEmpty())
    			{
					out.println("<li><a href='projectregister'>Project Registration</a></li>");
    			}
		%>
	</ul>
</div>
<!-- #sidebar-wrapper” -->
