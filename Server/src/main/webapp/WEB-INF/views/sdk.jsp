<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<jsp:include page="nav.jsp" flush="false" />

<script type="text/javascript">
			
			// 파일을 다운로드 한다. 
			function fileDownload_sdk() 
			{
				// Script 상에서 동적으로 form을 생성하여 직접 다운로드를 요청한다.
		    	var method = "post";
		    	var form = document.createElement("form");
		    	var filename = "RAP.jar";
		    
		    	if(filename == "")
		    		return;
		    
		    	form.setAttribute("method", "post");
		    	form.setAttribute("action", "./FileDownload_lanace");
		    
		    	var hiddenField = document.createElement("input");
		        hiddenField.setAttribute("type", "hidden");
		        hiddenField.setAttribute("name", "filename");
		        hiddenField.setAttribute("value", filename);
		        form.appendChild(hiddenField);
		    
		    	document.body.appendChild(form);
		    	form.submit();
			}
			
			// 파일을 다운로드 한다. 
			function fileDownload_api() 
			{
				// Script 상에서 동적으로 form을 생성하여 직접 다운로드를 요청한다.
		    	var method = "post";
		    	var form = document.createElement("form");
		    	var filename = "doc.zip";
		    
		    	if(filename == "")
		    		return;
		    
		    	form.setAttribute("method", "post");
		    	form.setAttribute("action", "./FileDownload_lanace");
		    
		    	var hiddenField = document.createElement("input");
		        hiddenField.setAttribute("type", "hidden");
		        hiddenField.setAttribute("name", "filename");
		        hiddenField.setAttribute("value", filename);
		        form.appendChild(hiddenField);
		    
		    	document.body.appendChild(form);
		    	form.submit();
			}
			
			function fileDownload_demo() 
			{
				// Script 상에서 동적으로 form을 생성하여 직접 다운로드를 요청한다.
		    	var method = "post";
		    	var form = document.createElement("form");
		    	var filename = "demo.zip";
		    
		    	if(filename == "")
		    		return;
		    
		    	form.setAttribute("method", "post");
		    	form.setAttribute("action", "./FileDownload_lanace");
		    
		    	var hiddenField = document.createElement("input");
		        hiddenField.setAttribute("type", "hidden");
		        hiddenField.setAttribute("name", "filename");
		        hiddenField.setAttribute("value", filename);
		        form.appendChild(hiddenField);
		    
		    	document.body.appendChild(form);
		    	form.submit();
			}
		</script>

<!-- Header -->
<header>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<img src="./resources/image/sdk_image.png" width="150" height="150">
				<div class="intro-text">
					<span class="name" style="font-size: 3em;">R.A.P</span> 
					<span class="skills">Report - Analysis - Promotion</span>
					<div style="margin: 10">
						<button type="button" class="btn btn-primary" onclick="fileDownload_sdk()">SDK 다운로드</button>
						<button type="button" class="btn btn-primary" onclick="fileDownload_api()">API 문서</button>
						<button type="button" class="btn btn-primary" onclick="fileDownload_demo()">Demo App</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</header>

	<body style="background: #18bc9c;">
		
	</body>
</html>