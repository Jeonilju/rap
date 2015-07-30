<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<jsp:include page="nav.jsp" flush="false" />

<script type="text/javascript">
			
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
		</script>

<!-- Header -->
<header>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<img src="./resources/image/apiDocument.png" width="150" height="150">
				<div class="intro-text">
					<span class="name" style="font-size: 3em;">R.A.P</span> 
					<span class="skills">Report - Analysis - Promotion</span>
					<div style="margin: 10">
						<button type="button" class="btn btn-primary" onclick="fileDownload_api()">API문서 다운로드</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</header>

<body style="background: #18bc9c;">
	<div class="row" style="text-align: center;">
		<div class="col-lg-2"></div>
		<div class="col-lg-2">
			<div>
				<img src="./resources/image/virtual_store.png" width="100"
					height="100">
			</div>

			<div>
				<a style="font-weight: bold; font-size: 2em; color: white">virtual store</a> <br>
				<br>
			</div>

			<div>
				<a style="font-weight: bold; font-size: 1em; color: white">
					Setting up a virtual store that contains both virtual currencies
					and virtual goods.</a> <br>
				<br>
			</div>

		</div>

		<div class="col-lg-1"></div>

		<div class="col-lg-2">
			<div>
				<img src="./resources/image/promotion.png" width="100" height="100">
			</div>

			<div>
				<a style="font-weight: bold; font-size: 2em; color: white">Promotions</a> 
				<br>
				<br>
			</div>

			<div>
					<a style="font-weight: bold; font-size: 1em; color: white">Creating promotions through dashboard.</a> <br>
				<br>
			</div>
		</div>
		<div class="col-lg-1"></div>
			<div class="col-lg-2">
				<div>
					<img src="./resources/image/analysis.png" width="100" height="100">
				</div>

				<div>
					<a style="font-weight: bold; font-size: 2em; color: white">Analytics</a> 
					<br>
					<br>
				</div>

				<div>
					<a style="font-weight: bold; font-size: 1em; color: white">
					Analyze user according to multiple criteria.</a> <br>
				<br>
				</div>
			</div>
		</div>
	</body>
</html>