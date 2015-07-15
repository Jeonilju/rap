<!-- sidebar-wrapper” -->
<%@ page import="java.util.List, com.rap.models.ProjectInfo"%>

<%
	ProjectInfo currentproject = (ProjectInfo)session.getAttribute("currentproject");
	String projectname = "";
	if(currentproject!=null)
		projectname = currentproject.getProject_name();
%>
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<li><br> <br> <br></li>
		<li class="sidebar-brand"><a href="#"> Project - <%= projectname %></a></li>
		<li role="presentation"><a role="menuitem" href="itemcategorization">Virtual Store</a></li>
		<li role="presentation"><a role="menuitem" href="promotions">Promotions</a></li>
		<li role="presentation"><a role="menuitem" href="operation_count">Analytics</a></li>
	</ul>
</div>
<!-- #sidebar-wrapper” -->
