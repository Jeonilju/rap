<!-- sidebar-wrapper” -->
<%@ page import="java.util.List, com.rap.models.ProjectInfo"%>

	<%
		ProjectInfo project = (ProjectInfo) session.getAttribute("currentproject");
		String projectname;

		if(project==null) projectname = "Project";
		else projectname = project.getProject_name();

		%>
		<script type="text/javascript">

		function aaa()
		{
			$("#appinfo2").attr('class','accordion-body collapse');			
			$("#iapinfo2").attr('class','accordion-body collapse');
			
		}
		
		function bbb()
		{
			$("#userinfo2").attr('class','accordion-body collapse');			
			$("#iapinfo2").attr('class','accordion-body collapse');
			
		}
		
		function ccc()
		{
			$("#appinfo2").attr('class','accordion-body collapse');			
			$("#userinfo2").attr('class','accordion-body collapse');
			
		}

	
	
	

</script>

<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<li><br> <br> <br></li>
		<li class="sidebar-brand"><a href="#"> <%=projectname %> </a></li>
		<li role="presentation"><a role="menuitem" href="projectsettings">Project Settings</a></li>
		<li role="presentation"><a role="menuitem" href="itemmanagement">Virtual Store</a></li>
		<li role="presentation"><a role="menuitem" href="promotions">Promotions</a></li>	
		<li role="presentation">			
		
				<div class="row">
					<div class="span12">
						<div class="menu">
							<div class="accordion">
								<!-- Áreas -->
								<div class="accordion-group">
									<!-- Área -->
									<div class="accordion-heading area">
										<a class="accordion-toggle sidebar-brand" data-toggle="collapse" 
											href="#basicuserinfo" id="analytics"  > Analytics </a>
									</div>
									<!-- /Área -->
		
									<div class="accordion-body collapse" id="basicuserinfo" >
										<div class="accordion-inner">
											<div class="accordion" id="equipamento1" >
												<!-- Equipamentos -->
		
												<div class="accordion-group">
													<div class="accordion-heading equipamento">
														<a class="accordion-toggle collapse" data-toggle="collapse" id="userinfo" onclick="aaa()"
															href="#userinfo2" style="padding-left:10px" >Basic User Info</a>
		
														<div class="accordion-body collapse"  id="userinfo2">
															<div class="accordion-inner">
																<ul class="nav nav-list">
																 
																	<!-- <li><a href="new_member" target="#page-content-wrapper">New user</a></li> -->
																	<li><a href="sex" >Sex ratio</a></li>
																	<li><a href="age">Age</a></li>
																	<li><a href="os">OS</a></li>
																	<li><a href="device">Device</a></li>
																	<li><a href="map">Location</a></li>
																	
																</ul>
															</div>
														</div>
														<!-- /Serviços -->
														<!-- Pontos -->
													</div>
													<div class="accordion-heading equipamento">
														<a class="accordion-toggle collapse" data-toggle="collapse"  id="appinfo" onclick="bbb()"
															href="#appinfo2"  style="padding-left:10px">Application Info</a>
		
														<div class="accordion-body collapse" id="appinfo2">
															<div class="accordion-inner">
																<ul class="nav nav-list">
																	<li><a href="new_member" >New user</a></li>
																	<li><a href="operation_count">Operation count</a></li>
																	<li><a href="operation_time">Operation time</a></li>
																	<li><a href="best_activity">Best activity</a></li>																	
																	<li><a href="promotions_analysis">Promotions analysis</a></li>
																	<li><a href="activity_path">Activity path</a></li>
																</ul>
															</div>
														</div>
													</div>
		
													<div class="accordion-heading equipamento" >
														<a class="accordion-toggle" data-toggle="collapse"  id="iapinfo" onclick="ccc()"
															href="#iapinfo2"  style="padding-left:10px">IAP Info</a>
		
														<div class="accordion-body collapse"   id="iapinfo2">
															<div class="accordion-inner">
																<ul class="nav nav-list">
																	<li><a href="sales_ranking">Sales Ranking</a></li>
																	<li><a href="IAP_amount">IAP amount</a></li>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- /accordion -->
					
				</div>
			</div>
		</li>
	</ul>
</div>
<!-- #sidebar-wrapper” -->
